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
    <title>列出订单项</title>
  </head>
  
  <body style="text-align: center;">
    
    <h3>订单明细</h3>
    
    	<table border="1" width="60%" style="margin:0 auto;">
    		<tr>
    			<td>书名</td>
    			<td>售价</td>
    			<td>数量</td>
    			<td>应收货款</td>
    		</tr>
    		
    		<c:forEach var="item" items="${order.orderitems }">
    			<tr>
	    			<td>${item.book.name }</td>
	    			<td>${item.book.price }</td>
	    			<td>${item.quantity }</td>
	    			<td>${item.price }</td>
    			</tr>
    		</c:forEach>
    		
    	</table>
    
    <h3>收货人详细地址</h3>
    	<table border="1" width="60%" style="margin:0 auto;">
    		<tr>
    			<td>用户</td>
    			<td>电话</td>
    			<td>手机</td>
    			<td>地址</td>
    			<td>邮箱</td>
    		</tr>
			<tr>
				<td>${order.user.username }</td>
				<td>${order.user.phone }</td>
				<td>${order.user.cellphone }</td>
				<td>${order.user.address }</td>
				<td>${order.user.email }</td>
			</tr>    		
    	</table>
    
    <a href="<%=basePath %>manager/OrderServlet2?method=statechange&id=${order.id }">确认发货</a>
  </body>
</html>
