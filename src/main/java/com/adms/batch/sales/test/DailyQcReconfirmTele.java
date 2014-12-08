package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;

public class DailyQcReconfirmTele {

	public static void main(String[] args)
			throws Exception
	{
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTLKBANK");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTIKBANK", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MTIKBANK");
		runRoot("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MSIGUOB", "D:/Work/ADAMS/Report/DailyReportTransformOutput/201410/TELE/MSIGUOB");
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
						return name.contains("QC_Reconfirm.xls") || (name.contains("QC_Reconfirm") && name.contains(".xls"));
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

					new DailyQcReconfirmFileTransform().transform("FileFormat_SSIS_QcReconfirm-input-TELE.xml", inputFile, "FileFormat_SSIS_QcReconfirm-output.xml", outputFile);
				}
			}

			continue;
		}
	}

}
