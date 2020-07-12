package cn.itcast.service;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.exception.UserException;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User login(String username, String password) throws UserException {
		User user = userDao.findByUsername(username);
		if(user == null) {
			throw new UserException("用户名错误！");
		}
		if(!user.getPassword().equals(password)) {
			throw new UserException("密码错误！");
		}
		return user;
	}
	
	public void regist(User user) throws UserException {
		User _user = userDao.findByUsername(user.getUsername());
		if(_user != null) {
			throw new UserException("用户名已注册！");
		}
		userDao.add(user);
	}
}
