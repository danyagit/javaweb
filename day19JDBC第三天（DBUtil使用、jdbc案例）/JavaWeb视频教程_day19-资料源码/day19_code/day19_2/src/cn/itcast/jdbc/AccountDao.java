package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

/**
 * @author cxf
 *
 */
public class AccountDao {
	public static void update(String name, double money) throws SQLException {
		QueryRunner qr = new TxQueryRunner();
		String sql = "update account set balance=balance+? where name=?";
		Object[] params = {money, name};
		
		// 我们需要自己来提供连接，保证多次调用使用的是同一个连接！
		qr.update(sql, params);
	}
}
