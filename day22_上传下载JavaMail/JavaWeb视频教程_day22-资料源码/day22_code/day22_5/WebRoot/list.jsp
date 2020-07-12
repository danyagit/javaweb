<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'list.jsp' starting page</title>
    
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
<table align="center" width="60%" border="1">
  <tr>
    <th>编号</th>
    <th>文件名称</th>
    <th>上传时间</th>
    <th>下载次数</th>
    <th>文件大小</th>
    <th>操作</th>
  </tr>
  
  <tr>
    <td>FILE_1001</td>
    <td>白冰.jpg</td>
    <td>2014-05-12 16:40:32</td>
    <td>123</td>
    <td>53456</td>
    <td><a href="<c:url value='/MyFileServlet?method=download&fid=FILE_1001'/>">下载</a></td>
  </tr>
</table>
  </body>
</html>
