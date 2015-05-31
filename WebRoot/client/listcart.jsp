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
    <title>购物车列表</title>
  </head>
  
  <body style="text-align: center;">

	<%@include file="/client/head.jsp" %>
	
	<h2>购物车页面</h2>
	
	<table border="1" width="70%" style="margin:0 auto;">
		<tr>
			<td>书名</td>
			<td>售价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		
		<c:forEach var="entry" items="${cart.map }">
			<tr>
				<td>${entry.value.book.name }</td>
				<td>${entry.value.book.price }</td>
				<td>${entry.value.quantity }</td>
				<td>${entry.value.price }</td>
			</tr>
		</c:forEach>	
			<tr>
				<td colspan="2">合计</td>
				<td colspan="2">${cart.price }</td>
			</tr>	
	</table>
	<a href="<%=basePath %>client/OrderServlet?method=generateorder">生成订单</a>
  </body>
</html>
