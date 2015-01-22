package com.adms.batch.sales.domain;

import java.math.BigDecimal;
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
@Table(name = "BILLING_RESULT")
@NamedNativeQueries({ @NamedNativeQuery(name = "findBillingResultByxReference", query = "select * from BILLING_RESULT where X_REFERENCE = ?", resultClass = BillingResult.class),
	@NamedNativeQuery(name = "findBillingResultByxRefAndBillingDate", query = "select * from BILLING_RESULT where X_REFERENCE = ? and BILLING_DATE = ?", resultClass = BillingResult.class)})
public class BillingResult extends BaseAuditDomain {

	private static final long serialVersionUID = 3245633463235803855L;

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

	@Column(name = "ACCOUNT_NO")
	private String accountNo;

	@Column(name = "ACCOUNT_EXP")
	private String accountExp;

	@Column(name = "PREMIUM")
	private BigDecimal premium;

	@Column(name = "FIRST_PREMIUM")
	private BigDecimal firstPremium;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "PAYMENT_FREQUENCY", referencedColumnName = "FREQUENCY_CODE")
	private PaymentFrequency paymentFrequency;

	@Column(name = "BILLING_DATE")
	@Temporal(TemporalType.DATE)
	private Date billingDate;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "BILLING_STATUS", referencedColumnName = "BILLING_STATUS")
	private BillingStatus billingStatus;

	@Column(name = "REMARK")
	private String remark;

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

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getAccountExp()
	{
		return accountExp;
	}

	public void setAccountExp(String accountExp)
	{
		this.accountExp = accountExp;
	}

	public BillingStatus getBillingStatus()
	{
		return billingStatus;
	}

	public void setBillingStatus(BillingStatus billingStatus)
	{
		this.billingStatus = billingStatus;
	}

	public BigDecimal getPremium()
	{
		return premium;
	}

	public void setPremium(BigDecimal premium)
	{
		this.premium = premium;
	}

	public BigDecimal getFirstPremium()
	{
		return firstPremium;
	}

	public void setFirstPremium(BigDecimal firstPremium)
	{
		this.firstPremium = firstPremium;
	}

	public PaymentFrequency getPaymentFrequency()
	{
		return paymentFrequency;
	}

	public void setPaymentFrequency(PaymentFrequency paymentFrequency)
	{
		this.paymentFrequency = paymentFrequency;
	}

	public Date getBillingDate()
	{
		return billingDate;
	}

	public void setBillingDate(Date billingDate)
	{
		this.billingDate = billingDate;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String toString()
	{
		return "BillingResult [id=" + id + ", fileImport=" + fileImport + ", xReference=" + xReference + ", accountNo=" + accountNo + ", accountExp=" + accountExp + ", billingStatus=" + billingStatus + ", premium=" + premium + ", firstPremium=" + firstPremium + ", paymentFrequency="
				+ paymentFrequency + ", billingDate=" + billingDate + ", remark=" + remark + "]";
	}

}
