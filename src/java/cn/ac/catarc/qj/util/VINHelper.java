package cn.ac.catarc.qj.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import cn.ac.catarc.qj.dobject.DVINSequence;

import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UserSequence;
import com.mes.compatibility.manager.ServerImpl;
import com.mes.compatibility.ui.grid.DsGrid;

public class VINHelper extends CommonHelper
{
	private int MAX_VIN_SEQUENCE = 999999;

	private String getSequenceName(boolean dummy, String lineCode)
	{
		if (dummy)
		{
			return "SGMW_DUM_VIN_SEQ_" + lineCode + getCurrentYear();
		}

		return "SGMW_VIN_SEQ_" + lineCode + getCurrentYear();
	}

	private String getSequenceName(boolean dummy, String lineCode, String model, int year)
	{
		if (dummy)
		{
			return "SGMW_DUM_VIN_SEQ_" + lineCode.toUpperCase() + "_" + model + "_" + year;
		}

		return "SGMW_VIN_SEQ_" + lineCode.toUpperCase() + "_" + model + "_" + year;
	}

	public String[] generateCode(String vin8, String lineNumber, String model, int numOfVIN)
		throws Exception
	{
		return generateCode(
			vin8,
			lineNumber,
			numOfVIN,
			null,
			model);
	}

	public String[] generateCode(String vin8, String lineNumber, int numOfVIN)
		throws Exception
	{
		return generateCode(
			vin8,
			lineNumber,
			numOfVIN,
			null,
			"ALL");
	}

	public String[] generateCode(
		String vin8,
		String lineNumber,
		int numOfVIN,
		String planId,
		String model)
		throws Exception
	{
		boolean dummy = false;
		ArrayList vinArray = new ArrayList();
		if ((vin8 == null) || (vin8.length() != 8))
		{
			vin8 = "DUMMYVLN";
			dummy = true;
		}
		String[] vin8Array = stringToArray(vin8);
		String lineCode = lineNumber;
		if (lineCode == null)
		{
			throw new IllegalArgumentException("生产线号" + lineCode + "为空，不能生成VIN码。请联系IT系统管理员。");
		}

		if (model == null)
		{
			model = "GP50";
		}

		int year =  getCurrentYear();

		if (model.equals("EXPORT"))
		{
			int newYear = getSpecialYearCode(planId);
			if (newYear < year)
			{
				throw new IllegalArgumentException("订单" + planId + "的批次号未维护对应年份");
			}
			year = newYear;
		}

		String yearCode = (String) yearMap.get(String.valueOf(year));
		if (yearCode == null)
		{
			throw new IllegalArgumentException(year + "对应年份码未维护，不能生成VIN码。请联系IT系统管理员。");
		}

//		String sequenceName = getSequenceName(
//			dummy,
//			lineCode,
//			model,
//			year);

//		if (!(isVaidSequence(sequenceName)))
//		{
//			throw new IllegalArgumentException(model + "VIN号段未维护，无法生成VIN号。请联系IT系统管理员。");
//		}
		String sequenceName = vin8+"_"+year;
		String[] seqs = getSerialNumbers(
			sequenceName,
			6,
			numOfVIN);
		for (int i = 0; i < numOfVIN; ++i)
		{
			String seq = yearCode + lineCode + seqs[i];
			String[] seqArray = stringToArray(seq);
			String bit9 = generateVINBit9(
				vin8Array,
				seqArray);

			String vin = vin8 + bit9 + seq;
			if (vin.length() != 17)
			{
				System.out.println("VIN is " + vin);
				throw new IllegalArgumentException("VIN码的长度不是17位");
			}
			vinArray.add(vin);
		}

		return ((String[]) vinArray.toArray(new String[vinArray.size()]));
	}

	private String generateVINBit9(String[] vin8, String[] seq)
		throws Exception
	{
		int total = 0;

		if ((vin8 == null) || (seq == null) || (vin8.length != 8) || (seq.length != 8))
		{
			throw new IllegalArgumentException("VIN码参数不正确");
		}
		int subtotal;
		for (int i = 0; i < 8; ++i)
		{
			if (codeMap.get(vin8[i]) == null)
			{
				throw new IllegalArgumentException("VIN码前8位包含字符[" + vin8[i] + "]不是合法的字符.");
			}
			subtotal = ((Integer) codeMap.get(vin8[i])).intValue() * getWeightCofficientNumber(
				Integer.valueOf(i + 1)).intValue();
			total = subtotal + total;
		}

		for (int i = 0; i < 8; ++i)
		{
			if (codeMap.get(seq[i]) == null)
			{
				throw new NumberFormatException("VIN码后8位包含字符[ " + seq[i] + "]不是合法的字符.");
			}
			subtotal = ((Integer) codeMap.get(seq[i])).intValue() * getWeightCofficientNumber(
				Integer.valueOf(i + 10)).intValue();
			total = subtotal + total;
		}
		int remainder = total % 11;
		if (remainder == 10)
		{
			return "X";
		}

		return String.valueOf(remainder);
	}

