package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.ReconfirmStatusDao;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.service.ReconfirmStatusService;

@Service("reconfirmStatusService")
@Transactional
public class ReconfirmStatusServiceImpl implements ReconfirmStatusService {

	@Autowired
	private ReconfirmStatusDao reconfirmStatusDao;

	public void setReconfirmStatusDao(ReconfirmStatusDao reconfirmStatusDao)
	{
		this.reconfirmStatusDao = reconfirmStatusDao;
	}

	public List<ReconfirmStatus> findReconfirmStatusAll()
			throws Exception
	{
		return this.reconfirmStatusDao.findAll();
	}

	public ReconfirmStatus findReconfirmStatusById(Long id)
			throws Exception
	{
		return this.reconfirmStatusDao.find(id);
	}

	public ReconfirmStatus findReconfirmStatusByReconfirmStatus(String reconfirmStatus)
			throws Exception
	{
		ReconfirmStatus example = new ReconfirmStatus();
		example.setReconfirmStatus(reconfirmStatus);

		List<ReconfirmStatus> reconfirmStatusList = this.reconfirmStatusDao.findByExamplePaging(example, null);

		if (reconfirmStatusList.size() == 0)
		{
			throw new Exception("not found any record for ReconfirmStatus[" + reconfirmStatus + "]");
		}

		if (reconfirmStatusList.size() > 1)
		{
			throw new Exception("more that 1 record found for ReconfirmStatus[" + reconfirmStatus + "]");
		}

		return reconfirmStatusList.get(0);
	}

	public List<ReconfirmStatus> findReconfirmStatusByExample(ReconfirmStatus reconfirmStatus)
			throws Exception
	{
		return this.reconfirmStatusDao.findByExamplePaging(reconfirmStatus, null);
	}

	public List<ReconfirmStatus> searchReconfirmStatusByExample(ReconfirmStatus reconfirmStatus)
			throws Exception
	{
		return this.reconfirmStatusDao.searchByExamplePaging(reconfirmStatus, null);
	}

	public ReconfirmStatus addReconfirmStatus(ReconfirmStatus reconfirmStatus, String batchId)
			throws Exception
	{
		return this.reconfirmStatusDao.save(reconfirmStatus);
	}

	public ReconfirmStatus updateReconfirmStatus(ReconfirmStatus reconfirmStatus, String batchId)
			throws Exception
	{
		return this.reconfirmStatusDao.save(reconfirmStatus);
	}

	public boolean deleteReconfirmStatus(ReconfirmStatus reconfirmStatus)
			throws Exception
	{
		return this.reconfirmStatusDao.delete(reconfirmStatus.getId());
	}

}
