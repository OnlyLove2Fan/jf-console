package com.atguigu.jf.console.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.order.ScShopMapper;
import com.atguigu.jf.console.order.bean.pojo.ScShop;
import com.atguigu.jf.console.order.service.ScShopService;

@Service
public class ScShopServiceImpl implements ScShopService {

	@Autowired
	private ScShopMapper scShopMapper;
	
	@Override
	public List<ScShop> selectScShopList() throws Exception {
		return scShopMapper.selectScShopList();
	}

}
