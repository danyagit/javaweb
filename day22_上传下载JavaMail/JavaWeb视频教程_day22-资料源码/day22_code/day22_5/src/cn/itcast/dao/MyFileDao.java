package cn.itcast.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.domain.MyFile;
import cn.itcast.jdbc.TxQueryRunner;

public class MyFileDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 添加文件
	 * @param myFile
	 */
	public void add(MyFile myFile) {
		
	}
	
	/**
	 * 查询所有文件
	 * @return
	 */
	public List<MyFile> findAll() {
		return null;
	}
	
	/**
	 * 通过fid加载一个MyFile对象
	 * @param fid
	 * @return
	 */
	public MyFile load(String fid) {
		return null;
	}
	
	/**
	 * 编辑文件
	 * @param myFile
	 */
	public void edit(MyFile myFile) {
		
	}
}
