package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DdopMappingResultDao;
import com.adms.batch.sales.domain.DdopMappingResult;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("ddopMappingResultDao")
public class DdopMappingResultDaoImpl extends GenericDaoHibernate<DdopMappingResult, Long> implements DdopMappingResultDao {

	public DdopMappingResultDaoImpl() {
		super(DdopMappingResult.class);
	}

}
