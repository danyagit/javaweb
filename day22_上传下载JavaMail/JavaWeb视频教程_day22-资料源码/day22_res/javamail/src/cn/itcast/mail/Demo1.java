package cn.itcast.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class Demo1 {
	@Test
	public void fun1() throws AddressException, MessagingException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("itcast_cxf", "itcast");
			}
		};
		
		Session session = Session.getDefaultInstance(prop, auth);
		
		session.setDebug(true);
		
		MimeMessage msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress("itcast_cxf@163.com"));
		msg.addRecipients(RecipientType.TO, "itcast_cxf@126.com, itcast_cxf@qq.com");
		msg.addRecipients(RecipientType.CC, "itcast_cxf@sina.com");
		msg.addRecipients(RecipientType.BCC, "itcast_cxf@sohu.com");
		msg.setSubject("����һ������ʼ���");
		msg.setText("����һ������ʼ����벻Ҫ���⣡", "utf-8");
		
		Transport.send(msg);
	}
	
	@Test
	public void fun2() throws AddressException, MessagingException, IOException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("itcast_cxf", "itcast");
			}
		};
		
		Session session = Session.getDefaultInstance(prop, auth);
		
		session.setDebug(false);
		
		MimeMessage msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress("itcast_cxf@163.com"));
		msg.addRecipients(RecipientType.TO, "itcast_cxf@qq.com");
		msg.setSubject("����һ����и������ʼ���");
		
		MimeMultipart parts = new MimeMultipart();
		
		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent("<h1><font color='red'>����һ����и������ʼ������������Եģ��벻Ҫ���⣡</font></h1>", "text/html;charset=utf-8");
		parts.addBodyPart(part1);
		
//		MimeBodyPart part1 = new MimeBodyPart();
//		part1.attachFile("F:\\maze-game.swf");
//		part1.setFileName("maze-game.swf");
//		parts.addBodyPart(part1);
		
		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile("F:\\�ױ�.jpg");
		part2.setFileName(MimeUtility.encodeText("�ױ�.jpg"));
		parts.addBodyPart(part2);
		
//		MimeBodyPart part3 = new MimeBodyPart();
//		part3.attachFile("F:\\û�뿪��.mp3");
//		part3.setFileName("û�뿪��.mp3");
//		parts.addBodyPart(part3);
		
		msg.setContent(parts);
		
		Transport.send(msg);
	}
	
	@Test
	public void fun3() throws MessagingException, IOException {
		Mail mail = new Mail("itcast_cxf@163.com", "itcast_cxf@126.com");
		mail.setSubject("����");
		mail.setContent("����һ������ʼ�����Ҫ̫���⣡");
		
		Session session = MailUtils.createSession("smtp.163.com", "itcast_cxf", "itcast");
		MailUtils.send(session, mail);
	}
}
