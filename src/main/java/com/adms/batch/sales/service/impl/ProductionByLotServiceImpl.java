package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.ProductionByLotDao;
import com.adms.batch.sales.domain.ProductionByLot;
import com.adms.batch.sales.service.ProductionByLotService;

@Service("productionByLotService")
@Transactional
public class ProductionByLotServiceImpl implements ProductionByLotService {

	@Autowired
	private ProductionByLotDao productionByLotDao;

	public void setProductionByLotDao(ProductionByLotDao productionByLotDao)
	{
		this.productionByLotDao = productionByLotDao;
	}

	public List<ProductionByLot> findProductionByLotAll()
			throws Exception
	{
		return this.productionByLotDao.findAll();
	}

	public ProductionByLot findProductionByLotById(Long id)
			throws Exception
	{
		return this.productionByLotDao.find(id);
	}

	public ProductionByLot findProductionByLotByListLotCodeAndProductionDate(String listLotCode, Date productionDate)
			throws Exception
	{
		List<ProductionByLot> productionByLotList = this.productionByLotDao.findByNamedQuery("findProductionByLotByListLotCodeAndProductionDate", listLotCode, productionDate);

		return productionByLotList.size() == 1 ? productionByLotList.get(0) : null;
	}

	public ProductionByLot addProductionByLot(ProductionByLot productionByLot, String batchId)
			throws Exception
	{
		return this.productionByLotDao.save(productionByLot);
	}

	public ProductionByLot updateProductionByLot(ProductionByLot productionByLot, String batchId)
			throws Exception
	{
		return this.productionByLotDao.save(productionByLot);
	}

	public boolean deleteProductionByLot(ProductionByLot productionByLot)
			throws Exception
	{
		return this.productionByLotDao.delete(productionByLot.getId());
	}

}
