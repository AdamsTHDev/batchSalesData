package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;

public class DailyPerformanceTrackingByLotTele {

	public static void main(String[] args)
			throws Exception
	{
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTLKBANK");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTIKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTIKBANK");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MSIGUOB", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MSIGUOB");

//		runRoot(args[0], args[1]);
	}

	public static void runRoot(String sInputPath, String sOutputPath)
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
						return (name.contains("Daily_Performance_Tracking_ByLot.xls") || name.contains("Daily_Performance_Tracking.xls")) || (name.contains("DailyPerformanceTrackingReport_") && name.contains(".xlsx"));
					}
				}))
				{
					System.out.println(inputFile);
					File outputPath = new File(sOutputPath + "/" + inputFile.getParentFile().getName());
					if (!outputPath.exists())
					{
						outputPath.mkdirs();
					}

//					File outputFile = new File(outputPath.getPath() + "/" + inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.')) + ".xlsx");
					File outputFile = new File(outputPath.getPath() + "/" + "DailyPerformanceTracking.xlsx");

					new DailyPerformanceTrackingByLotFileTransform().transform("FileFormat_SSIS_DailyPerformanceTrackingByLot-input-TELE.xml", inputFile, "FileFormat_SSIS_DailyPerformanceTrackingByLot-output.xml", outputFile);
				}
			}

			continue;
		}
	}

}
