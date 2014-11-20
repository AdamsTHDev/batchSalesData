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

public class DailyPerformanceTrackingByLotFileTransform implements DialyFileTransform {

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

			String campaignCode = null;
			String campaignInfo = null;
			String campaignName = null;
			List<DataHolder> cHeader = sheetDataHolder.getDataList("CampaignHeader");
			int i = 0;
			for (DataHolder c : cHeader)
			{
//				System.out.println(c.printValues());
				switch (i) {
				case 0:
					campaignCode = c.get("campaignInfo").getStringValue();
					break;
				case 1:
					campaignInfo = c.get("campaignInfo").getStringValue();
					break;
				case 2:
					campaignName = c.get("campaignInfo").getStringValue();
					break;
				}
				i++;
			}

			String fromDate = null;
			String toDate = null;
			String printDate = null;
			List<DataHolder> dHeader = sheetDataHolder.getDataList("DateHeader");
			i = 0;
			for (DataHolder d : dHeader)
			{
//				System.out.println(d.printValues());
				switch (i) {
				case 0:
					fromDate = d.get("dateInfo").getStringValue();
					break;
				case 1:
					toDate = d.get("dateInfo").getStringValue();
					break;
				case 2:
					printDate = d.get("dateInfo").getStringValue();
					break;
				}
				i++;
			}

			String leadLoad = null;
			String newLeadRemaining = null;
			String referralLead = null;
			String salePending = null;
			List<DataHolder> lHeader = sheetDataHolder.getDataList("LeadHeader");
			i = 0;
			for (DataHolder l : lHeader)
			{
//				System.out.println(l.printValues());
				switch (i) {
				case 0:
					leadLoad = l.get("lead1Info").getStringValue();
					newLeadRemaining = l.get("lead2Info").getStringValue();
					break;
				case 1:
					referralLead = l.get("lead1Info").getStringValue();
					salePending = l.get("lead2Info").getStringValue();
					break;
				}
				i++;
			}

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("dataRecord");
//			System.out.println(dataRecordList.size());
			for (DataHolder dataRecord : dataRecordList)
			{
				DataHolder dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignCode);
				dataRecord.put("Campaign Code", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignInfo);
				dataRecord.put("Campaign Info", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(campaignName);
				dataRecord.put("Campaign Name", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(fromDate);
				dataRecord.put("From Date", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(toDate);
				dataRecord.put("To Date", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(printDate);
				dataRecord.put("Print Date", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(leadLoad);
				dataRecord.put("Lead Load", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(newLeadRemaining);
				dataRecord.put("New Lead Remaining", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(referralLead);
				dataRecord.put("Referral Lead", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(salePending);
				dataRecord.put("Sale Pending", dataHolder);
				
				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("Date").getValue() != null && dataRecord.get("Date").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("Date").getValue()));
					dataRecord.put("Date", dataHolder);
				}

//				System.out.println(dataRecord.printValues());
			}

		}

		fileFormat.close();
		sampleReport.close();

		OutputStream sampleOutput = new FileOutputStream(outputFile);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();
	}

	public static void main(String[] ss) throws Exception
	{
		String inputFileFormat = "FileFormat_SSIS_DailyPerformanceTrackingByLot-input-OTO.xml";
		String inputFile = "D:/Work/ADAMS/Report/DailyReport/201410/OTO/MTLBL/21102014_MTLife Hip Broker/DailyPerformanceTrackingReport_MTL_BL_20141021.xlsx";
		String outputFileFormat = "FileFormat_SSIS_DailyPerformanceTrackingByLot-output.xml";
		String outputFile = "D:/testOutput.xlsx";
		new DailyPerformanceTrackingByLotFileTransform().transform(inputFileFormat, new File(inputFile), outputFileFormat, new File(outputFile));
	}
}
