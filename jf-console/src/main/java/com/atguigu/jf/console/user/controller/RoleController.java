package com.atguigu.jf.console.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.bean.pojo.SysRole;
import com.atguigu.jf.console.user.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.user.service.RoleFuncService;
import com.atguigu.jf.console.user.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleFuncService roleFuncService;
	
	@RequestMapping("/modifyFuncList")
	@ResponseBody
	public ResultBean modifyFuncList(SysRole sysRole, String funcIdArr) throws Exception {
		
		ResultBean resultBean = new ResultBean();
		
		// 修改功能烈表
		// 1、需要先清除原有的关系
		// 获得roleId，进行删除所有关联关系的操作
		Long roleId = sysRole.getRoleId();
		Map<String, Object> map = new HashedMap<>();
		// 将roleId放入map中
		map.put("roleId", roleId);
		
		// 将roleId对应的多有关系删除
		roleFuncService.deleteRoleFuncByRoleId(map);
		
		resultBean.setMsg("清除成功");
		
		List<SysRoleFunc> list = new ArrayList<>();
		
		// 2、将新的关联关系保存到数据库中
		// 解析字符串，将每一个funId放入到list集合中
		if(funcIdArr != null && funcIdArr != "") {
			// 将字符串按,进行分割
			String[] split = funcIdArr.split(",");
			for (int i = 0; i < split.length; i++) {
				// 遍历封装到roleFunc对象中
				SysRoleFunc roleFunc = new SysRoleFunc();
				roleFunc.setRoleId(roleId);
				roleFunc.setFuncId(Long.parseLong(split[i]));
				roleFunc.setDataState(new Short("1"));
				
				// 添加list集合中
				list.add(roleFunc);
			}
		}
		
		// 进行批量插入操作
		int i = roleFuncService.insertRoleFunc(list);
		if (i >= 1) {
			// 插入成功
			resultBean.setResult(ResultBean.RESULT_SUCCESS);
			resultBean.setMsg("操作成功");
		} else {
			// 插入失败
			resultBean.setResult(ResultBean.RESULT_FAILED);
			resultBean.setMsg("操作失败");
		}
		
		return resultBean;
	}
	
	@RequestMapping("/getMenuChk")
	@ResponseBody
	public List<SysRole> getMenuChk() throws Exception {
		
		// 获得所有的role对象
		List<SysRole> roleList = roleService.selectMenu();
		return roleList;
	}
	
	@ResponseBody
	@RequestMapping("/getMenuChkByRoleId")
	public String getMenuChkByRoleId(SysRole sysRole) throws Exception {
		
		Long roleId = sysRole.getRoleId();
		String roleName = sysRole.getRoleName();
		
		Map<String, Object> map = new HashedMap<>();
		// 第一次进行无条件查询
		List<SysFuncBeanTree> list2 = roleService.selectMenuChkByRoleId(map);
		
		map.put("roleId", roleId);
		map.put("roleName", roleName);
		// 第二次进行有条件查询
		List<SysFuncBeanTree> list = roleService.selectMenuChkByRoleId(map);
		
		// 遍历将第二次查询对应的对象的checked属性设置为true
		for (SysFuncBeanTree sysFuncBeanTree : list2) {
			Long funcId = sysFuncBeanTree.getFuncId();
			for (SysFuncBeanTree sysFuncBeanTree2 : list) {
				Long funcId2 = sysFuncBeanTree2.getFuncId();
				if (funcId.equals(funcId2)) {
					sysFuncBeanTree.setChecked("true");
				}
			}
		}
		
		// 将list集合装换成JSON字符串
		String result = JSON.toJSONString(list2);
		return result;
	}
}
