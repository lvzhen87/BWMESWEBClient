
package com.mes.webclient.controller.vo;

public class PlanScheduleConVO extends BaseVO
{
//	private long con_a;
//	
//	private long con_b;
	
	private String factory;
	
	private String workshop;
	
	private String type;
	
	private String subattru_a;
	
	private String subattru_b;
	
	private long interval;

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

	public String getSubattru_a()
	{
		return subattru_a;
	}

	public void setSubattru_a(String subattru_a)
	{
		this.subattru_a = subattru_a;
	}

	public String getSubattru_b()
	{
		return subattru_b;
	}

	public void setSubattru_b(String subattru_b)
	{
		this.subattru_b = subattru_b;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}


	
}
