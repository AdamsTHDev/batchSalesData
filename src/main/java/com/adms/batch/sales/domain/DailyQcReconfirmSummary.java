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
@NamedNativeQueries({ @NamedNativeQuery(name = "findQcReconfirmStatusSummary", query = "exec [dbo].[DAILY_QC_RECONFIRM_STATUS_SUMMARY] ?", resultClass = DailyQcReconfirmSummary.class),
		@NamedNativeQuery(name = "findQcReconfirmStatusTotal", query = "exec [dbo].[DAILY_QC_RECONFIRM_STATUS_TOTAL] ?", resultClass = DailyQcReconfirmSummary.class) })
public class DailyQcReconfirmSummary extends BaseDomain {

	private static final long serialVersionUID = 2458559782481947277L;

	@Id
	@Column(name = "QCStatus")
	private String qcStatus;

	@Column(name = "Total")
	private Integer total;

	public String getQcStatus()
	{
		return qcStatus;
	}

	public void setQcStatus(String qcStatus)
	{
		this.qcStatus = qcStatus;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	@Override
	public String toString()
	{
		return "DailyQcReconfirmSummary [qcStatus=" + qcStatus + ", total=" + total + "]";
	}

}
