<%@page import="com.mes.compatibility.client.ATRow"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/common/include.inc.jsp"%>
<div class="panel">
  <header class="panel-heading custom-tab dark-tab">
    <button class="close" data-dismiss="modal" type="button" aria-hidden="true">×</button>
    <ul class="nav nav-tabs">
      <li class="active">
        <a data-toggle="tab" href="#basic">${invokeType}</a>
      </li>
    </ul>
  </header>
   <div id="alertMessage"></div>
  <div class="panel-body">
    <form class="form-horizontal" id="form1" role="form" >
      <div class="tab-content">
        <div class="tab-pane active" id="basic">
		           <div class="form-group-inline">
		          
			            <label class="col-lg-2 col-sm-3 control-label" for="businessUnit"><span class="text-danger">* </span>业务单元</label>
			            <div class="col-lg-3">
			              <input class="form-control" name="businessUnit" type="text" value="${vo.businessUnit }" required placeholder="必填">
			              <input type="hidden" name="key" value="${vo.key }">
			             </div>
		            </div>
	        
			     
					<div class="form-group">
					  	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">VIN号</label>
					  	<div class="col-lg-3">
					    	<input class="form-control" name="vin" type="text" value="${vo.vin }">
					 	</div>
			         
		            </div>
          
		          <div class="form-group-inline">
		           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">订单号</label>
		            <div class="col-lg-3">
		              <input class="form-control" name="orderNum" type="text" value="${vo.orderNum }">
		            </div>
		          </div>
       
           
            <div class="form-group">
	           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">一级质量门</label>
	            <div class="col-lg-3">
	              <input class="form-control" name="qualityGate_1" type="text" value="${vo.qualityGate_1} ">
	            </div>
	          </div>
           </div>
    
          <div class="form-group-inline">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">二级质量门</label>
            <div class="col-lg-3">
              <input class="form-control" name="qualityGate_2" type="text" value="${vo.qualityGate_2	 }">
            </div>
          </div>
          <div class="form-group">
           	<label class="col-lg-2 col-sm-3 control-label" for="defectLevel">缺陷等级</label>
            <div class="col-lg-3">
              <input class="form-control" name="defectLevel" type="text" value="${vo.defectLevel }">
            </div>
          </div>
            
            <div class="form-group">
           	<label class="col-lg-2 col-sm-3 control-label" for="responsibleParty">责任单位</label>
            <div class="col-lg-3">
              <input class="form-control" name="responsibleParty" type="text" value="${vo.responsibleParty}">
            </div>
          </div>
            
             
            
          <div class="form-group">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">质量问题项描述</label>
            <div class="col-lg-8">
               
              <textarea class="form-control"  name="defectDesc"  rows="2" >${vo.defectDesc}</textarea> 
            </div>
          </div>
           
              
          <div class="form-group">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">返工意见</label>
            <div class="col-lg-3" required  >
				<select name="repairOpinion" class="form-control input-sm" >
	               
	                <option   <c:if test="${vo.repairOpinion eq '意见一'}">selected="selected"</c:if>>意见一</option>
	                <option   <c:if test="${vo.repairOpinion eq '意见二'}">selected="selected"</c:if>>意见二</option>
	                
                </select>
            </div>
          </div>
           
                
          
           <div class="form-group-inline">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">供应商编码</label>
            <div class="col-lg-3">
            	 <select id="suplierNum" class="form-control input-sm">
	                <option value="供应商编码1"  >供应商编码1</option>
	                <option value="供应商编码2"  >供应商编码2</option>
                </select>
            </div>
          </div>
            
            <div class="form-group-inline">
            <div class="col-lg-3">
            	 <select id="suplierPart" class="form-control input-sm">
	                <option value="零件号1"  >零件号1</option>
	                <option value="零件号2"  >零件号2</option>
                </select>
            </div>
          <!--   <input type="button" value = "shengcheng" onclick="addNew()"> -->
            
              <span>
                <a class="btn btn-primary" href="#" onclick="addNew()">
                 添加
                </a>
              </span>
           
          </div>
          
          
        </div> 
        
     
        
        
     <div class="col-md-12">
      <table class="table table-hover table-striped table-condensed" id="vartable" style="table-layout:fixed;">
        <thead>
          <tr>
            <th width="15%">零部件图号</th>
            <th width="15%">零部件名称</th>
            <th width="15%">供应商编码</th>
            <th width="15%">供应商名称</th>
            <th width="10%">数量</th>
            <th width="20%">修改时间</th>
            <th>操做</th>
          </tr>
        </thead>
        <tbody>
        <%  
        
        
        Object oo = request.getAttribute("repairATRows");
        if(oo instanceof java.util.Vector ){
          Vector<ATRow> vectorATRow = (Vector<ATRow>)oo;
          if(vectorATRow != null){
            for( ATRow atrow : vectorATRow  ){ %>
            
             <tr id="<%=atrow.getKey()%>">
        	
	            <td id="td11">
	              <input class="form-control input-sm" id="aa" type="text"  size='10' value="<%=atrow.getValue("part_num")%>">
	            </td>
	            <td >
	               <input class="form-control input-sm" id="" type="text"  size='10' value="<%=atrow.getValue("part_name")%>">
	            </td>
	            <td >
	               <input class="form-control input-sm" id="" type="text"  size='10' value="<%=atrow.getValue("supplier_num")%>" >
	            </td>
	            <td >
	               <input class="form-control input-sm" id="" type="text"  size='10' value="<%=atrow.getValue("supplier_name")%>">
	            </td>
	            <td >
	               <input class="form-control input-sm" id="" type="text"  size='10' value="<%=atrow.getValue("part_count")%>" >
	            </td>
	             <td >
	               <input class="form-control input-sm" id="" type="text"  size='10'  value="<%=atrow.getValue("repair_time")%>">
	            </td>
	            <td style="vertical-align:middle;">
	              <a class="btn btn-danger btn-xs" href="#" onclick="del(this)">
	                <i class="fa fa-ban" title="删除"></i>
	              </a>
	            </td>
          </tr> 
       
             
          <%}
            
            }}%>
        </tbody>
      </table>
        
        <div class="form-group-inline">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">处理人</label>
            <div class="col-lg-3">
				 <input class="form-control" name="brandName" type="text" value="A">
            </div>
          </div>
                   
          <div class="form-group">
           	<label class="col-lg-2 col-sm-3 control-label" for="listCategory">处理日期</label>
            <div class="col-lg-3">
				 <input class="form-control" name="brandName" type="text" value="A">
            </div>
          </div>
           
        
			        <div class="form-group">
			          <div class="col-lg-offset-4 col-lg-8">
			            <button class="btn btn-primary" type="button" onclick="doSubmitSave()">保存</button>
			            <button class="btn btn-primary" type="button" onclick="doSubmit()">提交</button>
			            <button class="btn btn-primary" data-dismiss="modal" type="button">关闭</button>
			            <button class="btn btn-primary" type="button">收藏</button>
			          </div>
			        </div>
      </div>
         </div>
    </form>
  </div>
