package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.UwResultDao;
import com.adms.batch.sales.domain.UwResult;
import com.adms.batch.sales.service.UwResultService;

@Service("uwResultService")
@Transactional
public class UwResultServiceImpl implements UwResultService {

	@Autowired
	private UwResultDao uwResultDao;

	public void setUwResultDao(UwResultDao uwResultDao)
	{
		this.uwResultDao = uwResultDao;
	}

	public List<UwResult> findUwResultAll()
			throws Exception
	{
		return this.uwResultDao.findAll();
	}

	public UwResult findUwResultById(Long id)
			throws Exception
	{
		return this.uwResultDao.find(id);
	}

	public UwResult findUwResultByxReference(String xReference)
			throws Exception
	{
		List<UwResult> uwResultList = this.uwResultDao.findByNamedQuery("findUwResultByxReference", xReference);

		if (uwResultList.size() == 0)
		{
			return null;
		}

		if (uwResultList.size() > 1)
		{
			throw new Exception("more than 1 UwResult record found for xReference[" + xReference + "]");
		}

		return uwResultList.get(0);
	}

	public List<UwResult> findUwResultByExample(UwResult uwResult)
			throws Exception
	{
		return this.uwResultDao.findByExamplePaging(uwResult, null);
	}

	public List<UwResult> searchUwResultByExample(UwResult uwResult)
			throws Exception
	{
		return this.uwResultDao.searchByExamplePaging(uwResult, null);
	}

	public UwResult addUwResult(UwResult uwResult, String batchId)
			throws Exception
	{
		return this.uwResultDao.save(uwResult);
	}

	public UwResult updateUwResult(UwResult uwResult, String batchId)
			throws Exception
	{
		return this.uwResultDao.save(uwResult);
	}

	public boolean deleteUwResult(UwResult uwResult)
			throws Exception
	{
		return this.uwResultDao.delete(uwResult.getId());
	}

}
