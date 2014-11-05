package com.adms.batch.sales.service;

import com.adms.batch.sales.domain.TestDomain;

public interface TestService {

	public TestDomain addTestDomain(TestDomain testDomain, String username)
			throws Exception;

	public TestDomain updateTestDomain(TestDomain testDomain, String username)
			throws Exception;

	public boolean deleteTestDomain(TestDomain testDomain)
			throws Exception;
}
