package cn.itcast.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class ActiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserDao userDao = new UserDao();
		String code = request.getParameter("code");
		try {
			User user = userDao.findByCode(code);
			if(user == null) {
				response.getWriter().print("无效激活码！");
				return;
			}
			if(user.isStatus()) {
				response.getWriter().print(user.getUsername() + ", 你给我听清楚了，你已经激活过了！激活过了就不要再激活了！如果你再激活，我就把你给删了");
				return;
			}
			userDao.updateStatus(user.getUid(), true);
			response.getWriter().print("您好：" + user.getUsername() + ", 恭喜你激活成功！");
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
