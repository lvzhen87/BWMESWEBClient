<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/defectTopN.js"></script>

<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datepicker/css/datepicker-custom.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-timepicker/css/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datetimepicker/css/datetimepicker-custom.css" />


<!--pickers plugins-->
<script type="text/javascript"
	src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>


<script src="js/pickers-init.js"></script>

<script type="text/javascript" src="dataTables/media/im/codeLevel1.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<!-- <div class="container-fulid clearfix">
		<div class="col-md-12 column" align="right">
			<div class="btn-group">
				 <button class="btn btn-default" type="button" onclick="sendDefect()"><em class="glyphicon glyphicon-align-left"></em> 信息推送 </button> 
				 <button class="btn btn-default" type="button" onclick="showDialog()"><em class="glyphicon glyphicon-align-center"></em> 检验标准 </button> 
				 <a class="btn btn-default" data-toggle="modal" data-backdrop="static" data-target="#defectSenderModal" href="defect.sp?getDefectSenderList"><em class="glyphicon glyphicon-align-right"></em> 信息提醒<span class="badge" id="warnMessageNumber" style="background-color:red;"></span></a>
				 
				 
				 
				 <button class="btn btn-default" type="button"><em class="glyphicon glyphicon-align-justify"></em> 溢出问题 </button><button class="btn btn-default" type="button"><em class="glyphicon glyphicon-align-justify"></em> 无问题 </button>
		    </div>
		</div>
	</div> -->
	
	
	
	
<!--  	<div class="panel-body">-->
		<form id="defectForm" role="form" action="defect.sp?save"	 class="form-horizontal">
				<table class="table table-hover table-striped table-condensed ">
			   
               <tr>
               
               <td style="vertical-align:middle;"><label >整车编号</label></td>
               <td><input id= "vechileNum" name="vechileNum" type="text" class="form-control"   size="10"    onkeypress="scanVin(event)" ></td>
               <td style="vertical-align:middle;"><label>班次</label> </td>
               <td style="vertical-align:middle;"> 
               		<input id='shiftA' type="radio" name="shift" value="A" checked="checked"> A 
               		<input id='shiftB' type="radio" name="shift" value="B" > B 
               		<input id='shiftC' type="radio" name="shift" value="C" > C </td>
               <td style="vertical-align:middle;"><label>检验员</label></td>
               <td><input name="inputUser" type="text" class="form-control" style="overflow:visible" size="10" value=<%=request.getAttribute("currntName") %> readonly="readonly"></td>
               
               </tr>          		    			

               <tr>
               <td style="vertical-align:middle;"><label>订单编号</label></td>
               <td><input id="orderNum" name="orderNum" type="text" class="form-control" value="${vo.orderNum}" size="10" ></td>
               <td style="vertical-align:middle;"><label>VIN号</label></td><td><input id= "vin" name="vin" type="text" value="${vo.vin}" class="form-control" size="10" ></td>
               <td style="vertical-align:middle;"><label>是否接车 </label></td>
               <td  style="vertical-align:middle;">
               	   <input id='isAccessTrue' type="radio" name="isAccess" value="是 "  checked="checked"> 是 
                   <input id='isAccessFalse' type="radio" name="isAccess" value="否" > 否 </td>
               </tr>
               
               <tr>
               <td style="vertical-align:middle;"><label>检验日期</label></td>
               <td><input class="form-control form_datetime" size="16" type="text" value="${vo.checkDate}" name="checkDate" id="checkDate" /></td>
               <td style="vertical-align:middle;"><label>问题描述</label></td>
               <td><input id= "memo" name="memo" type="text" class="form-control" value="${vo.memo}" size="50" ></td>               
               </tr>
            </table>
 	
 <!--</section>
 	</div>

<section class="panel">  -->

    <header class="panel-heading custom-tab gray-tab" style="text-align: center">
	零部件结构
	</header>

	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level1</td><td><input name="level1" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="selLevel1" name="level1"  style="height: 200px;width: 140px;" size="45">
 	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level2</td><td><input name="level2" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="selLevel2" name="level2" style="height: 200px;width: 140px;" size="45">
 	
	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level3</td><td><input name="level3" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="selLevel3" name="level3" style="height: 200px;width: 140px;" size="45">
	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level4</td><td><input name="level4" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="selLevel4" name="level4" style="height: 200px;width: 140px;" size="45">
	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level5</td><td><input name="level5" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="selLevel5" name="level5" style="height: 200px;width: 140px;" size="45">
	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level1</td><td><input name="level6" type="text" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	
	<select id="codeLevel1Group" name="code1" style="height: 200px;width: 140px;BACKGROUND-COLOR:gray;color: white;" size="45">
 	
	</select>
	</td>
	</tr>

	</table>
	</td>
	<td>
	<table  class="table table-hover table-striped table-condensed" >
	<tr>
	<td style="vertical-align:middle;">level2</td><td><input id="defectCode2" name="level7" type="text" oninput="change()" style="overflow:visible" size="10" maxlength="50"></td>
	</tr>
	<tr>
	<td colspan="2">	
	<select id="codeLevel2Group" name="code2" style="height: 200px;width: 140px;BACKGROUND-COLOR:gray;color: white;" size="45">
	</select>
	</td>
	</tr>

	</table>
	</td>
	</tr>
	</table>
	<table  class="table table-hover table-striped table-condensed" >
	<tr><td style="vertical-align:middle;"><label>问题等级*</label></td><td style="vertical-align:middle;">
					   <input id="defectLevelA" type="radio" name="defectLevel" value="A" checked="checked"> A
                       <input id="defectLevelB" type="radio" name="defectLevel" value="B" > B 
                       <input id="defectLevelC" type="radio" name="defectLevel" value="C" > C
                       <td style="vertical-align:middle;"><label>责任属性*</label></td>
                       <td style="vertical-align:middle;">
                       <select id="dep" name="responsible" class="form-control select2 select2-hidden-accessible forms-select2"
						style="width: 100%;" tabindex="-1" >
                       
                       <option value="0">请选择</option>
                       <option value="DepA"> DepA </option>
                       <option value="DepB"> DepB </option>
                       <option value="DepC"> DepC </option>
                       </select>
                       </td>
                       <td align="right">
                       <!-- <button id="prevButton" class="btn btn-success" type="button" onclick="prev()">上一辆车</button> -->
                    <!--    <button class="btn btn-success" type="submit" >保存问题</button> -->
                       <button class="btn btn-success" type="button"   onclick="toSubmit()" >保存问题</button>
                       <!-- <button id="nextButton" class="btn btn-success" type="button" onclick="next()">下一辆车</button> -->
                  
                  
                  
                  <span>
         	<a  style="display: none;" id="a_query" href="DefectQueryController.sp?toMainPage" data-toggle="modal"
				class="btn btn-success" data-backdrop="static" data-target="#modifyModal"></a>
		   <a  style="display: none;" id="sender_query" href="DefectSenderController.sp?toMainPage" data-toggle="modal"
				class="btn btn-success" data-backdrop="static" data-target="#modifyModal1"></a>
		</span>
               <button class="btn btn-success" type="button" onclick="queryCar()">本车所有问题</button>                     
            
            
                       </td></tr>
	</table>
