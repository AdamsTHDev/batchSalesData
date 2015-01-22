package com.adms.batch.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
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

	public List<String> findDailyPerformanceKeyCodeByCampaign(String campaignCode)
			throws Exception
	{
		List<DailyPerformanceTracking> dailyPerformanceTrackingList = this.dailyPerformanceTrackingDao.findByNamedQuery("findDailyPerformanceKeyCodeByCampaign", campaignCode);
		
		List<String> keyCodeList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(dailyPerformanceTrackingList))
		{
			for (DailyPerformanceTracking dailyPerformanceTracking : dailyPerformanceTrackingList)
			{
				if (!keyCodeList.contains(dailyPerformanceTracking.getKeyCode()))
				{
					keyCodeList.add(dailyPerformanceTracking.getKeyCode());
				}
			}
		}
		return keyCodeList;
	}

}
