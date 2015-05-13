package com.adms.batch.sales.report.partner.automail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.domain.KeyValueBean;
import com.adms.batch.sales.report.partner.DailyPerformanceTrackingReport;
import com.adms.batch.sales.service.CampaignService;
import com.adms.batch.sales.service.KeyValueBeanService;
import com.adms.notification.mail.service.MailSenderService;

public class MtiEmailSender {
	
	
	private ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-automail-mti.xml");
	
	public static void main(String[] s) throws Exception
	{
		new MtiEmailSender().execute();
	}

	@SuppressWarnings("unchecked")
	public void execute() throws Exception
	{
		Map model = new HashMap();
		String velocityTemplateFile = "DailyReport_MTI_KBANK.vm";
		model.put("mailContent", "99/04/2015");
		FileSystemResource[] attachments = getAttachments();
		
		MailSenderService mss = (MailSenderService) this.context.getBean("mailSenderService");
		mss.sendMailTemplate(velocityTemplateFile, model, attachments);

		this.context.close();
	}
	
	private FileSystemResource[] getAttachments() throws Exception
	{
		CampaignService campaignService = (CampaignService) this.context.getBean("campaignService");
		KeyValueBeanService keyValueBeanService = (KeyValueBeanService) this.context.getBean("keyValueBeanService");
		List<KeyValueBean> campaignCodeList = keyValueBeanService.findNamedQuery("findCampaignCodeByCampaignYearAndListSourceAndInsurer", new String[] { "2015", "KBank", "MTI" });

		int i = 0;
		FileSystemResource[] attachments = new FileSystemResource[campaignCodeList.size()];
		for (KeyValueBean keyValueBean : campaignCodeList)
		{
			System.out.println(keyValueBean);
			Campaign campaign = campaignService.findCampaignByCampaignCode(keyValueBean.getValue());

			String reportFileName = "D:\\temp\\" + campaign.getReportName();

			DailyPerformanceTrackingReport report = new DailyPerformanceTrackingReport();
			report.generateReport(campaign.getCampaignCode(), reportFileName);

			attachments[i++] = new FileSystemResource(reportFileName);
		}

		return attachments;
	}
}
