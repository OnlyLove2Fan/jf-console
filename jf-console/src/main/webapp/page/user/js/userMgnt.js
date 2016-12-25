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
    initCombo();
    
    // 初始化查询按钮
    initBtnFunc();
});

/*init btnFunc*/
function initBtnFunc() {
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
				opName : $("#opName").val(),
				opKind : $("#opKind").val()
		};
		store.load();
	});
}

/*init combo */
function initCombo(){
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
                 codeType : '1003'//自定义参数
	         } 
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'opKindCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value) {
        		$("#opKind").val(this.getValue());
        	},
        }
    });
}

function initGrid(){
	
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'opId', type: 'auto'},
           {name: 'opName', type: 'auto'},
           {name: 'opCode', type: 'auto'},
           {name: 'opKindCN', type: 'auto'},
           {name: 'opPic', type: 'auto'},
           {name: 'mobileNo', type: 'auto'},
           {name: 'emailAdress', type: 'auto'},
           {name: 'loginCode', type: 'auto'},
           {name: 'lockFlag', type: 'auto'},
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //type: 'pagingmemory',
            //url : rootPath + '/user/selectSysOplist',
            url: rootPath + '/user/selectSysOpListByHelper',
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
                text     : '操作员名称',
                sortable : false,
                flex     : 1,
                dataIndex: 'opName',
                renderer : qtips
            },
            {
                text     : '编码',
                width	 : 110,
                sortable : true,
                dataIndex: 'opCode',
            },
            {
                text     : '操作员类型',
                width	 : 110,
                sortable : true,
                dataIndex: 'opKindCN',
            },
            {
                text     : '头像',
                width	 : 110,
                sortable : true,
                dataIndex: 'opPic',
            },
            {
                text     : '手机号',
                width	 : 110,
                sortable : true,
                dataIndex: 'mobileNo',
            },
            {
                text     : '电子邮箱地址',
                width	 : 110,
                sortable : true,
                dataIndex: 'emailAdress',
            },
            {
                text     : '登陆名',
                width	 : 110,
                sortable : true,
                dataIndex: 'loginCode',
            },
            {
                text     : '是否被锁定',
                width	 : 110,
                sortable : true,
                dataIndex: 'lockFlag',
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 75,
                renderer: buttonRender,
                align   : 'center'
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
* 操作按钮
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var opId = record.data.opId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyUser(' + opId +')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteUser(' + opId +')"></em>';
    
    return returnValue;
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

