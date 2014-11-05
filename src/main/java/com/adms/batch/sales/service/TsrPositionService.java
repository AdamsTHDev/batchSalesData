package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.TsrPosition;

public interface TsrPositionService {

	public List<TsrPosition> findTsrPositionAll()
			throws Exception;

	public TsrPosition findTsrPositionById(Long id)
			throws Exception;

	public TsrPosition findTsrPositionByPositionCode(String positionCode)
			throws Exception;

	public TsrPosition findTsrPositionByPositionName(String positionName)
			throws Exception;

	public List<TsrPosition> findTsrPositionByExample(TsrPosition tsrPosition)
			throws Exception;

	public List<TsrPosition> searchTsrPositionByExample(TsrPosition tsrPosition)
			throws Exception;

	public TsrPosition addTsrPosition(TsrPosition tsrPosition, String batchId)
			throws Exception;

	public TsrPosition updateTsrPosition(TsrPosition tsrPosition, String batchId)
			throws Exception;

	public boolean deleteTsrPosition(TsrPosition tsrPosition)
			throws Exception;

}
