package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface UserService {

	/**
	 * 
	 * @方法名: selectSysOpList  
	 * @功能描述: 查询用户列表  
	 * @param map
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月27日
	 */
	public List<SysOp> selectSysOpList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @方法名: selectSysOpListCount  
	 * @功能描述: 查询用户的总记录数 
	 * @param map
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月27日
	 */
	public Integer selectSysOpListCount(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @方法名: insertSelective  
	 * @功能描述:增加用户
	 * @param sysOp
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	Integer insertSelective(SysOp sysOp) throws Exception;
	
	/**
	 * 
	 * @方法名: updateByPrimaryKeySelective  
	 * @功能描述: 修改用户  
	 * @param sysOp
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	Integer updateByPrimaryKeySelective(SysOp sysOp) throws Exception;
	
	/**
	 * 	
	 * @方法名: selectByPrimaryKey  
	 * @功能描述: 根据用户Id查询用户 
	 * @param opId
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	SysOp selectByPrimaryKey(Long opId) throws Exception;
	
	/**
	 * 
	 * @方法名: selectSysOpListByPageHelper  
	 * @功能描述: 查询用户的列表数据 使用了分页插件 
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月28日
	 */
	List<SysOp> selectSysOpListByPageHelper(Map<String, Object> map) throws Exception;
}
