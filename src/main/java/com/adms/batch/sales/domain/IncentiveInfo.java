package com.adms.batch.sales.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "INCENTIVE_INFO")
public class IncentiveInfo extends BaseDomain {

	private static final long serialVersionUID = 1148149709189120877L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "INCENTIVE_CODE")
	private String incentiveCode;

	@Column(name = "INCENTIVE_NAME")
	private String incentiveName;

	@Column(name = "EFFECTIVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

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

	public String getIncentiveCode()
	{
		return incentiveCode;
	}

	public void setIncentiveCode(String incentiveCode)
	{
		this.incentiveCode = incentiveCode;
	}

	public String getIncentiveName()
	{
		return incentiveName;
	}

	public void setIncentiveName(String incentiveName)
	{
		this.incentiveName = incentiveName;
	}

	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
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
		return "IncentiveInfo [id=" + id + ", incentiveCode=" + incentiveCode + ", incentiveName=" + incentiveName + ", effectiveDate=" + effectiveDate + ", endDate=" + endDate + ", isActive=" + isActive + "]";
	}

}
