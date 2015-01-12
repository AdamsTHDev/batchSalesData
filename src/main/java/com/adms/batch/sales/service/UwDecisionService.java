package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.UwDecision;

public interface UwDecisionService {

	public List<UwDecision> findUwDecisionAll()
			throws Exception;

	public UwDecision findUwDecisionById(Long id)
			throws Exception;

	public UwDecision findUwDecisionByDecision(String uwDecision)
			throws Exception;

	public List<UwDecision> findUwDecisionByExample(UwDecision uwDecision)
			throws Exception;

	public List<UwDecision> searchUwDecisionByExample(UwDecision uwDecision)
			throws Exception;

	public UwDecision addUwDecision(UwDecision uwDecision, String batchId)
			throws Exception;

	public UwDecision updateUwDecision(UwDecision uwDecision, String batchId)
			throws Exception;

	public boolean deleteUwDecision(UwDecision uwDecision)
			throws Exception;

}
