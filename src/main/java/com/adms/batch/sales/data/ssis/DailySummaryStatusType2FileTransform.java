package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.support.SalesDataHelper;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;

public class DailySummaryStatusType2FileTransform implements DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile)
			throws Exception
	{
		InputStream fileFormat = null;
		InputStream sampleReport = null;

		fileFormat = URLClassLoader.getSystemResourceAsStream(inputFileFormat);
		sampleReport = new FileInputStream(inputFile);

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(sampleReport);

		DataHolder newSheetDataHolder = new SimpleMapDataHolder();
		List<DataHolder> newDataRecordList = new ArrayList<DataHolder>();
		newSheetDataHolder.putDataList("dataRecord", newDataRecordList);
		fileDataHolder.put("Template summary table2", newSheetDataHolder);
		fileDataHolder.setSheetNameByIndex(2, "Template summary table2");

		processSheet(fileDataHolder, fileDataHolder.getSheetNameByIndex(0), newDataRecordList);
		processSheet(fileDataHolder, fileDataHolder.getSheetNameByIndex(1), newDataRecordList);

		fileFormat.close();
		sampleReport.close();

		OutputStream sampleOutput = new FileOutputStream(outputFile);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();
	}

	private void processSheet(DataHolder fileDataHolder, String sheetName, List<DataHolder> newDataRecordList) throws Exception
	{
		DataHolder sheetDataHolder = fileDataHolder.get(sheetName);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord1"), "List_lot1", newDataRecordList);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord2"), "List_lot2", newDataRecordList);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord3"), "List_lot3", newDataRecordList);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord4"), "List_lot4", newDataRecordList);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord5"), "List_lot5", newDataRecordList);
		processDataList(sheetDataHolder, sheetDataHolder.getDataList("dataRecord6"), "List_lot6", newDataRecordList);
		fileDataHolder.remove(sheetName);
	}

	private void processDataList(DataHolder sheetDataHolder, List<DataHolder> dataRecordList, String listLot, List<DataHolder> newDataRecordList) throws Exception
	{
		if (StringUtils.isNoneBlank(sheetDataHolder.get(listLot).getStringValue()))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

			String tempReasonMain = "";
			for (DataHolder dataRecord : dataRecordList)
			{
				if (StringUtils.isNotBlank(dataRecord.get("Reason_SUB").getStringValue()))
				{
					DataHolder dataHolder = new SimpleMapDataHolder();
					
					if (StringUtils.isBlank(dataRecord.get("Reason_Main").getStringValue()))
					{
						dataHolder = new SimpleMapDataHolder();
						dataHolder.setValue(tempReasonMain);
						dataRecord.put("Reason_Main", dataHolder);
					}
					else
					{
						tempReasonMain = dataRecord.get("Reason_Main").getStringValue();
					}
					

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(sheetDataHolder.get("Project").getValue());
					dataRecord.put("Project", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(sheetDataHolder.get("Campaign_Description").getValue());
					dataRecord.put("Campaign_Description", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(sheetDataHolder.get("Status").getValue());
					dataRecord.put("Status", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(SalesDataHelper.extractListLotName(sheetDataHolder.get(listLot).getStringValue()));
					dataRecord.put("List_lot", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(SalesDataHelper.extractListLotCode(sheetDataHolder.get(listLot).getStringValue()));
					dataRecord.put("KeyCode", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					dataHolder.setValue(sheetDataHolder.get("Lot_Description").getValue());
					dataRecord.put("Lot_Description", dataHolder);

					dataHolder = new SimpleMapDataHolder();
					if (sheetDataHolder.get("Date_Start").getValue() != null && sheetDataHolder.get("Date_Start").getValue() instanceof java.util.Date)
					{
						dataHolder.setValue(dateFormat.format(sheetDataHolder.get("Date_Start").getValue()));
						dataRecord.put("Date_Start", dataHolder);
					}

					dataHolder = new SimpleMapDataHolder();
					if (sheetDataHolder.get("Date_End").getValue() != null && sheetDataHolder.get("Date_End").getValue() instanceof java.util.Date)
					{
						dataHolder.setValue(dateFormat.format(sheetDataHolder.get("Date_End").getValue()));
						dataRecord.put("Date_End", dataHolder);
					}

					dataHolder = new SimpleMapDataHolder();
					if (sheetDataHolder.get("Print_Date").getValue() != null && sheetDataHolder.get("Print_Date").getValue() instanceof java.util.Date)
					{
						dataHolder.setValue(dateFormat.format(sheetDataHolder.get("Print_Date").getValue()));
						dataRecord.put("Print_Date", dataHolder);
					}

					newDataRecordList.add(dataRecord);
				}
			}
		}
	}

	public static void main(String[] ss) throws Exception
	{
		new DailySummaryStatusType2FileTransform().transform("FileFormat_SSIS_DailySummaryStatusType2-input-OTO.xml", new File("D:/Work/Report/DailyReport/201412/OTO/MTLBL/01122014_MTLife Hip Broker/SummaryStatusType2_MTL_BL_20141201.xlsx"), "FileFormat_SSIS_DailySummaryStatusType2-output.xml", new File("D:/testOutput.xlsx"));
	}
}
