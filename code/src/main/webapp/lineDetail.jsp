<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>线路攻略内容</title>
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
	 //点击预定
	 var user_id = "${userFront.user_id}";
	 var num = /^\d+$/;
	 $("#addCollect").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		var aQuery = {
			'user_id':user_id,
		    'line_id':'${line.line_id}'
		};  
		$.post('page_addCollect.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('收藏成功！');
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="line" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 线路攻略 >  </div>
		 <div class="article_title">${line.line_title}</div>
		 <div class="article_time">发布时间：${fn:substring(line.line_date,0,19)}　发布人：${line.line_admin}　<a id="addCollect" style="" href="javascript:void(0)">[<img src="images/house001.gif" style="vertical-align:middle"/> 收藏线路]</a></div>
		 <div class="article_pic">
			<img src='images/infos/${line.line_pic}'/>
		 </div>
		 <div class="article_con">${line.line_contentShow}</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>