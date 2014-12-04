package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.IncentiveCompositeDao;
import com.adms.batch.sales.domain.IncentiveComposite;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("incentiveCompositeDao")
public class IncentiveCompositeDaoImpl extends GenericDaoHibernate<IncentiveComposite, Long> implements IncentiveCompositeDao {

	public IncentiveCompositeDaoImpl() {
		super(IncentiveComposite.class);
	}

}
