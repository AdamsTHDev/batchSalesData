package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailyPerformanceTrackingDao;
import com.adms.batch.sales.domain.DailyPerformanceTracking;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailyPerformanceTrackingDao")
public class DailyPerformanceTrackingDaoImpl extends GenericDaoHibernate<DailyPerformanceTracking, Long> implements DailyPerformanceTrackingDao {

	public DailyPerformanceTrackingDaoImpl() {
		super(DailyPerformanceTracking.class);
	}

}
