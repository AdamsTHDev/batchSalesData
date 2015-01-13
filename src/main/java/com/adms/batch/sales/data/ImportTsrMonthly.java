package com.adms.batch.sales.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.domain.TsrPosition;
import com.adms.batch.sales.domain.TsrStatus;
import com.adms.batch.sales.test.AbstractImportSalesJob;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.utils.Logger;

public class ImportTsrMonthly extends AbstractImportSalesJob {

	private Tsr extractTsrRecord(DataHolder tsrDataHolder, Tsr tsr)
			throws Exception
	{
		log.debug("extractTsrRecord " + tsrDataHolder.printValues());
		
		String positionName = tsrDataHolder.get("positionName").getStringValue();
		TsrPosition tsrPosition = getTsrPositionService().findTsrPositionByPositionName(positionName);
		tsr.setTsrPosition(tsrPosition);
		
		String statusCode = tsrDataHolder.get("status").getStringValue();
		TsrStatus tsrStatus = getTsrStatusService().findTsrStatusByStatusCode(statusCode);
		tsr.setTsrStatus(tsrStatus);

//		tsr.setEmployeeCode(tsrDataHolder.get("employeeCode").getStringValue());
		
		String firstName = tsrDataHolder.get("firstName").getStringValue().trim();
		String lastName = tsrDataHolder.get("lastName").getStringValue().trim();
		tsr.setFullName(firstName + " " + lastName);
		tsr.setFirstName(firstName);
		tsr.setLastName(lastName);
		
		tsr.setEffectiveDate((Date) tsrDataHolder.get("effectiveDate").getValue());
		tsr.setResignDate((Date) tsrDataHolder.get("resignDate").getValue());
//		tsr.setRemark(tsrDataHolder.get("remark").getStringValue());

		return tsr;
	}

	private void importTsr(List<DataHolder> tsrDataHolderList)
			throws Exception
	{
		log.info("importTsr");
		for (DataHolder tsrDataHolder : tsrDataHolderList)
		{
			String tsrCode = tsrDataHolder.get("tsrCode").getStringValue();
			Tsr tsr = getTsrService().findTsrByTsrCode(tsrCode);

			boolean newTsr = false;
			if (tsr == null)
			{
				tsr = new Tsr();
				tsr.setTsrCode(tsrCode);
				newTsr = true;
			}

			extractTsrRecord(tsrDataHolder, tsr);

			if (newTsr)
			{
				getTsrService().addTsr(tsr, "batchId");
			}
			else
			{
				getTsrService().updateTsr(tsr, "batchId");
			}
		}
	}

	private void importFile(File fileFormat, File tsrRecordFile, String sheetName)
	{
		log.info("importFile: " + tsrRecordFile.getAbsolutePath());
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(tsrRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> tsrDataHolderList = fileDataHolder.get(sheetName).getDataList("tsrList");
			
			importTsr(tsrDataHolderList);
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
		String fileFormat = "D:/Eclipse/Workspace/ADAMS/batchSalesData/src/main/resources/FileFormat_TSR_Update_Monthly.xml";
//		String fileInput = "D:/Work/ADAMS/Report/TSR Update/Update New Staff by month for comm_September 14_no_pw.xlsx";
//		String fileInput = "D:/Work/ADAMS/Report/TSR Update/Update New Staff by month for comm_October 14_no_pw.xlsx";
//		String fileInput = "D:/Work/ADAMS/Report/TSR Update/test.xlsx";
//		String fileInput = "D:/Work/Report/TSR Update/Employees_OCT_for_batch.xlsx";
		String fileInput = "D:/Work/Report/TSR Update/Employees_Nov_for_batch.xlsx";
		String sheetName = "Data";
//		String processDate = "";
//		String rerun = "";
//
		System.out.println("main");
		ImportTsrMonthly batch = new ImportTsrMonthly();
		batch.setLogLevel(Logger.INFO);
		batch.importFile(new File(fileFormat), new File(fileInput), sheetName);
	}

}