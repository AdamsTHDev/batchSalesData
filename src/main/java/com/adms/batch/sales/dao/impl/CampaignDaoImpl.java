package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.CampaignDao;
import com.adms.batch.sales.domain.Campaign;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("campaignDao")
public class CampaignDaoImpl extends GenericDaoHibernate<Campaign, Long> implements CampaignDao {

	public CampaignDaoImpl() {
		super(Campaign.class);
	}

}
