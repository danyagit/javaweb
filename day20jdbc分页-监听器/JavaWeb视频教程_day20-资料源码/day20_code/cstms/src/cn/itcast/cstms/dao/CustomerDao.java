package cn.itcast.cstms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.cstms.domain.Customer;
import cn.itcast.cstms.domain.PageBean;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * 持久层
 * @author cxf
 *
 */
public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 添加客户
	 * @param customer
	 * @throws SQLException 
	 */
	public void add(Customer customer) throws SQLException {
		/*
		 * 1.准备sql语句
		 * 2. 给出参数
		 * 3. 调用QueryRunner的update()
		 */
		String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
		Object[] params = {customer.getCid(), customer.getCname(), customer.getGender(),
				customer.getBirthday(),customer.getCellphone(),customer.getEmail(),
				customer.getDescription()};
		qr.update(sql, params);
	}
	
	/**
	 * 查询所有
	 * @return
	 * @throws SQLException 
	 */
//	public List<Customer> findAll() throws SQLException {
//		String sql = "select * from t_customer";
//		return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
//	}
	
	public PageBean<Customer> findAll(int pc, int ps) throws SQLException {
		/*
		 * 1. 总记录数
		 * 2. 当前页记录数
		 */
		String sql = "select count(*) from t_customer";
		int tr = ((Number)qr.query(sql, new ScalarHandler())).intValue();
		
		sql = "select * from t_customer limit ?,?";
		List<Customer> customerList = qr.query(sql, 
				new BeanListHandler<Customer>(Customer.class), (pc-1)*ps, ps);
		
		PageBean<Customer> pb = new PageBean<Customer>();
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		pb.setDatas(customerList);
		
		return pb;
	}
	
	/**
	 * 按cid查询客户
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	public Customer load(String cid) throws SQLException {
		String sql = "select * from t_customer where cid=?";
		return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
	}
	
	/**
	 * 修改方法
	 * @param customer
	 * @throws SQLException 
	 */
	public void edit(Customer customer) throws SQLException {
		String sql = "update t_customer set cname=?,gender=?,birthday=?,cellphone=?,email=?,description=? where cid=?";
		Object[] params = {customer.getCname(), customer.getGender(),
				customer.getBirthday(),customer.getCellphone(),customer.getEmail(),
				customer.getDescription(), customer.getCid()};
		qr.update(sql, params);
	}
	
	/**
	 * 删除客户
	 * @param cid
	 * @throws SQLException 
	 */
	public void delete(String cid) throws SQLException {
		String sql = "delete from t_customer where cid=?";
		qr.update(sql, cid);
	}
	
	/**
	 * 多条件组合查询
	 * @param c
	 * @return
	 * @throws SQLException 
	 */
//	public List<Customer> query(Customer c) throws SQLException {
//		/*
//		 * 1. 给出基本的sql语句
//		 */
//		StringBuilder sb = new StringBuilder("select * from t_customer where 1=1");
//		/*
//		 * 2. 创建List，用来保存参数
//		 */
//		List<Object> params = new ArrayList<Object>();
//		/*
//		 * 3. 判断c中每个字段是否存在，如果存在说明有这个条件，如果不存在就没有这个条件
//		 */
//		String cname = c.getCname();
//		if(cname != null && !cname.trim().isEmpty()) {
//			sb.append(" and cname like ?");
//			params.add("%" + cname + "%");
//		}
//		
//		String gender = c.getGender();
//		if(gender != null && !gender.trim().isEmpty()) {
//			sb.append(" and gender=?");
//			params.add(gender);
//		}
//		
//		String cellphone = c.getCellphone();
//		if(cellphone != null && !cellphone.trim().isEmpty()) {
//			sb.append(" and cellphone like ?");
//			params.add("%" + cellphone + "%");
//		}
//		
//		String email = c.getEmail();
//		if(email != null && !email.trim().isEmpty()) {
//			sb.append(" and email like ?");
//			params.add("%" + email + "%");
//		}
//		
//		return qr.query(sb.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
//	}
	
	public PageBean<Customer> query(Customer c, int pc, int ps) throws SQLException {
		/***********拼凑where子句*************/
		
		/*
		 * 1. 给出基本的sql语句
		 */
		StringBuilder where = new StringBuilder("where 1=1");
		/*
		 * 2. 创建List，用来保存参数
		 */
		List<Object> params = new ArrayList<Object>();
		/*
		 * 3. 判断c中每个字段是否存在，如果存在说明有这个条件，如果不存在就没有这个条件
		 */
		String cname = c.getCname();
		if(cname != null && !cname.trim().isEmpty()) {
			where.append(" and cname like ?");
			params.add("%" + cname + "%");
		}
		
		String gender = c.getGender();
		if(gender != null && !gender.trim().isEmpty()) {
			where.append(" and gender=?");
			params.add(gender);
		}
		
		String cellphone = c.getCellphone();
		if(cellphone != null && !cellphone.trim().isEmpty()) {
			where.append(" and cellphone like ?");
			params.add("%" + cellphone + "%");
		}
		
		String email = c.getEmail();
		if(email != null && !email.trim().isEmpty()) {
			where.append(" and email like ?");
			params.add("%" + email + "%");
		}
		
		///////////////////////////////////////////////////////
		
		/*
		 * 得到count的sql语句
		 */
		StringBuilder countSql = new StringBuilder("select count(*) from t_customer");
		String sql = countSql.append(" ").append(where).toString();
		// 得到总记录数
		int tr = ((Number)qr.query(sql, new ScalarHandler(), params.toArray())).intValue();
		
		
		/*
		 * 查询当前页的记录
		 * 头 + where + limit
		 */
		StringBuilder selectSql = new StringBuilder("select * from t_customer");
		StringBuilder limitSql = new StringBuilder("limit ?,?");
		
		sql = selectSql.append(" ").append(where).append(" ").append(limitSql).toString();
		// 因为limit中存在，两个?，那么就要向params中添加两个值
		params.add((pc-1) * ps);
		params.add(ps);
		
		// 得到当前页记录
		List<Customer> customerList = qr.query(sql, 
				new BeanListHandler<Customer>(Customer.class), params.toArray());
		
		/******************************/
		/*
		 * 创建PageBean
		 */
		PageBean<Customer> pb = new PageBean<Customer>();
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		pb.setDatas(customerList);
		return pb;
	}
}
