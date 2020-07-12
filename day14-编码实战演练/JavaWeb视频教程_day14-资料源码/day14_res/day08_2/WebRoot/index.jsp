<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login.jsp</title>
  </head>
  
  <body>
    <h1>主页</h1>
    <hr/>
<c:choose>
	<c:when test="${empty sessionScope.user }">
		您还没有登录
	</c:when>
	<c:otherwise>
		用户名：${sessionScope.user.username }
		<a href="<c:url value='/QuitServlet'/>">退出</a>
	</c:otherwise>
</c:choose>
  </body>
</html>
