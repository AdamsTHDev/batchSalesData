package com.adms.batch.sales.report.partner.automail;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

import com.adms.batch.sales.domain.AutoReportControl;
import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.domain.KeyValueBean;
import com.adms.batch.sales.report.partner.DailyPerformanceTrackingReport;
import com.adms.batch.sales.service.AutoReportControlService;
import com.adms.batch.sales.service.CampaignService;
import com.adms.batch.sales.service.KeyValueBeanService;
import com.adms.notification.mail.service.MailSenderService;
import com.adms.utils.Logger;
import com.adms.utils.PropertyResource;

public class MtiEmailSender {
	
	public static final String MTI_CONFIG_FILE_LOCATION = "config/autoMail.properties";
	public static final String REPORT_TEMP_FOLDER = "report.temp.folder";
	public static final String REPORT_LOG_FILENAME = "report.mti.log.filename";
	
	private ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext-automail-mti.xml");

	protected Logger log = Logger.getLogger(Logger.DEBUG);
	
	public static void main(String[] s) throws Exception
	{
		new MtiEmailSender().execute();
	}

	@SuppressWarnings("rawtypes")
	public void execute() throws Exception
	{
		log.setOutputStream(new FileOutputStream(PropertyResource.getInstance(MTI_CONFIG_FILE_LOCATION).getValue(REPORT_LOG_FILENAME)));

		log.info("start!");
		Map model = new HashMap();
		String velocityTemplateFile = "DailyReport_MTI_KBANK.vm";

		MailSenderService mss = (MailSenderService) this.context.getBean("mailSenderService");
		
		FileSystemResource[] attachments = getAttachments(mss.getTemplateMailMessage(), model);
		
		if (attachments != null && attachments.length > 0)
		{
			mss.sendMailTemplate(velocityTemplateFile, model, attachments);
		}

		this.context.close();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private FileSystemResource[] getAttachments(SimpleMailMessage simpleMailMessage, Map mailContentModel) throws Exception
	{
		AutoReportControlService autoReportControlService = (AutoReportControlService) this.context.getBean("autoReportControlService");
		CampaignService campaignService = (CampaignService) this.context.getBean("campaignService");
		KeyValueBeanService keyValueBeanService = (KeyValueBeanService) this.context.getBean("keyValueBeanService");
		List<KeyValueBean> campaignCodeList = keyValueBeanService.findNamedQuery("findCampaignCodeByCampaignYearAndInsurerAndListSource", new String[] { "2015", "MTI", "KBank" });

		FileSystemResource[] attachments = null;
		if (campaignCodeList != null && campaignCodeList.size() > 0)
		{
			attachments = new FileSystemResource[campaignCodeList.size()];

			String subjectDate = "";
			
			int i = 0;
			for (KeyValueBean keyValueBean : campaignCodeList)
			{
				System.out.println(keyValueBean);
				Campaign campaign = campaignService.findCampaignByCampaignCode(keyValueBean.getKey());

				String reportDate = keyValueBean.getValue();
				String reportDateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyyMMdd").parse(reportDate));

				subjectDate = reportDateFormat;

				mailContentModel.put("mailContent", reportDateFormat);

				String reportPath = PropertyResource.getInstance(MTI_CONFIG_FILE_LOCATION).getValue(REPORT_TEMP_FOLDER);
				String reportFileName = campaign.getReportName().replace("yyyyMMdd", keyValueBean.getValue());
				String fullReportFileName = reportPath + "\\" + reportFileName;

				DailyPerformanceTrackingReport report = new DailyPerformanceTrackingReport();
				report.generateReport(campaign.getCampaignCode(), fullReportFileName);

				attachments[i++] = new FileSystemResource(fullReportFileName);
				
				AutoReportControl autoReportControl = new AutoReportControl();
				autoReportControl.setCampaign(campaign);
				autoReportControl.setReportName(fullReportFileName);
				autoReportControl.setReportDate(new SimpleDateFormat("yyyyMMdd").parse(reportDate));
				autoReportControl.setSentDate(new Date());
				autoReportControlService.addAutoReportControl(autoReportControl, "BATCH_ID");
			}

			simpleMailMessage.setSubject(simpleMailMessage.getSubject() + subjectDate);
		}

		return attachments;
	}
}
