
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
        <label class="col-lg-1 col-sm-1 control-label">VIN</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="vin" type="text" onkeypress="scanVin(event)">
        </div>
        <label class="col-lg-1 col-sm-1 control-label">订单</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="orderNumber" type="text" readonly>
        </div>
        <label class="col-lg-1 col-sm-1 control-label">车型</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="carType" type="text" readonly>
        </div>
        <div class="col-lg-1 col-sm-1 col-lg-offset-1 col-sm-offset-1">
          <button class="btn btn-success" id="btnQuery" type="button" onclick="passStation()" style="padding-left:25px;padding-right:25px;">过站</button>
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-1 col-sm-1 control-label">颜色</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="color" type="text" readonly>
        </div>
        <label class="col-lg-1 col-sm-1 control-label">配置</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="vehicleConfiguration" type="text" readonly>
        </div>
        <label class="col-lg-1 col-sm-1 control-label">发动机</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="engineType" type="text" readonly>
        </div>
        <label class="col-lg-1 col-sm-1 control-label">特殊订单</label>
        <div class="col-lg-2 col-sm-2">
          <input class="form-control" id="specialOrder" type="text" readonly>
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
          	<th width="10%">VIN</th>
          	<th width="10%">顺序号</th>
          	<th id="th3" width="15%">过站时间</th>
          	<th width="10%">订单号</th>
          	<th width="10%">车型</th>
          	<th width="10%">颜色</th>
          	<th width="10%">配置</th>
          	<th width="10%">发动机类型</th>
          	<th>特殊订单</th>
          </tr>
        </thead>
        <tbody id="list"></tbody>
      </table>
    </div>
  </div>
</div>
<script>
  var station;
  $(function() {
    //if(station=="焊接上线"){
    //  $("#th3").text("打印时间");
    //}
    
    getPassStationList(station);
    $("#vin").focus();
  });
  function scanVin(e){
    var key = window.event ? e.keyCode : e.which;
    if(key==13){
      var vin = getVin();
      if(vin==""){
        return false;
      }

      getCarInformation(vin);
    }
  }
  function getVin(){
    $("#message").val("").parent().removeClass("has-error");
    var vin = $("#vin").val().toUpperCase();
    $("#vin").val(vin);
    if(vin==null || vin==""){
      $("#message").val("请扫描VIN号").parent().addClass("has-error");
      $("#vin").focus();
      return "";
    }
    
    return vin;
  }
  function getStation(){
	  
	    $("#message").val("").parent().removeClass("has-error");
	    station = $("#station").val();
	    $("#station").val(vin);
	    if(station==null || station==""){
	      $("#message").val("请扫描VIN号").parent().addClass("has-error");
	      $("#station").focus();
	      return "";
	    }
	    return station;
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
          $("#message").val(message).parent().addClass("has-error");
          $("#vin").select();
          return false;
        }
        else{
          $("#orderNumber").val(data.order_number);
          $("#carType").val(data.car_type);
          $("#color").val(data.color);
          $("#vehicleConfiguration").val(data.vehicle_configuration);
          $("#engineType").val(data.engine_type);
          $("#specialOrder").val(data.special_order);
        }
      }
    });
  }
  function passStation(){
    var vin = getVin();
    getStation();
    if(vin==""){
      return false;
    }
    passStationByVinAndStation(vin, station);
  }
  function passStationByVinAndStation(vin, station){
    $.ajax({
      url: "passStation.sp?passStationByVinAndStation",
      type: "POST",
      dataType: "json",
      data: {vin: vin, station: station},
      async: false,
      cache: false,
      success: function(data){
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(result!=null && result=="FAIL"){
          $("#message").val(message).parent().addClass("has-error");
          $("#vin").select();
        }
        else{
          $("#message").val("").parent().addClass("has-success");
          $(":text").val("");
          getPassStationList(station);
          $("#vin").focus();
        }
        return false;
      }
    });
  }
  function getPassStationList(station){
    $.ajax({
      url: "passStation.sp?getPassStationList",
      type: "POST",
      dataType: "json",
      data: {station: station},
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
          //alert(data.list);
          var list = data.list;
          var html = "";
          $("#list").empty();
          for(var i=0;i<list.length;i++){
             
            var m = list[i];
            html = "<tr>";
            html += "<td>"+m.vin+"</td><td>"+m.csn+"</td><td>"+m.entry_time+"</td><td>"+m.order_number+"</td><td>"+m.car_type+"</td><td>"+m.color+"</td><td>"+m.vehicle_configuration+"</td><td>"+m.engine_type+"</td><td>"+m.special_order+"</td>";
            html += "</tr>";
            
            $("#list").append(html);
          }
        }
      }
    });
  }
</script>