/*
 * @(#) BaseController.java 2016-6-30 下午8:45:02
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.mes.compatibility.client.Keyed;

@Component("baseController")
public class BaseController
{
	// 前台业务查询bean
	public final static String VIEW_OBJECT = "vo";

	// 查询返回对象
	public final static String QUERY_LIST = "list";

	// 查询返回对象
	public final static String INVOKE_TYPE = "invokeType";

	// 业务类型-新增
	public final static String INVOKE_TYPE_ADD = "新增";

	// 业务类型-编辑
	public final static String INVOKE_TYPE_EDIT = "编辑";
	
	// 业务类型-修改密码
	public final static String INVOKE_TYPE_EDITPASSWORD = "修改密码";

	// 日志记录对象
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * AJAX执行后返回
	 * 
	 * @param statusCode
	 * @param message
	 * @param forwardUrl
	 * @return
	 */
	protected String ajaxDone(int statusCode, String message)
	{
		return message;
	}

	protected String ajaxDoneSuccess()
	{
		return ajaxDone(
			200, "操作成功");
	}

	protected String ajaxDoneError(String message)
	{
		if (message == null)
		{
			message = "空指针异常";
		}
		message = replaceSpecialCharacter(message);
		System.out.print(message);
		return ajaxDone(
			300, "操作失败，原因为：" + message);
	}

	protected String replaceSpecialCharacter(String msg)
	{
		if (msg != null)
		{
			msg = msg.replace(
				"\r\n", "<br>").replace(
				"\n", "<br>").replace(
				"\t", " ");
		}
		return msg;
	}

	/**
	 * 获得缺失的对象 获得originalObjects存在，但是currentObjects不存在的数据
	 */
	protected Vector<? extends Keyed> getMissingObject(Vector<? extends Keyed> currentObjects,
		Vector<? extends Keyed> originalObjects)
	{
		Map<Long, Keyed> currentKeyedMap = new HashMap<Long, Keyed>();
		for (int i = 0; i < currentObjects.size(); i++)
		{
			Keyed obj = currentObjects.get(i);
			currentKeyedMap.put(
				obj.getKey(), obj);
		}

		Map<Long, Keyed> originalKeyedMap = new HashMap<Long, Keyed>();
		for (int i = 0; i < originalObjects.size(); i++)
		{
			Keyed obj = originalObjects.get(i);
			originalKeyedMap.put(
				obj.getKey(), obj);
		}

		Vector<Keyed> toMissingObjs = new Vector<Keyed>();
		for (int i = 0; i < originalObjects.size(); i++)
		{
			Keyed obj = originalObjects.get(i);
			if (!currentKeyedMap.containsKey(obj.getKey()))
			{
				toMissingObjs.add(obj);
			}
		}

		return toMissingObjs;
	}

}
