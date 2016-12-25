package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBeanTree;
import com.atguigu.jf.console.user.bean.pojo.SysRole;

public interface RoleService {

	/**
     * 
     * @方法名: getMenuChkByRoleId  
     * @功能描述: 根据RoleId查询func列表
     * @param map
     * @return
     * @throws Exception
     * @作者 syl
     * @日期 2016年11月30日
     */
    List<SysFuncBeanTree> selectMenuChkByRoleId(Map<String, Object> map) throws Exception;

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
