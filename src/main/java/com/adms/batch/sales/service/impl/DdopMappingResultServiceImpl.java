package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DdopMappingResultDao;
import com.adms.batch.sales.domain.DdopMappingResult;
import com.adms.batch.sales.service.DdopMappingResultService;

@Service("ddopMappingResultService")
@Transactional
public class DdopMappingResultServiceImpl implements DdopMappingResultService {

	@Autowired
	private DdopMappingResultDao ddopMappingResultDao;

	public void setDdopMappingResultDao(DdopMappingResultDao ddopMappingResultDao)
	{
		this.ddopMappingResultDao = ddopMappingResultDao;
	}

	public List<DdopMappingResult> findDdopMappingResultAll()
			throws Exception
	{
		return this.ddopMappingResultDao.findAll();
	}

	public DdopMappingResult findDdopMappingResultById(Long id)
			throws Exception
	{
		return this.ddopMappingResultDao.find(id);
	}

	public List<DdopMappingResult> findDdopMappingResultByxReference(String xReference)
			throws Exception
	{
		List<DdopMappingResult> ddopMappingResultList = this.ddopMappingResultDao.findByNamedQuery("findDdopMappingResultByxReference", xReference);

		return ddopMappingResultList.size() > 0 ? ddopMappingResultList : null;
	}

	public DdopMappingResult findDdopMappingResultByxRefAndBatchDate(String xReference, Date batchDate)
			throws Exception
	{
		List<DdopMappingResult> ddopMappingResultList = this.ddopMappingResultDao.findByNamedQuery("findDdopMappingResultByxRefAndBatchDate", xReference, batchDate);

		return ddopMappingResultList.size() > 0 ? ddopMappingResultList.get(0) : null;
	}

	public List<DdopMappingResult> findDdopMappingResultByExample(DdopMappingResult ddopMappingResult)
			throws Exception
	{
		return this.ddopMappingResultDao.findByExamplePaging(ddopMappingResult, null);
	}

	public List<DdopMappingResult> searchDdopMappingResultByExample(DdopMappingResult ddopMappingResult)
			throws Exception
	{
		return this.ddopMappingResultDao.searchByExamplePaging(ddopMappingResult, null);
	}

	public DdopMappingResult addDdopMappingResult(DdopMappingResult ddopMappingResult, String batchId)
			throws Exception
	{
		return this.ddopMappingResultDao.save(ddopMappingResult);
	}

	public DdopMappingResult updateDdopMappingResult(DdopMappingResult ddopMappingResult, String batchId)
			throws Exception
	{
		return this.ddopMappingResultDao.save(ddopMappingResult);
	}

	public boolean deleteDdopMappingResult(DdopMappingResult ddopMappingResult)
			throws Exception
	{
		return this.ddopMappingResultDao.delete(ddopMappingResult.getId());
	}

}
