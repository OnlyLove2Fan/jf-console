package com.atguigu.jf.console.item.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.item.bean.pojo.IcItemEvaluation;

public interface IcItemEvaluationService {

	/**
     * 
     * @方法名: selectIcItemByStatusOrUserName  
     * @功能描述: 根据审核状态和用户名，查询IcItem集合 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    List<IcItemEvaluation> selectIcItemByStatus(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: selectIcItemById  
     * @功能描述: 根据Id查询IcItem
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月3日
     */
    IcItemEvaluation selectIcItemById(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: updateByPrimaryKeySelective  
     * @功能描述: 更新方法 
     * @param record
     * @return
     * @作者 syl
     * @日期 2016年12月3日
     */
    int updateByPrimaryKeySelective(IcItemEvaluation record);
}
