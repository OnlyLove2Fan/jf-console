package com.atguigu.jf.console.order.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.order.bean.bo.TcOrderSum;
import com.atguigu.jf.console.order.bean.pojo.TcOrder;
import com.atguigu.jf.console.order.service.TcOrderService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/TcOrder")
public class TcOrderController {

	@Autowired
	private TcOrderService tcOrderService;
	
	@RequestMapping("/getSum")
	@ResponseBody
	public TcOrderSum getSum(TcOrder tcOrder,
			@RequestParam(value="startDate1",required=false) String startDate1,
			@RequestParam(value="endDate1",required=false) String endDate1,
			@RequestParam(value="startDate2",required=false) String startDate2,
			@RequestParam(value="endDate2",required=false) String endDate2) throws Exception {
		
		TcOrderSum tos = new TcOrderSum();
		
		Map<String, Object> map = new HashedMap<>();
		
		// 给map中添加各种条件
		String itemName = tcOrder.getItemName();
		Long itemCode = tcOrder.getItemCode();
		Long mchtId = tcOrder.getMchtId();
		map.put("mchtId", mchtId);
		map.put("itemName", itemName);
		map.put("itemCode", itemCode);
		map.put("startDate1", startDate1);
		map.put("endDate1", endDate1);
		map.put("startDate2", startDate2);
		map.put("endDate2", endDate2);
		//////////////////////////////////////////////////
		
		Long sum = tcOrderService.selectSum(map);
		Long paySum = tcOrderService.selectPaySum(map);
		Long payMoney = tcOrderService.selectPayMoney(map);
		
		tos.setSum(sum);
		tos.setPaySum(paySum);
		tos.setPayMoney(payMoney);
		
		return tos;
	}
	
	@RequestMapping("/getTcOrderList")
	@ResponseBody
	public String getTcOrderList(TcOrder tcOrder, Integer page, Integer limit,
			@RequestParam(value="startDate1",required=false) String startDate1,
			@RequestParam(value="endDate1",required=false) String endDate1,
			@RequestParam(value="startDate2",required=false) String startDate2,
			@RequestParam(value="endDate2",required=false) String endDate2) throws Exception {
		
		Map<String, Object> map = new HashedMap<>();
		
		// 给map中添加各种条件
		String itemName = tcOrder.getItemName();
		Long itemCode = tcOrder.getItemCode();
		Long mchtId = tcOrder.getMchtId();
		map.put("mchtId", mchtId);
		map.put("itemName", itemName);
		map.put("itemCode", itemCode);
		map.put("startDate1", startDate1);
		map.put("endDate1", endDate1);
		map.put("startDate2", startDate2);
		map.put("endDate2", endDate2);
		//////////////////////////////////////////////////
		
		
		/**
		 * 开始进行分页
		 * 第一个参数：页码
		 * 第二个参数：每页显示条数
		 */
		PageHelper.startPage(page, limit);
		List<TcOrder> list = tcOrderService.selectTcOrderList(map);
		
		// 设置日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String result = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
		
		return result;
	}
}
