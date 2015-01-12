package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.BillingStatusDao;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("billingStatusDao")
public class BillingStatusDaoImpl extends GenericDaoHibernate<BillingStatus, Long> implements BillingStatusDao {

	public BillingStatusDaoImpl() {
		super(BillingStatus.class);
	}

}
