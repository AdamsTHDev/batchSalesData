package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.InsurerDao;
import com.adms.batch.sales.domain.Insurer;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("insurerDao")
public class InsurerDaoImpl extends GenericDaoHibernate<Insurer, Long> implements InsurerDao {

	public InsurerDaoImpl() {
		super(Insurer.class);
	}

}
