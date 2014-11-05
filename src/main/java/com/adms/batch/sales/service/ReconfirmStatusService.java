package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.ReconfirmStatus;

public interface ReconfirmStatusService {

	public List<ReconfirmStatus> findReconfirmStatusAll()
			throws Exception;

	public ReconfirmStatus findReconfirmStatusById(Long id)
			throws Exception;

	public ReconfirmStatus findReconfirmStatusByReconfirmStatus(String reconfirmStatus)
			throws Exception;

	public List<ReconfirmStatus> findReconfirmStatusByExample(ReconfirmStatus reconfirmStatus)
			throws Exception;

	public List<ReconfirmStatus> searchReconfirmStatusByExample(ReconfirmStatus reconfirmStatus)
			throws Exception;

	public ReconfirmStatus addReconfirmStatus(ReconfirmStatus reconfirmStatus, String batchId)
			throws Exception;

	public ReconfirmStatus updateReconfirmStatus(ReconfirmStatus reconfirmStatus, String batchId)
			throws Exception;

	public boolean deleteReconfirmStatus(ReconfirmStatus reconfirmStatus)
			throws Exception;

}
