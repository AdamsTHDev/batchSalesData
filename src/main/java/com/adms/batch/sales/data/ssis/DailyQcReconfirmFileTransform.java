package com.adms.batch.sales.data.ssis;

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

public class DailyQcReconfirmFileTransform implements DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile)
			throws Exception
	{
		InputStream fileFormat = null;
		InputStream sampleReport = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

		fileFormat = URLClassLoader.getSystemResourceAsStream(inputFileFormat);
		sampleReport = new FileInputStream(inputFile);

		ExcelFormat ex = new ExcelFormat(fileFormat);
		DataHolder fileDataHolder = ex.readExcel(sampleReport);

		List<String> sheetNames = fileDataHolder.getKeyList();
		for (String sheetName : sheetNames)
		{
			DataHolder sheetDataHolder = fileDataHolder.get(sheetName);

			List<DataHolder> dataRecordList = sheetDataHolder.getDataList("qcList");
			for (DataHolder dataRecord : dataRecordList)
			{
				DataHolder dataHolder = new SimpleMapDataHolder();

				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("saleDate").getValue() != null && dataRecord.get("saleDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("saleDate").getValue()));
					dataRecord.put("saleDate", dataHolder);
				}

				dataHolder = new SimpleMapDataHolder();
				if (dataRecord.get("qcStatusDate").getValue() != null && dataRecord.get("qcStatusDate").getValue() instanceof java.util.Date)
				{
					dataHolder.setValue(dateFormat.format(dataRecord.get("qcStatusDate").getValue()));
					dataRecord.put("qcStatusDate", dataHolder);
				}
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
