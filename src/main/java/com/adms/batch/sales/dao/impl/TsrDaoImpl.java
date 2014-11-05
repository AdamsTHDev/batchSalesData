package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.TsrDao;
import com.adms.batch.sales.domain.Tsr;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("tsrDao")
public class TsrDaoImpl extends GenericDaoHibernate<Tsr, Long> implements TsrDao {

	public TsrDaoImpl() {
		super(Tsr.class);
	}

}
