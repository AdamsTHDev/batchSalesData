package com.adms.batch.sales.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

/**
 * @author kampon.pan
 *
 */
@Entity
@Table(name = "BILLING_RESULT")
@NamedNativeQueries({ @NamedNativeQuery(name = "findDailyPerformanceTrackingByCampaign", query = "exec [dbo].[DAILY_PERFORMANCE_TRACKING] ?, ?", resultClass = DailyPerformanceTracking.class),
	@NamedNativeQuery(name = "findDailyPerformanceKeyCodeByCampaign", query = "select [Campaign Code] + KeyCode + [Date] as [Record ID], NULL as [LOT_EFFECTIVE_DATE], NULL as [Section], NULL as [TYP], NULL as [TSR No], NULL as [Total Lead Used], NULL as [Total Completed], NULL as [SPC], NULL as [Sale Pending], NULL as [Sale], NULL as [Referral Lead], NULL as [New Lead Used / TSR], NULL as [New Lead Used], NULL as [New Lead Remaining], NULL as [List Conv#], NULL as [Lead Load], NULL as [Print Date], NULL as [To Date], NULL as [From Date], NULL as [Follow up (Remaining)], NULL as [Date], NULL as [Contact], NULL as [Campaign Name], NULL as [Campaign Info], NULL as [Campaign Code], NULL as [Callback/ Nocontact (Remaining)], NULL as [Callback / Nocontact / Follow Used], NULL as [BP Name], NULL as [BP CR%], NULL as [AYP], NULL as [AMP], dp.KeyCode from [dbo].[daily_performance_tracking_report_cons] dp left outer join LIST_LOT ll on dp.KeyCode = ll.LIST_LOT_CODE where [Campaign Code] like ? order by ll.LOT_EFFECTIVE_DATE asc", resultClass = DailyPerformanceTracking.class)})
public class DailyPerformanceTracking extends BaseDomain {

	private static final long serialVersionUID = 4640567838018338096L;

	@Id
	@Column(name = "Record ID")
	private String recordId;

	@Column(name = "Section")
	private Integer section;

	@Column(name = "Date")
	private String date;

	@Column(name = "TSR No")
	private BigDecimal tsrNo;

	@Column(name = "New Lead Used / TSR")
	private BigDecimal newLeadUsedPerTsr;

	@Column(name = "New Lead Used")
	private BigDecimal newLeadUsed;

	@Column(name = "Callback / Nocontact / Follow Used")
	private BigDecimal callbackNocontactFollowUsed;

	@Column(name = "Total Lead Used")
	private BigDecimal totalLeadUsed;

	@Column(name = "Callback/ Nocontact (Remaining)")
	private BigDecimal callbackNocontactRemaining;

	@Column(name = "Follow up (Remaining)")
	private BigDecimal followUpRemaining;

	@Column(name = "Total Completed")
	private BigDecimal totalCompleted;

	@Column(name = "Contact")
	private BigDecimal contact;

	@Column(name = "Sale")
	private BigDecimal sale;

	@Column(name = "BP CR%")
	private BigDecimal bpCr;

	@Column(name = "SPC")
	private BigDecimal spc;

	@Column(name = "List Conv#")
	private BigDecimal listConversion;

	@Column(name = "TYP")
	private BigDecimal typ;

	@Column(name = "AMP")
	private BigDecimal amp;

	@Column(name = "AYP")
	private BigDecimal ayp;

	@Column(name = "Campaign Code")
	private String campaignCode;

	@Column(name = "Campaign Name")
	private String campaignName;

	@Column(name = "Campaign Info")
	private String campaignInfo;

	@Column(name = "KeyCode")
	private String keyCode;

	@Column(name = "LOT_EFFECTIVE_DATE")
	private Date lotEffectiveDate;

	@Column(name = "BP Name")
	private String bpName;

	@Column(name = "From Date")
	private Date formDate;

	@Column(name = "To Date")
	private Date toDate;

	@Column(name = "Print Date")
	private Date printDate;

	@Column(name = "Lead Load")
	private BigDecimal leadLoad;

	@Column(name = "New Lead Remaining")
	private BigDecimal newLeadRemaining;

	@Column(name = "Referral Lead")
	private BigDecimal referralLead;

	@Column(name = "Sale Pending")
	private BigDecimal salePending;

	public String getRecordId()
	{
		return recordId;
	}

	public void setRecordId(String recordId)
	{
		this.recordId = recordId;
	}

	public Integer getSection()
	{
		return section;
	}

