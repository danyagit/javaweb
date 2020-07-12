package cn.itcast.kick.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.itcast.kick.User;

public class UserListener implements HttpSessionAttributeListener {
	public void attributeAdded(HttpSessionBindingEvent evt) {
		// 获取添加到session中的name是否为"user"
		if (evt.getName().equals("user")) {
			// 获取在线名单
			Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
					.getServletContext().getAttribute("onlineUsers");
			// 如果当前登录的用户是第一个用户，那么这时还没有"在线名单"，所以要创建
			if(onlineUsers == null) {
				onlineUsers = new LinkedHashMap<String,User>();
			}
			// 获取添加到session中的value，即登录的User对象
			User user = (User) evt.getValue();
			// 把用户保存到在线名单中
			onlineUsers.put(user.getUsername(), user);
			// 把在线名单保存到ServletContext中
			evt.getSession().getServletContext().setAttribute("onlineUsers", onlineUsers);
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent evt) {
		// 获取添加到session中的name是否为"user"
		if (evt.getName().equals("user")) {
			// 获取在线名单
			Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
					.getServletContext().getAttribute("onlineUsers");
			// 如果当前登录的用户是第一个用户，那么这时还没有"在线名单"，所以要创建
			if(onlineUsers == null) {
				onlineUsers = new LinkedHashMap<String,User>();
			}
			// 因为是替换session中的值时才会调用本方法，所以替换就会有老值和新值。
			// evt.getValue()获取的是老值，即被替换的值
			// 我们要把它从在线名单中移除
			User user = (User) evt.getValue();
			// 从在线名单中移除老值，即把上一用户从session中挤下线
			onlineUsers.remove(user.getUsername());
			
			// 获取session中保存的user，这是新值
			// 我们要把它保存到在线名单中
			user = (User) evt.getSession().getAttribute("user");
			onlineUsers.put(user.getUsername(), user);
			
			// 把在线名单保存到ServletContext中
			evt.getSession().getServletContext().setAttribute("onlineUsers", onlineUsers);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent evt) {
		User user = (User)evt.getValue();
		// 获取在线名单
		Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
				.getServletContext().getAttribute("onlineUsers");
		// 从在线名单中移除User
		if(onlineUsers != null) {
			onlineUsers.remove(user.getUsername());
		}
	}
}
