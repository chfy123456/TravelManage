<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>旅游新闻信息</title>
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
   document.info.action="Admin_listInfos.action";
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
       alert("请至少选择一个要删除的选项！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delInfos.action?ids="+ids;
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
  document.info.action="Admin_listInfos.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listInfos.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">旅游新闻管理&gt;&gt;旅游新闻查询</span>
</div>
<form name="info" id="info" action="Admin_listInfos.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%"  class="table_top" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="">旅游新闻列表</td>
    <td width="" align="right">
            标题：
      <input type="text" id="info_title" name="info_title" value="${paramsInfo.info_title}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">标题</td>
     <td width="" align="center">发布人</td>
     <td width="" align="center">发布时间</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${infos!=null &&  fn:length(infos)>0}">
   <c:forEach items="${infos}" var="info" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${info.info_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsInfo.start}</td>
     <td width="" align="center">${info.info_title}</td>
     <td width="" align="center">${info.info_admin}</td>
     <td width="" align="center">${fn:substring(info.info_date,0,19)}</td>  
     <td width="" align="center">
       <a href="Admin_queryInfo.action?info_id=${info.info_id}">查看</a>&nbsp;
       <a href="Admin_editInfo.action?info_id=${info.info_id}">编辑</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${infos==null ||  fn:length(infos)==0}">
   <tr>
     <td height="60" colspan="8" align="center"><b>&lt;不存在旅游新闻信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>