package cn.ac.catarc.qj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UDADefinition;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.WorkCenter;
import com.mes.transactiongrouping.UserTransaction;
import com.mes.webclient.controller.BaseController;
import com.mes.webclient.controller.vo.OPCMonitorVO;
import com.mes.webclient.service.IIMService;


@Controller("OPCMonitorController")
@RequestMapping("/opcmonitor.sp")
public class OPCMonitorController extends BaseController {
	
	@Autowired
	IIMService imService;

	private String utName = "ECP_OPCItems";
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "qj/opcmonitor/list";
	}
	
	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String query(OPCMonitorVO opcMonitorVO)
	{
		JSONArray rt = null;
		try
		{
			
			UTRowFilter utRowFilter =  imService.createAtRowFilter(utName);
			if(opcMonitorVO  != null)
			{
				if(opcMonitorVO.getoPCName()!= null && !opcMonitorVO.getoPCName().equals(""))
				{
					utRowFilter.forColumnNameEqualTo("OPCName", opcMonitorVO.getoPCName() );
				}
				if(opcMonitorVO.getoPCItem()!= null && !opcMonitorVO.getoPCItem().equals(""))
				{
					utRowFilter.forColumnNameEqualTo("OPCItem", opcMonitorVO.getoPCItem() );
				}
				if(opcMonitorVO.getGroupID()!= null && !opcMonitorVO.getGroupID().equals(""))
				{
					utRowFilter.forColumnNameEqualTo("GroupID", opcMonitorVO.getGroupID() );
				}
				if(opcMonitorVO.getDeviceName()!= null && !opcMonitorVO.getDeviceName().equals(""))
				{
					utRowFilter.forColumnNameEqualTo("DeviceName", opcMonitorVO.getDeviceName() );
				}
				if(opcMonitorVO.getItemGroup()!= null && !opcMonitorVO.getItemGroup().equals(""))
				{
					utRowFilter.forColumnNameEqualTo("ItemGroup", opcMonitorVO.getItemGroup() );
				}
			}
			Vector<UTRow> vector = new Vector<UTRow>();
			vector = utRowFilter.exec();
			UTRow utRow ;
			
			Vector<Object> vos = new Vector<Object>();
			for(int i = 0;i<vector.size();i++)
			{
				OPCMonitorVO vo = new OPCMonitorVO(); 
				utRow = vector.get(i);
				vo.setoPCName(utRow.getValue("OPCName").toString());
				vo.setoPCItem(utRow.getValue("OPCItem").toString());
				vo.setGroupID(utRow.getValue("GroupID").toString());
				vo.setDeviceName(utRow.getValue("DeviceName").toString());
				vo.setMonitor(Boolean.valueOf(utRow.getValue("IsMonitor").toString()));
				vo.setoPCFunction(utRow.getValue("OPCFunction").toString());
				vo.setItemGroup(utRow.getValue("ItemGroup").toString());
				vo.setItemSeq(Integer.valueOf(utRow.getValue("ItemSeq").toString()));
				vo.setKey(utRow.getKey());
				vos.add(vo);
			}

			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(OPCMonitorVO opcMonitorVO, Model model)
	{
		
		List<OPCMonitorVO> items  = new ArrayList<OPCMonitorVO>();
		OPCMonitorVO vo = new OPCMonitorVO();
		UTRow utrow;
		UDADefinition udaDefinition = null;
		long atr_key = opcMonitorVO.getKey();
		Vector<UTRow> vector = new Vector<UTRow>();
		model.addAttribute(
			INVOKE_TYPE, atr_key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{
			
			UTRowFilter utRowFilter = imService.createAtRowFilter(utName);
			if(atr_key <= 0 )
			{
				
			
			}
			else
			{
				utRowFilter.forUTRowKeyEqualTo(opcMonitorVO.getKey());
				vector = utRowFilter.exec();
				
				for(int i  =0 ;i<vector.size();i++)
				{
					utrow = vector.elementAt(i);
					vo.setoPCName(utrow.getValue("OPCName").toString());
					vo.setoPCItem(utrow.getValue("OPCItem").toString());
					vo.setGroupID(utrow.getValue("GroupID").toString());
					vo.setDeviceName(utrow.getValue("DeviceName").toString());
					vo.setMonitor(Boolean.valueOf(utrow.getValue("IsMonitor").toString()));
					vo.setoPCFunction(utrow.getValue("OPCFunction").toString());
					vo.setItemGroup(utrow.getValue("ItemGroup").toString());
					vo.setItemSeq(Integer.valueOf(utrow.getValue("ItemSeq").toString()));
					vo.setKey(utrow.getKey());
					
					items.add(vo);
				}
			}
			
		}
		catch (MESException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute(
			VIEW_OBJECT, vo);
		model.addAttribute(
			"namedItems", items);
		return "qj/opcmonitor/addOrEdit";
	}
	
	
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(OPCMonitorVO opcMonitorVO)
	{
		try
		{
			long key = opcMonitorVO.getKey();
			WorkCenter workCenter = null;
			UTHandler athandler = imService.getAtHandler(utName);
			UTRow utRow = null ;
			Vector<UTRow> vector = new Vector<UTRow>();
			Response response = null;
//			UserTransaction userTransaction = imService.getUserTransaction();
//			userTransaction.begin();
			for(int i = 0; i<1;i++)
			{
	
				if (key <= 0)
				{
	
					 utRow = athandler.createATRow();
					 utRow.setValue("OPCName", opcMonitorVO.getoPCName());
					 utRow.setValue("OPCItem", opcMonitorVO.getoPCItem());
					 utRow.setValue("GroupID", opcMonitorVO.getGroupID());
					 utRow.setValue("DeviceName", opcMonitorVO.getDeviceName());
					 utRow.setValue("IsMonitor",opcMonitorVO.isMonitor());
					 utRow.setValue("OPCFunction",opcMonitorVO.getoPCFunction());
					 utRow.setValue("ItemGroup", opcMonitorVO.getItemGroup());
					 utRow.setValue("ItemSeq", opcMonitorVO.getItemSeq());
					 response = athandler.save();
				}
				else
				{
					UTRowFilter utRowFilter = imService.createAtRowFilter(utName);
					utRowFilter.forUTRowKeyEqualTo(key);
					vector = utRowFilter.exec();
					if(vector != null && vector.size()>0)
					{
						utRow = vector.elementAt(0);
						 utRow.setValue("OPCName", opcMonitorVO.getoPCName());
						 utRow.setValue("OPCItem", opcMonitorVO.getoPCItem());
						 utRow.setValue("GroupID", opcMonitorVO.getGroupID());
						 utRow.setValue("DeviceName", opcMonitorVO.getDeviceName());
						 utRow.setValue("IsMonitor",opcMonitorVO.isMonitor());
						 utRow.setValue("OPCFunction",opcMonitorVO.getoPCFunction());
						 utRow.setValue("ItemGroup", opcMonitorVO.getItemGroup());

						 utRow.setValue("ItemSeq", opcMonitorVO.getItemSeq());
						utRow.save(null,null, null);
					}
				}
				
			}
			
			if (response != null &&response.isError())
			{
//				userTransaction.rollback();
				throw new Exception(response.getFirstErrorMessage());
			}
			else
			{
//				userTransaction.commit();
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}

	}
	
	/**
	 * 删除
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		try
		{
			UTHandler athandler = imService.getAtHandler(utName);
			UTRow utRow = null ;
			Vector<UTRow> vector = new Vector<UTRow>();
			
			if (key > 0)
			{
//				UTRowFilter utRowFilter = imService.createAtRowFilter(utName);
//				utRowFilter.forUTRowKeyEqualTo(key);
				utRow = athandler.getATRowByKey(key,true );
				utRow.delete();
				athandler.save();
//				vector = utRowFilter.exec();
//				if(vector != null && vector.size()>0)
//				{
//					utRow = vector.get(0);
//				
//				}
//				utRow.delete();
				
				
				return ajaxDoneSuccess();
			}
			else
			{
				throw new Exception("无法找到该对象的key");
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}
}
