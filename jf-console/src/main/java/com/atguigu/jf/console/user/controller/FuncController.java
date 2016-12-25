package com.atguigu.jf.console.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.bean.pojo.SysOp;
import com.atguigu.jf.console.user.service.FuncService;

@Controller
@RequestMapping("/Func")
public class FuncController {

	@Autowired
	private FuncService funcService;
	
	@RequestMapping("/getFuncList")
	@ResponseBody
	public String getFuncList(SysOp sysOp) throws Exception {
		
		// 获取opId
		Long opId = sysOp.getOpId();
		// 获取opName
		String opName = sysOp.getOpName();
		
		// 创建存放查询条件的Map
		Map<String, Object> map = new HashedMap<>();
		// 第一次查询所有Func列表
		List<SysFuncBeanTree> list2 = funcService.selectFuncList(map);
		
		// 将查询条件放入map中
		map.put("opId", opId);
		map.put("opName", opName);
		// 第二次进行有条件查询
		List<SysFuncBeanTree> list = funcService.selectFuncList(map);
		
		// 遍历第一次查出的所有条件
		for (SysFuncBeanTree sysFuncBeanTree : list2) {
			// 获取到funcId
			Long funcId = sysFuncBeanTree.getFuncId();
			// 遍历第二次有条件查出的条件
			for (SysFuncBeanTree sysFuncBeanTree2 : list) {
				Long funcId2 = sysFuncBeanTree2.getFuncId();
				// 将第二次查询的记录对应的对象设置checked属性为true
				if (funcId.equals(funcId2)) {
					sysFuncBeanTree.setChecked("true");
				}
			}
		}
		
		// 将list集合转换成JSON字符串
		String result = JSON.toJSONString(list2);
		return result;
	}
}
