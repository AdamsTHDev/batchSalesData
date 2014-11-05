package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "FILE_CONTENT")
public class FileContent extends BaseDomain {

	private static final long serialVersionUID = 9001199512968893845L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "DOCUMENT_CONTENT")
	private String documentContent;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDocumentContent()
	{
		return documentContent;
	}

	public void setDocumentContent(String documentContent)
	{
		this.documentContent = documentContent;
	}

	@Override
	public String toString()
	{
		return "FileContent [id=" + id + ", documentContent=" + documentContent + "]";
	}

}
