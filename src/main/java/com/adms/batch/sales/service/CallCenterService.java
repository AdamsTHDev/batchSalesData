package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.CallCenter;

public interface CallCenterService {

	public List<CallCenter> findCallCenterAll()
			throws Exception;

	public CallCenter findCallCenterById(Long id)
			throws Exception;

	public CallCenter findCallCenterByCallCenterAbbr(String callCenterAbbr)
			throws Exception;

	public List<CallCenter> findCallCenterByExample(CallCenter callCenter)
			throws Exception;

	public List<CallCenter> searchCallCenterByExample(CallCenter callCenter)
			throws Exception;

	public CallCenter addCallCenter(CallCenter callCenter, String batchId)
			throws Exception;

	public CallCenter updateCallCenter(CallCenter callCenter, String batchId)
			throws Exception;

	public boolean deleteCallCenter(CallCenter callCenter)
			throws Exception;

}
