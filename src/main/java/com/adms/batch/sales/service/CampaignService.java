package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.Campaign;

public interface CampaignService {

	public List<Campaign> findCampaignAll()
			throws Exception;

	public Campaign findCampaignById(Long id)
			throws Exception;

	public Campaign findCampaignByCampaignCode(String campaignCode)
			throws Exception;

	public List<Campaign> findCampaignByExample(Campaign campaign)
			throws Exception;

	public List<Campaign> searchCampaignByExample(Campaign campaign)
			throws Exception;

	public Campaign addCampaign(Campaign campaign, String batchId)
			throws Exception;

	public Campaign updateCampaign(Campaign campaign, String batchId)
			throws Exception;

	public boolean deleteCampaign(Campaign campaign)
			throws Exception;

}
