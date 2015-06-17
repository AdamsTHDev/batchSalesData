package com.adms.batch.sales.service;

import java.util.Date;

import com.adms.batch.sales.domain.TsrHierarchy;

public interface TsrHierarchyService {

	public TsrHierarchy findTsrHierarchyByTsrAndDate(String tsrCode, Date hierarchyDate)
			throws Exception;

	public TsrHierarchy findTsrHierarchyByTsrAndUplineAndDate(String tsrCode, String uplineCode, Date hierarchyDate)
			throws Exception;

	public TsrHierarchy addTsrHierarchy(TsrHierarchy tsrHierarchy, String batchId)
			throws Exception;

	public TsrHierarchy updateTsrHierarchy(TsrHierarchy tsrHierarchy, String batchId)
			throws Exception;

	public boolean deleteTsrHierarchy(TsrHierarchy tsrHierarchy)
			throws Exception;

}
