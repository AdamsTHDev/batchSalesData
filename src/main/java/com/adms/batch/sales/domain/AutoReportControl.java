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
@Table(name = "AUTO_REPORT_CONTROL")
public class AutoReportControl extends BaseAuditDomain {

	private static final long serialVersionUID = 3158376321924923428L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_CODE", referencedColumnName = "CAMPAIGN_CODE")
	private Campaign campaign;


	@Column(name = "REPORT_NAME")
	private String reportName;

	@Column(name = "REPORT_DATE")
	@Temporal(TemporalType.DATE)
	private Date reportDate;

	@Column(name = "SENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date sentDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Campaign getCampaign()
	{
		return campaign;
	}

	public void setCampaign(Campaign campaign)
	{
		this.campaign = campaign;
	}

	public String getReportName()
	{
		return reportName;
	}

	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	public Date getReportDate()
	{
		return reportDate;
	}

	public void setReportDate(Date reportDate)
	{
		this.reportDate = reportDate;
	}

	public Date getSentDate()
	{
		return sentDate;
	}

	public void setSentDate(Date sentDate)
	{
		this.sentDate = sentDate;
	}

	@Override
	public String toString()
	{
		return "AutoReportControl [id=" + id + ", campaign=" + campaign + ", reportName=" + reportName + ", reportDate=" + reportDate + ", sentDate=" + sentDate + "]";
	}

}
