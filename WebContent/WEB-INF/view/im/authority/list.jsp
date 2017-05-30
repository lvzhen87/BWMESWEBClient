<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> 
 <%@ include file="/common/include.inc.jsp"%>
 <%@ include file="/common/includeJS.inc.jsp"%>
<!-- <link rel="stylesheet" href="js/zTree/css/demo.css" type="text/css"> -->
<link rel="stylesheet" href="js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<script type="text/javascript" src="js/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="js/zTree/js/jquery.ztree.exedit.js"></script>
	<SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true
			},

			data: {
                key: {
                    //将treeNode的ItemName属性当做节点名称
                    name: "name"        
                },
                simpleData: {
                    //是否使用简单数据模式
                    enable: true,
                    //当前节点id属性  
                    idKey: "id",
                    //当前节点的父节点id属性 
                    pIdKey: "pId"
                  
                }
            }
			         
		};		
		function focusKey(e) {
			if (key.hasClass("empty")) {
				key.removeClass("empty");
			}
		}
		function blurKey(e) {
			if (key.get(0).value === "") {
				key.addClass("empty");
			}
		}
		var lastValue = "", nodeList = [], fontCss = {};
		function clickRadio(e) {
			lastValue = "";
			searchNode(e);
		}
		function searchNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (!$("#getNodesByFilter").attr("checked")) {
				var value = $.trim(key.get(0).value);
				var keyType = "";
				if ($("#name").attr("checked")) {
					keyType = "name";
				} else if ($("#level").attr("checked")) {
					keyType = "level";
					value = parseInt(value);
				} else if ($("#id").attr("checked")) {
					keyType = "id";
					value = parseInt(value);
				}
				if (key.hasClass("empty")) {
					value = "";
				}
				if (lastValue === value) return;
				lastValue = value;
				if (value === "") return;
				updateNodes(false);

				if ($("#getNodeByParam").attr("checked")) {
					var node = zTree.getNodeByParam(keyType, value);
					if (node === null) {
						nodeList = [];
					} else {
						nodeList = [node];
					}
				} else if ($("#getNodesByParam").attr("checked")) {
					nodeList = zTree.getNodesByParam(keyType, value);
				} else if ($("#getNodesByParamFuzzy").attr("checked")) {
					nodeList = zTree.getNodesByParamFuzzy(keyType, value);
				}
			} else {
				updateNodes(false);
				nodeList = zTree.getNodesByFilter(filter);
			}
			updateNodes(true);

		}
		function updateNodes(highlight) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				zTree.updateNode(nodeList[i]);
			}
		}
		function getFontCss(treeId, treeNode) {
			return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		}
		function filter(node) {
			return !node.isParent && node.isFirstNode;
		}

		var key;
		
		
	</SCRIPT>
