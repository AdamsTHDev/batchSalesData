package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.QcReconfirm;

public interface QcReconfirmService {

	public List<QcReconfirm> findQcReconfirmAll()
			throws Exception;

	public QcReconfirm findQcReconfirmById(Long id)
			throws Exception;

	public List<QcReconfirm> findQcReconfirmByxReference(String xReference)
			throws Exception;

	public Long countReconfirmByxReference(String xReference)
			throws Exception;

	public QcReconfirm findByxReferenceAndQcStatusTime(String xReference, Date qcStatusTime)
			throws Exception;

	public List<QcReconfirm> findQcReconfirmByExample(QcReconfirm qcReconfirm)
			throws Exception;

	public List<QcReconfirm> searchQcReconfirmByExample(QcReconfirm qcReconfirm)
			throws Exception;

	public QcReconfirm addQcReconfirm(QcReconfirm qcReconfirm, String batchId)
			throws Exception;

	public QcReconfirm updateQcReconfirm(QcReconfirm qcReconfirm, String batchId)
			throws Exception;

	public boolean deleteQcReconfirm(QcReconfirm qcReconfirm)
			throws Exception;

}
