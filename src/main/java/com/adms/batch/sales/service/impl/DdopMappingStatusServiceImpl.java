package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DdopMappingStatusDao;
import com.adms.batch.sales.domain.DdopMappingStatus;
import com.adms.batch.sales.service.DdopMappingStatusService;

@Service("ddopMappingStatusService")
@Transactional
public class DdopMappingStatusServiceImpl implements DdopMappingStatusService {

	@Autowired
	private DdopMappingStatusDao ddopMappingStatusDao;

	public void setDdopMappingStatusDao(DdopMappingStatusDao ddopMappingStatusDao)
	{
		this.ddopMappingStatusDao = ddopMappingStatusDao;
	}

	public List<DdopMappingStatus> findDdopMappingStatusAll()
			throws Exception
	{
		return this.ddopMappingStatusDao.findAll();
	}

	public DdopMappingStatus findDdopMappingStatusById(Long id)
			throws Exception
	{
		return this.ddopMappingStatusDao.find(id);
	}

	public DdopMappingStatus findDdopMappingStatusByStatus(String ddopMappingStatus)
			throws Exception
	{
		DdopMappingStatus example = new DdopMappingStatus();
		example.setMappingStatus(ddopMappingStatus);

		List<DdopMappingStatus> ddopMappingStatusList = this.ddopMappingStatusDao.findByExamplePaging(example, null);

		if (ddopMappingStatusList.size() == 0)
		{
			return null;
		}

		if (ddopMappingStatusList.size() > 1)
		{
			throw new Exception("more than 1 DdopMappingStatus record found for ddopMappingStatus[" + ddopMappingStatus + "]");
		}

		return ddopMappingStatusList.get(0);
	}

	public DdopMappingStatus findDdopMappingStatusByDescription(String description)
			throws Exception
	{
		DdopMappingStatus example = new DdopMappingStatus();
		example.setDescription(description);

		List<DdopMappingStatus> ddopMappingStatusList = this.ddopMappingStatusDao.findByExamplePaging(example, null);

		if (ddopMappingStatusList.size() == 0)
		{
			return null;
		}

		if (ddopMappingStatusList.size() > 1)
		{
			throw new Exception("more than 1 DdopMappingStatus record found for description[" + description + "]");
		}

		return ddopMappingStatusList.get(0);
	}

	public List<DdopMappingStatus> findDdopMappingStatusByExample(DdopMappingStatus ddopMappingStatus)
			throws Exception
	{
		return this.ddopMappingStatusDao.findByExamplePaging(ddopMappingStatus, null);
	}

	public List<DdopMappingStatus> searchDdopMappingStatusByExample(DdopMappingStatus ddopMappingStatus)
			throws Exception
	{
		return this.ddopMappingStatusDao.searchByExamplePaging(ddopMappingStatus, null);
	}

	public DdopMappingStatus addDdopMappingStatus(DdopMappingStatus ddopMappingStatus, String batchId)
			throws Exception
	{
		return this.ddopMappingStatusDao.save(ddopMappingStatus);
	}

	public DdopMappingStatus updateDdopMappingStatus(DdopMappingStatus ddopMappingStatus, String batchId)
			throws Exception
	{
		return this.ddopMappingStatusDao.save(ddopMappingStatus);
	}

	public boolean deleteDdopMappingStatus(DdopMappingStatus ddopMappingStatus)
			throws Exception
	{
		return this.ddopMappingStatusDao.delete(ddopMappingStatus.getId());
	}

}
