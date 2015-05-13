package com.adms.batch.persistency.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.persistency.dao.OutputPersistencyDao;
import com.adms.batch.persistency.domain.OutputPersistency;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("outputPersistencyDao")
public class OutputPersistencyDaoImpl extends GenericDaoHibernate<OutputPersistency, String> implements OutputPersistencyDao {

	public OutputPersistencyDaoImpl() {
		super(OutputPersistency.class);
	}

}
