<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>旅游新闻内容</title>
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
	  
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:14px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"><jsp:param name="menu" value="info" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 旅游新闻 >  </div>
		 <div class="article_title">${info.info_title}</div>
		 <div class="article_time">发布时间：${fn:substring(info.info_date,0,19)}　发布人：${info.info_admin}</div>
		 <%--<s:if test="#attr.info.info_pic!=null && #attr.info.info_pic!=''">
		 <div class="article_pic">
			<img src='images/infos/<s:property value="#attr.info.info_pic" />'/>
		 </div>
		 </s:if>
		 --%><div class="article_con">${info.info_contentShow}</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>