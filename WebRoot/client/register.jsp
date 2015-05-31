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
    <title>用户注册</title>
  </head>
  
  <body style="text-align: center;">
    
	<h1>用户注册</h1>
		<form action="<%=basePath %>client/RegisterServlet" method="post">
			用户名: <input type="text" name="username" /><br /><br />
			密码: &nbsp;<input type="text" name="password" /><br /><br />
			电话: &nbsp;<input type="text" name="phone" /><br /><br />
			手机: &nbsp;<input type="text" name="cellphone" /><br /><br />
			住址: &nbsp;<input type="text" name="address" /><br /><br />
			邮箱: &nbsp;<input type="text" name="email" /><br /><br />
			 <input type="submit" value="注册" />
		</form>	

  </body>
</html>
