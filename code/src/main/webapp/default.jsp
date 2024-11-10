<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	var index=1;
	function changeImg(){
		index ++;
		index = index > 4 ? 1 : index;
		return "images/hdtp/"+index+".jpg";
	}
	setInterval(function(){
		$("#img1").hide();
		$("#img1").attr("src",changeImg());
		$("#img1").fadeIn(); 
	},3000);
});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:14px;
 }
 #infoField,#loginField{
 	line-height:35px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"><jsp:param name="menu" value="home" /></jsp:include>
<div id="picnews">
	<img id="img1" src="images/hdtp/1.jpg" style="width:100%;height:100%"/>
</div>
<div id="middle">	
	<div id="middle_left">
		<div id="info">
			<div class="titleBg">
				<div class="titleBg_text">最新线路攻略</div>
				<div class="titleBg_more">
					<a herf="page_listLines.action">More&gt;&gt;</a>
				</div>
			</div>
			<div class="info_con">
				<ul>
				 <c:forEach items="${lines}" var="line" varStatus="status">
				  <li>
					<div>
						<div class="info_text">
							<a href="page_queryLine.action?line_id=${line.line_id}" target="_blank" title="${line.line_title}">
								${fn:length(line.line_title)>25?fn:substring(line.line_title,0,24)+'...':line.line_title}
							</a>
						</div>
						<div class="info_time">[${fn:substring(line.line_date,0,10)}]</div>
					</div>
				  </li>
				  </c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="middle_right">
		<div id="info">
			<div class="titleBg">
				<div class="titleBg_text">最新旅游新闻</div>
				<div class="titleBg_more">
					<a herf="page_listInfos.action">More&gt;&gt;</a>
				</div>
			</div>
			<div class="info_con">
				<ul>
				 <c:forEach items="${infos}" var="info" varStatus="status">
				  <li>
					<div>
						<div class="info_text">
							<a href="page_queryInfo.action?info_id=${info.info_id}" target="_blank" title="${info.info_title}">
								${fn:length(info.info_title)>25?fn:substring(info.info_title,0,24)+'...':info.info_title}
							</a>
						</div>
						<div class="info_time">[${fn:substring(info.info_date,0,10)}]</div>
					</div>
				  </li>
				  </c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>