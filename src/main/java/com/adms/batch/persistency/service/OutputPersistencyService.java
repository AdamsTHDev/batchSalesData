package com.adms.batch.persistency.service;

import java.util.List;

import com.adms.batch.persistency.domain.OutputPersistency;

public interface OutputPersistencyService {

	public List<OutputPersistency> findOutputPersistencyAll()
			throws Exception;

	public List<OutputPersistency> dataCalculation(String param)
			throws Exception;

}
