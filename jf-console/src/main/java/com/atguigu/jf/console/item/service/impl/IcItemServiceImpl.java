package com.atguigu.jf.console.item.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.IcItemMapper;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.item.service.IcItemService;

@Service
public class IcItemServiceImpl implements IcItemService {

	@Autowired
	private IcItemMapper icItemMapper;
	
	@Override
	public List<IcItem> selectIcItemByNameOrType(Map<String, Object> map)
			throws Exception {
		return icItemMapper.selectIcItemByNameOrType(map);
	}

}
