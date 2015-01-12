package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.UwResultDao;
import com.adms.batch.sales.domain.UwResult;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("uwResultDao")
public class UwResultDaoImpl extends GenericDaoHibernate<UwResult, Long> implements UwResultDao {

	public UwResultDaoImpl() {
		super(UwResult.class);
	}

}
