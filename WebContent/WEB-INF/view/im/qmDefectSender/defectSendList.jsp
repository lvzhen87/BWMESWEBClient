<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">信息推送</h4>
		</ul>
	</header>
	<div class="panel-body">
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>问题描述</th>
					<th>检验员</th>
					<th>质量门</th>
				</tr>
			</thead>
			<tbody id="equipmentContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o[0]}">
						<td><input type="checkbox" name="equipmentKey" value="${o[0]}"></td>
						<td id="${o[0]}_defectDesc">${o[1]}</td>
						<td id="${o[0]}_defectLevel" >${o[2]}</td>
						<td id="${o[0]}_dateTime" >${o[3]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<a type="submit" class="btn btn-primary" onclick="setDefectSender()" >已关注</a>
			<button type="button" data-dismiss="modal" class="btn btn-primary">关闭</button>
		</div>
	</div>
</section>

<script>
  function setDefectSender(){
    var checkedKey = "";
    $("input:checked").each(function(i){
      if(i>0){
        checkedKey += ",";
      }
      checkedKey += $(this).val();
    });
    var requestURL = "defect.sp";
    $.get(requestURL+"?setDefectSender&t="+Math.random(), {key : checkedKey}, function(data){
    	setWarnMessageNumber();
    	return;
    });
    $("#defectSenderModal").modal("hide");
  }
</script>