package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.bean.pojo.SysFunc;

public interface SysFuncMapper {
    int deleteByPrimaryKey(Long funcId);

    int insert(SysFunc record);

    int insertSelective(SysFunc record);

    SysFunc selectByPrimaryKey(Long funcId);

    int updateByPrimaryKeySelective(SysFunc record);

    int updateByPrimaryKey(SysFunc record);
    
    /**
     * 根据opId查询菜单
     * @方法名: selectSysFuncByOpId  
     * @功能描述: 根据opId查询菜单 
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年11月26日
     */
    List<SysFuncBean> selectSysFuncByOpId(Map<String,Object> map) throws Exception;
    
    /**
     * 
     * @方法名: getFuncList  
     * @功能描述: 查询功能列表
     * @param map
     * @return
     * @作者 syl
     * @日期 2016年11月29日
     */
    List<SysFuncBeanTree> selectFuncList(Map<String, Object> map) throws Exception;
    
}