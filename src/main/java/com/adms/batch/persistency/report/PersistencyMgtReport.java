package com.adms.batch.persistency.report;

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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.adms.batch.persistency.domain.OutputPersistency;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.Logger;

public class PersistencyMgtReport extends AbstractPersistencyJob {

	public static void main(String[] args)
			throws Exception
	{
		String param = args[0];
		if (StringUtils.isBlank(param))
		{
			throw new Exception("args[0] cannot be empty");
		}
		
		String outputFileName = args[1] + "/TH_Persistancy_" + param + "_" + new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date()) + ".xlsx";
		
		new File(args[1]).mkdirs();

		PersistencyMgtReport batch = new PersistencyMgtReport();
		batch.setLogLevel(Logger.DEBUG);
		batch.generateReport(param, outputFileName);
	}

	private void generateReport(String param, String outputFileName)
			throws Exception
	{
		log.debug(outputFileName);
		log.debug(param);

		DataHolder fileDataHolder = null;
		fileDataHolder = toDataHolder(fileDataHolder, param);

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("fileformat/persistency/FileFormat_TH_PersistencyManagementReport-output.xml");
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

	private DataHolder toDataHolder(DataHolder fileDataHolder, String param)
			throws Exception
	{
//		List<OutputPersistency> persistencyRecordList = getOutputPersistencyService().findOutputPersistencyAll();
		List<OutputPersistency> persistencyRecordList = getOutputPersistencyService().dataCalculation(param);

		int maxHistoryYear = findMaxHistoryYear(persistencyRecordList);

		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("TH Persistency", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(1, "TH Persistency");
		
		sheetDataHolder.put("reportAsof", new SimpleMapDataHolder().setValue("Data Reporting " + param.substring(0, 6)));

		List<DataHolder> totalList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("totalDataRecord", totalList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("Total".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				totalList.add(record);
			}
		}

		List<DataHolder> frontingPartnerList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("frontingPartnerDataRecord", frontingPartnerList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[FRONTING PARTNER]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				frontingPartnerList.add(record);
			}
		}

		List<DataHolder> businessPartnerList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("businessPartnerDataRecord", businessPartnerList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[FILEOWNER CODE]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				businessPartnerList.add(record);
			}
		}

		List<DataHolder> paymentMethodList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("paymentMethodDataRecord", paymentMethodList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[BILLING MODE]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				paymentMethodList.add(record);
			}
		}

		List<DataHolder> productList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("productDataRecord", productList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[PRODUCT]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				productList.add(record);
			}
		}

		List<DataHolder> billingFrequencyList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("billingFrequencyDataRecord", billingFrequencyList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[BILLING FREQUENCY]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				billingFrequencyList.add(record);
			}
		}

		List<DataHolder> leadSourceList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("leadSourceDataRecord", leadSourceList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[Lead Source]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				leadSourceList.add(record);
			}
		}

		List<DataHolder> ageBandList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("ageBandDataRecord", ageBandList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[AGE BAND]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				ageBandList.add(record);
			}
		}

		List<DataHolder> premiumRangeList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("premiumRangeDataRecord", premiumRangeList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[PREM RANGE]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				premiumRangeList.add(record);
			}
		}

		List<DataHolder> planTypeList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("planTypeDataRecord", planTypeList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[PLAN TYPE]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				planTypeList.add(record);
			}
		}

		List<DataHolder> genderList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("genderDataRecord", genderList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[GENDER]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				genderList.add(record);
			}
		}

		List<DataHolder> frontingLeadSourceList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("frontingLeadSourceDataRecord", frontingLeadSourceList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[Fronting_Lead_Source]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				frontingLeadSourceList.add(record);
			}
		}

		List<DataHolder> campaignTypeList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("campaignTypeDataRecord", campaignTypeList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[CAMPAIGN TYPE]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				campaignTypeList.add(record);
			}
		}

		List<DataHolder> campaignList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("campaignDataRecord", campaignList);
		for (OutputPersistency persistency : persistencyRecordList)
		{
			if ("[Campaign]".equals(persistency.getVariableName()))
			{
				DataHolder record = toRecordDataHolder(persistency, maxHistoryYear);
				campaignList.add(record);
			}
		}


		ArrayList<DataHolder> blankList = new ArrayList<DataHolder>();
		blankList.add(new SimpleMapDataHolder());
		sheetDataHolder.putDataList("blankRecord1", blankList);
		sheetDataHolder.putDataList("blankRecord2", blankList);
		sheetDataHolder.putDataList("blankRecord3", blankList);
		sheetDataHolder.putDataList("blankRecord4", blankList);
		sheetDataHolder.putDataList("blankRecord5", blankList);
		sheetDataHolder.putDataList("blankRecord6", blankList);
		sheetDataHolder.putDataList("blankRecord7", blankList);
		sheetDataHolder.putDataList("blankRecord8", blankList);
		sheetDataHolder.putDataList("blankRecord9", blankList);
		sheetDataHolder.putDataList("blankRecord10", blankList);
		sheetDataHolder.putDataList("blankRecord11", blankList);
		sheetDataHolder.putDataList("blankRecord12", blankList);
		sheetDataHolder.putDataList("blankRecord13", blankList);

		sheetDataHolder.putDataList("headerRecord1", blankList);
		sheetDataHolder.putDataList("headerRecord2", blankList);
		sheetDataHolder.putDataList("headerRecord3", blankList);
		sheetDataHolder.putDataList("headerRecord4", blankList);
		sheetDataHolder.putDataList("headerRecord5", blankList);
		sheetDataHolder.putDataList("headerRecord6", blankList);
		sheetDataHolder.putDataList("headerRecord7", blankList);
		sheetDataHolder.putDataList("headerRecord8", blankList);
		sheetDataHolder.putDataList("headerRecord9", blankList);
		sheetDataHolder.putDataList("headerRecord10", blankList);
		sheetDataHolder.putDataList("headerRecord11", blankList);
		sheetDataHolder.putDataList("headerRecord12", blankList);
		sheetDataHolder.putDataList("headerRecord13", blankList);

		sheetDataHolder.putDataList("blankRecord99", blankList);

		return fileDataHolder;
	}

	private int findMaxHistoryYear(List<OutputPersistency> persistencyRecordList)
	{
		int maxHistoryYear = 10;

		if (CollectionUtils.isNotEmpty(persistencyRecordList))
		{
			for (OutputPersistency persistency : persistencyRecordList)
			{
				if ("Total".equals(persistency.getVariableName()))
				{
					if (persistency.getYear5().equals(persistency.getYear6()))
					{
						maxHistoryYear = 5;
					}
					else if (persistency.getYear6().equals(persistency.getYear7()))
					{
						maxHistoryYear = 6;
					}
					else if (persistency.getYear7().equals(persistency.getYear8()))
					{
						maxHistoryYear = 7;
					}
					else if (persistency.getYear8().equals(persistency.getYear9()))
					{
						maxHistoryYear = 8;
					}
					else if (persistency.getYear9().equals(persistency.getYear10()))
					{
						maxHistoryYear = 9;
					}
				}
			}
		}

		return maxHistoryYear;
	}

	private DataHolder toRecordDataHolder(OutputPersistency persistency, int maxHistoryYear)
	{
		DataHolder record = new SimpleMapDataHolder();

		record.put("variableName", new SimpleMapDataHolder().setValue(persistency.getVariableName()));
		if ("Total".equals(persistency.getVariableName()))
		{
			record.put("groupingFactor", new SimpleMapDataHolder().setValue("Total"));
		}
		else
		{
			record.put("groupingFactor", new SimpleMapDataHolder().setValue(persistency.getGroupingFactor()));
		}

		record.put("productionIap", new SimpleMapDataHolder().setValue(persistency.getProductionIap()));
		record.put("issuedIap", new SimpleMapDataHolder().setValue(persistency.getIssuedIap()));
		record.put("paidIap", new SimpleMapDataHolder().setValue(persistency.getPaidIap()));
		record.put("issuedRateIap", new SimpleMapDataHolder().setValue(persistency.getIssuedRateIap()));
		record.put("paidRateIap", new SimpleMapDataHolder().setValue(persistency.getPaidRateIap()));
		record.put("month3Iap", new SimpleMapDataHolder().setValue(persistency.getMonth3Iap()));
		record.put("month6Iap", new SimpleMapDataHolder().setValue(persistency.getMonth6Iap()));
		record.put("month9Iap", new SimpleMapDataHolder().setValue(persistency.getMonth9Iap()));
		record.put("month12Iap", new SimpleMapDataHolder().setValue(persistency.getMonth12Iap()));
		record.put("month18Iap", new SimpleMapDataHolder().setValue(persistency.getMonth18Iap()));
		record.put("year2Iap", new SimpleMapDataHolder().setValue(persistency.getYear2Iap()));
		record.put("year3Iap", new SimpleMapDataHolder().setValue(persistency.getYear3Iap()));
		record.put("year4Iap", new SimpleMapDataHolder().setValue(persistency.getYear4Iap()));

		record.put("production", new SimpleMapDataHolder().setValue(persistency.getProduction()));
		record.put("issued", new SimpleMapDataHolder().setValue(persistency.getIssued()));
		record.put("paid", new SimpleMapDataHolder().setValue(persistency.getExposure()));
		record.put("issuedRate", new SimpleMapDataHolder().setValue(persistency.getIssuedRate()));
		record.put("paidRate", new SimpleMapDataHolder().setValue(persistency.getPaidRate()));
		record.put("month3", new SimpleMapDataHolder().setValue(persistency.getMonth3()));
		record.put("month6", new SimpleMapDataHolder().setValue(persistency.getMonth6()));
		record.put("month9", new SimpleMapDataHolder().setValue(persistency.getMonth9()));
		record.put("month12", new SimpleMapDataHolder().setValue(persistency.getMonth12()));
		record.put("month18", new SimpleMapDataHolder().setValue(persistency.getMonth18()));
		record.put("year2", new SimpleMapDataHolder().setValue(persistency.getYear2()));
		record.put("year3", new SimpleMapDataHolder().setValue(persistency.getYear3()));
		record.put("year4", new SimpleMapDataHolder().setValue(persistency.getYear4()));

		if (maxHistoryYear >= 5)
		{
			record.put("year5Iap", new SimpleMapDataHolder().setValue(persistency.getYear5Iap()));
			record.put("year5", new SimpleMapDataHolder().setValue(persistency.getYear5()));
		}
		else
		{
			record.put("year5Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year5", new SimpleMapDataHolder().setValue(null));
		}

		if (maxHistoryYear >= 6)
		{
			record.put("year6Iap", new SimpleMapDataHolder().setValue(persistency.getYear6Iap()));
			record.put("year6", new SimpleMapDataHolder().setValue(persistency.getYear6()));
		}
		else
		{
			record.put("year6Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year6", new SimpleMapDataHolder().setValue(null));
		}

		if (maxHistoryYear >= 7)
		{
			record.put("year7Iap", new SimpleMapDataHolder().setValue(persistency.getYear7Iap()));
			record.put("year7", new SimpleMapDataHolder().setValue(persistency.getYear7()));
		}
		else
		{
			record.put("year7Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year7", new SimpleMapDataHolder().setValue(null));
		}

		if (maxHistoryYear >= 8)
		{
			record.put("year8Iap", new SimpleMapDataHolder().setValue(persistency.getYear8Iap()));
			record.put("year8", new SimpleMapDataHolder().setValue(persistency.getYear8()));
		}
		else
		{
			record.put("year8Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year8", new SimpleMapDataHolder().setValue(null));
		}

		if (maxHistoryYear >= 9)
		{
			record.put("year9Iap", new SimpleMapDataHolder().setValue(persistency.getYear9Iap()));
			record.put("year9", new SimpleMapDataHolder().setValue(persistency.getYear9()));
		}
		else
		{
			record.put("year9Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year9", new SimpleMapDataHolder().setValue(null));
		}

		if (maxHistoryYear >= 10)
		{
			record.put("year10Iap", new SimpleMapDataHolder().setValue(persistency.getYear10Iap()));
			record.put("year10", new SimpleMapDataHolder().setValue(persistency.getYear10()));
		}
		else
		{
			record.put("year10Iap", new SimpleMapDataHolder().setValue(null));
			record.put("year10", new SimpleMapDataHolder().setValue(null));
		}

		return record;
	}
}
