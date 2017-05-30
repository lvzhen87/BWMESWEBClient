<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/defectQuery.js"></script>
<!-- MAIN PAGE -->
<<style>
<!--
th, td { white-space: nowrap; }

-->
</style>


<div class="panel">
  <div class="panel-body">
    <form id="pageform" class="form-horizontal adminex-form">
      <div class="col-md-12">
      
        <div class="row">
        
		  <label class="col-sm-1 control-label col-lg-1">VIN号</label>
          <div class="col-sm-2">
            <input class="form-control" id="vin" name="vin" type="text" value="${vo.vin}">
          </div>
   
          <label class="col-sm-1 control-label col-lg-1">质量门1</label>
          <div class="col-sm-1">
            <input class="form-control" name="qualityGate_1" type="text" value="${vo.qualityGate_1}">
          </div>
          
           <label class="col-sm-1 control-label col-lg-1">质量门2</label>
          <div class="col-sm-1">
            <input class="form-control" name="qualityGate_2" type="text" value="${vo.qualityGate_2}">
          </div>
          
          <label class="col-sm-1 control-label col-lg-1">责任属性</label>
          <div class="col-sm-2">
            <input class="form-control" id="vin" name="responsibleParty" type="text" value="${vo.responsibleParty}">
          </div>
         
          <div class="col-sm-1">
             <button class="btn btn-success" type="button" onclick="queryList()">查询</button>
           
          </div>
          <div>
              <button class="btn btn-success" data-dismiss="modal" type="button">关闭</button>
         </div>
        
        </div>
       
          
      </div>
    </form>
  </div>
</div>


<div class="panel">
  <header class="panel-heading">
    
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
<div class="modal fade" id="modifyModal" data-backdrop="static" aria-hidden="true" aria-labelledby="modify" role="dialog" tabindex="-1">
  <div class="modal-dialog" style="width:60%;">
    <div class="modal-content"></div>
  </div>
</div>


<script>
  //解决重复加载时不更新数据问题
  $("#modifyModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
  });
  
  
</script>