package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.TsrPositionDao;
import com.adms.batch.sales.domain.TsrPosition;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("tsrPositionDao")
public class TsrPositionDaoImpl extends GenericDaoHibernate<TsrPosition, Long> implements TsrPositionDao {

	public TsrPositionDaoImpl() {
		super(TsrPosition.class);
	}

}
