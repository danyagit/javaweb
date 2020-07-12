package cn.itcast.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在这里给出多个请求处理方法
 * 请求处理方法：除了名称以外，都与service方法相同！
 * @author cxf
 *
 */
public class AServlet extends BaseServlet {
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("addUser()...");
		throw new IOException("测试一下");
	}

	public void editUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("editUser()...");
	}
	
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("deleteUser()...");
	}
	
	public void loadUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("loadUser()...");
	}
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("findAll()...");
	}
}
