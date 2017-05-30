package com.mes.webclient.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mes.compatibility.client.Keyed;
import com.mes.compatibility.client.ParentUTRow;
import com.mes.compatibility.client.UTRow;

 /**
  * 对象转换类
  * @author Nemo, 2014-1-20 下午8:56:22
  */
public class UIConverter
{
	Collection<? extends Keyed> objects;
	Keyed object;
	Class<? extends UIObject> classType;
	public UIConverter(Collection<? extends Keyed> objects,Class<? extends UIObject> classType)
	{
		this.objects = objects;
		this.classType = classType;
	}
	
	public UIConverter(Keyed object,Class<? extends UIObject> classType)
	{
		this.object = object;
	}
	
	public List<? extends UIObject> toUIObjects() throws Exception
	{
		try
		{
			List uiObjects = new ArrayList<UIObject>();
			if (objects != null)
			{
				for (Object keyed : objects)
				{
					if(keyed instanceof ParentUTRow)
					{
						Constructor constructor = classType.getConstructor(UTRow.class);
						UIObject uiObject = classType.cast(constructor.newInstance(keyed));
						uiObjects.add(uiObject);
					}
					else
					{
						Constructor constructor = classType.getConstructor(keyed.getClass());
						UIObject uiObject = classType.cast(constructor.newInstance(keyed));
						uiObjects.add(uiObject);
					}
				}
			}
			return uiObjects;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception("类型转换出错,目标类型为"+classType.getName());
		}
	}
	
	public UIObject convertToUIObject(Keyed object) throws Exception
	{
		try
		{
			if (object != null)
			{
				Constructor constructor = classType.getConstructor(object.getClass());
				return classType.cast(constructor.newInstance(object));
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("类型转换出错，原类型为"+object.getClassType()+",目标类型为"+classType.getName());
		}
	}
}
