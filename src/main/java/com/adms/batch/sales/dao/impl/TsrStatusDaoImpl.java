package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.TsrStatusDao;
import com.adms.batch.sales.domain.TsrStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("tsrStatusDao")
public class TsrStatusDaoImpl extends GenericDaoHibernate<TsrStatus, Long> implements TsrStatusDao {

	public TsrStatusDaoImpl() {
		super(TsrStatus.class);
	}

}
