/*
 * @(#) UIEventSheet.java 2016-8-11 下午2:37:22
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.bean;

import java.util.ArrayList;
import java.util.List;

import com.mes.compatibility.client.EventSheetHolder;
import com.mes.compatibility.client.Keyed;
import com.mes.eventmanager.sheet.EventComponent;
import com.mes.eventmanager.sheet.EventContainer;
import com.mes.eventmanager.sheet.EventDefinitionContainer;
import com.mes.eventmanager.sheet.EventSheet;
import com.mes.eventmanager.sheet.JMSMessageEventContainer;
import com.mes.eventmanager.sheet.JMSMessageEventDefinition;
import com.mes.eventmanager.sheet.LiveDataEventContainer;
import com.mes.eventmanager.sheet.LiveDataEventDefinition;
import com.mes.eventmanager.sheet.SerialEventContainer;
import com.mes.eventmanager.sheet.SerialEventDefinition;
import com.mes.eventmanager.sheet.SocketEventContainer;
import com.mes.eventmanager.sheet.SocketEventDefinition;
import com.mes.eventmanager.sheet.TimerEventContainer;
import com.mes.eventmanager.sheet.TimerEventDefinition;
import com.mes.eventmanager.sheet.WebServiceEventContainer;
import com.mes.eventmanager.sheet.WebServiceEventDefinition;
import com.mes.webclient.constants.IEventSheetControlType;
import com.mes.webclient.controller.vo.EventSheetVO;
import com.mes.webclient.util.StringUtil;
import com.mes.webclient.util.UIObject;

public class UIEventSheet extends UIObject
{

	EventSheetHolder eventSheetHolder;
	EventSheet eventSheet;
	
	public UIEventSheet(EventSheetHolder eventSheetHolder)
	{
		this.eventSheetHolder = eventSheetHolder;
		this.eventSheet = EventSheet.fromByteArray( eventSheetHolder.getEventSheet());
	}
	
	@Override
	protected Keyed getNativeObject()
	{
		return eventSheetHolder;
	}

	public String getName()
	{
		return eventSheetHolder.getName();
	}
	
	public String getDescription()
	{
		return eventSheetHolder.getDescription();
	}

	public void setName(String eventSheetNumber)
	{
		eventSheetHolder.setName(eventSheetNumber);
	}
	
	public void setDescription(String eventSheetName)
	{
		eventSheetHolder.setDescription(eventSheetName);
	}
	
	public List<UIEventControl> getUIEventControls()
	{
		List<UIEventControl> uiEventControls = new ArrayList<UIEventControl>();
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.JMS));
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.SERIAL_PORT));
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.SOCKET));
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.TIMER));
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.WEB_SERVICE));
		uiEventControls.addAll(getUIEventControls( IEventSheetControlType.PLC));
		return uiEventControls;
	}
	
	private EventContainer getEventContainer(String controlType)
	{
		EventContainer container = null;
		if (IEventSheetControlType.JMS.equals(controlType))
		{
			container = eventSheet.getContainerForClass(JMSMessageEventContainer.class);
		}
		else if (IEventSheetControlType.SERIAL_PORT.equals(controlType))
		{
			container = eventSheet.getContainerForClass(SerialEventContainer.class);
		}
		else if (IEventSheetControlType.SOCKET.equals(controlType))
		{
			container = eventSheet.getContainerForClass(SocketEventContainer.class);
		}
		else if (IEventSheetControlType.TIMER.equals(controlType))
		{
			container = eventSheet.getContainerForClass(TimerEventContainer.class);
		}
		else if (IEventSheetControlType.WEB_SERVICE.equals(controlType))
		{
			container = eventSheet.getContainerForClass(WebServiceEventContainer.class);
		}
		else if (IEventSheetControlType.PLC.equals(controlType))
		{
			container = eventSheet.getContainerForClass(LiveDataEventContainer.class);
		}
		return container;
	}
	
	private List<UIEventControl> getUIEventControls(String controlType)
	{
		List<UIEventControl> uiEventControls = new ArrayList<UIEventControl>();

		EventContainer container = getEventContainer(controlType);
		if (container != null)
		{
			EventComponent[] containers = container.getChildren();
			for (int i = 0; i < containers.length; i++)
			{
				EventComponent cc = containers[i];
				UIEventControl uiEventControl = new UIEventControl();
				uiEventControl.setClassName(cc.getName());
				uiEventControl.setControlType(controlType);
				
				if (IEventSheetControlType.JMS.equals(controlType))
				{
					JMSMessageEventContainer jms = (JMSMessageEventContainer)cc;
					uiEventControl.setContextFactory(jms.getInitialContextFactory());
					uiEventControl.setConnectionFactoryJNDI(jms.getJmsConnectionFactoryJndi());
					uiEventControl.setDesctinationJNDI(jms.getJmsDestinationJndi());
					uiEventControl.setProviderUrl(jms.getProviderUrl());
				}
				else if (IEventSheetControlType.SERIAL_PORT.equals(controlType))
				{
					SerialEventContainer sec = (SerialEventContainer)cc;
					uiEventControl.setSerialPort(sec.getPort());
					uiEventControl.setStopBatis(sec.getStopBits());
					uiEventControl.setBaudRate(sec.getBaudRate());
				}
				else if (IEventSheetControlType.SOCKET.equals(controlType))
				{
					SocketEventContainer sec = (SocketEventContainer)cc;
					uiEventControl.setPortNumber(String.valueOf(sec.getPortNumber()));
					uiEventControl.setHostName(sec.getHostName());
					uiEventControl.setServer(sec.getIsServer());
					uiEventControl.setSocketStyle(sec.getSocketStyle()==1?"TCP/IP":"Multicast");
				}
				else if (IEventSheetControlType.TIMER.equals(controlType))
				{
					TimerEventContainer sec = (TimerEventContainer)cc;
					uiEventControl.setInteval(sec.getInterval());
				}
				else if (IEventSheetControlType.PLC.equals(controlType))
				{
					LiveDataEventContainer sec = (LiveDataEventContainer)cc;
					LiveDataEventDefinition  dataChange = (LiveDataEventDefinition) sec.getChild(LiveDataEventDefinition.DATA_CHANGE);
					LiveDataEventDefinition  readComplete = (LiveDataEventDefinition) sec.getChild(LiveDataEventDefinition.READ_COMPLETE);
					uiEventControl.setServerName(dataChange.getScript());
					String opcDatas = readComplete.getScript();
					if(StringUtil.isNotNull(opcDatas))
					{
						uiEventControl.setOpcName(opcDatas.split("@#@")[0]);
						uiEventControl.setUserName(opcDatas.split("@#@")[1]);
						uiEventControl.setPassword(opcDatas.split("@#@")[2]);
					}
				}
				
				uiEventControls.add(uiEventControl);
			}
		}
		return uiEventControls;
	}
	
	public UIEventControl getUIEventControl(String className,String controlType)
	{
		
		List<UIEventControl> uiEventControls = getUIEventControls(controlType);
		for (int i = 0; i < uiEventControls.size(); i++)
		{
			UIEventControl uiEventControl = uiEventControls.get(i);
			if (className.equals(uiEventControl.getClassName()))
			{
				return uiEventControl;
			}
		}
		
		return null;
	}
	
	public UIEventControl getUIEventControl(String className)
	{
		
		List<UIEventControl> uiEventControls = getUIEventControls();
		for (int i = 0; i < uiEventControls.size(); i++)
		{
			UIEventControl uiEventControl = uiEventControls.get(i);
			if (className.equals(uiEventControl.getClassName()))
			{
				return uiEventControl;
			}
		}
		
		return null;
	}

	public void saveUIEventControl(EventSheetVO eventSheetVO) throws Exception
	{
		String controlType = eventSheetVO.getEventType();
		EventContainer ec = getEventContainer(controlType);
		
		String className = eventSheetVO.getClassName();
		
		EventDefinitionContainer sec = null ;
		String[] events  = null;
		if(IEventSheetControlType.JMS.equals(controlType))
		{
			JMSMessageEventContainer ecc = new JMSMessageEventContainer();
			events = JMSMessageEventDefinition.jmsMessageEvents;
			ecc.setInitialContextFactory(eventSheetVO.getContextFactory());
			ecc.setJmsConnectionFactoryJndi(eventSheetVO.getConnectionFactoryJNDI());
			ecc.setJmsDestinationJndi(eventSheetVO.getDesctinationJNDI());
			ecc.setProviderUrl(eventSheetVO.getProviderUrl());
			
			sec = ecc;
			
			for (int i = 0; i < events.length; i++)
			{
				sec.addChild(new JMSMessageEventDefinition(events[i]));
			}
			
		}
		else if(IEventSheetControlType.SERIAL_PORT.equals(controlType))
		{
			SerialEventContainer ecc = new SerialEventContainer();
			events = SerialEventDefinition.serialEvents;
			ecc.setPort(eventSheetVO.getSerialPort());
			ecc.setBaudRate(eventSheetVO.getBaudRate());
			ecc.setStopBits(eventSheetVO.getStopBatis());
			
			sec = ecc;
			
			for (int i = 0; i < events.length; i++)
			{
				sec.addChild(new SerialEventDefinition(events[i]));
			}
		}
		else if(IEventSheetControlType.SOCKET.equals(controlType))
		{
			SocketEventContainer ecc = new SocketEventContainer();
			events = SocketEventDefinition.socketEvents;
			ecc.setHostName(eventSheetVO.getHostName());
			ecc.setIsServer(eventSheetVO.getIsServer());
			ecc.setPortNumber(Integer.valueOf(eventSheetVO.getPortNumber()));
			ecc.setSocketStyle(eventSheetVO.getSocketStyle().equals("TCP/IP")?1:4);
			
			sec = ecc;
			
			for (int i = 0; i < events.length; i++)
			{
				sec.addChild(new SocketEventDefinition(events[i]));
			}
		}
		else if(IEventSheetControlType.TIMER.equals(controlType))
		{
			TimerEventContainer ecc = new TimerEventContainer();
			events = TimerEventDefinition.timerEvents;
			
			ecc.setInterval(eventSheetVO.getInteval());
			
			sec = ecc;
			
			for (int i = 0; i < events.length; i++)
			{
				sec.addChild(new TimerEventDefinition(events[i]));
			}
		}
		else if(IEventSheetControlType.WEB_SERVICE.equals(controlType))
		{
			WebServiceEventContainer ecc = new WebServiceEventContainer();
			events = WebServiceEventDefinition.webServiceEvents;
			sec = ecc;
			
			for (int i = 0; i < events.length; i++)
			{
				sec.addChild(new WebServiceEventDefinition(events[i]));
			}
		}
		else if(IEventSheetControlType.PLC.equals(controlType))
		{
			LiveDataEventContainer ecc = new LiveDataEventContainer();
			events = LiveDataEventDefinition.liveDataEvents;
			sec = ecc;
			for (int i = 0; i < events.length; i++)
			{
				LiveDataEventDefinition liveDataEventDefinition = new LiveDataEventDefinition(events[i]);
				if(events[i].equals(LiveDataEventDefinition.DATA_CHANGE))
				{
					liveDataEventDefinition.setScript(eventSheetVO.getServerName());
				}
				else if(events[i].equals(LiveDataEventDefinition.READ_COMPLETE))
				{
					liveDataEventDefinition.setScript(eventSheetVO.getOpcName()+"@#@"+eventSheetVO.getUserName()+"@#@"+eventSheetVO.getPassword());
				}
				sec.addChild(liveDataEventDefinition);
			}
		}
		
		sec.setName(className);
		ec.addChild(sec);
		
		save();
	}
	
	@Override
	public void save()
		throws Exception
	{
		eventSheetHolder.setEventSheet(eventSheet.toByteArray());
//		getFunctions().getServerImpl().getEventSheetManager().save(eventSheetHolder, null, null, null);
		super.save();
	}
	
	public void removeControl(String className) throws Exception
	{
		removeControl(className, IEventSheetControlType.JMS);
		removeControl(className, IEventSheetControlType.SERIAL_PORT);
		removeControl(className, IEventSheetControlType.SOCKET);
		removeControl(className, IEventSheetControlType.TIMER);
		removeControl(className, IEventSheetControlType.WEB_SERVICE);
		removeControl(className, IEventSheetControlType.PLC);
	}
	
	public void removeControl(String className,String controlType) throws Exception
	{
		EventContainer container = getEventContainer(controlType);
		if (container != null)
		{
			EventComponent[] containers = container.getChildren();
			for (int i = 0; i < containers.length; i++)
			{
				EventComponent cc = containers[i];
				if (cc.getName().equals(
					className))
				{
					container.removeChild(cc);
					save();
					break;
				}
			}
		}
	}

}
