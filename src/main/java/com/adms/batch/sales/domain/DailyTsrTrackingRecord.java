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
@NamedNativeQueries({ @NamedNativeQuery(name = "findTsrTrackingRecord", query = "exec [dbo].[DAILY_TSR_TRACKING_RECORD] ?", resultClass = DailyTsrTrackingRecord.class),
	@NamedNativeQuery(name = "findTsrTrackingSummary", query = "exec [dbo].[DAILY_TSR_TRACKING_SUMMARY] ?", resultClass = DailyTsrTrackingRecord.class)})
public class DailyTsrTrackingRecord extends BaseDomain {

	private static final long serialVersionUID = 2043602262240688295L;
	@Id
	@Column(name = "RecordId")
	private String recordId;

	@Column(name = "No#")
	private Integer itemNo;

	@Column(name = "Name")
	private String tsrName;

	@Column(name = "Work Days")
	private Integer workDays;

	@Column(name = "List Used")
	private Integer listUsed;

	@Column(name = "Comp#")
	private Integer completed;
	
	@Column(name = "Remaining New")
	private Integer remainingNew;
	
	@Column(name = "Remaining Call back")
	private Integer remainingCallBack;
	
	@Column(name = "Remaining No Contact")
	private Integer remainingNoContact;
	
	@Column(name = "Remaining Follow up")
	private Integer remainingFollowUp;
	
	@Column(name = "Remaining Total")
	private Integer remainingTotal;
	
	@Column(name = "New Used")
	private Integer newUsed;
	
	@Column(name = "Follow Used")
	private Integer followUsed;
	
	@Column(name = "Call Back Used")
	private Integer callBackUsed;
	
	@Column(name = "No Contact Used")
	private Integer noContactUsed;
	
	@Column(name = "Policy 1 st")
	private Integer policyFirst;
	
	@Column(name = "Policy %")
	private BigDecimal policyPercent;
	
	@Column(name = "Policy Follow")
	private Integer policyFollow;
	
	@Column(name = "Plan Standard")
	private Integer planStandard;
	
	@Column(name = "Plan Premier")
	private Integer planPremier;
	
	@Column(name = "Total Policy")
	private Integer totalPolicy;
	
	@Column(name = "Policy API")
	private BigDecimal policyApi;
	
	@Column(name = "Total DMC Present")
	private Integer totalDmcPresent;
	
	@Column(name = "Total DMC No Present")
	private Integer totalDmcNoPresent;
	
	@Column(name = "DMC Contact Rate (60%)")
	private BigDecimal dmcContactRate;
	
	@Column(name = "CPH")
	private BigDecimal cph;
	
	@Column(name = "SPH")
	private BigDecimal sph;
	
	@Column(name = "SPC (Total)")
	private BigDecimal spcTotal;
	
	@Column(name = "SPC (Present)")
	private BigDecimal spcPresent;
	
	@Column(name = "Hours (Hrs)")
	private BigDecimal hours;
	
	@Column(name = "DMC# Avg# Talk Time")
	private BigDecimal dmcAverageTalkTime;
	
	@Column(name = "Max# Talk Time (Min#)")
	private BigDecimal maxTalkTime;
	
	@Column(name = "DMC Talk Time (Hr#)")
	private BigDecimal dmcTalkTime;
	
	@Column(name = "Total Talk Time (Hr#)")
	private BigDecimal totalTalkTime;

	@Column(name = "Campaign")
	private String campaign;

	@Column(name = "Key Code")
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

	public Integer getItemNo()
	{
		return itemNo;
	}

	public void setItemNo(Integer itemNo)
	{
		this.itemNo = itemNo;
	}

	public String getTsrName()
	{
		return tsrName;
	}

	public void setTsrName(String tsrName)
	{
		this.tsrName = tsrName;
	}

	public Integer getWorkDays()
	{
		return workDays;
	}

	public void setWorkDays(Integer workDays)
	{
		this.workDays = workDays;
	}

	public Integer getListUsed()
	{
		return listUsed;
	}

	public void setListUsed(Integer listUsed)
	{
		this.listUsed = listUsed;
	}

	public Integer getCompleted()
	{
		return completed;
	}

	public void setCompleted(Integer completed)
	{
		this.completed = completed;
	}

	public Integer getRemainingNew()
	{
		return remainingNew;
	}

	public void setRemainingNew(Integer remainingNew)
	{
		this.remainingNew = remainingNew;
	}

	public Integer getRemainingCallBack()
	{
		return remainingCallBack;
	}

	public void setRemainingCallBack(Integer remainingCallBack)
	{
		this.remainingCallBack = remainingCallBack;
	}

	public Integer getRemainingNoContact()
	{
		return remainingNoContact;
	}

	public void setRemainingNoContact(Integer remainingNoContact)
	{
		this.remainingNoContact = remainingNoContact;
	}

	public Integer getRemainingFollowUp()
	{
		return remainingFollowUp;
	}

	public void setRemainingFollowUp(Integer remainingFollowUp)
	{
		this.remainingFollowUp = remainingFollowUp;
	}

	public Integer getRemainingTotal()
	{
		return remainingTotal;
	}

	public void setRemainingTotal(Integer remainingTotal)
	{
		this.remainingTotal = remainingTotal;
	}

	public Integer getNewUsed()
	{
		return newUsed;
	}

	public void setNewUsed(Integer newUsed)
	{
		this.newUsed = newUsed;
	}

	public Integer getFollowUsed()
	{
		return followUsed;
	}

	public void setFollowUsed(Integer followUsed)
	{
		this.followUsed = followUsed;
	}

	public Integer getCallBackUsed()
	{
		return callBackUsed;
	}

	public void setCallBackUsed(Integer callBackUsed)
	{
		this.callBackUsed = callBackUsed;
	}

