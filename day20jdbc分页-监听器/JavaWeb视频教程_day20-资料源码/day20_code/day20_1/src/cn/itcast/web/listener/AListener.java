package cn.itcast.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContext生死监听
 * @author cxf
 *  
 * 可以在这个监听器存放一些在tomcat启动时就要完成的代码！
 */
public class AListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("哇，我来也！");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("哇，我要挂也！");
	}
}
