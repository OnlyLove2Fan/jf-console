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

//定义zTree容器

var data, store, columns, queryGrid, pager, roleId, roleName, nodes;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();
});



/**
 * 初始化供应商列表
 */
function initGrid(){

    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'roleId', type: 'auto'},
           {name: 'roleName', type: 'auto'}
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //type: 'pagingmemory',
            url: rootPath + '/role/getMenuChk',
            actionMethods :{
            	read: "POST" 
            },
            reader: {
                type: 'json',
                //type: 'array'
            	root: 'list',
                totalProperty: 'total'
            }
        }
    });
    // width确定的宽度
    columns = [
            {
                text     : '角色名称',
                flex     : 1,
                sortable : false,
                dataIndex: 'roleName',
                renderer : qtips
            },
            {
                text     : '角色id',
                width    : 0,
                sortable : true,
                dataIndex: 'roleId'
            }
        ];
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
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        listeners : {
        	select: function (rowModel, record, index, eOpts) {
        		roleId = record.data.roleId, 
	    		Ztree(roleId);
	    	}
        },
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
* 查询按钮
*/
$(".btn-search").click(function(){
	roleId = "";
	roleName = $(".query-input").val();
	Ztree(roleId);
	roleName = "";
});

/*
 * 保存
 */
$(".submit-btn").click(function(){
	
	// 声明一个JSON数组，用来保存选中的funcId
	var funcIdArr = new Array();
	// 获取所有选中的数据
	var nodes = $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes(true);
	for (var i = 0; i < nodes.length; i++) {
		 funcIdArr[i] = nodes[i].funcId;
	}
	
	var data = {
		// 将数组转化为字符串返回
		funcIdArr : funcIdArr.join(),
		roleId : roleId
	}
	var type = 'json';
	var url = rootPath + '/role/modifyFuncList';
	var callback = function(result){
		alert(result.msg);
	};
	$.post(url,data,callback,type);
})
/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

function Ztree(roleId) {
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y": "p", "N": "s" }
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'funcId',
				pIdKey:'supFuncId'
			}
		}
	};
	
	var zNodes = '';
	$.ajax({
		url: rootPath + "/role/getMenuChkByRoleId",
		type:"POST",
		dataType:"json",
		async: false,
		data:{
			roleId : roleId,
			roleName : roleName,
		},
		success:function(value) {
			zNodes = value;
		},
		error:function(value) {
			alert("查询菜单发生错误！");
		}
	})
	if(treeObj != null){
		treeObj.destroy();
	}
	//初始化树，按配置进行渲染
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	//获取ztree对象
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	//获取到所有选择状态的节点
	nodes = treeObj.getCheckedNodes(true);
	//遍历所有节点，并展开，注意：只作用于父节点，因为子节点无法展开
	for (var i = 0; i < nodes.length; i++) {
		treeObj.expandNode(nodes[i], true, true, true);
	}
	roleId = "";
	roleName = "";
};

$(document).ready(function(){
	Ztree(roleId);
});