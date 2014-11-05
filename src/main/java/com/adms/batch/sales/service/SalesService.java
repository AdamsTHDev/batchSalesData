package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;

public interface SalesService {

	public List<Sales> findSalesRecordAll()
			throws Exception;

	public Sales findSalesRecordById(Long id)
			throws Exception;

	public Sales findSalesRecordByXRefference(String xRefference)
			throws Exception;

	public Sales findSalesRecordByCustomerFullNameAndTsrAndSaleDate(String customerFullName, Tsr tsr, Date saleDate)
			throws Exception;

	public Sales findSalesRecordByCustomerFullNameAndSaleDate(String customerFullName, Date saleDate)
			throws Exception;

	public List<Sales> findSalesRecordBySaleMonth(String saleMonth)
			throws Exception;

	public List<Sales> findSalesRecordByExample(Sales sales)
			throws Exception;

	public List<Sales> searchSalesRecordByExample(Sales sales)
			throws Exception;

	public Sales addSalesRecord(Sales sales, String batchId)
			throws Exception;

	public Sales updateSalesRecord(Sales sales, String batchId)
			throws Exception;

	public boolean deleteSalesRecord(Sales sales)
			throws Exception;

}
