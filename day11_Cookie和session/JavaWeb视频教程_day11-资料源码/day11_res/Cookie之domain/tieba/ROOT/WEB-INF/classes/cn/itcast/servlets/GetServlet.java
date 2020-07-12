package cn.itcast.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cs = request.getCookies();
		if(cs != null) {
			for(Cookie c : cs) {
				response.getWriter().print(c.getName() + "=" + c.getValue());
			}
		}
	}
}
