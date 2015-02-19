package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.VSalesCommDao;
import com.adms.batch.sales.domain.VSalesComm;
import com.adms.batch.sales.service.VSalesCommService;

@Service("vSalesCommService")
@Transactional
public class VSalesCommServiceImpl implements VSalesCommService {

	@Autowired
	private VSalesCommDao vSalesCommDao;

	public List<VSalesComm> findSaleCommByCampaignName(String campaignName)
			throws Exception
	{
		List<VSalesComm> vSalesCommList = this.vSalesCommDao.findByNamedQuery("findSaleCommByCampaignName", campaignName);

		return vSalesCommList.size() > 0 ? vSalesCommList : null;
	}

}
