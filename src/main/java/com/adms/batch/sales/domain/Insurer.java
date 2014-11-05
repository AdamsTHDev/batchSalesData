package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "INSURER")
public class Insurer extends BaseDomain {

	private static final long serialVersionUID = 6467278279184501459L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "INSURER_NAME")
	private String insurerName;

	@Column(name = "INSURER_ABBR")
	private String insurerAbbr;

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

	public String getInsurerName()
	{
		return insurerName;
	}

	public void setInsurerName(String insurerName)
	{
		this.insurerName = insurerName;
	}

	public String getInsurerAbbr()
	{
		return insurerAbbr;
	}

	public void setInsurerAbbr(String insurerAbbr)
	{
		this.insurerAbbr = insurerAbbr;
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
		return "Insurer [id=" + id + ", insurerName=" + insurerName + ", insurerAbbr=" + insurerAbbr + ", isActive=" + isActive + "]";
	}

}
