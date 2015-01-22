package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailyPerformanceTracking;

public interface DailyPerformanceTrackingService {

	public List<DailyPerformanceTracking> findDailyPerformanceTrackingByCampaign(String campaignCode, String keyCode)
			throws Exception;

	public List<String> findDailyPerformanceKeyCodeByCampaign(String campaignCode)
			throws Exception;

}
