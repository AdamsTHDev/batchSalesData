package com.adms.batch.sales.data;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestOpenExcel {

	public static void main(String[] args) throws InvalidFormatException, IOException
	{
		// TODO Auto-generated method stub

		String path = "D:/Work/ADAMS/Report/DailyReport/August/02082014/Health Return Cash_02082014/";
		path = "D:/Work/ADAMS/Report/DailyReport/20140918/18092014_MTLife Hip Broker/";
		Workbook wb = WorkbookFactory.create(new File(path + "TsrTrackingReport_MTL_BL_20140918.xlsx"));
	}

}
