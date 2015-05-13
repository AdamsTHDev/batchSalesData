package com.adms.batch.sales.report.partner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DailyTsrProductionInfo;
import com.adms.batch.sales.domain.DailyTsrProductionRecord;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailyTsrProductionReport extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignCode = args[0];
		//yyyyMMdd_HHmmssSSS
		String outputFileName = args[1] + "/TSR_Production_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xls";

		new File(args[1]).mkdirs();

		DailyTsrProductionReport batch = new DailyTsrProductionReport();
		batch.setLogLevel(Logger.DEBUG);
		batch.generateReport(campaignCode, outputFileName);
	}

	private void generateReport(String campaignCode, String outputFileName)
			throws Exception
	{
		log.debug(outputFileName);
		log.debug(campaignCode);

		DataHolder fileDataHolder = null;
		fileDataHolder = toDataHolder(fileDataHolder, campaignCode);

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailyTsrProduction-output.xml");
			output = new FileOutputStream(outputFileName);
			new ExcelFormat(fileFormat).writeExcel(output, fileDataHolder);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				fileFormat.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	private DataHolder toDataHolder(DataHolder fileDataHolder, String campaignCode)
			throws Exception
	{
		DailyTsrProductionInfo  dailyTsrProductionInfo = getDailyTsrProductionInfoService().findTsrProductionInfo();
		List<DailyTsrProductionRecord> dailyTsrProductionRecordList = getDailyTsrProductionRecordService().findTsrProductionRecord();
		List<DailyTsrProductionRecord> dailyTsrProductionSummaryList = getDailyTsrProductionRecordService().findTsrProductionSummary();

		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("TSR_Production", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "TSR_Production");

		sheetDataHolder.put("campaignName", new SimpleMapDataHolder().setValue(dailyTsrProductionInfo.getCampaignName()));
		sheetDataHolder.put("campaignCode", new SimpleMapDataHolder().setValue(dailyTsrProductionInfo.getCampaignCode()));
		sheetDataHolder.put("callCenter", new SimpleMapDataHolder().setValue(dailyTsrProductionInfo.getCallCenter()));
		sheetDataHolder.put("period", new SimpleMapDataHolder().setValue(dailyTsrProductionInfo.getPeriod()));
		sheetDataHolder.put("printDate", new SimpleMapDataHolder().setValue(dailyTsrProductionInfo.getPrintDate()));

		List<DataHolder> tsrList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataRecord", tsrList);
		for (DailyTsrProductionRecord tsr : dailyTsrProductionRecordList)
		{
			DataHolder record = new SimpleMapDataHolder();
			record.put("recordId", new SimpleMapDataHolder().setValue(tsr.getRecordId()));
			record.put("tsrFullName", new SimpleMapDataHolder().setValue(tsr.getTsrFullName()));
			record.put("workingDay", new SimpleMapDataHolder().setValue(tsr.getWorkingDay()));
			record.put("hours", new SimpleMapDataHolder().setValue(tsr.getHours()));
			record.put("completed", new SimpleMapDataHolder().setValue(tsr.getCompleted()));
			record.put("contacts", new SimpleMapDataHolder().setValue(tsr.getContacts()));
			record.put("sales", new SimpleMapDataHolder().setValue(tsr.getSales()));
			record.put("completedPerHours", new SimpleMapDataHolder().setValue(tsr.getComplPerHours()));
			record.put("cph", new SimpleMapDataHolder().setValue(tsr.getCph()));
			record.put("sph", new SimpleMapDataHolder().setValue(tsr.getSph()));
			record.put("spc", new SimpleMapDataHolder().setValue(tsr.getSpc()));
			record.put("spl", new SimpleMapDataHolder().setValue(tsr.getSpl()));
			record.put("amp", new SimpleMapDataHolder().setValue(tsr.getAmp()));
			record.put("tmp", new SimpleMapDataHolder().setValue(tsr.getTmp()));
			record.put("totalApi", new SimpleMapDataHolder().setValue(tsr.getTotalApi()));
			tsrList.add(record);
		}

		List<DataHolder> blankRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("blankRecord", blankRecordList);
		blankRecordList.add(new SimpleMapDataHolder());

		List<DataHolder> headerTotalRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("headerTotalRecord", headerTotalRecordList);
		headerTotalRecordList.add(new SimpleMapDataHolder());

		List<DataHolder> tsrSummaryList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataTotalRecord", tsrSummaryList);
		for (DailyTsrProductionRecord tsr : dailyTsrProductionSummaryList)
		{
			DataHolder record = new SimpleMapDataHolder();
			record.put("recordId", new SimpleMapDataHolder().setValue(tsr.getRecordId()));
			record.put("tsrFullName", new SimpleMapDataHolder().setValue(tsr.getTsrFullName()));
			record.put("workingDay", new SimpleMapDataHolder().setValue(tsr.getWorkingDay()));
			record.put("hours", new SimpleMapDataHolder().setValue(tsr.getHours()));
			record.put("completed", new SimpleMapDataHolder().setValue(tsr.getCompleted()));
			record.put("contacts", new SimpleMapDataHolder().setValue(tsr.getContacts()));
			record.put("sales", new SimpleMapDataHolder().setValue(tsr.getSales()));
			record.put("completedPerHours", new SimpleMapDataHolder().setValue(tsr.getComplPerHours()));
			record.put("cph", new SimpleMapDataHolder().setValue(tsr.getCph()));
			record.put("sph", new SimpleMapDataHolder().setValue(tsr.getSph()));
			record.put("spc", new SimpleMapDataHolder().setValue(tsr.getSpc()));
			record.put("spl", new SimpleMapDataHolder().setValue(tsr.getSpl()));
			record.put("amp", new SimpleMapDataHolder().setValue(tsr.getAmp()));
			record.put("tmp", new SimpleMapDataHolder().setValue(tsr.getTmp()));
			record.put("totalApi", new SimpleMapDataHolder().setValue(tsr.getTotalApi()));
			tsrSummaryList.add(record);
		}

		return fileDataHolder;
	}
}
