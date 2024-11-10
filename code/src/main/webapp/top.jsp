<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div id="navMenu">
	<ul>
	  <li style="margin-right:65px;margin-left:30px"><img src="images/logo.png"></li>
	  <li class="${param.menu==null || param.menu=='home'?'hover':''}"><a href="page_index.action">首页</a></li>
	  <li class="${param.menu=='line'?'hover':''}"><a href="page_listLines.action">线路攻略</a></li>
	  <li class="${param.menu=='info'?'hover':''}"><a href="page_listInfos.action">旅游新闻</a></li>
	  <li class="${param.menu=='pot'?'hover':''}"><a href="page_listPots.action">景点门票</a></li>
	  <c:if test="${userFront!=null }">
	  <li class="${param.menu=='potHobby'?'hover':''}"><a href="page_listPotsHobby.action">景点推荐</a></li>
	  </c:if>
	  <li class="${param.menu=='food'?'hover':''}"><a href="page_listFoods.action">当地美食</a></li>
	  <li class="${param.menu=='info2'?'hover':''}"><a href="page_listInfo2s.action">自然人文</a></li>
	  <li class="${param.menu=='hotel'?'hover':''}"><a href="page_listHotels.action">酒店预定</a></li>
	  <li class="${param.menu=='sblog'?'hover':''}"><a href="page_listSblogs.action">留言交流</a></li>
	  <c:if test="${userFront!=null }">
	  <li class="fright"><a id="loginOutTop" href="JavaScript:void(0)">退出</a></li>
	  <li class="fright ${param.menu=='self'?'hover':''}"><a href="page_myInfo.action">个人中心</a></li>
	  </c:if>
	  <c:if test="${userFront==null }">
	  <li class="fright ${param.menu=='reg'?'hover':''}"><a href="reg.jsp">注册</a></li>
	  <li class="fright ${param.menu=='login'?'hover':''}"><a href="login.jsp">登录</a></li>
	  </c:if>
	</ul>
</div>
<script type="text/javascript" src="js/login.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
	var EHeight = document.documentElement.clientHeight;
	var BHeight = document.body.clientHeight;
	var Height1 = Math.max(EHeight,BHeight);
	var ESHeight = document.documentElement.scrollHeight;
	var Height2 = Math.min(BHeight,ESHeight);
	var bottomM = Math.max(Height1 - Height2,5);
	$("#bottom").css("margin-top", bottomM);
});
</script>