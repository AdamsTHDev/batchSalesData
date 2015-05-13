package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailyQcReconfirmSummary;

public interface DailyQcReconfirmSummaryService {

	public List<DailyQcReconfirmSummary> findQcReconfirmStatusSummary(String campaignName)
			throws Exception;

	public List<DailyQcReconfirmSummary> findQcReconfirmStatusTotal(String campaignName)
			throws Exception;

}
