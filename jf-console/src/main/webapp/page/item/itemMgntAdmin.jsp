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
    <meta name="keyword" content="">
    <title>列表</title>
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">商品类型</label>
                <div class="combo" id="itemTypeCombo"></div>
                <input type="hidden" id="itemType" value="">
            </span>
            <span class="query-item mr20">
                <label class="query-txt">商品类别</label>
                <div class="combo" id="mallCatIdCombo"></div>
                <input type="hidden" id="mallCatId" value="">
            </span>
            <span class="query-item mr20" style="margin-left: 88px">
                <label class="query-txt">商品状态</label>
                <div class="combo" id="itemApprStateCombo"></div>
                <input type="hidden" id="itemApprState" value="">
            </span><br><br>
            	商品：
            <span class="query-item">
                <input class="query-input" id="itemNameOrId" placeholder="商品名称或编码">
            </span>
            <span class="form-aciton" style="margin-left: 370px">
                <button class="submit-btn">查询</button>
            	<button class="btn-unappro">重置</button>
            </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    <script src="<%=rootPath %>/page/item/js/itemMgntAdmin.js"></script>
</body>

</html>