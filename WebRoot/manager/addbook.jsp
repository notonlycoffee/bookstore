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
<title>添加书本</title>
</head>

<body>
	<form action="<%=basePath %>manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
		书名&nbsp;&nbsp; <input type="text" name="name" /> <br /><br /> 
		作者&nbsp;&nbsp; <input type="text" name="author" /> <br /><br />
		售价&nbsp;&nbsp; <input type="text" name="price" /> <br /> <br />
		图片&nbsp;&nbsp; <input type="file" name="image" /> <br /><br />
		描述&nbsp;&nbsp;<textarea rows="5" cols="50" name="description"></textarea><br /> <br />
		
		所属分类 <select name="category_id">
			<c:forEach var="c" items="${categories }">
				<option  value="${c.id }">${c.name }</option>
			</c:forEach>
		</select><br /><br /> 
		<input type="submit" value="添加图书" />
	</form>


</body>
</html>
