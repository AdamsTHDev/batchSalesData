package com.adms.batch.sales.data.fx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLClassLoader;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class ImportFxMtlAmdsCommIssue {

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatLocation = args[0];
		String fileDataLocation = args[1];

		// fileDataLocation =
		// "D:/Work/ADAMS/Report/FX/MTL/201407/ADMS_COMM_ISSUE_20140805_Revised.xls";

		System.out.println(fileFormatLocation);
		System.out.println(fileDataLocation);

		InputStream fileFormat = URLClassLoader.getSystemResourceAsStream(fileFormatLocation);
		InputStream fileData = new FileInputStream(new File(fileDataLocation));

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(fileData);

		DataHolder sheet = fileDataHolder.get("Broker license");

		for (DataHolder salesRecords : sheet.getDataList("issueRecords"))
		{
			System.out.println(salesRecords.printValues());
		}

		System.out.println(fileDataHolder.get("ADMS").getDataList("issueRecords").size());
		System.out.println(fileDataHolder.get("Broker license").getDataList("issueRecords").size());
		System.out.println(fileDataHolder.get("Issues_").getDataList("issueRecords").size());

		fileFormat.close();
		fileData.close();
	}

}
