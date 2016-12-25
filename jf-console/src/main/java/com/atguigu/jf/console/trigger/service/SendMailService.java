package com.atguigu.jf.console.trigger.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface SendMailService {

	/**
	 * @方法名: sendMail  
	 * @功能描述: 将fileName这个文件，以附件的形式发送邮件
	 * @param fileName
	 * @throws AddressException
	 * @throws MessagingException
	 * @作者 syl
	 * @日期 2016年12月5日
	 */
	public void sendMail(String fileName) throws AddressException, MessagingException;
}
