package com.adms.batch.sales.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "")
@NamedNativeQueries({ @NamedNativeQuery(name = "findDailySalesReportByRecord", query = "exec [dbo].[DAILY_SALES_REPORT_BY_RECORD] ?", resultClass = DailySalesReportByRecord.class),
	})
public class DailySalesReportByRecord extends BaseDomain {

	private static final long serialVersionUID = 6963858398033717829L;

	@Id
	@Column(name = "RecordId")
	private String recordId;

	@Column(name = "CampaignName")
	private String campaignName;

	@Column(name = "CampaignCode")
	private String campaignCode;

	@Column(name = "ListlotName")
	private String listLotName;

	@Column(name = "Approve Date")
	private String approveDate;

	@Column(name = "X-Ref")
	private String xReference;

	@Column(name = "No")
	private Integer itemNo;

	@Column(name = "Customer")
	private String customerFullName;

	@Column(name = "Product")
	private String product;

	@Column(name = "Premium")
	private BigDecimal premium;

	@Column(name = "AFYP")
	private BigDecimal annualPremium;

	@Column(name = "protectAmount")
	private String protectAmount;

	@Column(name = "Payment Channel")
	private String paymentChannel;

	@Column(name = "Mode Of Payment")
	private String paymentFrequency;

	@Column(name = "QA Status")
	private String qaStatus;

	@Column(name = "QA Reason Status")
	private String qaReason;

	@Column(name = "Sale Date")
	private String saleDate;

	@Column(name = "TMR Code")
	private String tsrCode;

	@Column(name = "TSR")
	private String tsrName;

	@Column(name = "TMR Code Manager")
	private String supName;

	@Column(name = "Manager Name")
	private String supCode;

	@Column(name = "Reason Detail")
	private String qaReasonDetail;

	@Column(name = "ListLot")
	private String listLot;

	@Column(name = "KeyCode")
	private String keyCode;

	@Column(name = "Period")
	private String period;

	@Column(name = "Print Date")
	private String printDate;

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

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getListLotName()
	{
		return listLotName;
	}

	public void setListLotName(String listLotName)
	{
		this.listLotName = listLotName;
	}

	public String getApproveDate()
	{
		return approveDate;
	}

	public void setApproveDate(String approveDate)
	{
		this.approveDate = approveDate;
	}

	public String getxReference()
	{
		return xReference;
	}

	public void setxReference(String xReference)
	{
		this.xReference = xReference;
	}

	public Integer getItemNo()
	{
		return itemNo;
	}

	public void setItemNo(Integer itemNo)
	{
		this.itemNo = itemNo;
	}

	public String getCustomerFullName()
	{
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName)
	{
		this.customerFullName = customerFullName;
	}

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public BigDecimal getPremium()
	{
		return premium;
	}

	public void setPremium(BigDecimal premium)
	{
		this.premium = premium;
	}

	public BigDecimal getAnnualPremium()
	{
		return annualPremium;
	}

	public void setAnnualPremium(BigDecimal annualPremium)
	{
		this.annualPremium = annualPremium;
	}

	public String getProtectAmount()
	{
		return protectAmount;
	}

	public void setProtectAmount(String protectAmount)
	{
		this.protectAmount = protectAmount;
	}

	public String getPaymentChannel()
	{
		return paymentChannel;
	}

	public void setPaymentChannel(String paymentChannel)
	{
		this.paymentChannel = paymentChannel;
	}

	public String getPaymentFrequency()
	{
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency)
	{
		this.paymentFrequency = paymentFrequency;
	}

	public String getQaStatus()
	{
		return qaStatus;
	}

	public void setQaStatus(String qaStatus)
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

	public String getSaleDate()
	{
		return saleDate;
	}

	public void setSaleDate(String saleDate)
	{
		this.saleDate = saleDate;
	}

	public String getTsrCode()
	{
		return tsrCode;
	}

	public void setTsrCode(String tsrCode)
	{
		this.tsrCode = tsrCode;
	}

	public String getTsrName()
	{
		return tsrName;
	}

	public void setTsrName(String tsrName)
	{
		this.tsrName = tsrName;
	}

	public String getSupName()
	{
		return supName;
	}

	public void setSupName(String supName)
	{
		this.supName = supName;
	}

	public String getSupCode()
	{
		return supCode;
	}

	public void setSupCode(String supCode)
	{
		this.supCode = supCode;
	}

	public String getQaReasonDetail()
	{
		return qaReasonDetail;
	}

	public void setQaReasonDetail(String qaReasonDetail)
	{
		this.qaReasonDetail = qaReasonDetail;
	}

	public String getListLot()
	{
		return listLot;
	}

	public void setListLot(String listLot)
	{
		this.listLot = listLot;
	}

	public String getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(String keyCode)
	{
		this.keyCode = keyCode;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}

	public String getPrintDate()
	{
		return printDate;
	}

	public void setPrintDate(String printDate)
	{
		this.printDate = printDate;
	}

	@Override
	public String toString()
	{
		return "DailySalesReportByRecord [recordId=" + recordId + ", campaignName=" + campaignName + ", campaignCode=" + campaignCode + ", listLotName=" + listLotName + ", approveDate=" + approveDate + ", xReference=" + xReference + ", itemNo=" + itemNo + ", customerFullName=" + customerFullName
				+ ", product=" + product + ", premium=" + premium + ", annualPremium=" + annualPremium + ", protectAmount=" + protectAmount + ", paymentChannel=" + paymentChannel + ", paymentFrequency=" + paymentFrequency + ", qaStatus=" + qaStatus + ", qaReason=" + qaReason + ", saleDate="
				+ saleDate + ", tsrCode=" + tsrCode + ", tsrName=" + tsrName + ", supName=" + supName + ", supCode=" + supCode + ", qaReasonDetail=" + qaReasonDetail + ", listLot=" + listLot + ", keyCode=" + keyCode + ", period=" + period + ", printDate=" + printDate + "]";
	}

}
