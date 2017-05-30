package cn.ac.catarc.qj.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ac.catarc.qj.framework.Context;

import com.mes.compatibility.client.ATDefinition;
import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.ManagerSupport;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UserSequence;
import com.mes.compatibility.client.UserSequenceValue;
import com.mes.compatibility.manager.ServerImpl;
import com.mes.compatibility.ui.Time;
import com.mes.webclient.service.IIMService;


public class CommonHelper
{
	@Autowired
	IIMService imService;
	
	protected static Integer[] weightcoefficient = { 8, 7, 6, 5, 4, 3, 2, 10,

	9, 8, 7, 6, 5, 4, 3, 2 };

	protected static HashMap<Integer, Integer> weightCofficientMap = new HashMap<Integer, Integer>();

	protected static Integer getWeightCofficientNumber(Integer key)
		throws Exception
	{
		if (weightCofficientMap.size() == 0)
		{
			loadVinWeightMap();
		}

		return weightCofficientMap.get(key);
	}

	protected static boolean loadVinWeightMap()
		throws Exception
	{
//		weightCofficientMap.clear();
//
//		String atName = Context.getVinWeightATName();
//		ATDefinition atDefinition = (ATDefinition) ServerImpl
//			.getDefaultServer()
//			.getATDefinitionManager()
//			.getObject(
//				atName);
//		ATHandler atHandler = null;
//		if (atDefinition != null && !atDefinition.isDependent())
//		{
//			atHandler = ManagerSupport.createATHandler(atDefinition);
//		}
//		//ATHandler atHandler = imService.getAtHandler(atName);
//		ATRowFilter filter = new ATRowFilter(ServerImpl.getDefaultServer(), atName);
//		filter.forColumnNameEqualTo(
//			"del_flag",
//			null);
//		filter.forColumnNameNotEqualTo(
//			"digit",
//			null);
//		filter.forColumnNameNotEqualTo(
//			"weight_value",
//			null);
//		Vector data = atHandler.getATRowsByFilter(
//			filter,
//			false);
//
//		for (int i = 0; i < data.size(); i++)
//		{
//			ATRow atRow = (ATRow) data.get(i);
//
//			String digit = (String) atRow.getValue("digit");
//			String weight = (String) atRow.getValue("weight_value");
//			Integer key = Integer.valueOf(digit);
//
//			weightCofficientMap.put(
//				key,
//				Integer.valueOf(weight));
//		}
//		if (weightCofficientMap.size() != 17)
//		{
//			throw new NumberFormatException("The vin weight table contains invalid data.");
//		}
//
//		for (int i = 1; i <= data.size(); i++)
//		{
//			Integer weight = weightCofficientMap.get(Integer.valueOf(i));
//			if (weight == null)
//			{
//				weightCofficientMap.clear();
//				throw new NumberFormatException("The vin weight table contains invalid data.");
//			}
//		}
//
//		atHandler.clearCache();
//		data.clear();
//		data = null;
//		return true;
		//以上是源代码，下面是新代码，暂时先硬编码
		weightCofficientMap.clear();
		weightCofficientMap.put(1, 8);
		weightCofficientMap.put(10, 9);
		weightCofficientMap.put(11, 8);
		weightCofficientMap.put(12, 7);
		weightCofficientMap.put(13, 6);
		weightCofficientMap.put(14, 5);
		weightCofficientMap.put(15, 4);
		weightCofficientMap.put(16, 3);
		weightCofficientMap.put(17, 2);
		weightCofficientMap.put(2, 7);
		weightCofficientMap.put(3, 6);
		weightCofficientMap.put(4, 5);
		weightCofficientMap.put(5, 4);
		weightCofficientMap.put(6, 3);
		weightCofficientMap.put(7, 2);
		weightCofficientMap.put(9, 0);
		weightCofficientMap.put(8, 10);
		return true;
	}

