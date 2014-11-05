package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.IncentiveInfo;

public interface IncentiveInfoService {

	public List<IncentiveInfo> findIncentiveInfoAll()
			throws Exception;

	public IncentiveInfo findIncentiveInfoById(Long id)
			throws Exception;

	public IncentiveInfo findIncentiveInfoByIncentiveCode(String incentiveCode)
			throws Exception;

	public List<IncentiveInfo> findIncentiveInfoByExample(IncentiveInfo incentiveInfo)
			throws Exception;

	public List<IncentiveInfo> searchIncentiveInfoByExample(IncentiveInfo incentiveInfo)
			throws Exception;

	public IncentiveInfo addIncentiveInfo(IncentiveInfo incentiveInfo, String batchId)
			throws Exception;

	public IncentiveInfo updateIncentiveInfo(IncentiveInfo incentiveInfo, String batchId)
			throws Exception;

	public boolean deleteIncentiveInfo(IncentiveInfo incentiveInfo)
			throws Exception;

}
