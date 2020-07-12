package cn.itcast.dao;

import java.io.FileWriter;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.itcast.domain.User;

public class UserDao {
	private String path;
	
	public UserDao() {
		path = this.getClass().getResource("/users.xml").getPath();
	}
	
	public void add(User user) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			
			Element root = doc.getRootElement();
			Element userEle = root.addElement("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			
			// 创建格式化器，使用\t缩进，添加换行
			OutputFormat format = new OutputFormat("\t", true);
			// 清空数据中原有的换行
			format.setTrimText(true);
			// 创建XML输出流对象
			XMLWriter writer = new XMLWriter(new FileWriter(path), format);
			// 输出Document
			writer.write(doc);
			// 关闭流
			writer.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public User findByUsername(String username) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			Element ele = (Element) doc.selectSingleNode("//user[@username='" + username + "']");
			if(ele == null) {
				return null;
			}
			User user = new User();
			user.setUsername(ele.attributeValue("username"));
			user.setPassword(ele.attributeValue("password"));
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
