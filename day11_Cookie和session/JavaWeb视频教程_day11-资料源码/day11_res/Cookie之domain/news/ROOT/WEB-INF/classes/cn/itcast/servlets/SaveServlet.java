package cn.itcast.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Cookie cookie = new Cookie("ok", "OK");
		cookie.setPath("/");
		cookie.setDomain(".qdmmy6.com");
		response.addCookie(cookie);
		
		response.getWriter().print("Cookie已经保存！");
	}
}
