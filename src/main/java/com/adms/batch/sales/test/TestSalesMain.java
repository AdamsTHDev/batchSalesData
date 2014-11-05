package com.adms.batch.sales.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.dao.SalesDataHelper;
//import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.domain.ListLot;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.batch.sales.domain.PaymentMethod;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class TestSalesMain extends AbstractImportSalesJob {

	public Sales findSaleRecord(String xRefference)
			throws Exception
	{
		return getSalesService().findSalesRecordByXRefference(xRefference);
	}

	private Sales extractSalesRecord(DataHolder salesDataHolder, Sales sales)
			throws Exception
	{
		System.out.println("extractSalesRecord " + salesDataHolder.printValues());

		String listLotText = salesDataHolder.get("listLotName").getStringValue();
		String listLotCode = SalesDataHelper.extractListLotCode(listLotText);
		String listLotName = SalesDataHelper.extractListLotName(listLotText);
		ListLot listLot = getListLotService().findListLotByListLotCode(listLotCode);
		if (listLot == null)
		{
			listLot = new ListLot();
			listLot.setListLotCode(listLotCode);
			listLot.setListLotName(listLotName);
			listLot = getListLotService().addListLot(listLot, BATCH_ID);
		}
		sales.setListLot(listLot);
		
//		String campaignCode = salesDataHolder.get("campaignCode").getStringValue();
//		String campaignName = salesDataHolder.get("campaignName").getStringValue();
		/*Campaign campaign = getCampaignService().findCampaignByCampaignCode(campaignCode);
		if (campaign == null)
		{
			campaign = new Campaign();
			campaign.setCampaignCode(campaignCode);
			campaign.setCampaignName(campaignName);
			campaign.setIsActive("Y");
			campaign = getCampaignService().addCampaign(campaign, BATCH_ID);
		}
		sales.setCampaign(campaign);*/

		String tsrCode = salesDataHolder.get("tsrCode").getStringValue();
		Tsr tsr = getTsrService().findTsrByTsrCode(tsrCode);
		if (tsr == null)
		{
			tsr = new Tsr();
			tsr.setTsrCode(tsrCode);
			tsr.setTsrPosition(getTsrPositionService().findTsrPositionByPositionCode("DMTSR"));
			tsr.setTsrStatus(getTsrStatusService().findTsrStatusByStatusCode("AD"));
			tsr.setRemark("" + sales.getxReference());
			tsr = getTsrService().addTsr(tsr, BATCH_ID);
		}
		sales.setTsr(tsr);

		String supCode = salesDataHolder.get("supCode").getStringValue();
		Tsr supervisor = getTsrService().findTsrByTsrCode(supCode);
		sales.setSupervisor(supervisor);

		sales.setSaleDate((Date) salesDataHolder.get("saleDate").getValue());
		sales.setApproveDate((Date) salesDataHolder.get("approveDate").getValue());
		sales.setItemNo(salesDataHolder.get("itemNo").getIntValue());
		sales.setCustomerFullName(salesDataHolder.get("customerFullName").getStringValue());
		sales.setPremium(salesDataHolder.get("premium").getDecimalValue());
		sales.setAnnualFyp(salesDataHolder.get("annualPremium").getDecimalValue());
		sales.setProtectAmount(salesDataHolder.get("protectAmount").getDecimalValue());

		String paymentDescription = salesDataHolder.get("paymentChannel").getStringValue();
		PaymentMethod paymentMethod = getPaymentMethodService().findPaymentMethodByDescription(paymentDescription);
		sales.setPaymentMethod(paymentMethod);
		
		String frequencyDescription = salesDataHolder.get("paymentFrequency").getStringValue();
		PaymentFrequency paymentFrequency = getPaymentFrequencyService().findPaymentFrequencyByDescription(frequencyDescription);
		sales.setPaymentFrequency(paymentFrequency);

		String qaStatusText = salesDataHolder.get("qaStatus").getStringValue();
		ReconfirmStatus qaStatus = getReconfirmStatusService().findReconfirmStatusByReconfirmStatus(qaStatusText);
		sales.setQaStatus(qaStatus);

		sales.setQaReason(salesDataHolder.get("qaReason").getStringValue());
		sales.setQaReasonDetail(salesDataHolder.get("qaReasonDetail").getStringValue());

		return sales;
	}

	private void importSalesRecord(List<DataHolder> salesDataHolderList)
			throws Exception
	{
		System.out.println("importSalesRecord");
		for (DataHolder salesDataHolder : salesDataHolderList)
		{
			Sales sales = null;
			try
			{
				String xReference = salesDataHolder.get("xRefference").getStringValue();
				sales = getSalesService().findSalesRecordByXRefference(xReference);

				boolean newSales = false;
				if (sales == null)
				{
					sales = new Sales();
					sales.setxReference(xReference);
					newSales = true;
				}

				extractSalesRecord(salesDataHolder, sales);

				if (newSales)
				{
					getSalesService().addSalesRecord(sales, "batchId");
				}
				else
				{
					getSalesService().updateSalesRecord(sales, "batchId");
				}
			}
			catch (Exception e)
			{
				System.err.println("error on: " + salesDataHolder.printValues());
				System.err.println("error on: " + sales);
				throw e;
			}
		}
	}

	private void importFile(File fileFormat, File salesRecordFile, String sheetName)
	{
		System.out.println("importFile");
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(salesRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> salesDataHolderList = fileDataHolder.get(sheetName).getDataList("salesRecord");
			
			importSalesRecord(salesDataHolderList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
		
		String fileFormat = "D:/Eclipse/Workspace/ADAMS/batch-sales-data/src/main/resources/FileFormat_SalesReportByRecords-sydney.xml";
		
		String fileInput = null;
		String sheetName = null;
//		String processDate = "";
//		String rerun = "";
		
		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/1_MTLife Kbank_HIP_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/2_MTLife Kbank_HRC_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jul-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/3_MTLife Kbank_POM_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/4_MTLife BL HIP_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/5_MTLife BL & MSIG BL POM_Jun-Aug 2014 (Sydney trip)_test.xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/6_MSIG BL_Jun-Aug 2014 (Sydney trip)_test.xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
		
		
		//TODO file 7 why had Approve Date (pending) column, resigned SUP
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/7_MSIG UOB_Jun-Aug 2014 (Sydney trip)_test.xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/8_MTI SC_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/9_MTI PA CB_Jun-Aug 2014 (Sydney trip)_test.xls";
//		sheetName = "Jun-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/10_MTI POM_Jun-Aug 2014 (Sydney trip).xls";
//		sheetName = "Jul-Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
//		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Raw data Sydney trip 2014/11_FWD_Raw Data_Aug 2014 (Sydney trip)_test.xls";
//		sheetName = "Aug";
//		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);
//		
		fileInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Sydney_trip_rawdata_Sep_2014/Rawdata_Sep 2014 (Sydney trip)/0_ALL.xls";
		sheetName = "Sep";
		new TestSalesMain().importFile(new File(fileFormat), new File(fileInput), sheetName);



	}

}
