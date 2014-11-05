package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "PAYMENT_FREQUENCY")
public class PaymentFrequency extends BaseDomain {

	private static final long serialVersionUID = -3145649589051421450L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "FREQUENCY_CODE")
	private String frequencyCode;

	@Column(name = "FREQUENCY_NAME")
	private String frequencyName;

	@Column(name = "DESCRIPTION")
	private String description;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFrequencyCode()
	{
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode)
	{
		this.frequencyCode = frequencyCode;
	}

	public String getFrequencyName()
	{
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName)
	{
		this.frequencyName = frequencyName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "PaymentFrequency [id=" + id + ", frequencyCode=" + frequencyCode + ", frequencyName=" + frequencyName + ", description=" + description + "]";
	}

}
