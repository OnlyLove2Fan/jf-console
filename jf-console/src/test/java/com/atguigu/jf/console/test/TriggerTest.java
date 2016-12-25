package com.atguigu.jf.console.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.jf.console.baseapi.item.IcItemMapper;
import com.atguigu.jf.console.item.bean.pojo.IcItem;
import com.atguigu.jf.console.trigger.service.CreateExcelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TriggerTest {

	@Autowired
	private CreateExcelService createExcelService;
	
	@Autowired
	private IcItemMapper icItemMapper;
	
	@Test
	public void testCreatExcel() throws Exception {
		String fileName = createExcelService.createExcel();
		System.out.println(fileName);
	}
	
	@Test
	public void testSelectIcItem() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("start", 0);
		map.put("limit", 10);
		List<IcItem> list = icItemMapper.selectIcItemByNameOrType(map);
		System.out.println(list);
		for (IcItem icItem : list) {
			System.out.println(icItem.getItemName());
		}
	}
}
