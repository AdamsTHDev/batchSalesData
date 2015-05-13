package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FilenameFilter;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.support.FileWalker;

public class DailyQcReconfirmOto extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
//		new DailyQcReconfirmOto().test("D:/Work/Report/DailyReport/201410");
//		new DailyQcReconfirmOto().test("D:/Work/Report/DailyReport/201411");
//		new DailyQcReconfirmOto().test("D:/Work/Report/DailyReport/201412");
//		new DailyQcReconfirmOto().test("D:/Work/Report/DailyReport/201501");
//		new DailyQcReconfirmOto().test("D:/Work/Report/DailyReport/201502");
		new DailyQcReconfirmOto().test(args[0]);
		
	}

	public void test(String sInputPath)
			throws Exception
	{
		FileWalker fw = new FileWalker();
		fw.walk(sInputPath, new FilenameFilter()
		{

			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !name.contains("Total") && (dir.getAbsolutePath().contains("OTO") && (name.contains("_Reconfirm") && name.contains(".xls")));
			}
		});

		for (String fileName : fw.getFileList())
		{
			String outputFileNameSsis = fileName.replace("DailyReport", "DailyReportSSIS");
			String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

			outputFileNameSsis = outputPathSsis + System.getProperty("file.separator") + "QcReconfirm.xlsx";

			File outputPath = new File(outputPathSsis);
			if (!outputPath.exists())
			{
				outputPath.mkdirs();
			}

			log.info(fileName + " --> " + outputFileNameSsis);
			new DailyQcReconfirmFileTransform().transform("FileFormat_SSIS_QcReconfirm-input-OTO.xml", new File(fileName), "FileFormat_SSIS_QcReconfirm-output.xml", new File(outputFileNameSsis));
		}
	}

}
