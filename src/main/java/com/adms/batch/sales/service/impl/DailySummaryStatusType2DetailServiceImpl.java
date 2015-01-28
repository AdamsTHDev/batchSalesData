package com.adms.batch.sales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DailySummaryStatusType2DetailDao;
import com.adms.batch.sales.domain.DailySummaryStatusType2Detail;
import com.adms.batch.sales.service.DailySummaryStatusType2DetailService;

@Service("dailySummaryStatusType2DetailService")
@Transactional
public class DailySummaryStatusType2DetailServiceImpl implements DailySummaryStatusType2DetailService {

	@Autowired
	private DailySummaryStatusType2DetailDao dailySummaryStatusType2DetailDao;

	public List<DailySummaryStatusType2Detail> findByCampaignAndProcessDateAndKeyCode(String campaign, String processDate, String keyCode)
			throws Exception
	{
		return this.dailySummaryStatusType2DetailDao.findByNamedQuery("findByCampaignAndProcessDateAndKeyCode", campaign, processDate, keyCode);
	}

	public List<String> findKeyCodeByCampaignAndProcessDate(String campaign, String processDate)
			throws Exception
	{
		List<DailySummaryStatusType2Detail> dailySummaryStatusType2DetailList  = this.dailySummaryStatusType2DetailDao.findByNamedQuery("findKeyCodeByCampaignAndProcessDate", campaign, processDate);

		List<String> keyCodeList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(dailySummaryStatusType2DetailList))
		{
			for (DailySummaryStatusType2Detail dailySummaryStatusType2Detail : dailySummaryStatusType2DetailList)
			{
				if (!keyCodeList.contains(dailySummaryStatusType2Detail.getKeyCode()))
				{
					keyCodeList.add(dailySummaryStatusType2Detail.getKeyCode());
				}
			}
		}
		return keyCodeList;
	}

}
