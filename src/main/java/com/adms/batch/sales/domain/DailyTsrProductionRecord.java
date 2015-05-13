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
@NamedNativeQueries({ @NamedNativeQuery(name = "findTsrProductionRecord", query = "SELECT tmrcode AS RecordId, * FROM SALES.dbo.TSR_PROD_DAILY_BYTSR_TMP", resultClass = DailyTsrProductionRecord.class),
	@NamedNativeQuery(name = "findTsrProductionSummary", query = "SELECT 'TEMP' AS RecordId, NULL AS [EMPLOYEE NAME], * FROM SALES.dbo.TSR_PROD_DAILY_TOTAL_TMP", resultClass = DailyTsrProductionRecord.class)})
public class DailyTsrProductionRecord extends BaseDomain {

	private static final long serialVersionUID = -5614695321723986445L;

	@Id
	@Column(name = "RecordId")
	private String recordId;

	@Column(name = "EMPLOYEE NAME")
	private String tsrFullName;

	@Column(name = "Working Day")
	private Integer workingDay;

	@Column(name = "Hours")
	private Integer hours;

	@Column(name = "Completed")
	private Integer completed;

	@Column(name = "Contacts")
	private Integer contacts;

	@Column(name = "Sales")
	private Integer sales;

	@Column(name = "Compl./Hours")
	private Integer complPerHours;

	@Column(name = "CPH")
	private Integer cph;

	@Column(name = "SPH")
	private Integer sph;

	@Column(name = "SPC")
	private Integer spc;

	@Column(name = "SPL")
	private Integer spl;

	@Column(name = "AMP")
	private Integer amp;

	@Column(name = "TMP")
	private Integer tmp;

	@Column(name = "Total API")
	private Integer totalApi;

	public String getRecordId()
	{
		return recordId;
	}

	public void setRecordId(String recordId)
	{
		this.recordId = recordId;
	}

	public String getTsrFullName()
	{
		return tsrFullName;
	}

	public void setTsrFullName(String tsrFullName)
	{
		this.tsrFullName = tsrFullName;
	}

	public Integer getWorkingDay()
	{
		return workingDay;
	}

	public void setWorkingDay(Integer workingDay)
	{
		this.workingDay = workingDay;
	}

	public Integer getHours()
	{
		return hours;
	}

	public void setHours(Integer hours)
	{
		this.hours = hours;
	}

	public Integer getCompleted()
	{
		return completed;
	}

	public void setCompleted(Integer completed)
	{
		this.completed = completed;
	}

	public Integer getContacts()
	{
		return contacts;
	}

	public void setContacts(Integer contacts)
	{
		this.contacts = contacts;
	}

	public Integer getSales()
	{
		return sales;
	}

	public void setSales(Integer sales)
	{
		this.sales = sales;
	}

	public Integer getComplPerHours()
	{
		return complPerHours;
	}

	public void setComplPerHours(Integer complPerHours)
	{
		this.complPerHours = complPerHours;
	}

	public Integer getCph()
	{
		return cph;
	}

	public void setCph(Integer cph)
	{
		this.cph = cph;
	}

	public Integer getSph()
	{
		return sph;
	}

	public void setSph(Integer sph)
	{
		this.sph = sph;
	}

	public Integer getSpc()
	{
		return spc;
	}

	public void setSpc(Integer spc)
	{
		this.spc = spc;
	}

	public Integer getSpl()
	{
		return spl;
	}

	public void setSpl(Integer spl)
	{
		this.spl = spl;
	}

	public Integer getAmp()
	{
		return amp;
	}

	public void setAmp(Integer amp)
	{
		this.amp = amp;
	}

	public Integer getTmp()
	{
		return tmp;
	}

	public void setTmp(Integer tmp)
	{
		this.tmp = tmp;
	}

	public Integer getTotalApi()
	{
		return totalApi;
	}

	public void setTotalApi(Integer totalApi)
	{
		this.totalApi = totalApi;
	}

	@Override
	public String toString()
	{
		return "DailyTsrProductionRecord [recordId=" + recordId + ", tsrFullName=" + tsrFullName + ", workingDay=" + workingDay + ", hours=" + hours + ", completed=" + completed + ", contacts=" + contacts + ", sales=" + sales + ", complPerHours=" + complPerHours + ", cph=" + cph + ", sph=" + sph
				+ ", spc=" + spc + ", spl=" + spl + ", amp=" + amp + ", tmp=" + tmp + ", totalApi=" + totalApi + "]";
	}

}
