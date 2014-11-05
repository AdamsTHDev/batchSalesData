package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "CALL_CENTER")
public class CallCenter extends BaseDomain {

	private static final long serialVersionUID = 8702455571839099304L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "CALL_CENTER_NAME")
	private String callCenterName;

	@Column(name = "CALL_CENTER_ABBR")
	private String callCenterAbbr;

	@Column(name = "IS_ACTIVE")
	private String isActive;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCallCenterName()
	{
		return callCenterName;
	}

	public void setCallCenterName(String callCenterName)
	{
		this.callCenterName = callCenterName;
	}

	public String getCallCenterAbbr()
	{
		return callCenterAbbr;
	}

	public void setCallCenterAbbr(String callCenterAbbr)
	{
		this.callCenterAbbr = callCenterAbbr;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	@Override
	public String toString()
	{
		return "CallCenter [id=" + id + ", callCenterName=" + callCenterName + ", callCenterAbbr=" + callCenterAbbr + ", isActive=" + isActive + "]";
	}

}
