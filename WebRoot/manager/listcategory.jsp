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
    <title>列出所有的分类</title>
  </head>
  
  <body style="text-align: center;">
	
	<h2 style="margin:100px 0 20px 0">书籍分类信息</h2>
	
	<table border="1" width="60%" style="margin:0 auto;">
		<tr>
			<td>分类名称</td>
			<td>分类描述</td>
			<td colspan="2">操作</td>
		</tr>
		
		<c:forEach var="categories" items="${list }">
			<tr>
				<td>${categories.name }</td>
				<td>${categories.description }</td>
				<td>
					<a href="">修改</a>
				</td>
				<td>
					<a href="">删除</a>
				</td>
			</tr>
		</c:forEach>
		
		
		
	</table>    

  </body>
</html>
