package com.adms.batch.sales.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "UW_DECISION")
@Cacheable
public class UwDecision extends BaseDomain {

	private static final long serialVersionUID = -4852313531292370536L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "UW_DECISION")
	private String uwDecision;

	@Column(name = "DESCRIPTION")
	private String description;

	@Override
	public String toString()
	{
		return "ReconfirmStatus [id=" + id + ", uwDecision=" + uwDecision + ", description=" + description + "]";
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUwDecision()
	{
		return uwDecision;
	}

	public void setUwDecision(String uwDecision)
	{
		this.uwDecision = uwDecision;
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
