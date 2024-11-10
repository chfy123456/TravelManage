<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头部页面</title>
<link type="text/css" href="css/style.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 
	 $("#logOut").bind('click',function(){
		if(confirm("确认退出？")){
			window.parent.location.href="LoginOutSystem.action";
		}
		 
	 });
	
});	 
	
</script>
</head>

<body>
<div class="top">
    <div class="logo" style="height:70px;line-height:70px;font-size: 32px;color: white;font-family: "微软雅黑";">
		葫芦岛旅游网站后台
	</div>
    <div class="top_info">
    	<img src="images/user.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;${admin.user_typeDesc }：${admin.real_name}　
    	<img src="images/exit.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;<a id="logOut" href="#">退出系统</a></div>
</div>
</body>
</html>
<script language="javascript">
</script>
