package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.dao.SalesDao;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.service.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("salesService")
@Transactional
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesDao salesDao;

	public void setSalesDao(SalesDao salesDao)
	{
		this.salesDao = salesDao;
	}

	public List<Sales> findSalesRecordAll()
			throws Exception
	{
		return this.salesDao.findAll();
	}

	public Sales findSalesRecordById(Long id)
			throws Exception
	{
		return this.salesDao.find(id);
	}

	public Sales findSalesRecordByXRefference(String xReference)
			throws Exception
	{
		Sales example = new Sales();
		example.setxReference(xReference);

		List<Sales> saleList = this.salesDao.findByExamplePaging(example, null);

		if (saleList.size() == 0)
		{
			return null;
		}

		if (saleList.size() > 1)
		{
			throw new Exception("more than 1 Sales record found for X-Reference[" + xReference + "]");
		}

		return saleList.get(0);
	}

	public Sales findSalesRecordByCustomerFullNameAndTsrAndSaleDate(String customerFullName, Tsr tsr, Date saleDate)
			throws Exception
	{
		customerFullName = customerFullName.replaceAll(" ", "");

		List<Sales> salesList = this.salesDao.findByNamedQuery("findSalesRecordByCustomerFullNameAndTsrAndSaleDate", customerFullName, tsr.getTsrCode(), saleDate);

		if (salesList.size() == 0)
		{
			if (customerFullName.length() > 3)
			{
				return findSalesRecordByCustomerFullNameAndTsrAndSaleDate(customerFullName.substring(1), tsr, saleDate);
			}

			throw new Exception("not found sales record for customerFullName[" + customerFullName + "] and tsr[" + tsr + "] and saleDate[" + saleDate + "]");
		}

		if (salesList.size() > 1)
		{
			throw new Exception("more that 1 record found for customerFullName[" + customerFullName + "] and tsr[" + tsr + "] and saleDate[" + saleDate + "]");
		}

		return salesList.get(0);
	}

	public Sales findSalesRecordByCustomerFullNameAndInsurerAndListSource(String customerFullName, String insurer, String listSource)
			throws Exception
	{
		customerFullName = customerFullName.replaceAll(" ", "");

		List<Sales> salesList = this.salesDao.findByNamedQuery("findSalesRecordByCustomerFullNameAndInsurerAndListSource", customerFullName, insurer, listSource);

		if (salesList.size() == 0)
		{
			if (customerFullName.length() > 3)
			{
				return findSalesRecordByCustomerFullNameAndInsurerAndListSource(customerFullName.substring(1), insurer, listSource);
			}

			throw new Exception("not found sales record for customerFullName[" + customerFullName + "] and insurer[" + insurer + "] and listSource[" + listSource + "]");
		}

		if (salesList.size() > 1)
		{
			throw new Exception("more that 1 record found for customerFullName[" + customerFullName + "] and insurer[" + insurer + "] and listSource[" + listSource + "]");
		}

		return salesList.get(0);
	}

	public Sales findSalesRecordByCustomerFullNameAndSaleDate(String customerFullName, Date saleDate)
			throws Exception
	{
		customerFullName = customerFullName.replaceAll(" ", "");

		List<Sales> salesList = this.salesDao.findByNamedQuery("findSalesRecordByCustomerFullNameAndSaleDate", customerFullName, saleDate);

		if (salesList.size() == 0)
		{
			if (customerFullName.length() > 3)
			{
				return findSalesRecordByCustomerFullNameAndSaleDate(customerFullName.substring(1), saleDate);
			}

			throw new Exception("not found sales record for customerFullName[" + customerFullName + "] and saleDate[" + saleDate + "]");
		}

		if (salesList.size() > 1)
		{
			throw new Exception("more that 1 record found for customerFullName[" + customerFullName + "] and saleDate[" + saleDate + "]");
		}

		System.out.println("found sales " + salesList.get(0));
		return salesList.get(0);
	}

	public List<Sales> findSalesRecordForxRefChanged(String xReference, String customerFullName, String tsrCode, Date saleDate)
			throws Exception
	{
		customerFullName = customerFullName.replaceAll(" ", "");
		return this.salesDao.findByNamedQuery("findSalesRecordForxRefChanged", xReference, customerFullName, tsrCode, saleDate);
	}

	public List<Sales> findSalesRecordByCampaignAndSaleDate(String campaignCode, Date saleDate)
			throws Exception
	{
		return this.salesDao.findByNamedQuery("findSalesRecordByCampaignAndSaleDate", campaignCode, saleDate);
	}

	public List<Sales> findSalesRecordBySaleMonth(String saleMonth)
			throws Exception
	{
		return this.salesDao.findByNamedQuery("findSalesRecordBySaleMonth", saleMonth);
	}

	public List<Sales> findSalesRecordByExample(Sales sales)
			throws Exception
	{
		return this.salesDao.findByExamplePaging(sales, null);
	}

	public List<Sales> searchSalesRecordByExample(Sales sales)
			throws Exception
	{
		return this.salesDao.searchByExamplePaging(sales, null);
	}

	public Sales addSalesRecord(Sales sales, String batchId)
			throws Exception
	{
		return this.salesDao.save(sales);
	}

	public Sales updateSalesRecord(Sales sales, String batchId)
			throws Exception
	{
		return this.salesDao.save(sales);
	}

	public boolean deleteSalesRecord(Sales sales)
			throws Exception
	{
		return this.salesDao.delete(sales.getId());
	}

}
