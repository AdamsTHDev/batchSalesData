package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailyTsrProductionInfoDao;
import com.adms.batch.sales.domain.DailyTsrProductionInfo;
import com.adms.batch.sales.service.DailyTsrProductionInfoService;

@Service("dailyTsrProductionInfoService")
@Transactional
public class DailyTsrProductionInfoServiceImpl implements DailyTsrProductionInfoService {

	@Autowired
	private DailyTsrProductionInfoDao dailyTsrProductionInfoDao;

	public DailyTsrProductionInfo findTsrProductionInfo()
			throws Exception
	{
		List<DailyTsrProductionInfo> list = this.dailyTsrProductionInfoDao.findAll();

		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

}
