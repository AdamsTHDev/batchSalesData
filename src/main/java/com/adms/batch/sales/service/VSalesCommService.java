package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.VSalesComm;

public interface VSalesCommService {

	public List<VSalesComm> findSaleCommByCampaignName(String campaignName)
			throws Exception;

}
