package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "")
@NamedNativeQueries({ @NamedNativeQuery(name = "findQcReconfirmRecord", query = "exec [dbo].[DAILY_QC_RECONFIRM_RECORD] ?", resultClass = DailyQcReconfirmRecord.class), })
public class DailyQcReconfirmRecord extends BaseDomain {

	private static final long serialVersionUID = -650098434753623768L;

	@Id
	@Column(name = "RecordId")
	private String recordId;

	@Column(name = "Campaign")
	private String campaignName;

	@Column(name = "ListLot")
	private String listLotName;

	@Column(name = "X-Ref")
	private String xReference;

	@Column(name = "Sale Date")
	private String saleDate;

	@Column(name = "Customer Name")
	private String customerFullName;

	@Column(name = "TMR Code")
	private String tsrCode;

	@Column(name = "TSR Name")
	private String tsrFullName;

	@Column(name = "Manager")
	private String supFullName;

	@Column(name = "QC Date")
	private String qcStatusDate;

	@Column(name = "QC ID")
	private String qcId;

	@Column(name = "Payment")
	private String paymentMethod;

	@Column(name = "premium")
	private String premium;

	@Column(name = "TSRStatus")
	private String tsrStatus;

	@Column(name = "QCStatus")
	private String qcStatus;

	@Column(name = "ReconfirmReason")
	private String reconfirmReason;

	@Column(name = "ReconfirmRemark")
	private String reconfirmRemark;

	@Column(name = "CurrentReason")
	private String currentReason;

	@Column(name = "CurrentRemark")
	private String currentRemark;

	public String getRecordId()
	{
		return recordId;
	}

	public void setRecordId(String recordId)
	{
		this.recordId = recordId;
	}

	public String getCampaignName()
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName)
	{
		this.campaignName = campaignName;
	}

	public String getListLotName()
	{
		return listLotName;
	}

	public void setListLotName(String listLotName)
	{
		this.listLotName = listLotName;
	}

	public String getxReference()
	{
		return xReference;
	}

	public void setxReference(String xReference)
	{
		this.xReference = xReference;
	}

	public String getSaleDate()
	{
		return saleDate;
	}

	public void setSaleDate(String saleDate)
	{
		this.saleDate = saleDate;
	}

	public String getCustomerFullName()
	{
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName)
	{
		this.customerFullName = customerFullName;
	}

	public String getTsrCode()
	{
		return tsrCode;
	}

	public void setTsrCode(String tsrCode)
	{
		this.tsrCode = tsrCode;
	}

	public String getTsrFullName()
	{
		return tsrFullName;
	}

	public void setTsrFullName(String tsrFullName)
	{
		this.tsrFullName = tsrFullName;
	}

	public String getSupFullName()
	{
		return supFullName;
	}

	public void setSupFullName(String supFullName)
	{
		this.supFullName = supFullName;
	}

	public String getQcStatusDate()
	{
		return qcStatusDate;
	}

	public void setQcStatusDate(String qcStatusDate)
	{
		this.qcStatusDate = qcStatusDate;
	}

	public String getQcId()
	{
		return qcId;
	}

	public void setQcId(String qcId)
	{
		this.qcId = qcId;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public String getPremium()
	{
		return premium;
	}

	public void setPremium(String premium)
	{
		this.premium = premium;
	}

	public String getTsrStatus()
	{
		return tsrStatus;
	}

	public void setTsrStatus(String tsrStatus)
	{
		this.tsrStatus = tsrStatus;
	}

	public String getQcStatus()
	{
		return qcStatus;
	}

	public void setQcStatus(String qcStatus)
	{
		this.qcStatus = qcStatus;
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
		return "DailyQcReconfirmRecord [recordId=" + recordId + ", campaignName=" + campaignName + ", listLotName=" + listLotName + ", xReference=" + xReference + ", saleDate=" + saleDate + ", customerFullName=" + customerFullName + ", tsrCode=" + tsrCode + ", tsrFullName=" + tsrFullName
				+ ", supFullName=" + supFullName + ", qcStatusDate=" + qcStatusDate + ", qcId=" + qcId + ", paymentMethod=" + paymentMethod + ", premium=" + premium + ", tsrStatus=" + tsrStatus + ", qcStatus=" + qcStatus + ", reconfirmReason=" + reconfirmReason + ", reconfirmRemark="
				+ reconfirmRemark + ", currentReason=" + currentReason + ", currentRemark=" + currentRemark + "]";
	}

}
