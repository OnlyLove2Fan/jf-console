package com.atguigu.jf.console.baseapi.trigger;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.trigger.bean.bo.ExchangeLogBean;

public interface PcExChangeLogMapper {

	/**
	 * @方法名: selectExChangLog  
	 * @功能描述: 查询积分流水 
	 * @param map
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年12月3日
	 */
	List<ExchangeLogBean> selectExChangLog(Map<String, Object> map) throws Exception;
}
