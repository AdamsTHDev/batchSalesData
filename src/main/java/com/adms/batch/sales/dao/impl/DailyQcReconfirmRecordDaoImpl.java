package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyQcReconfirmRecordDao;
import com.adms.batch.sales.domain.DailyQcReconfirmRecord;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyQcReconfirmRecordDao")
public class DailyQcReconfirmRecordDaoImpl extends GenericDaoHibernate<DailyQcReconfirmRecord, String> implements DailyQcReconfirmRecordDao {

	public DailyQcReconfirmRecordDaoImpl() {
		super(DailyQcReconfirmRecord.class);
	}

}
