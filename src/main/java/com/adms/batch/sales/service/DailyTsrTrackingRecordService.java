package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailyTsrTrackingRecord;

public interface DailyTsrTrackingRecordService {

	public List<DailyTsrTrackingRecord> findTsrTrackingRecord(String campaignName)
			throws Exception;

	public List<DailyTsrTrackingRecord> findTsrTrackingSummary(String campaignName)
			throws Exception;


}
