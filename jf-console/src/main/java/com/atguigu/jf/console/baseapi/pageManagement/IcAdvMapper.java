package com.atguigu.jf.console.baseapi.pageManagement;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.pageManagement.bean.pojo.IcAdv;

public interface IcAdvMapper {
	
	/**
	 * 
	 * @方法名: deleteByPrimaryKey  
	 * @功能描述: TODO(这里用一句话描述这个方法的作用)  
	 * @param advId
	 * @return
	 * @作者 syl
	 * @日期 2016年12月1日
	 */
    int deleteByPrimaryKey(Long advId);

    int insert(IcAdv record);

    /**
     * 
     * @方法名: insertSelective  
     * @功能描述: 新增方法
     * @param record
     * @return
     * @作者 syl
     * @日期 2016年12月1日
     */
    int insertSelective(IcAdv record);

    IcAdv selectByPrimaryKey(Long advId);

    /**
     * 
     * @方法名: updateByPrimaryKeySelective  
     * @功能描述: 更新方法  
     * @param record
     * @return
     * @作者 syl
     * @日期 2016年12月1日
     */
    int updateByPrimaryKeySelective(IcAdv record);

    int updateByPrimaryKey(IcAdv record);
    
    /**
     * @方法名: selectIcAdvListByAreaOrName  
     * @功能描述: 通过区域Id或广告名称查询IcAdv  
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年11月30日
     */
    List<IcAdv> selectIcAdvListByAreaOrName(Map<String, Object> map) throws Exception; 
    
    /**
     * 
     * @方法名: updateNewOrder  
     * @功能描述: 更新扇面的或下面的order方法 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月1日
     */
    void updateNewOrder(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: updateOldOrder  
     * @功能描述: 更新当前记录的order  
     * @param map
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月1日
     */
    void updateOldOrder(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: selectMaxOrder  
     * @功能描述: 查询最大的Order
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月2日
     */
    Integer selectMaxOrder() throws Exception;
}