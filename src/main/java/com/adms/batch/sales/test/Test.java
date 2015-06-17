package com.adms.batch.sales.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.domain.TsrHierarchy;
import com.adms.batch.sales.service.TsrHierarchyService;

public class Test {

	public static void main(String[] args) throws Exception
	{
		Date date = new SimpleDateFormat("yyyyMMdd").parse("20151231");
		System.out.println(date);
		
		
//		Calendar c1 = Calendar.getInstance(Locale.US);
//		Calendar c2 = Calendar.getInstance(Locale.US);
//		c1.setTime(date);
//		c2.setTime(date);
//		c2.add(Calendar.DATE, 1);
//		System.out.println(c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH));
		
		
		
		
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context-salesdb.xml");
//		TsrHierarchyService tsrHierarchyService = (TsrHierarchyService) applicationContext.getBean("tsrHierarchyService");
//		TsrHierarchy tsrHierarchy = tsrHierarchyService.findTsrHierarchyByTsrAndUplineAndDate("603640", "603353", date);
//		System.out.println(tsrHierarchy);
	}

}
