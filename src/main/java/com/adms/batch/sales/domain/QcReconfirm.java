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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "QC_RECONFIRM")
@Cache(region = "sales", usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedNativeQueries({ @NamedNativeQuery(name = "findByxReferenceAndQcStatusTime", query = "select t.* from QC_RECONFIRM t where t.X_REFERENCE = ? and t.QC_STATUS_TIME = ?", resultClass = QcReconfirm.class),
	@NamedNativeQuery(name = "countReconfirmByxRef", query = "select count(t.ID) TOTAL_RECONFIRM from QC_RECONFIRM t where t.X_REFERENCE = ?", resultClass = QcReconfirmCount.class)})
public class QcReconfirm extends BaseAuditDomain {

	private static final long serialVersionUID = 8769307120213205121L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "X_REFERENCE", referencedColumnName = "X_REFERENCE")
	private Sales xReference;

	@Column(name = "QC_ID")
	private String qcId;

	@ManyToOne
	@JoinColumn(name = "QC_STATUS", referencedColumnName = "RECONFIRM_STATUS")
	private ReconfirmStatus qcStatus;

	@Column(name = "QC_STATUS_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date qcStatusTime;

	@ManyToOne
	@JoinColumn(name = "TSR_STATUS", referencedColumnName = "RECONFIRM_STATUS")
	private ReconfirmStatus tsrStatus;

	@Column(name = "RECONFIRM_REASON")
	private String reconfirmReason;

	@Column(name = "RECONFIRM_REMARK")
	private String reconfirmRemark;

	@Column(name = "CURRENT_REASON")
	private String currentReason;

	@Column(name = "CURRENT_REMARK")
	private String currentRemark;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Sales getxReference()
	{
		return xReference;
	}

	public void setxReference(Sales xReference)
	{
		this.xReference = xReference;
	}

	public String getQcId()
	{
		return qcId;
	}

	public void setQcId(String qcId)
	{
		this.qcId = qcId;
	}

	public ReconfirmStatus getQcStatus()
	{
		return qcStatus;
	}

	public void setQcStatus(ReconfirmStatus qcStatus)
	{
		this.qcStatus = qcStatus;
	}

	public Date getQcStatusTime()
	{
		return qcStatusTime;
	}

	public void setQcStatusTime(Date qcStatusTime)
	{
		this.qcStatusTime = qcStatusTime;
	}

	public ReconfirmStatus getTsrStatus()
	{
		return tsrStatus;
	}

	public void setTsrStatus(ReconfirmStatus tsrStatus)
	{
		this.tsrStatus = tsrStatus;
	}

	public String getReconfirmReason()
	{
		return reconfirmReason;
	}

	public void setReconfirmReason(String reconfirmReason)
	{
		this.reconfirmReason = reconfirmReason;
	}

	public String getReconfirmRemark()
	{
		return reconfirmRemark;
	}

	public void setReconfirmRemark(String reconfirmRemark)
	{
		this.reconfirmRemark = reconfirmRemark;
	}

	public String getCurrentReason()
	{
		return currentReason;
	}

	public void setCurrentReason(String currentReason)
	{
		this.currentReason = currentReason;
	}

	public String getCurrentRemark()
	{
		return currentRemark;
	}

	public void setCurrentRemark(String currentRemark)
	{
		this.currentRemark = currentRemark;
	}

	@Override
	public String toString()
	{
		return "QcReconfirm [id=" + id + ", xReference=" + xReference + ", qcId=" + qcId + ", qcStatus=" + qcStatus + ", qcStatusTime=" + qcStatusTime + ", tsrStatus=" + tsrStatus + ", reconfirmReason=" + reconfirmReason + ", reconfirmRemark=" + reconfirmRemark + ", currentReason="
				+ currentReason + ", currentRemark=" + currentRemark + "]";
	}

}
