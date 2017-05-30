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
  <div class="panel-body">
    <form class="form-horizontal" role="form" action="dslist.sp?save" onsubmit="return toSubmit(this)">
      <div class="tab-content">
        <div class="tab-pane active" id="basic">
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="listName"><span class="text-danger">* </span>数据列表名</label>
            <div class="col-lg-8">
              <input class="form-control" name="listName" type="text" value="${vo.listName }" required placeholder="必填">
              <input type="hidden" name="key" value="${vo.key }">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="listCategory">数据列表分类</label>
            <div class="col-lg-8">
              <input class="form-control" name="listCategory" type="text" value="${vo.listCategory }">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="listDesc">数据列表描述</label>
            <div class="col-lg-8">
              <textarea class="form-control" name="listDesc" rows="3">${vo.listDesc }</textarea>
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label" for="listItemStr">数据列表内容</label>
            <div class="col-lg-8">
              <textarea class="form-control" name="listItemStr" rows="15">${vo.listItemStr}</textarea>
            </div>
          </div>
        </div> 
        <div class="form-group">
          <div class="col-lg-offset-8 col-lg-3">
            <button class="btn btn-primary" type="submit">保存</button>
            <button class="btn btn-primary" data-dismiss="modal" type="button">取消</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
  toSubmit = function(form) {
    //提交表单
    var $form = $(form);
    $.post($form.attr("action"), $form.serializeArray(), function(result) {
 	 getTableData();
     $("#modifyModal").modal("hide");
      operationConfirm(result);
    });
	return false;
  };
</script>