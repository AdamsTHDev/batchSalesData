package com.adms.batch.sales.service;

import java.util.Date;
import java.util.List;

import com.adms.batch.sales.domain.BillingResult;

public interface BillingResultService {

	public List<BillingResult> findBillingResultAll()
			throws Exception;

	public BillingResult findBillingResultById(Long id)
			throws Exception;

	public List<BillingResult> findBillingResultByxReference(String xReference)
			throws Exception;

	public BillingResult findBillingResultByxRefAndBillingDate(String xReference, Date billingDate)
			throws Exception;

	public List<BillingResult> findBillingResultByExample(BillingResult billingResult)
			throws Exception;

	public List<BillingResult> searchBillingResultByExample(BillingResult billingResult)
			throws Exception;

	public BillingResult addBillingResult(BillingResult billingResult, String batchId)
			throws Exception;

	public BillingResult updateBillingResult(BillingResult billingResult, String batchId)
			throws Exception;

	public boolean deleteBillingResult(BillingResult billingResult)
			throws Exception;

}
