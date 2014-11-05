package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.TsrPositionDao;
import com.adms.batch.sales.domain.TsrPosition;
import com.adms.batch.sales.service.TsrPositionService;

@Service("tsrPositionService")
@Transactional
public class TsrPositionServiceImpl implements TsrPositionService {

	@Autowired
	private TsrPositionDao tsrPositionDao;

	public void setTsrPositionDao(TsrPositionDao tsrPositionDao)
	{
		this.tsrPositionDao = tsrPositionDao;
	}

	public List<TsrPosition> findTsrPositionAll()
			throws Exception
	{
		return this.tsrPositionDao.findAll();
	}

	public TsrPosition findTsrPositionById(Long id)
			throws Exception
	{
		return this.tsrPositionDao.find(id);
	}

	public TsrPosition findTsrPositionByPositionCode(String positionCode)
			throws Exception
	{
		TsrPosition example = new TsrPosition();
		example.setPositionCode(positionCode);
		example.setIsActive("Y");

		List<TsrPosition> tsrPositionList = this.tsrPositionDao.find(example);

		if (tsrPositionList.size() == 0)
		{
			throw new Exception("not found any record for PositionCode[" + positionCode + "]");
		}

		if (tsrPositionList.size() > 1)
		{
			throw new Exception("more that 1 record found for PositionCode[" + positionCode + "]");
		}

		return tsrPositionList.get(0);
	}

	public TsrPosition findTsrPositionByPositionName(String positionName)
			throws Exception
	{
		TsrPosition example = new TsrPosition();
		example.setPositionName(positionName);
		example.setIsActive("Y");

		List<TsrPosition> tsrPositionList = this.tsrPositionDao.find(example);

		if (tsrPositionList.size() == 0)
		{
			throw new Exception("not found any record for PositionName[" + positionName + "]");
		}

		if (tsrPositionList.size() > 1)
		{
			throw new Exception("more that 1 record found for PositionName[" + positionName + "]");
		}

		return tsrPositionList.get(0);
	}

	public List<TsrPosition> findTsrPositionByExample(TsrPosition tsrPosition)
			throws Exception
	{
		return this.tsrPositionDao.findByExamplePaging(tsrPosition, null);
	}

	public List<TsrPosition> searchTsrPositionByExample(TsrPosition tsrPosition)
			throws Exception
	{
		return this.tsrPositionDao.searchByExamplePaging(tsrPosition, null);
	}

	public TsrPosition addTsrPosition(TsrPosition tsrPosition, String batchId)
			throws Exception
	{
		return this.tsrPositionDao.save(tsrPosition);
	}

	public TsrPosition updateTsrPosition(TsrPosition tsrPosition, String batchId)
			throws Exception
	{
		return this.tsrPositionDao.save(tsrPosition);
	}

	public boolean deleteTsrPosition(TsrPosition tsrPosition)
			throws Exception
	{
		return this.tsrPositionDao.delete(tsrPosition.getId());
	}

}
