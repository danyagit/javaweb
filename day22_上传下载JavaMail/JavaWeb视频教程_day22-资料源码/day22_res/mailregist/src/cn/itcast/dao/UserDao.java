package cn.itcast.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.domain.User;
import cn.itcast.domain.UserException;
import cn.itcast.jdbc.utils.JdbcUtils;
import cn.itcast.utils.CommonUtils;

public class UserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public User login(String username, String password) throws SQLException {
		String sql = "select * from t_user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username,
				password);
	}

	public void regist(User user) throws UserException, SQLException {
		String sql = "select * from t_user where username=?";
		User u = qr.query(sql, new BeanHandler<User>(User.class),
				user.getUsername());
		if (u != null) {
			throw new UserException("用户名已经注册，请选择其他用户名！");
		}
		sql = "select * from t_user where email=?";
		u = qr.query(sql, new BeanHandler<User>(User.class), user.getEmail());
		if (u != null) {
			throw new UserException("Email已经注册，请选择其他Email！");
		}

		user.setUid(CommonUtils.uuid());
		user.setStatus(false);
		user.setCode(CommonUtils.uuid() + CommonUtils.uuid() + CommonUtils.uuid()
				+ CommonUtils.uuid());

		sql = "insert into t_user values(?,?,?,?,?,?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
				user.getEmail(), user.getCode(), user.isStatus());
	}

	public void updateStatus(String uid, boolean status) throws SQLException {
		String sql = "update t_user set status=? where uid=?";
		qr.update(sql, status, uid);
	}

	public User findByCode(String code) throws SQLException {
		String sql = "select * from t_user where code=?";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}
}
