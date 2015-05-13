package com.adms.batch.sales.service;

import java.util.Date;

import com.adms.batch.sales.domain.KeyFloatValueBean;

public interface KeyFloatValueBeanService {

	public KeyFloatValueBean findListConversionBySupCodeAndLotEffectiveDate(String supCode, Date lotEffectiveDate)
			throws Exception;

	public KeyFloatValueBean findListConversionBySupCodeAndListLotCode(String supCode, String listLotCode)
			throws Exception;

}
