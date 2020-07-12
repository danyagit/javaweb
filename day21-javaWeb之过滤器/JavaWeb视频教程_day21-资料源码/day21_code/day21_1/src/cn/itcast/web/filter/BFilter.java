package cn.itcast.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BFilter implements Filter {
	/**
	 * 创建之后马上执行，用来做初始化！
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 每次过滤时都会执行
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("BFilter#start");
		chain.doFilter(request, response);//放行！
		System.out.println("BFilter#end");
	}

	/**
	 * 销毁之前执行，用来做对非内存资源进行释放
	 */
	public void destroy() {
	}
}