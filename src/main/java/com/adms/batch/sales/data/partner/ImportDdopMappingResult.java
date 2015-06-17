package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DdopMappingResult;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportDdopMappingResult extends AbstractImportSalesJob {

	private DdopMappingResult extractRecord(DataHolder ddopDataHolder, DdopMappingResult ddopMappingResult, String mappingStatus)
			throws Exception
	{
		log.debug("extract DDOP Record " + "[" + mappingStatus + "]" + ddopDataHolder.printValues());

		ddopMappingResult.setMappingStatus(getDdopMappingStatusService().findDdopMappingStatusByStatus(mappingStatus));

		ddopMappingResult.setLast4DigitAccountNo(ddopDataHolder.get("last4DigitCardNo").getStringValue());
		ddopMappingResult.setPlanCode(ddopDataHolder.get("planCode").getStringValue());
		ddopMappingResult.setKbankCampaignCode(ddopDataHolder.get("kbankCampaignCode").getStringValue());
		ddopMappingResult.setKbankCampaignCode(ddopDataHolder.get("kbankCampaignCode").getStringValue());
		
		ddopMappingResult.setCallDate((Date) ddopDataHolder.get("callDate").getValue());
		ddopMappingResult.setBatchDate((Date) ddopDataHolder.get("batchDate").getValue());
		ddopMappingResult.setSendToBankDate((Date) ddopDataHolder.get("sendToKbankDate").getValue());
		ddopMappingResult.setApproveDate((Date) ddopDataHolder.get("approveDate").getValue());
		ddopMappingResult.setRejectDate((Date) ddopDataHolder.get("rejectDate").getValue());

		return ddopMappingResult;
	}

	private void importDdop(List<DataHolder> ddopDataHolderList, String mappingStatus)
			throws Exception
	{
		log.info("import DDOP [" + mappingStatus + "]");
		for (DataHolder ddopDataHolder : ddopDataHolderList)
		{
//			log.debug("extract DDOP Record " + "["+mappingStatus+"]" + ddopDataHolder.printValues());
			String uniqueId = ddopDataHolder.get("uniqueId").getStringValue();
			String keyCode = ddopDataHolder.get("keyCode").getStringValue();
			String xReferenceString = keyCode + uniqueId;

			Sales sales = getSalesService().findSalesRecordByXRefference(xReferenceString);
			
			if (sales != null)
			{
				Date batchDate = (Date) ddopDataHolder.get("batchDate").getValue();
				DdopMappingResult ddopMappingResult = getDdopMappingResultService().findDdopMappingResultByxRefAndBatchDate(xReferenceString, processDateDf.parse(processDateDf.format(batchDate)));

				boolean newDdopMappingResult = false;
				if (ddopMappingResult == null)
				{
					ddopMappingResult = new DdopMappingResult();
					ddopMappingResult.setxReference(sales);
					newDdopMappingResult = true;
				}
				else
				{
					log.debug("found ddopMappingResult record [" + ddopMappingResult + "]");
				}

				try
				{
					extractRecord(ddopDataHolder, ddopMappingResult, mappingStatus);

					if (newDdopMappingResult)
					{
						getDdopMappingResultService().addDdopMappingResult(ddopMappingResult, BATCH_ID);
					}
					else
					{
						getDdopMappingResultService().updateDdopMappingResult(ddopMappingResult, BATCH_ID);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				log.warn("sales record not found [xRef: " + xReferenceString + "]");
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

			List<DataHolder> approveDataHolderList = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0)).getDataList("dataApproveRecords");
			List<DataHolder> rejectDataHolderList = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0)).getDataList("dataRejectRecords");

			importDdop(approveDataHolderList, "Approve");
			importDdop(rejectDataHolderList, "Reject");
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

	public static final String CONFIG_FILE_LOCATION = "config/importSales.properties";
	public static final String LOG_FILE_NAME = "log.mtiDdopMapping.file.name";

	public static void main(String[] args)
			throws Exception
	{
		System.out.println("main");

		String fileFormatFileName = "fileformat/salesdb/FileFormat_MTI_DDOP_Mapping_Result.xml";
		String rootPath = args[0] /* "D:/Work/Report/DailyReport/MTI_DDOP_Mapping" */;

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && name.toUpperCase().contains("DDP_MAPPING") && name.toLowerCase().endsWith(".xls");
			}
		});

		ImportDdopMappingResult batch = new ImportDdopMappingResult();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		String logFileName = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME).replace("logTime", "" + new SimpleDateFormat("yyyyMMdd_hhmmssSSS").format(new Date()));
		batch.setLogFileName(logFileName);

		for (String filename : fw.getFileList())
		{
			batch.importFile(fileFormatFileName, filename);
		}
	}

}
