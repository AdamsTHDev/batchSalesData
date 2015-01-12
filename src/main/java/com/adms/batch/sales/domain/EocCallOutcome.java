package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.adms.common.domain.BaseAuditDomain;

/**
 * @author kampon.pan
 *
 */
@Entity
@Table(name = "EOC_CALL_OUTCOME")
@NamedNativeQueries({
		@NamedNativeQuery(name = "findSydneyEocCallOutcomeBySupCodeAndOutcomeCode", query = "SELECT e.* FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e left outer join [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE WHERE l.LOT_EFFECTIVE_DATE >= '2014-06-01' and e.SUP_CODE = ? and e.OUTCOME_CODE = ?", resultClass = EocCallOutcome.class),
		@NamedNativeQuery(name = "findSydneyEocCallOutcomeBySupCode", query = "SELECT e.* FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e left outer join [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE WHERE l.LOT_EFFECTIVE_DATE >= '2014-06-01' and e.SUP_CODE = ?", resultClass = EocCallOutcome.class),
		@NamedNativeQuery(name = "countSydneyEocCallOutcomeBySupCodeAndOutcomeCode", query = "SELECT count(e.ID) TOTAL_EOC FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e left outer join [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE WHERE l.LOT_EFFECTIVE_DATE >= '2014-06-01' and e.SUP_CODE = ? and e.OUTCOME_CODE = ?", resultClass = EocCallOutcomeCount.class),
		@NamedNativeQuery(name = "countSydneyEocCallOutcomeBySupCode", query = "SELECT count(e.ID) TOTAL_EOC FROM [SALES].[dbo].[EOC_CALL_OUTCOME] e left outer join [SALES].[dbo].[LIST_LOT] l on e.LIST_LOT_CODE = l.LIST_LOT_CODE WHERE l.LOT_EFFECTIVE_DATE >= '2014-06-01' and e.SUP_CODE = ?", resultClass = EocCallOutcomeCount.class) })
public class EocCallOutcome extends BaseAuditDomain {

	private static final long serialVersionUID = -5801788832277559676L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EOC_ID")
	private Long eocId;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "LIST_LOT_CODE", referencedColumnName = "LIST_LOT_CODE")
	private ListLot listLot;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "TSR_CODE", referencedColumnName = "TSR_CODE")
	private Tsr tsr;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "SUP_CODE", referencedColumnName = "TSR_CODE")
	private Tsr supervisor;

	@Column(name = "OUTCOME_CODE")
	private String outcomeCode;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getEocId()
	{
		return eocId;
	}

	public void setEocId(Long eocId)
	{
		this.eocId = eocId;
	}

	public ListLot getListLot()
	{
		return listLot;
	}

	public void setListLot(ListLot listLot)
	{
		this.listLot = listLot;
	}

	public Tsr getTsr()
	{
		return tsr;
	}

	public void setTsr(Tsr tsr)
	{
		this.tsr = tsr;
	}

	public Tsr getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(Tsr supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getOutcomeCode()
	{
		return outcomeCode;
	}

	public void setOutcomeCode(String outcomeCode)
	{
		this.outcomeCode = outcomeCode;
	}

	@Override
	public String toString()
	{
		return "EocCallOutcome [id=" + id + ", eocId=" + eocId + ", listLot=" + listLot + ", tsr=" + tsr + ", supervisor=" + supervisor + ", outcomeCode=" + outcomeCode + "]";
	}

}
