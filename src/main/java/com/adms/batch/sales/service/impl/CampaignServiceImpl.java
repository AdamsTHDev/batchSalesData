package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.CampaignDao;
import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.service.CampaignService;

@Service("campaignService")
@Transactional
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	private CampaignDao campaignDao;

	public void setCampaignDao(CampaignDao campaignDao)
	{
		this.campaignDao = campaignDao;
	}

	public List<Campaign> findCampaignAll()
			throws Exception
	{
		return this.campaignDao.findAll();
	}

	public Campaign findCampaignById(Long id)
			throws Exception
	{
		return this.campaignDao.find(id);
	}

	public Campaign findCampaignByCampaignCode(String campaignCode)
			throws Exception
	{
		Campaign example = new Campaign();
		example.setCampaignCode(campaignCode);
		example.setIsActive("Y");

		List<Campaign> campaignList = this.campaignDao.find(example);

		if (campaignList.size() == 0)
		{
			return null;
//			throw new Exception("not found any record for CampaignCode[" + campaignCode + "]");
		}

		if (campaignList.size() > 1)
		{
			throw new Exception("more that 1 record found for CampaignCode[" + campaignCode + "]");
		}

		return campaignList.get(0);
	}

	public List<Campaign> findCampaignByExample(Campaign campaign)
			throws Exception
	{
		return this.campaignDao.findByExamplePaging(campaign, null);
	}

	public List<Campaign> searchCampaignByExample(Campaign campaign)
			throws Exception
	{
		return this.campaignDao.searchByExamplePaging(campaign, null);
	}

	public Campaign addCampaign(Campaign campaign, String batchId)
			throws Exception
	{
		return this.campaignDao.save(campaign);
	}

	public Campaign updateCampaign(Campaign campaign, String batchId)
			throws Exception
	{
		return this.campaignDao.save(campaign);
	}

	public boolean deleteCampaign(Campaign campaign)
			throws Exception
	{
		return this.campaignDao.delete(campaign.getId());
	}

}
