package com.adms.batch.sales.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.KeyFloatValueBeanDao;
import com.adms.batch.sales.domain.KeyFloatValueBean;
import com.adms.batch.sales.service.KeyFloatValueBeanService;

@Service("keyFloatValueBeanService")
@Transactional
public class KeyFloatValueBeanServiceImpl implements KeyFloatValueBeanService {

	@Autowired
	private KeyFloatValueBeanDao keyFloatValueBeanDao;

	public KeyFloatValueBean findListConversionBySupCodeAndLotEffectiveDate(String supCode, Date lotEffectiveDate)
			throws Exception
	{
		List<KeyFloatValueBean> list = this.keyFloatValueBeanDao.findByNamedQuery("findListConversionBySupCodeAndLotEffectiveDate", supCode, lotEffectiveDate);

		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public KeyFloatValueBean findListConversionBySupCodeAndListLotCode(String supCode, String listLotCode)
			throws Exception
	{
		List<KeyFloatValueBean> list = this.keyFloatValueBeanDao.findByNamedQuery("findListConversionBySupCodeAndListLotCode", supCode, listLotCode);

		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
