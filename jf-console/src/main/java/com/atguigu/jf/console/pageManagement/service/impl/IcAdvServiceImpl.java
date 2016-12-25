package com.atguigu.jf.console.pageManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.pageManagement.IcAdvMapper;
import com.atguigu.jf.console.pageManagement.bean.pojo.IcAdv;
import com.atguigu.jf.console.pageManagement.service.IcAdvService;

@Service
public class IcAdvServiceImpl implements IcAdvService {
	
	@Autowired
	private IcAdvMapper icAdvMapper;

	@Override
	public List<IcAdv> selectIcAdvListByAreaOrName(Map<String, Object> map)
			throws Exception {
		return icAdvMapper.selectIcAdvListByAreaOrName(map);
	}

	@Override
	public void updateNewOrder(Map<String, Object> map) throws Exception {
		icAdvMapper.updateNewOrder(map);
	}

	@Override
	public void updateOldOrder(Map<String, Object> map) throws Exception {
		icAdvMapper.updateOldOrder(map);
	}

	@Override
	public int insertSelective(IcAdv record) {
		return icAdvMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(IcAdv record) {
		return icAdvMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Integer selectMaxOrder() throws Exception {
		return icAdvMapper.selectMaxOrder();
	}


}
