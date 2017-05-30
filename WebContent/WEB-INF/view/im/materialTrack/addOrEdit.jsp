<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="panel">
  <header class="panel-heading custom-tab dark-tab">
    <button class="close" data-dismiss="modal" type="button" aria-hidden="true">×</button>
    <ul class="nav nav-tabs">
      <li class="active">
        <a href="#basic" data-toggle="tab">${invokeType}</a>
      </li>
    </ul>
  </header>
  
  <div class="panel-body">
    <div id="alertMessage"></div>
    <form class="form-horizontal" id="form1" role="form" action="materialTrack.sp?saveConfig" method="post">
      <div class="tab-content">
        <div class="tab-pane active" id="basic">
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="station"><span class="text-danger">* </span>工位</label>
            <div class="col-lg-8">
              <input class="form-control" id="stationPara" name="station" type="text" value="${vo.station }" required placeholder="必填">
              <input name="key" type="hidden" value="${vo.key }">

            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="partNumber" ><span class="text-danger">* </span>零件</label>
            <div class="col-lg-8">
							<div class="input-group m-bot15">

								<input type="text" class="form-control" name="partNumber"
									id="partNumberPara" value="${vo.partNumber }" 
									placeholder="必填">
								<span class="input-group-btn">
									<a href="utility.sp?toSelectBasicPart" data-toggle="modal"
									class="btn btn-default" data-target="#selectModal"> <i
										class="fa fa-search" title="选择零件号"></i>
									</a>
								</span>
							</div>
						</div>
          </div>
          				
					
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="description">描述</label>
            <div class="col-lg-8">
              <input class="form-control" id="descriptionPara" name="description" type="text" value="${vo.description }">
            </div>
          </div>
          <div class="form-group">
            <div class="col-lg-offset-8 col-lg-3">
              <button class="btn btn-primary" type="button" onclick="doSubmit()">保存</button>
              <button class="btn btn-primary" data-dismiss="modal" type="button">取消</button>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>

 <!-- ADD OR EDIT -->
<!--<div class="modal fade" id="addPartModal" data-backdrop="static" aria-hidden="true" aria-labelledby="addPartModal" role="dialog" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>  -->
<script>
  function doSubmit() {
    $("#alertMessage").html("").hide();
    var station = $("#stationPara").val();
	if(station==null || station==""){
      $("#alertMessage").html('<div class="alert alert-danger alert-dismissable"><button class="close" data-dismiss="alert" type="button" aria-hidden="true">&times;</button>工位不能为空</div>').show();
      return;
    }
    var partNumber = $("#partNumberPara").val();
	if(partNumber==null || partNumber==""){
      $("#alertMessage").html('<div class="alert alert-danger alert-dismissable"><button class="close" data-dismiss="alert" type="button" aria-hidden="true">&times;</button>零件不能为空</div>').show();
      return;
    }
    //提交表单
    var $form = $("#form1");
    $.post("materialTrack.sp?saveConfig", $form.serializeArray(), function(result){
      $("#modifyObj").modal("hide");
      operationConfirm(result);
    });
    return true;
  }
</script>