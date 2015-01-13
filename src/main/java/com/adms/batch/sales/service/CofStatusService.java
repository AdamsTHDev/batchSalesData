package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.CofStatus;

public interface CofStatusService {

	public List<CofStatus> findCofStatusAll()
			throws Exception;

	public CofStatus findCofStatusById(Long id)
			throws Exception;

	public List<CofStatus> findCofStatusByExample(CofStatus cofStatus)
			throws Exception;

	public List<CofStatus> searchCofStatusByExample(CofStatus cofStatus)
			throws Exception;

	public CofStatus addCofStatus(CofStatus cofStatus, String batchId)
			throws Exception;

	public CofStatus updateCofStatus(CofStatus cofStatus, String batchId)
			throws Exception;

	public boolean deleteCofStatus(CofStatus cofStatus)
			throws Exception;

}
