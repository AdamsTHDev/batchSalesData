package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.BillingResultDao;
import com.adms.batch.sales.domain.BillingResult;
import com.adms.batch.sales.service.BillingResultService;

@Service("billingResultService")
@Transactional
public class BillingResultServiceImpl implements BillingResultService {

	@Autowired
	private BillingResultDao billingResultDao;

	public void setBillingResultDao(BillingResultDao billingResultDao)
	{
		this.billingResultDao = billingResultDao;
	}

	public List<BillingResult> findBillingResultAll()
			throws Exception
	{
		return this.billingResultDao.findAll();
	}

	public BillingResult findBillingResultById(Long id)
			throws Exception
	{
		return this.billingResultDao.find(id);
	}

	public List<BillingResult> findBillingResultByxReference(String xReference)
			throws Exception
	{
		List<BillingResult> billingResultList = this.billingResultDao.findByNamedQuery("findBillingResultByxReference", xReference);

		return billingResultList.size() > 0 ? billingResultList : null;
	}

	public BillingResult findBillingResultByxRefAndBillingDate(String xReference, Date billingDate)
			throws Exception
	{
		List<BillingResult> billingResultList = this.billingResultDao.findByNamedQuery("findBillingResultByxRefAndBillingDate", xReference, billingDate);

		return billingResultList.size() > 0 ? billingResultList.get(0) : null;
	}

	public List<BillingResult> findBillingResultByExample(BillingResult billingResult)
			throws Exception
	{
		return this.billingResultDao.findByExamplePaging(billingResult, null);
	}

	public List<BillingResult> searchBillingResultByExample(BillingResult billingResult)
			throws Exception
	{
		return this.billingResultDao.searchByExamplePaging(billingResult, null);
	}

	public BillingResult addBillingResult(BillingResult billingResult, String batchId)
			throws Exception
	{
		return this.billingResultDao.save(billingResult);
	}

	public BillingResult updateBillingResult(BillingResult billingResult, String batchId)
			throws Exception
	{
		return this.billingResultDao.save(billingResult);
	}

	public boolean deleteBillingResult(BillingResult billingResult)
			throws Exception
	{
		return this.billingResultDao.delete(billingResult.getId());
	}

}
