package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.CallCenterDao;
import com.adms.batch.sales.domain.CallCenter;
import com.adms.batch.sales.service.CallCenterService;

@Service("callCenterService")
@Transactional
public class CallCenterServiceImpl implements CallCenterService {

	@Autowired
	private CallCenterDao callCenterDao;

	public void setCallCenterDao(CallCenterDao callCenterDao)
	{
		this.callCenterDao = callCenterDao;
	}

	public List<CallCenter> findCallCenterAll()
			throws Exception
	{
		return this.callCenterDao.findAll();
	}

	public CallCenter findCallCenterById(Long id)
			throws Exception
	{
		return this.callCenterDao.find(id);
	}

	public CallCenter findCallCenterByCallCenterAbbr(String callCenterAbbr)
			throws Exception
	{
		CallCenter example = new CallCenter();
		example.setCallCenterAbbr(callCenterAbbr);
		example.setIsActive("Y");

		List<CallCenter> callCenterList = this.callCenterDao.find(example);

		if (callCenterList.size() == 0)
		{
			return null;
		}

		if (callCenterList.size() > 1)
		{
			throw new Exception("more that 1 record found for callCenterAbbr[" + callCenterAbbr + "]");
		}

		return callCenterList.get(0);
	}

	public List<CallCenter> findCallCenterByExample(CallCenter callCenter)
			throws Exception
	{
		return this.callCenterDao.findByExamplePaging(callCenter, null);
	}

	public List<CallCenter> searchCallCenterByExample(CallCenter callCenter)
			throws Exception
	{
		return this.callCenterDao.searchByExamplePaging(callCenter, null);
	}

	public CallCenter addCallCenter(CallCenter callCenter, String batchId)
			throws Exception
	{
		return this.callCenterDao.save(callCenter);
	}

	public CallCenter updateCallCenter(CallCenter callCenter, String batchId)
			throws Exception
	{
		return this.callCenterDao.save(callCenter);
	}

	public boolean deleteCallCenter(CallCenter callCenter)
			throws Exception
	{
		return this.callCenterDao.delete(callCenter.getId());
	}

}
