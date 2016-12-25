package com.atguigu.jf.console.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.order.TcOrderMapper;
import com.atguigu.jf.console.order.bean.pojo.TcOrder;
import com.atguigu.jf.console.order.service.TcOrderService;

@Service
public class TcOrderServiceImpl implements TcOrderService {

	@Autowired
	private TcOrderMapper tcOrderMapper;
	
	@Override
	public List<TcOrder> selectTcOrderList(Map<String, Object> map)
			throws Exception {
		return tcOrderMapper.selectTcOrderList(map);
	}

	@Override
	public Long selectSum(Map<String, Object> map) throws Exception {
		return tcOrderMapper.selectSum(map);
	}

	@Override
	public Long selectPaySum(Map<String, Object> map) throws Exception {
		return tcOrderMapper.selectPaySum(map);
	}

	@Override
	public Long selectPayMoney(Map<String, Object> map) throws Exception {
		return tcOrderMapper.selectPayMoney(map);
	}

}
