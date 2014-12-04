package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.IncentiveCompositeDao;
import com.adms.batch.sales.domain.IncentiveComposite;
import com.adms.batch.sales.service.IncentiveCompositeService;

@Service("incentiveCompositeService")
@Transactional
public class IncentiveCompositeServiceImpl implements IncentiveCompositeService {

	@Autowired
	private IncentiveCompositeDao incentiveCompositeDao;

	public void setIncentiveCompositeDao(IncentiveCompositeDao incentiveCompositeDao)
	{
		this.incentiveCompositeDao = incentiveCompositeDao;
	}

	public List<IncentiveComposite> findIncentiveCompositeAll()
			throws Exception
	{
		return this.incentiveCompositeDao.findAll();
	}

	public IncentiveComposite findIncentiveCompositeById(Long id)
			throws Exception
	{
		return this.incentiveCompositeDao.find(id);
	}

	public IncentiveComposite findByIncentiveAndCampaignCode(String incentiveCode, String campaignCode)
			throws Exception
	{
		List<IncentiveComposite> list = this.incentiveCompositeDao.findByNamedQuery("findByIncentiveAndCampaignCode", incentiveCode, campaignCode);
		return (IncentiveComposite) (CollectionUtils.isNotEmpty(list) ? list.get(0) : null);
	}

	public IncentiveComposite findByIncentiveAndCompositeName(String incentiveCode, String compositeName)
			throws Exception
	{
		List<IncentiveComposite> list = this.incentiveCompositeDao.findByNamedQuery("findByIncentiveAndCompositeName", incentiveCode, compositeName);
		return (IncentiveComposite) (CollectionUtils.isNotEmpty(list) ? list.get(0) : null);
	}

	public List<IncentiveComposite> findIncentiveCompositeByExample(IncentiveComposite incentiveComposite)
			throws Exception
	{
		return this.incentiveCompositeDao.findByExamplePaging(incentiveComposite, null);
	}

	public List<IncentiveComposite> searchIncentiveCompositeByExample(IncentiveComposite incentiveComposite)
			throws Exception
	{
		return this.incentiveCompositeDao.searchByExamplePaging(incentiveComposite, null);
	}

	public IncentiveComposite addIncentiveComposite(IncentiveComposite incentiveComposite, String batchId)
			throws Exception
	{
		return this.incentiveCompositeDao.save(incentiveComposite);
	}

	public IncentiveComposite updateIncentiveComposite(IncentiveComposite incentiveComposite, String batchId)
			throws Exception
	{
		return this.incentiveCompositeDao.save(incentiveComposite);
	}

	public boolean deleteIncentiveComposite(IncentiveComposite incentiveComposite)
			throws Exception
	{
		return this.incentiveCompositeDao.delete(incentiveComposite.getId());
	}

}