</div>
<script>

function getTableData1() {
    var varTableData = "";
    var defalutValue = "";
    var varType = "";
    
    $('#vartable tr').find('td').each(function() {
    	indexTD = $(this).index();
        if(indexTD < "5" ){
    	  	 varTableData = varTableData + $(this).children(0).val() + ","; 
        }else{
        	 varTableData = varTableData + $(this).children(0).val() + ";"; 
        }
      
      
       
  
     
    });
    
    return varTableData;
  }
function doSubmitSave() {
   
     var tableData1 = getTableData1();
     tableData1 = encodeURI(encodeURI(tableData1));
    //提交表单
    var $form = $("#form1");
    $.post("DefectRepairController.sp?save&tableData="+tableData1, $form.serializeArray(), function(result) {
    	 $("#modifyModal").modal("hide");
         operationConfirm(result);
       
         
      });

    return true;
  }


	doSubmit = function(form) {
    //提交表单
     var $form = $("#form1");
    $.post("DefectRepairController.sp?saveSubmit", $form.serializeArray(), function(result) {
 	 getTableData();
     $("#modifyModal").modal("hide");
      operationConfirm(result);
    });
	return false;
  };


  index = 1 ;
  function addNew() {
	
	    txtSuplierNum =$('#suplierNum').val();
	    txtSuplierPart =$('#suplierPart').val();
	    
	    var table1 = $('#vartable');
	    var row = $("<tr></tr>");
	    
	    var partNumber = $("<td></td>");
	    partNumber.append($("<input class='form-control input-sm' type='text' size='10' value='"+ txtSuplierPart+"_*"+ index  +"' >"));
	    row.append(partNumber);
	    
	    var partDesc = $("<td></td>");
	    partDesc.append($("<input class='form-control input-sm' type='text' size='10'  value='"+txtSuplierPart +"_"+ index +"'>"));
	    row.append(partDesc);

	    var supplierNum = $("<td style='vertical-align:middle;'></td>");
	    supplierNum.append( $("<input class='form-control input-sm' type='text' size='10' value='"+txtSuplierNum+"_*"+ index +"'>"));
	    row.append(supplierNum);

	    var supplierName = $("<td></td>");
	    supplierName.append( $("<input class='form-control input-sm' type='text' size='10' value='"+txtSuplierNum +"_"+ index +"'>"));
	    row.append(supplierName);

	    var partCount = $("<td></td>");
	    //tdMesObjectType.append($("<select><option>String</option><option>Long</option><option>Float</option><option>Decimal</option><option>DateTime</option><option>Boolean</option><option>Image</option><option>Java Object</option></select>"));
	    partCount.append($("<input class='form-control' type='text' size='4'  value = '1' >"));
	    row.append(partCount);
	    var handleTime = $("<td></td>");
	    //tdMesObjectType.append($("<select><option>String</option><option>Long</option><option>Float</option><option>Decimal</option><option>DateTime</option><option>Boolean</option><option>Image</option><option>Java Object</option></select>"));
	    date1 = new Date();
	    handleTime.append($("<input class='form-control input-sm' type='text' size='20' value='"+date1.toLocaleString()+"'>"));
	    row.append(handleTime);
	    
	    var tdOperation = $("<td style='vertical-align:middle;'></td>");
	    tdOperation.append($("<a class='btn btn-danger btn-xs' href='#' onclick='del(this)'><i class='fa fa-ban' title='删除'></i></a>"));
	    row.append(tdOperation);
	    table1.append(row);


	    index ++ ;
	  }

  	  function del(obj){
	    $(obj).parents("tr").remove();
	  }
</script>