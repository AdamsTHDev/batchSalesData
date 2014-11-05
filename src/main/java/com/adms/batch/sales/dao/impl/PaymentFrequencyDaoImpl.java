package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.PaymentFrequencyDao;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("paymentFrequencyDao")
public class PaymentFrequencyDaoImpl extends GenericDaoHibernate<PaymentFrequency, Long> implements PaymentFrequencyDao {

	public PaymentFrequencyDaoImpl() {
		super(PaymentFrequency.class);
	}

}
