package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.IncentiveInfoDao;
import com.adms.batch.sales.domain.IncentiveInfo;
import com.adms.batch.sales.service.IncentiveInfoService;

@Service("incentiveInfoService")
@Transactional
public class IncentiveInfoServiceImpl implements IncentiveInfoService {

	@Autowired
	private IncentiveInfoDao incentiveInfoDao;

	public void setIncentiveInfoDao(IncentiveInfoDao incentiveInfoDao)
	{
		this.incentiveInfoDao = incentiveInfoDao;
	}

	public List<IncentiveInfo> findIncentiveInfoAll()
			throws Exception
	{
		return this.incentiveInfoDao.findAll();
	}

	public IncentiveInfo findIncentiveInfoById(Long id)
			throws Exception
	{
		return this.incentiveInfoDao.find(id);
	}

	public IncentiveInfo findIncentiveInfoByIncentiveCode(String incentiveCode)
			throws Exception
	{
		IncentiveInfo example = new IncentiveInfo();
		example.setIncentiveCode(incentiveCode);
		example.setIsActive("Y");

		List<IncentiveInfo> incentiveInfoList = this.incentiveInfoDao.find(example);

		if (incentiveInfoList.size() == 0)
		{
			return null;
//			throw new Exception("not found any record for IncentiveInfoCode[" + incentiveInfoCode + "]");
		}

		if (incentiveInfoList.size() > 1)
		{
			throw new Exception("more that 1 record found for incentiveCode[" + incentiveCode + "]");
		}

		return incentiveInfoList.get(0);
	}

	public List<IncentiveInfo> findIncentiveInfoByExample(IncentiveInfo incentiveInfo)
			throws Exception
	{
		return this.incentiveInfoDao.findByExamplePaging(incentiveInfo, null);
	}

	public List<IncentiveInfo> searchIncentiveInfoByExample(IncentiveInfo incentiveInfo)
			throws Exception
	{
		return this.incentiveInfoDao.searchByExamplePaging(incentiveInfo, null);
	}

	public IncentiveInfo addIncentiveInfo(IncentiveInfo incentiveInfo, String batchId)
			throws Exception
	{
		return this.incentiveInfoDao.save(incentiveInfo);
	}

	public IncentiveInfo updateIncentiveInfo(IncentiveInfo incentiveInfo, String batchId)
			throws Exception
	{
		return this.incentiveInfoDao.save(incentiveInfo);
	}

	public boolean deleteIncentiveInfo(IncentiveInfo incentiveInfo)
			throws Exception
	{
		return this.incentiveInfoDao.delete(incentiveInfo.getId());
	}

}
