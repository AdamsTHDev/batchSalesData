package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.IncentiveCriteria;

public interface IncentiveCriteriaService {

	public List<IncentiveCriteria> findIncentiveCriteriaAll()
			throws Exception;

	public IncentiveCriteria findIncentiveCriteriaById(Long id)
			throws Exception;

	public List<IncentiveCriteria> findBySydneyCriteria(String campaignCode, String qaStatus, String qaReason)
			throws Exception;

	public List<IncentiveCriteria> findBySydneyFloorCriteria(String campaignCode, String qaStatus, String qaReason)
			throws Exception;

	public List<IncentiveCriteria> findIncentiveCriteriaByExample(IncentiveCriteria incentiveCriteria)
			throws Exception;

	public List<IncentiveCriteria> searchIncentiveCriteriaByExample(IncentiveCriteria incentiveCriteria)
			throws Exception;

	public IncentiveCriteria addIncentiveCriteria(IncentiveCriteria incentiveCriteria, String batchId)
			throws Exception;

	public IncentiveCriteria updateIncentiveCriteria(IncentiveCriteria incentiveCriteria, String batchId)
			throws Exception;

	public boolean deleteIncentiveCriteria(IncentiveCriteria incentiveCriteria)
			throws Exception;

}
