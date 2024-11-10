<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<title>管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style type="text/css">
html,body{
	margin:0px;
	padding:0px;
	height:100%;
}
body{
	background-color:#0F59A4;
	color:white;
}
.left{width:190px; min-height:450px;max-height:600px;overflow-x:hidden;overflow-y:auto;}
div,td,a{ font-size:14px; color:white;font-family:Arial, Helvetica, sans-serif;}
#container {
	width: 190px;
}
#container img{
	height:20px;
}
H1 {
	margin: 0px;
	width: 190px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 190px;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 190px;
	height: 30px;
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	line-height: 30px;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 30px;
	width: 190px;
	padding-left: 0px;
}
.MM {
	width: 190px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
	background-color:#F2F3F5;
}
.MM a:link {
	color:#000;
	font-family: Arial, Helvetica, sans-serif;
	line-height: 30px;
	height: 30px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	color:#000;
	font-family: Arial, Helvetica, sans-serif;
	line-height: 30px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 30px;
	width: 190px;
	text-decoration: none;
}
.MM a:active {
	color:#000;
	font-family: Arial, Helvetica, sans-serif;
	line-height: 30px;
	height: 30px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	line-height: 30px;
	font-weight: bold;
	color: #006600;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 30px;
	width: 190px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="190" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
  	<td height="40" style="text-align:center; line-height:40px; color:white; border-bottom:1px solid #0E549F;font-weight:bold"><img src="images/navtop.png" height="20px" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;功能模块管理</td>
  </tr>
  <tr>
    <td width="190" valign="top">
    	<div class="left">
			 <table width="100%" height="280" border="0" cellpadding="0" cellspacing="0">
			  <tr>
				<td width="190" valign="top">
				<div id="container">
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;个人信息中心</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="modifyInfo.jsp" target="MainFrame">个人信息</a></li>
					  <li><a href="modifyPwd.jsp" target="MainFrame">修改密码</a></li>
					</ul>
				  </div>
				  
				  <c:if test="${admin.user_type==5}">
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;系统用户管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listManagers.action?user_type=2" target="MainFrame">网站管理员</a></li>
					  <li><a href="Admin_listManagers.action?user_type=3" target="MainFrame">酒店管理员</a></li>
					  <li><a href="Admin_listManagers.action?user_type=4" target="MainFrame">景点管理员</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;注册用户管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listUsers.action" target="MainFrame">注册用户查询</a></li>
					  <li><a href="Admin_addUserShow.action" target="MainFrame">新增注册用户</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;线路攻略管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listLines.action" target="MainFrame">线路攻略查询</a></li>
					  <li><a href="Admin_addLineShow.action" target="MainFrame">新增线路攻略</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;旅游新闻管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listInfos.action" target="MainFrame">旅游新闻查询</a></li>
					  <li><a href="Admin_addInfoShow.action" target="MainFrame">新增旅游新闻</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;景点门票管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listPots.action" target="MainFrame">景点门票查询</a></li>
					  <li><a href="Admin_addPotShow.action" target="MainFrame">新增景点门票</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;当地美食管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listFoods.action" target="MainFrame">当地美食查询</a></li>
					  <li><a href="Admin_addFoodShow.action" target="MainFrame">新增当地美食</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;自然人文景观</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listInfo2s.action" target="MainFrame">自然人文景观查询</a></li>
					  <li><a href="Admin_addInfo2Show.action" target="MainFrame">新增自然人文景观</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;门票订单管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listOrderss.action" target="MainFrame">门票订单查询</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;酒店信息管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listHotels.action" target="MainFrame">酒店信息查询</a></li>
					  <li><a href="Admin_addHotelShow.action" target="MainFrame">新增酒店信息</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;酒店预订管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listOrdershs.action" target="MainFrame">酒店预订查询</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;留言交流管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listSblogs.action" target="MainFrame">用户发帖查询</a></li>
					</ul>
				  </div>
				  </c:if>
				  
				  <c:if test="${admin.user_type==4}">
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;景点门票管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listPots.action" target="MainFrame">景点门票查询</a></li>
					  <li><a href="Admin_addPotShow.action" target="MainFrame">新增景点门票</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;门票订单管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listOrderss.action" target="MainFrame">门票订单查询</a></li>
					</ul>
				  </div>
				  </c:if>
				  
				  <c:if test="${admin.user_type==3}">
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;酒店信息管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listHotels.action" target="MainFrame">酒店信息查询</a></li>
					  <li><a href="Admin_addHotelShow.action" target="MainFrame">新增酒店信息</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;酒店预订管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listOrdershs.action" target="MainFrame">酒店预订查询</a></li>
					</ul>
				  </div>
				  </c:if>
				   
				  <c:if test="${admin.user_type==2}">
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;线路攻略管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listLines.action" target="MainFrame">线路攻略查询</a></li>
					  <li><a href="Admin_addLineShow.action" target="MainFrame">新增线路攻略</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;旅游新闻管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listInfos.action" target="MainFrame">旅游新闻查询</a></li>
					  <li><a href="Admin_addInfoShow.action" target="MainFrame">新增旅游新闻</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;当地美食管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listFoods.action" target="MainFrame">当地美食查询</a></li>
					  <li><a href="Admin_addFoodShow.action" target="MainFrame">新增当地美食</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;自然人文景观</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listInfo2s.action" target="MainFrame">自然人文景观查询</a></li>
					  <li><a href="Admin_addInfo2Show.action" target="MainFrame">新增自然人文景观</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)"><img src="images/navxh.png" style="border:0px;vertical-align:middle"/>&nbsp;&nbsp;留言交流管理</a></h1>
				  <div class="content">
					<ul class="MM">
					  <li><a href="Admin_listSblogs.action" target="MainFrame">留言交流查询</a></li>
					</ul>
				  </div>
				  </c:if>
				  
					<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');
					
						var myAccordion = new fx.Accordion(
							toggles, contents, {opacity: true, duration: 400}
						);
						myAccordion.showThisHideOpen(contents[0]);
					</script>
				</div>
				</td>
			  </tr>
			</table>       	
        </div>
    </td>
  </tr>
</table>
</body>
</html>
