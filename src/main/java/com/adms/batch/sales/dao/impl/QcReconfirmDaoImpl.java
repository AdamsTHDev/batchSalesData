package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.QcReconfirmDao;
import com.adms.batch.sales.domain.QcReconfirm;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("qcReconfirmDao")
public class QcReconfirmDaoImpl extends GenericDaoHibernate<QcReconfirm, Long> implements QcReconfirmDao {

	public QcReconfirmDaoImpl() {
		super(QcReconfirm.class);
	}

}
