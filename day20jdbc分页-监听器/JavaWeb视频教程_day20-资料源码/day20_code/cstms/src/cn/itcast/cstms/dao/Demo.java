package cn.itcast.cstms.dao;

import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.cstms.domain.Customer;

public class Demo {
	@Test
	public void fun1() throws SQLException {
		CustomerDao dao = new CustomerDao();
		for(int i = 1; i <= 300; i++) {
			Customer c = new Customer();
			c.setCid(CommonUtils.uuid());
			c.setCname("cname_" + i);
			c.setGender(i%2==0?"男":"女");
			c.setBirthday("2014-05-08");
			c.setCellphone("133" + i);
			c.setEmail(c.getCname() + "@163.com");
			c.setDescription("我是" + c.getCname());
			
			dao.add(c);
		}
	}
}
