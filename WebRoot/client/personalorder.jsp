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
    <title>My JSP 'personalorder.jsp' starting page</title>
  </head>
  
  <body style="text-align: center;">
    
	<table border="1" width="80%" style="margin:0 auto;">
		<tr>
			<td>订单人</td>
			<td>下单时间</td>
			<td>订单状态</td>
			<td>订单总价</td>
		</tr>
		
		<c:forEach var="o" items="${list }">
			<tr>
				<td>${o.user.username }</td>
				<td>${o.ordertime }</td>
				<td>${o.state }</td>
				<td>${o.price }</td>
			</tr>
		</c:forEach>
	</table>

  </body>
</html>
