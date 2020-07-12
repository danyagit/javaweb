package cn.itcast;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class User implements HttpSessionActivationListener, java.io.Serializable {
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("啊～我陪session去火星了，地球已经不安全了！");
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("啊～我和session一起重返地球了！");
	}
}
