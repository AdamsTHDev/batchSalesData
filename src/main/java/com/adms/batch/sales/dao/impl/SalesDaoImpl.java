package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.SalesDao;
import com.adms.batch.sales.domain.Sales;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("salesDao")
public class SalesDaoImpl extends GenericDaoHibernate<Sales, Long> implements SalesDao {

	public SalesDaoImpl() {
		super(Sales.class);
	}

}
