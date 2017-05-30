
package com.mes.webclient.controller.vo;

import java.sql.Time;
import java.util.Date;

public class QMDefectVO extends BaseVO
{
	//一级代码
	private String vin;
	
	private String vechileNum;
	//二级代码
	private String quality_gate_1;
	
	//三级代码
	private String quality_gate_2;

	//四级代码
	private String code1;

	//五级代码
	private String code2;

	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	
	private String inputUser;
	
	private String responsible;
	
	private String defectLevel;
	
	private String orderNum;
	
	private String shift;
	
	private Date input_time;
	
	private String memo;
	
	private String isAccess;
	
	private String checkDate;

	public String getVin()
	{
		return vin;
	}

	public void setVin(String vin)
	{
		this.vin = vin;
	}

	public String getVechileNum()
	{
		return vechileNum;
	}

	public void setVechileNum(String vechileNum)
	{
		this.vechileNum = vechileNum;
	}

	public String getQuality_gate_1()
	{
		return quality_gate_1;
	}

	public void setQuality_gate_1(String quality_gate_1)
	{
		this.quality_gate_1 = quality_gate_1;
	}

	public String getQuality_gate_2()
	{
		return quality_gate_2;
	}

	public void setQuality_gate_2(String quality_gate_2)
	{
		this.quality_gate_2 = quality_gate_2;
	}

	public String getCode1()
	{
		return code1;
	}

	public void setCode1(String code1)
	{
		this.code1 = code1;
	}

	public String getCode2()
	{
		return code2;
	}

	public void setCode2(String code2)
	{
		this.code2 = code2;
	}

	public String getLevel1()
	{
		return level1;
	}

	public void setLevel1(String level1)
	{
		this.level1 = level1;
	}

	public String getLevel2()
	{
		return level2;
	}

	public void setLevel2(String level2)
	{
		this.level2 = level2;
	}

	public String getLevel3()
	{
		return level3;
	}

	public void setLevel3(String level3)
	{
		this.level3 = level3;
	}

	public String getLevel4()
	{
		return level4;
	}

	public void setLevel4(String level4)
	{
		this.level4 = level4;
	}

	public String getLevel5()
	{
		return level5;
	}

	public void setLevel5(String level5)
	{
		this.level5 = level5;
	}

	public String getInputUser()
	{
		return inputUser;
	}

	public void setInputUser(String inputUser)
	{
		this.inputUser = inputUser;
	}

	public String getResponsible()
	{
		return responsible;
	}

	public void setResponsible(String responsible)
	{
		this.responsible = responsible;
	}

	public String getDefectLevel()
	{
		return defectLevel;
	}

	public void setDefectLevel(String defectLevel)
	{
		this.defectLevel = defectLevel;
	}

	public String getOrderNum()
	{
		return orderNum;
	}

	public void setOrderNum(String orderNum)
	{
		this.orderNum = orderNum;
	}

	public String getShift()
	{
		return shift;
	}

	public void setShift(String shift)
	{
		this.shift = shift;
	}

	public Date getInput_time()
	{
		return input_time;
	}

	public void setInput_time(Date input_time)
	{
		this.input_time = input_time;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String getIsAccess()
	{
		return isAccess;
	}

	public void setIsAccess(String isAccess)
	{
		this.isAccess = isAccess;
	}

	public String getCheckDate()
	{
		return checkDate;
	}

	public void setCheckDate(String checkDate)
	{
		this.checkDate = checkDate;
	}
	
	
}
