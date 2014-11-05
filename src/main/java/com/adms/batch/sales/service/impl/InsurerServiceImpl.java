package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.InsurerDao;
import com.adms.batch.sales.domain.Insurer;
import com.adms.batch.sales.service.InsurerService;

@Service("insurerService")
@Transactional
public class InsurerServiceImpl implements InsurerService {

	@Autowired
	private InsurerDao insurerDao;

	public void setInsurerDao(InsurerDao insurerDao)
	{
		this.insurerDao = insurerDao;
	}

	public List<Insurer> findInsurerAll()
			throws Exception
	{
		return this.insurerDao.findAll();
	}

	public Insurer findInsurerById(Long id)
			throws Exception
	{
		return this.insurerDao.find(id);
	}

	public Insurer findInsurerByInsurerAbbr(String insurerAbbr)
			throws Exception
	{
		Insurer example = new Insurer();
		example.setInsurerAbbr(insurerAbbr);
		example.setIsActive("Y");

		List<Insurer> insurerList = this.insurerDao.find(example);

		if (insurerList.size() == 0)
		{
			return null;
//			throw new Exception("not found any record for InsurerCode[" + insurerCode + "]");
		}

		if (insurerList.size() > 1)
		{
			throw new Exception("more that 1 record found for InsurerAbbr[" + insurerAbbr + "]");
		}

		return insurerList.get(0);
	}

	public List<Insurer> findInsurerByExample(Insurer insurer)
			throws Exception
	{
		return this.insurerDao.findByExamplePaging(insurer, null);
	}

	public List<Insurer> searchInsurerByExample(Insurer insurer)
			throws Exception
	{
		return this.insurerDao.searchByExamplePaging(insurer, null);
	}

	public Insurer addInsurer(Insurer insurer, String batchId)
			throws Exception
	{
		return this.insurerDao.save(insurer);
	}

	public Insurer updateInsurer(Insurer insurer, String batchId)
			throws Exception
	{
		return this.insurerDao.save(insurer);
	}

	public boolean deleteInsurer(Insurer insurer)
			throws Exception
	{
		return this.insurerDao.delete(insurer.getId());
	}

}
