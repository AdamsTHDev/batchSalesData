package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.support.FileWalker;

public class DailyTsrTrackingTele {

	public static void main(String[] args)
			throws Exception
	{
//		test("D:/Work/Report/DailyReport/201410");
//		test("D:/Work/Report/DailyReport/201411");
//		test("D:/Work/Report/DailyReport/201412");
//		test("D:/Work/Report/DailyReport/201501");
		test(args[0]);
	}

	public static void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return dir.getAbsolutePath().contains("TELE") && name.toUpperCase().contains("TSR") && name.toUpperCase().contains("TRACKING");
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "TsrTracking.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}

			System.out.println(fileName + " --> " + outputFileNameSsis);
			new DailyTsrProductionFileTransform().transform("FileFormat_SSIS_DailyTsrTracking-input-TELE.xml", new File(fileName), "FileFormat_SSIS_DailyTsrTracking-output.xml", new File(outputFileNameSsis));
		}
	}

}
