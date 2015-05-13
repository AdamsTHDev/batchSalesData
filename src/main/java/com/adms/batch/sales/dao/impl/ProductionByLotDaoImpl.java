package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.ProductionByLotDao;
import com.adms.batch.sales.domain.ProductionByLot;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("productionByLotDao")
public class ProductionByLotDaoImpl extends GenericDaoHibernate<ProductionByLot, Long> implements ProductionByLotDao {

	public ProductionByLotDaoImpl() {
		super(ProductionByLot.class);
	}

}
