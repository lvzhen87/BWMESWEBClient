package com.mes.webclient.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间处理函数集
 */
public class DateTimeUtils
{
	/**
	 * Calendar c = Calendar.getInstance(); c.set(2012,8, 6, 14, 16,37); Date
	 * date = c.getTime(); formatDate(date,"yyyy-MM-dd HH:mm:ss")
	 * 返回"2012-09-06 14:16:37"
	 * 
	 * @param date
	 *            需要格式化的日期
	 * @param pattern
	 *            year=yyyy month=MM day=dd hour=HH minute=mm second=ss
	 * @return 根据指定格式将输入日期转换成字符串格式
	 */
	public static String formatDate(java.util.Date date, String pattern)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date.getTime());
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * 用法同formatDate(java.util.Date date,String pattern),
	 * 传入的日期为com.mes.compatibility.ui.Time类型,即CMES中的createTime()方法返回值的类型
	 * 
	 * @see formatDate(java.util.Date date,String pattern)
	 */
	public static String formatDate(com.mes.compatibility.ui.Time date, String pattern)
	{
		return formatDate(
			transformDate(date), pattern);
	}

	/**
	 * changeDateFormat("2012-12-01","yyyy-MM-dd","yyyy/MM/dd")返回"2012/12/01",
	 * 注意若发生异常默认返回空字符串""
	 * 
	 * @param dateTime
	 *            输入日期
	 * @param inputFormat
	 *            输入日期的格式
	 * @param outputFormat
	 *            输出日期的格式
	 * @return 按输出日期格式转换后的日期
	 */
	public static String changeDateFormat(String dateTime, String inputFormat, String outputFormat)
	{
		try
		{
			SimpleDateFormat iFormat = new SimpleDateFormat(inputFormat);
			java.util.Date iDate = iFormat.parse(dateTime);
			SimpleDateFormat oFormat = new SimpleDateFormat(outputFormat);
			String sRet = oFormat.format(iDate);
			return sRet;
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * 注意相差24小时以内则算0天
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 两个日期之间的天数
	 */
	public static int getDays(java.util.Date fromDate, java.util.Date toDate)
	{
		long a = getMilliseconds(
			fromDate, toDate) / (24 * 60 * 60 * 1000);
		return (int) a;
	}

	/**
	 * 用法同getDays(java.util.Date fromDate,java.util.Date toDate)
	 * 
	 * @see getDays(java.util.Date fromDate,java.util.Date toDate)
	 */
	public static int getDays(com.mes.compatibility.ui.Time fromDate,
		com.mes.compatibility.ui.Time toDate)
	{
		return getDays(
			transformDate(fromDate), transformDate(toDate));
	}

	/**
	 * 注意相差60分以内则算0小时
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 两个日期时间之间的小时数
	 */
	public static int getHours(java.util.Date fromDate, java.util.Date toDate)
	{
		long a = getMilliseconds(
			fromDate, toDate) / (60 * 60 * 1000);
		return (int) a;
	}

	/**
	 * 用法同getHours(java.util.Date fromDate,java.util.Date toDate)
	 * 
	 * @see getHours(java.util.Date fromDate,java.util.Date toDate)
	 */
	public static int getHours(com.mes.compatibility.ui.Time fromDate,
		com.mes.compatibility.ui.Time toDate)
	{
		return getHours(
			transformDate(fromDate), transformDate(toDate));
	}

	/**
	 * 注意相差60秒以内则算0分
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 两个日期时间之间的分钟数
	 */
	public static int getMinutes(java.util.Date fromDate, java.util.Date toDate)
	{
		long a = getMilliseconds(
			fromDate, toDate) / (60 * 1000);
		return (int) a;
	}

	/**
	 * 用法同getMinutes(java.util.Date fromDate,java.util.Date toDate)
	 * 
	 * @see getMinutes(java.util.Date fromDate,java.util.Date toDate)
	 */
	public static int getMinutes(com.mes.compatibility.ui.Time fromDate,
		com.mes.compatibility.ui.Time toDate)
	{
		return getMinutes(
			transformDate(fromDate), transformDate(toDate));
	}

	/**
	 * 注意相差1000毫秒以内则算0秒
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 两个日期时间之间的秒数
	 */
	public static int getSeconds(java.util.Date fromDate, java.util.Date toDate)
	{
		long m1 = fromDate.getTime();
		long m2 = toDate.getTime();
		long a = (m2 - m1) / 1000;
		return (int) a;
	}

	/**
	 * 注意相差1000毫秒以内则算0秒
	 * 
	 * @see getSeconds(java.util.Date fromDate,java.util.Date toDate)
	 */
	public static int getSeconds(com.mes.compatibility.ui.Time fromDate,
		com.mes.compatibility.ui.Time toDate)
	{
		return getSeconds(
			transformDate(fromDate), transformDate(toDate));
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 两个日期间的毫秒数
	 */
	public static Long getMilliseconds(java.util.Date fromDate, java.util.Date toDate)
	{
		long m1 = fromDate.getTime();
		long m2 = toDate.getTime();
		return m2 - m1;
	}

	/**
	 * 比较两日期大小,即只比较到日,不比较时分秒
	 * 
	 * @param date1
	 * @param date2
	 * @return date1日期大于date2,则返回值1;date1日期小于date2,则返回值小于-1;相等返回0
	 */
	public static int compareDay(java.util.Date date1, java.util.Date date2)
	{
		return compareDate(
			getDayStart(date1), getDayStart(date2));
	}

	/**
	 * 用法同compareDay(java.util.Date date1,java.util.Date date2)
	 * 
	 * @see compareDay(java.util.Date date1,java.util.Date date2)
	 */
	public static int compareDay(com.mes.compatibility.ui.Time date1,
		com.mes.compatibility.ui.Time date2)
	{
		return compareDay(
			transformDate(date1), transformDate(date2));
	}

	/**
	 * @param date1
	 * @param date2
	 * @return 前者大于后者,返回1;前者小于后者,返回-1;两者相等返回0
	 */
	public static int compareDate(java.util.Date date1, java.util.Date date2)
	{
		int result = date1.compareTo(date2);
		if (result > 0)
		{
			result = 1;
		}
		else if (result < 0)
		{
			result = -1;
		}
		else
		{
			result = 0;
		}
		return result;
	}

	/**
	 * 用法同compareDate(java.util.Date date1,java.util.Date date2)
	 * 
	 * @see compareDate(java.util.Date date1,java.util.Date date2)
	 */
	public static int compareDate(com.mes.compatibility.ui.Time date1,
		com.mes.compatibility.ui.Time date2)
	{
		return date1.compareTo(date2);
	}

	/**
	 * parseDate("2012-12-01","yyyy-MM-dd"),若发生异常(传入的日期字符串与指定的时间格式不符),返回null
	 * 
	 * @param text
	 * @param pattern
	 * @return 根据指定格式将字符串转换后的日期
	 */
	public static java.util.Date parseDate(String text, String pattern)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(text);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 用法同java.util.Date parseDate(String text,String pattern)
	 * 
	 * @see java.util.Date parseDate(String text,String pattern)
	 */
	public static com.mes.compatibility.ui.Time parseDateOfPnut(String text, String pattern)
	{
		return transformDate(parseDate(
			text, pattern));
	}

	/**
	 * 将com.mes.compatibility.ui.Time转换成java.util.Date
	 * 
	 * @param date
	 * @return java.util.Date
	 */
	public static java.util.Date transformDate(com.mes.compatibility.ui.Time date)
	{
		if (date != null)
		{
			return date.getCalendar().getTime();
		}
		return null;
	}

	/**
	 * 将java.util.Date转换成com.mes.compatibility.ui.Time
	 * 
	 * @param date
	 * @return com.mes.compatibility.ui.Time 即CMES中createTime()方法返回值的类型
	 */
	public static com.mes.compatibility.ui.Time transformDate(java.util.Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		com.mes.compatibility.ui.Time date2 = new com.mes.compatibility.ui.Time(c);
		return date2;
	}

	/**
	 * 传入日期为2012-12-1 19:18:17, 返回日期为2012-12-1 00:00:00
	 * 
	 * @param date
	 * @return 该日期的开始时间,即该日的0时0分0秒
	 */
	public static java.util.Date getDayStart(java.util.Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(
			Calendar.HOUR_OF_DAY, 0);
		c.set(
			Calendar.MINUTE, 0);
		c.set(
			Calendar.SECOND, 0);
		c.set(
			Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 用法同getDayStart(java.util.Date date)
	 * 
	 * @see getDayStart(java.util.Date date)
	 */
	public static com.mes.compatibility.ui.Time getDayStart(
		com.mes.compatibility.ui.Time date)
	{
		return transformDate(getDayStart(transformDate(date)));
	}
	
	public static int daysBetween(Date date1,Date date2)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
	
	/**
	 * 获取String类型的Date
	 */
	
	public static java.util.Date getStringDate(String date)
	{
		DateFormat sdf = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
        Date time;
		try
		{
			time = sdf.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
        return time;
	}
}
