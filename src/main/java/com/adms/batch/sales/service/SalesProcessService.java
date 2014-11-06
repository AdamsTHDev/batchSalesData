package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.SalesProcess;

public interface SalesProcessService {

	public List<SalesProcess> findSalesProcessAll()
			throws Exception;

	public SalesProcess findSalesProcessById(Long id)
			throws Exception;

	public SalesProcess findSalesProcessByXRefferenceAndStatusDateAndReconfirmStatus(String xReference, Date statusDate, ReconfirmStatus reconfirmStatus)
			throws Exception;

	public List<SalesProcess> findSalesProcessByExample(SalesProcess salesProcess)
			throws Exception;

	public List<SalesProcess> searchSalesProcessByExample(SalesProcess salesProcess)
			throws Exception;

	public SalesProcess addSalesProcess(SalesProcess salesProcess, String batchId)
			throws Exception;

	public SalesProcess updateSalesProcess(SalesProcess salesProcess, String batchId)
			throws Exception;

	public boolean deleteSalesProcess(SalesProcess salesProcess)
			throws Exception;

}
