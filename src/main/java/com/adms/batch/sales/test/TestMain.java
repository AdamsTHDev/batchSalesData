package com.adms.batch.sales.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;


public class TestMain extends AbstractImportSalesJob {

	public static void main(String[] args) throws Exception
	{
		TestMain main = new TestMain();
		Date saleDate = null;
		Calendar c = Calendar.getInstance(Locale.US);
		c.set(Calendar.YEAR, 2014);
		c.set(Calendar.MONTH, Calendar.OCTOBER);
		c.set(Calendar.DATE, 17);
		saleDate = new SimpleDateFormat("yyyyMMdd", Locale.US).parse(new SimpleDateFormat("yyyyMMdd", Locale.US).format(c.getTime()));
		System.out.println(saleDate);
		Tsr tsr = main.getTsrService().findTsrByFullName("น.ส. พัณณ์ชิตา ธนวัฒน์กุลกานต์", saleDate);
		System.out.println(tsr.getTsrCode());
		Sales sale = main.getSalesService().findSalesRecordByCustomerFullNameAndTsrAndSaleDate("นางกัณหา จันทวาศ", tsr, saleDate);
		System.out.println(sale);
		
	}

}
