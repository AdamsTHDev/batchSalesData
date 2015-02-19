package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "DDOP_MAPPING_STATUS")
public class DdopMappingStatus extends BaseDomain {

	private static final long serialVersionUID = -5291147143786128740L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "MAPPING_STATUS")
	private String mappingStatus;

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

	public String getMappingStatus()
	{
		return mappingStatus;
	}

	public void setMappingStatus(String mappingStatus)
	{
		this.mappingStatus = mappingStatus;
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
		return "DdopMappingStatus [id=" + id + ", mappingStatus=" + mappingStatus + ", description=" + description + "]";
	}

}
