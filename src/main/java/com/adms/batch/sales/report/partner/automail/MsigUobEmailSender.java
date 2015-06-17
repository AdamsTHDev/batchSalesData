package com.adms.batch.sales.report.partner.automail;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class MsigUobEmailSender {
	
	public static final String MTI_CONFIG_FILE_LOCATION = "config/autoMail.properties";
	public static final String REPORT_TEMP_FOLDER = "report.temp.folder";
	public static final String REPORT_LOG_FILENAME = "report.msig.log.filename";
	
	private ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext-automail-msig-uob.xml");

	protected Logger log = Logger.getLogger(Logger.DEBUG);
	
	public static void main(String[] s) throws Exception
	{
		new MsigUobEmailSender().execute();
	}

	@SuppressWarnings("rawtypes")
	public void execute() throws Exception
	{
		log.setOutputStream(new FileOutputStream(PropertyResource.getInstance(MTI_CONFIG_FILE_LOCATION).getValue(REPORT_LOG_FILENAME)));

		log.info("start!");
		Map model = new HashMap();
		String velocityTemplateFile = "DailyReport_MSIG_UOB.vm";

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
		List<KeyValueBean> campaignCodeList = keyValueBeanService.findNamedQuery("findCampaignCodeByCampaignYearAndInsurerAndListSource", new String[] { "2015", "MSIG", "UOB" });

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

				String reportDateTxt = keyValueBean.getValue();
				Date reportDate = new SimpleDateFormat("yyyyMMdd").parse(reportDateTxt);

				subjectDate = new SimpleDateFormat("dd/MM/yyyy").format(reportDate);

				String reportPath = PropertyResource.getInstance(MTI_CONFIG_FILE_LOCATION).getValue(REPORT_TEMP_FOLDER);
				String reportFileName = campaign.getReportName().replace("dd_MMM_yyyy", new SimpleDateFormat("dd_MMM_yyyy").format(reportDate));
				String fullReportFileName = reportPath + "\\" + reportFileName;

				DailyPerformanceTrackingReport report = new DailyPerformanceTrackingReport();
				report.generateReport(campaign.getCampaignCode(), fullReportFileName);

				attachments[i++] = new FileSystemResource(fullReportFileName);
				
				AutoReportControl autoReportControl = new AutoReportControl();
				autoReportControl.setCampaign(campaign);
				autoReportControl.setReportName(fullReportFileName);
				autoReportControl.setReportDate(reportDate);
				autoReportControl.setSentDate(new Date());
				autoReportControlService.addAutoReportControl(autoReportControl, "BATCH_ID");

				mailContentModel.put("mailContent", new SimpleDateFormat("dd/MM/yyyy").format(reportDate));
				putFloorProduction(reportDateTxt, campaign, mailContentModel);
			}

			simpleMailMessage.setSubject(simpleMailMessage.getSubject() + subjectDate);
		}

		return attachments;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void putFloorProduction(String reportDateTxt, Campaign campaign, Map mailContentModel) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dfMail = new SimpleDateFormat("dd.MM.yyyy");
		DecimalFormat nf = new DecimalFormat("#,##0");
		Date reportDate = df.parse(reportDateTxt);
		Calendar c = Calendar.getInstance();
		c.setTime(reportDate);
		int dateInt = c.get(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE, -(dateInt - 1));
		
		ArrayList<Map> productionTable = new ArrayList<Map>();
		mailContentModel.put("productionTable", productionTable);

		BigDecimal floorAppMtd = new BigDecimal(0);
		BigDecimal floorTarpMtd = new BigDecimal(0);
		for (int i = 1; i <= dateInt; i++)
		{
			String floorDate = df.format(c.getTime());
			BigDecimal floorApp = getFloorApp(campaign, floorDate);
			BigDecimal floorTarp = getFloorTarp(campaign, floorDate);

			Map<String, String> dailyProduction = new HashMap<String, String>();
			dailyProduction.put("productionDate", dfMail.format(c.getTime()));
			dailyProduction.put("floorApp", nf.format(floorApp));
			dailyProduction.put("floorTarp", nf.format(floorTarp));

			productionTable.add(dailyProduction);

			floorAppMtd = floorAppMtd.add(floorApp);
			floorTarpMtd = floorTarpMtd.add(floorTarp);
			c.add(Calendar.DATE, 1);
		}
		// MTD
		Map<String, String> dailyProduction = new HashMap<String, String>();
		dailyProduction.put("productionDate", "MTD");
		dailyProduction.put("floorApp", nf.format(floorAppMtd));
		dailyProduction.put("floorTarp", nf.format(floorTarpMtd));
		productionTable.add(dailyProduction);
	}

	private BigDecimal getFloorApp(Campaign campaign, String floorDate)
			throws Exception
	{
		BigDecimal floorApp = null;
		try
		{
			KeyValueBeanService keyValueBeanService = (KeyValueBeanService) this.context.getBean("keyValueBeanService");
			List<KeyValueBean> floorAppList = keyValueBeanService.findNamedQuery("findDailyFloorAppByCampaignCodeAndDate", new String[] { campaign.getCampaignCode(), floorDate });

			if (floorAppList != null && floorAppList.size() > 0)
			{
				floorApp = new BigDecimal(floorAppList.get(0).getValue());
			}
		}
		catch (Exception e)
		{
			log.error("error param [campaignCode: " + campaign.getCampaignCode() + ", floorDate: " + floorDate + "]");
			throw e;
		}

		return floorApp == null ? new BigDecimal(0) : floorApp;
	}

	private BigDecimal getFloorTarp(Campaign campaign, String floorDate)
			throws Exception
	{
		BigDecimal floorTarp = null;
		try
		{
			KeyValueBeanService keyValueBeanService = (KeyValueBeanService) this.context.getBean("keyValueBeanService");
			List<KeyValueBean> floorTarpList = keyValueBeanService.findNamedQuery("findDailyFloorTarpByCampaignCodeAndDate", new String[] { campaign.getCampaignCode(), floorDate });

			if (floorTarpList != null && floorTarpList.size() > 0)
			{
				floorTarp = new BigDecimal(floorTarpList.get(0).getValue());
			}
		}
		catch (Exception e)
		{
			log.error("error param [campaignCode: " + campaign.getCampaignCode() + ", floorDate: " + floorDate + "]");
			throw e;
		}

		return floorTarp == null ? new BigDecimal(0) : floorTarp;
	}
}
