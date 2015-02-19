package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.CofStatusDao;
import com.adms.batch.sales.domain.CofStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("cofStatusDao")
public class CofStatusDaoImpl extends GenericDaoHibernate<CofStatus, Long> implements CofStatusDao {

	public CofStatusDaoImpl() {
		super(CofStatus.class);
	}

}
