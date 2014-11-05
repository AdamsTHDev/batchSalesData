package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.TestDao;
import com.adms.batch.sales.domain.TestDomain;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("testDao")
public class TestDaoImpl extends GenericDaoHibernate<TestDomain, Long> implements TestDao {

	public TestDaoImpl() {
		super(TestDomain.class);
	}

}
