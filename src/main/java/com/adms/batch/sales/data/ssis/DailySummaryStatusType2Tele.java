package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.support.FileWalker;
import com.adms.batch.sales.test.AbstractImportSalesJob;

public class DailySummaryStatusType2Tele extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		new DailySummaryStatusType2Tele().test("D:/Work/Report/DailyReport/201410");
		new DailySummaryStatusType2Tele().test("D:/Work/Report/DailyReport/201411");
		new DailySummaryStatusType2Tele().test("D:/Work/Report/DailyReport/201412");
		new DailySummaryStatusType2Tele().test("D:/Work/Report/DailyReport/201501");
//		new DailySummaryStatusType2Tele().test(args[0]);
	}

	public void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return dir.getAbsolutePath().contains("TELE") && name.contains("Summary") && name.contains("Status") && name.contains("Type") && name.contains("2.xls");
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "SummaryStatusType2.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}

			log.info(fileName + " --> " + outputFileNameSsis);
			new DailySummaryStatusType2FileTransform().transform("FileFormat_SSIS_DailySummaryStatusType2-input-TELE.xml", new File(fileName), "FileFormat_SSIS_DailySummaryStatusType2-output.xml", new File(outputFileNameSsis));
		}
	}

}
