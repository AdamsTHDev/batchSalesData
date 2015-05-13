package com.adms.batch.sales.report.partner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLClassLoader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DailyPerformanceTracking;
import com.adms.batch.sales.service.DailyPerformanceTrackingService;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailyPerformanceTrackingReport extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignCode = args[0];
		//yyyyMMdd_HHmmssSSS
		String outputFileName = args[1] + "/Daily_Performance_Tracking_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xls";
		
		new File(args[1]).mkdirs();
		
//		String campaignCode = "021DP1714L04";
//		String keyCode = "ACU15";
//		String outputFileName = "d:/testOutput.xls";

		DailyPerformanceTrackingReport batch = new DailyPerformanceTrackingReport();
		batch.setLogLevel(Logger.DEBUG);
		batch.generateReport(campaignCode, outputFileName);
	}

	public void generateReport(String campaignCode, String outputFileName)
			throws Exception
	{
		log.debug(outputFileName);
		log.debug(campaignCode);
		
		List<String> keyCodeList = getDailyPerformanceKeyCodeList(campaignCode);
		
		DataHolder fileDataHolder = null;
		int sheetIndex = 1;
		for (String keyCode : keyCodeList)
		{
			log.debug(keyCode);

			List<DailyPerformanceTracking> dailyDataList = getData(campaignCode, keyCode);

			fileDataHolder = toDataHolder(sheetIndex++, fileDataHolder, dailyDataList);
		}

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("fileformat/report/partner/FileFormat_Partner_DailyPerformanceTrackingByLot-output.xml");
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

	private List<String> getDailyPerformanceKeyCodeList(String campaignCode)
			throws Exception
	{
		DailyPerformanceTrackingService performanceTrackingService = (DailyPerformanceTrackingService) getBean("dailyPerformanceTrackingService");

		return performanceTrackingService.findDailyPerformanceKeyCodeByCampaign(campaignCode);
	}

	private List<DailyPerformanceTracking> getData(String campaignCode, String keyCode)
			throws Exception
	{
		DailyPerformanceTrackingService performanceTrackingService = (DailyPerformanceTrackingService) getBean("dailyPerformanceTrackingService");

		return performanceTrackingService.findDailyPerformanceTrackingByCampaign(campaignCode, keyCode);
	}

	private DataHolder toDataHolder(int sheetIndex, DataHolder fileDataHolder, List<DailyPerformanceTracking> dailyPerformanceTrackingList) throws ParseException
	{
		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		
		// data record
		List<DataHolder> dailyDataRecordList = new ArrayList<DataHolder>();
		List<DataHolder> mtdDataRecordList = new ArrayList<DataHolder>();
		List<DataHolder> dailyDataRecordList2 = new ArrayList<DataHolder>();
		List<DataHolder> mtdDataRecordList2 = new ArrayList<DataHolder>();
		List<DataHolder> ctdDataRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dailyRecord", dailyDataRecordList);
		sheetDataHolder.putDataList("mtdRecord", mtdDataRecordList);
		sheetDataHolder.putDataList("dailyRecord2", dailyDataRecordList2);
		sheetDataHolder.putDataList("mtdRecord2", mtdDataRecordList2);
		sheetDataHolder.putDataList("ctdRecord", ctdDataRecordList);

		setRecordDataHolder(dailyDataRecordList, dailyPerformanceTrackingList, "DAILY");
		setRecordDataHolder(mtdDataRecordList, dailyPerformanceTrackingList, "MTD");
		setRecordDataHolder(dailyDataRecordList2, dailyPerformanceTrackingList, "DAILY2");
		setRecordDataHolder(mtdDataRecordList2, dailyPerformanceTrackingList, "MTD2");
		setRecordDataHolder(ctdDataRecordList, dailyPerformanceTrackingList, "CTD");
		
		// data header (from MTD record)
		DataHolder dataHeader = CollectionUtils.isNotEmpty(mtdDataRecordList) ? mtdDataRecordList.get(0) : mtdDataRecordList2.get(0);

		// sheet name
		String sheetName = "Lot_" + new SimpleDateFormat("MMM", Locale.US).format(dataHeader.get("Lot Effective Date").getValue());
		fileDataHolder.setSheetNameByIndex(sheetIndex, sheetName);
		fileDataHolder.put(sheetName, sheetDataHolder);
		log.debug(sheetName + ", " + sheetIndex);

		DataHolder campaignCode = new SimpleMapDataHolder();
		campaignCode.setValue(dataHeader.get("Campaign Code").getValue());
		sheetDataHolder.put("Campaign Code", campaignCode);

		DataHolder campaignName = new SimpleMapDataHolder();
		campaignName.setValue(dataHeader.get("Campaign Name").getValue());
		sheetDataHolder.put("Campaign Name", campaignName);

		DataHolder campaignInfo = new SimpleMapDataHolder();
		campaignInfo.setValue(dataHeader.get("Campaign Info").getValue());
		sheetDataHolder.put("Campaign Info", campaignInfo);

		DataHolder bpName = new SimpleMapDataHolder();
		bpName.setValue(dataHeader.get("BP Name").getValue());
		sheetDataHolder.put("BP Name", bpName);

		DataHolder leadLoad = new SimpleMapDataHolder();
		leadLoad.setValue(dataHeader.get("Lead Load").getValue());
		sheetDataHolder.put("Lead Load", leadLoad);

		DataHolder newLeadRemaining = new SimpleMapDataHolder();
		newLeadRemaining.setValue(dataHeader.get("New Lead Remaining").getValue());
		sheetDataHolder.put("New Lead Remaining", newLeadRemaining);

		DataHolder referralLead = new SimpleMapDataHolder();
		referralLead.setValue(dataHeader.get("Referral Lead").getValue());
		sheetDataHolder.put("Referral Lead", referralLead);

		DataHolder salePending = new SimpleMapDataHolder();
		salePending.setValue(dataHeader.get("Sale Pending").getValue());
		sheetDataHolder.put("Sale Pending", salePending);
		
		DataHolder fromDate = new SimpleMapDataHolder();
		fromDate.setValue(dataHeader.get("From Date").getValue());
		sheetDataHolder.put("From Date", fromDate);
		
		DataHolder toDate = new SimpleMapDataHolder();
		toDate.setValue(dataHeader.get("To Date").getValue());
		sheetDataHolder.put("To Date", toDate);
		
		DataHolder printDate = new SimpleMapDataHolder();
		printDate.setValue(dataHeader.get("Print Date").getValue());
		sheetDataHolder.put("Print Date", printDate);

		return fileDataHolder;
	}
	
	private void setRecordDataHolder(List<DataHolder> dataRecordList, List<DailyPerformanceTracking> dailyPerformanceTrackingList, String recordType) throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.US);
		DecimalFormat dcf = new DecimalFormat("0.00");
		
		int section = 0;
		if ("DAILY".equals(recordType))
		{
			section = 1;
		}
		else if ("MTD".equals(recordType))
		{
			section = 2;
		}
		else if ("DAILY2".equals(recordType))
		{
			section = 3;
		}
		else if ("MTD2".equals(recordType))
		{
			section = 4;
		}
		else if ("CTD".equals(recordType))
		{
			section = 5;
		}
		
		for (DailyPerformanceTracking dailyPerformanceTracking : dailyPerformanceTrackingList)
		{
			if (section != dailyPerformanceTracking.getSection())
			{
				continue;
			}

			DataHolder recordDataHolder = new SimpleMapDataHolder();

			DataHolder date = new SimpleMapDataHolder();
			if ("MTD".equals(dailyPerformanceTracking.getDate()) || "CTD".equals(dailyPerformanceTracking.getDate()))
			{
				date.setValue(dailyPerformanceTracking.getDate());
			}
			else
			{
				date.setValue(df.parse(dailyPerformanceTracking.getDate()));
			}
			recordDataHolder.put("Date", date);

			DataHolder tsrNo = new SimpleMapDataHolder();
			tsrNo.setValue(dailyPerformanceTracking.getTsrNo());
			recordDataHolder.put("TSR No", tsrNo);

			DataHolder newLeadUsedPerTsr = new SimpleMapDataHolder();
			newLeadUsedPerTsr.setValue(dailyPerformanceTracking.getNewLeadUsedPerTsr());
			recordDataHolder.put("New Lead Used / TSR", newLeadUsedPerTsr);

			DataHolder newLeadUsed = new SimpleMapDataHolder();
			newLeadUsed.setValue(dailyPerformanceTracking.getNewLeadUsed());
			recordDataHolder.put("New Lead Used", newLeadUsed);

			DataHolder callbackNocontactFollowUsed = new SimpleMapDataHolder();
			callbackNocontactFollowUsed.setValue(dailyPerformanceTracking.getCallbackNocontactFollowUsed());
			recordDataHolder.put("Callback / Nocontact / Follow Used", callbackNocontactFollowUsed);

			DataHolder totalLeadUsed = new SimpleMapDataHolder();
			totalLeadUsed.setValue(dailyPerformanceTracking.getTotalLeadUsed());
			recordDataHolder.put("Total Lead Used", totalLeadUsed);

			DataHolder callbackNocontactRemaining = new SimpleMapDataHolder();
			callbackNocontactRemaining.setValue(dailyPerformanceTracking.getCallbackNocontactRemaining());
			recordDataHolder.put("Callback/ Nocontact (Remaining)", callbackNocontactRemaining);

			DataHolder followUpRemaining = new SimpleMapDataHolder();
			followUpRemaining.setValue(dailyPerformanceTracking.getFollowUpRemaining());
			recordDataHolder.put("Follow up (Remaining)", followUpRemaining);

			DataHolder totalCompleted = new SimpleMapDataHolder();
			totalCompleted.setValue(dailyPerformanceTracking.getTotalCompleted());
			recordDataHolder.put("Total Completed", totalCompleted);

			DataHolder contact = new SimpleMapDataHolder();
			contact.setValue(dailyPerformanceTracking.getContact());
			recordDataHolder.put("Contact", contact);

			DataHolder sale = new SimpleMapDataHolder();
			sale.setValue(dailyPerformanceTracking.getSale());
			recordDataHolder.put("Sale", sale);

			DataHolder bpCr = new SimpleMapDataHolder();
			bpCr.setValue(dcf.format(dailyPerformanceTracking.getBpCr().multiply(new BigDecimal(100))) + '%');
			recordDataHolder.put("BP CR%", bpCr);

			DataHolder spc = new SimpleMapDataHolder();
			spc.setValue(dcf.format(dailyPerformanceTracking.getSpc().multiply(new BigDecimal(100))) + '%');
//			spc.setValue(dailyPerformanceTracking.getSpc());
			recordDataHolder.put("SPC", spc);

			DataHolder lc = new SimpleMapDataHolder();
			lc.setValue(dcf.format(dailyPerformanceTracking.getListConversion().multiply(new BigDecimal(100))) + '%');
//			lc.setValue(dailyPerformanceTracking.getListConversion());
			recordDataHolder.put("List Conv.", lc);

			DataHolder typ = new SimpleMapDataHolder();
			typ.setValue(dailyPerformanceTracking.getTyp());
			recordDataHolder.put("TYP", typ);

			DataHolder amp = new SimpleMapDataHolder();
			amp.setValue(dailyPerformanceTracking.getAmp());
			recordDataHolder.put("AMP", amp);

			DataHolder ayp = new SimpleMapDataHolder();
			ayp.setValue(dailyPerformanceTracking.getAyp());
			recordDataHolder.put("AYP", ayp);

			DataHolder campaignCode = new SimpleMapDataHolder();
			campaignCode.setValue(dailyPerformanceTracking.getCampaignCode());
			recordDataHolder.put("Campaign Code", campaignCode);

			DataHolder campaignName = new SimpleMapDataHolder();
			campaignName.setValue(dailyPerformanceTracking.getCampaignName());
			recordDataHolder.put("Campaign Name", campaignName);

			DataHolder campaignInfo = new SimpleMapDataHolder();
			campaignInfo.setValue(dailyPerformanceTracking.getCampaignInfo());
			recordDataHolder.put("Campaign Info", campaignInfo);

			DataHolder bpName = new SimpleMapDataHolder();
			bpName.setValue(dailyPerformanceTracking.getBpName());
			recordDataHolder.put("BP Name", bpName);

			DataHolder fromDate = new SimpleMapDataHolder();
			fromDate.setValue(dailyPerformanceTracking.getFormDate());
			recordDataHolder.put("From Date", fromDate);

			DataHolder toDate = new SimpleMapDataHolder();
			toDate.setValue(dailyPerformanceTracking.getToDate());
			recordDataHolder.put("To Date", toDate);

			DataHolder printDate = new SimpleMapDataHolder();
			printDate.setValue(dailyPerformanceTracking.getPrintDate());
			recordDataHolder.put("Print Date", printDate);

			DataHolder leadLoad = new SimpleMapDataHolder();
			leadLoad.setValue(dailyPerformanceTracking.getLeadLoad());
			recordDataHolder.put("Lead Load", leadLoad);

			DataHolder newLeadRemaining = new SimpleMapDataHolder();
			newLeadRemaining.setValue(dailyPerformanceTracking.getNewLeadRemaining());
			recordDataHolder.put("New Lead Remaining", newLeadRemaining);

			DataHolder referralLead = new SimpleMapDataHolder();
			referralLead.setValue(dailyPerformanceTracking.getReferralLead());
			recordDataHolder.put("Referral Lead", referralLead);

			DataHolder salePending = new SimpleMapDataHolder();
			salePending.setValue(dailyPerformanceTracking.getSalePending());
			recordDataHolder.put("Sale Pending", salePending);

			DataHolder lotEffectiveDate = new SimpleMapDataHolder();
			lotEffectiveDate.setValue(dailyPerformanceTracking.getLotEffectiveDate());
			recordDataHolder.put("Lot Effective Date", lotEffectiveDate);

			dataRecordList.add(recordDataHolder);
		
		}
	}

}
