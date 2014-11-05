package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.CallCenterDao;
import com.adms.batch.sales.domain.CallCenter;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("callCenterDao")
public class CallCenterDaoImpl extends GenericDaoHibernate<CallCenter, Long> implements CallCenterDao {

	public CallCenterDaoImpl() {
		super(CallCenter.class);
	}

}
