<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>当地美食信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){

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
<jsp:include page="top.jsp"><jsp:param name="menu" value="food" /></jsp:include>
<div id="picnews2"></div>
<div id="middleBg">
	<!--  产品检索展示 -->
	 <div id="product_info">
	 		<!--  产品列表 -->
	 		<form name="info" id="info" action="page_listFoods.action" method="post">
	 		<input type="hidden" id="pageNo" name="pageNo" value="${paperUtil.pageNo}"/>
			<div class="list">
					<div class="sign" style="background:none;text-align:right">
					           美食名称：
     					 <input type="text" id="food_title" name="food_title" value="${paramsCourse.food_title}" class="inputstyle"/>&nbsp;
						 <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
					</div>
					<div class="products">
					<c:if test="${foods!=null &&  fn:length(foods)>0}">
   					<c:forEach items="${foods}" var="food" varStatus="status">
					<div class="product">
						<div class="productPic"><a href="page_queryFood.action?food_id=${food.food_id}"><img src="images/infos/${food.food_pic}" /></a></div>
						<div class="productText">
							<span class="title" title="${food.food_title}">
								${fn:length(food.food_title)>12?fn:substring(food.food_title,0,11):food.food_title}..
							</span>
						</div>
					</div>
					</c:forEach>
					</c:if> 
					</div>
					
					<!--  产品分页 -->
					<jsp:include page="page.jsp"></jsp:include>
				    <!--  产品分页 -->

			</div>
			</form>
			<!--  产品列表 -->
			
	 </div>
	 <!--  产品检索展示 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.submit();
}
function ChangePage(pagenum)
{
	 document.getElementById("pageNo").value=pagenum;
	 document.info.submit();
}	 
function serch()
{
   var num = /^\d+(\.\d+)?$/;
   document.info.action="page_listFoods.action";
   document.getElementById("pageNo").value=1;
   document.info.submit();
}
</script>
</body>
</html>