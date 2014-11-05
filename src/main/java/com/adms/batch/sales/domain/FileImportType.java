package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "FILE_IMPORT_TYPE")
public class FileImportType extends BaseDomain {

	private static final long serialVersionUID = 9001199512968893845L;

	@Id
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FREQUENCY", referencedColumnName = "FREQUENCY_CODE")
	private FileImportFrequency fileImportFrequency;

	@Column(name = "TYPE_CODE")
	private String typeCode;

	@Column(name = "TYPE_NAME")
	private String typeName;

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

	public FileImportFrequency getFileImportFrequency()
	{
		return fileImportFrequency;
	}

	public void setFileImportFrequency(FileImportFrequency fileImportFrequency)
	{
		this.fileImportFrequency = fileImportFrequency;
	}

	public String getTypeCode()
	{
		return typeCode;
	}

	public void setTypeCode(String typeCode)
	{
		this.typeCode = typeCode;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
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
		return "FileImportCategory [id=" + id + ", fileImportFrequency=" + fileImportFrequency + ", typeCode=" + typeCode + ", typeName=" + typeName + ", description=" + description + "]";
	}

}
