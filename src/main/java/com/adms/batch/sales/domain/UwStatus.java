package com.adms.batch.sales.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "UW_STATUS")
@Cacheable
public class UwStatus extends BaseDomain {

	private static final long serialVersionUID = 2356220076826117351L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "UW_STATUS")
	private String uwStatus;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "ReconfirmStatus [id=" + id + ", uwStatus=" + uwStatus + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUwStatus()
	{
		return uwStatus;
	}

	public void setUwStatus(String uwStatus)
	{
		this.uwStatus = uwStatus;
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
