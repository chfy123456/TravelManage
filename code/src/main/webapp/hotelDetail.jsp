<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
 $(document).ready(function(){
	 //点击预定
	 var user_id = "${userFront.user_id}";
	 var num = /^\d+$/;
	 $("#addCard").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 if($("#house_id").val()=="0"){
			 alert('请选择房间类型');
			 return;
		 }
		 if(!num.exec($("#hotel_count").val())){
			 alert('预订数量必须为数字');
			 return;
		 }
		 $("#info").submit();
	 });
	 
	 //选择类型
	 $("input[id^='selColor-']").bind('click',function(){
		 $("input[id^='selColor-']").css("border","1px solid #dbcdbd");
		 $(this).css("border","2px solid #F04844");
		 var color = $(this).attr('id').split('-')[1];
		 $("#house_id").val(color);
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="hotel" /></jsp:include>
<div id="picnews2"></div>
<div id="middleBg">
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="title">${hotel.hotel_title}</div>
					<div class="typehr"></div>
					<div class="pictext">
							<div class="pic"><img src="images/infos/${hotel.hotel_pic}" width="250px" height="250px"/></div>
							<div class="text">
									<form name="info" id="info" action="page_addOrdershShow.action" method="post">
									<input type="hidden" name="house_id" id="house_id" value="0"/>
									<div class="textTop" style="height:125px;line-height:40px;">
											酒店名称：<span style="color:black">${hotel.hotel_title}</span>
											<br/>酒店地址：<span style="color:black">${hotel.hotel_address}</span>
											<br/>联系电话：<span style="color:black">${hotel.hotel_phone}</span>
									</div>
									<div class="textBtn" style="height:140px;line-height:35px;">
										选择房间：
										<c:forEach items="${houses}" var="house" varStatus="status">
										<input type="button" id="selColor-${house.house_id}" value="${house.house_title}￥${house.house_price}" style="cursor:pointer;width:180px;height:30px;border:1px solid #dbcdbd;background-color:#fcfcfc" />
										</c:forEach>
										<br/>预订数量：<input type="text" id="hotel_count" name="house_count" value="1" style="width:80px"/>
										<br/><input id="addCard" type="button"  class="btnstyle" value="点击预订" style="height:35px!important;width:120px"/>
									</div>
									</form>
							</div>
					</div>
					<div class="typehr"></div>
					<div class="title">酒店详情介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						${hotel.hotel_contentShow}
					</div>
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
</script>
</body>
</html>