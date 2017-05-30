<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<!-- MAIN PAGE -->
<div class="panel">
  <div class="panel-body">
    <form class="form-horizontal" role="form">
      <div class="form-group">
        <label class="col-lg-1 col-sm-1 control-label">VIN</label>
        <div class="col-lg-3 col-sm-3">
          <input class="form-control" id="vin" type="text" onkeypress="scanVin(event)">
        </div>
        <label class="col-lg-1 col-sm-1 control-label">BARCODE</label>
        <div class="col-lg-3 col-sm-3">
          <input class="form-control" id="barcode" type="text" onkeypress="scanBarcode(event)">
        </div>
        <!-- <label class="col-lg-1 col-sm-1 control-label">全部</label> -->
        <label class="col-lg-1 col-sm-1 control-label"><span class="label label-info" id="totalNumber" style="font-size: 14px;">全部 0</span></label>
        <!-- <label class="col-lg-1 col-sm-1 control-label">已扫描</label> -->
        <label class="col-lg-1 col-sm-1 control-label"><span class="label label-info" id="scanNumber" style="font-size: 14px;">已扫描 0</span></label>
        <!-- <label class="col-lg-1 col-sm-1 control-label">未扫描</label> -->
        <label class="col-lg-1 col-sm-1 control-label"><span class="label label-success" id="notYetNumber" style="font-size: 14px;">未扫描 0</span></label>
      </div>
      <div class="form-group">
        <label class="col-lg-1 col-sm-1 control-label">MESSAGE</label>
        <div class="col-lg-10 col-sm-10">
          <input class="form-control" id="message" type="text" readonly>
        </div>
      </div>
      <!-- <div class="form-group">
        <div>
          <a class="btn btn-primary" onclick="insertMaterialTrack()">新建</a>
          <a class="btn btn-primary" onclick="deleteMaterialTrack()">删除</a>
        </div>
      </div> -->
    </form>
  </div>
</div>
<div class="panel">
  <div class="panel-body">
    <div class="col-md-12">
      <table class="table table-hover table-striped table-condensed">
        <thead>
          <tr>
          	<th width="30%">描述</th>
          	<th width="10%">零件</th>
          	<th width="20%">条码</th>
          	<th width="20%">扫描时间</th>
          	<th width="10%">扫描用户</th>
          	<th>操作</th>
          </tr>
        </thead>
        <tbody id="list"></tbody>
      </table>
    </div>
  </div>
</div>
<!-- ADD OR EDIT -->
<div class="modal fade" id="modifyModal" data-backdrop="static" aria-hidden="true" aria-labelledby="modify" role="dialog" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script>
  $(function() {
    $("#vin").focus();
  });
  function setFocus(){
    var totalNumber = $("#list>tr").size();
    var scanNumber = $("#list>tr.success").size();
    var notYetNumber = totalNumber - scanNumber;
    $("#totalNumber").text("全部 "+totalNumber);
    $("#scanNumber").text("已扫描 "+scanNumber);
    $("#notYetNumber").text("未扫描 "+notYetNumber);
    if(notYetNumber==0){
      $("#vin").select();
      $("#notYetNumber").removeClass("label-danger").removeClass("label-success").addClass("label-success");
    }
    else{
      $("#barcode").val("").focus();
      $("#notYetNumber").removeClass("label-danger").removeClass("label-success").addClass("label-danger");
    }
  }
  function scanBarcode(e){
    var key = window.event ? e.keyCode : e.which;
    if(key==13){
      $("#message").val("").parent().removeClass("has-error");
      var vin = $("#vin").val();
      if(vin==null || vin==""){
        $("#message").val("请扫描VIN号").parent().addClass("has-error");
        $("#vin").focus();
        return false;
      }
      var barcode = $("#barcode").val();
      if(barcode==null || barcode==""){
        $("#message").val("请扫描关键件条码").parent().addClass("has-error");
        $("#barcode").focus();
        return false;
      }
      $.ajax({
        url: "materialTrack.sp?scan",
        type: "POST",
        dataType: "json",
        data: {vin: vin, station: "TL04L", barcode: barcode},
        async: false,
        cache: false,
        success: function(data){
          var data = eval(data)[0];
          var result = data.RESULT;
          var message = data.MESSAGE;
          if(message!=null && message!=""){
            $("#message").val(message).parent().addClass("has-error");
            $("#barcode").select();
            return false;
          }
          else{
            getScanList(vin, "TL04L");
          }
        }
      });
    }
  }
  function getScanList(vin, station){
    $.ajax({
      url: "materialTrack.sp?getScanList",
      type: "POST",
      dataType: "json",
      data: {vin: vin, station: station},
      async: true,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(message!=null && message!=""){
          $("#message").val(message).parent().addClass("has-error");
          $("#vin").select();
          return false;
        }
        else{
          var list = data.LIST;
          $("#list").text("");
          for(var i in list){
            var html = "<tr id='"+list[i].key+"'><td>"+list[i].description+"</td><td>"+list[i].part_number+"</td><td>"+list[i].barcode+"</td><td>"+list[i].scan_time+"</td><td>"+list[i].scan_user+"</td><td></td></tr>";
            $("#list").append(html);
          }
          //$("#list td:last-child").each(function(i){
          //  var obj = $(this);alert(obj.text());
          //  if(obj.text()!=null && obj.text()!=""){
          //    obj.parent().addClass("success");
          //    obj.next(":last").text("重扫");
          //  }
          //});
          $("#list tr").each(function(i){
            var obj = $(this).children(":eq(2)");
            if(obj.text()!=null && obj.text()!=""){
              obj.parent().addClass("success");
              //obj.next(":last").text("重扫");
              //$(this).children(":last").text("重扫"+this.id);
              $(this).children(":last").html("<a class='btn btn-danger btn-xs' data-toggle='modal' href='#' " +
					" onclick='deleteBarcode("+this.id+")'><i class='fa fa-ban' title='重扫'></i></a>");
            }
          });
          setFocus();
        }
      }
    });
  }
  function scanVin(e){
    var key = window.event ? e.keyCode : e.which;
    if(key==13){
      $("#message").val("").parent().removeClass("has-error");
      var vin = $("#vin").val().toUpperCase();
      $("#vin").val(vin);
      if(vin==null || vin==""){
        $("#message").val("请扫描VIN号").parent().addClass("has-error");
        $("#vin").focus();
        return false;
      }
      getScanList(vin, "TL04L");
    }
  }
  function deleteBarcode(key){
    var vin = $("#vin").val();
    if(vin==null || vin==""){
      $("#message").val("请扫描VIN号").parent().addClass("has-error");
      $("#vin").focus();
      return false;
    }
    $.ajax({
      url: "materialTrack.sp?deleteBarcode",
      type: "POST",
      dataType: "json",
      data: {key: key},
      async: false,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(message!=null && message!=""){
          $("#message").val(message).parent().addClass("has-error");
          $("#barcode").select();
          return false;
        }
        else{
          getScanList(vin, "TL04L");
        }
      }
    });
  }
  //解决重复加载时不更新数据问题
  $("#modifyModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
  });
</script>