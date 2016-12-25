package com.atguigu.jf.console.trigger.service.impl;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.stereotype.Service;

import com.atguigu.jf.console.trigger.service.SendMailService;

@Service
public class SendMailServiceImpl implements SendMailService {

	@Override
	public void sendMail(String fileName) throws AddressException, MessagingException {
		
		/*
		 * 1、通过session创建邮件的配置信息
			2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
			3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		Properties props = new Properties();
		// 配置项参考文档
		// 邮箱服务器的地址
		props.setProperty("mail.host", "smtp.sina.com");
		props.setProperty("mail.transport.protocol", "smtp");
		// 开启认证
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		// 调试完毕，注释掉
		session.setDebug(true);
		
		/*
		 * 2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
		 */
		MimeMessage message = new MimeMessage(session);
		// 发件人
		message.setFrom(new InternetAddress("onlylove_ff@sina.com"));
		// 设置主题
		message.setSubject("您好,可以上车么？");
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress("1403915589@qq.com"));
		
		// 带复件的邮件体
		MimeMultipart mulpart = new MimeMultipart();
		// 设置正文
		MimeBodyPart bodyPart = new MimeBodyPart();
		// 第一部分、邮件的正文
		bodyPart.setContent("<span style='color:red;'>2016年12月5日 09:30:53</span>，这是本次的报表", "text/html;charset=UTF-8");
		mulpart.addBodyPart(bodyPart);
		
		// 第二部分、附件
		bodyPart = new MimeBodyPart();
		// 引入文件的数据源
		bodyPart.setDataHandler(new DataHandler(new FileDataSource(new File(fileName))));
		// 定义文件的名称
		bodyPart.setFileName("import_Log_test.xls");
		mulpart.addBodyPart(bodyPart);
		
		// 放入MimeMultipart对象
		message.setContent(mulpart);
		
		/*
		 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		Transport transport = session.getTransport();
		// 建立连接
		transport.connect("smtp.sina.com", "onlylove_ff@sina.com", "sg201314");
		// 发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		
		// 关闭连接
		transport.close();
	}

}
