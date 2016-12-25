package com.atguigu.jf.console.common.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.common.bean.pojo.CodeValue;

public interface CodeValueService {
	/**
     * 
     * @方法名: selectCodeValueByCodeType  
     * @功能描述: 根据codeType查询CodeValue 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年11月26日
     */
    List<CodeValue> selectCodeValueByCodeType(Map<String, Object> map) throws Exception;
}
