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
    <title>列出所有未发货订单</title>
  </head>
  	
  <body style="text-align: center;">
    
    <h2>未发货的订单</h2>
    <table border="1" width="80%" style="margin:0 auto;">
    	<tr>
    		<td>订单人</td>
    		<td>下单时间</td>
    		<td>订单状态</td>
    		<td>订单总价</td>
    		<td colspan="2">操作</td>
    	</tr>
    	
    	<c:forEach var="orders" items="${list }">
    		<tr>
	    		<td>${orders.user.username }</td>
	    		<td>${orders.ordertime }</td>
	    		<td>${orders.state }</td>
	    		<td>${orders.price }</td>
	    		<td>
	    			<a href="<%=basePath %>manager/OrderServlet2?method=getitem&id=${orders.id }">查看明细</a>
	    		</td>
	    		<td>
	    			<a href="">修改</a>
	    		</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
