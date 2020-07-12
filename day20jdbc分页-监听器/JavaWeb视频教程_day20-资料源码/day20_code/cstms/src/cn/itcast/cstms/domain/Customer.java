package cn.itcast.cstms.domain;

/**
 * 实体类
 * @author cxf
 *
 */
/*
 * 它要对应两样东西：
 *  * 表
 *  * 表单
 */
public class Customer {
	private String cid;//主键
	private String cname;//客户名
	private String gender;//客户性别
	private String birthday;//客户生日
	private String cellphone;//手机
	private String email;//邮件
	private String description;//描述
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", gender="
				+ gender + ", birthday=" + birthday + ", cellphone="
				+ cellphone + ", email=" + email + ", description="
				+ description + "]";
	}

	
}
