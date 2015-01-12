package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.EocCallOutcome;

public interface EocCallOutcomeService {

	public List<EocCallOutcome> findEocCallOutcomeAll()
			throws Exception;

	public EocCallOutcome findEocCallOutcomeById(Long id)
			throws Exception;

	public List<EocCallOutcome> findEocCallOutcomeByOutcomeCode(String outcomeCode)
			throws Exception;

	public List<EocCallOutcome> findEocCallOutcomeByExample(EocCallOutcome eocCallOutcome)
			throws Exception;

	public List<EocCallOutcome> searchEocCallOutcomeByExample(EocCallOutcome eocCallOutcome)
			throws Exception;

	public EocCallOutcome addEocCallOutcome(EocCallOutcome eocCallOutcome, String batchId)
			throws Exception;

	public EocCallOutcome updateEocCallOutcome(EocCallOutcome eocCallOutcome, String batchId)
			throws Exception;

	public boolean deleteEocCallOutcome(EocCallOutcome eocCallOutcome)
			throws Exception;

	public List<EocCallOutcome> findSydneyEocCallOutcomeBySupCode(String supCode)
			throws Exception;

	public List<EocCallOutcome> findSydneyEocCallOutcomeBySupCodeAndOutcomeCode(String supCode, String outcomeCode)
			throws Exception;

}
