package com.adms.batch.sales.report.partner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DailySalesReportByRecord;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailySalesReportByRecordsReport extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignCode = args[0];
		//yyyyMMdd_HHmmssSSS
		String outputFileName = args[1] + "/Sales_Report_By_Records_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xls";

		new File(args[1]).mkdirs();

		DailySalesReportByRecordsReport batch = new DailySalesReportByRecordsReport();
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
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailySalesReportByRecords-output.xml");
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
		List<DailySalesReportByRecord> dailySalesReportByRecordList = getDailySalesReportByRecordService().findDailySalesReportByRecord(campaignCode);

		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("Sales_Report_By_Records", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "Sales_Report_By_Records");

		BigDecimal totalPremium = new BigDecimal(0.0f);
		BigDecimal totalAnnualPremium = new BigDecimal(0.0f);
		
		List<DataHolder> dataRecord = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataRecord", dataRecord);
		boolean setHeader = false;
		for (DailySalesReportByRecord dsr : dailySalesReportByRecordList)
		{
			if (!setHeader)
			{
				sheetDataHolder.put("campaignName", new SimpleMapDataHolder().setValue(dsr.getCampaignName()));
				sheetDataHolder.put("listLot", new SimpleMapDataHolder().setValue(dsr.getListLot()));
				sheetDataHolder.put("period", new SimpleMapDataHolder().setValue(dsr.getPeriod()));
				sheetDataHolder.put("printDate", new SimpleMapDataHolder().setValue(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new SimpleDateFormat("yyyyMMdd", Locale.US).parseObject(dsr.getPrintDate()))));
				setHeader = true;
			}

			DataHolder record = new SimpleMapDataHolder();
			record.put("campaignName", new SimpleMapDataHolder().setValue(dsr.getCampaignName()));
			record.put("campaignCode", new SimpleMapDataHolder().setValue(dsr.getCampaignCode()));
			record.put("listLotName", new SimpleMapDataHolder().setValue(dsr.getListLotName()));
			record.put("approveDate", new SimpleMapDataHolder().setValue(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new SimpleDateFormat("yyyyMMdd", Locale.US).parseObject(dsr.getApproveDate()))));
			record.put("xReference", new SimpleMapDataHolder().setValue(dsr.getxReference()));
			record.put("itemNo", new SimpleMapDataHolder().setValue(dsr.getItemNo()));
			record.put("customerFullName", new SimpleMapDataHolder().setValue(dsr.getCustomerFullName()));
			record.put("product", new SimpleMapDataHolder().setValue(dsr.getProduct()));
			record.put("premium", new SimpleMapDataHolder().setValue(dsr.getPremium()));
			record.put("annualPremium", new SimpleMapDataHolder().setValue(dsr.getAnnualPremium()));
			record.put("protectAmount", new SimpleMapDataHolder().setValue(dsr.getProtectAmount()));
			record.put("paymentChannel", new SimpleMapDataHolder().setValue(dsr.getPaymentChannel()));
			record.put("paymentFrequency", new SimpleMapDataHolder().setValue(dsr.getPaymentFrequency()));
			record.put("qaStatus", new SimpleMapDataHolder().setValue(dsr.getQaStatus()));
			record.put("qaReason", new SimpleMapDataHolder().setValue(dsr.getQaReason()));
			record.put("saleDate", new SimpleMapDataHolder().setValue(new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new SimpleDateFormat("yyyyMMdd", Locale.US).parseObject(dsr.getSaleDate()))));
			record.put("tsrCode", new SimpleMapDataHolder().setValue(dsr.getTsrCode()));
			record.put("tsrFullName", new SimpleMapDataHolder().setValue(dsr.getTsrName()));
			record.put("supCode", new SimpleMapDataHolder().setValue(dsr.getSupCode()));
			record.put("supFullName	", new SimpleMapDataHolder().setValue(dsr.getSupName()));
			record.put("qaReasonDetail", new SimpleMapDataHolder().setValue(dsr.getQaReasonDetail()));
			
			totalPremium = totalPremium.add(dsr.getPremium());
			totalAnnualPremium = totalAnnualPremium.add(dsr.getAnnualPremium());

			dataRecord.add(record);
		}
		
		List<DataHolder> dataTotalRecord = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("dataTotalRecord", dataTotalRecord);
		
		DataHolder totalRecord = new SimpleMapDataHolder();
		totalRecord.put("premium", new SimpleMapDataHolder().setValue(totalPremium));
		totalRecord.put("annualPremium", new SimpleMapDataHolder().setValue(totalAnnualPremium));
		dataTotalRecord.add(totalRecord);

		return fileDataHolder;
	}
}
