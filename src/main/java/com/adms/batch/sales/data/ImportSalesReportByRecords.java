package com.adms.batch.sales.data;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.Locale;

import com.adms.batch.sales.test.FileWalker;
import com.adms.batch.sales.test.TestSalesMain;
import com.adms.utils.Logger;

public class ImportSalesReportByRecords extends TestSalesMain {

	private String locationDateFormat;
	private Locale locationDateLocale;
	private String dataRootLocation;
	private String fileFormatLocation;
	private String dataSheetName;

	public String getLocationDateFormat()
	{
		return locationDateFormat;
	}

	public void setLocationDateFormat(String locationDateFormat)
	{
		this.locationDateFormat = locationDateFormat;
	}

	public Locale getLocationDateLocale()
	{
		return locationDateLocale;
	}

	public void setLocationDateLocale(Locale locationDateLocale)
	{
		this.locationDateLocale = locationDateLocale;
	}

	public String getDataRootLocation()
	{
		return dataRootLocation;
	}

	public void setDataRootLocation(String dataRootLocation)
	{
		this.dataRootLocation = dataRootLocation;
	}

	public String getFileFormatLocation()
	{
		return fileFormatLocation;
	}

	public void setFileFormatLocation(String fileFormatLocation)
	{
		this.fileFormatLocation = fileFormatLocation;
	}

	public String getDataSheetName()
	{
		return dataSheetName;
	}

	public void setDataSheetName(String dataSheetName)
	{
		this.dataSheetName = dataSheetName;
	}

	/*private String getNextProcessDate(String processDate)
			throws ParseException
	{
		if (processDate.length() == 6)
		{
			return processDate + "01";
		}
		else
		{
			Date date = super.processDateDf.parse(processDate);

			Calendar c = Calendar.getInstance(PROCESS_DATE_LOCALE);
			c.setTime(date);
			c.add(Calendar.DATE, 1);

			if (c.get(Calendar.DATE) == 1)
			{
				return null;
			}
			else
			{
				return super.processDateDf.format(c.getTime());
			}
		}
	}

	private boolean isFileExist(String dataLocation)
	{
		FileInputStream test = null;
		try
		{
			test = new FileInputStream(dataLocation);
			return true;
		}
		catch (Exception e)
		{
			System.err.println("file not found: " + dataLocation);
			return false;
		}
		finally
		{
			try
			{
				test.close();
			}
			catch (Exception e)
			{
			}
			test = null;
		}
	}

	private void importByMonth(String yyyyMM)
			throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat(getLocationDateFormat(), getLocationDateLocale());

		int counter = 0;
		String processDate = getNextProcessDate(yyyyMM);
		while (processDate != null)
		{
			setProcessDate(super.processDateDf.parse(processDate));

			String dataLocation = getDataRootLocation().replace("[LOCATION_MONTH]", yyyyMM).replace("[LOCATION_DATE]", df.format(getProcessDate()));

			if (isFileExist(dataLocation))
			{
				System.out.println("import file: " + dataLocation);
				importFile(new File(getFileFormatLocation()), new File(dataLocation), getDataSheetName());
				counter++;
			}

			processDate = getNextProcessDate(processDate);
		}
		
		System.out.println("end: " + counter + " files imported");
	}*/

	public static void main(String[] args)
			throws Exception
	{
		System.out.println("main");

		ImportSalesReportByRecords batch = null;

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTLKBANK/Health Return Cash_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTLKBANK/HIP_DDOP_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("dd.MM.yyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTLKBANK/POM_PA_Cash_Back_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTIKBANK/KBANK DDOP -PA Cash Back_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTIKBANK/KBANK DDOP -POM PA Cash Back_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MTIKBANK/MTI-KBank_[LOCATION_DATE]/Sales_Report_By_Records.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords.xml");
//		batch.importByMonth("201410");

//		batch = new ImportSalesReportByRecords(false);
//		batch.setLocationDateFormat("ddMMyyyy");
//		batch.setLocationDateLocale(Locale.US);
//		batch.setDataSheetName("Sales_Report_By_Records_Pending");
//		batch.setDataRootLocation("D:/Work/ADAMS/Report/DailyReport/[LOCATION_MONTH]/TELE/MSIGUOB/MSIG UOB_[LOCATION_DATE]/Sales_Report_By_Records_Pending.xls");
//		batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SalesReportByRecords-MSIG.xml");
//		batch.importByMonth("201410");
		
		

		String rootPath = "D:/Work/Report/DailyReport/201412";
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.contains("Sales_Report_By_Records") || (name.contains("SalesReportByRecords_") && name.contains(".xlsx")) || name.contains("SalesReportByRecords.xlsx");
			}
		});

		batch = new ImportSalesReportByRecords();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		for (String filename : fw.getFileList())
		{
//			System.out.println("import file: " + filename);

			if (filename.contains("MSIGUOB"))
			{
				batch.setDataSheetName("Sales_Report_By_Records_Pending");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-MSIGUOB.xml");
			}
			else if (filename.contains("OTO"))
			{
				batch.setDataSheetName("Sales_Report_By_Records");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-OTO.xml");
			}
			else if (filename.contains("TELE") && !filename.contains("MSIGUOB"))
			{
				batch.setDataSheetName("Sales_Report_By_Records");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-TELE.xml");
			}
			else
			{
				throw new Exception("file not supported");
			}

			batch.importFile(new File(batch.getFileFormatLocation()), new File(filename), batch.getDataSheetName());
		}
	}
}
