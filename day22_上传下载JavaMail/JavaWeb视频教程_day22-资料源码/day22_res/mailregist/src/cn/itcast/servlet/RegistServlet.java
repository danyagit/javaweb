package cn.itcast.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.domain.UserException;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.utils.CommonUtils;

public class RegistServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserDao userDao = new UserDao();
		final User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			userDao.regist(user);
			new Thread() {
				public void run() {
					Session session = MailUtils.createSession("smtp.163.com", "itcast_cxf", "itcast");
					Mail mail = new Mail("itcast_cxf@163.com", user.getEmail());
					mail.setSubject("来自XXX网站的注册激活邮件");
					String text = "请点击<a href='http://localhost:8080/mailregist/ActiveServlet?code=" + user.getCode() + "'>这里</a>激活";
					mail.setContent(text);
					
					try {
						MailUtils.send(session, mail);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}.start();
			response.getWriter().print("恭喜您注册成功！请马上到您的邮箱去激活！");
		} catch (UserException e) {
			response.getWriter().print(e.getMessage());
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
