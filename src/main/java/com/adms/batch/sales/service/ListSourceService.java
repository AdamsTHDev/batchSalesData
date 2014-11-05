package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.ListSource;

public interface ListSourceService {

	public List<ListSource> findListSourceAll()
			throws Exception;

	public ListSource findListSourceById(Long id)
			throws Exception;

	public ListSource findListSourceByListSourceAbbr(String listSourceAbbr)
			throws Exception;

	public List<ListSource> findListSourceByExample(ListSource listSource)
			throws Exception;

	public List<ListSource> searchListSourceByExample(ListSource listSource)
			throws Exception;

	public ListSource addListSource(ListSource listSource, String batchId)
			throws Exception;

	public ListSource updateListSource(ListSource listSource, String batchId)
			throws Exception;

	public boolean deleteListSource(ListSource listSource)
			throws Exception;

}
