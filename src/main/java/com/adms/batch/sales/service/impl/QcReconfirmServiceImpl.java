package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.QcReconfirmDao;
import com.adms.batch.sales.domain.QcReconfirm;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.service.QcReconfirmService;

@Service("qcReconfirmService")
@Transactional
public class QcReconfirmServiceImpl implements QcReconfirmService {

	@Autowired
	private QcReconfirmDao qcReconfirmDao;

	public void setQcReconfirmDao(QcReconfirmDao qcReconfirmDao)
	{
		this.qcReconfirmDao = qcReconfirmDao;
	}

	public List<QcReconfirm> findQcReconfirmAll()
			throws Exception
	{
		return this.qcReconfirmDao.findAll();
	}

	public QcReconfirm findQcReconfirmById(Long id)
			throws Exception
	{
		return this.qcReconfirmDao.find(id);
	}

	public List<QcReconfirm> findQcReconfirmByxReference(String xReference)
			throws Exception
	{
		QcReconfirm example = new QcReconfirm();
		Sales sales = new Sales();
		example.setxReference(sales);

		List<QcReconfirm> qcReconfirmList = this.qcReconfirmDao.find(example);

		if (qcReconfirmList.size() == 0)
		{
			return null;
		}

		return qcReconfirmList;
	}

	public Long countReconfirmByxReference(String xReference)
			throws Exception
	{
		return this.qcReconfirmDao.countByNamedQuery("countReconfirmByxRef", xReference);
	}

	public QcReconfirm findByxReferenceAndQcStatusTime(String xReference, Date qcStatusTime)
			throws Exception
	{
		List<QcReconfirm> qcReconfirmList = this.qcReconfirmDao.findByNamedQuery("findByxReferenceAndQcStatusTime", xReference, qcStatusTime);

		return qcReconfirmList.size() > 0 ? qcReconfirmList.get(0) : null;
	}

	public List<QcReconfirm> findQcReconfirmByExample(QcReconfirm qcReconfirm)
			throws Exception
	{
		return this.qcReconfirmDao.findByExamplePaging(qcReconfirm, null);
	}

	public List<QcReconfirm> searchQcReconfirmByExample(QcReconfirm qcReconfirm)
			throws Exception
	{
		return this.qcReconfirmDao.searchByExamplePaging(qcReconfirm, null);
	}

	public QcReconfirm addQcReconfirm(QcReconfirm qcReconfirm, String batchId)
			throws Exception
	{
		return this.qcReconfirmDao.save(qcReconfirm);
	}

	public QcReconfirm updateQcReconfirm(QcReconfirm qcReconfirm, String batchId)
			throws Exception
	{
		return this.qcReconfirmDao.save(qcReconfirm);
	}

	public boolean deleteQcReconfirm(QcReconfirm qcReconfirm)
			throws Exception
	{
		return this.qcReconfirmDao.delete(qcReconfirm.getId());
	}

}
