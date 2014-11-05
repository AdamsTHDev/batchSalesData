package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.PaymentFrequency;

public interface PaymentFrequencyService {

	public List<PaymentFrequency> findPaymentFrequencyAll()
			throws Exception;

	public PaymentFrequency findPaymentFrequencyById(Long id)
			throws Exception;

	public PaymentFrequency findPaymentFrequencyByDescription(String description)
			throws Exception;

	public List<PaymentFrequency> findPaymentFrequencyByExample(PaymentFrequency paymentFrequency)
			throws Exception;

	public List<PaymentFrequency> searchPaymentFrequencyByExample(PaymentFrequency paymentFrequency)
			throws Exception;

	public PaymentFrequency addPaymentFrequency(PaymentFrequency paymentFrequency, String batchId)
			throws Exception;

	public PaymentFrequency updatePaymentFrequency(PaymentFrequency paymentFrequency, String batchId)
			throws Exception;

	public boolean deletePaymentFrequency(PaymentFrequency paymentFrequency)
			throws Exception;

}
