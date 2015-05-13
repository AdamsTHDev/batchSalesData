package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.ProductionByLot;

public interface ProductionByLotService {

	public List<ProductionByLot> findProductionByLotAll()
			throws Exception;

	public ProductionByLot findProductionByLotById(Long id)
			throws Exception;

	public ProductionByLot findProductionByLotByListLotCodeAndProductionDate(String listLotCode, Date productionDate)
			throws Exception;

	public ProductionByLot addProductionByLot(ProductionByLot productionByLot, String batchId)
			throws Exception;

	public ProductionByLot updateProductionByLot(ProductionByLot productionByLot, String batchId)
			throws Exception;

	public boolean deleteProductionByLot(ProductionByLot productionByLot)
			throws Exception;

}
