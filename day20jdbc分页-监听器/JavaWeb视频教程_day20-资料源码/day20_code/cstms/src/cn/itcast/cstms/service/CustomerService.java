package cn.itcast.cstms.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.cstms.dao.CustomerDao;
import cn.itcast.cstms.domain.Customer;
import cn.itcast.cstms.domain.PageBean;

/**
 * 业务层
 * @author cxf
 *
 */
public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * 添加客户
	 * @param customer
	 */
	public void add(Customer customer) {
		try {
			customerDao.add(customer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询所有
	 * @return
	 */
//	public List<Customer> findAll() {
//		try {
//			return customerDao.findAll();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public PageBean<Customer> findAll(int pc, int ps) {
		try {
			return customerDao.findAll(pc, ps);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按cid查询
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {
		try {
			return customerDao.load(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 编辑客户
	 * @param customer
	 */
	public void edit(Customer customer) {
		try {
			customerDao.edit(customer);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 删除客户
	 * @param customer
	 */
	public void delete(String cid) {
		try {
			customerDao.delete(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 多条件组合查询
	 * @param c
	 * @return
	 */
	public PageBean<Customer> query(Customer c, int pc, int ps) {
		try {
			return customerDao.query(c, pc, ps);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
