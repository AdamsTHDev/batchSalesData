package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyTsrTrackingRecordDao;
import com.adms.batch.sales.domain.DailyTsrTrackingRecord;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyTsrTrackingRecordDao")
public class DailyTsrTrackingRecordDaoImpl extends GenericDaoHibernate<DailyTsrTrackingRecord, String> implements DailyTsrTrackingRecordDao {

	public DailyTsrTrackingRecordDaoImpl() {
		super(DailyTsrTrackingRecord.class);
	}

}
