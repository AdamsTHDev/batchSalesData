package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.SalesProcessDao;
import com.adms.batch.sales.domain.SalesProcess;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("salesProcessDao")
public class SalesProcessDaoImpl extends GenericDaoHibernate<SalesProcess, Long> implements SalesProcessDao {

	public SalesProcessDaoImpl() {
		super(SalesProcess.class);
	}

}
