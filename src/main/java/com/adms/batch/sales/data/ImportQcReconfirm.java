package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.QcReconfirm;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.support.FileWalker;
import com.adms.batch.sales.support.SalesDataHelper;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class ImportQcReconfirm extends AbstractImportSalesJob {

	private QcReconfirm extractQcRecord(DataHolder qcDataHolder, QcReconfirm qcReconfirm)
			throws Exception
	{
		log.debug("extractTsrRecord " + qcDataHolder.printValues());

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
		log.info("importQc: " + qcDataHolderList.size() + " records found.");
		for (DataHolder qcDataHolder : qcDataHolderList)
		{
			log.debug("import QC: " + qcDataHolder.printValues());

			Date saleDate = null;
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
			if (qcDataHolder.get("saleDate").getValue() instanceof BigDecimal)
			{
				String s = SalesDataHelper.recoveryExcelDate(qcDataHolder.get("saleDate").getDecimalValue().doubleValue(), "ddMMyyyy");
				saleDate = df.parse(s);
			}
			else if (qcDataHolder.get("saleDate").getValue() instanceof Date)
			{
				saleDate = (Date) qcDataHolder.get("saleDate").getValue();
				saleDate = df.parse(df.format(saleDate));
			}
			else if (qcDataHolder.get("saleDate").getValue() instanceof String)
			{
				saleDate = df2.parse(qcDataHolder.get("saleDate").getStringValue());
			}
			else
			{
				log.error(qcDataHolder.get("saleDate").getValue());
				throw new Exception();
			}

			String tsrCode = qcDataHolder.get("tsrCode") == null ? null : qcDataHolder.get("tsrCode").getStringValue();
			String fullName = qcDataHolder.get("tsrFullName").getStringValue();
			Tsr tsr = null;
			try
			{
				if (StringUtils.isNotBlank(tsrCode))
				{
					tsr = getTsrService().findTsrByTsrCode(tsrCode);
				}

				if (tsr == null)
				{
					tsr = getTsrService().findTsrByFullName(fullName, saleDate);
				}
			}
			catch (Exception e)
			{
				log.error("Error!! TSR fullName" + fullName);
				throw e;
			}

			String xReferenceString = qcDataHolder.get("xReference") != null ? qcDataHolder.get("xReference").getStringValue() : null;
			String customerFullName = qcDataHolder.get("customerFullName").getStringValue();

			Sales xReference = null;
			//disable find by xRef
			if (StringUtils.isNotBlank(xReferenceString))
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
			}

			if (xReference == null)
			{
				// try search by customer name
				try
				{
					xReference = getSalesService().findSalesRecordByCustomerFullNameAndTsrAndSaleDate(customerFullName, tsr, saleDate);
				}
				catch (Exception e)
				{
					log.error("customerFullName: " + customerFullName + ", tsr: " + tsr + ", saleDate: " + saleDate);
					e.printStackTrace();
					if (xReference == null)
					{
						xReference = getSalesService().findSalesRecordByCustomerFullNameAndSaleDate(customerFullName, saleDate);
					}
				}
			}
			
			if (xReference == null)
			{
				throw new Exception("not found SalesRecord for QC Reconfirm: " + qcDataHolder.printValues());
			}

			QcReconfirm qcReconfirm = null;
			Date qcStatusDate = null;
			if (qcDataHolder.get("qcStatusDate").getValue() instanceof BigDecimal)
			{
				String s = SalesDataHelper.recoveryExcelDate(qcDataHolder.get("qcStatusDate").getDecimalValue().doubleValue(), "ddMMyyyy");
				qcStatusDate = df.parse(s);
			}
			else if (qcDataHolder.get("qcStatusDate").getValue() instanceof Date)
			{
				qcStatusDate = (Date) qcDataHolder.get("qcStatusDate").getValue();
				qcStatusDate = df.parse(df.format(qcStatusDate));
			}
			else if (qcDataHolder.get("qcStatusDate").getValue() instanceof String)
			{
				qcStatusDate = df2.parse(qcDataHolder.get("qcStatusDate").getStringValue());
			}
			else
			{
				log.error(qcDataHolder.get("qcStatusDate").getValue());
				throw new Exception();
			}
			
			Date recoveryQcStatusDate = null;
			try
			{
				qcReconfirm = getQcReconfirmService().findByxReferenceAndQcStatusTime(xReference.getxReference(), qcStatusDate);
			}
			catch (Exception e)
			{
				log.warn("xReference.getxReference(): " + xReference.getxReference());
				log.warn("qcStatusDate: " + qcStatusDate);

				try
				{
					String qcStatusDateText = qcDataHolder.get("qcStatusDateText").getStringValue();
					log.warn("recovery qcStatusDateText: " + qcStatusDateText);
					
					recoveryQcStatusDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss a", Locale.US).parse(qcStatusDateText);
					log.warn("recoveryQcStatusDate: " + recoveryQcStatusDate);
					
					qcReconfirm = getQcReconfirmService().findByxReferenceAndQcStatusTime(xReference.getxReference(), recoveryQcStatusDate);
				}
				catch (Exception e2)
				{
					throw e2;
				}
			}

			boolean newQc = false;
			if (qcReconfirm == null)
			{
				qcReconfirm = new QcReconfirm();
				qcReconfirm.setxReference(xReference);
				qcReconfirm.setQcStatusTime(recoveryQcStatusDate != null ? recoveryQcStatusDate : qcStatusDate);
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

	private void importFile(File fileFormat, File qcRecordFile) throws Exception
	{
		log.info("importFile: " + qcRecordFile.getAbsolutePath());
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(qcRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> qcDataHolderList = fileDataHolder.get(fileDataHolder.getSheetNameByIndex(0)).getDataList("qcList");

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

	public static final String CONFIG_FILE_LOCATION = "config/importSales.properties";
	public static final String LOG_FILE_NAME = "log.qcRecords.file.name";

	public static void main(String[] args)
			throws Exception
	{
		System.out.println("main");

		String rootPath = args[0]/*"D:/Work/Report/DailyReport/201502/TELE/MTLKBANK"*/;
		
		FileWalker fw = new FileWalker();
		fw.walk(rootPath, new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return !name.contains("~$") && !dir.getAbsolutePath().toLowerCase().contains("archive") && ((name.toLowerCase().contains("reconfirm") || (name.toUpperCase().contains("_QCRECO_") && name.toUpperCase().contains("_CTD_ALL"))) && (name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx")));
			}
		});

		ImportQcReconfirm batch = new ImportQcReconfirm();
		batch.setLogLevel(Logger.INFO);
		batch.setProcessDate(new Date());
		batch.setLogFileName(PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(LOG_FILE_NAME));

		String fileFormatLocation = null;
		
		for (String filename : fw.getFileList())
		{
			if (filename.contains("OTO"))
			{
				/*if (filename.contains("FWDTVD"))
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
				}*/
				fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-OTO.xml";
			}
			else if (filename.contains("TELE"))
			{
				/*if (filename.contains("MSIGUOB"))
				{
					fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-MSIGUOB.xml";
				}
				else
				{
					fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-TELE.xml";
				}*/
				fileFormatLocation = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_SSIS_QcReconfirm-input-TELE.xml";
			}
			else
			{
				throw new Exception("file not supported [" + filename + "]");
			}

			batch.importFile(new File(fileFormatLocation), new File(filename));
		}
	}

}
