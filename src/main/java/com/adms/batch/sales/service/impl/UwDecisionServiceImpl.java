package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.UwDecisionDao;
import com.adms.batch.sales.domain.UwDecision;
import com.adms.batch.sales.service.UwDecisionService;

@Service("uwDecisionService")
@Transactional
public class UwDecisionServiceImpl implements UwDecisionService {

	@Autowired
	private UwDecisionDao uwDecisionDao;

	public void setUwDecisionDao(UwDecisionDao uwDecisionDao)
	{
		this.uwDecisionDao = uwDecisionDao;
	}

	public List<UwDecision> findUwDecisionAll()
			throws Exception
	{
		return this.uwDecisionDao.findAll();
	}

	public UwDecision findUwDecisionById(Long id)
			throws Exception
	{
		return this.uwDecisionDao.find(id);
	}

	public UwDecision findUwDecisionByDecision(String uwDecision)
			throws Exception
	{
		UwDecision example = new UwDecision();
		example.setUwDecision(uwDecision);

		List<UwDecision> uwDecisionList = this.uwDecisionDao.findByExamplePaging(example, null);

		if (uwDecisionList.size() == 0)
		{
			return null;
		}

		if (uwDecisionList.size() > 1)
		{
			throw new Exception("more than 1 UwDecision record found for uwDecision[" + uwDecision + "]");
		}

		return uwDecisionList.get(0);
	}

	public List<UwDecision> findUwDecisionByExample(UwDecision uwDecision)
			throws Exception
	{
		return this.uwDecisionDao.findByExamplePaging(uwDecision, null);
	}

	public List<UwDecision> searchUwDecisionByExample(UwDecision uwDecision)
			throws Exception
	{
		return this.uwDecisionDao.searchByExamplePaging(uwDecision, null);
	}

	public UwDecision addUwDecision(UwDecision uwDecision, String batchId)
			throws Exception
	{
		return this.uwDecisionDao.save(uwDecision);
	}

	public UwDecision updateUwDecision(UwDecision uwDecision, String batchId)
			throws Exception
	{
		return this.uwDecisionDao.save(uwDecision);
	}

	public boolean deleteUwDecision(UwDecision uwDecision)
			throws Exception
	{
		return this.uwDecisionDao.delete(uwDecision.getId());
	}

}
