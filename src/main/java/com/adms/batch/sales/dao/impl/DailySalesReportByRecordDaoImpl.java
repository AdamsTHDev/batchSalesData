package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DailySalesReportByRecordDao;
import com.adms.batch.sales.domain.DailySalesReportByRecord;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("dailySalesReportByRecordDao")
public class DailySalesReportByRecordDaoImpl extends GenericDaoHibernate<DailySalesReportByRecord, String> implements DailySalesReportByRecordDao {

	public DailySalesReportByRecordDaoImpl() {
		super(DailySalesReportByRecord.class);
	}

}
