package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.AutoReportControlDao;
import com.adms.batch.sales.domain.AutoReportControl;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("autoReportControlDao")
public class AutoReportControlDaoImpl extends GenericDaoHibernate<AutoReportControl, Long> implements AutoReportControlDao {

	public AutoReportControlDaoImpl() {
		super(AutoReportControl.class);
	}

}
