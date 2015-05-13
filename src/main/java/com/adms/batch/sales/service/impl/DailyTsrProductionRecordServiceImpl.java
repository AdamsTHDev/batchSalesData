package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyTsrProductionRecordDao;
import com.adms.batch.sales.domain.DailyTsrProductionRecord;
import com.adms.batch.sales.service.DailyTsrProductionRecordService;

@Service("dailyTsrProductionRecordService")
@Transactional
public class DailyTsrProductionRecordServiceImpl implements DailyTsrProductionRecordService {

	@Autowired
	private DailyTsrProductionRecordDao dailyTsrProductionRecordDao;

	public List<DailyTsrProductionRecord> findTsrProductionRecord()
			throws Exception
	{
		List<DailyTsrProductionRecord> list = this.dailyTsrProductionRecordDao.findByNamedQuery("findTsrProductionRecord");

		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

	public List<DailyTsrProductionRecord> findTsrProductionSummary()
			throws Exception
	{
		List<DailyTsrProductionRecord> list = this.dailyTsrProductionRecordDao.findByNamedQuery("findTsrProductionSummary");

		return CollectionUtils.isNotEmpty(list) ? list : null;
	}

}
