
package com.mes.webclient.controller.vo;

public class PlanSchedulePropsVO extends BaseVO
{
	private String name;
	
	private String factory;

	private String workshop;

	private String attribute;
	
	private int batch;
	
	private int pripority;
	
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getFactory()
	{
		return factory;
	}

	public void setFactory(String factory)
	{
		this.factory = factory;
	}

	public String getWorkshop()
	{
		return workshop;
	}

	public void setWorkshop(String workshop)
	{
		this.workshop = workshop;
	}

	public String getAttribute()
	{
		return attribute;
	}

	public void setAttribute(String attribute)
	{
		this.attribute = attribute;
	}

	public int getBatch()
	{
		return batch;
	}

	public void setBatch(int batch)
	{
		this.batch = batch;
	}

	public int getPripority()
	{
		return pripority;
	}

	public void setPripority(int pripority)
	{
		this.pripority = pripority;
	}


}
