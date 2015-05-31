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
    <title>前台首页</title>
    
    <style type="text/css">
    	body {
    		margin:0;
    		padding:0;
    	}
    	#categories {
    		width:180px;
    		border:solid 1px red;
    		float:left;
    		margin-left:60px;
    	}
    	
    	#books {
    		width:920px;
    		float:right;
    		margin-right:100px;
    		
    	}
    	#book {
    		width:300px;
    		float:left;
    		height:130px;
    	}
    	
    	#info {
    		width:200px;
    		text-align: left;
    		float:left;
    	}
    	
    	#image {
    		float:left;
    	}
    	
    </style>
    
  </head>
  
  <body style="text-align: center;">
    
    <div id="container">
    	
    	<div id="head">
    		<%@include file="/client/head.jsp" %>
			<hr />
		</div>
    	<div id="main">
    		<div id="categories">
    			<c:forEach var="c" items="${categories }">
    				<ul>
    					<li>
    						<a href="<%=basePath %>client/IndexServlet?category_id=${c.id }">${c.name }</a>
    					</li>
    				</ul>
    			</c:forEach>
    		</div>
    		
    		<div id="books">
    			<c:forEach var="book" items="${pagebean.list }">
    				<div id="book">
	    				<div id="image">
	    					<img alt="img" src="<%=basePath %>/images/${book.image }">
	    				</div>
	    				<div id="info">
	    					<ul>
	    						<li>${book.name }</li>
	    						<li>${book.author }</li>
	    						<li>${book.price }</li>
	    						<li>
	    							<a href="<%=basePath %>client/BuyServlet?id=${book.id }">购买</a>
	    						</li>	
	    					</ul>
	    				</div>
    				</div>
    			</c:forEach>
    			
    			<div style="clear:both;"></div>
    			<br /><br />
    			
    			
    			<div style="text-align: center;">
					
					共[${pagebean.totalRecord }]条记录,
					每页[${pagebean.pageSize }]条记录,
					共[${pagebean.totalPage }]页,
					当前第[${pagebean.currentPage }]页,
					<a href="<%=basePath %>client/IndexServlet?currentPage=${pagebean.previousPage }&&category_id=${param.category_id }">上一页</a>
					<c:forEach var="pagenum" items="${pagebean.pageBar }">
						<a href="<%=basePath %>client/IndexServlet?currentPage=${pagenum }&&category_id=${param.category_id }">${pagenum }</a>
					</c:forEach>
					<a href="<%=basePath %>client/IndexServlet?currentPage=${pagebean.nextPage }&&category_id=${param.category_id }">下一页</a>
				</div>
    		</div>
    		
    	
    			
    	</div>
    	
    </div>
    
    
    
    
	

  </body>
</html>
