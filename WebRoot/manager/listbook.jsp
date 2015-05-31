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
    <title>后台列出所有图书,不分页</title>
  </head>
  
  <body style="text-align: center">
	<h2>图书信息</h2>
	
	<table border="1" width="70%">
		<tr>
			<td>书名</td>
			<td>作者</td>
			<td>描述</td>
			<td>图片</td>
			<td colspan="2">操作</td>
		</tr>
		
		<c:forEach var="b" items="${list }">
			<tr>
				<td>${b.name }</td>
				<td>${b.author }</td>
				<td>${b.description }</td>
				<td>
					<a href="<%=basePath %>images/${b.image }">查看图片</a>
				</td>
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
