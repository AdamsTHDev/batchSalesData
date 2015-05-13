package com.adms.batch.persistency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.persistency.dao.OutputPersistencyDao;
import com.adms.batch.persistency.domain.OutputPersistency;
import com.adms.batch.persistency.service.OutputPersistencyService;

@Service("outputPersistencyService")
@Transactional
public class OutputPersistencyServiceImpl implements OutputPersistencyService {

	@Autowired
	private OutputPersistencyDao outputPersistencyDao;

	public void setOutputPersistencyDao(OutputPersistencyDao outputPersistencyDao)
	{
		this.outputPersistencyDao = outputPersistencyDao;
	}

	public List<OutputPersistency> findOutputPersistencyAll()
			throws Exception
	{
		return this.outputPersistencyDao.findByNamedQuery("execReportDataQuery");
	}

	public List<OutputPersistency> dataCalculation(String param)
			throws Exception
	{
		return this.outputPersistencyDao.findByNamedQuery("execReportDataCalculation", param);
	}

}
