var topNtb;
var inputTblRs = [];
var form;
function refreshData() {
	topNtb.ajax.reload();
}
function getTopNInit() {
	
	
	topNtb = $('#topNtb')
			.DataTable(
					{
						ordering : true,
						searching : false,
						paging : false,
						info : false,
						bStateSave:true,
						scroller : {
							loadingIndicator : true
						},
						scrollCollapse : false,
						columnDefs : [ {
							"title" : "ID",
							"targets" : [ 0 ],
							"visible" : false,
							"searchable" : false,
							"orderable" : false
						}, {
							"title" : "序号",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "问题描述",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getTopNData=function(){
	inputTblRs = [];
//	 form = new FormData($("#site")[0]);
//	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "defect.sp?toListTopN&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			if(datas == null)
			{
				return;
			}
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						row[0] = item.key;
						
						row[1] = "<span  id='"
								+ item.key
								+ "-no'>"
								+ item.no
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-issueDes'>"
								+ item.issueDes
								+ "</span>";						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			topNtb.destroy();
			getTopNInit();
		
};
querySite=function(){
	getTopNData();
};
	


$(document).ready(function() {
	getTopNInit();
	getTopNData();
					
});
