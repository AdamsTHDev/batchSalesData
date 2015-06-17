package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.PaymentMethodDao;
import com.adms.batch.sales.domain.PaymentMethod;
import com.adms.batch.sales.service.PaymentMethodService;

@Service("paymentMethodService")
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodDao paymentMethodDao;

	public void setPaymentMethodDao(PaymentMethodDao paymentMethodDao)
	{
		this.paymentMethodDao = paymentMethodDao;
	}

	public List<PaymentMethod> findPaymentMethodAll()
			throws Exception
	{
		return this.paymentMethodDao.findAll();
	}

	public PaymentMethod findPaymentMethodById(Long id)
			throws Exception
	{
		return this.paymentMethodDao.find(id);
	}

	public PaymentMethod findPaymentMethodByDescription(String description)
			throws Exception
	{
		PaymentMethod example = new PaymentMethod();
		example.setDescription(description);

		List<PaymentMethod> paymentMethodList = this.paymentMethodDao.findByExamplePaging(example, null);

		if (paymentMethodList.size() == 0)
		{
			throw new Exception("not found record for PaymentMethod [" + description + "]");
		}

		if (paymentMethodList.size() > 1)
		{
			throw new Exception("more than 1 record found for PaymentMethod [" + description + "]");
		}

		return paymentMethodList.get(0);
	}

	public List<PaymentMethod> findPaymentMethodByExample(PaymentMethod paymentMethod)
			throws Exception
	{
		return this.paymentMethodDao.findByExamplePaging(paymentMethod, null);
	}

	public List<PaymentMethod> searchPaymentMethodByExample(PaymentMethod paymentMethod)
			throws Exception
	{
		return this.paymentMethodDao.searchByExamplePaging(paymentMethod, null);
	}

	public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod, String batchId)
			throws Exception
	{
		return this.paymentMethodDao.save(paymentMethod);
	}

	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod, String batchId)
			throws Exception
	{
		return this.paymentMethodDao.save(paymentMethod);
	}

	public boolean deletePaymentMethod(PaymentMethod paymentMethod)
			throws Exception
	{
		return this.paymentMethodDao.delete(paymentMethod.getId());
	}

}
