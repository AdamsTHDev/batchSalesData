package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.EocCallOutcomeDao;
import com.adms.batch.sales.domain.EocCallOutcome;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("eocCallOutcomeDao")
public class EocCallOutcomeDaoImpl extends GenericDaoHibernate<EocCallOutcome, Long> implements EocCallOutcomeDao {

	public EocCallOutcomeDaoImpl() {
		super(EocCallOutcome.class);
	}

}
