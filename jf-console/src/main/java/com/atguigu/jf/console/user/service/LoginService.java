package com.atguigu.jf.console.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.jf.console.user.bean.bo.SysFuncBean;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

public interface LoginService {

	/**
	 * 
	 * @方法名: login  
	 * @功能描述: 根据用户名和密码查询用户信息 
	 * @param sysOp
	 * @return
	 * @throws Exception
	 * @作者 syl
	 * @日期 2016年11月25日
	 */
	public SysOp login(SysOp sysOp) throws Exception;
	
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
    public List<SysFuncBean> selectSysFuncByOpId(Map<String,Object> map) throws Exception;

}
