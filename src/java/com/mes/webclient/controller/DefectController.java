
package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.Site;
import com.mes.compatibility.client.SiteFilter;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.shopflow.common.constants.filtering.IAreaFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.ISiteFilterAttributes;
import com.mes.webclient.app.demo.api.QMDefectAPI;
import com.mes.webclient.controller.vo.DefectTOPNVO;
import com.mes.webclient.controller.vo.QMDefectCodeVO;
import com.mes.webclient.controller.vo.QMDefectPartVO;
import com.mes.webclient.controller.vo.QMDefectVO;
import com.mes.webclient.controller.vo.QMVINCSNVO;
import com.mes.webclient.controller.vo.SiteVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.StringUtil;

@Controller("DefectController")
@RequestMapping("/defect.sp")
public class DefectController extends BaseController
{
	@Autowired
	IMService imService;
	QMDefectAPI defectAPI = new QMDefectAPI();

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(Model model)
	{
		model.addAttribute("currntName", WebServerProxy.getCurrentUser().getName());
		
		return "im/qmdefectinput/list";
	}
	
	/**
	 * 查询DefectCodeLevel1
	 */
	@RequestMapping(params = "toListCode1")
	public void toMainPage(HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> code1Vec = defectAPI.getLevel1Code();
			ArrayList<QMDefectCodeVO> vos = new ArrayList<QMDefectCodeVO>();
			for(String[] code1array : code1Vec)
			{
				QMDefectCodeVO vo = new QMDefectCodeVO();	
				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setCodeLevel1(code1array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	/**
	 * 查询DefectCodeLevel2
	 */
	@RequestMapping(params = "toListCode2")
	public void toListCode2(@RequestParam("codeLevel1") String level1, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> code1Vec = defectAPI.getLevel2CodeByLevel1(level1);
			ArrayList<QMDefectCodeVO> vos = new ArrayList<QMDefectCodeVO>();
			for(String[] code1array : code1Vec)
			{
				QMDefectCodeVO vo = new QMDefectCodeVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setCodeLevel2(code1array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	/**
	 * 查询DefectLevel1
	 */
	@RequestMapping(params = "toListLevel1")
	public void toListLevel1(HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> level1Vec = defectAPI.getLevel1();
			ArrayList<QMDefectPartVO> vos = new ArrayList<QMDefectPartVO>();
			for(String[] level1array : level1Vec)
			{
				QMDefectPartVO vo = new QMDefectPartVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setLevel1(level1array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	/**
	 * 查询DefectLevel2
	 */
	@RequestMapping(params = "toListLevel2")
	public void toListLevel2(@RequestParam("level1") String level1, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> level2Vec = defectAPI.getLevel2ByLevel1(level1);
			ArrayList<QMDefectPartVO> vos = new ArrayList<QMDefectPartVO>();
			for(String[] level2array : level2Vec)
			{
				QMDefectPartVO vo = new QMDefectPartVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setLevel2(level2array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	
	/**
	 * 查询DefectLevel3
	 */
	@RequestMapping(params = "toListLevel3")
	public void toListLevel3(@RequestParam("level1") String level1, @RequestParam("level2") String level2, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> level3Vec = defectAPI.getLevel3ByLevel12(level1, level2);
			ArrayList<QMDefectPartVO> vos = new ArrayList<QMDefectPartVO>();
			for(String[] level3array : level3Vec)
			{
				QMDefectPartVO vo = new QMDefectPartVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setLevel3(level3array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	/**
	 * 查询DefectLevel4
	 */
	@RequestMapping(params = "toListLevel4")
	public void toListLevel4(@RequestParam("level1") String level1, @RequestParam("level2") String level2, @RequestParam("level3") String level3, 
		HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> level4Vec = defectAPI.getLevel4ByLevel123(level1, level2, level3);
			ArrayList<QMDefectPartVO> vos = new ArrayList<QMDefectPartVO>();
			for(String[] level4array : level4Vec)
			{
				QMDefectPartVO vo = new QMDefectPartVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setLevel4(level4array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	/**
	 * 查询DefectLevel5
	 */
	@RequestMapping(params = "toListLevel5")
	public void toListLevel5(@RequestParam("level1") String level1, @RequestParam("level2") String level2, @RequestParam("level3") String level3, @RequestParam("level4") String level4, 
		HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> level5Vec = defectAPI.getLevel5ByLevel1234(level1, level2, level3, level4);
			ArrayList<QMDefectPartVO> vos = new ArrayList<QMDefectPartVO>();
			for(String[] level5array : level5Vec)
			{
				QMDefectPartVO vo = new QMDefectPartVO();	
//				vo.setKey(Integer.parseInt(code1array[1]));
				vo.setLevel5(level5array[0]);
				vos.add(vo);
			}
//			rt = JSONArray.fromObject(vos);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	/**
	 * 查询前一辆车
	 */
	@RequestMapping(params = "toPrevVIN")
	public void toPrevVIN(@RequestParam("currentvin") String current, 
		HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			String prevVIN = defectAPI.getPrevVIN(current);
			ArrayList<QMVINCSNVO> vos = new ArrayList<QMVINCSNVO>();

			QMVINCSNVO vo = new QMVINCSNVO();	
//			
			vo.setPrevvin(prevVIN);
			vos.add(vo);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	/**
	 * 查询后一辆车
	 */
	@RequestMapping(params = "toNextVIN")
	public void toNextVIN(@RequestParam("currentvin") String current, 
		HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			String nextVIN = defectAPI.getNextVIN(current);
			ArrayList<QMVINCSNVO> vos = new ArrayList<QMVINCSNVO>();

			QMVINCSNVO vo = new QMVINCSNVO();	
//			
			vo.setNextvin(nextVIN);
			vos.add(vo);
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	/**
	 * 查询缺陷代码
	 */
	@ResponseBody
	@RequestMapping(params = "toListTopN")
	public String toMainPage(DefectTOPNVO topNVO)
	{
		JSONArray rt = null;
		try
		{
//			String no = topNVO.getNo();
//			String issueDes = topNVO.getIssueDes();
			Vector<String[]> topNVec = defectAPI.getTopNData();
			Vector<Object> vos = new Vector<Object>();
			for(String[] topN : topNVec)
			{
				DefectTOPNVO vo = new DefectTOPNVO();
				vo.setKey(Integer.parseInt(topN[0]));
				vo.setNo(topN[1]);
				vo.setIssueDes(topN[2]);
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		return null;
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(SiteVO siteVO, Model model)
	{
		long key = siteVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				Site site = imService.getSiteByKey(key);
				siteVO.setSiteName(site.getDescription());
				siteVO.setSiteNumber(site.getName());
				siteVO.setSiteCategory(site.getCategory());
				siteVO.setKey(key);
				siteVO.setAreas(site.getAreas());
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}
		model.addAttribute(VIEW_OBJECT, siteVO);
		return "im/qmdefectinput/addOrEdit";
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
					Site site = imService.getSiteByKey(key);
					Response response = site.delete(
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
	public String toMainPage(SiteVO siteVO)
	{
		JSONArray rt = null;
		try
		{
			String siteNumber = siteVO.getSiteNumber();
			String siteName = siteVO.getSiteName();
			SiteFilter siteFilter = imService.createSiteFilter();
			if (StringUtil.isNotNull(siteNumber))
			{
				siteFilter.forNameContaining(siteNumber);
			}
			if (StringUtil.isNotNull(siteName))
			{
				siteFilter.addSearchBy(
					ISiteFilterAttributes.DESCRIPTION,
					IFilterComparisonOperators.CONTAINING, siteName);
			}
			siteFilter.setMaxRows(siteVO.getQueryNum());
			Vector<Site> sites = siteFilter.exec();
			Vector<Object> vos = new Vector<Object>();
			for(Site site : sites)
			{
				SiteVO vo = new SiteVO();
				vo.setKey(site.getKey());
				vo.setSiteNumber(site.getName());;
				vo.setSiteName(site.getDescription());
				vo.setCategory(site.getCategory());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (MESException e)
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
	String save(QMDefectVO defectVO)
	{
		try
		{
			Response response = defectAPI.saveDefect(defectVO);
			if (response.isError())
			{
				throw new Exception("保存缺陷失败，原因为:" + response.getFirstErrorMessage());
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
	 * 
	 */
	@RequestMapping(params = "getWarnMessageNumber")
	public void getWarnMessageNumber(HttpServletRequest req, HttpServletResponse res){

//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		try
		{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("defect_sender");
			filter.forColumnNameNotEqualTo("status", "1");
			Vector atRows = filter.exec();
			jsonObj.put("result", atRows.size());
			res.setContentType("text/html; charset=UTF-8");
			
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(params = "getDefectSenderList")
	public String getDefectSenderList(Model model){
		String sql = " SELECT TOP 10 atr_key, defect_desc_S, check_user_S, ISNULL(quality_gate_1_S, '')+'/'+ISNULL(quality_gate_2_S, '') QUALITY_GATE_S FROM UD_defect_sender WHERE status_S <> '1' ORDER BY last_modified_time DESC ";
		try {
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 100);
			model.addAttribute("list", vectorData);
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "im/qmDefectSender/defectSendList";
	}

	/**
	 * 
	 */
	@RequestMapping(params = "openDefectSenderList")
	public String openDefectSenderList(Model model){
		return "im/qmDefectSender/defectSendList";
	}

	/**
	 * 
	 */
	@RequestMapping(params = "setDefectSender")
	public void setDefectSender(HttpServletRequest req, HttpServletResponse res){
		String atrKey = (String) req.getParameter("key");
		String[] keys = atrKey.split(",");
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("defect_sender");
			for (String key : keys) {
				filter.forUTRowKeyEqualTo(Long.parseLong(key+""));
				Vector atRows = filter.exec();
				UTRow atRow = (UTRow) atRows.get(0);
				atRow.setValue("status", "1");
				atRow.save(null, null, null);
			}
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	
	
}