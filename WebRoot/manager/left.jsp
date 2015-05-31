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
    <title>后台左边的链接</title>
    
    <style type="text/css">
    	.dc {
    		display:none;
    	}
    	.pa {
    		margin:0 0 20px 10px;
    	}
    </style>
    
  </head>
  
  <body>
  
  <script type="text/javascript">
  	function displaytag(child) {
  		child.style.display = child.style.display == "block" ? "none" : "block";
  	}
  </script>
  
    <div class="pa">
    	<a href="javascript:void(0)" id="kk" onclick="displaytag(document.getElementById('cid'))">分类管理</a>
    		<div class="dc" id="cid">
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/addcategory.jsp" target="right">添加分类</a> <br />
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/CategoryServlet?method=getAll" target="right">查看分类</a>
    		</div>
    </div>	
    
    <div class="pa">
    	<a href="javascript:void(0)" onclick="displaytag(document.getElementById('bid'))">图书管理</a>
    		<div class="dc" id="bid">
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/BookServlet?method=addUI" target="right">添加图书</a> <br />
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/BookServlet?method=list" target="right">查看图书</a>
    		</div>
    </div>
    
    <div class="pa">
    	<a href="javascript:void(0)" onclick="displaytag(document.getElementById('oid'))">订单管理</a>
    		<div class="dc" id="oid">
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/OrderServlet2?method=getAll&state=false" target="right">待处理订单</a> <br />
    			&nbsp;&nbsp;<a href="<%=basePath %>manager/OrderServlet2?method=getAll&state=true" target="right">已发货订单</a>
    		</div>
    </div>
    
    
    <div class="pa">
    	<a href="javascript:void(0)" onclick="displaytag(document.getElementById('did'))">数据库管理</a>
    		<div class="dc" id="did">
    			&nbsp;&nbsp;<a href="<%=basePath %>/manager/dbbak.jsp" target="right">数据库备份</a> <br />
    			&nbsp;&nbsp;<a href="<%=basePath %>/manager/DbbakServlet?method=list" target="right">数据库恢复</a>
    		</div>
    </div>
    
    
  </body>
</html>
