package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailySalesReportByRecordDao;
import com.adms.batch.sales.domain.DailySalesReportByRecord;
import com.adms.batch.sales.service.DailySalesReportByRecordService;

@Service("dailySalesReportByRecordService")
@Transactional
public class DailySalesReportByRecordServiceImpl implements DailySalesReportByRecordService {

	@Autowired
	private DailySalesReportByRecordDao dailySalesReportByRecordDao;

	public List<DailySalesReportByRecord> findDailySalesReportByRecord(String campaignCode)
			throws Exception
	{
		List<DailySalesReportByRecord> list = this.dailySalesReportByRecordDao.findByNamedQuery("findDailySalesReportByRecord", campaignCode);

		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

}
