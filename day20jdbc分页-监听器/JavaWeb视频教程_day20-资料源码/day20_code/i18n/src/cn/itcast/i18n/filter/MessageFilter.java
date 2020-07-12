package cn.itcast.i18n.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.i18n.MessageUtils;

public class MessageFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String l = req.getParameter("request_locale");
		Locale locale = null;
		if(l != null && !l.isEmpty()) {
			String[] strs = l.split("_");
			locale = new Locale(strs[0], strs[1]);
			req.getSession().setAttribute("WW_TRANS_I18N_LOCALE", locale);
		} else {
			locale = (Locale)req.getSession().getAttribute("WW_TRANS_I18N_LOCALE");
		}
		if(locale == null) {
			locale = req.getLocale();
		}
		MessageUtils.setLocale(locale);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
