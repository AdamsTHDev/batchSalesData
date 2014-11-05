package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.ListSourceDao;
import com.adms.batch.sales.domain.ListSource;
import com.adms.batch.sales.service.ListSourceService;

@Service("listSourceService")
@Transactional
public class ListSourceServiceImpl implements ListSourceService {

	@Autowired
	private ListSourceDao listSourceDao;

	public void setListSourceDao(ListSourceDao listSourceDao)
	{
		this.listSourceDao = listSourceDao;
	}

	public List<ListSource> findListSourceAll()
			throws Exception
	{
		return this.listSourceDao.findAll();
	}

	public ListSource findListSourceById(Long id)
			throws Exception
	{
		return this.listSourceDao.find(id);
	}

	public ListSource findListSourceByListSourceAbbr(String listSourceAbbr)
			throws Exception
	{
		ListSource example = new ListSource();
		example.setListSourceAbbr(listSourceAbbr);

		List<ListSource> listSourceList = this.listSourceDao.find(example);

		if (listSourceList.size() == 0)
		{
			return null;
		}

		if (listSourceList.size() > 1)
		{
			throw new Exception("more that 1 record found for listSourceAbbr[" + listSourceAbbr + "]");
		}

		return listSourceList.get(0);
	}

	public List<ListSource> findListSourceByExample(ListSource listSource)
			throws Exception
	{
		return this.listSourceDao.findByExamplePaging(listSource, null);
	}

	public List<ListSource> searchListSourceByExample(ListSource listSource)
			throws Exception
	{
		return this.listSourceDao.searchByExamplePaging(listSource, null);
	}

	public ListSource addListSource(ListSource listSource, String batchId)
			throws Exception
	{
		return this.listSourceDao.save(listSource);
	}

	public ListSource updateListSource(ListSource listSource, String batchId)
			throws Exception
	{
		return this.listSourceDao.save(listSource);
	}

	public boolean deleteListSource(ListSource listSource)
			throws Exception
	{
		return this.listSourceDao.delete(listSource.getId());
	}

}
