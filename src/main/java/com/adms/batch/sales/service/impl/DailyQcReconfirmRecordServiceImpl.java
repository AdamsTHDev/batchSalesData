package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyQcReconfirmRecordDao;
import com.adms.batch.sales.domain.DailyQcReconfirmRecord;
import com.adms.batch.sales.service.DailyQcReconfirmRecordService;

@Service("dailyQcReconfirmRecordService")
@Transactional
public class DailyQcReconfirmRecordServiceImpl implements DailyQcReconfirmRecordService {

	@Autowired
	private DailyQcReconfirmRecordDao dailyQcReconfirmRecordDao;

	public List<DailyQcReconfirmRecord> findQcReconfirmRecord(String campaignName)
			throws Exception
	{
		return this.dailyQcReconfirmRecordDao.findByNamedQuery("findQcReconfirmRecord", campaignName);
	}

}
