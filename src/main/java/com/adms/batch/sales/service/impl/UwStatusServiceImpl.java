package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.UwStatusDao;
import com.adms.batch.sales.domain.UwStatus;
import com.adms.batch.sales.service.UwStatusService;

@Service("uwStatusService")
@Transactional
public class UwStatusServiceImpl implements UwStatusService {

	@Autowired
	private UwStatusDao uwStatusDao;

	public void setUwStatusDao(UwStatusDao uwStatusDao)
	{
		this.uwStatusDao = uwStatusDao;
	}

	public List<UwStatus> findUwStatusAll()
			throws Exception
	{
		return this.uwStatusDao.findAll();
	}

	public UwStatus findUwStatusById(Long id)
			throws Exception
	{
		return this.uwStatusDao.find(id);
	}

	public UwStatus findUwStatusByStatus(String uwStatus)
			throws Exception
	{
		UwStatus example = new UwStatus();
		example.setUwStatus(uwStatus);

		List<UwStatus> uwStatusList = this.uwStatusDao.findByExamplePaging(example, null);

		if (uwStatusList.size() == 0)
		{
			return null;
		}

		if (uwStatusList.size() > 1)
		{
			throw new Exception("more than 1 UwStatus record found for uwStatus[" + uwStatus + "]");
		}

		return uwStatusList.get(0);
	}

	public UwStatus findUwStatusByDescription(String description)
			throws Exception
	{
		UwStatus example = new UwStatus();
		example.setDescription(description);

		List<UwStatus> uwStatusList = this.uwStatusDao.findByExamplePaging(example, null);

		if (uwStatusList.size() == 0)
		{
			return null;
		}

		if (uwStatusList.size() > 1)
		{
			throw new Exception("more than 1 UwStatus record found for description[" + description + "]");
		}

		return uwStatusList.get(0);
	}

	public List<UwStatus> findUwStatusByExample(UwStatus uwStatus)
			throws Exception
	{
		return this.uwStatusDao.findByExamplePaging(uwStatus, null);
	}

	public List<UwStatus> searchUwStatusByExample(UwStatus uwStatus)
			throws Exception
	{
		return this.uwStatusDao.searchByExamplePaging(uwStatus, null);
	}

	public UwStatus addUwStatus(UwStatus uwStatus, String batchId)
			throws Exception
	{
		return this.uwStatusDao.save(uwStatus);
	}

	public UwStatus updateUwStatus(UwStatus uwStatus, String batchId)
			throws Exception
	{
		return this.uwStatusDao.save(uwStatus);
	}

	public boolean deleteUwStatus(UwStatus uwStatus)
			throws Exception
	{
		return this.uwStatusDao.delete(uwStatus.getId());
	}

}
