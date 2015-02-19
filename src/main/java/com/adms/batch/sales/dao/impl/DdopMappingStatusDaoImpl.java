package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DdopMappingStatusDao;
import com.adms.batch.sales.domain.DdopMappingStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("ddopMappingStatusDao")
public class DdopMappingStatusDaoImpl extends GenericDaoHibernate<DdopMappingStatus, Long> implements DdopMappingStatusDao {

	public DdopMappingStatusDaoImpl() {
		super(DdopMappingStatus.class);
	}

}
