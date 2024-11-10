<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>留言板块</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="sblog" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="list">
		 <div class="nav">
			 <div class="nav1">
			 	当前位置: 首页 > 留言交流 >  
			 </div>
			 <div class="nav2" style="margin-right:82px;">
			 	<c:if test="${userFront!=null }">
			 	<input type="button" style="font-size:16px;" value="+我要发帖" onclick="window.location.href='page_addSblogShow.action';" class="btnstyle"/>
			 	</c:if>
			 </div>
		 </div>
		 <div class="list_info">
			<ul>
				<c:forEach items="${sblogs}" var="sblog" varStatus="status">
				<li>
				    <div class="info_text">
					<a href="page_querySblog.action?sblog_id=${sblog.sblog_id}"  title="${sblog.sblog_title}"> 
						${sblog.sblog_title}
					</a>　
					</div>
					<div class="info_time">${fn:substring(sblog.sblog_date,0,19)}</div>
				</li>
				</c:forEach>
			</ul>
		 </div>
		 <jsp:include page="page.jsp"></jsp:include>
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
    var sblog_type='<s:property value="#attr.sblog_type"/>';
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listSblogs.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listSblogs.action?pageNo="+pagenum;
	}
</script>
</body>
</html>