
package com.mes.webclient.controller.vo;

public class PlanScheduleSubPropsVO extends BaseVO
{
	private String subname;
	
	private String subattribute;
	
	private long subpripority;
	
	private long parentKey;
	
	private long subBatch;

	public String getSubname()
	{
		return subname;
	}

	public void setSubname(String subname)
	{
		this.subname = subname;
	}

	public String getSubattribute()
	{
		return subattribute;
	}

	public void setSubattribute(String subattribute)
	{
		this.subattribute = subattribute;
	}

	public long getSubpripority()
	{
		return subpripority;
	}

	public void setSubpripority(long subpripority)
	{
		this.subpripority = subpripority;
	}

	public long getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(long parentKey)
	{
		this.parentKey = parentKey;
	}

	public long getSubBatch()
	{
		return subBatch;
	}

	public void setSubBatch(long subBatch)
	{
		this.subBatch = subBatch;
	}

	
}
