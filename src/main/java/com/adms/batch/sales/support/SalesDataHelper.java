package com.adms.batch.sales.support;

import org.apache.commons.lang3.StringUtils;

public class SalesDataHelper {

	public static String extractListLotCode(String listLotFromSalesRecord)
	{
		String temp = listLotFromSalesRecord.trim();
		int a = temp.lastIndexOf("(") + 1;
		int b = temp.lastIndexOf(")");
		return StringUtils.isBlank(temp) ? null : temp.substring(a, b).trim();
	}

	public static String extractListLotName(String listLotFromSalesRecord)
	{
		String temp = listLotFromSalesRecord.trim();
		int a = temp.lastIndexOf("(");
		return StringUtils.isBlank(temp) ? null : temp.substring(0, a).trim();
	}

	public static void main(String ss[])
	{
		System.out.println(SalesDataHelper.extractListLotCode("  Happy Life - Lot 4 (May. 14)  (AGO14)  "));
		System.out.println(SalesDataHelper.extractListLotName("  Happy Life - Lot 4 (May. 14)  (AGO14)  "));
		System.out.println(SalesDataHelper.extractListLotCode("  Happy Life - Lot 4 (May. 14)  ( AGO14  )  "));
		System.out.println(SalesDataHelper.extractListLotName("  Happy Life - Lot 4 (May. 14)  ( AGO14  )  "));
	}

}
