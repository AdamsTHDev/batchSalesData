package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.BillingResultDao;
import com.adms.batch.sales.domain.BillingResult;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("billingResultDao")
public class BillingResultDaoImpl extends GenericDaoHibernate<BillingResult, Long> implements BillingResultDao {

	public BillingResultDaoImpl() {
		super(BillingResult.class);
	}

}
