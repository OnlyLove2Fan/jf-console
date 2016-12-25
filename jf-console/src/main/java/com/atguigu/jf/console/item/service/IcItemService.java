package com.atguigu.jf.console.item.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcItem;

public interface IcItemService {

	/**
     * @方法名: selectIcItemByNameOrType  
     * @功能描述: 根据名称或类别查询IcItem 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月6日
     */
    List<IcItem> selectIcItemByNameOrType(Map<String, Object> map) throws Exception;
}
