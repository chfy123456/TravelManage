<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>葫芦岛旅游网站后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="javascript" type="text/javascript" src="js/index.js"></script>
<script language="javascript" type="text/javascript"> 
var admin = "${admin.user_id}";
if(admin==null||admin=='')
{
  window.location.href="login.jsp";
} 
	
</script>
<style type="text/css">
html,body{
	margin:0px;
	padding:0px;
	height:100%;
}
body{
	background-color:#F2F3F5;
}
 body,td,div
 {
   font-size:12px;
 }
 table{
 	border-collapse: collapse; 
 	padding: 0;
 	margin:0px;
 }
 table tr{
 	padding: 0;
 	margin:0px;
 }
</style>
</head>

<body onload="setIframeWH()" style="margin:0px; padding:0px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td colspan="5" height="70" style="background-color:#093469;">
		<iframe width="100%" style="margin:0px; padding:0px;" height="70" scrolling="no" src="top.jsp" frameborder="0"></iframe>
	</td>
  </tr>
  <tr>
    <td width="190">
		<iframe name="LeftMenu" id="LeftMenu" width="100%" style="margin:0px; padding:0px;" height="100%" scrolling="no" src="left.jsp" frameborder="0"></iframe>
	</td>
    <td width="5"></td>
    <td>
		<iframe id="MainFrame" name="MainFrame" width="100%" style="margin:0px; padding:0px;" height="100%" scrolling="yes" src="main.jsp" frameborder="0"></iframe>
	</td>
  </tr>
</table>
</body>
</html>