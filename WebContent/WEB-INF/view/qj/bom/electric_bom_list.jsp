<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="qj/js/ele_bom_excel.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
function ajaxFileUpload(rdkey) {
	 $("#alertMessage").html("").hide();
	 var uploadFilePath = $("#myfile").val();
	 if(uploadFilePath.trim() == ""){
			 
			 $("#alertMessage").html('<div class="alert alert-danger alert-dismissable"><button class="close" data-dismiss="alert" type="button" aria-hidden="true">&times;</button>上传模版路径不能为空</div>').show();
		     return;
		 
		}
		  
	  $.ajaxFileUpload({
	    url : "plan.sp?importExcelEleBom",
	    secureuri : false,
	    fileElementId : 'myfile',
	    dataType : 'text',
	    success : function(data, status) {
		
	                if(data.indexOf( "操作成功" ) >= 0) {
	                  $("#modifyObj").modal("hide");
	                  queryPlan();
	                }
	                else {
	                	 $("#alertMessage").html('<div class="alert alert-danger alert-dismissable"><button class="close" data-dismiss="alert" type="button" aria-hidden="true">&times;</button>'+data+'</div>').show();
	        		     return;
	                }
	                return;
	    }
	  });  
	  return false;  
	} 


</script>


<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="plan" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">父零件号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control"  id = "parentNumber" name="parentNumber"
							value="${vo.parentNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">子零件号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="partNum" name="sunNumber"
							value="${vo.sunNumber}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryPlan()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
	 
	
		<span>
			<div class="form-group">
				<div class="col-lg-1">
				<a   href="<%=request.getContextPath() %>/exceltemplate/ele_bom.xlsx" >模版下载</a>
				</div>
					
			
			
	            <label class="col-lg-1 col-sm-1 control-label" for="myfile">模板上传</label>
	            <div class="col-lg-3">
	              <input id="myfile" name="myfile" type="file" style="display:none">
	              <div class="input-group">
	                <input class="form-control" id="photoCover" type="text">
	                <span class="input-group-btn"> 
	                  <button class="btn btn-default" type="button" onclick="$('input[id=myfile]').click();">文件</button> 
	                </span> 
	              </div>
	            </div>
	          </div>
		
		
			<a class="btn btn-success" type="button" href="javascript:;" onclick="ajaxFileUpload(${vo.key })" style=" padding-left: 25px; padding-right: 25px; ">导入</a>		
			
		</span>
		<input type="hidden" id="thisPlan" value="-1">
    	<span class="tools pull-right" onclick="adjust(plantb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
	<div id="alertMessage"></div>
		<div class="col-md-12" >
			<table class="table table-hover table-striped table-condensed" id="plantb">
			</table>
		</div>
	</div>
</section>

<script type="text/javascript">
///////////////
var planKey;


$('input[id=myfile]').change(function() {
    $('#photoCover').val($(this).val());
  });
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  
$("#selectModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal"); 
});  


//点击行事件
$("#plantb tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	planKey = radio.val();
	$("#thisPlan").val(planKey);
})

var checkedPlan = function()
{
	var checkedPlanId = $("#thisPlan").val()+'-key';
	$("#"+checkedPlanId).attr("checked","checked");
}

$(document).ready(function(){
});
////////////////新增//////////////////
var decompose= function(){
	var key = $("input[name='selectPlan']:checked").val();
	if(key == null || key == "undefined"){
		bootbox.alert("请选择一条要分解的计划");
		return;
	} 
	var status=$("#"+key+"-status").html();
	if(status=="分解"){
		bootbox.alert("您选择的计划已分解，不能重复分解");
		return ;
	}
	
	$.post("plan.sp?decompose",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisPlan").val(-1);
    		getPlanData();
		}
		bootbox.alert(result);
  	});
}
</script>
