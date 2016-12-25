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
    initCombo("advStateCombo");
    initCombo2("advPosCombo");
    initCombo3("areaNameCombo");
    
    // 初始化查询按钮
    initBtnFunc();
});

//初始化广告状态下拉框
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1002'
            },
	        type: 'ajax',
	        url : rootPath + '/CodeValueController/getCodeValueList'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advStateCombo',
        editable: false, // 不允许输入 
        // 下拉框显示的值
        displayField: 'codeName',
        // 下拉框提交的值
        valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advState赋值
        		$("#advState").val(this.getValue());
        	}
        }
    });
}

// 初始化广告位位置下拉框
function initCombo2(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '2001'
            },
	        type: 'ajax',
	        url : rootPath + '/CodeValueController/getCodeValueList'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advPosCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'codeName',
        // 下拉框提交的值
        valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给advPos赋值
        		$("#advPos").val(this.getValue());
        	}
        }
    });
}

//初始化区域下拉框 1级
function initCombo3(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	areaLevel : ''
            },
	        type: 'ajax',
	        url : rootPath + '/AreaDef/selectAreaDef'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'advAreaIdCombo',
        editable: false, // 不允许输入
        // 下拉框显示的值
        displayField: 'areaName',
        // 下拉框提交的值
        valueField: 'areaCode',
        editable: false,
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给areaId赋值
        		$("#advAreaId").val(this.getValue());
        	}
        }
    });
}

function initGrid(){
	
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'advId', type: 'auto'},
           {name: 'advName', type: 'auto'},
           {name: 'advPos', type: 'auto'},
           {name: 'advAreaId', type: 'auto'},
           {name: 'areaName', type: 'auto'},
           {name: 'advPic', type: 'auto'},
           {name: 'advDesc', type: 'auto'},
           {name: 'advUrl', type: 'auto'},
           {name: 'advStartTime', type: 'auto'},
           {name: 'advEndTime', type: 'auto'},
           {name: 'advOrder', type: 'auto'},
           {name: 'advState', type: 'auto'}
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //type: 'pagingmemory',
            //url : rootPath + '/user/selectSysOplist',
            url: rootPath + '/IcAdv/selectIcAdvListByAreaOrName',
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
			    text     : '名称',
			    align 	 : 'center', 
			    width    : 80,
			    sortable : true,
			    dataIndex: 'advName',
			    renderer : qtips
			},
			{
			    text     : '广告位',
			    align 	 : 'center', 
			    width    : 50,
			    sortable : true,
			    dataIndex: 'advPos',
			    renderer : thePos
			},
			{
			    text     : '区域',
			    align 	 : 'center', 
			    width    : 50,
			    sortable : true,
			    dataIndex: 'advAreaId',
			    renderer : theArea
			},
			{
			    text     : '广告位图片',
			    align 	 : 'center', 
			    width    : 120,
			    sortable : true,
			    dataIndex: 'advPic',
			    renderer : jpg
			},
			{
			    text     : '广告位说明',
			    align 	 : 'center', 
			    width    : 100,
			    sortable : true,
			    dataIndex: 'advDesc',
			    renderer : qtips
			},
			{
			    text     : '链接地址',
			    align 	 : 'center', 
			    width    : 90,
			    sortable : true,
			    dataIndex: 'advUrl',
			    renderer : qtips
			},
			{
			    text     : '播放开始时间',
			    align 	 : 'center', 
			    width    : 110,
			    sortable : true,
			    dataIndex: 'advStartTime',
			    renderer : qtips
			},
			{
			    text     : '播放结束时间',
			    align 	 : 'center', 
			    width    : 110,
			    sortable : true,
			    dataIndex: 'advEndTime',
			    renderer : qtips
			},
			{
			    text     : '广告位状态',
			    align 	 : 'center', 
			    width    : 70,
			    sortable : true,
			    dataIndex: 'advState',
			    renderer : advState
			},
			{
				text     : '调整顺序',
				align 	 : 'center', 
				width    : 70,
				sortable : true,
				renderer : move
			},
			{
			    text: '操作',
			    menuDisabled: true,
			    flex     : 1,
			    sortable: false,
			    width: 150,
			    align   : 'center',
			    renderer: buttonRender
			}
        ];
    // 操作区域
    var dockedItems = [/*{
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            layout: {
                pack: 'center'
            }
        }, */{
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
							window.location.href = rootPath + "/IcAdv/addIcAdvPage?type=add";
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
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*resizable: {
          handles: 's',
          minHeight: 100
        },*/
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
 * 设置查询方法
 */
function initBtnFunc() {
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
				advPosName : $("#advPosName").val(),
				advState : $("#advState").val(),
				advPos : $("#advPos").val(),
				advAreaId : $("#advAreaId").val(),
		};
		store.load();
	});
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

