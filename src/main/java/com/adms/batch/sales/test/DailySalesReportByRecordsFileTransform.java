package com.adms.batch.sales.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;

public class DailySalesReportByRecordsFileTransform implements DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile)
			throws Exception
	{
		InputStream fileFormat = null;
		InputStream sampleReport = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

//		fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_SSIS_DailySalesReportByRecords-input.xml");
		fileFormat = URLClassLoader.getSystemResourceAsStream(inputFileFormat);
//		sampleReport = new FileInputStream("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK/POM_PA_Cash_Back_31.10.2014/Sales_Report_By_Records.xls");
		sampleReport = new FileInputStream(inputFile);

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(sampleReport);

		List<String> sheetNames = fileDataHolder.getKeyList();
		for (String sheetName : sheetNames)
		{
			DataHolder sheetDataHolder = fileDataHolder.get(sheetName);

			String campaignName = null;
			String listLot = null;
			String period = null;
			String printDate = null;
			List<DataHolder> reportHeader = sheetDataHolder.getDataList("reportHeader");
			int i = 0;
			for (DataHolder rh : reportHeader)
			{
//				System.out.println(rh.printValues());
				switch (i) {
				case 0:
					campaignName = rh.get("reportInfo").getStringValue();
					break;
				case 1:
					listLot = rh.get("reportInfo").getStringValue();
					break;
				case 2:
					period = rh.get("reportInfo").getStringValue();
					break;
				case 3:
					printDate = rh.get("reportInfo").getStringValue();
					break;
				}
				i++;
			}

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("salesRecord");
			for (DataHolder dataRecord : dataRecordList)
			{
				DataHolder dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignName);
				dataRecord.put("Campaign Name", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(listLot);
				dataRecord.put("ListLot", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(period);
				dataRecord.put("Period", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(printDate);
				dataRecord.put("Print Date", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("approveDate").getValue() != null && dataRecord.get("approveDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("approveDate").getValue()));
					dataRecord.put("approveDate", dataHolder);
				}
				
				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("saleDate").getValue() != null && dataRecord.get("saleDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("saleDate").getValue()));
					dataRecord.put("saleDate", dataHolder);
				}

//				System.out.println(dataRecord.printValues());
			}

		}

		fileFormat.close();
		sampleReport.close();

//		OutputStream sampleOutput = new FileOutputStream("D:/testOutput.xlsx");
		OutputStream sampleOutput = new FileOutputStream(outputFile);
//		new ExcelFormat(URLClassLoader.getSystemResourceAsStream("FileFormat_SSIS_DailySalesReportByRecords-output.xml")).writeExcel(sampleOutput, fileDataHolder);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();
	}

}
