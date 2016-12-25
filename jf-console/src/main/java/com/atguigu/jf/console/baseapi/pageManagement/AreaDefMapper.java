package com.atguigu.jf.console.baseapi.pageManagement;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.pageManagement.bean.bo.AreaDef;

public interface AreaDefMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(AreaDef record);

    int insertSelective(AreaDef record);

    AreaDef selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(AreaDef record);

    int updateByPrimaryKey(AreaDef record);
    
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
    List<AreaDef> seleceAreaDef(Map<String, Object> map) throws Exception;
}