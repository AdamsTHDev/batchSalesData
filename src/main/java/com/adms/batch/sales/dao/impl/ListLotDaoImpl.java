package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.ListLotDao;
import com.adms.batch.sales.domain.ListLot;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("listLotDao")
public class ListLotDaoImpl extends GenericDaoHibernate<ListLot, Long> implements ListLotDao {

	public ListLotDaoImpl() {
		super(ListLot.class);
	}

}
