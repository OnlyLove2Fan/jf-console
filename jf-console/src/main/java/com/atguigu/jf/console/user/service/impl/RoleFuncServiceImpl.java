package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.role.SysRoleFuncMapper;
import com.atguigu.jf.console.user.bean.pojo.SysRoleFunc;
import com.atguigu.jf.console.user.service.RoleFuncService;

@Service
public class RoleFuncServiceImpl implements RoleFuncService {

	@Autowired
	private SysRoleFuncMapper sysRoleFuncMapper;

	@Override
	public int deleteRoleFuncByRoleId(Map<String, Object> map) throws Exception {
		return sysRoleFuncMapper.deleteRoleFuncByRoleId(map);
	}

	@Override
	public int insertRoleFunc(List<SysRoleFunc> list) throws Exception {
		return sysRoleFuncMapper.insertRoleFunc(list);
	}
	
}
