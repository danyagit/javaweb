登录成功后显示在线用户

1. login
2. login()
3. users.jsp

UserServlet#login()
1 获取用户名
2 创建User，补全数据（ip、logintime）
3 保存到session中
4 转发到users.jsp

UserListener
add()
key: "user"
value: User对象

1 查看servletContext中是否存在users
2 如果不存在，创建users = new Map<String,User>();
3 把添加的User对象存放到users中
4 把users放到ServletContext中

users.jsp
循环显示ServletContext中的users。

=================================

UserListener
replace()
key: "user"
value: 老User对象
1 查看servletContext中是否存在users
2 如果不存在，创建users = new Map<String, User>();
3 获取session，从session中获取"user"，即新User对象
4 在users中移除老User对象，再保存新User对象

==================================

