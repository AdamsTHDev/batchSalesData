package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.KeyFloatValueBeanDao;
import com.adms.batch.sales.domain.KeyFloatValueBean;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("keyFloatValueBeanDao")
public class KeyFloatValueBeanDaoImpl extends GenericDaoHibernate<KeyFloatValueBean, String> implements KeyFloatValueBeanDao {

	public KeyFloatValueBeanDaoImpl() {
		super(KeyFloatValueBean.class);
	}

}
