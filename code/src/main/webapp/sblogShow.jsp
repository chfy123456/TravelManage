<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>我发布的帖子</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="self" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  我发布的帖子</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_listMyGoodss.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
						<td colspan="7" align="right">
							帖子标题：
      						<input type="text" id="sblog_title" name="sblog_title" style="width:100px"
      							value="${paramsSblog.sblog_title}" class="inputstyle"/>&nbsp;
      					    <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
							<input type="button" id="delBtn" value="删除" class="btnstyle"/>&nbsp;
						</td>
					</tr>
					<tr class="head_text">
						<td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
						<td align="center">帖子标题</td>
						<td align="center">发帖时间</td>
						<td align="center">浏览数</td>
						<td align="center">回复数</td>
						<td align="center">操作</td>
					</tr>
					<c:if test="${sblogs!=null &&  fn:length(sblogs)>0}">
   					<c:forEach items="${sblogs}" var="sblog" varStatus="status">
					<tr>
						 <td width="" align="center"><input type="checkbox" name="chkid" value="${sblog.sblog_id}" Style="vertical-align:text-bottom;"/></td>
						 <td width="" align="center" title="${sblog.sblog_title}">
						 	${fn:length(sblog.sblog_title)>14?fn:substring(sblog.sblog_title,0,13)+'...':sblog.sblog_title}
						 </td>
					     <td width="" align="center">${fn:substring(sblog.sblog_date,0,19)}</td>
					     <td width="" align="center">${sblog.sblog_click}</td>  
					     <td width="" align="center">${sblog.sblog_reply}</td>
					     <td width="" align="center">
					     	<a href="page_editSblog.action?sblog_id=${sblog.sblog_id}">编辑</a>
					     </td>
					</tr>
					</c:forEach>
				    </c:if>
				    <c:if test="${sblogs==null ||  fn:length(sblogs)==0}">
				    <tr>
				      <td height="60" colspan="7" align="center"><b>&lt;不存在发布的帖子信息&gt;</b></td>
				    </tr>
				    </c:if>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.info.action="page_listMySblogs.action";
	   document.getElementById("pageNo").value=1;
	   document.info.submit();
	}
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
	  document.info.action="page_listMySblogs.action";
	  document.info.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMySblogs.action";
		document.info.submit();
	}
	function CheckAll(obj) 
	{
		var checks=document.getElementsByName("chkid");
	    for (var i=0;i<checks.length;i++)
		{
		    var e = checks[i];
		    e.checked = obj.checked;
		}
	    
	}
	$(document).ready(function(){
		$("#delBtn").bind('click',function(){
			var checks=document.getElementsByName("chkid");
		    var ids="";
			for (var i=0;i<checks.length;i++)
		    {
		        var e = checks[i];
				if(e.checked==true)
				{
				  if(ids=="")
				  {
				    ids=ids+e.value;
				  }
				  else
				  {
				    ids=ids+","+e.value;
				  }
				}
		    }
		    if(ids=="")
		    {
		       alert("请至少选择一个要删除的帖子！");
		       return false;
		    }
		    if(confirm('确认删除吗!?'))
		    {
		    	var aQuery = {
						'ids':ids
				};  
				$.post('page_delSblogs.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.href="page_listMySblogs.action";
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>