package com.mes.webclient.controller.vo;

import java.io.Serializable;

import com.mes.common.db.agent.DBConnectionAdmin;

public class ComponentRegistration extends BaseVO
{
	
	 static final long serialVersionUID = -2942065870982137852L;
	

	Long key;
	
	String name;
	
	String oldname;
	
	String description;
	
	int componentType;

	public long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComponentType() {
		return componentType;
	}

	public void setComponentType(int componentType) {
		this.componentType = componentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
