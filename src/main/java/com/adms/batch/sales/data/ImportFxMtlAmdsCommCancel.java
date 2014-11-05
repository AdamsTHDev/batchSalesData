package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class ImportFxMtlAmdsCommCancel {

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

		DataHolder sheet = fileDataHolder.get("Broker license");

		for (DataHolder cancelRecords : sheet.getDataList("cancelRecords"))
		{
			System.out.println(cancelRecords.printValues());
		}

		System.out.println(fileDataHolder.get("ADMS").getDataList("cancelRecords").size());
		System.out.println(fileDataHolder.get("Broker license").getDataList("cancelRecords").size());

		fileFormat.close();
		fileData.close();
		
		
		OutputStream output = new FileOutputStream("d:/test.xlsx");
		ex.writeExcel(output, fileDataHolder);
		output.close();
	}

}
