package com.atguigu.jf.console.item.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.item.IcItemEvaluationMapper;
import com.atguigu.jf.console.item.bean.pojo.IcItemEvaluation;
import com.atguigu.jf.console.item.service.IcItemEvaluationService;

@Service
public class IcItemEvaluationServiceImpl implements IcItemEvaluationService{

	@Autowired
	private IcItemEvaluationMapper icItemEvaluationMapper;
	
	@Override
	public List<IcItemEvaluation> selectIcItemByStatus(
			Map<String, Object> map) throws Exception {
		return icItemEvaluationMapper.selectIcItemByStatus(map);
	}

	@Override
	public IcItemEvaluation selectIcItemById(Map<String, Object> map)
			throws Exception {
		return icItemEvaluationMapper.selectIcItemById(map);
	}

	@Override
	public int updateByPrimaryKeySelective(IcItemEvaluation record) {
		return icItemEvaluationMapper.updateByPrimaryKeySelective(record);
	}

}
