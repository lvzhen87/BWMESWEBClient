<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="qj/js/plan.js"></script>
<script src="js/ajaxfileupload.js"></script>

<!-- 电池上线   -->
<!-- MAIN PAGE -->
<div class="panel">
  <div class="panel-body">
    <form class="form-horizontal" role="form">
     <div class="form-group">
       <label class="col-lg-1 col-sm-1 control-label"></label>
        <div class="col-lg-2 col-sm-2">
        
        </div>
        <label class="col-lg-1 col-sm-1 control-label"></label>
        <div class="col-lg-8 col-sm-8">
         
        </div>
      </div>
           <div class="form-group">
       <label class="col-lg-1 col-sm-1 control-label"></label>
        <div class="col-lg-2 col-sm-2">
        
        </div>
        <label class="col-lg-1 col-sm-1 control-label"></label>
        <div class="col-lg-8 col-sm-8">
         
        </div>
      </div>
      
      <div class="form-group">
        <label class="col-lg-1 col-sm-1 control-label">电池包ID</label>
        <div class="col-lg-3 col-sm-3">
          <input class="form-control" id="ele_id" type="text" >
        </div>      
        <label class="col-lg-1 col-sm-1 control-label">订单</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="ele_order_num" type="text" readonly>
        </div>
        <label class="col-lg-1 col-sm-1 control-label">电池型号</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="ele_part_num" type="text" readonly>
        </div>
        <div class="col-lg-1 col-sm-1 ">
          <button class="btn btn-success" id="btnQuery" type="button" onclick="passStation()"  >上线</button>
        </div>
         <div class="col-lg-1 col-sm-1 ">
           <button class="btn btn-success" id="btnQuery" type="button" onclick="getEleUploadList()"  >刷新</button>
        </div>
        
      </div>
     
      <div class="form-group">

        <label class="col-lg-1 col-sm-1 control-label">MESSAGE</label>
        <div class="col-lg-11 col-sm-8">
          <input class="form-control" id="message" type="text" readonly>
        </div>
      </div>
    </form>
  </div>
</div>
<div class="panel">
  <div class="panel-body">
    <div class="col-md-12">
      <table class="table table-hover table-striped table-condensed">
        <thead>
          <tr>
          	<th width="10%">电池包ID号</th>
          	<th width="10%">电池型号</th>
          	<th width="10%">电池型号描述</th>
            <th width="10%">订单号</th>
          	<th width="10%">上线日期</th>
          	<th width="10%">上线顺序</th>
          </tr>
        </thead>
        <tbody id="list"></tbody>
      </table>
    </div>
  </div>
</div>
<script>
  $(function() {
	  getEleUploadList();
      $("#ele_id").focus();
  });
  function scanVin(){
      var vin = getVin();
      if(vin==""){
        return false;
      }
  }
  function getVin(){
    $("#message").val("").parent().removeClass("has-error");
    var vin = $("#ele_id").val().toUpperCase();
    $("#ele_id").val(vin);
    if(vin==null || vin==""){
      $("#message").val("请扫描或输入电池包号").parent().addClass("has-error");
      $("#ele_id").focus();
      return "";
    }
    return vin;
  }
  
 
  function passStation(){
    var vin = getVin();
   
    if(vin==""){
      return false;
    }
    eleUpload(vin);
  }
  function eleUpload(vin ){
    $.ajax({
      url: "passStation.sp?eleUpload",
      type: "POST",
      dataType: "json",
      data: {vin: vin},
      async: false,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(result!=null && result=="FAIL"){
          $("#message").val(message).parent().addClass("has-error");
          $("#ele_id").select();
        }
        else{
           $("#message").val("").parent().addClass("has-success");
          $(":text").val(""); 
           getEleUploadList();
          $("#ele_id").focus();
        }
        return false;
      }
    });
  }
  function getEleUploadList(){
    $.ajax({
      url: "passStation.sp?getEleUploadList",
      type: "POST",
      dataType: "json",
      //data: {station: station},
      async: true,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(result!=null && result=="FAIL"){
          $("#message").val(message).parent().addClass("has-error");
          $("#vin").select();
          return false;
        }
        else{
          var list = data.list;
          var html = "";
          $("#list").empty();
          for(var i=0;i<list.length;i++){
              
            var m = list[i];
			if( i== 0 ){
			    $("#ele_id").val( m.ele_id );
			    $("#ele_order_num").val( m.ele_order_num );
			    $("#ele_part_num").val( m.ele_part_num );
			   
				
			}
            
            html = "<tr>";
            html += "<td>"+m.ele_id+"</td><td>"+m.ele_part_num+"</td><td>"+m.ele_part_desc+"</td><td>"+m.ele_order_num+"</td><td>"+m.start_time+"</td><td>"+m.csn+"</td>";
            html += "</tr>";
            
            $("#list").append(html);
          }
        }
      }
    });
  }
</script>