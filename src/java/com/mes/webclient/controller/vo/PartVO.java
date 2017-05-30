package com.mes.webclient.controller.vo;

public class PartVO extends BaseVO
{
	private String partNumber;

	private String partName;

	private String partRevision;

	private String consumptionType;

	private String category;

	private String uom;

	private String shelfLife;

	private String effectStart;

	private String effectEnd;

	public String getPartNumber()
	{
		return partNumber;
	}

	public void setPartNumber(String partNumber)
	{
		this.partNumber = partNumber;
	}

	public String getPartName()
	{
		return partName;
	}

	public void setPartName(String partName)
	{
		this.partName = partName;
	}

	public String getConsumptionType()
	{
		return consumptionType;
	}

	public void setConsumptionType(String consumptionType)
	{
		this.consumptionType = consumptionType;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getUom()
	{
		return uom;
	}

	public void setUom(String uom)
	{
		this.uom = uom;
	}


	public String getEffectStart()
	{
		return effectStart;
	}

	public void setEffectStart(String effectStart)
	{
		this.effectStart = effectStart;
	}

	public String getEffectEnd()
	{
		return effectEnd;
	}

	public void setEffectEnd(String effectEnd)
	{
		this.effectEnd = effectEnd;
	}

	public String getPartRevision()
	{
		return partRevision;
	}

	public void setPartRevision(String partRevision)
	{
		this.partRevision = partRevision;
	}

	@Override
	public String toString()
	{
		return "PartVO [partNumber=" + partNumber + ", partName=" + partName + "]";
	}

	public String getShelfLife()
	{
		return shelfLife;
	}

	public void setShelfLife(String shelfLife)
	{
		this.shelfLife = shelfLife;
	}

	

}
