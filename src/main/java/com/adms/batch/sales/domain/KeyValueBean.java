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
@NamedNativeQueries({
	@NamedNativeQuery(name = "findQcReconfirmMaxPrintDate", query = ""
			+ "SELECT [Campaign] as [KEY] ,max([PrintDate]) as [VALUE]"
			+ "  FROM [SALES].[dbo].[daily_qc_reconfirm_status_cons]"
			+ " where [Campaign] = ?"
			+ " group by [Campaign]"
			+ "", resultClass = KeyValueBean.class),
	@NamedNativeQuery(name = "findTsrTrackingMaxPrintDate", query = ""
			+ "SELECT [Campaign] as [KEY] ,max([Print Date]) as [VALUE]"
			+ "  FROM [SALES].[dbo].[daily_tsr_tracking_cons]"
			+ " where [Campaign] = ?"
			+ " group by [Campaign]"
			+ "", resultClass = KeyValueBean.class),
	@NamedNativeQuery(name = "findCampaignCodeByCampaignYearAndListSourceAndInsurer", query = ""
			+ "SELECT [CAMPAIGN_CODE] as [KEY], [CAMPAIGN_CODE] as [VALUE]"
			+ "  FROM [SALES].[dbo].[CAMPAIGN]"
			+ " WHERE 1 = 1"
			+ "   AND CAMPAIGN_YEAR = ?"
			+ "   AND [LIST_SOURCE] like ?"
			+ "   AND [INSURER] like ?"
			+ " ORDER BY [CAMPAIGN_CODE]"
			+ "", resultClass = KeyValueBean.class),})
public class KeyValueBean extends BaseDomain {

	private static final long serialVersionUID = 407548182288908839L;

	@Id
	@Column(name = "KEY")
	private String key;

	@Column(name = "VALUE")
	private String value;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "KeyValueBean [key=" + key + ", value=" + value + "]";
	}

}
