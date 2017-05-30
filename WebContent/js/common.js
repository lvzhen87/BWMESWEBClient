//加载页面
loadpage = function(url,caption) {
	$("#appContent").load(url);
	if(caption == null){
		$("#caption").html("生产制造系统");
	}else{
		$("#caption").html(caption);
	}
}

// 提交表单
submitForm = function(form, callback) {
	var $form = $(form);
	$("#appContent").load($form.attr("action"), $form.serializeArray(),
			callback);
	return false;
}

// 提交表单
submitSubForm = function(form, id, callback) {
	var $form = $(form);
	$("#" + id).load($form.attr("action"), $form.serializeArray(), callback);
	return false;
}

// 确认删除
confirmDelete = function(key, callback) {
	bootbox.confirm("是否确认要删除?", function(result) {
		if (result) {
			callback(key);
		}
	});
}

confirmDelete2 = function(key1, key2, callback) {
	bootbox.confirm("是否确认要删除?", function(result) {
		if (result) {
			callback(key1, key2);
		}
	});
}

confirmDelete3 = function(key1, key2, key3, callback) {
	bootbox.confirm("是否确认要删除?", function(result) {
		if (result) {
			callback(key1, key2, key3);
		}
	});
}

// 操作确认
operationConfirm = function(msg) {
	bootbox.alert(eval(msg));
}

confirmClose = function(name, callback) {
	bootbox.confirm("是否确认要关闭?", function(result) {
		if (result) {
			callback(name);
		}
	});
}
$(function(){
	$(".toggle-btn").click(function(){
		if($.fn.dataTable)
		{
			$.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
		}
		 
	});

	
});
function  adjust(o){
		o.draw();
	};