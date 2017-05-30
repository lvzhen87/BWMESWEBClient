package com.mes.webclient.controller.vo;

public class InstoreStaticsVO extends BaseVO
{
	private String part_no;
	
	private String part_name;

	private String part_type;
	
	private String supplier;
	
	private int num;

	public String getPart_no()
	{
		return part_no;
	}

	public void setPart_no(String part_no)
	{
		this.part_no = part_no;
	}

	public String getPart_name()
	{
		return part_name;
	}

	public void setPart_name(String part_name)
	{
		this.part_name = part_name;
	}

	public String getPart_type()
	{
		return part_type;
	}

	public void setPart_type(String part_type)
	{
		this.part_type = part_type;
	}

	public String getSupplier()
	{
		return supplier;
	}

	public void setSupplier(String supplier)
	{
		this.supplier = supplier;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}
	
	
}
