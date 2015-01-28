package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailySummaryStatusType2DetailDao;
import com.adms.batch.sales.domain.DailySummaryStatusType2Detail;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailySummaryStatusType2DetailDao")
public class DailySummaryStatusType2DetailDaoImpl extends GenericDaoHibernate<DailySummaryStatusType2Detail, Long> implements DailySummaryStatusType2DetailDao {

	public DailySummaryStatusType2DetailDaoImpl() {
		super(DailySummaryStatusType2Detail.class);
	}

}
