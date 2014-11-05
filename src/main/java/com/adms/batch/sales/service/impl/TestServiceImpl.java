package com.adms.batch.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.TestDao;
import com.adms.batch.sales.domain.TestDomain;
import com.adms.batch.sales.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testDao;

	public TestDomain addTestDomain(TestDomain testDomain, String username)
			throws Exception
	{
		return this.testDao.save(testDomain);
	}

	public TestDomain updateTestDomain(TestDomain testDomain, String username)
			throws Exception
	{
		return this.testDao.save(testDomain);
	}

	public boolean deleteTestDomain(TestDomain testDomain)
			throws Exception
	{
		return this.testDao.delete(testDomain.getId());
	}

}
