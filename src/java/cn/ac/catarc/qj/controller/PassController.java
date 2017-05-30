package cn.ac.catarc.qj.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.catarc.qj.function.CreateCsn;
import cn.ac.catarc.qj.function.FunctionConstants;
import cn.ac.catarc.qj.function.PassStation;
import cn.ac.catarc.qj.util.FileUtil;
import cn.ac.catarc.qj.util.StringUtil;
import cn.ac.catarc.qj.util.VINHelper;
import cn.ac.catarc.qj.vo.OrderVO;
import cn.ac.catarc.qj.vo.PlanVO;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.OrderFilter;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.BaseController;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.DateTimeUtils;

@Controller("passController")
@RequestMapping("/pass.sp")
public class PassController extends BaseController {
	@Autowired
	IMService imService;

	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toScanPage")
	public String toScanPage(){
		return "qj/passstation/scan";
	}

	/**
	 * 获得车辆数据
	 */
	@RequestMapping(params = "getCarInformation")
	@ResponseBody 
	public String getCarInformation(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		HashMap<String, Object> map = null;
		try {
			String atDefinitionName = "car";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			filter.forColumnNameEqualTo("vin", vin);
			Vector<ATRow> vectorData = filter.exec();
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return JSONArray.fromObject(map).toString();
	}

}
