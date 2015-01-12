package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.UwStatusDao;
import com.adms.batch.sales.domain.UwStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("uwStatusDao")
public class UwStatusDaoImpl extends GenericDaoHibernate<UwStatus, Long> implements UwStatusDao {

	public UwStatusDaoImpl() {
		super(UwStatus.class);
	}

}
