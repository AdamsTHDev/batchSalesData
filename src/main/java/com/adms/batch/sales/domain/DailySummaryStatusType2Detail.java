package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "V_RPT_DAILY_SUMMARY_STATUS_TYPE_2_D")
@NamedNativeQueries({ @NamedNativeQuery(name = "findByCampaignAndProcessDateAndKeyCode", query = "SELECT * FROM [SALES].[dbo].[V_RPT_DAILY_SUMMARY_STATUS_TYPE_2_D] where Campaign like ? and DateEnd like ? and KeyCode like ? order by KeyCode, SortMain, SortSub", resultClass = DailySummaryStatusType2Detail.class),
	@NamedNativeQuery(name = "findKeyCodeByCampaignAndProcessDate", query = "select distinct KeyCode as RecordId, KeyCode as KeyCode, null as Campaign, null as ReasonMain, null as ReasonSub, null as LotDescription, null as NoOfRecord, null as DateStart, null as DateEnd, null as PrintDate from [SALES].[dbo].[V_RPT_DAILY_SUMMARY_STATUS_TYPE_2_D] where Campaign like ? and DateEnd like ? order by KeyCode ", resultClass = DailySummaryStatusType2Detail.class),
	@NamedNativeQuery(name = "findLotDecriptionByKeyCode", query = "select distinct KeyCode as RecordId, KeyCode as KeyCode, null as Campaign, null as ReasonMain, null as ReasonSub, LotDescription + ' (' + KeyCode + ')' as LotDescription, null as NoOfRecord, null as DateStart, null as DateEnd, null as PrintDate from [SALES].[dbo].[V_RPT_DAILY_SUMMARY_STATUS_TYPE_2_D] where KeyCode like ?", resultClass = DailySummaryStatusType2Detail.class)})
public class DailySummaryStatusType2Detail extends BaseDomain {

	private static final long serialVersionUID = 4640567838018338096L;

	@Id
	@Column(name = "RecordId")
	private String recordId;

	@Column(name = "ReasonMain")
	private String reasonMain;

	@Column(name = "ReasonSub")
	private String reasonSub;

	@Column(name = "LotDescription")
	private String lotDescription;

	@Column(name = "KeyCode")
	private String keyCode;

	@Column(name = "NoOfRecord")
	private Integer noOfRecord;

	@Column(name = "DateStart")
	private String dateStart;

	@Column(name = "DateEnd")
	private String dateEnd;

	@Column(name = "PrintDate")
	private String printDate;

	public String getRecordId()
	{
		return recordId;
	}

	public void setRecordId(String recordId)
	{
		this.recordId = recordId;
	}

	public String getReasonMain()
	{
		return reasonMain;
	}

	public void setReasonMain(String reasonMain)
	{
		this.reasonMain = reasonMain;
	}

	public String getReasonSub()
	{
		return reasonSub;
	}

	public void setReasonSub(String reasonSub)
	{
		this.reasonSub = reasonSub;
	}

	public String getLotDescription()
	{
		return lotDescription;
	}

	public void setLotDescription(String lotDescription)
	{
		this.lotDescription = lotDescription;
	}

	public String getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(String keyCode)
	{
		this.keyCode = keyCode;
	}

	public Integer getNoOfRecord()
	{
		return noOfRecord;
	}

	public void setNoOfRecord(Integer noOfRecord)
	{
		this.noOfRecord = noOfRecord;
	}

	public String getDateStart()
	{
		return dateStart;
	}

	public void setDateStart(String dateStart)
	{
		this.dateStart = dateStart;
	}

	public String getDateEnd()
	{
		return dateEnd;
	}

	public void setDateEnd(String dateEnd)
	{
		this.dateEnd = dateEnd;
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
		return "DailySummaryStatusType2Detail [recordId=" + recordId + ", reasonMain=" + reasonMain + ", reasonSub=" + reasonSub + ", lotDescription=" + lotDescription + ", keyCode=" + keyCode + ", noOfRecord=" + noOfRecord + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", printDate="
				+ printDate + "]";
	}

}