/*
 * 广告位渲染
 */
function thePos(value, cellmeta, record, rowIndex, colIndex, store){
	var thePos = record.data.advPos;
	if(thePos == 1) {
		return "首页";
	} else if (thePos == 2) {
		return "特价区";
	}
}

/*
 * 区域的渲染
 */
function theArea(value, cellmeta, record, rowIndex, colIndex, store) {
	var theArea = record.data.advAreaId;
	if(theArea == 1) {
		return "全国";
	}
}

/*
 * 图片的渲染
 */
function jpg(value, cellmeta, record, rowIndex, colIndex, store){
	var src = record.data.advPic;
	return "<image src='"+ rootPath + src+"'></image>"
//	return src;
}

/*
 * 广告位状态的渲染
 */
function advState(value, cellmeta, record, rowIndex, colIndex, store) {
	var advState = record.data.advState;
	if(advState == 1) {
		return "已启用";
	} else if (advState == 2) {
		return "已停用";
	}
}

/*
 * 移动的渲染
 */
function move(value, meta, record, rowIndex, colIndex, store) {
	var advOrder = record.data.advOrder;
	var advId = record.data.advId;
	return '<a onclick="upMove(' + advOrder +',' + advId +')">上移</a> '+
		'<a onclick="downMove(' + advOrder +',' + advId +')">下移</a> ';
}

/*
 * 上移操作的方法
 */
function upMove(advOrder, advId) {
	/**
	 * 上移操作
	 */
	$.ajax({
		url : rootPath + "/IcAdv/upMove?type=up",
		dataType : 'json',
		type : 'post',
		data : {
			advOrder : advOrder,
			advId : advId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
		},
		error : function(value) {
		}
	});
}
/*
 * 下移操作的方法
 */
function downMove(advOrder, advId) {
	/**
	 * 下移操作
	 */
	$.ajax({
		url : rootPath + "/IcAdv/upMove?type=down",
		dataType : 'json',
		type : 'post',
		data : {
			advOrder : advOrder,
			advId : advId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
		},
		error : function(value) {
			alert("发生错误!")
		}
	});
}

/*
 * 操作的渲染
 */
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var advState = record.data.advState;
    var advId = record.data.advId;
    if(advState == 1) {
		return '<a onclick="seeAdv(' + advId +')">查看</a> '+
				'<a onclick="shutDownAdv(' + advId +')">下架</a> '+
				'<a onclick="seeNow(' + advId +')">预览</a>';
	} else {
		return '<a onclick="modifyAdv(' + advId +')">修改</a> '+
				'<a onclick="seeAdv(' + advId +')">查看</a> '+
				'<a onclick="showAdv(' + advId +')">发布</a> '+
				'<a onclick="deleteAdv(' + advId +')">删除</a>';
	}
}

/*
 * 查看的方法
 */
function seeAdv(advId) {
	alert("后续完成，查看功能....");
}
/*
 * 下架的方法
 */
function shutDownAdv(advId) {
//	alert(advId);
	$.ajax({
		url : rootPath + "/IcAdv/showOrShutDownOrDeleteIcAdv",
		dataType : 'json',
		type : 'post',
		data : {
			type : 'shutDownAdv',
			advId : advId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
		},
		error : function(value) {
		}
	});
//	window.location.href = rootPath + "/IcAdv/showOrShutDownIcAdv?type=shutDownAdv&advId=" + advId;
}
/*
 * 预览的方法
 */
function seeNow(advId) {
	alert("后续完成，查看预览....");
}
/*
 * 修改的方法
 */
function modifyAdv(advId) {
//	alert(advId);
	window.location.href = rootPath + "/IcAdv/addIcAdvPage?type=modify&advId=" + advId;
}
/*
 * 发布的方法
 */
function showAdv(advId) {
//	alert(advId);
	$.ajax({
		url : rootPath + "/IcAdv/showOrShutDownOrDeleteIcAdv",
		dataType : 'json',
		type : 'post',
		data : {
			type : 'showIcAdv',
			advId : advId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
		},
		error : function(value) {
		}
	});
}
/*
 * 删除的方法
 */
function deleteAdv(advId) {
//	alert(advId);
	$.ajax({
		url : rootPath + "/IcAdv/showOrShutDownOrDeleteIcAdv",
		dataType : 'json',
		type : 'post',
		data : {
			type : 'deleteIcAdv',
			advId : advId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
		},
		error : function(value) {
		}
	});
}