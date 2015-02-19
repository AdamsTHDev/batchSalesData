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

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "DDOP_MAPPING_RESULT")
@NamedNativeQueries({ @NamedNativeQuery(name = "findDdopMappingResultByxReference", query = "select * from DDOP_MAPPING_RESULT where X_REFERENCE = ?", resultClass = DdopMappingResult.class),
	@NamedNativeQuery(name = "findDdopMappingResultByxRefAndBatchDate", query = "select * from DDOP_MAPPING_RESULT where X_REFERENCE = ? and BATCH_DATE = ?", resultClass = DdopMappingResult.class)})
public class DdopMappingResult extends BaseAuditDomain {

	private static final long serialVersionUID = 489281996770891209L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "IMPORT_ID")
	private FileImport fileImport;

	@ManyToOne
	@JoinColumn(name = "X_REFERENCE", referencedColumnName = "X_REFERENCE")
	private Sales xReference;

	@Column(name = "LAST_4_DIGIT_ACCOUNT_NO")
	private String last4DigitAccountNo;

	@OneToOne
	@JoinColumn(name = "MAPPING_STATUS", referencedColumnName = "MAPPING_STATUS")
	private DdopMappingStatus mappingStatus;

	@Column(name = "PLAN_CODE")
	private String planCode;

	@Column(name = "KBANK_CAMPAIGN_CODE")
	private String kbankCampaignCode;

	@Column(name = "CALL_DATE")
	@Temporal(TemporalType.DATE)
	private Date callDate;

	@Column(name = "BATCH_DATE")
	@Temporal(TemporalType.DATE)
	private Date batchDate;

	@Column(name = "SEND_TO_KBANK_DATE")
	@Temporal(TemporalType.DATE)
	private Date sendToBankDate;

	@Column(name = "APPROVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date approveDate;

	@Column(name = "REJECT_DATE")
	@Temporal(TemporalType.DATE)
	private Date rejectDate;

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

	public String getLast4DigitAccountNo()
	{
		return last4DigitAccountNo;
	}

	public void setLast4DigitAccountNo(String last4DigitAccountNo)
	{
		this.last4DigitAccountNo = last4DigitAccountNo;
	}

	public DdopMappingStatus getMappingStatus()
	{
		return mappingStatus;
	}

	public void setMappingStatus(DdopMappingStatus mappingStatus)
	{
		this.mappingStatus = mappingStatus;
	}

	public String getPlanCode()
	{
		return planCode;
	}

	public void setPlanCode(String planCode)
	{
		this.planCode = planCode;
	}

	public String getKbankCampaignCode()
	{
		return kbankCampaignCode;
	}

	public void setKbankCampaignCode(String kbankCampaignCode)
	{
		this.kbankCampaignCode = kbankCampaignCode;
	}

	public Date getCallDate()
	{
		return callDate;
	}

	public void setCallDate(Date callDate)
	{
		this.callDate = callDate;
	}

	public Date getBatchDate()
	{
		return batchDate;
	}

	public void setBatchDate(Date batchDate)
	{
		this.batchDate = batchDate;
	}

	public Date getSendToBankDate()
	{
		return sendToBankDate;
	}

	public void setSendToBankDate(Date sendToBankDate)
	{
		this.sendToBankDate = sendToBankDate;
	}

	public Date getApproveDate()
	{
		return approveDate;
	}

	public void setApproveDate(Date approveDate)
	{
		this.approveDate = approveDate;
	}

	public Date getRejectDate()
	{
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate)
	{
		this.rejectDate = rejectDate;
	}

	@Override
	public String toString()
	{
		return "DdopMappingResult [id=" + id + ", fileImport=" + fileImport + ", xReference=" + xReference + ", last4DigitAccountNo=" + last4DigitAccountNo + ", mappingStatus=" + mappingStatus + ", planCode=" + planCode + ", kbankCampaignCode=" + kbankCampaignCode + ", callDate=" + callDate
				+ ", batchDate=" + batchDate + ", sendToBankDate=" + sendToBankDate + ", approveDate=" + approveDate + ", rejectDate=" + rejectDate + "]";
	}

}
