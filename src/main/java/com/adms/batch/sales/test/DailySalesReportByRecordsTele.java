package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;

public class DailySalesReportByRecordsTele {

	public static void main(String[] args)
			throws Exception
	{
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTLKBANK");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTIKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTIKBANK");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MSIGUOB", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MSIGUOB");
//
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MTLBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MTLBL");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MSIGBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MSIGBL");
//		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/FWDTVD", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/FWDTVD");
		
		test("D:/Work/Report/DailyReport/201411");
	}

	/*public static void runRoot(String sInputPath, String sOutputPath)
			throws Exception
	{
		File inputPath = new File(sInputPath);

		for (File p : inputPath.listFiles())
		{
			if (p.isDirectory())
			{
				for (File inputFile : p.listFiles(new FilenameFilter()
				{

					public boolean accept(File dir, String name)
					{
						return name.contains("Sales_Report_By_Records.xls") || (name.contains("SalesReportByRecords_") && name.contains(".xlsx"));
					}
				}))
				{
					System.out.println(inputFile);
					File outputPath = new File(sOutputPath + "/" + inputFile.getParentFile().getName());
					if (!outputPath.exists())
					{
						outputPath.mkdirs();
					}

					File outputFile = new File(outputPath.getPath() + "/" + inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.')) + ".xlsx");

					new DailySalesReportByRecordsFileTransform().transform("FileFormat_SSIS_DailySalesReportByRecords-input.xml", inputFile, "FileFormat_SSIS_DailySalesReportByRecords-output.xml", outputFile);
				}
			}

			continue;
		}
	}*/
	
	public static void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return dir.getAbsolutePath().contains("TELE") && name.contains("Sales_Report_By_Records.xls");
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "SalesReportByRecords.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}
			
			System.out.println(fileName + " --> " + outputFileNameSsis);
			new DailySalesReportByRecordsFileTransform().transform("FileFormat_SSIS_DailySalesReportByRecords-input-TELE.xml", new File(fileName), "FileFormat_SSIS_DailySalesReportByRecords-output.xml", new File(outputFileNameSsis));
		}
		
	}

}
