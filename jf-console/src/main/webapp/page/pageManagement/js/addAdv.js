// 注册保存事件
Ext.onReady(function(){
    initBtnFunc();
    // 初始化下拉框
    initCombo();
    initDateTime();
});
function initDateTime() {
	// 开始时间
	$("#startDateBox").live("click", function() {
		WdatePicker({
					el : "startDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#endDateBox").live("click", function() {
		WdatePicker({
					el : "endDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}

/*init combo */
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        // 指定下拉框属性
        fields: ['areaCode', 'areaName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	areaLevel : ''
            },
	        type: 'ajax',
	        url : rootPath + '/AreaDef/selectAreaDef',
	        reader: {
	        	 // 数据解析的格式json
	             type: 'json'
	        },
	    },
	    autoDestroy: true,
        
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
        	select :function(value){
        		// 给areaId赋值
        		$("#advAreaId").val(this.getValue());
        	},
		    render : function(value) {
				// store.load();
				this.setValue($("#advAreaId").val());
			}
        }
    });
}

//定义按钮的事件
function initBtnFunc() {
	/**
	 * 保存
	 */
	$(".submit-btn").click(function(){
		// 通过jquery的id选择器获取到type的值
//		var type = $("#type").val();
//		// 定义表单的action值
//		if(type == "add") {
//			$("#fbean").attr("action", rootPath + "/IcAdv/addIcAdv");
//		} else {
//			$("#fbean").attr("action", rootPath + "/IcAdv/modifyIcAdv");
//		}
//		// 提交表单
//		$("#fbean").submit();
//		
		/**
		 * ajax提交表单
		 */
		var type = $("#type").val();
		var url = '';
		if(type == "add") {
			url = rootPath + "/IcAdv/addIcAdv";
		} else {
			url =  rootPath + "/IcAdv/modifyIcAdv";
		}
		$.ajax({
			url : url,
			dataType : 'json',
			type : 'post',
			data : $("#fbean").serialize(),
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
	});
	
	$(".btn-appro").click(function(){
		alert("要发布喽");
		/**
		 * ajax发布
		 */
		var type = $("#type").val();
		var url = '';
		if(type == "add") {
			url = rootPath + "/IcAdv/showIcAdv";
		} else {
			url =  rootPath + "/IcAdv/showIcAdv";
		}
		$.ajax({
			url : url,
			dataType : 'json',
			type : 'post',
			data : $("#fbean").serialize(),
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
	});
	
	/**
	 * 上传图片
	 */
	$(".upload-btn").click(function(){
		var f = $("#uploadFile").val();
		var ext = '.jpg.jpeg.gif.bmp.png.';
		if (f == "") { // 先判断是否选择了文件
			alert("请选择文件!");
			return false;
		}
		f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
		if (ext.indexOf('.' + f + '.') == -1) {
			alert("图片格式不正确！");
			return false;
		}
		// ajax方式上传文件
		$.ajaxFileUpload({
			url : rootPath + '/IcAdv/uploadFile',
			type : 'post',
			dataType : 'json',
			// 文本域的id
			fileElementId : 'uploadFile',
			data :{
			},
			success:function(data){
				if(data.result == "success"){
					$("#advPic").val(data.msg);
				}
				alert(data.msg);
			},
			error:function(data){
			}
			
		});
	});
}

/**
 * 文件路径的显示
 */
function uploadImage() {
	var filePath = $("#uploadFile").val();
	$("#path").val(filePath);
}