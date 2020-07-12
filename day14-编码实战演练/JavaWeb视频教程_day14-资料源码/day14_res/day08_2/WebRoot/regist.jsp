<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>regist.jsp</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    
    <script type="text/javascript">
		function _change() {
			var img = document.getElementById("vCode");
			img.src = "<c:url value='/VerifyCodeServlet?name=registCode&'/>" + new Date().getTime();
		}
    </script>
  </head>
  
  <body>
    <h1>注册</h1>
    <hr/>
    <p style="font-weight: 900; color: red;">${msg }</p>
    <form action="<c:url value='/RegistServlet'/>" method="post">
    	用户名：<input type="text" name="username" value="${user.username }" /><br/>
    	密　码：<input type="password" name="password"/><br/>
    	确认密码：<input type="password" name="repassword"/><br/>
    	验证码：<input type="text" name="registCode" size="2"/>
    	<img id="vCode" src="<c:url value='/VerifyCodeServlet?name=registCode'/>" border="2"/>
    	<a href="javascript:_change()" style="font-size: 12;">看不清，换一张</a><br/>
        <input type="submit" value="注册"/>
    </form>
   
  </body>
</html>
