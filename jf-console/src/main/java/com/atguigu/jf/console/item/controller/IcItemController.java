package com.atguigu.jf.console.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.item.service.IcItemService;

@Controller
@RequestMapping("/IcItem")
public class IcItemController {

	@Autowired
	private IcItemService icItemService;
	
	@RequestMapping("/getIcItemByNameOrType")
	@ResponseBody
	public String getIcItemByNameOrType(Integer start, Integer limit, IcItem icItem) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("itemType", icItem.getItemType());
		map.put("MallCatId", icItem.getMallCatId());
		map.put("itemApprState", icItem.getItemApprState());
		map.put("itemName", icItem.getItemName());
		map.put("itemId", icItem.getItemId());
		map.put("start", start);
		map.put("limit", limit);
		
		List<IcItem> list = icItemService.selectIcItemByNameOrType(map);
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String result = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
		return result;
	}
}
