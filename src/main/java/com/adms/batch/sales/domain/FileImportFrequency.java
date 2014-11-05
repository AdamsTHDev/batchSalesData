package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "FILE_IMPORT_FREQUENCY")
public class FileImportFrequency extends BaseDomain {

	private static final long serialVersionUID = -9019024046519443712L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "FREQUENCY_CODE")
	private String frequencyCode;

	@Column(name = "FREQUENCY_NAME")
	private String frequencyName;

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

	@Override
	public String toString()
	{
		return "FileImportFrequency [id=" + id + ", frequencyCode=" + frequencyCode + ", frequencyName=" + frequencyName + "]";
	}

}
