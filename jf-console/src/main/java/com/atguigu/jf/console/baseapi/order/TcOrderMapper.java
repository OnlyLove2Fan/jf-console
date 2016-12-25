package com.atguigu.jf.console.baseapi.order;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.order.bean.pojo.TcOrder;

public interface TcOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(TcOrder record);

    int insertSelective(TcOrder record);

    TcOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(TcOrder record);

    int updateByPrimaryKey(TcOrder record);
    
    /**
     * 
     * @方法名: selectTcOrderList  
     * @功能描述: 根据各种条件查询TcOrder集合
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    List<TcOrder> selectTcOrderList(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: selectSum  
     * @功能描述: 查询消费总量 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    Long selectSum(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: selectPaySum  
     * @功能描述: 查询以结账总量
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    Long selectPaySum(Map<String, Object> map) throws Exception; 
    
    /**
     * 
     * @方法名: selectPayMoney  
     * @功能描述: 查询结账总结额
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    Long selectPayMoney(Map<String, Object> map) throws Exception; 
}