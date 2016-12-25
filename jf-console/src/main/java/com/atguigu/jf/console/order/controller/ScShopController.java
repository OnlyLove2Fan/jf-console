package com.atguigu.jf.console.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.order.bean.pojo.ScShop;
import com.atguigu.jf.console.order.service.ScShopService;

@Controller
@RequestMapping("/ScShop")
public class ScShopController {

	@Autowired
	private ScShopService scShopService;
	
	@ResponseBody
	@RequestMapping("/getScShopList")
	public List<ScShop> getScShopList() throws Exception {
		
		List<ScShop> list = scShopService.selectScShopList();
		return list;
	}
}