	public void setSection(Integer section)
	{
		this.section = section;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public BigDecimal getTsrNo()
	{
		return tsrNo;
	}

	public void setTsrNo(BigDecimal tsrNo)
	{
		this.tsrNo = tsrNo;
	}

	public BigDecimal getNewLeadUsedPerTsr()
	{
		return newLeadUsedPerTsr;
	}

	public void setNewLeadUsedPerTsr(BigDecimal newLeadUsedPerTsr)
	{
		this.newLeadUsedPerTsr = newLeadUsedPerTsr;
	}

	public BigDecimal getNewLeadUsed()
	{
		return newLeadUsed;
	}

	public void setNewLeadUsed(BigDecimal newLeadUsed)
	{
		this.newLeadUsed = newLeadUsed;
	}

	public BigDecimal getCallbackNocontactFollowUsed()
	{
		return callbackNocontactFollowUsed;
	}

	public void setCallbackNocontactFollowUsed(BigDecimal callbackNocontactFollowUsed)
	{
		this.callbackNocontactFollowUsed = callbackNocontactFollowUsed;
	}

	public BigDecimal getTotalLeadUsed()
	{
		return totalLeadUsed;
	}

	public void setTotalLeadUsed(BigDecimal totalLeadUsed)
	{
		this.totalLeadUsed = totalLeadUsed;
	}

	public BigDecimal getCallbackNocontactRemaining()
	{
		return callbackNocontactRemaining;
	}

	public void setCallbackNocontactRemaining(BigDecimal callbackNocontactRemaining)
	{
		this.callbackNocontactRemaining = callbackNocontactRemaining;
	}

	public BigDecimal getFollowUpRemaining()
	{
		return followUpRemaining;
	}

	public void setFollowUpRemaining(BigDecimal followUpRemaining)
	{
		this.followUpRemaining = followUpRemaining;
	}

	public BigDecimal getTotalCompleted()
	{
		return totalCompleted;
	}

	public void setTotalCompleted(BigDecimal totalCompleted)
	{
		this.totalCompleted = totalCompleted;
	}

	public BigDecimal getContact()
	{
		return contact;
	}

	public void setContact(BigDecimal contact)
	{
		this.contact = contact;
	}

	public BigDecimal getSale()
	{
		return sale;
	}

	public void setSale(BigDecimal sale)
	{
		this.sale = sale;
	}

	public BigDecimal getBpCr()
	{
		return bpCr;
	}

	public void setBpCr(BigDecimal bpCr)
	{
		this.bpCr = bpCr;
	}

	public BigDecimal getSpc()
	{
		return spc;
	}

	public void setSpc(BigDecimal spc)
	{
		this.spc = spc;
	}

	public BigDecimal getListConversion()
	{
		return listConversion;
	}

	public void setListConversion(BigDecimal listConversion)
	{
		this.listConversion = listConversion;
	}

	public BigDecimal getTyp()
	{
		return typ;
	}

	public void setTyp(BigDecimal typ)
	{
		this.typ = typ;
	}

	public BigDecimal getAmp()
	{
		return amp;
	}

	public void setAmp(BigDecimal amp)
	{
		this.amp = amp;
	}

	public BigDecimal getAyp()
	{
		return ayp;
	}

	public void setAyp(BigDecimal ayp)
	{
		this.ayp = ayp;
	}

	public String getCampaignCode()
	{
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode)
	{
		this.campaignCode = campaignCode;
	}

	public String getCampaignName()
	{
		return campaignName;
	}

	public void setCampaignName(String campaignName)
	{
		this.campaignName = campaignName;
	}

	public String getCampaignInfo()
	{
		return campaignInfo;
	}

	public void setCampaignInfo(String campaignInfo)
	{
		this.campaignInfo = campaignInfo;
	}

	public String getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(String keyCode)
	{
		this.keyCode = keyCode;
	}

	public Date getLotEffectiveDate()
	{
		return lotEffectiveDate;
	}

	public void setLotEffectiveDate(Date lotEffectiveDate)
	{
		this.lotEffectiveDate = lotEffectiveDate;
	}

	public String getBpName()
	{
		return bpName;
	}

	public void setBpName(String bpName)
	{
		this.bpName = bpName;
	}

	public Date getFormDate()
	{
		return formDate;
	}

	public void setFormDate(Date formDate)
	{
		this.formDate = formDate;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}

	public Date getPrintDate()
	{
		return printDate;
	}

	public void setPrintDate(Date printDate)
	{
		this.printDate = printDate;
	}

	public BigDecimal getLeadLoad()
	{
		return leadLoad;
	}

	public void setLeadLoad(BigDecimal leadLoad)
	{
		this.leadLoad = leadLoad;
	}

	public BigDecimal getNewLeadRemaining()
	{
		return newLeadRemaining;
	}

	public void setNewLeadRemaining(BigDecimal newLeadRemaining)
	{
		this.newLeadRemaining = newLeadRemaining;
	}

	public BigDecimal getReferralLead()
	{
		return referralLead;
	}

	public void setReferralLead(BigDecimal referralLead)
	{
		this.referralLead = referralLead;
	}

	public BigDecimal getSalePending()
	{
		return salePending;
	}

	public void setSalePending(BigDecimal salePending)
	{
		this.salePending = salePending;
	}

	@Override
	public String toString()
	{
		return "DailyPerformanceTracking [recordId=" + recordId + ", section=" + section + ", date=" + date + ", tsrNo=" + tsrNo + ", newLeadUsedPerTsr=" + newLeadUsedPerTsr + ", newLeadUsed=" + newLeadUsed + ", callbackNocontactFollowUsed=" + callbackNocontactFollowUsed + ", totalLeadUsed="
				+ totalLeadUsed + ", callbackNocontactRemaining=" + callbackNocontactRemaining + ", followUpRemaining=" + followUpRemaining + ", totalCompleted=" + totalCompleted + ", contact=" + contact + ", sale=" + sale + ", bpCr=" + bpCr + ", spc=" + spc + ", listConversion=" + listConversion
				+ ", typ=" + typ + ", amp=" + amp + ", ayp=" + ayp + ", campaignCode=" + campaignCode + ", campaignName=" + campaignName + ", campaignInfo=" + campaignInfo + ", keyCode=" + keyCode + ", lotEffectiveDate=" + lotEffectiveDate + ", bpName=" + bpName + ", formDate=" + formDate
				+ ", toDate=" + toDate + ", printDate=" + printDate + ", leadLoad=" + leadLoad + ", newLeadRemaining=" + newLeadRemaining + ", referralLead=" + referralLead + ", salePending=" + salePending + "]";
	}

}
