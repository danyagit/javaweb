package cn.itcast.i18n.demo;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import cn.itcast.i18n.MessageUtils;

public class Demo1 {
	@Test
	public void fun1() {
		MessageUtils.setLocale(new Locale("zh", "CN"));
		String username = MessageUtils.getText("msg.username");
		String password = MessageUtils.getText("msg.password");
		System.out.println(username);
		System.out.println(password);		
	}
	
	@Test
	public void fun2() {
		MessageUtils.setLocale(new Locale("en", "US"));
		String username = MessageUtils.getText("msg.username");
		String password = MessageUtils.getText("msg.password");
		System.out.println(username);
		System.out.println(password);
	}
}