</HEAD>
<style type="text/css">
<!--
.div1{ border:5px solid #3e02ff; float:left; height:100px; width:140px;}
.div2{ border:5px solid #3e02ff; border-left:0 none; float:left; height:100px; width:440px;}
-->
</style>
<body> 
<section class="panel">
  <div class="panel-body">
      <div class="row">
      <div class="control">
      <div class="col-md-4">
           <div style="float:left;" id="userGrouptb">
  			 <h4 style="font-weight:400">用户组111</h4>
			 <select name="selectleft" size="10" id="selectleft" onclick="showAuthority(this)" style="border: 1px solid #eee;width: 300px;height: 700px;"/> 
		</div>
			<div   style=" width: 130px; padding-left: 0px; "> 
		<button class="btn btn-success" type="button" onclick="onSubmit()" style=" padding-left: 25px; padding-right: 25px;margin-top:10px ">确认</button>
	</div>
      </div>
            <div class="col-md-8">
     <div style="float:left;">
 				 <h4 style="font-weight:400">菜单列表</h4>
		<ul id="treeDemo" class="ztree" style="width: 750px;border: 1px solid #eee;height: 700px;overflow:auto;font-size:600px;"></ul>
	</div>      
      </div>
    
      </div>
      


      </div>
  
  
  </div>
</section>


	<input type="hidden" id="userGroupkey" >
 </body>
 
 <script type="text/javascript">
 var userGrouptb;
 var inputTblRs = [];
 function Node(id, pId, name, isParent,checked){  
	    this.id = id;    
	    this.pId = pId;    
	    this.name = name;  
	    this.isParent = isParent;
	     
	    if(checked=="1")
	    	this.checked=true;
	}; 
	 $(function() {
//	$(document).ready(function() {
	
	//	getUserGroupInit();
		getUserGroupData();
		getAuthorityData();
						
	});


	function getUserGroupInit() {
		userGrouptb = $('#userGrouptb')
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
								"title" : "用户组编号",
								"targets" : [ 1 ],
								"visible" : true,
								"searchable" : true,
								"orderable" : true
							}, {
								"title" : "用户组名称",
								"targets" : [ 2 ],
								"visible" : true,
								"searchable" : true,
								"orderable" : true
							}, {
								"title" : "用户组分类",
								"targets" : [ 3 ],
								"visible" : true,
								"searchable" : true,
								"orderable" : true
							}, {
								"title" : "操作",
								"targets" : [ 4 ],
								"visible" : true,
								"searchable" : true,
								"orderable" : true
							}],
							data:inputTblRs});
		
	};
	
	function getUserGroupData(){
		
		inputTblRs = [];
		
 		$.ajax({
			url : "usergroup.sp?toList&t="+Math.random(),
			 type: 'POST',  
	         dataType:'json',
	         async: false,  
	         cache: false,  
	         processData: false,  
	         contentType: false,  
			success : function(data) {
				var datas = eval(data);
				//alert(datas);
				var k = 0;
 				$.each(
						datas,
						function(i, item) {
 							var nowIndex = i;
							var text  = item.description;
							var value  = item.key;
							var newoption = new Option(text,value,false,false); 
							document.getElementById("selectleft").options[nowIndex] = newoption;
							if(i==0){
								document.getElementById("selectleft").options[nowIndex].selected=true;
								$("#userGroupkey").val(item.key); 
							}
						});
			}
		});		 
	};
						
						
	 function getAuthorityData(){		  
		 	var zNodes = new Array();
		 	var userGroupkey = $("#userGroupkey").val();
 		 	$.ajax({
				 url : "authority.sp?toList&t="+Math.random(),
				 type: "post",
				 data : {"userGroupkey":userGroupkey},				 
		         dataType:"json",
		         async: true,		        		        
				 success : function(data) {		 
					var datas = eval(data);
					if(datas == null)
					{
						return;
					}									
					var k = 0;
					$.each(datas,function(i, item) {						 
		                if(item.parent==0){  
                            isParent = true;  
                        }else{  	                        	 
 							isParent = false;
                        };  	                       
                        zNodes.push(new Node(item.key, item.pid, item.menuName, false,item.v_check));                
                        $.fn.zTree.init($("#treeDemo"), setting, zNodes);	                         
					});
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
			 		treeObj.expandAll(true); 
				}		 		
			});
			
	};

	function onSubmit(){
	    var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
	    nodes=treeObj.getCheckedNodes(true);
	    var menu="";
	    for(var i=0;i<nodes.length;i++){
		    menu+=nodes[i].id + ",";		  
	    }	   
	    if(menu=="")
		{
			bootbox.alert("请选择菜单!");
			return false;
		}	
 		var userGroupkey = $("#userGroupkey").val();		 
 		$.ajax({
	 		  url : "authority.sp?save&t="+Math.random(),
			 type: "post",
			 data : {"userGroupkey":userGroupkey,"menu":menu},				 
	         dataType:"json",
	         async: true,		        		        
			 success : function(data) {
				 getAuthorityData();
			 }		 		
		});
 		bootbox.alert("保存成功!"); 
	}
	
	function showAuthority(value){
		var selectedOption=value.options[value.selectedIndex]; 		
		$("#userGroupkey").val(selectedOption.value);
		getAuthorityData();
	}
 </script>