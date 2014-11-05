package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.TsrStatus;

public interface TsrStatusService {

	public List<TsrStatus> findTsrStatusAll()
			throws Exception;

	public TsrStatus findTsrStatusById(Long id)
			throws Exception;

	public TsrStatus findTsrStatusByStatusCode(String statusCode)
			throws Exception;

	public List<TsrStatus> findTsrStatusByExample(TsrStatus tsrStatus)
			throws Exception;

	public List<TsrStatus> searchTsrStatusByExample(TsrStatus tsrStatus)
			throws Exception;

	public TsrStatus addTsrStatus(TsrStatus tsrStatus, String batchId)
			throws Exception;

	public TsrStatus updateTsrStatus(TsrStatus tsrStatus, String batchId)
			throws Exception;

	public boolean deleteTsrStatus(TsrStatus tsrStatus)
			throws Exception;

}
