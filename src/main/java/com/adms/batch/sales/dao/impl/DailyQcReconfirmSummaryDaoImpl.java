package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyQcReconfirmSummaryDao;
import com.adms.batch.sales.domain.DailyQcReconfirmSummary;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyQcReconfirmSummaryDao")
public class DailyQcReconfirmSummaryDaoImpl extends GenericDaoHibernate<DailyQcReconfirmSummary, Long> implements DailyQcReconfirmSummaryDao {

	public DailyQcReconfirmSummaryDaoImpl() {
		super(DailyQcReconfirmSummary.class);
	}

}
