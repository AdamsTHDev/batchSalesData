package com.adms.batch.sales.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class SalesDataHelper {

	public static String extractListLotCode(String listLotFromSalesRecord)
	{
		String temp = listLotFromSalesRecord.trim();
		int a = temp.lastIndexOf("(") + 1;
		int b = temp.lastIndexOf(")");
		return StringUtils.isBlank(temp) ? null : temp.substring(a, b).trim();
	}

	public static String extractListLotName(String listLotFromSalesRecord) throws Exception
	{
		try
		{
			String temp = listLotFromSalesRecord.trim();
			int a = temp.lastIndexOf("(");
			return StringUtils.isBlank(temp) ? null : temp.substring(0, a).trim();
		}
		catch (Exception e)
		{
			System.err.println(listLotFromSalesRecord);
			throw e;
		}
	}
	
	public static String recoveryExcelDate(String txt, String pattern)
	{
		if (StringUtils.isNotBlank(txt))
		{
			if (txt.contains("/"))
			{
				return txt;
			}

			try
			{
				Double d = Double.valueOf(txt);
				return recoveryExcelDate(d, pattern);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static String recoveryExcelDate(Double dateExcelNumber, String pattern)
	{
		if (dateExcelNumber != null)
		{
			try
			{
				Double df = Math.floor(dateExcelNumber);
				int i = df.intValue();
				Date date = new SimpleDateFormat("yyyyMMdd", Locale.US).parse("19000101");
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, i - 2);
				return new SimpleDateFormat(pattern, Locale.US).format(c.getTime());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static void main(String ss[]) throws Exception
	{
		System.out.println(SalesDataHelper.extractListLotCode("  Happy Life - Lot 4 (May. 14)  (AGO14)  "));
		System.out.println(SalesDataHelper.extractListLotName("  Happy Life - Lot 4 (May. 14)  (AGO14)  "));
		System.out.println(SalesDataHelper.extractListLotCode("  Happy Life - Lot 4 (May. 14)  ( AGO14  )  "));
		System.out.println(SalesDataHelper.extractListLotName("  Happy Life - Lot 4 (May. 14)  ( AGO14  )  "));
		
		System.out.println(SalesDataHelper.recoveryExcelDate("42013.6271527778", "yyyyMMdd"));
		System.out.println(SalesDataHelper.recoveryExcelDate("42012.8118981481", "dd/MM/yyyy"));
		System.out.println(SalesDataHelper.recoveryExcelDate("42013.7188194444", "dd MMMM yy"));
	}

}
