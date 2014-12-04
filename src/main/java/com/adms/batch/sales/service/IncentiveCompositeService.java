package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.IncentiveComposite;

public interface IncentiveCompositeService {

	public List<IncentiveComposite> findIncentiveCompositeAll()
			throws Exception;

	public IncentiveComposite findIncentiveCompositeById(Long id)
			throws Exception;

	public IncentiveComposite findByIncentiveAndCampaignCode(String incentiveCode, String campaignCode)
			throws Exception;

	public IncentiveComposite findByIncentiveAndCompositeName(String incentiveCode, String compositeName)
			throws Exception;

	public List<IncentiveComposite> findIncentiveCompositeByExample(IncentiveComposite incentiveComposite)
			throws Exception;

	public List<IncentiveComposite> searchIncentiveCompositeByExample(IncentiveComposite incentiveComposite)
			throws Exception;

	public IncentiveComposite addIncentiveComposite(IncentiveComposite incentiveComposite, String batchId)
			throws Exception;

	public IncentiveComposite updateIncentiveComposite(IncentiveComposite incentiveComposite, String batchId)
			throws Exception;

	public boolean deleteIncentiveComposite(IncentiveComposite incentiveComposite)
			throws Exception;

}
