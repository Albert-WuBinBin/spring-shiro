<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>ZTREE DEMO - beforeEditName / beforeRemove / onRemove /
	beforeRename / onRename</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"
	src="../static/js/jquery/jquery-3.2.1.min.js"></script>



<SCRIPT type="text/javascript">
$(function(){
	$("#h1").hide();
	$("#div1").mouseover(function(){
		  $("#div1").css("background-color","yellow");
		  $("#h1").show();
	});
	$("#div1").mouseout(function(){
		  $("#div1").css("background-color","white");
		  $("#h1").hide();
	});
})
</SCRIPT>
</HEAD>
<BODY>
	<div style="width:100px;height:100px;" id="div1">来来来</div>
	<a href="testAn">Test Ann</a>
</BODY>
</HTML>