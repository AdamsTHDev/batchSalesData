package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.VSalesCommDao;
import com.adms.batch.sales.domain.VSalesComm;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("vSalesCommDao")
public class VSalesCommDaoImpl extends GenericDaoHibernate<VSalesComm, String> implements VSalesCommDao {

	public VSalesCommDaoImpl() {
		super(VSalesComm.class);
	}

}
