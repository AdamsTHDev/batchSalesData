package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.IncentiveInfoDao;
import com.adms.batch.sales.domain.IncentiveInfo;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("incentiveInfoDao")
public class IncentiveInfoDaoImpl extends GenericDaoHibernate<IncentiveInfo, Long> implements IncentiveInfoDao {

	public IncentiveInfoDaoImpl() {
		super(IncentiveInfo.class);
	}

}
