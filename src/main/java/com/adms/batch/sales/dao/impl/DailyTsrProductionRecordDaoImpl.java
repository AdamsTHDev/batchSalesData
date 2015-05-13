package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyTsrProductionRecordDao;
import com.adms.batch.sales.domain.DailyTsrProductionRecord;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyTsrProductionRecordDao")
public class DailyTsrProductionRecordDaoImpl extends GenericDaoHibernate<DailyTsrProductionRecord, String> implements DailyTsrProductionRecordDao {

	public DailyTsrProductionRecordDaoImpl() {
		super(DailyTsrProductionRecord.class);
	}

}
