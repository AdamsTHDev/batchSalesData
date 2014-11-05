package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.IncentiveCriteriaDao;
import com.adms.batch.sales.domain.IncentiveCriteria;
import com.adms.batch.sales.service.IncentiveCriteriaService;

@Service("incentiveCriteriaService")
@Transactional
public class IncentiveCriteriaServiceImpl implements IncentiveCriteriaService {

	@Autowired
	private IncentiveCriteriaDao incentiveCriteriaDao;

	public void setIncentiveCriteriaDao(IncentiveCriteriaDao incentiveCriteriaDao)
	{
		this.incentiveCriteriaDao = incentiveCriteriaDao;
	}

	public List<IncentiveCriteria> findIncentiveCriteriaAll()
			throws Exception
	{
		return this.incentiveCriteriaDao.findAll();
	}

	public IncentiveCriteria findIncentiveCriteriaById(Long id)
			throws Exception
	{
		return this.incentiveCriteriaDao.find(id);
	}

	public List<IncentiveCriteria> findBySydneyCriteria(String campaignCode, String qaStatus, String qaReason)
			throws Exception
	{
		return this.incentiveCriteriaDao.findByNamedQuery("findBySydneyCriteria", campaignCode, qaStatus, qaReason);
	}

	public List<IncentiveCriteria> findIncentiveCriteriaByExample(IncentiveCriteria incentiveCriteria)
			throws Exception
	{
		return this.incentiveCriteriaDao.findByExamplePaging(incentiveCriteria, null);
	}

	public List<IncentiveCriteria> searchIncentiveCriteriaByExample(IncentiveCriteria incentiveCriteria)
			throws Exception
	{
		return this.incentiveCriteriaDao.searchByExamplePaging(incentiveCriteria, null);
	}

	public IncentiveCriteria addIncentiveCriteria(IncentiveCriteria incentiveCriteria, String batchId)
			throws Exception
	{
		return this.incentiveCriteriaDao.save(incentiveCriteria);
	}

	public IncentiveCriteria updateIncentiveCriteria(IncentiveCriteria incentiveCriteria, String batchId)
			throws Exception
	{
		return this.incentiveCriteriaDao.save(incentiveCriteria);
	}

	public boolean deleteIncentiveCriteria(IncentiveCriteria incentiveCriteria)
			throws Exception
	{
		return this.incentiveCriteriaDao.delete(incentiveCriteria.getId());
	}

}
