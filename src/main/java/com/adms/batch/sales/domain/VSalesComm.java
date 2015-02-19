package com.adms.batch.sales.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "V_SALES_COMM_ALL")
@NamedNativeQueries({ @NamedNativeQuery(name = "findSaleCommByCampaignName", query = "select * from V_SALES_COMM_ALL where CampaignName = ? order by [X-Ref]", resultClass = VSalesComm.class) })
public class VSalesComm extends BaseDomain {

	private static final long serialVersionUID = -1462971843929833181L;

	@Id
	@Column(name = "X-Ref")
	private String xReference;

	@Column(name = "CampaignName")
	private String campaignName;

	@Column(name = "CampaignCode")
	private String campaignCode;

	@Column(name = "ListlotName")
	private String listlotName;

	@Column(name = "Approve Date")
	@Temporal(TemporalType.DATE)
	private Date approveDate;

	@Column(name = "No")
	private Integer itemNo;

	@Column(name = "Customer")
	private String customerFullName;

	@Column(name = "PRODUCT")
	private String product;

	@Column(name = "PREMIUM")
	private BigDecimal premium;

	@Column(name = "AFYP")
	private BigDecimal annualPremium;

	@Column(name = "ProtectAmount")
	private BigDecimal protectAmount;

	@Column(name = "Payment Channel")
	private String paymentChannel;

	@Column(name = "Mode Of Payment")
	private String paymentFrequency;

	@Column(name = "Sale Date")
	@Temporal(TemporalType.DATE)
	private Date saleDate;

	@Column(name = "TMR Code")
	private String tsrCode;

	@Column(name = "TSR")
	private String tsrFullName;

	@Column(name = "TMR Code Manager")
	private String supCode;

	@Column(name = "Manager Name")
	private String supFullName;

	@Column(name = "QA Status")
	private String qaStatus;

	@Column(name = "QA Reason Status")
	private String qaReasonStatus;

	public String getxReference()
	{
		return xReference;
	}

	public void setxReference(String xReference)
	{
		this.xReference = xReference;
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

	public String getListlotName()
	{
		return listlotName;
	}

	public void setListlotName(String listlotName)
	{
		this.listlotName = listlotName;
	}

	public Date getApproveDate()
	{
		return approveDate;
	}

	public void setApproveDate(Date approveDate)
	{
		this.approveDate = approveDate;
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

	public BigDecimal getProtectAmount()
	{
		return protectAmount;
	}

	public void setProtectAmount(BigDecimal protectAmount)
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

	public Date getSaleDate()
	{
		return saleDate;
	}

	public void setSaleDate(Date saleDate)
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

	public String getTsrFullName()
	{
		return tsrFullName;
	}

	public void setTsrFullName(String tsrFullName)
	{
		this.tsrFullName = tsrFullName;
	}

	public String getSupCode()
	{
		return supCode;
	}

	public void setSupCode(String supCode)
	{
		this.supCode = supCode;
	}

	public String getSupFullName()
	{
		return supFullName;
	}

	public void setSupFullName(String supFullName)
	{
		this.supFullName = supFullName;
	}

	public String getQaStatus()
	{
		return qaStatus;
	}

	public void setQaStatus(String qaStatus)
	{
		this.qaStatus = qaStatus;
	}

	public String getQaReasonStatus()
	{
		return qaReasonStatus;
	}

	public void setQaReasonStatus(String qaReasonStatus)
	{
		this.qaReasonStatus = qaReasonStatus;
	}

	@Override
	public String toString()
	{
		return "VSalesComm [xReference=" + xReference + ", campaignName=" + campaignName + ", campaignCode=" + campaignCode + ", listlotName=" + listlotName + ", approveDate=" + approveDate + ", itemNo=" + itemNo + ", customerFullName=" + customerFullName + ", product=" + product + ", premium="
				+ premium + ", annualPremium=" + annualPremium + ", protectAmount=" + protectAmount + ", paymentChannel=" + paymentChannel + ", paymentFrequency=" + paymentFrequency + ", saleDate=" + saleDate + ", tsrCode=" + tsrCode + ", tsrFullName=" + tsrFullName + ", supCode=" + supCode
				+ ", supFullName=" + supFullName + ", qaStatus=" + qaStatus + ", qaReasonStatus=" + qaReasonStatus + "]";
	}
}
