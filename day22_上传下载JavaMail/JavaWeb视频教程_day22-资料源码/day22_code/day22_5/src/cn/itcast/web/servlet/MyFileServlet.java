package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.service.MyFileService;
import cn.itcast.servlet.BaseServlet;

public class MyFileServlet extends BaseServlet {
	private MyFileService myFileService = new MyFileService();
	
	/**
	 * 查询所有
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return null;
	}

	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return null;
	}
}
