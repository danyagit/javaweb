package cn.itcast.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 从application中获取Map
 * 从request中得到当前客户端的IP
 * 进行统计工作，结果保存到Map中
 * @author cxf
 *
 */
public class AFilter implements Filter {
	private FilterConfig config;
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 得到application中的map
		 * 2. 从request中获取当前客户端的ip地址
		 * 3. 查看map中是否存在这个ip对应访问次数，如果存在，把次数+1再保存回去
		 * 4. 如果不存在这个ip，那么说明是第一次访问本站，设置访问次数为1
		 */
		/*
		 * 1. 得到appliction
		 */
		ServletContext app = config.getServletContext();
		Map<String,Integer> map = (Map<String, Integer>) app.getAttribute("map");
		/*
		 * 2. 获取客户端的ip地址
		 */
		String ip = request.getRemoteAddr();
		/*
		 * 3. 进行判断
		 */
		if(map.containsKey(ip)) {//这个ip在map中存在，说明不是第一次访问
			int cnt = map.get(ip);
			map.put(ip, cnt+1);
		} else {//这个ip在map中不存在，说明是第一次访问
			map.put(ip, 1);
		}
		app.setAttribute("map", map);//把map再放回到app中
		
		chain.doFilter(request, response);//肯定放行
	}

	/**
	 * 在服务器启动时就会执行本方法，而且本方法只执行一次！
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
}
