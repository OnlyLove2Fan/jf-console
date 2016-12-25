package com.atguigu.jf.console.pageManagement.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.jf.console.pageManagement.bean.bo.AreaDef;
import com.atguigu.jf.console.pageManagement.service.AreaDefService;

@Controller
@RequestMapping("/AreaDef")
public class AreaDefController {

	@Autowired
	private AreaDefService areaDefService;
	
	@RequestMapping("/selectAreaDef")
	@ResponseBody
	public List<AreaDef> selectAreaDef(AreaDef areaDef) throws Exception {
		
		// 设置查询条件，并进行查询对应的记录
		Map<String, Object> map = new HashedMap<>();
		map.put("areaLevel", areaDef.getAreaLevel());
		
		return areaDefService.seleceAreaDef(map);
	}
}
