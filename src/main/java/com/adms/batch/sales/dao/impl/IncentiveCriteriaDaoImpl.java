package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.IncentiveCriteriaDao;
import com.adms.batch.sales.domain.IncentiveCriteria;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("incentiveCriteriaDao")
public class IncentiveCriteriaDaoImpl extends GenericDaoHibernate<IncentiveCriteria, Long> implements IncentiveCriteriaDao {

	public IncentiveCriteriaDaoImpl() {
		super(IncentiveCriteria.class);
	}

}
