package com.adms.batch.sales.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "INCENTIVE_CRITERIA")
@Cacheable
@NamedNativeQueries({ @NamedNativeQuery(name = "findBySydneyCriteria", query = "select ic.* from INCENTIVE_CRITERIA ic where ic.CAMPAIGN_CODE = ? and ic.QA_STATUS = ? and (ic.QA_REASON = ? or ic.QA_REASON is null) ", resultClass = IncentiveCriteria.class) })
public class IncentiveCriteria extends BaseDomain {

	private static final long serialVersionUID = 5897569210741467139L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "INCENTIVE_CODE", referencedColumnName = "INCENTIVE_CODE")
	private IncentiveInfo incentiveInfo;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_CODE", referencedColumnName = "CAMPAIGN_CODE")
	private Campaign campaign;

	@ManyToOne
	@JoinColumn(name = "QA_STATUS", referencedColumnName = "RECONFIRM_STATUS")
	private ReconfirmStatus qaStatus;

	@Column(name = "QA_REASON")
	private String qaReason;

	@Column(name = "IS_COUNT")
	private String isCount;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public IncentiveInfo getIncentiveInfo()
	{
		return incentiveInfo;
	}

	public void setIncentiveInfo(IncentiveInfo incentiveInfo)
	{
		this.incentiveInfo = incentiveInfo;
	}

	public Campaign getCampaign()
	{
		return campaign;
	}

	public void setCampaign(Campaign campaign)
	{
		this.campaign = campaign;
	}

	public ReconfirmStatus getQaStatus()
	{
		return qaStatus;
	}

	public void setQaStatus(ReconfirmStatus qaStatus)
	{
		this.qaStatus = qaStatus;
	}

	public String getQaReason()
	{
		return qaReason;
	}

	public void setQaReason(String qaReason)
	{
		this.qaReason = qaReason;
	}

	public String getIsCount()
	{
		return isCount;
	}

	public void setIsCount(String isCount)
	{
		this.isCount = isCount;
	}

	@Override
	public String toString()
	{
		return "IncentiveCriteria [id=" + id + ", incentiveInfo=" + incentiveInfo + ", campaign=" + campaign + ", qaStatus=" + qaStatus + ", qaReason=" + qaReason + ", isCount=" + isCount + "]";
	}

}
