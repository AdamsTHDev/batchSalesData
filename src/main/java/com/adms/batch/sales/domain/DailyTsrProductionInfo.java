package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "[TSR_PROD_DAILY_INFO_TMP]")
public class DailyTsrProductionInfo extends BaseDomain {

	private static final long serialVersionUID = 6221647717317849441L;

	@Id
	@Column(name = "[Campaign Type]")
	private String campaignName;

	@Column(name = "[Campaign Number]")
	private String campaignCode;

	@Column(name = "[TM Agency]")
	private String callCenter;

	@Column(name = "[Period]")
	private String period;

	@Column(name = "[Print Date]")
	private String printDate;

	public String getCampaignName()
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName)
	{
		this.campaignName = campaignName;
	}

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getCallCenter()
	{
		return callCenter;
	}

	public void setCallCenter(String callCenter)
	{
		this.callCenter = callCenter;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}

	public String getPrintDate()
	{
		return printDate;
	}

	public void setPrintDate(String printDate)
	{
		this.printDate = printDate;
	}

	@Override
	public String toString()
	{
		return "DailyTsrProductionInfo [campaignName=" + campaignName + ", campaignCode=" + campaignCode + ", callCenter=" + callCenter + ", period=" + period + ", printDate=" + printDate + "]";
	}

}
