package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLClassLoader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class ImportSalesReportByRecords {

	public static void main(String[] args)
			throws Exception
	{
		String fileFormatLocation = args[0];
		String fileDataLocation = args[1];

		// fileDataLocation =
		// "D:/Work/ADAMS/Report/DailyReport/August/01082014/KBANK DDOP -PA Cash Back_01082014/Sales_Report_By_Records_test.xls";
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileDataLocation));

		System.out.println(fileFormatLocation);
		System.out.println(fileDataLocation);

		InputStream fileFormat = URLClassLoader.getSystemResourceAsStream(fileFormatLocation);
		InputStream fileData = new FileInputStream(new File(fileDataLocation));

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(fileData);

		DataHolder sheet = fileDataHolder.get("Sales_Report_By_Records");

		System.out.println(sheet.getDataList("salesRecords").size());
		for (DataHolder salesRecords : sheet.getDataList("salesRecords"))
		{
			System.out.println(salesRecords.printValues());
		}

		fileFormat.close();
		fileData.close();
	}

}
