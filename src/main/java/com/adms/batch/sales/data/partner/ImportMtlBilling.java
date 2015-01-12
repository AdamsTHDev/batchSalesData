package com.adms.batch.sales.data.partner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.BillingResult;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.test.AbstractImportSalesJob;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportMtlBilling extends AbstractImportSalesJob {

	private BillingResult extractRecord(DataHolder dataHolder, BillingResult billingResult)
			throws Exception
	{
		log.debug("extractRecord " + dataHolder.printValues());

		billingResult.setAccountNo(dataHolder.get("accountNo").getStringValue());
		billingResult.setAccountExp(dataHolder.get("accountExp").getStringValue());
		billingResult.setPremium(dataHolder.get("premium").getDecimalValue());
		billingResult.setFirstPremium(dataHolder.get("firstPremiun").getDecimalValue());

		String paymentFrequencyString = dataHolder.get("paymentMode").getStringValue();
		if (StringUtils.isNotBlank(paymentFrequencyString))
		{
			PaymentFrequency paymentFrequency = getPaymentFrequencyService().findPaymentFrequencyByDescription(paymentFrequencyString);
			if (paymentFrequency == null)
			{
				throw new Exception("PaymentFrequency not found for paymentFrequencyDescription [" + paymentFrequencyString + "]");
			}
			billingResult.setPaymentFrequency(paymentFrequency);
		}

		billingResult.setBillingDate((Date) dataHolder.get("effDate").getValue());

		String remarkString = dataHolder.get("remark").getStringValue();
		String billingStatusString = null;
		if (StringUtils.isBlank(remarkString))
		{
			billingStatusString = "Paid";
		}
		else
		{
			billingStatusString = "Reject";
		}

		if (StringUtils.isNotBlank(billingStatusString))
		{
			BillingStatus billingStatus = getBillingStatusService().findBillingStatusByStatus(billingStatusString);
			if (billingStatus == null)
			{
				throw new Exception("BillingStatus not found for billingStatusString [" + billingStatusString + "]");
			}
			billingResult.setBillingStatus(billingStatus);
		}

		billingResult.setRemark(remarkString);

		return billingResult;
	}

	private void importDataHolderList(List<DataHolder> dataHolderList)
			throws Exception
	{
		for (DataHolder dataHolder : dataHolderList)
		{
			extractRecord(dataHolder, null);
			
			String xReference = dataHolder.get("xRef").getStringValue();
			Sales sales = getSalesService().findSalesRecordByXRefference(xReference);

			if (sales != null)
			{
				Date billingDate = (Date) dataHolder.get("effDate").getValue();
				BillingResult billingResult = getBillingResultService().findBillingResultByxRefAndBillingDate(xReference, billingDate);

				boolean newBillingResult = false;
				if (billingResult == null)
				{
					billingResult = new BillingResult();
					billingResult.setxReference(sales);
					newBillingResult = true;
				}
				else
				{
					log.debug("found billingResult record [" + billingResult + "]");
				}

				try
				{
					extractRecord(dataHolder, billingResult);

					if (newBillingResult)
					{
						getBillingResultService().addBillingResult(billingResult, BATCH_ID);
					}
					else
					{
						getBillingResultService().updateBillingResult(billingResult, BATCH_ID);
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

				List<DataHolder> dataHolderList = sheet.getDataList("dataRecords");

				importDataHolderList(dataHolderList);
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
		String fileFormatLocation = /* args[0]; */"D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_MTL_Result_1st_Billing.xml";
//		String fileDataLocation = /* args[1]; */"D:/Work/Report/DailyReport/MTL_Billing/DDOP_Plan_Post 30_12_2014 report.xls";
		String fileDataLocation = /* args[1]; */"D:/Work/Report/DailyReport/MTL_Billing/ADMS Plan Post 30_12_2014 report.xls";

		System.out.println(fileFormatLocation);
		System.out.println(fileDataLocation);

		ImportMtlBilling batch = new ImportMtlBilling();
		batch.setLogLevel(Logger.DEBUG);
		batch.importFile(new File(fileFormatLocation), new File(fileDataLocation));
		
	}
}
