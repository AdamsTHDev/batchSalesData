package com.adms.batch.sales.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "FILE_IMPORT")
public class FileImport extends BaseAuditDomain {

	private static final long serialVersionUID = -1033272361357486038L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "IMPORT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date importTime;

	@Column(name = "DOCUMENT_NAME")
	private String documentName;

	@Column(name = "DOCUMENT_TYPE")
	private String documentType;

	@ManyToOne
	@JoinColumn(name = "FILE_IMPORT_TYPE")
	private FileImportType fileImportType;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getImportTime()
	{
		return importTime;
	}

	public void setImportTime(Date importTime)
	{
		this.importTime = importTime;
	}

	public String getDocumentName()
	{
		return documentName;
	}

	public void setDocumentName(String documentName)
	{
		this.documentName = documentName;
	}

	public String getDocumentType()
	{
		return documentType;
	}

	public void setDocumentType(String documentType)
	{
		this.documentType = documentType;
	}

	public FileImportType getFileImportType()
	{
		return fileImportType;
	}

	public void setFileImportType(FileImportType fileImportType)
	{
		this.fileImportType = fileImportType;
	}

	@Override
	public String toString()
	{
		return "FileImport [id=" + id + ", importTime=" + importTime + ", documentName=" + documentName + ", documentType=" + documentType + ", fileImportType=" + fileImportType + "]";
	}

}
