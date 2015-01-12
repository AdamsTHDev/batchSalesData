package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.UwDecisionDao;
import com.adms.batch.sales.domain.UwDecision;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("uwDecisionDao")
public class UwDecisionDaoImpl extends GenericDaoHibernate<UwDecision, Long> implements UwDecisionDao {

	public UwDecisionDaoImpl() {
		super(UwDecision.class);
	}

}
