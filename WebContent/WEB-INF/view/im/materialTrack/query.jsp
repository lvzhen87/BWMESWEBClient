<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/materialTrackQuery.js"></script>

<script src="js/ajaxfileupload.js"></script>
<!--pickers css-->
<link rel="stylesheet" href="js/bootstrap-datepicker/css/datepicker-custom.css" />
<link rel="stylesheet" href="js/bootstrap-timepicker/css/timepicker.css" />
<link rel="stylesheet" href="js/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" href="js/bootstrap-datetimepicker/css/datetimepicker-custom.css" />

<!--pickers plugins-->
<script src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-daterangepicker/moment.min.js"></script>
<script src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

<script src="js/pickers-init.js"></script>
<script type="text/javascript" >
function ajaxFileUpload(rdkey) {
	 $("#alertMessage").html("").hide();
	 var uploadFilePath = $("#myfile").val();
	 if(uploadFilePath.trim() == ""){
			 
			 $("#alertMessage").html('<div class="alert alert-danger alert-dismissable"><button class="close" data-dismiss="alert" type="button" aria-hidden="true">&times;</button>上传模版路径不能为空</div>').show();
		     return;
		 
		}
		  
	  $.ajaxFileUpload({
	    url : "plan.sp?importExcelKeyVin",
	    secureuri : false,
	    fileElementId : 'myfile',
	    dataType : 'text',
	    success : function(data, status) {
		
	                if(data.indexOf( "操作成功" ) >= 0) {
	                  $("#modifyObj").modal("hide");
	                  queryList();
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
<div class="panel">
  <div class="panel-body">
    <form id="pageform" class="form-horizontal" action="materialTrack.sp?toQuery" method="post">
      <div class="col-md-12 form-group">
        <div class="row">
          <label class="col-sm-1 control-label col-lg-1">VIN:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="vin" name="vin" type="text" value="${vo.vin}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">工位:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="station" name="station" type="text" value="${vo.station}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">零件:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="partNumber" name="partNumber" type="text" value="${vo.partNumber}">
          </div>
        </div>
      </div>
      <div class="col-md-12 form-group">
        <div class="row">
          <label class="col-sm-1 control-label col-lg-1">条码:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="barcode" name="barcode" type="text" value="${vo.barcode}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">描述:</label>
          <div class="col-sm-5 col-lg-5">
            <input class="form-control" id="description" name="description" type="text" value="${vo.description}">
          </div>
          <div class="col-sm-1 col-sm-offset-1 col-lg-1 col-lg-offset-1">
            <button class="btn btn-success" type="button" onclick="queryList()">查询</button>
          </div>
          <div class="col-sm-1 col-lg-1">
            <button class="btn btn-success" type="button" onclick="resetCondition()">重置</button>
          </div>
        </div>
      </div>
      <div class="col-md-12 form-group">
        <div class="row">
          <label class="col-sm-1 control-label col-lg-1">用户:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="scanUser" name="scanUser" type="text" value="${vo.scanUser}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">开始:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control form_datetime" id="scanTimeStart" name="scanTimeStart" type="text" value="${vo.scanTimeStart}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">结束:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control form_datetime" id="scanTimeEnd" name="scanTimeEnd" type="text" value="${vo.scanTimeEnd}">
          </div>
        </div>
      </div>
    </form>
  </div>
</div>
<div class="panel">
  <header class="panel-heading">
    
	<div id="alertMessage"></div>
    <span>
			<div class="form-group">
				<div class="col-lg-1">
				<a   href="<%=request.getContextPath() %>/exceltemplate/key_part_vin.xlsx" >模版下载</a>
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
    
    
    
    
    
  
    <span class="tools pull-right" onclick="adjust(querytable)">
      <a class="fa fa-chevron-down" href="javascript:;"></a>
    </span>
    <c:import url="/common/setNum.jsp"></c:import>
  </header>
  <div class="panel-body">
    <div class="col-md-12">
       <table id="querytable" class="table table-hover table-striped table-condensed">
      </table>
    </div>
  </div>
</div>
<script>
  //解决重复加载时不更新数据问题
  function resetCondition(){
    $("#pageform input").val("");
  }


  $('input[id=myfile]').change(function() {
	    $('#photoCover').val($(this).val());
   });  
</script>