package cn.itcast.kick;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取登录的用户名
		String username = request.getParameter("username");
		// 在ServletContext中获取“在线名单”
		Map<String, User> onlineUsers = (Map<String, User>) this
				.getServletContext().getAttribute("onlineUsers");
		// 如果“在线名单”存在
		if (onlineUsers != null) {
			// 如果“在线名单”中已经存在这个用户名
			if (onlineUsers.containsKey(username)) {
				// 转发到登录页面，显示错误信息
				request.setAttribute("msg", "用户" + username + "已经登录！");
				return "/index.jsp";
			}
		}
		// 获取IP地址
		String ip = request.getRemoteAddr();
		// 获取登录时间
		String logintime = String.format("%tF %<tT", new java.util.Date());
		// 创建User对象
		User user = new User(username, ip, logintime);
		// 把User对象保存到session中，这会执行监听器的attributeAdded()或attributeReplaced()方法
		request.getSession().setAttribute("user", user);
		// 设置当前用户的session
		user.setSession(request.getSession());

		// 重定向到users.jsp显示在线用户名单
		response.sendRedirect(request.getContextPath() + "/users.jsp");
		return null;
	}

	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		response.getWriter().print("退出成功！");
		return null;
	}

	public String kick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		if (!ip.equals("127.0.0.1")) {
			response.getWriter().print("您没有踢人的权限！");
			return null;
		}
		String username = request.getParameter("username");
		User currUser = (User) request.getSession().getAttribute("user");
		// 如果当前用户就是被踢用户，那么就是自己踢自己
		if(currUser.getUsername().equals(username)) {
			response.getWriter().print("您是不是有病，为什么要踢自己呢！");
			return null;
		}
		
		// 从在线名单中获取被踢的用户
		Map<String, User> onlineUsers = (Map<String, User>) this
				.getServletContext().getAttribute("onlineUsers");
		User user = onlineUsers.get(username);
		// 获取当前用户的session，再从session中移除当前用户
		user.getSession().removeAttribute("user");
		// 重定向到users.jsp显示在线用户名单
		response.sendRedirect(request.getContextPath() + "/users.jsp");
		return null;
	}
}
