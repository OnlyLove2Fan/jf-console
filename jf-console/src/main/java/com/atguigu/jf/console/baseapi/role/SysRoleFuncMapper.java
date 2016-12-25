package com.atguigu.jf.console.baseapi.role;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysRoleFunc;

public interface SysRoleFuncMapper {
    int deleteByPrimaryKey(Long roleFuncId);

    int insert(SysRoleFunc record);

    int insertSelective(SysRoleFunc record);

    SysRoleFunc selectByPrimaryKey(Long roleFuncId);

    int updateByPrimaryKeySelective(SysRoleFunc record);

    int updateByPrimaryKey(SysRoleFunc record);
    
    /**
     * 
     * @方法名: deleteRoleFuncByRoleId  
     * @功能描述: 根据roleId删除对应的关联关系  
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月1日
     */
    int deleteRoleFuncByRoleId(Map<String, Object> map) throws Exception;
    
    /**
     * 
     * @方法名: insertRoleFunc  
     * @功能描述: 插入新的关联关系 
     * @param list
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年12月1日
     */
    int insertRoleFunc(List<SysRoleFunc> list) throws Exception;
}