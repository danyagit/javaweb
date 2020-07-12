package cn.itcast.kick;

import javax.servlet.http.HttpSession;

public class User {
	private String username;
	private String ip;
	private String logintime;
	private HttpSession session;
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String ip, String logintime) {
		super();
		this.username = username;
		this.ip = ip;
		this.logintime = logintime;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", ip=" + ip + ", logintime="
				+ logintime + "]";
	}
	
	
}
