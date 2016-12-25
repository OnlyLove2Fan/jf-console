<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/commonHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content=""
	<title></title>
</head>
<body>
	<div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">审核状态</label>
                <div class="combo" id="statusCombo"></div>
                <input type="hidden" id="evaluationStatus" value="">
            </span>
            <span class="query-item" style="margin-left: 320px">
                <input class="query-input" id="userNickname" placeholder="评论账号">
            </span>
            <button class="submit-btn" id="saveUser">搜索</button>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    <script src="<%=rootPath %>/page/item/js/itemEvaluationMgnt.js"></script>
</body>
</html>