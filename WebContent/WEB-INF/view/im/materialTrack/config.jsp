<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/materialTrackConfig.js"></script>
<!-- MAIN PAGE -->
<div class="panel">
  <div class="panel-body">
    <form id="pageform" class="form-horizontal adminex-form" action="materialTrack.sp?toConfig" method="post" onsubmit="return submitForm(this)">
      <div class="col-md-12">
        <div class="row">
          <label class="col-sm-1 control-label col-lg-1">工位:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="station" name="station" type="text" value="${vo.station}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">零件:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="partNumber" name="partNumber" type="text" value="${vo.partNumber}">
          </div>
          <label class="col-sm-1 control-label col-lg-1">描述:</label>
          <div class="col-sm-2 col-lg-2">
            <input class="form-control" id="description" name="description" type="text" value="${vo.description}">
          </div>
          <div class="col-sm-1 col-lg-1">
            <button class="btn btn-success" type="button" onclick="queryList()">查询</button>
          </div>
          <div class="col-sm-2 col-lg-2">
            <button class="btn btn-success" type="button" onclick="resetCondition()">重置</button>
          </div>
        </div>
      </div>
    <!--    <div class="form-group">
        <div>
          <a class="btn btn-primary" onclick="insertMaterialTrack()">新建</a>
          <a class="btn btn-primary" onclick="deleteMaterialTrack()">删除</a>
        </div>
      </div>  -->
    </form>
  </div>
</div>
<div class="panel">
  <header class="panel-heading">关键件配置列表
    <span>
      <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyObj" href="materialTrack.sp?toAddOrEdit">
        <i class="fa fa-plus" title="新增"></i>
      </a>
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
<!-- ADD OR EDIT -->
<div class="modal fade" id="modifyObj" data-backdrop="static" aria-hidden="true" aria-labelledby="modifyObj" role="dialog" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>

<!-- SELECT -->
<div aria-hidden="true" aria-labelledby="select" role="dialog"
	tabindex="-1" id="selectModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="selectContent"></div>
	</div>
</div>
<script>
  //解决重复加载时不更新数据问题
  $("#modifyObj").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    queryList();
  });
  deleteObj = function(key) {
    $.post("materialTrack.sp?del", {key:key}, function(result){
      operationConfirm(result);
      queryList();
    });
  };
  function insertMaterialTrack(){
    $.ajax({
      url: "materialTrack.sp?insert",
      type: "POST",
      dataType: "json",
      async: false,
      cache: false
    });
  }
  function deleteMaterialTrack(){
    $.ajax({
      url: "materialTrack.sp?delete",
      type: "POST",
      dataType: "json",
      async: false,
      cache: false
    });
  }
  function resetCondition(){
    $("#pageform input").val("");
  }
</script>