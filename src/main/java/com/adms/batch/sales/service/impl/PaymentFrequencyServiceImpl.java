package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.PaymentFrequencyDao;
import com.adms.batch.sales.domain.PaymentFrequency;
import com.adms.batch.sales.service.PaymentFrequencyService;

@Service("paymentFrequencyService")
@Transactional
public class PaymentFrequencyServiceImpl implements PaymentFrequencyService {

	@Autowired
	private PaymentFrequencyDao paymentFrequencyDao;

	public void setPaymentFrequencyDao(PaymentFrequencyDao paymentFrequencyDao)
	{
		this.paymentFrequencyDao = paymentFrequencyDao;
	}

	public List<PaymentFrequency> findPaymentFrequencyAll()
			throws Exception
	{
		return this.paymentFrequencyDao.findAll();
	}

	public PaymentFrequency findPaymentFrequencyById(Long id)
			throws Exception
	{
		return this.paymentFrequencyDao.find(id);
	}

	public PaymentFrequency findPaymentFrequencyByDescription(String description)
			throws Exception
	{
		PaymentFrequency example = new PaymentFrequency();
		example.setDescription(description);

		List<PaymentFrequency> paymentFrequencyList = this.paymentFrequencyDao.findByExamplePaging(example, null);

		if (paymentFrequencyList.size() == 0)
		{
			throw new Exception("not found any record for PaymentFrequency[" + description + "]");
		}

		if (paymentFrequencyList.size() > 1)
		{
			throw new Exception("more that 1 record found for PaymentFrequency[" + description + "]");
		}

		return paymentFrequencyList.get(0);
	}

	public List<PaymentFrequency> findPaymentFrequencyByExample(PaymentFrequency paymentFrequency)
			throws Exception
	{
		return this.paymentFrequencyDao.findByExamplePaging(paymentFrequency, null);
	}

	public List<PaymentFrequency> searchPaymentFrequencyByExample(PaymentFrequency paymentFrequency)
			throws Exception
	{
		return this.paymentFrequencyDao.searchByExamplePaging(paymentFrequency, null);
	}

	public PaymentFrequency addPaymentFrequency(PaymentFrequency paymentFrequency, String batchId)
			throws Exception
	{
		return this.paymentFrequencyDao.save(paymentFrequency);
	}

	public PaymentFrequency updatePaymentFrequency(PaymentFrequency paymentFrequency, String batchId)
			throws Exception
	{
		return this.paymentFrequencyDao.save(paymentFrequency);
	}

	public boolean deletePaymentFrequency(PaymentFrequency paymentFrequency)
			throws Exception
	{
		return this.paymentFrequencyDao.delete(paymentFrequency.getId());
	}

}
