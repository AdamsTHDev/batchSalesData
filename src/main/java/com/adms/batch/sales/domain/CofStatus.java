package com.adms.batch.sales.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "COF_STATUS")
@Cacheable
public class CofStatus extends BaseDomain {

	private static final long serialVersionUID = -5325862523590216115L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "COF_STATUS")
	private String cofStatus;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "ReconfirmStatus [id=" + id + ", cofStatus=" + cofStatus + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCofStatus()
	{
		return cofStatus;
	}

	public void setCofStatus(String cofStatus)
	{
		this.cofStatus = cofStatus;
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
