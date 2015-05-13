package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.SalesProcess;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportFwdDailyProductionReport  extends AbstractImportSalesJob {

	private SalesProcess extractRecord(DataHolder dataHolder, SalesProcess salesProcess)
			throws Exception
	{
		log.debug("extractRecord " + dataHolder.printValues());

		String transactionDateYear = dataHolder.get("transactionDateYear").getStringValue();
		String transactionDateMonth = dataHolder.get("transactionDateMonth").getStringValue();
		String transactionDateDay = dataHolder.get("transactionDateDay").getStringValue();
		Date transactionDate = processDateDf.parse(transactionDateYear + StringUtils.leftPad(transactionDateMonth, 2, "0") + StringUtils.leftPad(transactionDateDay, 2, "0"));

		salesProcess.setBatchDate(getProcessDate());
		salesProcess.setStatusDate(transactionDate);
		salesProcess.setPolicyStatus(dataHolder.get("policyStatus").getStringValue());

		return salesProcess;
	}

	private void importDataHolder(DataHolder dataHolder, Sales sales)
			throws Exception
	{
		log.debug("sales: " + sales);

		String policyStatus = dataHolder.get("policyStatus").getStringValue();
		String transactionDateYear = dataHolder.get("transactionDateYear").getStringValue();
		String transactionDateMonth = dataHolder.get("transactionDateMonth").getStringValue();
		String transactionDateDay = dataHolder.get("transactionDateDay").getStringValue();
		Date transactionDate = processDateDf.parse(transactionDateYear + StringUtils.leftPad(transactionDateMonth, 2, "0") + StringUtils.leftPad(transactionDateDay, 2, "0"));

		SalesProcess salesProcess = getSalesProcessService().findSalesProcessByXRefferenceAndStatusDateAndPolicyStatus(sales.getxReference(), transactionDate, policyStatus);

		boolean newSalesProcess = false;
		if (salesProcess == null)
		{
			salesProcess = new SalesProcess();
			salesProcess.setxReference(sales);
			newSalesProcess = true;
		}
		else
		{
			log.debug("found salesProcess record [" + salesProcess + "]");
		}

		try
		{
			extractRecord(dataHolder, salesProcess);

			if (newSalesProcess)
			{
				getSalesProcessService().addSalesProcess(salesProcess, BATCH_ID);
			}
			else
			{
				getSalesProcessService().updateSalesProcess(salesProcess, BATCH_ID);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void importDataHolderList(List<DataHolder> dataHolderList)
			throws Exception
	{
		log.debug("dataHolderList size: " + dataHolderList.size());
		for (DataHolder dataHolder : dataHolderList)
		{
			// for FWD - use customer name
			String insuredName = dataHolder.get("insuredName").getStringValue();
			
			Sales sales = getSalesService().findSalesRecordByCustomerFullNameAndInsurerAndListSource(insuredName, "FWD", "TVD");
			log.debug("sales: " + sales);

			if (sales != null)
			{
				importDataHolder(dataHolder, sales);
			}
			else
			{
				log.warn("sales record not found [insuredName: " + insuredName + "]");
			}
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
			log.info("Total sheet: " + sheetNames.size());

			for (String sheetName : sheetNames)
			{
				log.info("process sheetName: " + sheetName);
				DataHolder sheet = fileDataHolder.get(sheetName);

				List<DataHolder> dataHolderList = sheet.getDataList("productionRecords");

				importDataHolderList(dataHolderList);
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
		String fileFormatLocation = "fileformat/salesdb/FileFormat_FWD_Daily_Production_Report.xml";
		String rootPath = args[0] /* "D:/Work/Report/DailyReport/FWD_Daily_Production" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.contains("ADAMS - TVD Daily Report") && name.contains(".xls");
			}
		});

		ImportFwdDailyProductionReport batch = new ImportFwdDailyProductionReport();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatLocation, filename);
		}
	}
}
