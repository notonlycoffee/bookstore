<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据库恢复描述</title>
  </head>
  
  <body>
    <form action="<%=basePath %>manager/DbbakServlet?method=backup" method="post">
    	<textarea rows="8" cols="60" name="description"></textarea><br />
    	<input type="submit" value="提交备份信息" />
    </form>
  </body>
</html>
