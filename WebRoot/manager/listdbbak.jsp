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
    <title>备份记录列表</title>
  </head>
  
  <body style="text-align: center;">
    
	<table border="1" width="80%" style="margin:0 auto;">
		<tr>
			<td>备份文件名</td>
			<td>备份时间</td>
			<td>备份原因</td>
			<td>操作</td>
		</tr>
		
		<c:forEach var="bak" items="${list }">
			<tr>
				<td>${bak.filename }</td>
				<td>${bak.baktime }</td>
				<td>${bak.description }</td>
				<td>
					<a href="<%=basePath %>manager/DbbakServlet?method=restore&id=${bak.id }">恢复</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>

  </body>
</html>
