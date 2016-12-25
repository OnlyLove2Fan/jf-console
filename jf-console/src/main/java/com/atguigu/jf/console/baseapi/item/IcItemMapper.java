package com.atguigu.jf.console.baseapi.item;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcItem;

public interface IcItemMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(IcItem record);

    int insertSelective(IcItem record);

    IcItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(IcItem record);

    int updateByPrimaryKey(IcItem record);
    
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