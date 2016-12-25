package com.atguigu.jf.console.pageManagement.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.pageManagement.bean.bo.AreaDef;

public interface AreaDefService {

	/**
     * 
     * @param map 
	 * @方法名: seleceAreaDef  
     * @功能描述: 查询所有的AreaDef
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年11月30日
     */
    public List<AreaDef> seleceAreaDef(Map<String, Object> map) throws Exception;
}
