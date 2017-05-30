package com.mes.webclient.controller.vo;

import java.util.Vector;

public class ConditionVO extends BaseVO
{
	private String name;

	private String description;

	private String category;

	private String string1;

	private String string2;

	public Vector<Object> getSubObjs()
	{
		return subObjs;
	}

	public void setSubObjs(Vector<Object> subObjs)
	{
		this.subObjs = subObjs;
	}

	private String string3;

	Vector<Object> subObjs = new Vector<Object>();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public String getDescription() {
		return description;

	}



	public void setDescription(String description) {
		this.description = description;

	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getString1()
	{
		return string1;
	}

	public void setString1(String string1)
	{
		this.string1 = string1;
	}

	public String getString2()
	{
		return string2;
	}

	public void setString2(String string2)
	{
		this.string2 = string2;
	}

	public String getString3()
	{
		return string3;
	}

	public void setString3(String string3)
	{
		this.string3 = string3;
	}

}
