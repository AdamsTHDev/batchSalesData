package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.adms.common.domain.CountableDomain;

@Entity
public class QcReconfirmCount extends CountableDomain {

	private static final long serialVersionUID = 8769307120213205121L;

	@Id
	@Column(name = "TOTAL_RECONFIRM")
	private Long totalReconfirm;

	public Long getTotalReconfirm()
	{
		return totalReconfirm;
	}

	public void setTotalReconfirm(Long totalReconfirm)
	{
		this.totalReconfirm = totalReconfirm;
	}

	@Override
	public Long getTotalCount()
	{
		return this.getTotalReconfirm();
	}

	@Override
	public String toString()
	{
		return "QcReconfirmCount [totalReconfirm=" + totalReconfirm + "]";
	}

}
