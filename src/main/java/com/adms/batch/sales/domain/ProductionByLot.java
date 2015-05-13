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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "PRODUCTION_BY_LOT")
@NamedNativeQueries({@NamedNativeQuery(name = "findProductionByLotByListLotCodeAndProductionDate", query = "select * from PRODUCTION_BY_LOT where LIST_LOT_CODE = ? and PRODUCTION_DATE = ?", resultClass = ProductionByLot.class)})
public class ProductionByLot extends BaseAuditDomain {

	private static final long serialVersionUID = -9080876180767957030L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "IMPORT_ID")
	private FileImport fileImport;

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "LIST_LOT_CODE", referencedColumnName = "LIST_LOT_CODE")
	private ListLot listLot;

	@Column(name = "PRODUCTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date productionDate;

	@Column(name = "TOTAL_LEAD")
	private Integer totalLead;

	@Column(name = "REMAINING_LEAD")
	private Integer remainingLead;

	@Column(name = "HOUR")
	private Integer hour;

	@Column(name = "MINUTE")
	private Integer minute;

	@Column(name = "SECOND")
	private Integer second;

	@Column(name = "DIALING")
	private Integer dialing;

	@Column(name = "COMPLETED")
	private Integer completed;

	@Column(name = "CONTACT")
	private Integer contact;

	@Column(name = "SALES")
	private Integer sales;

	@Column(name = "ABANDONS")
	private Integer abandons;

	@Column(name = "UW_RELEASE_SALES")
	private Integer uwReleaseSales;

	@Column(name = "RELEASE_SALES")
	private Integer releaseSales;

	@Column(name = "DECLINES")
	private Integer declines;

	@Column(name = "TYP")
	private BigDecimal typ;

	@Column(name = "AMP_POST_UW")
	private BigDecimal ampPostUw;

	@Column(name = "TOTAL_COST")
	private BigDecimal totalCost;

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

	public ListLot getListLot()
	{
		return listLot;
	}

	public void setListLot(ListLot listLot)
	{
		this.listLot = listLot;
	}

	public Date getProductionDate()
	{
		return productionDate;
	}

	public void setProductionDate(Date productionDate)
	{
		this.productionDate = productionDate;
	}

	public Integer getTotalLead()
	{
		return totalLead;
	}

	public void setTotalLead(Integer totalLead)
	{
		this.totalLead = totalLead;
	}

	public Integer getRemainingLead()
	{
		return remainingLead;
	}

	public void setRemainingLead(Integer remainingLead)
	{
		this.remainingLead = remainingLead;
	}

	public Integer getHour()
	{
		return hour;
	}

	public void setHour(Integer hour)
	{
		this.hour = hour;
	}

	public Integer getMinute()
	{
		return minute;
	}

	public void setMinute(Integer minute)
	{
		this.minute = minute;
	}

	public Integer getSecond()
	{
		return second;
	}

	public void setSecond(Integer second)
	{
		this.second = second;
	}

	public Integer getDialing()
	{
		return dialing;
	}

	public void setDialing(Integer dialing)
	{
		this.dialing = dialing;
	}

	public Integer getCompleted()
	{
		return completed;
	}

	public void setCompleted(Integer completed)
	{
		this.completed = completed;
	}

	public Integer getContact()
	{
		return contact;
	}

	public void setContact(Integer contact)
	{
		this.contact = contact;
	}

	public Integer getSales()
	{
		return sales;
	}

	public void setSales(Integer sales)
	{
		this.sales = sales;
	}

	public Integer getAbandons()
	{
		return abandons;
	}

	public void setAbandons(Integer abandons)
	{
		this.abandons = abandons;
	}

	public Integer getUwReleaseSales()
	{
		return uwReleaseSales;
	}

	public void setUwReleaseSales(Integer uwReleaseSales)
	{
		this.uwReleaseSales = uwReleaseSales;
	}

	public Integer getReleaseSales()
	{
		return releaseSales;
	}

	public void setReleaseSales(Integer releaseSales)
	{
		this.releaseSales = releaseSales;
	}

	public Integer getDeclines()
	{
		return declines;
	}

	public void setDeclines(Integer declines)
	{
		this.declines = declines;
	}

	public BigDecimal getTyp()
	{
		return typ;
	}

	public void setTyp(BigDecimal typ)
	{
		this.typ = typ;
	}

	public BigDecimal getAmpPostUw()
	{
		return ampPostUw;
	}

	public void setAmpPostUw(BigDecimal ampPostUw)
	{
		this.ampPostUw = ampPostUw;
	}

	public BigDecimal getTotalCost()
	{
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost)
	{
		this.totalCost = totalCost;
	}

	@Override
	public String toString()
	{
		return "ProductionByLot [id=" + id + ", fileImport=" + fileImport + ", listLot=" + listLot + ", productionDate=" + productionDate + ", totalLead=" + totalLead + ", remainingLead=" + remainingLead + ", hour=" + hour + ", minute=" + minute + ", second=" + second + ", dialing=" + dialing
				+ ", completed=" + completed + ", contact=" + contact + ", sales=" + sales + ", abandons=" + abandons + ", uwReleaseSales=" + uwReleaseSales + ", releaseSales=" + releaseSales + ", declines=" + declines + ", typ=" + typ + ", ampPostUw=" + ampPostUw + ", totalCost=" + totalCost + "]";
	}

}
