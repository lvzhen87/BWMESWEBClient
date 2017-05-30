<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="qj/js/plan.js"></script>
<script src="js/ajaxfileupload.js"></script>

<!-- 焊接上线   -->

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
          <input class="form-control" id="vin" type="text" >
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
          <button class="btn btn-success" id="btnQuery" type="button" onclick="printBarcodeBtn()" style="padding-left:25px;padding-right:25px;">打印</button>
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
          	<th width="10%">项目</th>
          	<th width="10%">车型</th>
          	<th width="10%">车型描述</th>
          	<th width="10%">颜色</th>
          	<th width="10%">订单号</th>
          
          	<th width="10%">计划上线日期</th>
          	<th width="10%">配置</th>
          	<th width="10%">特殊订单</th>
          	<th width="10%">上线顺序</th>
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
	
	  getWbonUploadCarList();
    $("#vin").focus();
  });
  function scanVin(){
   
      var vin = getVin();
      if(vin==""){
        return false;
      }
      getCarInformation(vin);
    
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

          printBarcodeTxt(vin);
          getWbonUploadCarList();
          $("#vin").focus();
        }
        return false;
      }
    });
  }
  function getWbonUploadCarList(){
  
    $.ajax({
      url: "passStation.sp?getWbonUploadCarList",
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
			    $("#vin").val( m.vin );
				scanVin();
				
			}
            
            html = "<tr>";

			
            html += "<td>"+m.vin+"</td><td>"+m.car_project+"</td><td>"+m.car_type+"</td><td>"+m.car_type_desc+"</td><td>"+m.color+"</td><td>"+m.color+"</td><td>"+m.plan_id+"</td><td>"+m.plan_start_time+"</td><td>"+m.vehicle_configuration+"</td><td>"+m.special_order+"</td><td>"+m.csn+"</td>";
            html += "</tr>";
            
            $("#list").append(html);
          }
        }
      }
    });
  }

  function printBarcode(vin){
	  alert( "act")
		var fso = new ActiveXObject("Microsoft.XMLDOM");
		alert("actend")
		var tf = fso.CreateTextFile("C://output.prn", true);
		alert("tf1")
		
		tf.Write(vin);
		tf.Close();
		alert(tf222)
		tf = fso.CreateTextFile("c:/printBC.bat", true);
		//str = "copy c:\\Output.prn COM1";
		str = "copy c:\\Output.prn \\hanjieshangxian\bm";
		tf.WriteLine(str);
		tf.Close();

		var objShell = new ActiveXObject("WScript.Shell");
		var iReturnCode = objShell.Run("c:/printBC.bat", 0, true);
  }

  function printBarcode1(vin){
	  alert( "act")
		var fso = new ActiveXObject("Scripting.FileSystemObject");
		alert("actend")
		var tf = fso.CreateTextFile("C://output.prn", true);
		alert("tf1")
		
		tf.Write(vin);
		tf.Close();
		alert(tf222)
		tf = fso.CreateTextFile("c:/printBC.bat", true);
		//str = "copy c:\\Output.prn COM1";
		str = "copy c:\\Output.prn \\hanjieshangxian\bm";
		tf.WriteLine(str);
		tf.Close();

		var objShell = new ActiveXObject("WScript.Shell");
		var iReturnCode = objShell.Run("c:/printBC.bat", 0, true);
  }

  
  function printBarcodeBtn(  ){
	  alert(11)
	  var vin = getVin();
	  alert( vin );
	  printBarcodeTxt( vin );
  }
  
  
  function printBarcodeTxt( vin ){
	  $.ajax({
	      url: "passStation.sp?printBarcodeTxt",
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
					alert( message)
					printBarcode(message) ;
				}else{
					alert("VIN["+vin+"]打印失败 ");
				}
	        
	      }
	    });
  }
  
</script>