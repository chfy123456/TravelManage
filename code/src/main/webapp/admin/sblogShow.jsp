<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言交流信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
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


function serch()
{
   document.info.action="Admin_listSblogs.action";
   document.getElementById("pageNo").value=1;
   document.info.submit();
}
function del()
{
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
       alert("请至少选择一个要删除的用户留言！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delSblogs.action?ids="+ids;
       document.info.submit();
    }
    
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
  document.info.action="Admin_listSblogs.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listSblogs.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">留言交流管理&gt;&gt;留言交流查询</span>
</div>
<form name="info" id="info" action="Admin_listSblogs.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%"  class="table_top" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="">留言交流列表</td>
    <td width="" align="right">
            帖子名称：
      <input type="text" id="sblog_title" name="sblog_title" 
      		value="${paramsSblog.sblog_title}" class="inputstyle" style="width:100px;"/>&nbsp;
            发帖用户：
      <input type="text" id="user_name" name="user_name" 
      		value="${paramsSblog.user_name}" class="inputstyle" style="width:100px;"/>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">帖子标题</td>
     <td width="" align="center">发帖用户</td>
     <td width="" align="center">发帖时间</td>
     <td width="" align="center">浏览数</td>
     <td width="" align="center">回复数</td>
   </tr>
   <c:if test="${sblogs!=null &&  fn:length(sblogs)>0}">
   <c:forEach items="${sblogs}" var="sblog" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${sblog.sblog_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsSblog.start}</td>
     <td width="" align="center">${sblog.sblog_title}</td>
     <td width="" align="center">${sblog.user_name}</td>
     <td width="" align="center">${fn:substring(sblog.sblog_date,0,19)}&nbsp;</td>
     <td width="" align="center">${sblog.sblog_click}&nbsp;</td>
     <td width="" align="center">${sblog.sblog_reply}&nbsp;</td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${sblogs==null ||  fn:length(sblogs)==0}">
   <tr>
     <td height="60" colspan="12" align="center"><b>&lt;不存在用户留言信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>