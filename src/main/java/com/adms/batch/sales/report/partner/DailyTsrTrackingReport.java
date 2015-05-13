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
import com.adms.batch.sales.domain.DailyTsrTrackingRecord;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailyTsrTrackingReport extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignName = args[0];
		//yyyyMMdd_HHmmssSSS
		String outputFileName = args[1] + "/TSR_Tracking_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xls";
		
		new File(args[1]).mkdirs();

		DailyTsrTrackingReport batch = new DailyTsrTrackingReport();
		batch.setLogLevel(Logger.DEBUG);
		batch.generateReport(campaignName, outputFileName);
	}

	private void generateReport(String campaignName, String outputFileName)
			throws Exception
	{
		log.debug(outputFileName);
		log.debug(campaignName);

		DataHolder fileDataHolder = null;
		fileDataHolder = toDataHolder(fileDataHolder, campaignName);

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailyTsrTracking-output.xml");
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

	private DataHolder toDataHolder(DataHolder fileDataHolder, String campaignName)
			throws Exception
	{
		List<DailyTsrTrackingRecord> tsrTrackingRecordList = getDailyTsrTrackingRecordService().findTsrTrackingRecord(campaignName);
		List<DailyTsrTrackingRecord> tsrTrackingSummaryList = getDailyTsrTrackingRecordService().findTsrTrackingSummary(campaignName);
		
		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("TSR Tracking", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "TSR Tracking");

		List<DataHolder> tsrList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataRecord", tsrList);
		boolean setHeader = false;
		for (DailyTsrTrackingRecord tsr : tsrTrackingRecordList)
		{
			if (!setHeader)
			{
				sheetDataHolder.put("campaignName", new SimpleMapDataHolder().setValue(tsr.getCampaign()));
				sheetDataHolder.put("period", new SimpleMapDataHolder().setValue(tsr.getPeriod()));
				sheetDataHolder.put("printDate", new SimpleMapDataHolder().setValue(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new SimpleDateFormat("yyyyMMdd", Locale.US).parseObject(tsr.getPrintDate()))));
				setHeader = true;
			}

			DataHolder record = new SimpleMapDataHolder();
			record.put("recordId", new SimpleMapDataHolder().setValue(tsr.getRecordId()));
			record.put("No.", new SimpleMapDataHolder().setValue(tsr.getItemNo()));
			record.put("Name", new SimpleMapDataHolder().setValue(tsr.getTsrName()));
			record.put("Work Days", new SimpleMapDataHolder().setValue(tsr.getWorkDays()));
			record.put("List Used", new SimpleMapDataHolder().setValue(tsr.getListUsed()));
			record.put("Comp.", new SimpleMapDataHolder().setValue(tsr.getCompleted()));
			record.put("Remaining New", new SimpleMapDataHolder().setValue(tsr.getRemainingNew()));
			record.put("Remaining Call back", new SimpleMapDataHolder().setValue(tsr.getRemainingCallBack()));
			record.put("Remaining No Contact", new SimpleMapDataHolder().setValue(tsr.getRemainingNoContact()));
			record.put("Remaining Follow up", new SimpleMapDataHolder().setValue(tsr.getRemainingFollowUp()));
			record.put("Remaining Total", new SimpleMapDataHolder().setValue(tsr.getRemainingTotal()));
			record.put("New Used", new SimpleMapDataHolder().setValue(tsr.getNewUsed()));
			record.put("Follow Used", new SimpleMapDataHolder().setValue(tsr.getFollowUsed()));
			record.put("Call Back Used", new SimpleMapDataHolder().setValue(tsr.getCallBackUsed()));
			record.put("No Contact Used", new SimpleMapDataHolder().setValue(tsr.getNoContactUsed()));
			record.put("Policy 1 st", new SimpleMapDataHolder().setValue(tsr.getPolicyFirst()));
			record.put("Policy %", new SimpleMapDataHolder().setValue(tsr.getPolicyPercent()));
			record.put("Policy Follow", new SimpleMapDataHolder().setValue(tsr.getPolicyFollow()));
			record.put("Plan Standard", new SimpleMapDataHolder().setValue(tsr.getPlanStandard()));
			record.put("Plan Premier", new SimpleMapDataHolder().setValue(tsr.getPlanPremier()));
			record.put("Total Policy", new SimpleMapDataHolder().setValue(tsr.getTotalPolicy()));
			record.put("Policy API", new SimpleMapDataHolder().setValue(tsr.getPolicyApi()));
			record.put("Total DMC Present", new SimpleMapDataHolder().setValue(tsr.getTotalDmcPresent()));
			record.put("Total DMC No Present", new SimpleMapDataHolder().setValue(tsr.getTotalDmcNoPresent()));
			record.put("DMC Contact Rate (60%)", new SimpleMapDataHolder().setValue(tsr.getDmcContactRate()));
			record.put("CPH", new SimpleMapDataHolder().setValue(tsr.getCph()));
			record.put("SPH", new SimpleMapDataHolder().setValue(tsr.getSph()));
			record.put("SPC (Total)", new SimpleMapDataHolder().setValue(tsr.getSpcTotal()));
			record.put("SPC (Present)", new SimpleMapDataHolder().setValue(tsr.getSpcPresent()));
			record.put("Hours (Hrs)", new SimpleMapDataHolder().setValue(tsr.getHours()));
			record.put("DMC. Avg. Talk Time", new SimpleMapDataHolder().setValue(tsr.getDmcAverageTalkTime()));
			record.put("Max. Talk Time (Min.)", new SimpleMapDataHolder().setValue(tsr.getMaxTalkTime()));
			record.put("DMC Talk Time (Hr.)", new SimpleMapDataHolder().setValue(tsr.getDmcTalkTime()));
			record.put("Total Talk Time (Hr.)", new SimpleMapDataHolder().setValue(tsr.getTotalTalkTime()));
			tsrList.add(record);
		}

		List<DataHolder> tsrSummaryList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataSummary", tsrSummaryList);
		for (DailyTsrTrackingRecord tsr : tsrTrackingSummaryList)
		{
			DataHolder record = new SimpleMapDataHolder();
			record.put("recordId", new SimpleMapDataHolder().setValue(tsr.getRecordId()));
			record.put("No.", new SimpleMapDataHolder().setValue(tsr.getItemNo()));
			record.put("Name", new SimpleMapDataHolder().setValue("Total"));
			record.put("Work Days", new SimpleMapDataHolder().setValue(tsr.getWorkDays()));
			record.put("List Used", new SimpleMapDataHolder().setValue(tsr.getListUsed()));
			record.put("Comp.", new SimpleMapDataHolder().setValue(tsr.getCompleted()));
			record.put("Remaining New", new SimpleMapDataHolder().setValue(tsr.getRemainingNew()));
			record.put("Remaining Call back", new SimpleMapDataHolder().setValue(tsr.getRemainingCallBack()));
			record.put("Remaining No Contact", new SimpleMapDataHolder().setValue(tsr.getRemainingNoContact()));
			record.put("Remaining Follow up", new SimpleMapDataHolder().setValue(tsr.getRemainingFollowUp()));
			record.put("Remaining Total", new SimpleMapDataHolder().setValue(tsr.getRemainingTotal()));
			record.put("New Used", new SimpleMapDataHolder().setValue(tsr.getNewUsed()));
			record.put("Follow Used", new SimpleMapDataHolder().setValue(tsr.getFollowUsed()));
			record.put("Call Back Used", new SimpleMapDataHolder().setValue(tsr.getCallBackUsed()));
			record.put("No Contact Used", new SimpleMapDataHolder().setValue(tsr.getNoContactUsed()));
			record.put("Policy 1 st", new SimpleMapDataHolder().setValue(tsr.getPolicyFirst()));
			record.put("Policy %", new SimpleMapDataHolder().setValue(tsr.getPolicyPercent()));
			record.put("Policy Follow", new SimpleMapDataHolder().setValue(tsr.getPolicyFollow()));
			record.put("Plan Standard", new SimpleMapDataHolder().setValue(tsr.getPlanStandard()));
			record.put("Plan Premier", new SimpleMapDataHolder().setValue(tsr.getPlanPremier()));
			record.put("Total Policy", new SimpleMapDataHolder().setValue(tsr.getTotalPolicy()));
			record.put("Policy API", new SimpleMapDataHolder().setValue(tsr.getPolicyApi()));
			record.put("Total DMC Present", new SimpleMapDataHolder().setValue(tsr.getTotalDmcPresent()));
			record.put("Total DMC No Present", new SimpleMapDataHolder().setValue(tsr.getTotalDmcNoPresent()));
			record.put("DMC Contact Rate (60%)", new SimpleMapDataHolder().setValue(tsr.getDmcContactRate()));
			record.put("CPH", new SimpleMapDataHolder().setValue(tsr.getCph()));
			record.put("SPH", new SimpleMapDataHolder().setValue(tsr.getSph()));
			record.put("SPC (Total)", new SimpleMapDataHolder().setValue(tsr.getSpcTotal()));
			record.put("SPC (Present)", new SimpleMapDataHolder().setValue(tsr.getSpcPresent()));
			record.put("Hours (Hrs)", new SimpleMapDataHolder().setValue(tsr.getHours()));
			record.put("DMC. Avg. Talk Time", new SimpleMapDataHolder().setValue(tsr.getDmcAverageTalkTime()));
			record.put("Max. Talk Time (Min.)", new SimpleMapDataHolder().setValue(tsr.getMaxTalkTime()));
			record.put("DMC Talk Time (Hr.)", new SimpleMapDataHolder().setValue(tsr.getDmcTalkTime()));
			record.put("Total Talk Time (Hr.)", new SimpleMapDataHolder().setValue(tsr.getTotalTalkTime()));
			tsrSummaryList.add(record);
		}

		return fileDataHolder;
	}
}
