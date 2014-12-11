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

import com.adms.batch.sales.support.SalesDataHelper;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;

public class DailyTsrTrackingFileTransform implements DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile)
			throws Exception
	{
		InputStream fileFormat = null;
		InputStream sampleReport = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

		fileFormat = URLClassLoader.getSystemResourceAsStream(inputFileFormat);
		sampleReport = new FileInputStream(inputFile);

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(sampleReport);

		List<String> sheetNames = fileDataHolder.getKeyList();
		for (String sheetName : sheetNames)
		{
			DataHolder sheetDataHolder = fileDataHolder.get(sheetName);

			String keyCode = null;
			String period = null;
			String printDate = null;
			List<DataHolder> dataHeader = sheetDataHolder.getDataList("dataHeader");
			int i = 0;
			for (DataHolder d : dataHeader)
			{
//				System.out.println(d.printValues());
				switch (i) {
				case 0:
					keyCode = d.get("dataInfo").getStringValue();
					break;
				case 1:
					period = d.get("dataInfo").getStringValue();
					break;
				case 2:
					printDate = d.get("dataInfo").getStringValue();
					break;
				}
				i++;
			}

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("dataRecord");
//			System.out.println(dataRecordList.size());
			for (DataHolder dataRecord : dataRecordList)
			{
				DataHolder dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(SalesDataHelper.extractListLotCode(keyCode));
				dataRecord.put("Key Code", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(dateFormat.parse(period));
				dataRecord.put("Period", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(dateFormat.parse(printDate.substring(0, 10)));
				dataRecord.put("Print Date", dataHolder);

//				System.out.println(dataRecord.printValues());
			}
		}

		// merge data into first sheet
		boolean removeFirstSheet = false;
		sheetNames = fileDataHolder.getKeyList();
		if (sheetNames.size() > 1)
		{
			for (String sheetName : sheetNames)
			{
				if (sheetName.contains("Summary") || sheetName.contains("ALL") || sheetName.contains("All"))
				{
					removeFirstSheet = true;
					continue;
				}
			}
		}

		if (removeFirstSheet)
		{
			fileDataHolder.remove(sheetNames.get(0));
		}

		sheetNames = fileDataHolder.getKeyList();
		if (sheetNames.size() > 0)
		{
			String baseSheetName = null;
			int i = 0;
			for (String sheetName : sheetNames)
			{
				if (i == 0)
				{
					baseSheetName = sheetName;
					i++;
					continue;
				}

				fileDataHolder.get(baseSheetName).getDataList("dataRecord").addAll(fileDataHolder.get(sheetName).getDataList("dataRecord"));
			}
			
			if (!baseSheetName.equals("DailyTsrTracking"))
			{
				fileDataHolder.put("DailyTsrTracking", fileDataHolder.get(baseSheetName));
				fileDataHolder.setSheetNameByIndex(0, "DailyTsrTracking");
				fileDataHolder.remove(baseSheetName);
			}
		}

		fileFormat.close();
		sampleReport.close();

		OutputStream sampleOutput = new FileOutputStream(outputFile);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();

	}

	public static void main(String[] args) throws Exception
	{
		String inputFileFormat = "FileFormat_SSIS_DailyTsrTracking-input-TELE.xml";
		String inputFile = "D:/Work/Report/DailyReport/201411/TELE/MTIKBANK/KBANK DDOP -PA Cash Back_04112014/TSRTracking.xls";
		String outputFileFormat = "FileFormat_SSIS_DailyTsrTracking-output.xml";
		String outputFile = "D:/testOutput.xlsx";
		new DailyTsrTrackingFileTransform().transform(inputFileFormat, new File(inputFile), outputFileFormat, new File(outputFile));
	}

}
