package cn.itcast.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		try {
			User user = userDao.login(username, password);
			if(user == null) {
				response.getWriter().print("用户名或密码错误！");
				return;
			}
			if(!user.isStatus()) {
				response.getWriter().print(user.getUsername() + "先生，您还没有激活，请马上到您的邮箱完成激活！");
				return;
			}
			response.getWriter().print("您好" + user.getUsername() + ", 欢迎登录本系统！");
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
