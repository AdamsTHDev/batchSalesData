package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailyQcReconfirmRecord;

public interface DailyQcReconfirmRecordService {

	public List<DailyQcReconfirmRecord> findQcReconfirmRecord(String campaignName)
			throws Exception;


}
