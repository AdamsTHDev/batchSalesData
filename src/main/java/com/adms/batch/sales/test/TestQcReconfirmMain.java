package com.adms.batch.sales.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.QcReconfirm;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class TestQcReconfirmMain extends AbstractImportSalesJob {

	public TestQcReconfirmMain(boolean enableLog)
			throws Exception
	{
		super(enableLog);
	}

	private QcReconfirm extractQcRecord(DataHolder qcDataHolder, QcReconfirm qcReconfirm)
			throws Exception
	{
		System.out.println("extractTsrRecord " + qcDataHolder.printValues());

		qcReconfirm.setQcStatusTime((Date) qcDataHolder.get("qcStatusDate").getValue());

		String qcId = qcDataHolder.get("qcId").getStringValue();
		qcReconfirm.setQcId(qcId);

		String qcStatus = qcDataHolder.get("qcStatus").getStringValue();
		ReconfirmStatus qcReconfirmStatus = getReconfirmStatusService().findReconfirmStatusByReconfirmStatus(qcStatus);
		qcReconfirm.setQcStatus(qcReconfirmStatus);

		String tsrStatus = qcDataHolder.get("tsrStatus").getStringValue();
		ReconfirmStatus tsrReconfirmStatus = getReconfirmStatusService().findReconfirmStatusByReconfirmStatus(tsrStatus);
		qcReconfirm.setTsrStatus(tsrReconfirmStatus);

		qcReconfirm.setReconfirmReason(qcDataHolder.get("reconfirmReason").getStringValue());
		qcReconfirm.setReconfirmRemark(qcDataHolder.get("reconfirmRemark").getStringValue());
		qcReconfirm.setCurrentReason(qcDataHolder.get("currentReason").getStringValue());
		qcReconfirm.setCurrentRemark(qcDataHolder.get("currentReason").getStringValue());

		return qcReconfirm;
	}

	private void importQc(List<DataHolder> qcDataHolderList)
			throws Exception
	{
		System.out.println("importQc");
		for (DataHolder qcDataHolder : qcDataHolderList)
		{
//			QcReconfirm example = new QcReconfirm();

			String fullName = qcDataHolder.get("tsrFullName").getStringValue();
			Tsr tsr = getTsrService().findTsrByFullName(fullName);
			String customerFullName = qcDataHolder.get("customerFullName").getStringValue();
			Date saleDate = (Date) qcDataHolder.get("saleDate").getValue();
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			saleDate = df.parse(df.format(saleDate));

			Sales xReference = null;
			try
			{
				xReference = getSalesService().findSalesRecordByCustomerFullNameAndTsrAndSaleDate(customerFullName, tsr, saleDate);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (xReference == null)
				{
					xReference = getSalesService().findSalesRecordByCustomerFullNameAndSaleDate(customerFullName, saleDate);
				}
			}

//			example.setxReference(xReference);
//			example.setQcStatusTime((Date) qcDataHolder.get("qcStatusDate").getValue());

			QcReconfirm qcReconfirm = null;
//			System.out.println("********** queryByExp "+example);
//			List<QcReconfirm> qcReconfirmList = getQcReconfirmService().findQcReconfirmByExample(example);
//			for (QcReconfirm r : qcReconfirmList)
//			{
//				System.out.println("**********"+r);
//			}
//			if (qcReconfirmList.size() > 0)
//			{
//				qcReconfirm = qcReconfirmList.get(0);
//			}
			
			qcReconfirm = getQcReconfirmService().findByxReferenceAndQcStatusTime(xReference.getxReference(), (Date) qcDataHolder.get("qcStatusDate").getValue());

			boolean newQc = false;
			if (qcReconfirm == null)
			{
				qcReconfirm = new QcReconfirm();
				qcReconfirm.setxReference(xReference);
				qcReconfirm.setQcStatusTime((Date) qcDataHolder.get("qcStatusDate").getValue());
				newQc = true;
			}
			else
			{
				System.out.println("found reconfirm record [" + qcReconfirm + "]");
			}

			extractQcRecord(qcDataHolder, qcReconfirm);

			if (newQc)
			{
				getQcReconfirmService().addQcReconfirm(qcReconfirm, "batchId");
			}
			else
			{
				getQcReconfirmService().updateQcReconfirm(qcReconfirm, "batchId");
			}
		}
	}

	private void importFile(File fileFormat, File qcRecordFile, String sheetName) throws Exception
	{
		System.out.println("importFile");
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(qcRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> qcDataHolderList = fileDataHolder.get(sheetName).getDataList("qcList");

			importQc(qcDataHolderList);
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
		
		String fileFormat = "D:/Eclipse/Workspace/ADAMS/batch-sales-data/src/main/resources/FileFormat_Sydney_QcReconfirm.xml";
		String pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/QA Reconfirm_Jul 14/";
		String fileInput = null;
		String sheetName = null;

		fileInput = "QA_Reconfirm_MSIG UOB Super Care_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI DDOP PA CASH BACK_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI Kbank DDOP Safety Care 31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI POM PA CASH BACK_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTL KBANK DDOP Health Return Cash_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTLife Kbank DDOP HIP_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTLife Kbank POM Cash Back_31 July 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		// Aug
		pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/QA Reconfirm_Aug 14/";
		fileInput = "QA_Reconfirm_MSIG UOB Super Care_30 Aug 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI DDOP PA CASH BACK_29 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI Kbank DDOP Safety Care 29 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTI POM PA CASH BACK_30 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTL KBANK DDOP Health Return Cash_29 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTLife Kbank DDOP HIP_29 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

		fileInput = "QA_Reconfirm_MTLife Kbank POM Cash Back_29 August 2014_test.xlsx";
		sheetName = "QC_Reconfirm";
		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

//		pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/";
//		fileInput = "test.xlsx";
//		sheetName = "Sheet1";
//		new TestQcReconfirmMain().importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);

	}

}
