package com.mes.webclient.constants;

import com.mes.webclient.util.StringUtil;

public class Message implements FunctionConstants{
	/**
	 * 消息类型
	 */
	private String type;

	/**
	 * 消息内容
	 */
	private String text;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 构造函数
	 */
	public Message(String type, String text){
		this.type = type;
		this.text = text;
	}
	
	/**
	 * 构造函数
	 */
	public Message(String text){
		this.type = FunctionConstants.MESSAGE_TYPE_ERROR;
		this.text = text;
	}
	
	/**
	 * 构造函数
	 */
	public Message(){
		this.type = "";
		this.text = "";
	}
	
	public String toString(){
		if(StringUtil.isNull(this.type)){
			return "";
		}
		return this.type + ":" + this.text;
	}
	
	public boolean isError(){
		return FunctionConstants.MESSAGE_TYPE_ERROR.equals(this.type);
	}
	
	public boolean isOk(){
		return !isError();
	}
}