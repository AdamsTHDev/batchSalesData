package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.Insurer;

public interface InsurerService {

	public List<Insurer> findInsurerAll()
			throws Exception;

	public Insurer findInsurerById(Long id)
			throws Exception;

	public Insurer findInsurerByInsurerAbbr(String insurerAbbr)
			throws Exception;

	public List<Insurer> findInsurerByExample(Insurer insurer)
			throws Exception;

	public List<Insurer> searchInsurerByExample(Insurer insurer)
			throws Exception;

	public Insurer addInsurer(Insurer insurer, String batchId)
			throws Exception;

	public Insurer updateInsurer(Insurer insurer, String batchId)
			throws Exception;

	public boolean deleteInsurer(Insurer insurer)
			throws Exception;

}
