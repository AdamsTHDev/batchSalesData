package com.adms.batch.sales.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.service.CallCenterService;
import com.adms.batch.sales.service.CampaignService;
import com.adms.batch.sales.service.IncentiveCriteriaService;
import com.adms.batch.sales.service.IncentiveInfoService;
import com.adms.batch.sales.service.InsurerService;
import com.adms.batch.sales.service.ListLotService;
import com.adms.batch.sales.service.ListSourceService;
import com.adms.batch.sales.service.PaymentFrequencyService;
import com.adms.batch.sales.service.PaymentMethodService;
import com.adms.batch.sales.service.QcReconfirmService;
import com.adms.batch.sales.service.ReconfirmStatusService;
import com.adms.batch.sales.service.SalesService;
import com.adms.batch.sales.service.TsrPositionService;
import com.adms.batch.sales.service.TsrService;
import com.adms.batch.sales.service.TsrStatusService;

public class AbstractImportSalesJob {

	private ApplicationContext applicationContext;
	private DateFormat logDf;
	private boolean enableLog = false;
	public static final String BATCH_ID = "BATCH_ID";
	public static final String LOG_TIME_FORMAT = "yyyyMMdd HH:mm:ss:sss";
	public static final Locale LOG_TIME_LOCALE = Locale.US;

	public void setEnableLog(boolean enableLog)
	{
		this.enableLog = enableLog;
	}

	protected void log(String message)
	{
		if (this.enableLog)
		{
			if (logDf == null)
			{
				this.logDf = new SimpleDateFormat(LOG_TIME_FORMAT, LOG_TIME_LOCALE);
			}

			System.out.println(this.logDf.format(new Date()) + " " + message);
		}
	}

	protected Object getBean(String beanId)
	{
		if (this.applicationContext == null)
		{
			this.applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		}

		return this.applicationContext.getBean(beanId);
	}

	protected TsrService getTsrService()
	{
		return (TsrService) getBean("tsrService");
	}

	protected TsrPositionService getTsrPositionService()
	{
		return (TsrPositionService) getBean("tsrPositionService");
	}

	protected TsrStatusService getTsrStatusService()
	{
		return (TsrStatusService) getBean("tsrStatusService");
	}

	protected SalesService getSalesService()
	{
		return (SalesService) getBean("salesService");
	}

	protected CallCenterService getCallCenterService()
	{
		return (CallCenterService) getBean("callCenterService");
	}

	protected InsurerService getInsurerService()
	{
		return (InsurerService) getBean("insurerService");
	}

	protected ListSourceService getListSourceService()
	{
		return (ListSourceService) getBean("listSourceService");
	}

	protected CampaignService getCampaignService()
	{
		return (CampaignService) getBean("campaignService");
	}

	protected ListLotService getListLotService()
	{
		return (ListLotService) getBean("listLotService");
	}

	protected PaymentMethodService getPaymentMethodService()
	{
		return (PaymentMethodService) getBean("paymentMethodService");
	}

	protected PaymentFrequencyService getPaymentFrequencyService()
	{
		return (PaymentFrequencyService) getBean("paymentFrequencyService");
	}

	protected ReconfirmStatusService getReconfirmStatusService()
	{
		return (ReconfirmStatusService) getBean("reconfirmStatusService");
	}

	protected QcReconfirmService getQcReconfirmService()
	{
		return (QcReconfirmService) getBean("qcReconfirmService");
	}

	protected IncentiveInfoService getIncentiveInfoService()
	{
		return (IncentiveInfoService) getBean("incentiveInfoService");
	}

	protected IncentiveCriteriaService getIncentiveCriteriaService()
	{
		return (IncentiveCriteriaService) getBean("incentiveCriteriaService");
	}

}
