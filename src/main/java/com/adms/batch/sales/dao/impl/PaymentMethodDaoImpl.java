package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.PaymentMethodDao;
import com.adms.batch.sales.domain.PaymentMethod;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("paymentMethodDao")
public class PaymentMethodDaoImpl extends GenericDaoHibernate<PaymentMethod, Long> implements PaymentMethodDao {

	public PaymentMethodDaoImpl() {
		super(PaymentMethod.class);
	}

}
