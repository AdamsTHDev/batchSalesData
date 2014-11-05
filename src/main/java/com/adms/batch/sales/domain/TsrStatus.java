package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "TSR_STATUS")
public class TsrStatus extends BaseDomain {

	private static final long serialVersionUID = 7538511239836836499L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "STATUS_CODE")
	private String statusCode;

	@Column(name = "STATUS_NAME")
	private String statusName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	@Override
	public String toString()
	{
		return "TsrStatus [id=" + id + ", statusCode=" + statusCode + ", statusName=" + statusName + "]";
	}

}
