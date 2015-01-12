package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.UwResult;

public interface UwResultService {

	public List<UwResult> findUwResultAll()
			throws Exception;

	public UwResult findUwResultById(Long id)
			throws Exception;

	public UwResult findUwResultByxReference(String xReference)
			throws Exception;

	public List<UwResult> findUwResultByExample(UwResult uwResult)
			throws Exception;

	public List<UwResult> searchUwResultByExample(UwResult uwResult)
			throws Exception;

	public UwResult addUwResult(UwResult uwResult, String batchId)
			throws Exception;

	public UwResult updateUwResult(UwResult uwResult, String batchId)
			throws Exception;

	public boolean deleteUwResult(UwResult uwResult)
			throws Exception;

}
