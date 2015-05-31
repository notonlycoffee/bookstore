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
<title>My JSP 'manager.jsp' starting page</title>
</head>

	<frameset rows="18%,*">
		<frame src="<%=basePath %>/manager/head.jsp" name="head">
		<frameset cols="15%,*">
			<frame src="<%=basePath %>/manager/left.jsp" name="left">
			<frame src="about:bank;" name="right">
		</frameset>
	</frameset>
</html>
