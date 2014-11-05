package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.TsrDao;
import com.adms.batch.sales.domain.Tsr;
import com.adms.batch.sales.service.TsrService;

@Service("tsrService")
@Transactional
public class TsrServiceImpl implements TsrService {

	@Autowired
	private TsrDao tsrDao;

	public void setTsrDao(TsrDao tsrDao)
	{
		this.tsrDao = tsrDao;
	}

	public List<Tsr> findTsrAll()
			throws Exception
	{
		return this.tsrDao.findAll();
	}

	public Tsr findTsrById(Long id)
			throws Exception
	{
		return this.tsrDao.find(id);
	}

	public Tsr findTsrByTsrCode(String tsrCode)
			throws Exception
	{
		Tsr example = new Tsr();
		example.setTsrCode(tsrCode);

		List<Tsr> tsrList = this.tsrDao.findByExamplePaging(example, null);

		if (tsrList.size() == 0)
		{
			return null;
		}

		if (tsrList.size() > 1)
		{
			throw new Exception("more that 1 record found for TsrCode[" + tsrCode + "]");
		}

		return tsrList.get(0);
	}

	public Tsr findTsrByFullName(String fullName)
			throws Exception
	{
		fullName = fullName.replaceAll(" ", "");

		List<Tsr> tsrList = this.tsrDao.findByNamedQuery("findTsrByFullName", fullName);

		if (tsrList.size() == 0)
		{
			if (fullName.length() > 3)
			{
				return findTsrByFullName(fullName.substring(1));
			}

			throw new Exception("not found for fullName[" + fullName + "]");
//			return null;
		}

//		if (tsrList.size() > 1)
//		{
//			throw new Exception("more that 1 record found for fullName[" + fullName + "]");
//		}

		System.out.println("found " + tsrList.size() + " tsr " + tsrList.get(0));
		return tsrList.get(0);
	}

	public List<Tsr> findTsrByExample(Tsr tsr)
			throws Exception
	{
		return this.tsrDao.findByExamplePaging(tsr, null);
	}

	public List<Tsr> searchTsrByExample(Tsr tsr)
			throws Exception
	{
		return this.tsrDao.searchByExamplePaging(tsr, null);
	}

	public Tsr addTsr(Tsr tsr, String batchId)
			throws Exception
	{
		return this.tsrDao.save(tsr);
	}

	public Tsr updateTsr(Tsr tsr, String batchId)
			throws Exception
	{
		return this.tsrDao.save(tsr);
	}

	public boolean deleteTsr(Tsr tsr)
			throws Exception
	{
		return this.tsrDao.delete(tsr.getId());
	}

}
