package com.adms.batch.sales.service;

import java.util.Date;

import com.adms.batch.sales.domain.AutoReportControl;

public interface AutoReportControlService {

	public AutoReportControl findAutoReportControlByCampaignCodeAndReportDate(String campaignCode, Date reportDate)
			throws Exception;

	public AutoReportControl addAutoReportControl(AutoReportControl autoReportControl, String batchId)
			throws Exception;

	public AutoReportControl updateAutoReportControl(AutoReportControl autoReportControl, String batchId)
			throws Exception;

	public boolean deleteAutoReportControl(AutoReportControl autoReportControl)
			throws Exception;

}
