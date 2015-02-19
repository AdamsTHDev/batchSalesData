package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.CofStatusDao;
import com.adms.batch.sales.domain.CofStatus;
import com.adms.batch.sales.service.CofStatusService;

@Service("cofStatusService")
@Transactional
public class CofStatusServiceImpl implements CofStatusService {

	@Autowired
	private CofStatusDao cofStatusDao;

	public void setCofStatusDao(CofStatusDao cofStatusDao)
	{
		this.cofStatusDao = cofStatusDao;
	}

	public List<CofStatus> findCofStatusAll()
			throws Exception
	{
		return this.cofStatusDao.findAll();
	}

	public CofStatus findCofStatusById(Long id)
			throws Exception
	{
		return this.cofStatusDao.find(id);
	}

	public CofStatus findCofStatusByStatus(String cofStatus)
			throws Exception
	{
		CofStatus example = new CofStatus();
		example.setCofStatus(cofStatus);

		List<CofStatus> cofStatusList = this.cofStatusDao.findByExamplePaging(example, null);

		if (cofStatusList.size() == 0)
		{
			return null;
		}

		if (cofStatusList.size() > 1)
		{
			throw new Exception("more than 1 CofStatus record found for cofStatus[" + cofStatus + "]");
		}

		return cofStatusList.get(0);
	}

	public CofStatus findCofStatusByDescription(String description)
			throws Exception
	{
		CofStatus example = new CofStatus();
		example.setDescription(description);

		List<CofStatus> cofStatusList = this.cofStatusDao.findByExamplePaging(example, null);

		if (cofStatusList.size() == 0)
		{
			return null;
		}

		if (cofStatusList.size() > 1)
		{
			throw new Exception("more than 1 CofStatus record found for description[" + description + "]");
		}

		return cofStatusList.get(0);
	}

	public List<CofStatus> findCofStatusByExample(CofStatus cofStatus)
			throws Exception
	{
		return this.cofStatusDao.findByExamplePaging(cofStatus, null);
	}

	public List<CofStatus> searchCofStatusByExample(CofStatus cofStatus)
			throws Exception
	{
		return this.cofStatusDao.searchByExamplePaging(cofStatus, null);
	}

	public CofStatus addCofStatus(CofStatus cofStatus, String batchId)
			throws Exception
	{
		return this.cofStatusDao.save(cofStatus);
	}

	public CofStatus updateCofStatus(CofStatus cofStatus, String batchId)
			throws Exception
	{
		return this.cofStatusDao.save(cofStatus);
	}

	public boolean deleteCofStatus(CofStatus cofStatus)
			throws Exception
	{
		return this.cofStatusDao.delete(cofStatus.getId());
	}

}
