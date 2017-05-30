package cn.ac.catarc.qj.dobject;

public class DVINSequence
{
	private String yearCode;

	private String lineCode;

	private String modelCode;

	private int minValue;

	private int maxValue;

	private int value;

	private String sequenceName;

	public String getYearCode()
	{
		return yearCode;
	}

	public void setYearCode(String yearCode)
	{
		this.yearCode = yearCode;
	}

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	public String getModelCode()
	{
		return modelCode;
	}

	public void setModelCode(String modelCode)
	{
		this.modelCode = modelCode;
	}

	public int getMinValue()
	{
		return minValue;
	}

	public void setMinValue(int minValue)
	{
		this.minValue = minValue;
	}

	public int getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public String getSequenceName()
	{
		return sequenceName;
	}

	public void setSequenceName(String sequenceName)
	{
		this.sequenceName = sequenceName;
	}

}
