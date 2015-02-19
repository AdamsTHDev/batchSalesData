package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "BILLING_STATUS")
public class BillingStatus extends BaseDomain {

	private static final long serialVersionUID = -1754931890204719077L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "BILLING_STATUS")
	private String billingStatus;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "ReconfirmStatus [id=" + id + ", billingStatus=" + billingStatus + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getBillingStatus()
	{
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus)
	{
		this.billingStatus = billingStatus;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