	protected static Map<String, Integer> codeMap = new HashMap<String, Integer>();
	static
	{
		codeMap.put(
			"0",
			0);
		codeMap.put(
			"1",
			1);
		codeMap.put(
			"2",
			2);
		codeMap.put(
			"3",
			3);
		codeMap.put(
			"4",
			4);
		codeMap.put(
			"5",
			5);
		codeMap.put(
			"6",
			6);
		codeMap.put(
			"7",
			7);
		codeMap.put(
			"8",
			8);
		codeMap.put(
			"9",
			9);

		codeMap.put(
			"A",
			1);
		codeMap.put(
			"B",
			2);
		codeMap.put(
			"C",
			3);
		codeMap.put(
			"D",
			4);
		codeMap.put(
			"E",
			5);
		codeMap.put(
			"F",
			6);
		codeMap.put(
			"G",
			7);
		codeMap.put(
			"H",
			8);
		codeMap.put(
			"J",
			1);
		codeMap.put(
			"K",
			2);
		codeMap.put(
			"L",
			3);
		codeMap.put(
			"M",
			4);
		codeMap.put(
			"N",
			5);
		codeMap.put(
			"P",
			7);
		codeMap.put(
			"R",
			9);
		codeMap.put(
			"S",
			2);
		codeMap.put(
			"T",
			3);
		codeMap.put(
			"U",
			4);
		codeMap.put(
			"V",
			5);
		codeMap.put(
			"W",
			6);
		codeMap.put(
			"X",
			7);
		codeMap.put(
			"Y",
			8);
		codeMap.put(
			"Z",
			9);
	}

	protected static Map<String, String> yearMap = new HashMap<String, String>();
	static
	{
		yearMap.put(
			"2000",
			"Y");
		yearMap.put(
			"2001",
			"1");
		yearMap.put(
			"2002",
			"2");
		yearMap.put(
			"2003",
			"3");
		yearMap.put(
			"2004",
			"4");
		yearMap.put(
			"2005",
			"5");
		yearMap.put(
			"2006",
			"6");
		yearMap.put(
			"2007",
			"7");
		yearMap.put(
			"2008",
			"8");
		yearMap.put(
			"2009",
			"9");
		yearMap.put(
			"2010",
			"A");
		yearMap.put(
			"2011",
			"B");
		yearMap.put(
			"2012",
			"C");
		yearMap.put(
			"2013",
			"D");
		yearMap.put(
			"2014",
			"E");
		yearMap.put(
			"2015",
			"F");
		yearMap.put(
			"2016",
			"G");
		yearMap.put(
			"2017",
			"H");
		yearMap.put(
			"2018",
			"J");
		yearMap.put(
			"2019",
			"K");
		yearMap.put(
			"2020",
			"L");
		yearMap.put(
			"2021",
			"M");
		yearMap.put(
			"2022",
			"N");
		yearMap.put(
			"2023",
			"P");
		yearMap.put(
			"2024",
			"R");
		yearMap.put(
			"2025",
			"S");
		yearMap.put(
			"2026",
			"T");
		yearMap.put(
			"2027",
			"V");
		yearMap.put(
			"2028",
			"W");
		yearMap.put(
			"2029",
			"X");
		yearMap.put(
			"2030",
			"Y");
		yearMap.put(
			"2031",
			"1");
		yearMap.put(
			"2032",
			"2");
		yearMap.put(
			"2033",
			"3");
		yearMap.put(
			"2034",
			"4");
		yearMap.put(
			"2035",
			"5");
		yearMap.put(
			"2036",
			"6");
		yearMap.put(
			"2037",
			"7");
		yearMap.put(
			"2038",
			"8");
		yearMap.put(
			"2039",
			"9");
		yearMap.put(
			"2040",
			"A");
	}

	protected static Map<String, String> lineMap = new HashMap<String, String>();
	static
	{
		lineMap.put(
			"A",
			"B");
	}

	protected static Map<String, String> acMap = new HashMap<String, String>();
	static
	{
		acMap.put(
			"0",
			"无空调");
		acMap.put(
			"F",
			"前置空调，冷媒R12");
		acMap.put(
			"K",
			"顶置空调（含顶置加前置），冷媒R12");
		acMap.put(
			"G",
			"前置空调，冷媒R134a");
		acMap.put(
			"H",
			"顶置空调（含顶置加前置），冷媒R134a");
	}

	public String getAirConditionDesc(String acCode)
	{
		return acMap.get(acCode);
	}

	protected String getSerialNumber(String sequenceName, int digitCount)
	{
		String[] serials = getSerialNumbers(
			sequenceName,
			digitCount,
			1);

		return serials[0];
	}

