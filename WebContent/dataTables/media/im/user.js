var usertb;
var inputTblRs = [];
var form;
function refreshData() {
	usertb.ajax.reload();
}
function getUserInit() {
	
	
	usertb = $('#usertb')
			.DataTable(
					{
						ordering : true,
						searching : false,
						bStateSave:true,
						"aLengthMenu": [[10, 100, 500,1000, -1], [10, 100, 500,1000, "所有"]],   
						"iDisplayLength":100,   
						search   : {
						    "caseInsensitive": false
						  },
						processing : true,
						info : true,
						paging : true,
						scrollY : 450,
						//scrollX: true,
						bScrollCollapse: true,
						order : [ [ 0, "asc" ] ],
						"bAutoWidth" : true,
						lengthChange : true,
						"language" : {
							"lengthMenu" : "每页显示 _MENU_ 记录",
							"zeroRecords" : "数据库中没有记录",
							"info" : "第 _PAGE_页（ 共_PAGES_页）共_MAX_条记录",
							"infoEmpty" : "没有记录",
							"infoFiltered" : "(从_MAX_条记录中查询)",
							"sSearch" : "查询",
							"paginate" : {
								"previous" : "前一页",
								"next" : "后一页"
							}
						},
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
							"title" : "用户名",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "说明",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "姓",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "名",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "备注",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "密码过期日期",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "能否修改密码",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "状态",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "用户过期时间",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "email",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 11 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getUserData=function(){
	inputTblRs = [];
	 form = new FormData($("#user")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "user.sp?toList&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						console.log(item.key);
						row[0] = item.key;
						row[1] = "<span  id='"
								+ item.key
								+ "-name'>"
								+ item.name
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-description'>"
								+ item.description
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-firstName'>"
								+ item.firstName
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-lastName'>"
								+ item.lastName
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-note'>"
								+ item.note
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-passwordExpiration'>"
								+ item.passwordExpiration
								+ "</span>";
						row[7] = "<span id='"
								+ item.key
								+ "-passwordModifiable'>"
								+ item.passwordModifiable
								+ "</span>";
						row[8] = "<span id='"
								+ item.key
								+ "-status'>"
								+ item.status
								+ "</span>";
						row[9] = "<span id='"
								+ item.key
								+ "-userExpiration'>"
								+ item.userExpiration
								+ "</span>";
						row[10] = "<span id='"
								+ item.key
								+ "-email'>"
								+ item.email
								+ "</span>";
 						row[11]= " <a id='"+item.key+"-edit' href='user.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' data-backdrop='static' class='btn btn-info btn-xs' " +
								" data-target='#modifyUser'><i class='fa fa-cog' title='修改'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' data-backdrop='static' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteUser)'><i class='fa fa-ban' title='删除'></i></a>";
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			usertb.destroy();
			getUserInit();
		
};

function getUserDataByName(username){
	inputTblRs = [];
	
	$.ajax({
		url : "user.sp?getUserByName&t="+Math.random()+"&name="+username,
		 type: 'POST',  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						console.log(item.key);
						row[0] = item.key;
						row[1] = "<span  id='"
								+ item.key
								+ "-name'>"
								+ item.name
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-description'>"
								+ item.description
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-firstName'>"
								+ item.firstName
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-lastName'>"
								+ item.lastName
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-note'>"
								+ item.note
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-passwordExpiration'>"
								+ item.passwordExpiration
								+ "</span>";
						row[7] = "<span id='"
								+ item.key
								+ "-passwordModifiable'>"
								+ item.passwordModifiable
								+ "</span>";
						row[8] = "<span id='"
								+ item.key
								+ "-status'>"
								+ item.status
								+ "</span>";
						row[9] = "<span id='"
								+ item.key
								+ "-userExpiration'>"
								+ item.userExpiration
								+ "</span>";
						row[10] = "<span id='"
								+ item.key
								+ "-email'>"
								+ item.email
								+ "</span>";
 						row[11]= " <a id='"+item.key+"-edit' href='user.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' data-backdrop='static' class='btn btn-info btn-xs' " +
								" data-target='#modifyUser'><i class='fa fa-cog' title='修改'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' data-backdrop='static' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteUser)'><i class='fa fa-ban' title='删除'></i></a>";
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			usertb.destroy();
			getUserInit();
		
};



queryUser=function(){
	getUserData();
};
	



