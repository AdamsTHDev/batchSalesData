package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.ListLotDao;
import com.adms.batch.sales.domain.ListLot;
import com.adms.batch.sales.service.ListLotService;

@Service("listLotService")
@Transactional
public class ListLotServiceImpl implements ListLotService {

	@Autowired
	private ListLotDao listLotDao;

	public void setListLotDao(ListLotDao listLotDao)
	{
		this.listLotDao = listLotDao;
	}

	public List<ListLot> findListLotAll()
			throws Exception
	{
		return this.listLotDao.findAll();
	}

	public ListLot findListLotById(Long id)
			throws Exception
	{
		return this.listLotDao.find(id);
	}

	public ListLot findListLotByListLotCode(String listLotCode)
			throws Exception
	{
		ListLot example = new ListLot();
		example.setListLotCode(listLotCode);

		List<ListLot> listLotList = this.listLotDao.findByExamplePaging(example, null);

		if (listLotList.size() == 0)
		{
			return null;
//			throw new Exception("not found any record for ListLotCode[" + listLotCode + "]");
		}

		if (listLotList.size() > 1)
		{
			throw new Exception("more that 1 record found for ListLotCode[" + listLotCode + "]");
		}

		return listLotList.get(0);
	}

	public List<ListLot> findListLotByExample(ListLot listLot)
			throws Exception
	{
		return this.listLotDao.findByExamplePaging(listLot, null);
	}

	public List<ListLot> searchListLotByExample(ListLot listLot)
			throws Exception
	{
		return this.listLotDao.searchByExamplePaging(listLot, null);
	}

	public ListLot addListLot(ListLot listLot, String batchId)
			throws Exception
	{
		return this.listLotDao.save(listLot);
	}

	public ListLot updateListLot(ListLot listLot, String batchId)
			throws Exception
	{
		return this.listLotDao.save(listLot);
	}

	public boolean deleteListLot(ListLot listLot)
			throws Exception
	{
		return this.listLotDao.delete(listLot.getId());
	}

}
