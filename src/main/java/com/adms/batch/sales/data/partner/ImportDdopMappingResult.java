package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.DdopMappingResult;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.support.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

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
		}
	}

	private void importFile(File fileFormat, File qcRecordFile) throws Exception
	{
		log.info("importFile: " + qcRecordFile.getAbsolutePath());
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(qcRecordFile);
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

		String rootPath = "D:/Work/Report/DailyReport/MTI_DDOP_Mapping";
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && name.toUpperCase().contains("DDP_MAPPING") && name.toLowerCase().endsWith(".xls");
			}
		});

		ImportDdopMappingResult batch = new ImportDdopMappingResult();
		batch.setLogLevel(Logger.DEBUG);

		String fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_MTI_DDOP_Mapping_Result.xml";

		for (String filename : fw.getFileList())
		{
			batch.importFile(new File(fileFormatLocation), new File(filename));
		}
	}

}
