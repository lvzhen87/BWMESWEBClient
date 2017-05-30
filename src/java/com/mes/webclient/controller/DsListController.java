
package com.mes.webclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.admin.dataobjects.filter.IFilterSortOrders;
import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.ListFilter;
import com.mes.compatibility.client.Response;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IListFilterAttributes;
import com.mes.webclient.controller.vo.DsListVO;
import com.mes.webclient.service.impl.IMService;

@Controller("dslistController")
@RequestMapping("/dslist.sp")
public class DsListController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/dslist/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(DsListVO listVO, Model model)
	{
		long key = listVO.getKey();
		model.addAttribute(INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				ListFilter listFilter = imService.getFunctions().createListFilter();
				listFilter.addSearchBy(IListFilterAttributes.KEY, IFilterComparisonOperators.EQUAL_TO, key);
				Vector<DsList> currentList = listFilter.exec();
				if(currentList.size() ==0){
					System.out.println("系统数据错误");
				}
				DsList dsListObj  = currentList.get(0);
				DsListVO listVODB = new DsListVO() ;
				long dbKey = dsListObj.getKey();
				String listName = dsListObj.getName();
				String listDesc = dsListObj.getDescription();
				String listCategory = dsListObj.getCategory();
				List<String > listItems =  dsListObj.getItems();
				String listItemStr = "";
				for(String item : listItems){
					listItemStr = listItemStr+item +"\r\n";
				}
				listVODB.setKey( dbKey );
				listVODB.setListName(listName);
				listVODB.setListDesc(listDesc);
				listVODB.setListCategory(listCategory);
				listVODB.setListItemStr(listItemStr);
				
				model.addAttribute(VIEW_OBJECT, listVODB);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}else{
			model.addAttribute(VIEW_OBJECT, listVO);
		}
		return "im/dslist/addOrEdit";
	}

	/**
	 * 删除
	 * @return 
	 */
	@RequestMapping(params = "del")
	public @ResponseBody String del(@RequestParam("key") long key)
	{
			try
			{
				if (key > 0)
				{
					ListFilter listFilter = imService.createListFilter();
					listFilter.addSearchBy(IListFilterAttributes.KEY, IFilterComparisonOperators.EQUAL_TO, key);
					Vector<DsList> currentList = listFilter.exec();
					if(currentList.size() ==0){
						System.out.println("系统数据错误");
					}
					DsList dsListObj  = currentList.get(0);
					Response response = dsListObj.delete(
						null, null);
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
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

	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String queryList( DsListVO listVO)
	{
		JSONArray rt = null;
		try
		{
			String listName = listVO.getListName();
			String listDesc = listVO.getListDesc();
			ListFilter listFilter = imService.createListFilter();
			if( listName !=null && !listName.equals("")){
				listFilter.addSearchBy(IListFilterAttributes.NAME, IFilterComparisonOperators.LIKE, "%"+listName+"%");
			}
			
			if( listDesc !=null && !listDesc.equals("")){
				listFilter.addSearchBy(IListFilterAttributes.DESCRIPTION, IFilterComparisonOperators.LIKE, "%"+listDesc+"%");
			}
			listFilter.addOrderBy(IListFilterAttributes.CREATIONTIME, IFilterSortOrders.DESCENDING);
			listFilter.setMaxRows(listVO.getQueryNum());
			Vector<DsList> currentList = listFilter.exec();
			Vector<Object> vos = new Vector<Object>();
			for(DsList dsList : currentList)
			{
				String dsName = dsList.getName();
				if(!"DistributedEventConfiguration".equals(dsName)&&
					!"PartReplacementType".equals(dsName)&&
					!"ProcessBomItemReplacementType".equals(dsName)&&
					!"UnitOfMeasure".equals(dsName)&&
					!"SICCode".equals(dsName)&&
					!"Category".equals(dsName))
				{
					DsListVO vo = new DsListVO();
					vo.setKey(dsList.getKey());
					vo.setListName(dsList.getName());
					vo.setListDesc(dsList.getDescription());
					vo.setListCategory(dsList.getCategory());
					vos.add(vo);
				}
			}
			rt = JSONArray.fromObject(vos);
//			System.out.println(rt.toString());
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;

	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(DsListVO listVO  , HttpServletRequest request,
			HttpServletResponse response )
	{
		try
		{
			long key = listVO.getKey();
			String listName = listVO.getListName();
			String listDesc = listVO.getListDesc();
			String listCategory = listVO.getListCategory();
			DsList dsListObj  = null;
			if( key > 0 ){
				ListFilter listFilter = imService.createListFilter();
				listFilter.addSearchBy(IListFilterAttributes.KEY, IFilterComparisonOperators.EQUAL_TO, key);
				Vector<DsList> currentList = listFilter.exec();
				if(currentList.size() == 0){
					return ajaxDoneError( "数据列表【"+key+"】不存在");
				}
			    dsListObj  = currentList.get(0);
			}else{	
				ListFilter listFilter = imService.createListFilter();
				listFilter.addSearchBy(IListFilterAttributes.NAME, IFilterComparisonOperators.EQUAL_TO, listName);
				Vector<DsList> currentList = listFilter.exec();
				if(currentList.size() > 0){
					return ajaxDoneError( "数据列表【"+listName+"】已经存在");
				}
				dsListObj = imService.getFunctions().createList(listName);
			}
				
			if(dsListObj == null  ){
			}
			dsListObj.setCategory(listCategory);
			dsListObj.setName(listName);
			dsListObj.setDescription(listDesc);
			String listItemStr = listVO.getListItemStr();
			dsListObj.removeItems(dsListObj.getItems(),true);
			if( listItemStr != null && !listItemStr.equals("")){
				String[] strItem = listItemStr.split("\r\n");
				ArrayList 	listItem = new   ArrayList();
				for (String item : strItem) {
					if( item != null && ! item.trim().equals("")){
						listItem.add(item);
					}
				}
				
				dsListObj.addItems(listItem);
			}
			Response r =  dsListObj.save();
			if(r.isError()){
				return ajaxDoneError( "数据列表【"+listName+"】保存失败"+r.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}
}