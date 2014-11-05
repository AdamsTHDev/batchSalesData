package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.PaymentMethod;

public interface PaymentMethodService {

	public List<PaymentMethod> findPaymentMethodAll()
			throws Exception;

	public PaymentMethod findPaymentMethodById(Long id)
			throws Exception;

	public PaymentMethod findPaymentMethodByDescription(String description)
			throws Exception;

	public List<PaymentMethod> findPaymentMethodByExample(PaymentMethod paymentMethod)
			throws Exception;

	public List<PaymentMethod> searchPaymentMethodByExample(PaymentMethod paymentMethod)
			throws Exception;

	public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod, String batchId)
			throws Exception;

	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod, String batchId)
			throws Exception;

	public boolean deletePaymentMethod(PaymentMethod paymentMethod)
			throws Exception;

}
