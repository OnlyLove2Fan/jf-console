Ext.onReady(function(){
	//初始化操作
    initBtnFunc();
    
    // 初始化列表
    initGrid();
    // 初始化下拉框
    initCombo();
    // 初始化时间
    initDateTime();
});

/*
 * 初始化时间插件
 */
function initDateTime() {
	// 开始时间
	$("#startDateBox1").live("click", function() {
		WdatePicker({
					el : "startDate1",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#endDateBox1").live("click", function() {
		WdatePicker({
					el : "endDate1",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 开始时间
	$("#startDateBox2").live("click", function() {
		WdatePicker({
					el : "startDate2",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#endDateBox2").live("click", function() {
		WdatePicker({
					el : "endDate2",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}

// 初始化下拉框
function initCombo() {
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['mchtId', 'shopShortName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1002'
            },
	        type: 'ajax',
	        url : rootPath + '/ScShop/getScShopList'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'mchtIdCombo',
        editable: false, // 不允许输入 
        // 下拉框显示的值
        displayField: 'shopShortName',
        // 下拉框提交的值
        valueField: 'mchtId',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advState赋值
        		$("#mchtId").val(this.getValue());
        	}
        }
    });
}

// 初始化列表
function initGrid() {
	// create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'orderId', type: 'auto'},
           {name: 'orderCode', type: 'auto'},
           {name: 'orderTime', type: 'auto'},
           {name: 'mchtName', type: 'auto'},
           {name: 'itemName', type: 'auto'},
           {name: 'itemCode', type: 'auto'},
           {name: 'itemActPrice', type: 'auto'},
           {name: 'goodsDealFee', type: 'auto'},
           {name: 'payState', type: 'auto'},
           {name: 'payTime', type: 'auto'},
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //type: 'pagingmemory',
            //url : rootPath + '/user/selectSysOplist',
            url: rootPath + '/TcOrder/getTcOrderList',
            actionMethods :{
    	    	read: "POST"
    	    },
            reader: {
                type: 'json',
                //type: 'array'
                // 当使用PageHelper插件实现分页时，每一行是list
                root: 'list',
                totalProperty: 'total'
            }
        }
    });
    // width确定的宽度
    columns = [
            {
			    text     : '小积券号编号',
			    align 	 : 'center', 
			    width    : 120,
			    sortable : true,
			    dataIndex: 'orderCode',
			    renderer : qtips
			},
			{
			    text     : '消费时间',
			    align 	 : 'center', 
			    width    : 150,
			    sortable : true,
			    dataIndex: 'orderTime',
			    renderer : qtips
			},
			{
			    text     : '分店',
			    align 	 : 'center', 
			    width    : 120,
			    sortable : true,
			    dataIndex: 'mchtName',
			    renderer : qtips
			},
			{
			    text     : '商品名称',
			    align 	 : 'center', 
			    width    : 150,
			    sortable : true,
			    dataIndex: 'itemName',
			    renderer : qtips
			},
			{
			    text     : '商品代码',
			    align 	 : 'center', 
			    width    : 90,
			    sortable : true,
			    dataIndex: 'itemCode',
			    renderer : qtips
			},
			{
			    text     : '商品价格',
			    align 	 : 'center', 
			    width    : 80,
			    sortable : true,
			    dataIndex: 'itemActPrice',
			    renderer : qtips
			},
			{
			    text     : '结算金额',
			    align 	 : 'center', 
			    width    : 80,
			    sortable : true,
			    dataIndex: 'goodsDealFee',
			    renderer : qtips
			},
			{
			    text     : '结算状态',
			    align 	 : 'center', 
			    width    : 80,
			    sortable : true,
			    dataIndex: 'payState',
			    renderer : payState
			},
			{
			    text     : '结算时间',
			    align 	 : 'center', 
			    width    : 150,
			    sortable : true,
			    dataIndex: 'payTime',
			    renderer : payTime
			},
        ];
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        columns: columns,
        selModel: selModel,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        resizable: {
          handles: 's',
          minHeight: 100
        },
        bbar: pager,
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true,
            deferRowRender : false,
            forceFit : true,
            emptyText : "<font class='emptyText'>没有符合条件的记录</font>",
            autoScroll:true,
            scrollOffset:-10
        }
    });
    store.load();
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

/*
 * 结算状态
 */
function payState(value, cellmeta, record, rowIndex, colIndex, store){
    var payState = record.data.payState;
    if(payState == 1) {
    	return "未结算";
    } else if(payState == 2) {
    	return "已结算";
    }
}

/*
 *  结算时间
 */
function payTime(value, cellmeta, record, rowIndex, colIndex, store) {
	var payState = record.data.payState;
	var payTime = record.data.payTime;
    if(payState == 1) {
    	return "";
    } else if(payState == 2) {
    	return payTime;
    }
}
/*
 * 操作按钮
 */
function initBtnFunc() {
	$("#select").click(function() {
		
		var item = $("#itemName").val();
		if(isNaN(item)) {
			var itemName = item;
		} else {
			var itemCode = item;
		}
		store.proxy.extraParams = {
				mchtId : $("#mchtId").val(),
				itemName : itemName,
				itemCode : itemCode,
				startDate1 : $("#startDate1").val(),
				endDate1 : $("#endDate1").val(),
				startDate2 : $("#startDate2").val(),
				endDate2 : $("#endDate2").val()
		};
		store.load();
		initSum();
	});
	$("#excel").click(function(){
		alert("后续完成导出EXCEL......");
	});
	$(".btn-appro").click(function(){
		alert("后续完成结算......");
	});
}

$(document).ready(function(){
	initSum();
});

/*
 * 查询总量，总价格的方法
 */
function initSum() {
	/**
	 * 查询总量
	 */
	var item = $("#itemName").val();
	if(isNaN(item)) {
		var itemName = item;
	} else {
		var itemCode = item;
	}
	$.ajax({
		url : rootPath + '/TcOrder/getSum',
		dataType : 'json',
		type : 'post',
		data : {
			mchtId : $("#mchtId").val(),
			itemName : itemName,
			itemCode : itemCode,
			startDate1 : $("#startDate1").val(),
			endDate1 : $("#endDate1").val(),
			startDate2 : $("#startDate2").val(),
			endDate2 : $("#endDate2").val()
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			// 加入跳转
			// window.location.href = rootPath + '/page/order/MerchantSettlement.jsp';
			var sum = value.sum;
			var paySum = value.paySum;
			var payMoney = value.payMoney;
			if(sum != null) {
				$("#sum").text(value.sum);
			} else {
				$("#sum").text(0);
			};
			if(paySum != null) {
				$("#paySum").text(value.paySum);
			} else {
				$("#paySum").text(0);
			};
			if(payMoney != null) {
				$("#payMoney").text(value.payMoney);
			} else {
				$("#payMoney").text(0);
			};
		},
		error : function(value) {
			alert("发生错误!")
		}
	});
}