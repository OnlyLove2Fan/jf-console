<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/commonHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>添加广告位</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>

<body>

    <div class="asideR-cont">
        <div class="add-cnt">
            <ul class="add-lst">
            	<form id="fbean" action="" method="post">
	            	<!-- 两个隐藏域接收type和opId -->
	            	<input type="hidden" id="type" name="type" value="${type }">
	            	<input type="hidden" id="advId" name="advId" value="${advId }">
	            	
	            	<li>
	                    <label class="lbl-txt">广告位位置：</label>
	                    <span class="form-input">
	                        <label class="mr30"><input type="radio" name="advPos" value="1"
								<c:if test="${icAdv.advPos == 1 }">
									checked = "checked"
								</c:if>
								/> 首页</label>
	                        <label><input type="radio" name="advPos" value="2"
								<c:if test="${icAdv.advPos == 2 }">
									checked = "checked"
								</c:if>
							/> 特价区</label>
	                    </span>
	                    <span class="require">*</span>
	                </li>
	                
	                <li>
	                    <label class="lbl-txt">区域：</label>
	                    <span class="query-item">
	                        <div class="combo" id="advAreaIdCombo"></div>
	                    </span>
	                    <span class="require">*</span>
	                    <input type="hidden" id="advAreaId" name="advAreaId" value="${areaDef.areaCode}">
	                </li>
	                
	                <li>
	                    <label class="lbl-txt">广告位名称：</label>
	                    <input type="text" class="input-text-lg" name="advName" value="${icAdv.advName }" />
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">广告位超链接地址：</label>
	                    <input type="text" class="input-text-lg" name="advUrl" value="${icAdv.advUrl }" />
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">广告位说明：</label>
	                    <textarea name="advDesc" class="textarea textarea-lg">${icAdv.advDesc }</textarea>
	                    <span class="require">*</span>
	                </li>
	            	<li>
                    	<label class="lbl-txt">播放开始时间：</label>
	                    <span class="posR">
	                        <input value="${icAdv.advStartTime }" readonly="readonly" id="startDate" name="advStartTime" type="text" class="input-text">
	                        <i class="cal-ico" id="startDateBox"></i>
	                        <span class="require">*</span>
	                    </span> &nbsp;&nbsp;播放结束时间
	                    <span class="posR">
	                        <input value="${icAdv.advEndTime }" readonly="readonly" id="endDate" name="advEndTime" type="text" class="input-text">
	                        <i class="cal-ico" id="endDateBox"></i>
	                        <span class="require">*</span>
	                    </span>
                	</li>
                	<!-- 文件上传时，需要给变次输入域的值 -->
                	<input type="hidden" name="advPic" id="advPic" class="input-text" value="${icAdv.advPic }" />
				</form>
	                <li>
	                    <label class="lbl-txt">广告照片：</label>
	                    <div class="upload-box">
	                    	<input type="text" class="input-text" name="path" id="path" value="${icAdv.advPic }"/>
	                        <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadImage()" />
	                        <span class="require">*</span>
	                        <button class="browse-btn">浏览</button>
	                    </div>    
	                    <button class="upload-btn">上传</button>
	                </li>
            </ul>
            <div class="form-aciton" style="margin-left: 200px">
            	<!-- 通过save()保存数据 -->
            	<button class="submit-btn" >保存</button>
                <button class="btn-appro" style="margin-left: 100px">发布</button>
                <button class="quit-btn" onclick="history.back();" style="margin-left: 100px">取消</button>
            </div>
        </div>
    </div>
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
    <script src="<%=rootPath %>/page/pageManagement/js/addAdv.js"></script>
</body>

</html>