package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;

public interface FuncService {
	
	/**
     * 
     * @方法名: getFuncList  
     * @功能描述: 查询功能列表
     * @param map
     * @return
     * @作者 syl
     * @日期 2016年11月29日
     */
    public List<SysFuncBeanTree> selectFuncList(Map<String, Object> map) throws Exception;
    
}
