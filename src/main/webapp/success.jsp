<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"href="static/js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="static/js/easyui/themes/icon.css"/>
<script type="text/javascript" src="static/js/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 80px;  padding: 10px">

		<span id="welcome">欢迎您：<a href="#" class="easyui-menubutton" data-options="menu:'#mm1'"><h1><shiro:principal/></h1></a></span>
		<div id="mm1" style="width:100px;">
		<div><a href="#" id="editpass">修改密码</a></div>
		<div><a href="logout">注销/退出</a></div>
		
		</div>
	
	</div>
	<div data-options="region:'west',split:true,title:'菜单'"
		style="width: 200px; ">
		<div id="nav" class="easyui-accordion" fit="false" border="false">
		<!--  导航内容 -->
 				功能菜单<br/>
		<c:forEach items="${sessionScope.url}" var="u">
			<h1><a href="${pageContext.request.contextPath}/${u.url}" target="MyIframe">${u.re_name}</a> </h1>
		</c:forEach>
		
		</div>
    </div>
	

	<div data-options="region:'center'">
		<iframe name="MyIframe"  id="MyIfrmae" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>
		
	</div>
</body>
</html>