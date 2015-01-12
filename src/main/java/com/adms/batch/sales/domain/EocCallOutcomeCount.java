package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.adms.common.domain.CountableDomain;

@Entity
public class EocCallOutcomeCount extends CountableDomain {

	private static final long serialVersionUID = 8769307120213205121L;

	@Id
	@Column(name = "TOTAL_EOC")
	private Long totalEoc;

	public Long getTotalEoc()
	{
		return totalEoc;
	}

	public void setTotalEoc(Long totalEoc)
	{
		this.totalEoc = totalEoc;
	}

	@Override
	public Long getTotalCount()
	{
		return this.getTotalEoc();
	}

	@Override
	public String toString()
	{
		return "EocCallOutcomeCount [totalEoc=" + totalEoc + "]";
	}

}
