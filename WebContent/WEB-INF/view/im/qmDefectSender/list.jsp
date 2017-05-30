<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include	file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/defectSender.js"></script>
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
        
		  <label class="col-sm-1 control-label col-lg-1">描述</label>
          <div class="col-sm-2">
            <input class="form-control" id="defectDesc" name="defectDesc" type="text" value="${vo.defectDesc}">
          </div>
   
           
          <div class="col-sm-1">
             <button class="btn btn-success" type="button" onclick="queryList()">查询</button>
           
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


	  <form class="form-inline" role="form">
	  
	            <c:forEach items="${list1}" var="o">
					
					  <div class="form-group">
					      <div class="checkbox">
						    <label>
						      <input type="checkbox"  value="${ o[0]}/${ o[1 ]}"> ${  o[0]    }/${ o[1]}
						    </label>
						  </div>
					  </div>
					
					
				</c:forEach>
				
				
	   <div>
              <button class="btn btn-success" type="button" onclick="defectSend()">信息推送</button>
              
                 <button class="btn btn-success" data-dismiss="modal" type="button">关闭</button>
         </div>
            <div>
           
         </div>
        
		  
	</form>
			  
			  



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