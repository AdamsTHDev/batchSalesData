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
import com.adms.batch.sales.domain.DailyQcReconfirmRecord;
import com.adms.batch.sales.domain.DailyQcReconfirmSummary;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailyQcReconfirmReport extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignName = args[0];
		//yyyyMMdd_HHmmssSSS
		String outputFileName = args[1] + "/QC_Reconfirm_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xls";
		
		new File(args[1]).mkdirs();

		DailyQcReconfirmReport batch = new DailyQcReconfirmReport();
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
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailyQcReconfirm-output.xml");
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
		String printDate = getKeyValueBeanService().findQcReconfirmMaxPrintDate(campaignName).getValue();
		List<DailyQcReconfirmSummary> qcReconfirmStatusSummaryList = getDailyQcReconfirmSummaryService().findQcReconfirmStatusSummary(campaignName);
		List<DailyQcReconfirmSummary> qcReconfirmStatusTotalList = getDailyQcReconfirmSummaryService().findQcReconfirmStatusTotal(campaignName);
		List<DailyQcReconfirmRecord> qcReconfirmRecordList = getDailyQcReconfirmRecordService().findQcReconfirmRecord(campaignName);
		
		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("QC Reconfirm", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "QC Reconfirm");
		
		sheetDataHolder.put("printDate", new SimpleMapDataHolder().setValue(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new SimpleDateFormat("yyyyMMdd", Locale.US).parseObject(printDate))));
		
		List<DataHolder> summaryRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataSummary", summaryRecordList);
		for (DailyQcReconfirmSummary statusSummary : qcReconfirmStatusSummaryList)
		{
			DataHolder summaryRecord = new SimpleMapDataHolder();
			summaryRecord.put("qaStatus", new SimpleMapDataHolder().setValue(statusSummary.getQcStatus()));
			summaryRecord.put("qaStatusCount", new SimpleMapDataHolder().setValue(statusSummary.getTotal()));
			summaryRecordList.add(summaryRecord);
		}
		
		List<DataHolder> statusTotalRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataSummaryTotal", statusTotalRecordList);
		for (DailyQcReconfirmSummary statusTotal : qcReconfirmStatusTotalList)
		{
			DataHolder summaryRecord = new SimpleMapDataHolder();
			summaryRecord.put("qaStatus", new SimpleMapDataHolder().setValue(statusTotal.getQcStatus()));
			summaryRecord.put("qaStatusCount", new SimpleMapDataHolder().setValue(statusTotal.getTotal()));
			statusTotalRecordList.add(summaryRecord);
		}
		
		List<DataHolder> qcHeaderList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("qcListHeader", qcHeaderList);
		DataHolder qcHeader = new SimpleMapDataHolder();
		qcHeader.put("campaignName", new SimpleMapDataHolder().setValue("Campaign"));
		qcHeader.put("listLotName", new SimpleMapDataHolder().setValue("LotName/Slice"));
		qcHeader.put("xReference", new SimpleMapDataHolder().setValue("X-Ref"));
		qcHeader.put("saleDate", new SimpleMapDataHolder().setValue("Sale Date"));
		qcHeader.put("customerFullName", new SimpleMapDataHolder().setValue("Customer Name"));
		qcHeader.put("tsrCode", new SimpleMapDataHolder().setValue("TSR Code"));
		qcHeader.put("tsrFullName", new SimpleMapDataHolder().setValue("TSR Name"));
		qcHeader.put("supFullName", new SimpleMapDataHolder().setValue("Manager"));
		qcHeader.put("qcStatusDate", new SimpleMapDataHolder().setValue("QC Date"));
		qcHeader.put("qcId", new SimpleMapDataHolder().setValue("QC ID"));
		qcHeader.put("paymentMethod", new SimpleMapDataHolder().setValue("Payment"));
		qcHeader.put("premium", new SimpleMapDataHolder().setValue("Premium"	));
		qcHeader.put("tsrStatus", new SimpleMapDataHolder().setValue("TSRStatus"));
		qcHeader.put("qcStatus", new SimpleMapDataHolder().setValue("QCStatus"));
		qcHeader.put("reconfirmReason", new SimpleMapDataHolder().setValue("ReconfirmReason"));
		qcHeader.put("reconfirmRemark", new SimpleMapDataHolder().setValue("ReconfirmRemark"));
		qcHeader.put("currentReason", new SimpleMapDataHolder().setValue("CurrentReason"));
		qcHeader.put("currentRemark", new SimpleMapDataHolder().setValue("CurrentRemark"));
		qcHeaderList.add(qcHeader);
		
		List<DataHolder> qcRecordList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("qcList", qcRecordList);
		for (DailyQcReconfirmRecord qc : qcReconfirmRecordList)
		{
			DataHolder qcRecord = new SimpleMapDataHolder();
			qcRecord.put("campaignName", new SimpleMapDataHolder().setValue(qc.getCampaignName()));
			qcRecord.put("listLotName", new SimpleMapDataHolder().setValue(qc.getListLotName()));
			qcRecord.put("xReference", new SimpleMapDataHolder().setValue(qc.getxReference()));
			qcRecord.put("saleDate", new SimpleMapDataHolder().setValue(qc.getSaleDate()));
			qcRecord.put("customerFullName", new SimpleMapDataHolder().setValue(qc.getCustomerFullName()));
			qcRecord.put("tsrCode", new SimpleMapDataHolder().setValue(qc.getTsrCode()));
			qcRecord.put("tsrFullName", new SimpleMapDataHolder().setValue(qc.getTsrFullName()));
			qcRecord.put("supFullName", new SimpleMapDataHolder().setValue(qc.getSupFullName()));
			qcRecord.put("qcStatusDate", new SimpleMapDataHolder().setValue(qc.getQcStatusDate()));
			qcRecord.put("qcId", new SimpleMapDataHolder().setValue(qc.getQcId()));
			qcRecord.put("paymentMethod", new SimpleMapDataHolder().setValue(qc.getPaymentMethod()));
			qcRecord.put("premium", new SimpleMapDataHolder().setValue(qc.getPremium()));
			qcRecord.put("tsrStatus", new SimpleMapDataHolder().setValue(qc.getTsrStatus()));
			qcRecord.put("qcStatus", new SimpleMapDataHolder().setValue(qc.getQcStatus()));
			qcRecord.put("reconfirmReason", new SimpleMapDataHolder().setValue(qc.getReconfirmReason()));
			qcRecord.put("reconfirmRemark", new SimpleMapDataHolder().setValue(qc.getReconfirmRemark()));
			qcRecord.put("currentReason", new SimpleMapDataHolder().setValue(qc.getCurrentReason()));
			qcRecord.put("currentRemark", new SimpleMapDataHolder().setValue(qc.getCurrentRemark()));
			qcRecordList.add(qcRecord);
		}

		return fileDataHolder;
	}
}
