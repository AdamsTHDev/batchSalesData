package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.EocCallOutcomeDao;
import com.adms.batch.sales.domain.EocCallOutcome;
import com.adms.batch.sales.service.EocCallOutcomeService;

@Service("eocCallOutcomeService")
@Transactional
public class EocCallOutcomeServiceImpl implements EocCallOutcomeService {

	@Autowired
	private EocCallOutcomeDao eocCallOutcomeDao;

	public void setEocCallOutcomeDao(EocCallOutcomeDao eocCallOutcomeDao)
	{
		this.eocCallOutcomeDao = eocCallOutcomeDao;
	}

	public List<EocCallOutcome> findEocCallOutcomeAll()
			throws Exception
	{
		return this.eocCallOutcomeDao.findAll();
	}

	public EocCallOutcome findEocCallOutcomeById(Long id)
			throws Exception
	{
		return this.eocCallOutcomeDao.find(id);
	}

	public List<EocCallOutcome> findEocCallOutcomeByOutcomeCode(String outcomeCode)
			throws Exception
	{
		EocCallOutcome example = new EocCallOutcome();
		example.setOutcomeCode(outcomeCode);

		return this.eocCallOutcomeDao.find(example);
	}

	public List<EocCallOutcome> findEocCallOutcomeByExample(EocCallOutcome eocCallOutcome)
			throws Exception
	{
		return this.eocCallOutcomeDao.findByExamplePaging(eocCallOutcome, null);
	}

	public List<EocCallOutcome> searchEocCallOutcomeByExample(EocCallOutcome eocCallOutcome)
			throws Exception
	{
		return this.eocCallOutcomeDao.searchByExamplePaging(eocCallOutcome, null);
	}

	public EocCallOutcome addEocCallOutcome(EocCallOutcome eocCallOutcome, String batchId)
			throws Exception
	{
		return this.eocCallOutcomeDao.save(eocCallOutcome);
	}

	public EocCallOutcome updateEocCallOutcome(EocCallOutcome eocCallOutcome, String batchId)
			throws Exception
	{
		return this.eocCallOutcomeDao.save(eocCallOutcome);
	}

	public boolean deleteEocCallOutcome(EocCallOutcome eocCallOutcome)
			throws Exception
	{
		return this.eocCallOutcomeDao.delete(eocCallOutcome.getId());
	}

	public List<EocCallOutcome> findSydneyEocCallOutcomeBySupCode(String supCode)
			throws Exception
	{
		return this.eocCallOutcomeDao.findByNamedQuery("findSydneyEocCallOutcomeBySupCode", supCode);
	}

	public List<EocCallOutcome> findSydneyEocCallOutcomeBySupCodeAndOutcomeCode(String supCode, String outcomeCode)
			throws Exception
	{
		return this.eocCallOutcomeDao.findByNamedQuery("findSydneyEocCallOutcomeBySupCodeAndOutcomeCode", supCode, outcomeCode);
	}

	public Long countSydneyEocCallOutcomeBySupCode(String supCode)
			throws Exception
	{
		return this.eocCallOutcomeDao.countByNamedQuery("countSydneyEocCallOutcomeBySupCode", supCode);
	}

	public Long countSydneyEocCallOutcomeBySupCodeAndOutcomeCode(String supCode, String outcomeCode)
			throws Exception
	{
		return this.eocCallOutcomeDao.countByNamedQuery("countSydneyEocCallOutcomeBySupCodeAndOutcomeCode", supCode, outcomeCode);
	}

}
