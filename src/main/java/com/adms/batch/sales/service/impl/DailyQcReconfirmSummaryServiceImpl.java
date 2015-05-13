package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyQcReconfirmSummaryDao;
import com.adms.batch.sales.domain.DailyQcReconfirmSummary;
import com.adms.batch.sales.service.DailyQcReconfirmSummaryService;

@Service("dailyQcReconfirmSummaryService")
@Transactional
public class DailyQcReconfirmSummaryServiceImpl implements DailyQcReconfirmSummaryService {

	@Autowired
	private DailyQcReconfirmSummaryDao dailyQcReconfirmSummaryDao;

	public List<DailyQcReconfirmSummary> findQcReconfirmStatusSummary(String campaignName)
			throws Exception
	{
		return this.dailyQcReconfirmSummaryDao.findByNamedQuery("findQcReconfirmStatusSummary", campaignName);
	}

	public List<DailyQcReconfirmSummary> findQcReconfirmStatusTotal(String campaignName)
			throws Exception
	{
		return this.dailyQcReconfirmSummaryDao.findByNamedQuery("findQcReconfirmStatusTotal", campaignName);
	}

}
