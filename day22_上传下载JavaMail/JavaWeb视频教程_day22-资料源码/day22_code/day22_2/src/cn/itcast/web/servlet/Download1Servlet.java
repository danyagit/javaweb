package cn.itcast.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

public class Download1Servlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 两个头一个流
		 * 1. Content-Type
		 * 2. Content-Disposition
		 * 3. 流：下载文件的数据
		 */
		String filename = "F:/流光飞舞.mp3";
		
		// 为了使下载框中显示中文文件名称不出乱码！
//		String framename = new String("流光%飞舞.mp3".getBytes("GBK"), "ISO-8859-1");
		String framename = filenameEncoding("流光%飞舞.mp3", req);
		
		String contentType = this.getServletContext()
				.getMimeType(filename);//通过文件名称获取MIME类型
		String contentDisposition = "attachment;filename=" + framename;
		// 一个流
		FileInputStream input = new FileInputStream(filename);
		
		//设置头
		resp.setHeader("Content-Type", contentType);
		resp.setHeader("Content-Disposition", contentDisposition);
		
		// 获取绑定了响应端的流
		ServletOutputStream output = resp.getOutputStream();
		
		IOUtils.copy(input, output);//把输入流中的数据写入到输出流中。
		
		input.close();
	}
	
	// 用来对下载的文件名称进行编码的！
	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); //获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		} else if(agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
