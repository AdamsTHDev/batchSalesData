package com.adms.batch.sales.domain;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.adms.common.domain.BaseDomain;

@Entity
@Table(name = "INCENTIVE_COMPOSITE")
@Cacheable
@NamedNativeQueries({ @NamedNativeQuery(name = "findByIncentiveAndCampaignCode", query = "select ic.* from INCENTIVE_COMPOSITE ic where ic.INCENTIVE_CODE = ? and ic.CAMPAIGN_CODE = ? ", resultClass = IncentiveComposite.class),
						@NamedNativeQuery(name = "findByIncentiveAndCompositeName", query = "select ic.* from INCENTIVE_COMPOSITE ic where ic.INCENTIVE_CODE = ? and ic.COMPOSITE_NAME = ? ", resultClass = IncentiveComposite.class)})
public class IncentiveComposite extends BaseDomain {

	private static final long serialVersionUID = -2385619720458024903L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "INCENTIVE_CODE", referencedColumnName = "INCENTIVE_CODE")
	private IncentiveInfo incentiveInfo;

	@ManyToOne
	@JoinColumn(name = "CAMPAIGN_CODE", referencedColumnName = "CAMPAIGN_CODE")
	private Campaign campaign;

	@Column(name = "COMPOSITE_NAME")
	private String compositeName;

	@Column(name = "TARGET_A")
	private BigDecimal targetA;

	@Column(name = "TARGET_B")
	private BigDecimal targetB;

	@Column(name = "TARGET_C")
	private BigDecimal targetC;

	@Column(name = "TARGET_D")
	private BigDecimal targetD;

	@Column(name = "TARGET_E")
	private BigDecimal targetE;

	@Column(name = "TARGET_F")
	private BigDecimal targetF;

	@Column(name = "TARGET_G")
	private BigDecimal targetG;

	@Column(name = "TARGET_H")
	private BigDecimal targetH;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public IncentiveInfo getIncentiveInfo()
	{
		return incentiveInfo;
	}

	public void setIncentiveInfo(IncentiveInfo incentiveInfo)
	{
		this.incentiveInfo = incentiveInfo;
	}

	public Campaign getCampaign()
	{
		return campaign;
	}

	public void setCampaign(Campaign campaign)
	{
		this.campaign = campaign;
	}

	public String getCompositeName()
	{
		return compositeName;
	}

	public void setCompositeName(String compositeName)
	{
		this.compositeName = compositeName;
	}

	public BigDecimal getTargetA()
	{
		return targetA;
	}

	public void setTargetA(BigDecimal targetA)
	{
		this.targetA = targetA;
	}

	public BigDecimal getTargetB()
	{
		return targetB;
	}

	public void setTargetB(BigDecimal targetB)
	{
		this.targetB = targetB;
	}

	public BigDecimal getTargetC()
	{
		return targetC;
	}

	public void setTargetC(BigDecimal targetC)
	{
		this.targetC = targetC;
	}

	public BigDecimal getTargetD()
	{
		return targetD;
	}

	public void setTargetD(BigDecimal targetD)
	{
		this.targetD = targetD;
	}

	public BigDecimal getTargetE()
	{
		return targetE;
	}

	public void setTargetE(BigDecimal targetE)
	{
		this.targetE = targetE;
	}

	public BigDecimal getTargetF()
	{
		return targetF;
	}

	public void setTargetF(BigDecimal targetF)
	{
		this.targetF = targetF;
	}

	public BigDecimal getTargetG()
	{
		return targetG;
	}

	public void setTargetG(BigDecimal targetG)
	{
		this.targetG = targetG;
	}

	public BigDecimal getTargetH()
	{
		return targetH;
	}

	public void setTargetH(BigDecimal targetH)
	{
		this.targetH = targetH;
	}

	@Override
	public String toString()
	{
		return "IncentiveComposite [id=" + id + ", incentiveInfo=" + incentiveInfo + ", campaign=" + campaign + ", compositeName=" + compositeName + "]";
	}

}
