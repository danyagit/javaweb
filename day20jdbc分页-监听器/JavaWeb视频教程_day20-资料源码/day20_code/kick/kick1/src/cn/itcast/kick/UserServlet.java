package cn.itcast.kick;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��¼���û���
		String username = request.getParameter("username");
		// ��ServletContext�л�ȡ������������
		Map<String, User> onlineUsers = (Map<String, User>) this
				.getServletContext().getAttribute("onlineUsers");
		// �������������������
		if (onlineUsers != null) {
			// ������������������Ѿ���������û���
			if (onlineUsers.containsKey(username)) {
				// ת������¼ҳ�棬��ʾ������Ϣ
				request.setAttribute("msg", "�û�" + username + "�Ѿ���¼��");
				return "/index.jsp";
			}
		}
		// ��ȡIP��ַ
		String ip = request.getRemoteAddr();
		// ��ȡ��¼ʱ��
		String logintime = String.format("%tF %<tT", new java.util.Date());
		// ����User����
		User user = new User(username, ip, logintime);
		// ��User���󱣴浽session�У����ִ�м�������attributeAdded()��attributeReplaced()����
		request.getSession().setAttribute("user", user);
		// ���õ�ǰ�û���session
		user.setSession(request.getSession());

		// �ض���users.jsp��ʾ�����û�����
		response.sendRedirect(request.getContextPath() + "/users.jsp");
		return null;
	}

	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		response.getWriter().print("�˳��ɹ���");
		return null;
	}

	public String kick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		if (!ip.equals("127.0.0.1")) {
			response.getWriter().print("��û�����˵�Ȩ�ޣ�");
			return null;
		}
		String username = request.getParameter("username");
		User currUser = (User) request.getSession().getAttribute("user");
		// �����ǰ�û����Ǳ����û�����ô�����Լ����Լ�
		if(currUser.getUsername().equals(username)) {
			response.getWriter().print("���ǲ����в���ΪʲôҪ���Լ��أ�");
			return null;
		}
		
		// �����������л�ȡ���ߵ��û�
		Map<String, User> onlineUsers = (Map<String, User>) this
				.getServletContext().getAttribute("onlineUsers");
		User user = onlineUsers.get(username);
		// ��ȡ��ǰ�û���session���ٴ�session���Ƴ���ǰ�û�
		user.getSession().removeAttribute("user");
		// �ض���users.jsp��ʾ�����û�����
		response.sendRedirect(request.getContextPath() + "/users.jsp");
		return null;
	}
}
