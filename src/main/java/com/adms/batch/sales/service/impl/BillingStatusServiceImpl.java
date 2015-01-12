package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.BillingStatusDao;
import com.adms.batch.sales.domain.BillingStatus;
import com.adms.batch.sales.service.BillingStatusService;

@Service("billingStatusService")
@Transactional
public class BillingStatusServiceImpl implements BillingStatusService {

	@Autowired
	private BillingStatusDao billingStatusDao;

	public void setBillingStatusDao(BillingStatusDao billingStatusDao)
	{
		this.billingStatusDao = billingStatusDao;
	}

	public List<BillingStatus> findBillingStatusAll()
			throws Exception
	{
		return this.billingStatusDao.findAll();
	}

	public BillingStatus findBillingStatusById(Long id)
			throws Exception
	{
		return this.billingStatusDao.find(id);
	}

	public BillingStatus findBillingStatusByStatus(String billingStatus)
			throws Exception
	{
		BillingStatus example = new BillingStatus();
		example.setBillingStatus(billingStatus);

		List<BillingStatus> billingStatusList = this.billingStatusDao.findByExamplePaging(example, null);

		if (billingStatusList.size() == 0)
		{
			return null;
		}

		if (billingStatusList.size() > 1)
		{
			throw new Exception("more than 1 BillingStatus record found for billingStatus[" + billingStatus + "]");
		}

		return billingStatusList.get(0);
	}

	public BillingStatus findBillingStatusByDescription(String description)
			throws Exception
	{
		BillingStatus example = new BillingStatus();
		example.setDescription(description);

		List<BillingStatus> billingStatusList = this.billingStatusDao.findByExamplePaging(example, null);

		if (billingStatusList.size() == 0)
		{
			return null;
		}

		if (billingStatusList.size() > 1)
		{
			throw new Exception("more than 1 BillingStatus record found for description[" + description + "]");
		}

		return billingStatusList.get(0);
	}

	public List<BillingStatus> findBillingStatusByExample(BillingStatus billingStatus)
			throws Exception
	{
		return this.billingStatusDao.findByExamplePaging(billingStatus, null);
	}

	public List<BillingStatus> searchBillingStatusByExample(BillingStatus billingStatus)
			throws Exception
	{
		return this.billingStatusDao.searchByExamplePaging(billingStatus, null);
	}

	public BillingStatus addBillingStatus(BillingStatus billingStatus, String batchId)
			throws Exception
	{
		return this.billingStatusDao.save(billingStatus);
	}

	public BillingStatus updateBillingStatus(BillingStatus billingStatus, String batchId)
			throws Exception
	{
		return this.billingStatusDao.save(billingStatus);
	}

	public boolean deleteBillingStatus(BillingStatus billingStatus)
			throws Exception
	{
		return this.billingStatusDao.delete(billingStatus.getId());
	}

}
