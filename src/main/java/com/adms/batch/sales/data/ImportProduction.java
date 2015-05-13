package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLClassLoader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.domain.ListLot;
import com.adms.batch.sales.domain.ProductionByLot;
import com.adms.batch.sales.service.ListLotService;
import com.adms.batch.sales.service.ProductionByLotService;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportProduction {
	
	public static final String BATCH_ID = "BATCH_ID";
	public static final String APPLICATION_CONTEXT_FILE = "application-context.xml";

	public static final String FILE_FORMAT_PRODUCTION_BY_LOT_TELE = "FileFormat_SSIS_DailyProductionByLot-input-TELE.xml";
	public static final String FILE_FORMAT_PRODUCTION_BY_LOT_OTO = "FileFormat_SSIS_DailyProductionByLot-input-OTO.xml";

	public static final String PRODUCTION_BY_LOT_SERVICE_BEAN = "productionByLotService";
	public static final String LIST_LOT_SERVICE_BEAN = "listLotService";

	private ApplicationContext applicationContext;
	protected Logger log = Logger.getLogger(Logger.DEBUG);

	protected void setLogLevel(int logLevel)
	{
		this.log.setLogLevel(logLevel);
	}


	protected Object getBean(String beanId)
	{
		if (this.applicationContext == null)
		{
			this.applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_FILE);
		}

		return this.applicationContext.getBean(beanId);
	}

	protected ProductionByLotService getProductionByLotService()
	{
		return (ProductionByLotService) getBean(PRODUCTION_BY_LOT_SERVICE_BEAN);
	}

	protected ListLotService getListLotService()
	{
		return (ListLotService) getBean(LIST_LOT_SERVICE_BEAN);
	}

	private ProductionByLot extractRecord(DataHolder dataHolder, ProductionByLot productionByLot)
			throws Exception
	{
		log.debug("extractRecord " + dataHolder.printValues());

		BigDecimal minutes = dataHolder.get("minutes").getDecimalValue();
		String minutesTxt = new DecimalFormat("0.000000000000000").format(minutes);
//		log.warn(Integer.valueOf(minutesTxt.split("\\.")[0]) / 60);
//		log.warn(Integer.valueOf(minutesTxt.split("\\.")[0]) % 60);
//		log.warn(Math.round(Float.valueOf("0." + minutesTxt.split("\\.")[1]) * 60));
		
		productionByLot.setHour(Integer.valueOf(minutesTxt.split("\\.")[0]) / 60);
		productionByLot.setMinute(Integer.valueOf(minutesTxt.split("\\.")[0]) % 60);
		productionByLot.setSecond(Math.round(Float.valueOf("0." + minutesTxt.split("\\.")[1]) * 60));
		
		productionByLot.setDialing(dataHolder.get("dialing").getIntValue());
		productionByLot.setCompleted(dataHolder.get("completed").getIntValue());
		productionByLot.setContact(dataHolder.get("contact").getIntValue());
		productionByLot.setSales(dataHolder.get("sales").getIntValue());
		productionByLot.setAbandons(dataHolder.get("abandons").getIntValue());
		productionByLot.setUwReleaseSales(dataHolder.get("uwReleaseSales").getIntValue());
		productionByLot.setTyp(dataHolder.get("typ").getDecimalValue());
		productionByLot.setTotalCost(dataHolder.get("totalCost").getDecimalValue());
		productionByLot.setReleaseSales(dataHolder.get("releaseSales").getIntValue());
		productionByLot.setAmpPostUw(dataHolder.get("ampPostUw").getDecimalValue());
		productionByLot.setDeclines(dataHolder.get("declines").getIntValue());

		return productionByLot;
	}

	private void importDataHolder(ListLot listLot, Integer totalLead, Integer remainingLead, DataHolder dataHolder)
			throws Exception
	{
		Date productionDate = (Date) dataHolder.get("productionDate").getValue();
		ProductionByLot productionByLot = getProductionByLotService().findProductionByLotByListLotCodeAndProductionDate(listLot.getListLotCode(), productionDate);

		boolean newProductionByLot = false;
		if (productionByLot == null)
		{
			productionByLot = new ProductionByLot();
			newProductionByLot = true;
		}
		else
		{
			log.debug("found productionByLot record [" + productionByLot + "]");
		}

		productionByLot.setProductionDate(productionDate);
		productionByLot.setListLot(listLot);
		productionByLot.setTotalLead(totalLead);
		productionByLot.setRemainingLead(remainingLead);

		try
		{
			extractRecord(dataHolder, productionByLot);

			if (newProductionByLot)
			{
				getProductionByLotService().addProductionByLot(productionByLot, BATCH_ID);
			}
			else
			{
				getProductionByLotService().updateProductionByLot(productionByLot, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void importDataHolderList(ListLot listLot, Integer totalLead, Integer remainingLead, List<DataHolder> dataHolderList)
			throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			importDataHolder(listLot, totalLead, remainingLead, dataHolder);
		}
	}

	private void importFile(String fileFormatFileName, String dataFileLocation)
			throws Exception
	{
		log.info("importFile: " + dataFileLocation);
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = URLClassLoader.getSystemResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<String> sheetNames = fileDataHolder.getKeyList();

			for (String sheetName : sheetNames)
			{
				DataHolder sheetDataHolder = fileDataHolder.get(sheetName);

				ListLot listLot = null;
				Integer totalLead = null;
				Integer remainingLead = null;
				List<DataHolder> listLotList = sheetDataHolder.getDataList("listLotList");
				if (listLotList.size() != 1)
				{
					throw new Exception("listLotCode invalid on sheetName: " + sheetName);
				}
				else
				{
					DataHolder listLotDataHolder = listLotList.get(0);
					String listLotCode = listLotDataHolder.get("listLotCode").getStringValue();
					listLot = getListLotService().findListLotByListLotCode(listLotCode);
					if (listLot == null)
					{
						log.warn("not found listLot for listLotCode: " + listLotCode);
						continue;
					}
				}

				List<DataHolder> totalLeadList = sheetDataHolder.getDataList("totalLeadList");
				if (totalLeadList.size() != 1)
				{
					throw new Exception("totalLead invalid on sheetName: " + sheetName);
				}
				else
				{
					DataHolder totalLeadDataHolder = totalLeadList.get(0);
					totalLead = totalLeadDataHolder.get("totalLead").getIntValue();
				}
				
				List<DataHolder> remainingLeadList = sheetDataHolder.getDataList("remainingLeadList");
				if (remainingLeadList.size() != 1)
				{
					throw new Exception("remainingLead invalid on sheetName: " + sheetName);
				}
				else
				{
					DataHolder remainingLeadDataHolder = remainingLeadList.get(0);
					remainingLead = remainingLeadDataHolder.get("remainingLead").getIntValue();
				}

				List<DataHolder> dataHolderList = sheetDataHolder.getDataList("dataRecords");
				importDataHolderList(listLot, totalLead, remainingLead, dataHolderList);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				format.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = /* args[0]; */ null;
		String rootPath = /* args[1]; */ "D:/Work/Report/DailyReport/201502";

		ImportProduction batch = new ImportProduction();
		batch.setLogLevel(Logger.DEBUG);

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !name.contains("TSR") && !name.contains("SalesReportByRecords") && (name.contains("Production.xls") || name.contains("Production Report.xlsx") || (name.contains("Production Report_") && name.contains(".xlsx")));
			}
		});

		for (String filename : fw.getFileList())
		{
			if (filename.contains("TELE"))
			{
				fileFormatFileName = FILE_FORMAT_PRODUCTION_BY_LOT_TELE;
			}
			else if (filename.contains("OTO"))
			{
				fileFormatFileName = FILE_FORMAT_PRODUCTION_BY_LOT_OTO;
			}

			batch.importFile(fileFormatFileName, filename);
		}
	}
}