	protected String[] getSerialNumbers(String sequenceName, int digitCount, int num)
	{
		final int maxDecInt = 999999999;
		int usedDigitCount;
		int modulo;

		if (digitCount <= 1)
		{
			modulo = 9;
			usedDigitCount = 1;
		}
		else if (digitCount >= 9)
		{
			modulo = maxDecInt;
			usedDigitCount = 9;
		}
		else
		{
			modulo = 1;
			for (int i = 0; i < digitCount; i++)
			{
				modulo *= 10;
			}
			modulo -= 1;
			usedDigitCount = digitCount;
		}

		long[] numbers = getNUniqueNumber(
			sequenceName,
			num,
			modulo);
		String[] serials = new String[num];
		for (int i = 0; i < num; i++)
		{
			long number = (numbers[i] % modulo);// + 1;
			if (number == 0)
			{
				number = 1;
			}
			String serial = String.format(
				"%0" + usedDigitCount + "d",
				number);

			serials[i] = serial;

		}
		return serials;
	}

	private UserSequence getUserSequence(String sequenceName, int modulo)
	{
		UserSequence sequence = null;
		try
		{
			sequence = (UserSequence) ServerImpl
				.getDefaultServer()
				.getUserSequenceManager()
				.getObject(
					sequenceName);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (sequence == null)
		{
			sequence = ServerImpl.getDefaultServer().getUserSequenceManager().createUserSequence(
				sequenceName);
			sequence.setIncrementValue(1);
			sequence.setInitialValue(0);
			sequence.setMaxValue(modulo); //
			sequence.setDescription("SGMW MES Data Sequence");

			Response response = sequence.save();
			if (response.isError())
			{
				return null;
			}
		}
		return sequence;
	}

	protected boolean isVaidSequence(String sequenceName)
	{
		UserSequence sequence = null;
		try
		{
			sequence = (UserSequence) ServerImpl
				.getDefaultServer()
				.getUserSequenceManager()
				.getObject(
					sequenceName);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (sequence == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	protected long getUniqueNumber(String sequenceName, int modulo)
	{
		UserSequence sequence = getUserSequence(
			sequenceName,
			modulo);
		Response response = sequence.getNextValue();
		if (response.isError())
		{
			return 0;
		}
		UserSequenceValue value = (UserSequenceValue) response.getResult();
		if (value != null)
		{
			return value.getValue();
		}
		else
		{
			return 0;
		}
	}

	protected long[] getNUniqueNumber(String sequenceName, int qty, int modulo)
	{
		UserSequence sequence = getUserSequence(
			sequenceName,
			modulo);
		Response response = sequence.getNextNValues(qty);
		if (response.isError())
		{
			return null;
		}

		UserSequenceValue value = (UserSequenceValue) response.getResult();
		Vector<Integer> values = value.getValues();
		if (values != null && values.size() > 0)
		{
			long[] numbers = new long[values.size()];
			for (int i = 0; i < values.size(); i++)
			{
				numbers[i] = Long.valueOf(((Integer) values.get(i)).longValue());
			}
			return numbers;
		}
		return null;
	}

	protected String[] stringToArray(String str)
	{
		if (str == null)
		{
			return null;
		}

		String[] code = new String[str.length()];
		for (int i = 0; str != null && i < str.length(); i++)
		{
			code[i] = String.valueOf(str.charAt(i));
		}
		return code;
	}

	protected int getCurrentYear()
	{
		int year = 2012;
		try
		{
			year = ServerImpl.getDefaultServer().getUtilityManager().getDBTime().getYear();
		}
		catch (Exception e)
		{
			Calendar c = Calendar.getInstance(Locale.getDefault());
			Time t = new Time(c);
			year = t.getYear();
		}
		return year;
	}

	protected int getCurrentMonth()
	{
		int month = 00;
		try
		{
			month = ServerImpl.getDefaultServer().getUtilityManager().getDBTime().getMonth();
		}
		catch (Exception e)
		{
			Calendar c = Calendar.getInstance(Locale.getDefault());
			Time t = new Time(c);
			month = t.getMonth();
		}
		return month;
	}

	protected int getCurrentDay()
	{
		int day = 00;
		try
		{
			day = ServerImpl.getDefaultServer().getUtilityManager().getDBTime().getDay();
		}
		catch (Exception e)
		{
			Calendar c = Calendar.getInstance(Locale.getDefault());
			Time t = new Time(c);
			day = t.getDay();
		}
		return day;
	}

	public String getTransimissionDescriptionByCode(String code)
	{

		return null;
	}

}
