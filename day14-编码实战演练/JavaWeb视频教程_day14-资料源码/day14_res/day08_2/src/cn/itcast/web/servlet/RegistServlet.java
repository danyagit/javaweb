package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.domain.User;
import cn.itcast.exception.UserException;
import cn.itcast.service.UserService;

public class RegistServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
		}
		String loginCode = request.getParameter("registCode");
		String repassword = request.getParameter("repassword");
		
		if(user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			request.setAttribute("msg", "用户名不能为空!");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			request.setAttribute("msg", "密码不能为空!");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		if(!user.getPassword().equals(repassword)) {
			request.setAttribute("msg", "两次输入不一致！");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;			
		}
		if(loginCode == null || loginCode.trim().isEmpty()) {
			request.setAttribute("msg", "验证码不能为空！");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;			
		}
		
		String vCode = (String)request.getSession().getAttribute("registCode");
		request.getSession().removeAttribute("registCode");
		if(!vCode.equalsIgnoreCase(loginCode)) {
			request.setAttribute("msg", "验证码错误！");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;				
		}
		
		UserService userService = new UserService();
		try {
			userService.regist(user);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
	}
}
