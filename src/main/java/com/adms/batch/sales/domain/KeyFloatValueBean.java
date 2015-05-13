package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "KEY_VALUE")
@NamedNativeQueries({@NamedNativeQuery(name = "findListConversionBySupCodeAndLotEffectiveDate", query = ""
		+ "SELECT e.SUP_CODE as [KEY], convert(float, SUM(case when e.OUTCOME_CODE = '05' then 1 else 0 end)) / convert(float, count(e.ID)) as [VALUE]"
		+ "  FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e"
		+ "  LEFT OUTER JOIN [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE"
		+ " WHERE e.SUP_CODE = ?"
		+ "   AND l.LOT_EFFECTIVE_DATE >= ?"
		+ " GROUP BY e.SUP_CODE", resultClass = KeyFloatValueBean.class),
		@NamedNativeQuery(name = "findListConversionBySupCodeAndListLotCode", query = ""
				+ "SELECT e.SUP_CODE as [KEY], convert(float, SUM(case when e.OUTCOME_CODE = '05' then 1 else 0 end)) / convert(float, count(e.ID)) as [VALUE]"
				+ "  FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e"
				+ "  LEFT OUTER JOIN [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE"
				+ " WHERE e.SUP_CODE = ?"
				+ "   AND l.LIST_LOT_CODE = ?"
				+ " GROUP BY e.SUP_CODE", resultClass = KeyFloatValueBean.class),})
public class KeyFloatValueBean extends BaseDomain {

	private static final long serialVersionUID = -6215943115515760592L;

	@Id
	@Column(name = "KEY")
	private String key;

	@Column(name = "VALUE")
	private Float value;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}


	public Float getValue()
	{
		return value;
	}

	public void setValue(Float value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "KeyValueBean [key=" + key + ", value=" + value + "]";
	}

}
