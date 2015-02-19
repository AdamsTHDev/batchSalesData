package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.DdopMappingResult;

public interface DdopMappingResultService {

	public List<DdopMappingResult> findDdopMappingResultAll()
			throws Exception;

	public DdopMappingResult findDdopMappingResultById(Long id)
			throws Exception;

	public List<DdopMappingResult> findDdopMappingResultByxReference(String xReference)
			throws Exception;

	public DdopMappingResult findDdopMappingResultByxRefAndBatchDate(String xReference, Date batchDate)
			throws Exception;

	public List<DdopMappingResult> findDdopMappingResultByExample(DdopMappingResult ddopMappingResult)
			throws Exception;

	public List<DdopMappingResult> searchDdopMappingResultByExample(DdopMappingResult ddopMappingResult)
			throws Exception;

	public DdopMappingResult addDdopMappingResult(DdopMappingResult ddopMappingResult, String batchId)
			throws Exception;

	public DdopMappingResult updateDdopMappingResult(DdopMappingResult ddopMappingResult, String batchId)
			throws Exception;

	public boolean deleteDdopMappingResult(DdopMappingResult ddopMappingResult)
			throws Exception;

}
