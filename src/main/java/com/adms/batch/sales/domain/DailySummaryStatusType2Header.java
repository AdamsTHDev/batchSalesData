package com.adms.batch.sales.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "V_RPT_SUMMARY_STATUS_TYPE_2_H")
@NamedNativeQueries({ @NamedNativeQuery(name = "findDailySummaryStatusType2ByCampaignAndProcessDate", query = "exec [dbo].[DAILY_PERFORMANCE_TRACKING] ?, ?", resultClass = DailySummaryStatusType2Header.class),
	@NamedNativeQuery(name = "findDailyPerformanceKeyCodeByCampaign", query = "select [Campaign Code] + KeyCode + [Date] as [Record ID], NULL as [LOT_EFFECTIVE_DATE], NULL as [Section], NULL as [TYP], NULL as [TSR No], NULL as [Total Lead Used], NULL as [Total Completed], NULL as [SPC], NULL as [Sale Pending], NULL as [Sale], NULL as [Referral Lead], NULL as [New Lead Used / TSR], NULL as [New Lead Used], NULL as [New Lead Remaining], NULL as [List Conv#], NULL as [Lead Load], NULL as [Print Date], NULL as [To Date], NULL as [From Date], NULL as [Follow up (Remaining)], NULL as [Date], NULL as [Contact], NULL as [Campaign Name], NULL as [Campaign Info], NULL as [Campaign Code], NULL as [Callback/ Nocontact (Remaining)], NULL as [Callback / Nocontact / Follow Used], NULL as [BP Name], NULL as [BP CR%], NULL as [AYP], NULL as [AMP], dp.KeyCode from [dbo].[daily_performance_tracking_report_cons] dp left outer join LIST_LOT ll on dp.KeyCode = ll.LIST_LOT_CODE where [Campaign Code] like ? order by ll.LOT_EFFECTIVE_DATE asc", resultClass = DailySummaryStatusType2Header.class)})
public class DailySummaryStatusType2Header extends BaseDomain {

	private static final long serialVersionUID = 4640567838018338096L;

	@Id
	@Column(name = "RecordI")
	private String recordId;

	@Column(name = "Project")
	private String project;

	@Column(name = "Campaign")
	private String campaign;

	@Column(name = "ListLot")
	private String listLot;

	@Column(name = "Status")
	private String status;

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

	public String getProject()
	{
		return project;
	}

	public void setProject(String project)
	{
		this.project = project;
	}

	public String getCampaign()
	{
		return campaign;
	}

	public void setCampaign(String campaign)
	{
		this.campaign = campaign;
	}

	public String getListLot()
	{
		return listLot;
	}

	public void setListLot(String listLot)
	{
		this.listLot = listLot;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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
		return "DailySummaryStatusType2Header [recordId=" + recordId + ", project=" + project + ", campaign=" + campaign + ", listLot=" + listLot + ", status=" + status + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", printDate=" + printDate + "]";
	}

}
