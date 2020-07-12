package cn.itcast.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		
		VerifyCode vc = new VerifyCode();//创建验证码类
		BufferedImage image  = vc.getImage();//创建验证码图片
		request.getSession().setAttribute(name, vc.getText());//获取验证码文本
System.out.println(vc.getText());
		VerifyCode.output(image, response.getOutputStream());//输出图片到页面
	}
}
