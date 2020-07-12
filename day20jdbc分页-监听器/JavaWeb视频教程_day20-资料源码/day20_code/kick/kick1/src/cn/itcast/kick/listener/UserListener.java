package cn.itcast.kick.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.itcast.kick.User;

public class UserListener implements HttpSessionAttributeListener {
	public void attributeAdded(HttpSessionBindingEvent evt) {
		// ��ȡ��ӵ�session�е�name�Ƿ�Ϊ"user"
		if (evt.getName().equals("user")) {
			// ��ȡ��������
			Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
					.getServletContext().getAttribute("onlineUsers");
			// �����ǰ��¼���û��ǵ�һ���û�����ô��ʱ��û��"��������"������Ҫ����
			if(onlineUsers == null) {
				onlineUsers = new LinkedHashMap<String,User>();
			}
			// ��ȡ��ӵ�session�е�value������¼��User����
			User user = (User) evt.getValue();
			// ���û����浽����������
			onlineUsers.put(user.getUsername(), user);
			// �������������浽ServletContext��
			evt.getSession().getServletContext().setAttribute("onlineUsers", onlineUsers);
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent evt) {
		// ��ȡ��ӵ�session�е�name�Ƿ�Ϊ"user"
		if (evt.getName().equals("user")) {
			// ��ȡ��������
			Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
					.getServletContext().getAttribute("onlineUsers");
			// �����ǰ��¼���û��ǵ�һ���û�����ô��ʱ��û��"��������"������Ҫ����
			if(onlineUsers == null) {
				onlineUsers = new LinkedHashMap<String,User>();
			}
			// ��Ϊ���滻session�е�ֵʱ�Ż���ñ������������滻�ͻ�����ֵ����ֵ��
			// evt.getValue()��ȡ������ֵ�������滻��ֵ
			// ����Ҫ�����������������Ƴ�
			User user = (User) evt.getValue();
			// �������������Ƴ���ֵ��������һ�û���session�м�����
			onlineUsers.remove(user.getUsername());
			
			// ��ȡsession�б����user��������ֵ
			// ����Ҫ�������浽����������
			user = (User) evt.getSession().getAttribute("user");
			onlineUsers.put(user.getUsername(), user);
			
			// �������������浽ServletContext��
			evt.getSession().getServletContext().setAttribute("onlineUsers", onlineUsers);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent evt) {
		User user = (User)evt.getValue();
		// ��ȡ��������
		Map<String, User> onlineUsers = (Map<String, User>) evt.getSession()
				.getServletContext().getAttribute("onlineUsers");
		// �������������Ƴ�User
		if(onlineUsers != null) {
			onlineUsers.remove(user.getUsername());
		}
	}
}
