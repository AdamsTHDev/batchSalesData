package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DdopMappingStatus;

public interface DdopMappingStatusService {

	public List<DdopMappingStatus> findDdopMappingStatusAll()
			throws Exception;

	public DdopMappingStatus findDdopMappingStatusById(Long id)
			throws Exception;

	public DdopMappingStatus findDdopMappingStatusByStatus(String ddopMappingStatus)
			throws Exception;

	public DdopMappingStatus findDdopMappingStatusByDescription(String ddopMappingDescription)
			throws Exception;

	public List<DdopMappingStatus> findDdopMappingStatusByExample(DdopMappingStatus ddopMappingStatus)
			throws Exception;

	public List<DdopMappingStatus> searchDdopMappingStatusByExample(DdopMappingStatus ddopMappingStatus)
			throws Exception;

	public DdopMappingStatus addDdopMappingStatus(DdopMappingStatus ddopMappingStatus, String batchId)
			throws Exception;

	public DdopMappingStatus updateDdopMappingStatus(DdopMappingStatus ddopMappingStatus, String batchId)
			throws Exception;

	public boolean deleteDdopMappingStatus(DdopMappingStatus ddopMappingStatus)
			throws Exception;

}
