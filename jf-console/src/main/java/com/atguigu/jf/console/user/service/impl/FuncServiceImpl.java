package com.atguigu.jf.console.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.service.FuncService;

@Service
public class FuncServiceImpl implements FuncService {
	
	@Autowired
	private SysFuncMapper sysFuncMapper;

	@Override
	public List<SysFuncBeanTree> selectFuncList(Map<String, Object> map) throws Exception {
		return sysFuncMapper.selectFuncList(map);
	}

}