</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading custom-tab gray-tab" style="text-align: center">
	当前本质量门TOP10问题
	</header>
	<div class="panel-body">
	<div class="col-md-12">
			<table id="topNtb" class="table table-hover table-striped table-condensed">
			</table>
		</div>
	</div>
	</section>
	
	
<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modify" role="dialog"
	tabindex="-1" id="modifyModal" class="modal fade">
	<div class="modal-dialog" style="width:90%;">
		<div class="modal-content"></div>
	</div>
</div>

<div aria-hidden="true" aria-labelledby="modify" role="dialog"
	tabindex="-1" id="modifyModal1" class="modal fade">
	<div class="modal-dialog" style="width:50%;">
		<div class="modal-content"></div>
	</div>
</div>



<!-- DEFECT SENDER -->
<div aria-hidden="true" aria-labelledby="modify" role="dialog"
	tabindex="-1" id="defectSenderModal" class="modal fade">
	<div class="modal-dialog" style="width:90%;">
		<div class="modal-content"></div>
	</div>
</div>

 

<script type="text/javascript">





var requestURL = "defect.sp";
function setWarnMessageNumber(){
  $.getJSON(requestURL+"?getWarnMessageNumber&t="+Math.random(), function(data){
    var warnMessageNumber = data.result;
    if("0"!=warnMessageNumber){
      $("#warnMessageNumber").text(warnMessageNumber);
    }
    else{
      $("#warnMessageNumber").text("");
    }
  });
}
$(function(){
	  setWarnMessageNumber();
});

	
	
	 

//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//解决重复加载时不更新数据问题
$("#defectSenderModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
    //setWarnMessageNumber();
});  



toSubmit = function(form) {
	var selectedVal = $("#formsSelect").val();
	//提交表单
	var $form = $("#defectForm");
	var res = checkedValue() ;
	if( res == false ){
		return false
	}

	$.post($form.attr("action"), $form.serializeArray(), function(result) {
	 //	$("#modifyModal").modal("hide");
		//getSiteData();
	//	operationConfirm(result);
	
		 $("#vechileNum").val("")
		 
		 $("#vechileNum").focus();
	});
	return false;
}
checkedValue = function()
{
	
	
	if($("#selLevel1").val()==""  || $("#selLevel1").val()== null  )
	{

		bootbox.alert("零件部位不能为空!");
		return false;
	}
	else if($("#codeLevel1Group").val()==""   || $("#codeLevel1Group").val()== null )
	{

		bootbox.alert("缺陷不能为空!");
		return false;
	}
	return true;
}


function sendDefect(){
	 
 
	//$("#a_query").attr("href" , aHref +"&vin="+vinnum1);
 
	$("#sender_query").click();
	//$("#a_query").attr("href" , aHref);
}

function queryCar(){
	vinnum1 = $("#vin").val() ;
	if( vinnum1== "" ){
		alert("请输入VIN号");
		return 
	}
	
	aHref =  $("#a_query").attr("href");
	$("#a_query").attr("href" , aHref +"&vin="+vinnum1);
 
	$("#a_query").click();
	$("#a_query").attr("href" , aHref);
}


function scanVin(e){
	 
	var key = window.event ? e.keyCode : e.which;
    if(key==13){
      var inputVechileNum = $("#vechileNum").val().toUpperCase();
      if(inputVechileNum==""){
        return false;
      }
      $("vechileNum").val(inputVechileNum)
      getCarInformation(inputVechileNum);
    }
}


function getCarInformation(vin){
    $.ajax({
      url: "passStation.sp?getCarInformation",
      type: "POST",
      dataType: "json",
      data: {vin: vin},
      async: true,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(result!=null && result=="FAIL"){
                   return false;
        }
        else{
          $("#vin").val(vin);
          $("#orderNum").val(data.order_number);
          
        }
      }
    });
  }
</script>
