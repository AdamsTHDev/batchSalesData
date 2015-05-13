package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyTsrTrackingRecordDao;
import com.adms.batch.sales.domain.DailyTsrTrackingRecord;
import com.adms.batch.sales.service.DailyTsrTrackingRecordService;

@Service("dailyTsrTrackingRecordService")
@Transactional
public class DailyTsrTrackingRecordServiceImpl implements DailyTsrTrackingRecordService {

	@Autowired
	private DailyTsrTrackingRecordDao dailyTsrTrackingRecordDao;

	public List<DailyTsrTrackingRecord> findTsrTrackingRecord(String campaignName)
			throws Exception
	{
		List<DailyTsrTrackingRecord> list = this.dailyTsrTrackingRecordDao.findByNamedQuery("findTsrTrackingRecord", campaignName);

		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

	public List<DailyTsrTrackingRecord> findTsrTrackingSummary(String campaignName)
			throws Exception
	{
		List<DailyTsrTrackingRecord> list = this.dailyTsrTrackingRecordDao.findByNamedQuery("findTsrTrackingSummary", campaignName);

		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

}
