package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.KeyValueBean;

public interface KeyValueBeanService {

	public KeyValueBean findQcReconfirmMaxPrintDate(String campaignName)
			throws Exception;

	public List<KeyValueBean> findNamedQuery(String queryName, String ... param)
			throws Exception;

}
