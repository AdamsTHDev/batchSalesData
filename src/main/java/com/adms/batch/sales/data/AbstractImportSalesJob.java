package com.adms.batch.sales.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adms.batch.sales.service.BillingResultService;
import com.adms.batch.sales.service.BillingStatusService;
import com.adms.batch.sales.service.CallCenterService;
import com.adms.batch.sales.service.CampaignService;
import com.adms.batch.sales.service.CofStatusService;
import com.adms.batch.sales.service.DdopMappingResultService;
import com.adms.batch.sales.service.DdopMappingStatusService;
import com.adms.batch.sales.service.EocCallOutcomeService;
import com.adms.batch.sales.service.IncentiveCompositeService;
import com.adms.batch.sales.service.IncentiveCriteriaService;
import com.adms.batch.sales.service.IncentiveInfoService;
import com.adms.batch.sales.service.InsurerService;
import com.adms.batch.sales.service.ListLotService;
import com.adms.batch.sales.service.ListSourceService;
import com.adms.batch.sales.service.PaymentFrequencyService;
import com.adms.batch.sales.service.PaymentMethodService;
import com.adms.batch.sales.service.QcReconfirmService;
import com.adms.batch.sales.service.ReconfirmStatusService;
import com.adms.batch.sales.service.SalesProcessService;
import com.adms.batch.sales.service.SalesService;
import com.adms.batch.sales.service.TsrPositionService;
import com.adms.batch.sales.service.TsrService;
import com.adms.batch.sales.service.TsrStatusService;
import com.adms.batch.sales.service.UwDecisionService;
import com.adms.batch.sales.service.UwResultService;
import com.adms.batch.sales.service.UwStatusService;
import com.adms.batch.sales.service.VSalesCommService;
import com.adms.utils.Logger;

public class AbstractImportSalesJob {

	private ApplicationContext applicationContext;
	private Date processDate;
	public static final String BATCH_ID = "BATCH_ID";
	
	public static final String PROCESS_DATE_FORMAT = "yyyyMMdd";
	public static final Locale PROCESS_DATE_LOCALE = Locale.US;
	protected DateFormat processDateDf = new SimpleDateFormat(PROCESS_DATE_FORMAT, PROCESS_DATE_LOCALE);

	protected Logger log = Logger.getLogger(Logger.DEBUG);

	protected void setLogLevel(int logLevel)
	{
		this.log.setLogLevel(logLevel);
	}

	public Date getProcessDate()
	{
		return processDate;
	}

	public void setProcessDate(Date processDate)
	{
		this.processDate = processDate;
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

	protected SalesProcessService getSalesProcessService()
	{
		return (SalesProcessService) getBean("salesProcessService");
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

	protected IncentiveCompositeService getIncentiveCompositeService()
	{
		return (IncentiveCompositeService) getBean("incentiveCompositeService");
	}

	protected EocCallOutcomeService getEocCallOutcomeService()
	{
		return (EocCallOutcomeService) getBean("eocCallOutcomeService");
	}

	protected UwResultService getUwResultService()
	{
		return (UwResultService) getBean("uwResultService");
	}

	protected UwStatusService getUwStatusService()
	{
		return (UwStatusService) getBean("uwStatusService");
	}

	protected UwDecisionService getUwDecisionService()
	{
		return (UwDecisionService) getBean("uwDecisionService");
	}

	protected CofStatusService getCofStatusService()
	{
		return (CofStatusService) getBean("cofStatusService");
	}

	protected BillingStatusService getBillingStatusService()
	{
		return (BillingStatusService) getBean("billingStatusService");
	}

	protected BillingResultService getBillingResultService()
	{
		return (BillingResultService) getBean("billingResultService");
	}

	protected DdopMappingStatusService getDdopMappingStatusService()
	{
		return (DdopMappingStatusService) getBean("ddopMappingStatusService");
	}

	protected DdopMappingResultService getDdopMappingResultService()
	{
		return (DdopMappingResultService) getBean("ddopMappingResultService");
	}

	protected VSalesCommService getVSalesCommService()
	{
		return (VSalesCommService) getBean("vSalesCommService");
	}

}
