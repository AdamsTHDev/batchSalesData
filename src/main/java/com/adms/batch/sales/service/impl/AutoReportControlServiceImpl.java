package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.AutoReportControlDao;
import com.adms.batch.sales.domain.AutoReportControl;
import com.adms.batch.sales.service.AutoReportControlService;

@Service("autoReportControlService")
@Transactional
public class AutoReportControlServiceImpl implements AutoReportControlService {

	@Autowired
	private AutoReportControlDao autoReportControlDao;

	public void setAutoReportControlDao(AutoReportControlDao autoReportControlDao)
	{
		this.autoReportControlDao = autoReportControlDao;
	}

	public AutoReportControl findAutoReportControlByCampaignCodeAndReportDate(String campaignCode, Date reportDate)
			throws Exception
	{
		List<AutoReportControl> autoReportControlList = this.autoReportControlDao.findByNamedQuery("findAutoReportControlByCampaignCodeAndReportDate", campaignCode, reportDate);

		return autoReportControlList.size() > 0 ? autoReportControlList.get(0) : null;
	}

	public AutoReportControl addAutoReportControl(AutoReportControl autoReportControl, String batchId)
			throws Exception
	{
		return this.autoReportControlDao.save(autoReportControl);
	}

	public AutoReportControl updateAutoReportControl(AutoReportControl autoReportControl, String batchId)
			throws Exception
	{
		return this.autoReportControlDao.save(autoReportControl);
	}

	public boolean deleteAutoReportControl(AutoReportControl autoReportControl)
			throws Exception
	{
		return this.autoReportControlDao.delete(autoReportControl.getId());
	}

}