	private static String getFullYearByCode(String code)
	{
		Iterator iterator = yearMap.keySet().iterator();
		while (iterator.hasNext())
		{
			String yearCode = (String) iterator.next();
			if (yearCode.equals(code))
			{
				return ((String) yearMap.get(yearCode));
			}
		}

		return null;
	}

	public DVINSequence[] getVINSequence(String seqName)
	{
		String sql = "SELECT user_seq_name, Substring(user_seq_name, len('SGMW_VIN_SEQ_')+1, 1) as 'lineCode' , ";

		sql = sql
			+ "reverse(left(stuff(reverse(user_seq_name),1,charindex('_',user_seq_name),''),charindex('_',stuff(reverse(user_seq_name),1,charindex('_',user_seq_name),''))-1)) as 'modelCode', ";
		sql = sql
			+ "Right(user_seq_name, 4) as 'yearCode', initial_value, max_value, sequence_number from ";
		sql = sql + "USER_SEQUENCE WHERE 1= 1 and ";
		if ((seqName == null) || (seqName.equals("")))
		{
			sql = sql + "user_seq_name like 'SGMW_VIN_SEQ_%'";
		}
		else
		{
			sql = sql + "user_seq_name = '" + seqName + "%'";
		}

		try
		{
			Vector vectorData = ServerImpl
				.getDefaultServer()
				.getUtilityManager()
				.getArrayDataFromActive(
					sql,
					0);

			if ((vectorData != null) && (vectorData.size() > 0))
			{
				ArrayList vinSequences = new ArrayList();

				int i = 0;
				while (true)
				{
					String[] arrayData = (String[]) vectorData.get(i);

					DVINSequence sequence = new DVINSequence();
					sequence.setSequenceName(arrayData[0]);
					sequence.setModelCode(arrayData[2]);
					sequence.setLineCode(arrayData[1]);
					sequence.setYearCode(arrayData[3]);

					sequence.setMinValue(Integer.valueOf(
						arrayData[4]).intValue());
					sequence.setMaxValue(Integer.valueOf(
						arrayData[5]).intValue());
					sequence.setValue(Integer.valueOf(
						arrayData[6]).intValue());

					vinSequences.add(sequence);

					++i;
					if (i >= vectorData.size())
					{
						label281: return ((DVINSequence[]) vinSequences
							.toArray(new DVINSequence[vinSequences.size()]));
					}
				}
			}
			return null;
		}
		catch (Exception e)
		{
		}
		return null;
	}

	public boolean updateVINSequnce(
		String yearCode,
		String lineCode,
		String modelCode,
		String minValue,
		String maxValue,
		String value)
		throws Exception
	{
		if ((yearCode != null)
			&& (yearCode.length() == 4)
			&& (lineCode != null)
			&& (lineCode.length() == 1)
			&& (modelCode != null)
			&& (minValue != null)
			&& (maxValue != null)
			&& (value != null))
		{
			DVINSequence vinSequence = new DVINSequence();

			vinSequence.setYearCode(yearCode);
			vinSequence.setLineCode(lineCode);
			vinSequence.setModelCode(modelCode);
			vinSequence.setMinValue(Integer.valueOf(
				minValue).intValue());
			vinSequence.setMaxValue(Integer.valueOf(
				maxValue).intValue());
			vinSequence.setValue(Integer.valueOf(
				value).intValue());

			vinSequence.setSequenceName("VIN_SEQ_"
				+ lineCode.toUpperCase()
				+ "_"
				+ modelCode
				+ "_"
				+ yearCode);

			return updateVINSequnce(vinSequence);
		}
		return false;
	}

