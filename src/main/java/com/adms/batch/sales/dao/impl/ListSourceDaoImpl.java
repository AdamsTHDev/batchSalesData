package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.ListSourceDao;
import com.adms.batch.sales.domain.ListSource;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("listSourceDao")
public class ListSourceDaoImpl extends GenericDaoHibernate<ListSource, Long> implements ListSourceDao {

	public ListSourceDaoImpl() {
		super(ListSource.class);
	}

}
