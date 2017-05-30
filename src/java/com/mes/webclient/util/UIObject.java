package com.mes.webclient.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.mes.compatibility.client.Categorical;
import com.mes.compatibility.client.Keyed;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.manager.ObjectManager;
import com.mes.compatibility.ui.Time;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.proxy.WebServerProxy;



public abstract class UIObject implements IDateFormat
{
	protected abstract Keyed getNativeObject();
	
	public WebServerProxy getFunctions() throws Exception
	{
		return WebServerProxy.getInstance();
	}
	
	public void save() throws Exception
	{
		if(!(getNativeObject() instanceof Categorical))
		{
			throw new Exception("对象"+getNativeObject().getClassType()+"不支持父类的保存方法，需要重写保存方法");
		}
		Response response = ((Categorical) getNativeObject()).save(null, null, null);
		if(response.isError())
		{
			System.out.println(response.getFirstErrorMessage());
			throw new Exception("保存数据失败，原因为：名称重复或为空");
		}
	}
	
	/**
	 * 删除
	 */
	public void delete()
		throws Exception
	{
		if(!(getNativeObject() instanceof Categorical))
		{
			throw new Exception("对象"+getNativeObject().getClassType()+"不支持父类的删除方法，需要重写删除方法");
		}
		
		try
		{
			ObjectManager objectManager = getNativeObject().getServerImpl().getObjectManager(
				getNativeObject().getClass());
			objectManager.delete(
				getNativeObject(), null, null, null);
		}
		catch (Exception e)
		{
			throw new Exception("保存数据失败，原因为:该对象与其他对象有关联，不允许删除");
		}
	}

	public long getKey()
	{
		return getNativeObject().getKey();
	}
	
	/**
	 * 获得附属对象
	 * @param datas			数据
	 * @param classType	数据类型
	 */
	public List<? extends UIObject> getDependentObjects(Vector<? extends Keyed> datas,Class<? extends UIObject> classType) throws Exception
	{
		UIConverter uiConverter = new UIConverter(datas, classType);
		return uiConverter.toUIObjects();
	}
	
	/**
	 * 返回一个格式化的时间
	 * @param time	时间
	 * @param partten	表达式
	 */
	private String formatDate(Time time,String partten)
	{
		return DateTimeUtils.formatDate(time, partten);
	}
	
	/**
	 * 将时间字符串转换为对象
	 * @param timeStr	时间字符串
	 * @param partten	表达式
	 */
	private Time parseTime(String timeStr,String partten)
	{
		return DateTimeUtils.parseDateOfPnut(timeStr, partten);
	}
	
	/**
	 * 标准日期
	 * 2013-01-01
	 */
	protected String toStandardTime(Time time)
	{
		return formatDate(time, TIME_STANDARD);
	}
	
	/**
	 * 转换为百分比
	 */
	protected String toPercent(Object obj)
	{
		BigDecimal bigDecimal = new BigDecimal(obj.toString()).multiply(new BigDecimal(100)).setScale(2,4);
		return bigDecimal.toString()+"%";
	}
	
	/**
	 * 带时间的日期
	 * 2013-01-01 10:10:10
	 */
	protected String toLongTime(Time time)
	{
		return formatDate(time,TIME_LONG);
	}
	
	/**
	 * 只有时间的日期
	 * 10:10:10
	 */
	protected String toShortTime(Time time)
	{
		return formatDate(time, TIME_SHORT);
	}
	
	protected Date toDate(Time time)
	{
		return time.getCalendar().getTime();
	}
	
	/**
	 * 标准日期
	 * 2013-01-01
	 */
	protected Time parseStandardTime(String timeStr)
	{
		return parseTime(timeStr, TIME_STANDARD);
	}
	
	/**
	 * 带时间的日期
	 * 2013-01-01 10:10:10
	 */
	protected Time parseLongTime(String timeStr)
	{
		return parseTime(timeStr, TIME_LONG);
	}
	
	/**
	 * 格式化数字
	 * @param number	数字
	 * @param length	长度
	 */
	protected String formatNumber(int number,int length)
	{
		return String.format("%0"+length+"d", number);
	}
	
}
