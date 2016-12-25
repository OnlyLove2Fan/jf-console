package com.atguigu.jf.console.test;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class DBTest {

	@Autowired
	private DruidDataSource datasource;
	
	@Test
	public void testTime() {
		Date date = new Date();
		long time = date.getTime();
		System.out.println(time+"");
	}
	
	@Test
	public void testDB() {
		
		try {
			System.out.println(datasource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
