package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.TsrHierarchyDao;
import com.adms.batch.sales.domain.TsrHierarchy;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("tsrHierarchyDao")
public class TsrHierarchyDaoImpl extends GenericDaoHibernate<TsrHierarchy, Long> implements TsrHierarchyDao {

	public TsrHierarchyDaoImpl() {
		super(TsrHierarchy.class);
	}

}
