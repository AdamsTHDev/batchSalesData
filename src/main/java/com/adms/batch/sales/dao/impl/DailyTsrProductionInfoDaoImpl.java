package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyTsrProductionInfoDao;
import com.adms.batch.sales.domain.DailyTsrProductionInfo;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyTsrProductionInfoDao")
public class DailyTsrProductionInfoDaoImpl extends GenericDaoHibernate<DailyTsrProductionInfo, String> implements DailyTsrProductionInfoDao {

	public DailyTsrProductionInfoDaoImpl() {
		super(DailyTsrProductionInfo.class);
	}

}
