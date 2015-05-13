package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.KeyValueBeanDao;
import com.adms.batch.sales.domain.KeyValueBean;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("keyValueBeanDao")
public class KeyValueBeanDaoImpl extends GenericDaoHibernate<KeyValueBean, String> implements KeyValueBeanDao {

	public KeyValueBeanDaoImpl() {
		super(KeyValueBean.class);
	}

}
