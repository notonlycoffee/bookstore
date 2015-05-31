<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>添加分类</title>
</head>

<body>

	<form action="<%=basePath %>manager/CategoryServlet?method=add" method="post">
		分类名称 : <input type="text" name="name" style="margin:0 0 20px 0" /><br />
		分类描述 : <textarea rows="10" cols="50" name="description" style="margin:0 0 20px 0"></textarea><br />
		<input type="submit" value="添加分类" style="margin:0 0 0 100px"/>
	</form>

</body>
</html>
