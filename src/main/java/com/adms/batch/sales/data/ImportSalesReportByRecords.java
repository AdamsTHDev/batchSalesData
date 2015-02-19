package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Date;
//import java.util.Locale;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.ListLot;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.batch.sales.domain.PaymentMethod;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.SalesProcess;
import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.support.FileWalker;
import com.adms.batch.sales.support.SalesDataHelper;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.StringUtil;

public class ImportSalesReportByRecords extends AbstractImportSalesJob {

	private String fileFormatLocation;

	public String getFileFormatLocation()
	{
		return fileFormatLocation;
	}

	public void setFileFormatLocation(String fileFormatLocation)
	{
		this.fileFormatLocation = fileFormatLocation;
	}

	protected void addSalesProcess(Sales sales)
			throws Exception
	{
		Date statusDate = sales.getApproveDate() == null ? sales.getSaleDate() : sales.getApproveDate();
		SalesProcess salesProcess = getSalesProcessService().findSalesProcessByXRefferenceAndStatusDateAndReconfirmStatus(sales.getxReference(), statusDate, sales.getQaStatus());

		boolean newSalesProcess = false;
		if (salesProcess == null)
		{
			salesProcess = new SalesProcess();
			newSalesProcess = true;
		}

		salesProcess.setFileImport(null);
		salesProcess.setBatchDate(getProcessDate());
		salesProcess.setxReference(sales);
		salesProcess.setStatusDate(statusDate);
		salesProcess.setReconfirmStatus(sales.getQaStatus());

		if (newSalesProcess)
		{
			getSalesProcessService().addSalesProcess(salesProcess, BATCH_ID);
		}
		else
		{
			getSalesProcessService().updateSalesProcess(salesProcess, BATCH_ID);
		}
	}

	protected Sales extractSalesRecord(DataHolder salesDataHolder, Sales sales)
			throws Exception
	{
		log.debug("extractSalesRecord: " + salesDataHolder.printValues());

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

		Tsr tsr = null;
		String tsrCode = salesDataHolder.get("tsrCode").getStringValue();
		if (StringUtils.isNotBlank(tsrCode))
		{
			tsr = getTsrService().findTsrByTsrCode(tsrCode);
		}
		if (tsr == null)
		{
			String tsrName = salesDataHolder.get("tsrName").getStringValue();
			tsr = getTsrService().findTsrByFullName(tsrName, (Date) salesDataHolder.get("saleDate").getValue());
		}
		
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
		sales.setCustomerFullName(StringUtil.removeDoubleSpace(salesDataHolder.get("customerFullName").getStringValue()));
		sales.setProduct(salesDataHolder.get("product").getStringValue());
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

	protected void importSalesRecord(List<DataHolder> salesDataHolderList)
			throws Exception
	{
		log.debug("importSalesRecord...");

		if (getProcessDate() == null)
		{
			throw new Exception("processDate cannot be null");
		}

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
					sales = getSalesService().addSalesRecord(sales, BATCH_ID);
				}
				else
				{
					sales = getSalesService().updateSalesRecord(sales, BATCH_ID);
				}

				addSalesProcess(sales);
			}
			catch (Exception e)
			{
				System.err.println("error on: " + salesDataHolder.printValues());
				System.err.println("error on: " + sales);
				e.printStackTrace();
			}
		}
	}

	protected void importFile(File fileFormat, File salesRecordFile/*, String sheetName*/)
			throws FileNotFoundException
	{
		log.info("importFile: " + salesRecordFile.getAbsolutePath());
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(salesRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> salesDataHolderList = fileDataHolder.get(/*sheetName*/ fileDataHolder.getSheetNameByIndex(0)).getDataList("salesRecord");

			importSalesRecord(salesDataHolderList);
		}
		catch (FileNotFoundException e)
		{
			throw e;
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

		String rootPath = "D:/Work/Report/DailyReport/201502/OTO/MSIGBL";
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && (name.contains("Sales_Report_By_Records") || (name.contains("SalesReportByRecords_") && name.contains(".xlsx")) || name.contains("SalesReportByRecords.xlsx"));
			}
		});

		ImportSalesReportByRecords batch = new ImportSalesReportByRecords();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		for (String filename : fw.getFileList())
		{
			if (filename.contains("MSIGUOB"))
			{
//				batch.setDataSheetName("Sales_Report_By_Records_Pending");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-MSIGUOB.xml");
			}
			else if (filename.contains("OTO"))
			{
//				batch.setDataSheetName("Sales_Report_By_Records");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-OTO.xml");
			}
			else if (filename.contains("TELE") && !filename.contains("MSIGUOB"))
			{
//				batch.setDataSheetName("Sales_Report_By_Records");
				batch.setFileFormatLocation("D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_DailySalesReportByRecords-input-TELE.xml");
			}
			else
			{
				throw new Exception("file not supported");
			}

			batch.importFile(new File(batch.getFileFormatLocation()), new File(filename)/*, batch.getDataSheetName()*/);
		}
	}
}
