package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.UwResult;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportCof extends AbstractImportSalesJob {

	private UwResult extractCofRecord(DataHolder cofDataHolder, UwResult uwResult)
			throws Exception
	{
		log.debug("extractCofRecord " + cofDataHolder.printValues());

		String cofStatus = cofDataHolder.get("cofStatus").getStringValue();
		if (StringUtils.isNotBlank(cofStatus))
		{
			uwResult.setCofStatus(getCofStatusService().findCofStatusByDescription(cofStatus));
		}
		uwResult.setCofIssueDate((Date) cofDataHolder.get("cofIssueDate").getValue());
		uwResult.setCofDueDate((Date) cofDataHolder.get("cofDueDate").getValue());
		uwResult.setCofDate((Date) cofDataHolder.get("cofCallDate").getValue());

		return uwResult;
	}

	private void importCof(List<DataHolder> cofDataHolderList)
			throws Exception
	{
		log.info("importCOF");
		for (DataHolder cofDataHolder : cofDataHolderList)
		{
			String xReferenceString = cofDataHolder.get("xReference").getStringValue();

			UwResult uwResult = getUwResultService().findUwResultByxReference(xReferenceString);
			log.debug("findUwResultByxReference: " + xReferenceString + ", uwResult: " + uwResult);

			if (uwResult != null)
			{
				try
				{
					extractCofRecord(cofDataHolder, uwResult);

					getUwResultService().updateUwResult(uwResult, BATCH_ID);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				log.warn("UW Result not found [xReference: " + xReferenceString + "]");
			}
		}
	}

	private void importFile(String fileFormatFileName, String dataFileLocation)
			throws Exception
	{
		log.info("importFile: " + dataFileLocation);
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = URLClassLoader.getSystemResourceAsStream(fileFormatFileName);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFileLocation);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> cofDataHolderList = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0)).getDataList("dataRecords");

			importCof(cofDataHolderList);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				format.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public static void main(String[] args)
			throws Exception
	{
		System.out.println("main");

		String fileFormatFileName = "fileformat/salesdb/FileFormat_QC_COF.xml";
		String rootPath = args[0] /* "D:/Work/Report/DailyReport/QA_COF" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && dir.getAbsolutePath().contains("2015") && name.toUpperCase().contains("ADMS ") && name.toLowerCase().endsWith(".xls");
			}
		});

		ImportCof batch = new ImportCof();
		batch.setLogLevel(Logger.INFO);

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatFileName, filename);
		}
	}

}
