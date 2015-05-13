package com.adms.batch.sales.data.ssis;

import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.support.SalesDataHelper;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;

public class DailyQcReconfirmFileTransform implements DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile)
			throws Exception
	{
		String filePath = inputFile.getAbsolutePath();
		InputStream fileFormat = null;
		InputStream sampleReport = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.US);

		fileFormat = URLClassLoader.getSystemResourceAsStream(inputFileFormat);
		sampleReport = new FileInputStream(inputFile);

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(sampleReport);

		String campaignName = null;
		List<String> sheetNames = fileDataHolder.getKeyList();
		for (String sheetName : sheetNames)
		{
			DataHolder sheetDataHolder = fileDataHolder.get(sheetName);
			
			List<DataHolder> dataHeaderList = sheetDataHolder.getDataList("dataHeader");
			Date printDate = null;
			for (DataHolder dataHeader : dataHeaderList)
			{
				printDate = (Date) dataHeader.get("printDate").getValue();
			}

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("qcList");
			for (DataHolder dataRecord : dataRecordList)
			{
				if (StringUtils.isEmpty(campaignName))
				{
					campaignName = dataRecord.get("campaignName").getStringValue();
				}
				
				if (StringUtils.isEmpty(campaignName))
				{
					campaignName = dataRecord.get("campaignName").getStringValue();
				}

				DataHolder dataHolder = null;

				dataHolder = new SimpleMapDataHolder();
//				System.out.println(dataRecord.get("saleDate").getValue().getClass());
				if (dataRecord.get("saleDate").getValue() != null && dataRecord.get("saleDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("saleDate").getValue()));
					dataRecord.put("saleDate", dataHolder);
				}
				else if (dataRecord.get("saleDate").getValue() != null && dataRecord.get("saleDate").getValue() instanceof BigDecimal)
				{
					dataHolder.setValue(SalesDataHelper.recoveryExcelDate(dataRecord.get("saleDate").getStringValue(), "dd/MM/yyyy"));
					dataRecord.put("saleDate", dataHolder);
				}

				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("qcStatusDate").getValue() != null && dataRecord.get("qcStatusDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("qcStatusDate").getValue()));
					dataRecord.put("qcStatusDate", dataHolder);
				}
				else if (dataRecord.get("qcStatusDate").getValue() != null && dataRecord.get("qcStatusDate").getValue() instanceof BigDecimal)
				{
					dataHolder.setValue(SalesDataHelper.recoveryExcelDate(dataRecord.get("qcStatusDate").getStringValue(), "dd/MM/yyyy"));
					dataRecord.put("qcStatusDate", dataHolder);
				}

				dataRecord.put("printDate", new SimpleMapDataHolder().setValue(yyyyMMdd.format(printDate)));
			}

			// summary info
			DataHolder summarySheetDataHolder = new SimpleMapDataHolder();
			fileDataHolder.put("Summary", summarySheetDataHolder);
			List<DataHolder> summaryList = new ArrayList<DataHolder>();
			summarySheetDataHolder.putDataList("summaryList", summaryList);
			List<DataHolder> dataSummaryList = sheetDataHolder.getDataList("dataSummary");
			int itemNo = 0;
			for (DataHolder dataSummary : dataSummaryList)
			{
				DataHolder summary = new SimpleMapDataHolder();
				DataHolder dataHolder = null;

				summary.put("campaignName", new SimpleMapDataHolder().setValue(campaignName));
				summary.put("printDate", new SimpleMapDataHolder().setValue(yyyyMMdd.format(printDate)));
				summary.put("itemNo", new SimpleMapDataHolder().setValue(new Integer(++itemNo)));

				summary.put("qcStatus", dataSummary.get("qcStatus"));
				summary.put("qcStatusCount", dataSummary.get("qcStatusCount"));

				dataHolder = new SimpleMapDataHolder();
				dataHolder.setValue(filePath);
				summary.put("filePath", dataHolder);

				summaryList.add(summary);
			}
		}

		String baseSheetName = fileDataHolder.getKeyList().get(0);
		if (!baseSheetName.equals("QC_Reconfirm"))
		{
			fileDataHolder.put("QC_Reconfirm", fileDataHolder.get(fileDataHolder.getKeyList().get(0)));
			fileDataHolder.setSheetNameByIndex(0, "QC_Reconfirm");
			fileDataHolder.remove(baseSheetName);
		}

		fileFormat.close();
		sampleReport.close();

		OutputStream sampleOutput = new FileOutputStream(outputFile);
		new ExcelFormat(URLClassLoader.getSystemResourceAsStream(outputFileFormat)).writeExcel(sampleOutput, fileDataHolder);
		sampleOutput.close();
	}

	public static void main(String[] ss) throws Exception
	{
		new DailyQcReconfirmFileTransform().transform("", new File("D:/Work/ADAMS/Report/DailyReport/201410/TELE/MTLKBANK/HIP_DDOP_30102014/QC_Reconfirm.xls"), "", new File("D:/testOutput.xlsx"));
	}
}
