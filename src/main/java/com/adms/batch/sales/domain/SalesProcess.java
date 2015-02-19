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
@Table(name = "SALES_PROCESS")
@NamedNativeQueries({
		@NamedNativeQuery(name = "findSalesProcessByXRefferenceAndStatusDateAndReconfirmStatus", query = "select salesProcess.* from SALES_PROCESS salesProcess where salesProcess.X_REFERENCE = ? and salesProcess.STATUS_DATE = ? and salesProcess.RECONFIRM_STATUS = ?", resultClass = SalesProcess.class),
		@NamedNativeQuery(name = "findSalesProcessByXRefferenceAndStatusDateAndPolicyStatus", query = "select salesProcess.* from SALES_PROCESS salesProcess where salesProcess.X_REFERENCE = ? and salesProcess.STATUS_DATE = ? and salesProcess.POLICY_STATUS = ?", resultClass = SalesProcess.class) })
public class SalesProcess extends BaseAuditDomain {

	private static final long serialVersionUID = 7794769129567742338L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "IMPORT_ID")
	private FileImport fileImport;

	@Column(name = "BATCH_DATE")
	@Temporal(TemporalType.DATE)
	private Date batchDate;

	@ManyToOne
	@JoinColumn(name = "X_REFERENCE", referencedColumnName = "X_REFERENCE")
	private Sales xReference;

	@Column(name = "STATUS_DATE")
	@Temporal(TemporalType.DATE)
	private Date statusDate;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "RECONFIRM_STATUS", referencedColumnName = "RECONFIRM_STATUS")
	private ReconfirmStatus reconfirmStatus;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "UW_STATUS", referencedColumnName = "UW_STATUS")
	private UwStatus uwStatus;

	@Column(name = "POLICY_STATUS")
	private String policyStatus;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public FileImport getFileImport()
	{
		return fileImport;
	}

	public void setFileImport(FileImport fileImport)
	{
		this.fileImport = fileImport;
	}

	public Date getBatchDate()
	{
		return batchDate;
	}

	public void setBatchDate(Date batchDate)
	{
		this.batchDate = batchDate;
	}

	public Sales getxReference()
	{
		return xReference;
	}

	public void setxReference(Sales xReference)
	{
		this.xReference = xReference;
	}

	public Date getStatusDate()
	{
		return statusDate;
	}

	public void setStatusDate(Date statusDate)
	{
		this.statusDate = statusDate;
	}

	public ReconfirmStatus getReconfirmStatus()
	{
		return reconfirmStatus;
	}

	public void setReconfirmStatus(ReconfirmStatus reconfirmStatus)
	{
		this.reconfirmStatus = reconfirmStatus;
	}

	public UwStatus getUwStatus()
	{
		return uwStatus;
	}

	public void setUwStatus(UwStatus uwStatus)
	{
		this.uwStatus = uwStatus;
	}

	public String getPolicyStatus()
	{
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus)
	{
		this.policyStatus = policyStatus;
	}

	@Override
	public String toString()
	{
		return "SalesProcess [id=" + id + ", fileImport=" + fileImport + ", batchDate=" + batchDate + ", xReference=" + xReference + ", statusDate=" + statusDate + ", reconfirmStatus=" + reconfirmStatus + ", uwStatus=" + uwStatus + ", policyStatus=" + policyStatus + "]";
	}

}
