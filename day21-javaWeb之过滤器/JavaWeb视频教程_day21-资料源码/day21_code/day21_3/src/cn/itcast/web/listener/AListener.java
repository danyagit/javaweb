package cn.itcast.web.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AListener implements ServletContextListener {
	/**
	 * 在服务器启动时创建Map，保存到ServletContext
	 */
    public void contextInitialized(ServletContextEvent sce) {
    	// 创建Map
    	Map<String,Integer> map = new LinkedHashMap<String,Integer>();
    	// 得到ServletContext
    	ServletContext application = sce.getServletContext();
    	// 把map保存到application中
    	application.setAttribute("map", map);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
