package com.mes.webclient.controller.vo;

public class ReceiveInfoVO extends BaseVO
{
	private String bill_no;

	private String part_no;
	
	private String part_name;

	private String part_type;
	
	private String batch_date;
	
	private String seq_no;
	
	private String supplier;
	
	private int batch_no;
	
	private String operator;
	
	private String receive_time;
	
	private String color;
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	//add 
	private String pack_size;
	public String getPack_size() {
		return pack_size;
	}

	public void setPack_size(String pack_size) {
		this.pack_size = pack_size;
	}

	public String getPack_code() {
		return pack_code;
	}

	public void setPack_code(String pack_code) {
		this.pack_code = pack_code;
	}

	private String pack_code;

	public String getBill_no()
	{
		return bill_no;
	}

	public void setBill_no(String bill_no)
	{
		this.bill_no = bill_no;
	}

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

	public String getBatch_date()
	{
		return batch_date;
	}

	public void setBatch_date(String batch_date)
	{
		this.batch_date = batch_date;
	}


	public String getSeq_no()
	{
		return seq_no;
	}

	public void setSeq_no(String seq_no)
	{
		this.seq_no = seq_no;
	}

	public String getSupplier()
	{
		return supplier;
	}

	public void setSupplier(String supplier)
	{
		this.supplier = supplier;
	}

	public int getBatch_no()
	{
		return batch_no;
	}

	public void setBatch_no(int batch_no)
	{
		this.batch_no = batch_no;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getReceive_time()
	{
		return receive_time;
	}

	public void setReceive_time(String receive_time)
	{
		this.receive_time = receive_time;
	}

	
}
