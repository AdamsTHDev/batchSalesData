package com.adms.batch.persistency.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "Rpt_Output_Persistency_Report")
@NamedNativeQueries({
	@NamedNativeQuery(name = "execReportDataQuery", query = "EXEC [11_Report_data_query]", resultClass = OutputPersistency.class),
	@NamedNativeQuery(name = "execReportDataCalculation", query = "EXEC [1_Generate Lapse Study table] ?", resultClass = OutputPersistency.class)})
public class OutputPersistency extends BaseDomain {

	private static final long serialVersionUID = 2329055742208851538L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "[Variable Name]")
	private String variableName;

	@Column(name = "[grouping_factor]")
	private String groupingFactor;

	@Column(name = "[production_IAP]")
	private BigDecimal productionIap;

	@Column(name = "[issued_IAP]")
	private BigDecimal issuedIap;

	@Column(name = "[Paid_IAP]")
	private BigDecimal paidIap;

	@Column(name = "[Issue Rate_IAP]")
	private BigDecimal issuedRateIap;

	@Column(name = "[Paid Rate_IAP]")
	private BigDecimal paidRateIap;

	@Column(name = "[3 Month_IAP]")
	private BigDecimal month3Iap;

	@Column(name = "[6 Month_IAP]")
	private BigDecimal month6Iap;

	@Column(name = "[9 Month_IAP]")
	private BigDecimal month9Iap;

	@Column(name = "[12 Month_IAP]")
	private BigDecimal month12Iap;

	@Column(name = "[18 Month_IAP]")
	private BigDecimal month18Iap;

	@Column(name = "[2 Year_IAP]")
	private BigDecimal year2Iap;

	@Column(name = "[3 Year_IAP]")
	private BigDecimal year3Iap;

	@Column(name = "[4 Year_IAP]")
	private BigDecimal year4Iap;

	@Column(name = "[5 Year_IAP]")
	private BigDecimal year5Iap;

	@Column(name = "[6 Year_IAP]")
	private BigDecimal year6Iap;

	@Column(name = "[7 Year_IAP]")
	private BigDecimal year7Iap;

	@Column(name = "[8 Year_IAP]")
	private BigDecimal year8Iap;

	@Column(name = "[9 Year_IAP]")
	private BigDecimal year9Iap;

	@Column(name = "[10 Year_IAP]")
	private BigDecimal year10Iap;

	@Column(name = "[production]")
	private BigDecimal production;

	@Column(name = "[issued]")
	private BigDecimal issued;

	@Column(name = "[Exposure]")
	private BigDecimal exposure;

	@Column(name = "[Issue Rate]")
	private BigDecimal issuedRate;

	@Column(name = "[Paid Rate]")
	private BigDecimal paidRate;

	@Column(name = "[3 Month]")
	private BigDecimal month3;

	@Column(name = "[6 Month]")
	private BigDecimal month6;

	@Column(name = "[9 Month]")
	private BigDecimal month9;

	@Column(name = "[12 Month]")
	private BigDecimal month12;

	@Column(name = "[18 Month]")
	private BigDecimal month18;

	@Column(name = "[2 Year]")
	private BigDecimal year2;

	@Column(name = "[3 Year]")
	private BigDecimal year3;

	@Column(name = "[4 Year]")
	private BigDecimal year4;

	@Column(name = "[5 Year]")
	private BigDecimal year5;

	@Column(name = "[6 Year]")
	private BigDecimal year6;

	@Column(name = "[7 Year]")
	private BigDecimal year7;

	@Column(name = "[8 Year]")
	private BigDecimal year8;

	@Column(name = "[9 Year]")
	private BigDecimal year9;

	@Column(name = "[10 Year]")
	private BigDecimal year10;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getVariableName()
	{
		return variableName;
	}

	public void setVariableName(String variableName)
	{
		this.variableName = variableName;
	}

	public String getGroupingFactor()
	{
		return groupingFactor;
	}

	public void setGroupingFactor(String groupingFactor)
	{
		this.groupingFactor = groupingFactor;
	}

	public BigDecimal getProductionIap()
	{
		return productionIap;
	}

	public void setProductionIap(BigDecimal productionIap)
	{
		this.productionIap = productionIap;
	}

	public BigDecimal getIssuedIap()
	{
		return issuedIap;
	}

	public void setIssuedIap(BigDecimal issuedIap)
	{
		this.issuedIap = issuedIap;
	}

	public BigDecimal getPaidIap()
	{
		return paidIap;
	}

	public void setPaidIap(BigDecimal paidIap)
	{
		this.paidIap = paidIap;
	}

	public BigDecimal getIssuedRateIap()
	{
		return issuedRateIap;
	}

	public void setIssuedRateIap(BigDecimal issuedRateIap)
	{
		this.issuedRateIap = issuedRateIap;
	}

	public BigDecimal getPaidRateIap()
	{
		return paidRateIap;
	}

	public void setPaidRateIap(BigDecimal paidRateIap)
	{
		this.paidRateIap = paidRateIap;
	}

	public BigDecimal getMonth3Iap()
	{
		return month3Iap;
	}

	public void setMonth3Iap(BigDecimal month3Iap)
	{
		this.month3Iap = month3Iap;
	}

	public BigDecimal getMonth6Iap()
	{
		return month6Iap;
	}

	public void setMonth6Iap(BigDecimal month6Iap)
	{
		this.month6Iap = month6Iap;
	}

	public BigDecimal getMonth9Iap()
	{
		return month9Iap;
	}

	public void setMonth9Iap(BigDecimal month9Iap)
	{
		this.month9Iap = month9Iap;
	}

	public BigDecimal getMonth12Iap()
	{
		return month12Iap;
	}

	public void setMonth12Iap(BigDecimal month12Iap)
	{
		this.month12Iap = month12Iap;
	}

	public BigDecimal getMonth18Iap()
	{
		return month18Iap;
	}

	public void setMonth18Iap(BigDecimal month18Iap)
	{
		this.month18Iap = month18Iap;
	}

	public BigDecimal getYear2Iap()
	{
		return year2Iap;
	}

	public void setYear2Iap(BigDecimal year2Iap)
	{
		this.year2Iap = year2Iap;
	}

	public BigDecimal getYear3Iap()
	{
		return year3Iap;
	}

	public void setYear3Iap(BigDecimal year3Iap)
	{
		this.year3Iap = year3Iap;
	}

	public BigDecimal getYear4Iap()
	{
		return year4Iap;
	}

	public void setYear4Iap(BigDecimal year4Iap)
	{
		this.year4Iap = year4Iap;
	}

	public BigDecimal getYear5Iap()
	{
		return year5Iap;
	}

	public void setYear5Iap(BigDecimal year5Iap)
	{
		this.year5Iap = year5Iap;
	}

	public BigDecimal getYear6Iap()
	{
		return year6Iap;
	}

	public void setYear6Iap(BigDecimal year6Iap)
	{
		this.year6Iap = year6Iap;
	}

	public BigDecimal getYear7Iap()
	{
		return year7Iap;
	}

	public void setYear7Iap(BigDecimal year7Iap)
	{
		this.year7Iap = year7Iap;
	}

	public BigDecimal getYear8Iap()
	{
		return year8Iap;
	}

	public void setYear8Iap(BigDecimal year8Iap)
	{
		this.year8Iap = year8Iap;
	}

	public BigDecimal getYear9Iap()
	{
		return year9Iap;
	}

	public void setYear9Iap(BigDecimal year9Iap)
	{
		this.year9Iap = year9Iap;
	}

	public BigDecimal getYear10Iap()
	{
		return year10Iap;
	}

	public void setYear10Iap(BigDecimal year10Iap)
	{
		this.year10Iap = year10Iap;
	}

	public BigDecimal getProduction()
	{
		return production;
	}

	public void setProduction(BigDecimal production)
	{
		this.production = production;
	}

	public BigDecimal getIssued()
	{
		return issued;
	}

	public void setIssued(BigDecimal issued)
	{
		this.issued = issued;
	}

	public BigDecimal getExposure()
	{
		return exposure;
	}

	public void setExposure(BigDecimal exposure)
	{
		this.exposure = exposure;
	}

	public BigDecimal getIssuedRate()
	{
		return issuedRate;
	}

	public void setIssuedRate(BigDecimal issuedRate)
	{
		this.issuedRate = issuedRate;
	}

	public BigDecimal getPaidRate()
	{
		return paidRate;
	}

	public void setPaidRate(BigDecimal paidRate)
	{
		this.paidRate = paidRate;
	}

	public BigDecimal getMonth3()
	{
		return month3;
	}

	public void setMonth3(BigDecimal month3)
	{
		this.month3 = month3;
	}

	public BigDecimal getMonth6()
	{
		return month6;
	}

	public void setMonth6(BigDecimal month6)
	{
		this.month6 = month6;
	}

	public BigDecimal getMonth9()
	{
		return month9;
	}

	public void setMonth9(BigDecimal month9)
	{
		this.month9 = month9;
	}

	public BigDecimal getMonth12()
	{
		return month12;
	}

	public void setMonth12(BigDecimal month12)
	{
		this.month12 = month12;
	}

	public BigDecimal getMonth18()
	{
		return month18;
	}

	public void setMonth18(BigDecimal month18)
	{
		this.month18 = month18;
	}

	public BigDecimal getYear2()
	{
		return year2;
	}

	public void setYear2(BigDecimal year2)
	{
		this.year2 = year2;
	}

	public BigDecimal getYear3()
	{
		return year3;
	}

	public void setYear3(BigDecimal year3)
	{
		this.year3 = year3;
	}

	public BigDecimal getYear4()
	{
		return year4;
	}

	public void setYear4(BigDecimal year4)
	{
		this.year4 = year4;
	}

	public BigDecimal getYear5()
	{
		return year5;
	}

	public void setYear5(BigDecimal year5)
	{
		this.year5 = year5;
	}

	public BigDecimal getYear6()
	{
		return year6;
	}

	public void setYear6(BigDecimal year6)
	{
		this.year6 = year6;
	}

	public BigDecimal getYear7()
	{
		return year7;
	}

	public void setYear7(BigDecimal year7)
	{
		this.year7 = year7;
	}

	public BigDecimal getYear8()
	{
		return year8;
	}

	public void setYear8(BigDecimal year8)
	{
		this.year8 = year8;
	}

	public BigDecimal getYear9()
	{
		return year9;
	}

	public void setYear9(BigDecimal year9)
	{
		this.year9 = year9;
	}

	public BigDecimal getYear10()
	{
		return year10;
	}

	public void setYear10(BigDecimal year10)
	{
		this.year10 = year10;
	}

	@Override
	public String toString()
	{
		return "OutputPersistency [id=" + id + ", variableName=" + variableName + ", groupingFactor=" + groupingFactor + ", productionIap=" + productionIap + ", issuedIap=" + issuedIap + ", paidIap=" + paidIap + ", issuedRateIap=" + issuedRateIap + ", paidRateIap=" + paidRateIap + ", month3Iap="
				+ month3Iap + ", month6Iap=" + month6Iap + ", month9Iap=" + month9Iap + ", month12Iap=" + month12Iap + ", month18Iap=" + month18Iap + ", year2Iap=" + year2Iap + ", year3Iap=" + year3Iap + ", year4Iap=" + year4Iap + ", year5Iap=" + year5Iap + ", year6Iap=" + year6Iap + ", year7Iap="
				+ year7Iap + ", year8Iap=" + year8Iap + ", year9Iap=" + year9Iap + ", year10Iap=" + year10Iap + ", production=" + production + ", issued=" + issued + ", exposure=" + exposure + ", issuedRate=" + issuedRate + ", paidRate=" + paidRate + ", month3=" + month3 + ", month6=" + month6
				+ ", month9=" + month9 + ", month12=" + month12 + ", month18=" + month18 + ", year2=" + year2 + ", year3=" + year3 + ", year4=" + year4 + ", year5=" + year5 + ", year6=" + year6 + ", year7=" + year7 + ", year8=" + year8 + ", year9=" + year9 + ", year10=" + year10 + "]";
	}

}
