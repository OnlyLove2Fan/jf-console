package com.atguigu.jf.console.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.common.CodeValueMapper;
import com.atguigu.jf.console.common.bean.pojo.CodeValue;
import com.atguigu.jf.console.common.service.CodeValueService;

@Service
public class CodeValueServiceImpl implements CodeValueService {

	@Autowired
	private CodeValueMapper codeValueMapper;

	@Override
	public List<CodeValue> selectCodeValueByCodeType(Map<String, Object> map)
			throws Exception {
		return codeValueMapper.selectCodeValueByCodeType(map);
	}
}
