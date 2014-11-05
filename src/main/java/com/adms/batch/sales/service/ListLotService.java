package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.ListLot;

public interface ListLotService {

	public List<ListLot> findListLotAll()
			throws Exception;

	public ListLot findListLotById(Long id)
			throws Exception;

	public ListLot findListLotByListLotCode(String listLotCode)
			throws Exception;

	public List<ListLot> findListLotByExample(ListLot listLot)
			throws Exception;

	public List<ListLot> searchListLotByExample(ListLot listLot)
			throws Exception;

	public ListLot addListLot(ListLot listLot, String batchId)
			throws Exception;

	public ListLot updateListLot(ListLot listLot, String batchId)
			throws Exception;

	public boolean deleteListLot(ListLot listLot)
			throws Exception;

}
