package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.UwDecision;
import com.adms.batch.sales.domain.UwResult;
import com.adms.batch.sales.domain.UwStatus;
import com.adms.batch.sales.support.FileWalker;
import com.adms.batch.sales.test.AbstractImportSalesJob;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportMtlPendingPreUw extends AbstractImportSalesJob {

	private UwResult extractUwRecord(DataHolder uwDataHolder, UwResult uwResult)
			throws Exception
	{
		log.debug("extractUwRecord " + uwDataHolder.printValues());

		uwResult.setAppSubmitDate((Date) uwDataHolder.get("appSubmitDate").getValue());
		uwResult.setItemNo(uwDataHolder.get("itemNo").getIntValue());
		uwResult.setUwSubmitDate((Date) uwDataHolder.get("uwSubmitDate").getValue());
		uwResult.setUwResultDate((Date) uwDataHolder.get("uwResultDate").getValue());

		String acceptFlag = uwDataHolder.get("acceptFlag").getStringValue();
		String declineFlag = uwDataHolder.get("declineFlag").getStringValue();
		String cancelFlag = uwDataHolder.get("cancelFlag").getStringValue();
		String uwDecisionString = null;

		if (StringUtils.isNotBlank(acceptFlag))
		{
			uwDecisionString = "Accept";
		}
		else if (StringUtils.isNotBlank(declineFlag))
		{
			uwDecisionString = "Decline";
		}
		else if (StringUtils.isNotBlank(cancelFlag))
		{
			uwDecisionString = "Cancel";
		}

		if (StringUtils.isNotBlank(uwDecisionString))
		{
			UwDecision uwDecision = getUwDecisionService().findUwDecisionByDecision(uwDecisionString);
			if (uwDecision == null)
			{
				throw new Exception("UW Decision not found for uwDecisionString [" + uwDecisionString + "]");
			}
			uwResult.setUwDecision(uwDecision);
		}

		String uwStatusString = uwDataHolder.get("uwStatus").getStringValue();
		if (StringUtils.isNotBlank(uwStatusString))
		{
			UwStatus uwStatus = getUwStatusService().findUwStatusByDescription(uwStatusString);
			if (uwStatus == null)
			{
				throw new Exception("UW Status not found for uwStatusString [" + uwStatusString + "]");
			}
			uwResult.setUwStatus(uwStatus);
		}

		uwResult.setUwRemark(uwDataHolder.get("remark").getStringValue());

		return uwResult;
	}

	private void importUw(List<DataHolder> uwDataHolderList)
			throws Exception
	{
		for (DataHolder uwDataHolder : uwDataHolderList)
		{
			String xReference = uwDataHolder.get("xRef").getStringValue();
			Sales sales = getSalesService().findSalesRecordByXRefference(xReference);

			if (sales != null)
			{
				UwResult uwResult = getUwResultService().findUwResultByxReference(xReference);

				boolean newUwResult = false;
				if (uwResult == null)
				{
					uwResult = new UwResult();
					uwResult.setxReference(sales);
					newUwResult = true;
				}
				else
				{
					log.debug("found uwResult record [" + uwResult + "]");
				}

				try
				{
					extractUwRecord(uwDataHolder, uwResult);

					if (newUwResult)
					{
						getUwResultService().addUwResult(uwResult, BATCH_ID);
					}
					else
					{
						getUwResultService().updateUwResult(uwResult, BATCH_ID);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void importFile(File fileFormat, File dataFile)
			throws Exception
	{
		log.info("importFile: " + dataFile.getAbsolutePath());
		InputStream format = null;
		InputStream input = null;
		try
		{
			format = new FileInputStream(fileFormat);
			ExcelFormat excelFormat = new ExcelFormat(format);

			input = new FileInputStream(dataFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<String> sheetNames = fileDataHolder.getKeyList();

			for (String sheetName : sheetNames)
			{
				DataHolder sheet = fileDataHolder.get(sheetName);

				List<DataHolder> uwDataHolderList = sheet.getDataList("uwRecords");

				importUw(uwDataHolderList);
			}
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
		String fileFormatLocation = /* args[0]; */"D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_MTL_Pending_Pre_UW.xml";
//		String fileDataLocation = /* args[1]; */"D:/Work/Report/DailyReport/MTL_Pending_Pre_UW/ADMS_Pending Pre UW 2014.xls";
		String rootPath = /* args[1]; */"D:/Work/Report/DailyReport/MTL_Pending_Pre_UW";

		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.contains("ADMS_Pending Pre UW");
			}
		});

		ImportMtlPendingPreUw batch = new ImportMtlPendingPreUw();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());

		for (String filename : fw.getFileList())
		{
			batch.importFile(new File(fileFormatLocation), new File(filename));
		}
		
	}
}
