package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "LIST_SOURCE")
public class ListSource extends BaseDomain {

	private static final long serialVersionUID = -8151772555553821818L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "LIST_SOURCE_NAME")
	private String listSourceName;

	@Column(name = "LIST_SOURCE_ABBR")
	private String listSourceAbbr;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getListSourceName()
	{
		return listSourceName;
	}

	public void setListSourceName(String listSourceName)
	{
		this.listSourceName = listSourceName;
	}

	public String getListSourceAbbr()
	{
		return listSourceAbbr;
	}

	public void setListSourceAbbr(String listSourceAbbr)
	{
		this.listSourceAbbr = listSourceAbbr;
	}

	@Override
	public String toString()
	{
		return "ListSource [id=" + id + ", listSourceName=" + listSourceName + ", listSourceAbbr=" + listSourceAbbr + "]";
	}

}
