package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.Tsr;

public interface TsrService {

	public List<Tsr> findTsrAll()
			throws Exception;

	public Tsr findTsrById(Long id)
			throws Exception;

	public Tsr findTsrByTsrCode(String tsrCode)
			throws Exception;

	public Tsr findTsrByFullName(String fullName, Date saleDate)
			throws Exception;

	public List<Tsr> findTsrByExample(Tsr tsr)
			throws Exception;

	public List<Tsr> searchTsrByExample(Tsr tsr)
			throws Exception;

	public Tsr addTsr(Tsr tsr, String batchId)
			throws Exception;

	public Tsr updateTsr(Tsr tsr, String batchId)
			throws Exception;

	public boolean deleteTsr(Tsr tsr)
			throws Exception;

}
