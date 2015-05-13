package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.support.FileWalker;

public class DailyPerformanceTrackingByLotTele extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
//		new DailyPerformanceTrackingByLotTele().test("D:/Work/Report/DailyReport/201501");
//		new DailyPerformanceTrackingByLotTele().test("D:/Work/Report/DailyReport/201502");
//		new DailyPerformanceTrackingByLotTele().test("D:/Work/Report/DailyReport/201503");
//		new DailyPerformanceTrackingByLotTele().test("D:/Work/Report/DailyReport/201504");
//		new DailyPerformanceTrackingByLotTele().test("D:/Work/Report/DailyReport/201505");
//		new DailyPerformanceTrackingByLotTele().test(args[0]);
	}

	public void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return dir.getAbsolutePath().contains("TELE") && ((name.contains("_DAILYP_") && name.contains("_MTD_")) ||name.contains("Daily_Performance_Tracking_ByLot.xls") || name.contains("Daily_Performance_Tracking.xls") || name.contains("DailyPerformanceTrackingReport.xlsx") || (name.contains("DailyPerformanceTrackingReport_") && name.contains(".xlsx")));
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");

			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));
			if (outputFileNameSsis.contains("TELE") && outputFileNameSsis.contains("AUTO") && outputFileNameSsis.contains("_DAILYP_"))
			{
				int beginIndex = outputFileNameSsis.indexOf("_MTD_") + 5;
				int endIndex = beginIndex + 5;
				outputPathSsis = outputPathSsis + System.getProperty("file.separator") + outputFileNameSsis.substring(beginIndex, endIndex);
			}

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "DailyPerformanceTracking.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}

			log.info(fileName + " --> " + outputFileNameSsis);

			String inputFileFormat = "fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-input-TELE.xml";
			if (fileName.contains("_DAILYP_") && fileName.contains("201501"))
			{
				inputFileFormat = "fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-input-TELE-AUTO-201501.xml";
			}
			else if (fileName.contains("_DAILYP_"))
			{
				inputFileFormat = "fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-input-TELE-AUTO.xml";
			}
			
			new DailyPerformanceTrackingByLotFileTransform().transform(inputFileFormat, new File(fileName), "fileformat/report/ssis/FileFormat_SSIS_DailyPerformanceTrackingByLot-output.xml", new File(outputFileNameSsis));
		}
	}

}
