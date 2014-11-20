package com.adms.batch.sales.test;

import java.math.BigDecimal;

import com.adms.batch.sales.domain.Sales;

public class TestMain {
	
	public Sales findSaleRecord()
	{
		return null;
	}

	public static void test(Integer i) {
		i++;
	}
	
	public static void test2(Inner in)
	{
		in.i++;
	}
	public static void test3(BigDecimal dec)
	{
		dec = dec.add(new BigDecimal(1));
	}

	public static void main(String[] args) throws Exception
	{
		
		Integer i = 0;
		test(i);
		test(i);
		System.out.println(i);
		
		Inner in = new TestMain().new Inner();
		test2(in);
		test2(in);
		test2(in);
		System.out.println(in.i);
		
		BigDecimal dec = new BigDecimal(0);
		test3(dec);
		test3(dec);
		test3(dec);
		test3(dec);
		System.out.println(dec);
		
//		String inputFile = "";
//		String processDate = "";
//		String rerun = "";
//		
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
//		
//		TestService testService = (TestService) applicationContext.getBean("testService");
//		
//		TestDomain testDomain = new TestDomain();
//		testDomain.setId(1l);
//		testDomain.setName("didi didi");
//		testService.addTestDomain(testDomain, "didi");
//		testService.deleteTestDomain(testDomain);
		
	}
	
	class Inner {
		Integer i = 0;
		
	}

}
