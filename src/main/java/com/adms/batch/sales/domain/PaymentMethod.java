package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "PAYMENT_METHOD")
public class PaymentMethod extends BaseDomain {

	private static final long serialVersionUID = -2884465259094805497L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "PaymentChannel [id=" + id + ", paymentMethod=" + paymentMethod + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
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
