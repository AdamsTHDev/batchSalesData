package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.QcReconfirm;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.test.AbstractImportSalesJob;
import com.adms.batch.sales.test.FileWalker;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportQcReconfirm extends AbstractImportSalesJob {

	private QcReconfirm extractQcRecord(DataHolder qcDataHolder, QcReconfirm qcReconfirm)
			throws Exception
	{
		log.debug("extractTsrRecord " + qcDataHolder.printValues());

		qcReconfirm.setQcStatusTime((Date) qcDataHolder.get("qcStatusDate").getValue());

		String qcId = qcDataHolder.get("qcId").getStringValue();
		qcReconfirm.setQcId(qcId);

		String qcStatus = qcDataHolder.get("qcStatus").getStringValue();
		if (StringUtils.isNotBlank(qcStatus)) {
			ReconfirmStatus qcReconfirmStatus = getReconfirmStatusService().findReconfirmStatusByReconfirmStatus(qcStatus);
			if (qcReconfirmStatus == null)
			{
				throw new Exception("QC Status not found: qcStatus[" + qcStatus + "]");
			}
			qcReconfirm.setQcStatus(qcReconfirmStatus);
		}

		String tsrStatus = qcDataHolder.get("tsrStatus").getStringValue();
		if (StringUtils.isNotBlank(tsrStatus))
		{
			ReconfirmStatus tsrReconfirmStatus = getReconfirmStatusService().findReconfirmStatusByReconfirmStatus(tsrStatus);
			if (tsrReconfirmStatus == null)
			{
				throw new Exception("TSR Status not found: tsrStatus[" + tsrStatus + "]");
			}
			qcReconfirm.setTsrStatus(tsrReconfirmStatus);
		}

		qcReconfirm.setReconfirmReason(qcDataHolder.get("reconfirmReason").getStringValue());
		qcReconfirm.setReconfirmRemark(qcDataHolder.get("reconfirmRemark").getStringValue());
		qcReconfirm.setCurrentReason(qcDataHolder.get("currentReason").getStringValue());
		qcReconfirm.setCurrentRemark(qcDataHolder.get("currentReason").getStringValue());

		return qcReconfirm;
	}

	private void importQc(List<DataHolder> qcDataHolderList)
			throws Exception
	{
		log.info("importQc");
		for (DataHolder qcDataHolder : qcDataHolderList)
		{
//			QcReconfirm example = new QcReconfirm();

			Date saleDate = (Date) qcDataHolder.get("saleDate").getValue();
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			saleDate = df.parse(df.format(saleDate));
			
			String fullName = qcDataHolder.get("tsrFullName").getStringValue();
			Tsr tsr = null;
			try
			{
				tsr = getTsrService().findTsrByFullName(fullName, saleDate);
			}
			catch (Exception e)
			{
				log.error("Error!! TSR fullName" + fullName);
				throw e;
			}
			
//			String xReferenceString = qcDataHolder.get("xReference").getStringValue();
			String customerFullName = qcDataHolder.get("customerFullName").getStringValue();

			Sales xReference = null;
			/*if (StringUtils.isNoneBlank(xReferenceString))
			{
				// try search by X-Reference
				try
				{
					xReference = getSalesService().findSalesRecordByXRefference(xReferenceString);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}*/

			if (xReference == null)
			{
				// try search by customer name
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
			}

			QcReconfirm qcReconfirm = getQcReconfirmService().findByxReferenceAndQcStatusTime(xReference.getxReference(), (Date) qcDataHolder.get("qcStatusDate").getValue());

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
				log.debug("found reconfirm record [" + qcReconfirm + "]");
			}

			try
			{
				extractQcRecord(qcDataHolder, qcReconfirm);

				if (newQc)
				{
					getQcReconfirmService().addQcReconfirm(qcReconfirm, BATCH_ID);
				}
				else
				{
					getQcReconfirmService().updateQcReconfirm(qcReconfirm, BATCH_ID);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void importFile(File fileFormat, File qcRecordFile, String sheetName) throws Exception
	{
		log.info("importFile: " + qcRecordFile.getAbsolutePath());
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
		
//		String fileFormat = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_Sydney_QcReconfirm.xml";
//		String pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/QA Reconfirm_Jul 14/";
//		String fileInput = null;
//		String sheetName = null;
//
//		fileInput = "QA_Reconfirm_MSIG UOB Super Care_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI DDOP PA CASH BACK_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI Kbank DDOP Safety Care 31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI POM PA CASH BACK_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTL KBANK DDOP Health Return Cash_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTLife Kbank DDOP HIP_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTLife Kbank POM Cash Back_31 July 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		// Aug
//		pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/QA Reconfirm_Aug 14/";
//		fileInput = "QA_Reconfirm_MSIG UOB Super Care_30 Aug 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI DDOP PA CASH BACK_29 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI Kbank DDOP Safety Care 29 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTI POM PA CASH BACK_30 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTL KBANK DDOP Health Return Cash_29 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTLife Kbank DDOP HIP_29 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		fileInput = "QA_Reconfirm_MTLife Kbank POM Cash Back_29 August 2014_test.xlsx";
//		sheetName = "QC_Reconfirm";
//		new TestQcReconfirmMain(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		pathInput = "D:/Work/Report/Sydney Trip Report/Rawdata 201410/Rawdata_Oct 2014 (Sydney trip)/";
//		fileInput = "0_qc_reconfirm_all_Oct_14.xlsx";
//		sheetName = "Sheet1";
//		new ImportQcReconfirm(false).importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);
//
//		pathInput = "D:/Work/ADAMS/Report/Sydney Trip Report/Reconfirm/QA Reconfirm/";
//		fileInput = "test.xlsx";
//		sheetName = "Sheet1";
//		new TestQcReconfirmMain().importFile(new File(fileFormat), new File(pathInput + fileInput), sheetName);


//		String rootPath = "D:/Work/Report/DailyReport/201412/TELE/MTLKBANK";
		String rootPath = "D:/Work/Report/DailyReport/201411/OTO/FWDTVD";
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.toLowerCase().contains("reconfirm") && (name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx"));
			}
		});

		ImportQcReconfirm batch = new ImportQcReconfirm();
		batch.setLogLevel(Logger.INFO);

		String dataSheetName = null;
		String fileFormatLocation = null;
		
		for (String filename : fw.getFileList())
		{
//			System.out.println("import file: " + filename);

			if (filename.contains("OTO"))
			{
				dataSheetName = "Sheet1";
				
				if (filename.contains("FWDTVD"))
				{
					int i = filename.indexOf("FWD_TVD_Endowment 15_7_") + 23;
					Date date = new SimpleDateFormat("yyyyMMdd", Locale.US).parse(filename.substring(i, i + 8));
					
					if (date.before(new SimpleDateFormat("yyyyMMdd", Locale.US).parse("20141108")))
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO-MMddyy.xml";
					}
					else
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO.xml";
					}
				}
				else if (filename.contains("MSIGBL"))
				{
					int i = filename.indexOf("MSIGBL") + 7;
					Date date = new SimpleDateFormat("ddMMyyyy", Locale.US).parse(filename.substring(i, i + 8));
					
					if (date.before(new SimpleDateFormat("yyyyMMdd", Locale.US).parse("20141112")))
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO-MMddyy.xml";
					}
					else
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO.xml";
					}
				}
				else if (filename.contains("MTLBL"))
				{
					int i = filename.indexOf("MTLBL") + 6;
					Date date = new SimpleDateFormat("ddMMyyyy", Locale.US).parse(filename.substring(i, i + 8));
					
					if (date.before(new SimpleDateFormat("yyyyMMdd", Locale.US).parse("20141111")))
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO-MMddyy.xml";
					}
					else
					{
						fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO.xml";
					}
				}
			}
			else if (filename.contains("TELE"))
			{
				dataSheetName = "QC_Reconfirm";
				fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-TELE.xml";
			}
			else
			{
				throw new Exception("file not supported [" + filename + "]");
			}

			batch.importFile(new File(fileFormatLocation), new File(filename), dataSheetName);
		}
	}

}
