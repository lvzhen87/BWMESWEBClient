
package com.mes.webclient.controller.vo;

public class QMVINCSNVO extends BaseVO
{
	//一级代码
	private String currentvin;

	//二级代码
	private String prevvin;
	
	private String nextvin;

	public String getCurrentvin()
	{
		return currentvin;
	}

	public void setCurrentvin(String currentvin)
	{
		this.currentvin = currentvin;
	}

	public String getPrevvin()
	{
		return prevvin;
	}

	public void setPrevvin(String prevvin)
	{
		this.prevvin = prevvin;
	}

	public String getNextvin()
	{
		return nextvin;
	}

	public void setNextvin(String nextvin)
	{
		this.nextvin = nextvin;
	}



}