	public Integer getNoContactUsed()
	{
		return noContactUsed;
	}

	public void setNoContactUsed(Integer noContactUsed)
	{
		this.noContactUsed = noContactUsed;
	}

	public Integer getPolicyFirst()
	{
		return policyFirst;
	}

	public void setPolicyFirst(Integer policyFirst)
	{
		this.policyFirst = policyFirst;
	}

	public BigDecimal getPolicyPercent()
	{
		return policyPercent;
	}

	public void setPolicyPercent(BigDecimal policyPercent)
	{
		this.policyPercent = policyPercent;
	}

	public Integer getPolicyFollow()
	{
		return policyFollow;
	}

	public void setPolicyFollow(Integer policyFollow)
	{
		this.policyFollow = policyFollow;
	}

	public Integer getPlanStandard()
	{
		return planStandard;
	}

	public void setPlanStandard(Integer planStandard)
	{
		this.planStandard = planStandard;
	}

	public Integer getPlanPremier()
	{
		return planPremier;
	}

	public void setPlanPremier(Integer planPremier)
	{
		this.planPremier = planPremier;
	}

	public Integer getTotalPolicy()
	{
		return totalPolicy;
	}

	public void setTotalPolicy(Integer totalPolicy)
	{
		this.totalPolicy = totalPolicy;
	}

	public BigDecimal getPolicyApi()
	{
		return policyApi;
	}

	public void setPolicyApi(BigDecimal policyApi)
	{
		this.policyApi = policyApi;
	}

	public Integer getTotalDmcPresent()
	{
		return totalDmcPresent;
	}

	public void setTotalDmcPresent(Integer totalDmcPresent)
	{
		this.totalDmcPresent = totalDmcPresent;
	}

	public Integer getTotalDmcNoPresent()
	{
		return totalDmcNoPresent;
	}

	public void setTotalDmcNoPresent(Integer totalDmcNoPresent)
	{
		this.totalDmcNoPresent = totalDmcNoPresent;
	}

	public BigDecimal getDmcContactRate()
	{
		return dmcContactRate;
	}

	public void setDmcContactRate(BigDecimal dmcContactRate)
	{
		this.dmcContactRate = dmcContactRate;
	}

	public BigDecimal getCph()
	{
		return cph;
	}

	public void setCph(BigDecimal cph)
	{
		this.cph = cph;
	}

	public BigDecimal getSph()
	{
		return sph;
	}

	public void setSph(BigDecimal sph)
	{
		this.sph = sph;
	}

	public BigDecimal getSpcTotal()
	{
		return spcTotal;
	}

	public void setSpcTotal(BigDecimal spcTotal)
	{
		this.spcTotal = spcTotal;
	}

	public BigDecimal getSpcPresent()
	{
		return spcPresent;
	}

	public void setSpcPresent(BigDecimal spcPresent)
	{
		this.spcPresent = spcPresent;
	}

	public BigDecimal getHours()
	{
		return hours;
	}

	public void setHours(BigDecimal hours)
	{
		this.hours = hours;
	}

	public BigDecimal getDmcAverageTalkTime()
	{
		return dmcAverageTalkTime;
	}

	public void setDmcAverageTalkTime(BigDecimal dmcAverageTalkTime)
	{
		this.dmcAverageTalkTime = dmcAverageTalkTime;
	}

	public BigDecimal getMaxTalkTime()
	{
		return maxTalkTime;
	}

	public void setMaxTalkTime(BigDecimal maxTalkTime)
	{
		this.maxTalkTime = maxTalkTime;
	}

	public BigDecimal getDmcTalkTime()
	{
		return dmcTalkTime;
	}

	public void setDmcTalkTime(BigDecimal dmcTalkTime)
	{
		this.dmcTalkTime = dmcTalkTime;
	}

	public BigDecimal getTotalTalkTime()
	{
		return totalTalkTime;
	}

	public void setTotalTalkTime(BigDecimal totalTalkTime)
	{
		this.totalTalkTime = totalTalkTime;
	}

	public String getCampaign()
	{
		return campaign;
	}

	public void setCampaign(String campaign)
	{
		this.campaign = campaign;
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
		return "DailyTsrTrackingRecord [recordId=" + recordId + ", itemNo=" + itemNo + ", tsrName=" + tsrName + ", workDays=" + workDays + ", listUsed=" + listUsed + ", completed=" + completed + ", remainingNew=" + remainingNew + ", remainingCallBack=" + remainingCallBack + ", remainingNoContact="
				+ remainingNoContact + ", remainingFollowUp=" + remainingFollowUp + ", remainingTotal=" + remainingTotal + ", newUsed=" + newUsed + ", followUsed=" + followUsed + ", callBackUsed=" + callBackUsed + ", noContactUsed=" + noContactUsed + ", policyFirst=" + policyFirst
				+ ", policyPercent=" + policyPercent + ", policyFollow=" + policyFollow + ", planStandard=" + planStandard + ", planPremier=" + planPremier + ", totalPolicy=" + totalPolicy + ", policyApi=" + policyApi + ", totalDmcPresent=" + totalDmcPresent + ", totalDmcNoPresent="
				+ totalDmcNoPresent + ", dmcContactRate=" + dmcContactRate + ", cph=" + cph + ", sph=" + sph + ", spcTotal=" + spcTotal + ", spcPresent=" + spcPresent + ", hours=" + hours + ", dmcAverageTalkTime=" + dmcAverageTalkTime + ", maxTalkTime=" + maxTalkTime + ", dmcTalkTime="
				+ dmcTalkTime + ", totalTalkTime=" + totalTalkTime + ", campaign=" + campaign + ", keyCode=" + keyCode + ", period=" + period + ", printDate=" + printDate + "]";
	}

}
