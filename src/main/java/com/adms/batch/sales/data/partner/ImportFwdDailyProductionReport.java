package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.List;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class ImportFwdDailyProductionReport {

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatLocation = args[0];
		String fileDataLocation = args[1];

		System.out.println(fileFormatLocation);
		System.out.println(fileDataLocation);

		InputStream fileFormat = URLClassLoader.getSystemResourceAsStream(fileFormatLocation);
		InputStream fileData = new FileInputStream(new File(fileDataLocation));

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(fileData);

		List<String> sheetNames = fileDataHolder.getKeyList();

		for (String sheetName : sheetNames)
		{
			DataHolder sheet = fileDataHolder.get(sheetName);
			System.out.println(sheet.get("reportDescription").getValue());

			for (DataHolder productionRecords : sheet.getDataList("productionRecords"))
			{
				System.out.println(productionRecords.printValues());
			}

			System.out.println(fileDataHolder.get(sheetName).getDataList("productionRecords").size());
		}

		fileFormat.close();
		fileData.close();
	}

}
