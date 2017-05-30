/*
 * @(#) UserVO.java 2016年7月4日 下午3:02:17
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class LogisticsMaindataVO extends BaseVO
{
	public String part_name;
	public String pack_code;
	public String part_no;
	public String pack_size;
	public String part_type;
	
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public String getPack_code() {
		return pack_code;
	}
	public void setPack_code(String pack_code) {
		this.pack_code = pack_code;
	}
	public String getPart_no() {
		return part_no;
	}
	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}
	public String getPack_size() {
		return pack_size;
	}
	public void setPack_size(String pack_size) {
		this.pack_size = pack_size;
	}
	public String getPart_type() {
		return part_type;
	}
	public void setPart_type(String part_type) {
		this.part_type = part_type;
	}
}
