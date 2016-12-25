<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/commonHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>评论详情</title>
	<script type="text/javascript">
		$(function(){
			$(".submit-btn").click(function(){
				//模拟浏览器的后退操作
				window.history.back();
			});
		});
	</script>
</head>
<body>
	<br><br>
	<span style="margin-left: 200px">商家名称：</span>${icItemEvaluation.shopShortName }<br><br>
	<span style="margin-left: 200px">商品名称：</span>${icItemEvaluation.userNickname }<br><br>
	<span style="margin-left: 200px">评价分值：</span>${icItemEvaluation.evaluationGrade }<br><br>
	<span style="margin-left: 200px">评论时间：</span>${icItemEvaluation.evaluationTime }<br><br>
	<span style="margin-left: 200px">评论名称：</span>${icItemEvaluation.evaluationContent }<br><br>
	<span style="margin-left: 200px">晒单图片：</span>${icItemEvaluation.itemPicUrl }<br><br>
	<span style="margin-left: 200px"></span><img  src="${icItemEvaluation.itemPicUrl }">
	<br><br>
	<span class="form-aciton" style="margin-left: 200px">
        <button class="submit-btn">返回</button>
    </span>
</body>
</html>