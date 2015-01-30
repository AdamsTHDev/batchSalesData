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

import org.apache.commons.lang3.StringUtils;

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

		campaignName = "AEGON FWD-ENDOWMENT";
		processDate = "20150105";
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
			fileFormat = URLClassLoader.getSystemResourceAsStream("FileFormat_Partner_DailySummaryStatusType2-output-OTO.xml");
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
		DailySummaryStatusType2DetailService service = (DailySummaryStatusType2DetailService) getBean("dailySummaryStatusType2DetailService");
		
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("SummaryStatusType2Report", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "SummaryStatusType2Report");
		
		List<String> keyCodeList = service.findKeyCodeByCampaignAndProcessDate(campaignName, processDate);

		int i = 1;
		for (String keyCode : keyCodeList)
		{
			String lotDescription = service.findLotDecriptionByKeyCode(keyCode);
			
			DataHolder lotDescriptionDataHolder = new SimpleMapDataHolder();
			lotDescriptionDataHolder.setValue(lotDescription);
			
			List<DataHolder> headerRecordList = new ArrayList<DataHolder>();
			DataHolder headerRecord = new SimpleMapDataHolder();
			headerRecord.put("LotDescription", lotDescriptionDataHolder);
			headerRecordList.add(headerRecord);
			sheetDataHolder.putDataList("headerRecord" + i, headerRecordList);
			
			List<DataHolder> dataRecordList = new ArrayList<DataHolder>();
			List<DailySummaryStatusType2Detail> dailySummaryStatusType2DetailList = service.findByCampaignAndProcessDateAndKeyCode(campaignName, processDate, keyCode);
			String reasonMainCheck = "";
			Integer totalMain = 0;
			Integer totalSub = 0;
			for (DailySummaryStatusType2Detail detail : dailySummaryStatusType2DetailList)
			{
				if (StringUtils.isBlank(reasonMainCheck))
				{
					reasonMainCheck = detail.getReasonMain();
				}

				DataHolder dataRecord = null;
				DataHolder noOfRecord = null;

				if (!reasonMainCheck.equals(detail.getReasonMain()))
				{
					dataRecord = new SimpleMapDataHolder();
					noOfRecord = new SimpleMapDataHolder();
					noOfRecord.setValue(totalSub);
					dataRecord.put("NoOfRecord", noOfRecord);
					dataRecordList.add(dataRecord);

					totalSub = 0;
					reasonMainCheck = detail.getReasonMain();
				}

				dataRecord = new SimpleMapDataHolder();
				noOfRecord = new SimpleMapDataHolder();
				noOfRecord.setValue(detail.getNoOfRecord());
				dataRecord.put("NoOfRecord", noOfRecord);
				dataRecordList.add(dataRecord);

				totalMain += detail.getNoOfRecord();
				totalSub += detail.getNoOfRecord();
			}

			DataHolder dataRecord = new SimpleMapDataHolder();
			DataHolder noOfRecord = new SimpleMapDataHolder();
			noOfRecord.setValue(totalSub);
			dataRecord.put("NoOfRecord", noOfRecord);
			dataRecordList.add(dataRecord);

			dataRecord = new SimpleMapDataHolder();
			noOfRecord = new SimpleMapDataHolder();
			noOfRecord.setValue(totalMain);
			dataRecord.put("NoOfRecord", noOfRecord);
			dataRecordList.add(dataRecord);

			sheetDataHolder.putDataList("dataRecord" + i, dataRecordList);

			i++;
		}
	}

}
