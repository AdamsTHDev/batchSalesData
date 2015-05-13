package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailyTsrProductionRecord;

public interface DailyTsrProductionRecordService {

	public List<DailyTsrProductionRecord> findTsrProductionRecord()
			throws Exception;

	public List<DailyTsrProductionRecord> findTsrProductionSummary()
			throws Exception;


}
