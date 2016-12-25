package com.atguigu.jf.console.test;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.jf.console.baseapi.user.SysFuncMapper;
import com.atguigu.jf.console.baseapi.user.SysRoleMapper;

public class MapperTest {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring.xml");
	SysRoleMapper sysRoleMapper = ioc.getBean(SysRoleMapper.class);
	SysFuncMapper sysFuncMapper = ioc.getBean(SysFuncMapper.class);
	@Test
	public void test () throws Exception {
		Map<String, Object> map = new HashedMap<>();
		map.put("roleId", 1);
//		List<SysFuncBean> list = sysFuncMapper.selectSysFuncByOpId(map);
	}
	
}
