package cn.itcast.jdbc;

import java.sql.SQLException;

import org.junit.Test;

public class Demo1 {
	private AccountDao dao = new AccountDao();

	@Test
	public void serviceMethod() throws Exception {
		try {
			JdbcUtils.beginTransaction();
			
			dao.update("zs", -100);
			
			if(true) throw new RuntimeException();
			
			dao.update("ls", 100);
			
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw e;
		}
	}
}
