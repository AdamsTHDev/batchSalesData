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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.adms.common.domain.BaseAuditDomain;

/**
 * @author kampon.pan
 *
 */
@Entity
@Table(name = "UW_RESULT")
@NamedNativeQueries({ @NamedNativeQuery(name = "findUwResultByxReference", query = "select * from UW_RESULT where X_REFERENCE = ?", resultClass = UwResult.class) })
public class UwResult extends BaseAuditDomain {

	private static final long serialVersionUID = 3357080972310231447L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "IMPORT_ID")
	private FileImport fileImport;

	@OneToOne
	@JoinColumn(name = "X_REFERENCE", referencedColumnName = "X_REFERENCE")
	private Sales xReference;

	@Column(name = "APP_SUBMIT_DATE")
	@Temporal(TemporalType.DATE)
	private Date appSubmitDate;

	@Column(name = "ITEM_NO")
	private Integer itemNo;

	@Column(name = "UW_SUBMIT_DATE")
	@Temporal(TemporalType.DATE)
	private Date uwSubmitDate;

	@Column(name = "UW_RESULT_DATE")
	@Temporal(TemporalType.DATE)
	private Date uwResultDate;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "UW_DECISION", referencedColumnName = "UW_DECISION")
	private UwDecision uwDecision;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "UW_STATUS", referencedColumnName = "UW_STATUS")
	private UwStatus uwStatus;

	@Column(name = "UW_REMARK")
	private String uwRemark;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "COF_STATUS", referencedColumnName = "COF_STATUS")
	private CofStatus cofStatus;

	@Column(name = "COF_ISSUE_DATE")
	@Temporal(TemporalType.DATE)
	private Date cofIssueDate;

	@Column(name = "COF_DUE_DATE")
	@Temporal(TemporalType.DATE)
	private Date cofDueDate;

	@Column(name = "COF_DATE")
	@Temporal(TemporalType.DATE)
	private Date cofDate;

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

	public Sales getxReference()
	{
		return xReference;
	}

	public void setxReference(Sales xReference)
	{
		this.xReference = xReference;
	}

	public Date getAppSubmitDate()
	{
		return appSubmitDate;
	}

	public void setAppSubmitDate(Date appSubmitDate)
	{
		this.appSubmitDate = appSubmitDate;
	}

	public Integer getItemNo()
	{
		return itemNo;
	}

	public void setItemNo(Integer itemNo)
	{
		this.itemNo = itemNo;
	}

	public Date getUwSubmitDate()
	{
		return uwSubmitDate;
	}

	public void setUwSubmitDate(Date uwSubmitDate)
	{
		this.uwSubmitDate = uwSubmitDate;
	}

	public Date getUwResultDate()
	{
		return uwResultDate;
	}

	public void setUwResultDate(Date uwResultDate)
	{
		this.uwResultDate = uwResultDate;
	}

	public UwDecision getUwDecision()
	{
		return uwDecision;
	}

	public void setUwDecision(UwDecision uwDecision)
	{
		this.uwDecision = uwDecision;
	}

	public UwStatus getUwStatus()
	{
		return uwStatus;
	}

	public void setUwStatus(UwStatus uwStatus)
	{
		this.uwStatus = uwStatus;
	}

	public String getUwRemark()
	{
		return uwRemark;
	}

	public void setUwRemark(String uwRemark)
	{
		this.uwRemark = uwRemark;
	}

	public Date getCofIssueDate()
	{
		return cofIssueDate;
	}

	public void setCofIssueDate(Date cofIssueDate)
	{
		this.cofIssueDate = cofIssueDate;
	}

	public Date getCofDueDate()
	{
		return cofDueDate;
	}

	public void setCofDueDate(Date cofDueDate)
	{
		this.cofDueDate = cofDueDate;
	}

	public CofStatus getCofStatus()
	{
		return cofStatus;
	}

	public void setCofStatus(CofStatus cofStatus)
	{
		this.cofStatus = cofStatus;
	}

	public Date getCofDate()
	{
		return cofDate;
	}

	public void setCofDate(Date cofDate)
	{
		this.cofDate = cofDate;
	}

	@Override
	public String toString()
	{
		return "UwResult [id=" + id + ", fileImport=" + fileImport + ", xReference=" + xReference + ", appSubmitDate=" + appSubmitDate + ", itemNo=" + itemNo + ", uwSubmitDate=" + uwSubmitDate + ", uwResultDate=" + uwResultDate + ", uwDecision=" + uwDecision + ", uwStatus=" + uwStatus
				+ ", uwRemark=" + uwRemark + ", cofStatus=" + cofStatus + ", cofIssueDate=" + cofIssueDate + ", cofDueDate=" + cofDueDate + ", cofDate=" + cofDate + "]";
	}

}
