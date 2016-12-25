package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.baseapi.user.SysRoleMapper;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.bean.pojo.SysRole;
import com.atguigu.jf.console.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysFuncMapper sysFuncMapper;

	@Override
	public List<SysRole> selectMenu() throws Exception {
		return sysRoleMapper.selectMenu();
	}

	@Override
	public List<SysFuncBeanTree> selectMenuChkByRoleId(Map<String, Object> map)
			throws Exception {
		return sysFuncMapper.selectFuncList(map);
	}

	@Override
	public Integer deleteFunc(Map<String, Object> map) throws Exception {
		return sysRoleMapper.deleteFunc(map);
	}
}
