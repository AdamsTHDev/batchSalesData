package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;

public class BatchDailyQcReconfirmOtoFileTransform {

	public static void main(String[] args)
			throws Exception
	{
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MTLBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MTLBL");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/MSIGBL", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/MSIGBL");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/OTO/FWDTVD", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/OTO/FWDTVD");
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
						return name.contains("_Reconfirm") && name.contains(".xls");
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

					new DailyQcReconfirmFileTransform().transform("FileFormat_SSIS_QcReconfirm-input-OTO.xml", inputFile, "FileFormat_SSIS_QcReconfirm-output.xml", outputFile);
				}
			}

			continue;
		}
	}

}
