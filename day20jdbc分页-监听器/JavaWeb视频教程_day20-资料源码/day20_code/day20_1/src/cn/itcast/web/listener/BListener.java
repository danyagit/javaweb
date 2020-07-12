package cn.itcast.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class BListener implements ServletContextAttributeListener {
	public void attributeAdded(ServletContextAttributeEvent scab) {
		System.out.println("您向application中添加了一个名为" + scab.getName() + ", 值为："
				+ scab.getValue() + "的属性");
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println(scab.getName() + "=" + scab.getValue() + ", "
				+ scab.getServletContext().getAttribute(scab.getName()));
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println(scab.getName() + "=" + scab.getValue());
	}
}
