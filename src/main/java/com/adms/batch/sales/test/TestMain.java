package com.adms.batch.sales.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.dao.TestDao;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.TestDomain;
import com.adms.batch.sales.service.TestService;

public class TestMain {
	
	public Sales findSaleRecord()
	{
		return null;
	}

	public static void main(String[] args) throws Exception
	{
		String inputFile = "";
		String processDate = "";
		String rerun = "";
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		
		TestService testService = (TestService) applicationContext.getBean("testService");
		
		TestDomain testDomain = new TestDomain();
		testDomain.setId(1l);
		testDomain.setName("didi didi");
		testService.addTestDomain(testDomain, "didi");
//		testService.deleteTestDomain(testDomain);
		
	}

}
