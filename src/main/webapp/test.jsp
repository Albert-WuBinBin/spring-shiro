<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="static/js/jquery/jquery-3.2.1.min.js"></script>

<!-- ZTree树形插件 -->

<link rel="stylesheet" href="static/zTree/css/zTreeStyle/zTreeStyle.css"type="text/css"/>
 <link rel="stylesheet" href="static/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript"src="static/zTree/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript"src="static/zTree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript"src="static/zTree/js/jquery.ztree.exedit.min.js"></script>

<script type="text/javascript">
$(function(){
	
	var zTreeObj;
	var setting = {
			  /*view: {
	                addHoverDom: addHoverDom,
	                removeHoverDom: removeHoverDom,
	                selectedMulti: false
	            },
	            check: {
	                enable: true
	            },*/
	
			 async: {
			        enable: true,
			        type: "post",
			        dataType:"json",
			        url:"http://localhost:8080/spring-shiro-mybatis/getTree",
			 },
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: ""
				}
			},
			 edit: {
	                enable: true
	            }
	};
	
	//var zNodes = [
		//{id:1, pId:0, name: "父节点1"},
		//{id:11, pId:1, name: "子节点1"},
		//{id:12, pId:1, name: "子节点2"}
	//];
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);	 
});
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
	    var sObj = $("#" + treeNode.tId + "_span");
	    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	        + "' title='add node' onfocus='this.blur();'></span>";
	    sObj.after(addStr);
	    var btn = $("#addBtn_"+treeNode.tId);
	    if (btn) btn.bind("click", function(){
	        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	        console.log(treeNode.id);
	        zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
	        return false;
	    });
	};
	function removeHoverDom(treeId, treeNode) {
	    $("#addBtn_"+treeNode.tId).unbind().remove();
	};

</SCRIPT>
</HEAD>
<BODY>
    <div id="treeDemo" class="ztree">  
  	</div>
</BODY>
</html>