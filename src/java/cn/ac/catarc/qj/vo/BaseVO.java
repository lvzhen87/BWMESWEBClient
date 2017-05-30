/*
 * @(#) BaseVO.java 2016-6-30 下午10:08:28
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package cn.ac.catarc.qj.vo;

import javax.persistence.Entity;

@Entity
public class BaseVO
{
	// 主键
	private long key = -1;
	
	private int queryNum=10;

	private String category;

	String uda0;

	String uda1;

	String uda2;

	String uda3;

	String uda4;

	String uda5;

	String uda6;

	String uda7;

	String uda8;

	String uda9;

	public long getKey()
	{
		return key;
	}

	public void setKey(long key)
	{
		this.key = Long.valueOf(key);
	}

	public String getUda0()
	{
		return uda0;
	}

	public String getUda1()
	{
		return uda1;
	}

	public String getUda2()
	{
		return uda2;
	}

	public String getUda3()
	{
		return uda3;
	}

	public String getUda4()
	{
		return uda4;
	}

	public String getUda5()
	{
		return uda5;
	}

	public String getUda6()
	{
		return uda6;
	}

	public String getUda7()
	{
		return uda7;
	}

	public String getUda8()
	{
		return uda8;
	}

	public String getUda9()
	{
		return uda9;
	}

	public void setUda0(String uda0)
	{
		this.uda0 = uda0;
	}

	public void setUda1(String uda1)
	{
		this.uda1 = uda1;
	}

	public void setUda2(String uda2)
	{
		this.uda2 = uda2;
	}

	public void setUda3(String uda3)
	{
		this.uda3 = uda3;
	}

	public void setUda4(String uda4)
	{
		this.uda4 = uda4;
	}

	public void setUda5(String uda5)
	{
		this.uda5 = uda5;
	}

	public void setUda6(String uda6)
	{
		this.uda6 = uda6;
	}

	public void setUda7(String uda7)
	{
		this.uda7 = uda7;
	}

	public void setUda8(String uda8)
	{
		this.uda8 = uda8;
	}

	public void setUda9(String uda9)
	{
		this.uda9 = uda9;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @param category
	 *            The category to set.
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	public int getQueryNum() {
		return queryNum;
	}

	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}
	
}
