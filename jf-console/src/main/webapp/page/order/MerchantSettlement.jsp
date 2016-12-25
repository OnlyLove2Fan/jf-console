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
   <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>
<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
        	<label class="lbl-txt" style="margin-left: 20px">销售开始时间：</label>
               <span class="posR" >
                   <input value="${ScShop.advStartTime }" readonly="readonly" id="startDate1" name="advStartDate" type="text" class="input-text">
                   <i class="cal-ico" id="startDateBox1"></i>
               </span> 
               	<span style="margin-left: 60px">销售结束时间：</span>
               <span class="posR">
                   <input value="${ScShop.advEndTime }" readonly="readonly" id="endDate1" name="advStartDate" type="text" class="input-text">
                   <i class="cal-ico" id="endDateBox1"></i>
               </span><br><br>
               <label class="lbl-txt" style="margin-left: 20px">结算开始时间：</label>
                <span class="posR">
                    <input value="${ScShop.advStartTime }" readonly="readonly" id="startDate2" name="advStartDate" type="text" class="input-text">
                    <i class="cal-ico" id="startDateBox2"></i>
                </span> 
                <span style="margin-left: 60px">结算结束时间：</span>
                <span class="posR">
                    <input value="${ScShop.advEndTime }" readonly="readonly" id="endDate2" name="advStartDate" type="text" class="input-text">
                    <i class="cal-ico" id="endDateBox2"></i>
                </span><br><br>
                <label class="lbl-txt" style="margin-left: 10px">请选择分店：</label>
                <span class="query-item">
                    <div class="combo" id="mchtIdCombo"></div>
                </span>
                <input type="hidden" id="mchtId" name="mchtId" value="${ScShop.mchtId}">
                <span class="query-item" style="margin-left: 130px">
	              <input class="query-input" id="itemName" placeholder="请输商品名称或编码" value="">
	         	</span>
	         	<input type="hidden" id="itemName" name="itemName" value="${ScShop.itemName}">
	        	<button class="submit-btn" id="select" style="margin-left: 60px">搜索</button><br><br>
            <div class="form-aciton">
           	<!-- 通过save()保存数据 -->
            	<span style="margin-left: 60px">已消费总量：</span><span id="sum"></span>
            	<span style="margin-left: 60px">已结算总量：</span><span id="paySum"></span>
            	<span style="margin-left: 60px">结算金额：</span><span id="payMoney"></span>万
            	<button class="submit-btn"  id="excel" style="margin-left: 190px">导出EXCEL</button>
                <button class="btn-appro" style="margin-left: 60px">结算</button>
            </div>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    <!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
    <script src="<%=rootPath %>/page/order/js/MerchantSettlement.js"></script>
</body>

</html>