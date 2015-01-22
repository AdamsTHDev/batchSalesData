package com.adms.batch.sales.data.ssis;

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

public class DailyTsrProductionFileTransform implements DialyFileTransform {

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

			String campaignInfo = null;
			String campaignCode = null;
			String keyCode = null;
			String tmAgency = null;
			String period = null;
			String printDate = null;
			List<DataHolder> dataHeader = sheetDataHolder.getDataList("dataHeader");
			int i = 0;
			for (DataHolder d : dataHeader)
			{
//				System.out.println(d.printValues());
				switch (i) {
				case 0:
					campaignInfo = d.get("dataInfo").getStringValue();
					break;
				case 1:
					campaignCode = SalesDataHelper.extractListLotName(d.get("dataInfo").getStringValue());
					keyCode = SalesDataHelper.extractListLotCode(d.get("dataInfo").getStringValue());
					break;
				case 2:
					tmAgency = d.get("dataInfo").getStringValue();
					break;
				case 3:
					period = d.get("dataInfo").getStringValue();
					break;
				case 4:
					printDate = d.get("dataInfo").getStringValue();
					break;
				}
				i++;
			}

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("dataRecord");
//			System.out.println(dataRecordList.size());
			for (DataHolder dataRecord : dataRecordList)
			{
				DataHolder dataHolder = null;

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignInfo);
				dataRecord.put("Campaign Info", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignCode);
				dataRecord.put("Campaign Code", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(keyCode);
				dataRecord.put("Key Code", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(tmAgency);
				dataRecord.put("TM Agency", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(dateFormat.parse(period));
				dataRecord.put("Period", dataHolder);

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(dateFormat.parse(printDate));
				dataRecord.put("Print Date", dataHolder);

//				System.out.println(dataRecord.printValues());
			}
		}

		fileDataHolder.put("DailyTsrProduction", fileDataHolder.get("TSR_Production"));
		fileDataHolder.setSheetNameByIndex(0, "DailyTsrProduction");
		fileDataHolder.remove("TSR_Production");

		fileFormat.close();
		sampleReport.close();

		OutputStream sampleOutput = new FileOutputStream(outputFile);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();

	}

	public static void main(String[] args) throws Exception
	{
		String inputFileFormat = "FileFormat_SSIS_DailyTsrProduction-input-TELE.xml";
		String inputFile = "D:/Work/Report/DailyReport/201411/TELE/MTLKBANK/HIP_DDOP_17112014/TSR_Production.xls";
		String outputFileFormat = "FileFormat_SSIS_DailyTsrProduction-output.xml";
		String outputFile = "D:/testOutput.xlsx";
		new DailyTsrProductionFileTransform().transform(inputFileFormat, new File(inputFile), outputFileFormat, new File(outputFile));
	}

}
