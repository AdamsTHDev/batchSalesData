package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyPerformanceTrackingDao;
import com.adms.batch.sales.domain.DailyPerformanceTracking;
import com.adms.batch.sales.service.DailyPerformanceTrackingService;

@Service("dailyPerformanceTrackingService")
@Transactional
public class DailyPerformanceTrackingServiceImpl implements DailyPerformanceTrackingService {

	@Autowired
	private DailyPerformanceTrackingDao dailyPerformanceTrackingDao;

	public List<DailyPerformanceTracking> findDailyPerformanceTrackingByCampaign(String campaignCode, String keyCode)
			throws Exception
	{
		return this.dailyPerformanceTrackingDao.findByNamedQuery("findDailyPerformanceTrackingByCampaign", campaignCode, keyCode);
	}

}
