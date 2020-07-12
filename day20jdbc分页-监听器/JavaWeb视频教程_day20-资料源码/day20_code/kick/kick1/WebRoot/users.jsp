<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'users.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<c:choose>
	<c:when test="${empty sessionScope.user }">
		您还没有登录
	</c:when>
	<c:otherwise>

  用户名：${sessionScope.user.username }　
 <a href="<c:url value='/UserServlet?method=quit'/>">退出</a>
 <hr/>
    <table border="1" width="50%" align="center">
    <tr>
    	<th>用户名</th>
    	<th>IP地址</th>
    	<th>登录时间</th>
    	<th>操作</th>
    </tr>
<c:forEach items="${onlineUsers }" var="entry">
    <tr>
    	<td>${entry.value.username }</td>
    	<td>${entry.value.ip }</td>
    	<td>${entry.value.logintime }</td>
    	<td>
    		<a href="<c:url value='/UserServlet?method=kick&username=${entry.value.username }'/>">踢他</a>
    	</td>
    </tr>
</c:forEach>
    </table>
	</c:otherwise>
</c:choose>
  </body>
</html>
