package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailySummaryStatusType2Detail;

public interface DailySummaryStatusType2DetailService {

	public List<DailySummaryStatusType2Detail> findByCampaignAndProcessDateAndKeyCode(String campaign, String processDate, String keyCode)
			throws Exception;

	public List<String> findKeyCodeByCampaignAndProcessDate(String campaign, String processDate)
			throws Exception;

	public String findLotDecriptionByKeyCode(String keyCode)
			throws Exception;

}
