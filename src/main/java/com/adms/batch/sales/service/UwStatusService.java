package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.UwStatus;

public interface UwStatusService {

	public List<UwStatus> findUwStatusAll()
			throws Exception;

	public UwStatus findUwStatusById(Long id)
			throws Exception;

	public UwStatus findUwStatusByStatus(String uwStatus)
			throws Exception;

	public UwStatus findUwStatusByDescription(String uwDescription)
			throws Exception;

	public List<UwStatus> findUwStatusByExample(UwStatus uwStatus)
			throws Exception;

	public List<UwStatus> searchUwStatusByExample(UwStatus uwStatus)
			throws Exception;

	public UwStatus addUwStatus(UwStatus uwStatus, String batchId)
			throws Exception;

	public UwStatus updateUwStatus(UwStatus uwStatus, String batchId)
			throws Exception;

	public boolean deleteUwStatus(UwStatus uwStatus)
			throws Exception;

}
