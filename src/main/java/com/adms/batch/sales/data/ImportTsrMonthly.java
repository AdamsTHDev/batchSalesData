package com.adms.batch.sales.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.domain.TsrPosition;
import com.adms.batch.sales.domain.TsrStatus;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportTsrMonthly extends AbstractImportSalesJob {

	private Tsr extractTsrRecord(DataHolder tsrDataHolder, Tsr tsr)
			throws Exception
	{
		log.debug("extractTsrRecord " + tsrDataHolder.printValues());
		
		String positionName = tsrDataHolder.get("positionName").getStringValue();
		TsrPosition tsrPosition = getTsrPositionService().findTsrPositionByPositionName(positionName);
		tsr.setTsrPosition(tsrPosition);
		
		String statusCode = tsrDataHolder.get("status").getStringValue();
		TsrStatus tsrStatus = getTsrStatusService().findTsrStatusByStatusCode(statusCode);
		tsr.setTsrStatus(tsrStatus);

//		tsr.setEmployeeCode(tsrDataHolder.get("employeeCode").getStringValue());
		
		String firstName = tsrDataHolder.get("firstName").getStringValue().trim();
		String lastName = tsrDataHolder.get("lastName").getStringValue().trim();
		tsr.setFullName(firstName + " " + lastName);
		tsr.setFirstName(firstName);
		tsr.setLastName(lastName);
		
		tsr.setEffectiveDate((Date) tsrDataHolder.get("effectiveDate").getValue());
		tsr.setResignDate((Date) tsrDataHolder.get("resignDate").getValue());
		tsr.setRemark(tsrDataHolder.get("remark").getStringValue());

		return tsr;
	}

	private void importTsr(List<DataHolder> tsrDataHolderList)
			throws Exception
	{
		log.info("importTsr");
		for (DataHolder tsrDataHolder : tsrDataHolderList)
		{
			log.debug("process record: " + tsrDataHolder);
			String tsrCode = tsrDataHolder.get("tsrCode").getStringValue();
			Tsr tsr = getTsrService().findTsrByTsrCode(tsrCode);

			boolean newTsr = false;
			if (tsr == null)
			{
				tsr = new Tsr();
				tsr.setTsrCode(tsrCode);
				newTsr = true;
			}

			extractTsrRecord(tsrDataHolder, tsr);

			if (newTsr)
			{
				getTsrService().addTsr(tsr, BATCH_ID);
			}
			else
			{
				getTsrService().updateTsr(tsr, BATCH_ID);
			}
		}
	}

	private void importFile(String fileFormatFileName, String dataFileLocation)
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

			List<DataHolder> tsrDataHolderList = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0)).getDataList("tsrList");
			log.debug("found " + tsrDataHolderList.size() + " record(s)");
			
			importTsr(tsrDataHolderList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	public static final String CONFIG_FILE_LOCATION = "config/importSales.properties";
	public static final String LOG_FILE_NAME = "log.tsrUpdate.file.name";

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatFileName = "fileformat/salesdb/FileFormat_TSR_Update_Monthly.xml";
		String dataFileLocation = args[0] /* "D:/Work/Report/TSR Update/Employees_201503_for_batch.xlsx" */;

		System.out.println("main");
		ImportTsrMonthly batch = new ImportTsrMonthly();
		batch.setLogLevel(Logger.DEBUG);
		batch.setProcessDate(new Date());
		String logFileName = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME).replace("logTime", "" + new SimpleDateFormat("yyyyMMdd_hhmmssSSS").format(new Date()));
		batch.setLogFileName(logFileName);

		batch.importFile(fileFormatFileName, dataFileLocation);
	}

}
