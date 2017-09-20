<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<!-- ZTree树形插件 -->
<link rel="stylesheet" href="static/zTree/css/zTreeStyle/zTreeStyle.css"type="text/css"/>
<script type="text/javascript"src="static/zTree/js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript">

$(function(){
	var zTreeObj;
	var setting = {
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: ""
				}
			},
	};
	var zNodes = [
		{id:1, pId:0, name: "父节点1"},
		{id:11, pId:1, name: "子节点1"},
		{id:12, pId:1, name: "子节点2"}
	];
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$('#dg').datagrid( {
		  loadMsg : "正在加载数据",
		  url : 'getAllRoles',
		  searching : true,
		  pagination : true,
		  toolbar:"#toolbar",
		  paginatio:"true" ,
		  rownumbers:"true",
		  fitColumns:"true" ,
		  singleSelect:"true",
		  onLoadSuccess: function (data) {
              if (data.total == 0) {
            	  var body = $(this).data().datagrid.dc.body2;
            	  body.find('table ').append('<tr><td colspan="3" style="color:red;height: 35px; text-align: center;"><h1>暂无数据</h1></td></tr>');
                  $(this).closest('div.datagrid-wrap').find
                  ('div.datagrid-pager').hide();
              }

              else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
          }
		  
	});
		 
});
function add(){
    $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加停车场');
    $('#fm').form('clear');
    
}
function edit(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#dlg1').dialog('open').dialog('center').dialog('setTitle','修改停车场');
        $('#fm1').form('load',row);
        url = 'editUsers';
    }
}
function addUsers(){
	var userid=$("#userid").val();
	
	if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(userid))){ 
		$.messager.alert({
            title: '提示',
            msg: "请输入正确的手机号码(11位)!"
        });
        $("#usercode").focus();
        return false; 
    } 
    $('#fm').form('submit',{
        url: 'addUsers',
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
                var result = eval('('+result+')');
                if(result.Msg=="true"){
                	$.messager.alert({
                        title: 'Success',
                        msg: "添加成功"
                    });
                	  $('#dlg').dialog('close');        
                      $('#dg').datagrid('reload'); 
                }
                else if(result.Msg=="false"){
                	$.messager.alert({
                        title: '提示',
                        msg: "添加失败，该用户编码已经存在，请重新输入!"
                    });
                }
                
            
        }
    });
}
function editUsers(){
	 var row = $('#dg').datagrid('getSelected');
	 var usercode=row.usercode;
    $('#fm1').form('submit',{
        url: 'editUsers?usercode='+usercode,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
        	var result = eval('('+result+')');
            if(result.Msg=="true"){
            	$.messager.alert({
                    title: '提示',
                    msg: "修改成功"
                });
            	  $('#dlg1').dialog('close');        
                  $('#dg').datagrid('reload'); 
            }
            
        }
    });
}
function deleteUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('删除提示','确定要删除这个用户的信息吗?',function(r){
            if (r){
                $.post('deleteUser',{usercode:row.usercode},function(result){
                    if (result.Msg){
                    	$.messager.alert({
                            title: '提示',
                            msg: "删除成功"
                        });
                        $('#dg').datagrid('reload');    // reload the user data
                    } 
                },'json');
            }
        });
    }
}
</SCRIPT>
</HEAD>
<BODY>
		<div>
		<!-- <ul id="treeDemo" class="ztree"></ul> -->
		<table id="dg" title="角色管理" class="easyui-datagrid" style="height:550px;">
        <thead>
            <tr>
                <th field="role_name" width="100">角色名称</th>
                <th field="description" width="100">角色描述</th>
                <th field="resource_id"width="300">角色资源</th>
 
            </tr>
        </thead>
    </table>
    <div id="toolbar">
       <shiro:hasPermission name="role:add"> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a></shiro:hasPermission>
       <shiro:hasPermission name="role:edit"> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">修改</a></shiro:hasPermission>
       <shiro:hasPermission name="role:delete"> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletePark()">删除</a></shiro:hasPermission>
    </div>
    <div id="dlg" class="easyui-dialog" style="width:400px"
            closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">用户信息</div>
            <div style="margin-bottom:10px">
                <input name="usercode" type="text" id="userid" class="easyui-textbox"  label="用户编码:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="usrname" type="text" class="easyui-textbox"  label="用户名字" style="width:100%">
            </div>
             <div style="margin-bottom:10px" id="treeDemo" class="ztree">
               
            </div>
  
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="addUsers()" style="width:90px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
  </div>
 
</BODY>
</html>