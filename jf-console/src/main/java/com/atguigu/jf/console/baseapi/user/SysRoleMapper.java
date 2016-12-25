package com.atguigu.jf.console.baseapi.user;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysRole;

public interface SysRoleMapper {
	
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * 
     * @方法名: selectMenu  
     * @功能描述: 查询所用角色
     * @return
     * @作者 syl
     * @日期 2016年11月30日
     */
	List<SysRole> selectMenu() throws Exception;
	
	/**
	 * 
	 * @方法名: deleteFunc  
	 * @功能描述: 删除roleId对应的所有func
	 * @param map
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月30日
	 */
	Integer deleteFunc(Map<String, Object> map) throws Exception; 
}