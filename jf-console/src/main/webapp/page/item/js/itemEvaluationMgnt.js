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
	$(".submit-btn").click(function(){
//		alert($("#evaluationStatus").val());
//		alert($("#userNickname").val());
		store.proxy.extraParams = {
				evaluationStatus : $("#evaluationStatus").val(),
				userNickname : $("#userNickname").val()
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
                 codeType : '1001'//自定义参数
	         } 
	     },
	     autoLoad: true
	     
	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'statusCombo',
        displayField: 'codeName',
        // 字段名对应的值
	    valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value) {
        		$("#evaluationStatus").val(this.getValue());
        	},
        }
    });
}

function initGrid(){
	
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'evaluationId', type: 'auto'},
           {name: 'userNickname', type: 'auto'},
           {name: 'itemName', type: 'auto'},
           {name: 'itemPicName', type: 'auto'},
           {name: 'evaluationGrade', type: 'auto'},
           {name: 'evaluationContent', type: 'auto'},
           {name: 'itemPicUrl', type: 'auto'},
           {name: 'shopShortName', type: 'auto'},
           {name: 'evaluationTime', type: 'auto'},
        ],  
        remoteSort: true,
        // 每页显示的条数
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //type: 'pagingmemory',
            //url : rootPath + '/user/selectSysOplist',
            url: rootPath + '/IvItem/getIcItemList',
            actionMethods :{
    	    	read: "POST"
    	    },
            reader: {
                type: 'json',
                //type: 'array'
                // 当使用PageHelper插件实现分页时，每一行是list
                root: 'rows',
                totalProperty: 'total'
            }
        }
    });
    // width确定的宽度
    columns = [
            {
                text     : '评论账号',
                sortable : false,
                width	 : 60,
                dataIndex: 'userNickname',
                renderer : qtips
            },
            {
                text     : '商品名称',
                width	 : 160,
                sortable : true,
                dataIndex: 'itemName',
            },
            {
                text     : '商品图片',
                width	 : 120,
                sortable : true,
                dataIndex: 'itemPicName',
            },
            {
                text     : '评论分值',
                width	 : 60,
                sortable : true,
                dataIndex: 'evaluationGrade',
            },
            {
                text     : '评论内容',
                width	 : 110,
                sortable : true,
                dataIndex: 'evaluationContent',
            },
            {
                text     : '评论图片',
                width	 : 130,
                sortable : true,
                dataIndex: 'itemPicUrl',
            },
            {
                text     : '评论店铺',
                width	 : 130,
                sortable : true,
                dataIndex: 'shopShortName',
            },
            {
                text     : '评论时间',
                width	 : 150,
                sortable : true,
                dataIndex: 'evaluationTime',
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
    var evaluationId = record.data.evaluationId;
    returnValue += '<em class="modify-ico" title="详情" onclick="modifyUser(' + evaluationId +')"></em>'+
                    '<em class="del-ico" title="屏蔽" onclick="deleteUser(' + evaluationId +')"></em>';
    
    return returnValue;
}

/*
 * 预览的方法
 */
function modifyUser(evaluationId) {
	window.location.href = rootPath + "/IvItem/getIcItemById?evaluationId=" + evaluationId;
}

/*
 * 删除用户的方法
 */
function deleteUser(evaluationId) {
	alert(evaluationId);
	/**
	 * 删除用户
	 */
	$.ajax({
		url : rootPath + "/IvItem/deleteIcItem",
		dataType : 'json',
		type : 'post',
		data : {
			evaluationId : evaluationId
		},
		success: function(value) {
			// 将后台传入前台的提示，打印出来
			alert(value.msg);
			// 加入跳转
			window.location.href = rootPath + '/page/item/itemEvaluationMgnt.jsp';
		},
		error : function(value) {
			alert("发生错误!")
		}
	});
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

