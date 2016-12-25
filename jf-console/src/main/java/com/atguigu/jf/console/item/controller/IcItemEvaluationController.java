package com.atguigu.jf.console.item.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.jf.console.common.bean.bo.ResultBean;
import com.atguigu.jf.console.item.bean.pojo.IcItemEvaluation;
import com.atguigu.jf.console.item.service.IcItemEvaluationService;

@Controller
@RequestMapping("/IvItem")
public class IcItemEvaluationController {

	@Autowired
	private IcItemEvaluationService icItemEvaluationService;
	
	@RequestMapping("/deleteIcItem")
	@ResponseBody
	public ResultBean deleteIcItem(IcItemEvaluation icItemEvaluation) {
		
		ResultBean rt = new ResultBean();
		
		icItemEvaluation.setDataState(new Short("0"));
		int i = icItemEvaluationService.updateByPrimaryKeySelective(icItemEvaluation);
		if(i >= 1) {
			rt.setResult(ResultBean.RESULT_SUCCESS);
			rt.setMsg("屏蔽成功");
		} else {
			rt.setResult(ResultBean.RESULT_FAILED);
			rt.setMsg("屏蔽失败");
		}
		return rt;
	}
	
	@RequestMapping("/getIcItemById")
	public String getIcItemById(Integer page, Integer limit, Long evaluationId, HttpSession session) throws Exception {
		
		Map<String, Object> map = new HashedMap<>();
		
		map.put("evaluationId", evaluationId);
		// 查询IcItem集合
		IcItemEvaluation icItemEvaluation = icItemEvaluationService.selectIcItemById(map);
		session.setAttribute("icItemEvaluation", icItemEvaluation);
		return "item/showItemEvaluation";
	}
	
	@RequestMapping("/getIcItemList")
	@ResponseBody
	public String getIcItemList(Integer page, Integer limit, IcItemEvaluation icItemEvaluation) throws Exception {
		
		Map<String, Object> map = new HashedMap<>();
		// 设置查询条件
		map.put("evaluationStatus", icItemEvaluation.getEvaluationStatus());
		map.put("userNickname", icItemEvaluation.getUserNickname());
		map.put("page", page);
		map.put("limit", limit);
		
		// 查询IcItem集合
		List<IcItemEvaluation> list = icItemEvaluationService.selectIcItemByStatus(map);
		
		// 设置日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String result = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
		return result;
	}
}
