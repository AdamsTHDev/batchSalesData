package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.ReconfirmStatusDao;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("reconfirmStatusDao")
public class ReconfirmStatusDaoImpl extends GenericDaoHibernate<ReconfirmStatus, Long> implements ReconfirmStatusDao {

	public ReconfirmStatusDaoImpl() {
		super(ReconfirmStatus.class);
	}

}
