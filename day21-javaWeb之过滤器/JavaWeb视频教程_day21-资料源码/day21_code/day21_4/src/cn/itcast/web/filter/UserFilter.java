package cn.itcast.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 得到session
		 * 2. 判断session域中是否存在admin，如果存在，放行
		 * 3. 判断session域中是否存在username，如果存在，放行，否则打回到login.jsp，并告诉它不要瞎留达
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		String name = (String)req.getSession().getAttribute("admin");
		if(name != null) {
			chain.doFilter(request, response);
			return;
		}
		
		name = (String)req.getSession().getAttribute("username");
		if(name != null) {
			chain.doFilter(request, response);
		} else {
			req.setAttribute("msg", "您啥都不是，不要瞎溜达！");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
