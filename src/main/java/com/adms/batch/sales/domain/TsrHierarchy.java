package com.adms.batch.sales.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "TSR_HIERARCHY")
@NamedNativeQueries({
	@NamedNativeQuery(name = "findTsrHierarchyByTsrAndUplineAndDate", query = ""
			+ "SELECT *"
			+ "  FROM [SALES].[dbo].[TSR_HIERARCHY]"
			+ " WHERE 1 = 1"
			+ "   AND [TSR_CODE] = ?"
			+ "   AND [UPLINE_CODE] = ?"
			+ "   AND ([EFFECTIVE_DATE] <= ? AND (? <= [END_DATE] OR [END_DATE] IS NULL))"
			+ "", resultClass = TsrHierarchy.class),
	@NamedNativeQuery(name = "findTsrHierarchyByTsrAndDate", query = ""
			+ "SELECT *"
			+ "  FROM [SALES].[dbo].[TSR_HIERARCHY]"
			+ " WHERE 1 = 1"
			+ "   AND [TSR_CODE] = ?"
			+ "   AND ([EFFECTIVE_DATE] <= ? AND (? <= [END_DATE] OR [END_DATE] IS NULL))"
			+ "", resultClass = TsrHierarchy.class),
})
public class TsrHierarchy extends BaseAuditDomain {

	private static final long serialVersionUID = 5124028163280318495L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_CODE", referencedColumnName = "CAMPAIGN_CODE")
	private Campaign campaign;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "TSR_CODE", referencedColumnName = "TSR_CODE")
	private Tsr tsr;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "UPLINE_CODE", referencedColumnName = "TSR_CODE")
	private Tsr upline;

	@Column(name = "EFFECTIVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

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

	public Tsr getTsr()
	{
		return tsr;
	}

	public void setTsr(Tsr tsr)
	{
		this.tsr = tsr;
	}

	public Tsr getUpline()
	{
		return upline;
	}

	public void setUpline(Tsr upline)
	{
		this.upline = upline;
	}

	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Override
	public String toString()
	{
		return "TsrHierarchy [id=" + id + ", campaign=" + campaign + ", tsr=" + tsr + ", upline=" + upline + ", effectiveDate=" + effectiveDate + ", endDate=" + endDate + "]";
	}

}
