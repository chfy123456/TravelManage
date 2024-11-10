<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>景点门票内容</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 var user_id = "${userFront.user_id}";
	 $("#addOrders").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		window.location.href="page_addOrdersShow.action?pot_id=${pot.pot_id}";
	 });
}); 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:14px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"><jsp:param name="menu" value="pot" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 景点门票 >  </div>
		 <div class="article_title">${pot.pot_title}</div>
		 <div class="article_time">价格：${pot.pot_price}　发布时间：${fn:substring(pot.pot_date,0,19)}　发布人：${pot.pot_admin}　
		 <a id="addOrders" style="" href="javascript:void(0)">[<img src="images/bodong.gif" style="vertical-align:middle"/> 购买]</a></div>
		 <div class="article_pic">
			<img src='images/infos/${pot.pot_pic}'/>
		 </div>
		 <div class="article_con">${pot.pot_contentShow}</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>