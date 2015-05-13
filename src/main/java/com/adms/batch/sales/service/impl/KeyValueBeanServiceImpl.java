package com.adms.batch.sales.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.KeyValueBeanDao;
import com.adms.batch.sales.domain.KeyValueBean;
import com.adms.batch.sales.service.KeyValueBeanService;

@Service("keyValueBeanService")
@Transactional
public class KeyValueBeanServiceImpl implements KeyValueBeanService {

	@Autowired
	private KeyValueBeanDao keyValueBeanDao;

	public KeyValueBean findQcReconfirmMaxPrintDate(String campaignName)
			throws Exception
	{
		List<KeyValueBean> list = this.keyValueBeanDao.findByNamedQuery("findQcReconfirmMaxPrintDate", campaignName);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public KeyValueBean findTsrTrackingMaxPrintDate(String campaignName)
			throws Exception
	{
		List<KeyValueBean> list = this.keyValueBeanDao.findByNamedQuery("findTsrTrackingMaxPrintDate", campaignName);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public List<KeyValueBean> findNamedQuery(String queryName, String ... param)
			throws Exception
	{
		List<KeyValueBean> list = this.keyValueBeanDao.findByNamedQuery(queryName, param);
		return CollectionUtils.isEmpty(list) ? null : list;
	}

}
