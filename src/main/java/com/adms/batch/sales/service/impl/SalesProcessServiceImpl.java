package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.SalesProcessDao;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.SalesProcess;
import com.adms.batch.sales.service.SalesProcessService;

@Service("salesProcessService")
@Transactional
public class SalesProcessServiceImpl implements SalesProcessService {

	@Autowired
	private SalesProcessDao salesProcessDao;

	public void setSalesProcessDao(SalesProcessDao salesProcessDao)
	{
		this.salesProcessDao = salesProcessDao;
	}

	public List<SalesProcess> findSalesProcessAll()
			throws Exception
	{
		return this.salesProcessDao.findAll();
	}

	public SalesProcess findSalesProcessById(Long id)
			throws Exception
	{
		return this.salesProcessDao.find(id);
	}

	public SalesProcess findSalesProcessByXRefferenceAndStatusDateAndReconfirmStatus(String xReference, Date statusDate, ReconfirmStatus reconfirmStatus)
			throws Exception
	{
		List<SalesProcess> salesProcessList = null;

//		if (statusDate == null)
//		{
//			salesProcessList = this.salesProcessDao.findByNamedQuery("findSalesProcessByXRefferenceAndReconfirmStatus", xReference, reconfirmStatus.getReconfirmStatus());
//		}
//		else
//		{
			salesProcessList = this.salesProcessDao.findByNamedQuery("findSalesProcessByXRefferenceAndStatusDateAndReconfirmStatus", xReference, statusDate, reconfirmStatus.getReconfirmStatus());
//		}

		if (salesProcessList.size() > 1)
		{
			throw new Exception("more that 1 record found for xReference[" + xReference + "] and statusDate[" + statusDate + "] and reconfirmStatus[" + reconfirmStatus.getReconfirmStatus() + "]");
		}

		return salesProcessList.size() > 0 ? salesProcessList.get(0) : null;
	}

	public List<SalesProcess> findSalesProcessByExample(SalesProcess salesProcess)
			throws Exception
	{
		return this.salesProcessDao.findByExamplePaging(salesProcess, null);
	}

	public List<SalesProcess> searchSalesProcessByExample(SalesProcess salesProcess)
			throws Exception
	{
		return this.salesProcessDao.searchByExamplePaging(salesProcess, null);
	}

	public SalesProcess addSalesProcess(SalesProcess salesProcess, String batchId)
			throws Exception
	{
		return this.salesProcessDao.save(salesProcess);
	}

	public SalesProcess updateSalesProcess(SalesProcess salesProcess, String batchId)
			throws Exception
	{
		return this.salesProcessDao.save(salesProcess);
	}

	public boolean deleteSalesProcess(SalesProcess salesProcess)
			throws Exception
	{
		return this.salesProcessDao.delete(salesProcess.getId());
	}

}
