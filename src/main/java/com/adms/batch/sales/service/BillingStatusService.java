package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.BillingStatus;

public interface BillingStatusService {

	public List<BillingStatus> findBillingStatusAll()
			throws Exception;

	public BillingStatus findBillingStatusById(Long id)
			throws Exception;

	public BillingStatus findBillingStatusByStatus(String billingStatus)
			throws Exception;

	public BillingStatus findBillingStatusByDescription(String billingDescription)
			throws Exception;

	public List<BillingStatus> findBillingStatusByExample(BillingStatus billingStatus)
			throws Exception;

	public List<BillingStatus> searchBillingStatusByExample(BillingStatus billingStatus)
			throws Exception;

	public BillingStatus addBillingStatus(BillingStatus billingStatus, String batchId)
			throws Exception;

	public BillingStatus updateBillingStatus(BillingStatus billingStatus, String batchId)
			throws Exception;

	public boolean deleteBillingStatus(BillingStatus billingStatus)
			throws Exception;

}