	public boolean updateVINSequnce(DVINSequence vinSequence)
		throws Exception
	{
		if (vinSequence != null)
		{
			if ((vinSequence.getMaxValue() > vinSequence.getValue())
				&& (vinSequence.getMaxValue() > vinSequence.getMinValue())
				&& (vinSequence.getValue() >= vinSequence.getMinValue())
				&& (vinSequence.getLineCode() != null)
				&& (vinSequence.getLineCode().length() == 1)
				&& (vinSequence.getModelCode() != null)
				&& (vinSequence.getYearCode() != null)
				&& (vinSequence.getModelCode().length() >= 4))
			{
				if (vinSequence.getMaxValue() > this.MAX_VIN_SEQUENCE)
				{
					throw new Exception("VIN号段的截止数值超出最大数值" + this.MAX_VIN_SEQUENCE + "，请检查");
				}

				String yearCodeId = (String) yearMap.get(String.valueOf(vinSequence.getYearCode()));
				if (yearCodeId == null)
				{
					throw new Exception("VIN号段的年份代码" + vinSequence.getYearCode() + "不是有效代码，请检查");
				}

				DVINSequence[] userSequences = getVINSequence(null);
				for (int i = 0; i < userSequences.length; ++i)
				{
					DVINSequence sequence = userSequences[i];
					if (!(sequence.getSequenceName().equals(vinSequence.getSequenceName())))
					{
						if ((vinSequence.getYearCode() != sequence.getYearCode())
							|| ((((vinSequence.getMinValue() < sequence.getMinValue()) || (vinSequence
								.getMinValue() > sequence.getMaxValue())))
								&& (((vinSequence.getMinValue() > sequence.getMinValue()) || (vinSequence
									.getMaxValue() < sequence.getMinValue())))
								&& (((vinSequence.getMinValue() < sequence.getMinValue()) || (vinSequence
									.getMaxValue() < sequence.getMaxValue()))) && (((vinSequence
								.getMinValue() > sequence.getMinValue()) || (vinSequence
								.getMaxValue() < sequence.getMaxValue())))))
						{
							continue;
						}
						throw new Exception("VIN号段与其他号段值冲突，请检查");
					}

					if ((((vinSequence.getMinValue() == sequence.getMinValue()) || (sequence
						.getValue() == sequence.getMinValue())))
						&& (vinSequence.getMaxValue() > sequence.getValue()))
						continue;
					throw new Exception("当前VIN号段的实际使用值已超出设置的区间，请检查");
				}

				String sequenceName = vinSequence.getSequenceName();

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
					throw new Exception("保存VIN号段出错，请检查" + e.getMessage());
				}

				if (sequence != null)
				{
					sequence.setIncrementValue(1);
					sequence.setInitialValue(vinSequence.getMinValue());
					sequence.setMaxValue(vinSequence.getMaxValue());
					sequence.setDescription("SGMW VIN Sequence");

					Response response = sequence.save();
					if (response.isError())
					{
						throw new Exception("保存VIN号段出错，请检查" + response.getFirstErrorMessage());
					}

					return true;
				}

				sequence = ServerImpl
					.getDefaultServer()
					.getUserSequenceManager()
					.createUserSequence(
						sequenceName);
				sequence.setIncrementValue(1);
				sequence.setInitialValue(vinSequence.getMinValue());
				sequence.setMaxValue(vinSequence.getMaxValue());
				sequence.setDescription("SGMW VIN Sequence");

				Response response = sequence.save();
				if (response.isError())
				{
					throw new Exception("创建VIN号段出错，请检查" + response.getFirstErrorMessage());
				}

				return true;
			}

			throw new Exception("VIN号段定义中的数据不正确，请检查");
		}

		return false;
	}

	public void initVinSequnceGrid(DsGrid grid)
	{
		if (grid == null)
			return;
		grid.beginUpdate();
		grid.clearGrid();
		grid.setNumberOfRows(0);

		DVINSequence[] vinSequences = getVINSequence(null);
		for (int i = 0; i < vinSequences.length; ++i)
		{
			DVINSequence vinSequence = vinSequences[i];
			grid.setCellText(
				i,
				0,
				i + 1);
			grid.setCellText(
				i,
				1,
				vinSequence.getYearCode());
			grid.setCellText(
				i,
				2,
				vinSequence.getLineCode());
			grid.setCellText(
				i,
				3,
				vinSequence.getModelCode());
			grid.setCellText(
				i,
				4,
				vinSequence.getMinValue());
			grid.setCellText(
				i,
				5,
				vinSequence.getMaxValue());
			grid.setCellText(
				i,
				6,
				vinSequence.getValue());

			grid.setRowObject(
				i,
				vinSequence);
		}

		grid.endUpdate();
	}

	public static void main(String[] args)
	{
		VINHelper helper = new VINHelper();
		String vin8 = "LZWNBCH1";
		String code = "Y1013687";
		String[] vin8s = helper.stringToArray(vin8);
		String[] codes = helper.stringToArray(code);
		try
		{
			String x = helper.generateVINBit9(
				vin8s,
				codes);
			System.out.println(x);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private int getSpecialYearCode(String planNo)
	{
		String sql = "select year_code_S From AT_SGMW_PP_YearCode where batch_no_S = ( ";
		sql = sql
			+ "select batch_no_S from AT_SGMW_Interface_ERP_Order where plan_id_S = '"
			+ planNo
			+ "' and status_I = -1 )";
		sql = sql + "order by year_code_S DESC";
		try
		{
			Vector vectorData = ServerImpl
				.getDefaultServer()
				.getUtilityManager()
				.getArrayDataFromActive(
					sql,
					0);
			if ((vectorData != null) && (vectorData.size() > 0))
			{
				String[] yearCodes = (String[]) vectorData.get(0);
				String yearCode = yearCodes[0];
				int yearNum = Integer.valueOf(
					yearCode).intValue();
				return yearNum;
			}
		}
		catch (Exception e)
		{
			return 0;
		}
		return 0;
	}
}
