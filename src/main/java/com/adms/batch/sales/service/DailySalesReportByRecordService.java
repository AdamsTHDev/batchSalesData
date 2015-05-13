package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DailySalesReportByRecord;

public interface DailySalesReportByRecordService {

	public List<DailySalesReportByRecord> findDailySalesReportByRecord(String campaignCode)
			throws Exception;

}
