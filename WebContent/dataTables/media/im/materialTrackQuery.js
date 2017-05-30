var pageTable;
var inputTblRs = [];
var form;
var formID = "pageform";
var tableName = "querytable";
var requestURL = "materialTrack.sp";
//修改查询按钮事件
function refreshTable() {
  $('#'+tableName).ajax.reload();
}
function tableInit() {
  pageTable = $('#'+tableName).DataTable({
    ordering : true,
    searching : false,
    bStateSave : true,
    "aLengthMenu" : [[10, 100, 500, 1000, -1], [10, 100, 500, 1000, "所有"]],
    "iDisplayLength" : 10,
    search : {"caseInsensitive": false},
    processing : true,
    info : true,
    paging : true,
    scrollY : "100%",
    order : [[0, "asc"]],
    "bAutoWidth" : true,
    lengthChange : true,
    "language" : {
      "lengthMenu" : "每页显示 _MENU_记录",
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
    scroller : {loadingIndicator : true},
    scrollCollapse : false,
    columnDefs : [{
      "title" : "ID",
      "targets" : [ 0 ],
      "visible" : false,
      "searchable" : false,
      "orderable" : false
    }, {
      "title" : "VIN",
      "targets" : [ 1 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "工位",
      "targets" : [ 2 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "零件",
      "targets" : [ 3 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "描述",
      "targets" : [ 4 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "条码",
      "targets" : [ 5 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "扫描用户",
      "targets" : [ 6 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }, {
      "title" : "扫描时间",
      "targets" : [ 7 ],
      "visible" : true,
      "searchable" : true,
      "orderable" : true
    }],
    data:inputTblRs
  });
};
getTableData=function() {
  inputTblRs = [];
  form = new FormData($("#pageform")[0]);
  form.append("queryNum", $("#setNum").val());
  $.ajax({
    url : requestURL+"?toQuery&t="+Math.random(),
    type : 'POST',
    data : form,
    dataType : 'json',
    async : false,
    cache : false,
    processData : false,
    contentType : false,
    success : function(data) {
      var datas = eval(data);
      if(datas==null){
        return;
      }
      var k = 0;
      $.each(datas, function(i, item) {
        var row = [];
        row[0] = item.key;
        row[1] = "<span id='"
               + item.key
               + "-vin'>"
               + item.vin
               + " </span>";
        row[2] = "<span id='"
               + item.key
               + "-station'>"
               + item.station
               + " </span>";
        row[3] = "<span id='"
               + item.key
               + "-partNumber'>"
               + item.partNumber
               + "</span>";
        row[4] = "<span id='"
               + item.key
               + "-description'>"
               + item.description
               + "</span>";
        row[5] = "<span id='"
               + item.key
               + "-barcode'>"
               + item.barcode
               + "</span>";
        row[6] = "<span id='"
               + item.key
               + "-scanUser'>"
               + item.scanUser
               + "</span>";
        row[7] = "<span id='"
               + item.key
               + "-scanTimeStart'>"
               + item.scanTimeStart
               + "</span>";
        inputTblRs[k] = row;
        k = k + 1;
      });
    }
  });
  pageTable.destroy();
  tableInit();
};
queryList=function() {
  getTableData();
};
$(document).ready(function() {
  tableInit();
  getTableData();
});