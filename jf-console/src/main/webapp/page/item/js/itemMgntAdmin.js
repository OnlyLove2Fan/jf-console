Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', '../../res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    // 初始化列表
    initGrid();
    
    // 初始化下拉框
    initCombo1();
    initCombo2();
    initCombo3();
    
    // 初始化查询按钮
    initBtnFunc();
});

/* init btnFunc */
function initBtnFunc() {
	/*
	 * 查询按钮
	 */
	$(".submit-btn").click(function(){
		var itemNameOrId = $("#itemNameOrId").val();
		if(isNaN(itemNameOrId)) {
			var itemName = itemNameOrId;
		} else{
			var itemId = itemNameOrId;
		}
		store.proxy.extraParams = {
				itemType : $("#itemType").val(),
				mallCatId : $("#mallCatId").val(),
				itemApprState : $("#itemApprState").val(),
				itemName : itemName,
				itemId : itemId
		};
		store.load();
	});
	
	/*
	 * 重置按钮
	 */
	$(".btn-unappro").click(function(){
		var itemName = '';
		var itemId = '';
		$("#itemType").val('');
		$("#mallCatId").val('');
		$("#itemApprState").val('');
		store.proxy.extraParams = {
				itemType : $("#itemType").val(),
				mallCatId : $("#mallCatId").val(),
				itemApprState : $("#itemApprState").val(),
				itemName : itemName,
				itemId : itemId
		};
		store.load();
	});
}

/* init combo1 */
function initCombo1(){
	var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['codeValue', 'codeName'],
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/CodeValueController/getCodeValueList',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	         extraParams:{
                 codeType : '2003'// 自定义参数
	         } 
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'itemTypeCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value) {
        		$("#itemType").val(this.getValue());
        	},
        }
    });
}

/* init combo2 */
function initCombo2(){
	var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['codeValue', 'codeName'],
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/CodeValueController/getCodeValueList',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	         extraParams:{
                 codeType : '2007'// 自定义参数
	         } 
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'mallCatIdCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value) {
        		$("#mallCatId").val(this.getValue());
        	},
        }
    });
}

/* init combo3 */
function initCombo3(){
	var store = Ext.create('Ext.data.Store', {
		// 定义模型
	    fields: ['codeValue', 'codeName'],
	    // 动态加载
	    proxy: {
	    	 // 异步请求
	         type: 'ajax',
	         // 后台服务地址
	         url: rootPath + '/CodeValueController/getCodeValueList',
	         reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	         },
	         extraParams:{
                 codeType : '1001'// 自定义参数
	         } 
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'itemApprStateCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value) {
        		$("#itemApprState").val(this.getValue());
        	},
        }
    });
}

function initGrid(){
	
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'itemId', type: 'auto'},
           {name: 'itemName', type: 'auto'},
           {name: 'itemPicUrl', type: 'auto'},
           {name: 'itemType', type: 'auto'},
           {name: 'mallCatId', type: 'auto'},
           {name: 'itemMarketPrice', type: 'auto'},
           {name: 'itemSalePrice', type: 'auto'},
           {name: 'itemValidStart', type: 'auto'},
           {name: 'itemValidEnd', type: 'auto'},
           {name: 'itemApprState', type: 'auto'},
           {name: 'itemUdState', type: 'auto'},
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            // type: 'pagingmemory',
            // url : rootPath + '/user/selectSysOplist',
            url: rootPath + '/IcItem/getIcItemByNameOrType',
            actionMethods :{
    	    	read: "POST"
    	    },
            reader: {
                type: 'json',
                // type: 'array'
                // 当使用PageHelper插件实现分页时，每一行是list
                root: 'list',
                totalProperty: 'total'
            }
        }
    });
    // width确定的宽度
    columns = [
            {
                text     : '编码',
                width	 : 60,
                sortable : false,
                dataIndex: 'itemId',
                renderer : qtips
            },
            {
                text     : '名称',
                width	 : 150,
                sortable : true,
                dataIndex: 'itemName',
                renderer : qtips
            },
            {
                text     : '商品主图',
                width	 : 170,
                sortable : true,
                dataIndex: 'itemPicUrl',
                renderer : image
            },
            {
                text     : '类型',
                width	 : 60,
                sortable : true,
                dataIndex: 'itemType',
                renderer : itemType
            },
            {
                text     : '类别',
                width	 : 60,
                sortable : true,
                dataIndex: 'mallCatId',
                renderer : mallCatId
            },
            {
                text     : '原价',
                width	 : 40,
                sortable : true,
                dataIndex: 'itemMarketPrice',
                renderer : qtips
            },
            {
                text     : '售价',
                width	 : 40,
                sortable : true,
                dataIndex: 'itemSalePrice',
                renderer : qtips
            },
            {
                text     : '有效期起',
                width	 : 150,
                sortable : true,
                dataIndex: 'itemValidStart',
                renderer : qtips
            },
            {
            	text     : '有效期止',
            	width	 : 150,
            	sortable : true,
            	dataIndex: 'itemValidEnd',
            	renderer : qtips
            },
            {
            	text     : '销售状态',
            	width	 : 60,
            	sortable : true,
            	renderer : state
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 75,
                renderer: buttonRender,
                align   : 'center',
            }
        ];
    // 操作区域
    var dockedItems = [/*
						 * { xtype: 'toolbar', dock: 'bottom', ui: 'footer',
						 * layout: { pack: 'center' } },
						 */{
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新建',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "/user/addUserPage?type=add";
						}
					}
				}
            }]
        }];
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    // pager
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
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*
		 * resizable: { handles: 's', minHeight: 100 },
		 */
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
 * 类型
 */
function itemType(value, meta, record, rowIndex, colIndex, store){
	var itemType = record.data.itemType;
	if(itemType == 1) {
		return "抵用卷";
	}
}
/*
 * 类别
 */
function mallCatId(value, meta, record, rowIndex, colIndex, store){
	var mallCatId = record.data.mallCatId;
	if(mallCatId == 1) {
		return "美食";
	} else if(mallCatId == 2) {
		return "游玩";
	} else {
		return "电影";
	}
}

/*
 * 图片
 */
function image(value, meta, record, rowIndex, colIndex, store){
	var src = record.data.itemPicUrl;
	return "<img src='"+ rootPath + src+"'></img>"
}
/*
 * 销售状态
 */
function state(value, meta, record, rowIndex, colIndex, store){
	var itemUdState = record.data.itemUdState;
	var itemApprState = record.data.itemApprState;
	var state = itemUdState + "" + itemApprState;
	if(itemApprState == 2 ) {
		return "飞机";
	}
	return state;
}

/*
 * 操作按钮
 */
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
	var itemId = record.data.itemId;
	var itemApprState = record.data.itemApprState;
    if(itemApprState == 2) {
    	return "<a onclick='modifyUser(" + itemId +")'>查看</a>";
    }
    return "<a onclick='modifyUser(" + itemId +")'>删除</a>";
}

/*
 * 删除用户的方法
 */
function deleteUser(opId) {
	/**
	 * 删除用户
	 */
	$.ajax({
		url : rootPath + "/user/deleteUser?codeType='1003'&opId=" + opId,
		dataType : 'json',
		type : 'post',
		data : {
			opId : opId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/user/userMgnt.jsp';
		},
		error : function(value) {
			alert("发生错误!")
		}
	});
}

/*
 * 修改用户的方法
 */
function modifyUser(opId) {
	window.location.href = rootPath + "/user/addUserPage?type=modify&opId=" + opId;
}
/*
 * 提示文字
 */
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

