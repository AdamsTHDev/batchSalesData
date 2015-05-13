package com.adms.batch.persistency.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.persistency.service.OutputPersistencyService;
import com.adms.utils.Logger;

public class AbstractPersistencyJob {

	private ApplicationContext applicationContext;
	public static final String BATCH_ID = "BATCH_ID";
	
	public static final String PROCESS_DATE_FORMAT = "yyyyMMdd";
	public static final Locale PROCESS_DATE_LOCALE = Locale.US;
	protected DateFormat processDateDf = new SimpleDateFormat(PROCESS_DATE_FORMAT, PROCESS_DATE_LOCALE);

	protected Logger log = Logger.getLogger(Logger.DEBUG);

	protected void setLogLevel(int logLevel)
	{
		this.log.setLogLevel(logLevel);
	}

	protected Object getBean(String beanId)
	{
		if (this.applicationContext == null)
		{
			this.applicationContext = new ClassPathXmlApplicationContext("/application-context-pmrs.xml");
		}

		return this.applicationContext.getBean(beanId);
	}

	protected OutputPersistencyService getOutputPersistencyService()
	{
		return (OutputPersistencyService) getBean("outputPersistencyService");
	}

}
