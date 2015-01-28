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

import com.adms.batch.sales.domain.DailySummaryStatusType2Detail;
import com.adms.batch.sales.service.DailySummaryStatusType2DetailService;
import com.adms.batch.sales.test.AbstractImportSalesJob;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class DailySummaryStatusType2Report extends AbstractImportSalesJob {

	public static void main(String[] args)
			throws Exception
	{
		String campaignName = args[0];
		String processDate = args[1];
		String outputFileName = args[2] + "/DailySummaryStatusType2_" + new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US).format(new Date()) + ".xls";
		
		new File(args[2]).mkdirs();

		campaignName = "AEGON MSIG-POM";
		processDate = "20150107";
//		String outputFileName = args[2] 
		
		DailySummaryStatusType2Report batch = new DailySummaryStatusType2Report();
		batch.setLogLevel(Logger.DEBUG);
		batch.generateReport(campaignName, processDate, outputFileName);
	}

	private void generateReport(String campaignName, String processDate, String outputFileName)
			throws Exception
	{
		log.debug(campaignName);
		log.debug(processDate);
		log.debug(outputFileName);
		

		DataHolder fileDataHolder = new SimpleMapDataHolder();
		aggregateFileDataHolder(campaignName, processDate, fileDataHolder);

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailySummaryStatusType2-output-TELE.xml");
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

	private void aggregateFileDataHolder(String campaignName, String processDate, DataHolder fileDataHolder) throws Exception
	{
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("SummaryStatusType2Report", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "SummaryStatusType2Report");

		List<DataHolder> recordDataHolderList = new ArrayList<DataHolder>();
		List<DailySummaryStatusType2Detail> dataList = getData(campaignName, processDate);
		for (DailySummaryStatusType2Detail detail : dataList)
		{
			DataHolder recordDataHolder = new SimpleMapDataHolder();
			DataHolder dataHolder = null;
			
			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(detail.getReasonMain());
			recordDataHolder.put("ReasonMain", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(detail.getReasonSub());
			recordDataHolder.put("ReasonSub", dataHolder);
			
			recordDataHolderList.add(recordDataHolder);
		}
		
		sheetDataHolder.putDataList("dailyRecord", recordDataHolderList);
		
		
		List<DataHolder> totalDataHolderList = new ArrayList<DataHolder>();
		DataHolder totalRecordDataHolder = new SimpleMapDataHolder();
		DataHolder totalDataHolder = new SimpleMapDataHolder();
		totalDataHolder.setValue("Total");
		totalRecordDataHolder.put("total", totalDataHolder);
		totalDataHolderList.add(totalRecordDataHolder);
		sheetDataHolder.putDataList("totalRecord", totalDataHolderList);
	}

	private List<DailySummaryStatusType2Detail> getData(String campaignName, String processDate) throws Exception
	{
		DailySummaryStatusType2DetailService service = (DailySummaryStatusType2DetailService) getBean("dailySummaryStatusType2DetailService");
		List<String> keyCodeList = service.findKeyCodeByCampaignAndProcessDate(campaignName, processDate);
		for (String keyCode : keyCodeList)
		{
			return service.findByCampaignAndProcessDateAndKeyCode(campaignName, processDate, keyCode);
		}
//		return service.findByCampaignAndProcessDate(campaignName, processDate);
		return null;
	}

}
