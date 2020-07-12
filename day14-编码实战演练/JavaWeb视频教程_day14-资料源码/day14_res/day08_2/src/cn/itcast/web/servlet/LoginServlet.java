package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.exception.UserException;
import cn.itcast.service.UserService;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginCode = request.getParameter("loginCode");
		
		if(username == null || username.trim().isEmpty()) {
			request.setAttribute("msg", "用户名不能为空!");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(password == null || password.trim().isEmpty()) {
			request.setAttribute("msg", "密码不能为空!");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;			
		}
		if(loginCode == null || loginCode.trim().isEmpty()) {
			request.setAttribute("msg", "验证码不能为空！");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;			
		}
		
		String vCode = (String)request.getSession().getAttribute("loginCode");
		request.getSession().removeAttribute("loginCode");
		if(!vCode.equalsIgnoreCase(loginCode)) {
			request.setAttribute("msg", "验证码错误！");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;				
		}
		
		UserService userService = new UserService();
		User user;
		try {
			user = userService.login(username, password);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;	
		}
		
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
