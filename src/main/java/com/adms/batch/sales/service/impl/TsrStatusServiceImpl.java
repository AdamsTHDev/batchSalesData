package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.TsrStatusDao;
import com.adms.batch.sales.domain.TsrStatus;
import com.adms.batch.sales.service.TsrStatusService;

@Service("tsrStatusService")
@Transactional
public class TsrStatusServiceImpl implements TsrStatusService {

	@Autowired
	private TsrStatusDao tsrStatusDao;

	public void setTsrStatusDao(TsrStatusDao tsrStatusDao)
	{
		this.tsrStatusDao = tsrStatusDao;
	}

	public List<TsrStatus> findTsrStatusAll()
			throws Exception
	{
		return this.tsrStatusDao.findAll();
	}

	public TsrStatus findTsrStatusById(Long id)
			throws Exception
	{
		return this.tsrStatusDao.find(id);
	}

	public TsrStatus findTsrStatusByStatusCode(String statusCode)
			throws Exception
	{
		TsrStatus example = new TsrStatus();
		example.setStatusCode(statusCode);

		List<TsrStatus> tsrStatusList = this.tsrStatusDao.find(example);

		if (tsrStatusList.size() == 0)
		{
			throw new Exception("not found any record for statusCode[" + statusCode + "]");
		}

		if (tsrStatusList.size() > 1)
		{
			throw new Exception("more that 1 record found for statusCode[" + statusCode + "]");
		}

		return tsrStatusList.get(0);
	}

	public List<TsrStatus> findTsrStatusByExample(TsrStatus tsrStatus)
			throws Exception
	{
		return this.tsrStatusDao.findByExamplePaging(tsrStatus, null);
	}

	public List<TsrStatus> searchTsrStatusByExample(TsrStatus tsrStatus)
			throws Exception
	{
		return this.tsrStatusDao.searchByExamplePaging(tsrStatus, null);
	}

	public TsrStatus addTsrStatus(TsrStatus tsrStatus, String batchId)
			throws Exception
	{
		return this.tsrStatusDao.save(tsrStatus);
	}

	public TsrStatus updateTsrStatus(TsrStatus tsrStatus, String batchId)
			throws Exception
	{
		return this.tsrStatusDao.save(tsrStatus);
	}

	public boolean deleteTsrStatus(TsrStatus tsrStatus)
			throws Exception
	{
		return this.tsrStatusDao.delete(tsrStatus.getId());
	}

}
