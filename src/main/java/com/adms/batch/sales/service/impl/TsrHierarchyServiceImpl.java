package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.TsrHierarchyDao;
import com.adms.batch.sales.domain.TsrHierarchy;
import com.adms.batch.sales.service.TsrHierarchyService;

@Service("tsrHierarchyService")
@Transactional
public class TsrHierarchyServiceImpl implements TsrHierarchyService {

	@Autowired
	private TsrHierarchyDao tsrHierarchyDao;

	public TsrHierarchy findTsrHierarchyByTsrAndDate(String tsrCode, Date hierarchyDate)
			throws Exception
	{
		List<TsrHierarchy> tsrHierarchyList = this.tsrHierarchyDao.findByNamedQuery("findTsrHierarchyByTsrAndDate", tsrCode, hierarchyDate, hierarchyDate);

		if (tsrHierarchyList.size() > 1)
		{
			throw new Exception("ERROR!!! found overlapsed TSR hierarchy [tsrCode: " + tsrCode + ", hierarchyDate: " + hierarchyDate + "]");
		}

		return tsrHierarchyList.size() > 0 ? tsrHierarchyList.get(0) : null;
	}

	public TsrHierarchy findTsrHierarchyByTsrAndUplineAndDate(String tsrCode, String uplineCode, Date hierarchyDate)
			throws Exception
	{
		List<TsrHierarchy> tsrHierarchyList = this.tsrHierarchyDao.findByNamedQuery("findTsrHierarchyByTsrAndUplineAndDate", tsrCode, uplineCode, hierarchyDate, hierarchyDate);

		if (tsrHierarchyList.size() > 1)
		{
			throw new Exception("ERROR!!! found overlapsed TSR hierarchy [tsrCode: " + tsrCode + ", uplineCode: " + uplineCode + ", hierarchyDate: " + hierarchyDate + "]");
		}

		return tsrHierarchyList.size() > 0 ? tsrHierarchyList.get(0) : null;
	}

	public TsrHierarchy addTsrHierarchy(TsrHierarchy tsrHierarchy, String batchId)
			throws Exception
	{
		return this.tsrHierarchyDao.save(tsrHierarchy);
	}

	public TsrHierarchy updateTsrHierarchy(TsrHierarchy tsrHierarchy, String batchId)
			throws Exception
	{
		return this.tsrHierarchyDao.save(tsrHierarchy);
	}

	public boolean deleteTsrHierarchy(TsrHierarchy tsrHierarchy)
			throws Exception
	{
		return this.tsrHierarchyDao.delete(tsrHierarchy.getId());
	}

}
