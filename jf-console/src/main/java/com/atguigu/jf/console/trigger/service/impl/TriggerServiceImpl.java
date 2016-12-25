package com.atguigu.jf.console.trigger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.jf.console.trigger.service.CreateExcelService;
import com.atguigu.jf.console.trigger.service.SendMailService;

public class TriggerServiceImpl {
	
	@Autowired
	private CreateExcelService createExcelService;
	
	private SendMailService sendMailService;

	public void doIt() throws Exception {
//		System.out.println("doIt方法被调用.." + new Date());
		// 创建Excel文件
		String fileName = createExcelService.createExcel();
		// 将创建好的Excel文件以邮件形式发送
		sendMailService.sendMail(fileName);
	}
}
