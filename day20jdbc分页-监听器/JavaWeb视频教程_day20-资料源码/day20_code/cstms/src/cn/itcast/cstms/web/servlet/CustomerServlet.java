package cn.itcast.cstms.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.cstms.domain.Customer;
import cn.itcast.cstms.domain.PageBean;
import cn.itcast.cstms.service.CustomerService;
import cn.itcast.servlet.BaseServlet;

/**
 * Web层
 * @author cxf
 *
 */
public class CustomerServlet extends BaseServlet {
	private CustomerService customerService = new CustomerService();
	
	/**
	 * 多条件组合查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
//	public String query(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		/*
//		 * 1. 获取表单数据，封装到Customer中，它是一个条件对象！
//		 * 2. 调用service的方法查询出所有满足条件的客户
//		 * 3. 保存到request中
//		 * 4. 转发到list.jsp显示
//		 */
//		Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
//		List<Customer> customerList = customerService.query(c);
//		req.setAttribute("customerList", customerList);
//		return "/list.jsp";
//	}
	
	
	
	public String query(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 把条件截取出来，保存到pb.url中！
		 */
		String url = getUrl(req);

		
		/*
		 * 1. 搞定当前页码：pc
		 */
		int pc = getPc(req);
		/*
		 * 2. 设置每页记录数
		 */
		int ps = 10;
		
		
		// 获取页面传递的查询条件
		Customer c = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		// 处理get请求编码问题
		c = code(c);
		
		// 使用查询条件来完成查询
		PageBean<Customer> pb = customerService.query(c, pc, ps);
		
		/*
		 * 向pb中保存url
		 */
		pb.setUrl(url);
		
		req.setAttribute("pb", pb);
		return "f:/list.jsp";
	}
	
	private String getUrl(HttpServletRequest req) {
		String url = req.getQueryString();
		/*
		 * url中有可能存在pc，这需要把pc截取下去，不要它！
		 */
		int index = url.lastIndexOf("&pc=");
		if(index == -1) {
			return url;
		}
		return url.substring(0, index);
	}

	// 处理get请求的编码问题
	private Customer code(Customer c) throws UnsupportedEncodingException {
		c.setCname(new String(c.getCname().getBytes("ISO-8859-1"), "UTF-8"));
		c.setGender(new String(c.getGender().getBytes("ISO-8859-1"), "UTF-8"));
		c.setCellphone(new String(c.getCellphone().getBytes("ISO-8859-1"), "UTF-8"));
		c.setEmail(new String(c.getEmail().getBytes("ISO-8859-1"), "UTF-8"));
		return c;
	}

	/**
	 * 删除客户
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 */
		String cid = req.getParameter("cid");
		/*
		 * 2.调用service的delete()方法完成删除
		 */
		customerService.delete(cid);
		/*
		 * 3. 保存成功信息到request
		 */
		req.setAttribute("msg", "删除成功！");
		/*
		 * 4. 转发到msg.jsp
		 */
		return "/msg.jsp";
	}
	
	/**
	 * 编辑第二步之编辑
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Customer对象中
		 */
		Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		/*
		 * 2. 调用service方法完成修改
		 */
		customerService.edit(customer);
		
		/*
		 * 3. 保存成功信息到request中
		 */
		req.setAttribute("msg", "编辑成功！");
		/*
		 * 4. 转发到msg.jsp显示成功信息
		 */
		return "f:/msg.jsp";
	}
	
	/**
	 * 编辑第一步之查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadForEdit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 * 2. 通过cid查询出来Customer
		 * 3. 把Customer保存到request中
		 * 4. 转发到edit.jsp页面显示
		 */
		String cid = req.getParameter("cid");
		req.setAttribute("customer", customerService.load(cid));
		return "f:/edit.jsp";
	}
	
	/**
	 * 查询所有
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
//	public String findAll(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		/*
//		 * 1. 使用service查询得到所有客户信息
//		 * 2. 保存到request中
//		 * 3. 转发到list.jsp，list.jsp会使用<c:forEach>循环来遍历request中的用户信息
//		 */
//		List<Customer> customerList = customerService.findAll();
//		req.setAttribute("customerList", customerList);
//		return "f:/list.jsp";
//	}
	
	/*
	 * 获取当前页码
	 */
	private int getPc(HttpServletRequest req) {
		/*
		 * 1. 查看是否存在pc参数，如果存在，转换成int类型
		 * 2. 如果不存在pc参数，返回1
		 */
		String pc = req.getParameter("pc");
		if(pc != null && !pc.trim().isEmpty()) {
			return Integer.parseInt(pc);
		}
		return 1;
	}
	
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = getUrl(req);
		/*
		 * 1. 搞定当前页码：pc
		 */
		int pc = getPc(req);
		int ps = 10;//每页记录数
		
		
		
		PageBean<Customer> pb = customerService.findAll(pc, ps);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/list.jsp";
	}

	/**
	 * 添加客户
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Customer对象中。
		 * 2. 补全数据：cid，使用uuid()
		 * 3. 调用service完成添加
		 * 4. 输出成功!
		 */
		/*
		 * 1. 封装表单数据到Customer对象中
		 */
		Customer customer = CommonUtils.toBean(req.getParameterMap(), Customer.class);
		/*
		 * 2. 补全cid
		 */
		customer.setCid(CommonUtils.uuid());
		/*
		 * 3. 调用service的add()方法，完成添加业务
		 */
		customerService.add(customer);
		/*
		 * 4. 保存添加成功信息，转发到msg.jsp
		 */
		req.setAttribute("msg", "添加成功！");
		return "f:/msg.jsp";
	}
}
