package com.atguigu.jf.console.pageManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.pageManagement.AreaDefMapper;
import com.atguigu.jf.console.pageManagement.bean.bo.AreaDef;
import com.atguigu.jf.console.pageManagement.service.AreaDefService;

@Service
public class AreaDefServiceImpl implements AreaDefService {

	@Autowired
	private AreaDefMapper areaDefMapper;

	@Override
	public List<AreaDef> seleceAreaDef(Map<String, Object> map) throws Exception {
		return areaDefMapper.seleceAreaDef(map);
	}
}
