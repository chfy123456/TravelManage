<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户订单信息</title>
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
   document.info.action="Admin_listOrderss.action";
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
       alert("请至少选择一个要删除的用户订单！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delOrderss.action?ids="+ids;
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
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listOrderss.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">用户订单管理&gt;&gt;用户订单查询</span>
</div>
<form name="info" id="info" action="Admin_listOrderss.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">用户订单列表</td>
    <td width="" align="right">
		订单编号：
		<input type="text" id="orders_no" name="orders_no" style="" value="${paramsOrders.orders_no}" class="inputstyle"/>&nbsp;
		用户：
		<input type="text" id="real_name" name="real_name" style="" value="${paramsOrders.real_name}" class="inputstyle"/>&nbsp;
		景点名称：
		<input type="text" id="pot_title" name="pot_title" style="" value="${paramsOrders.pot_title}" class="inputstyle"/>&nbsp;
		<input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
        <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">订单编号</td>
     <td width="" align="center">用户</td>
	 <td width="" align="center">电话</td>
     <td width="200" align="center">景点名称</td>
	 <td width="" align="center">价格</td>
	 <td width="" align="center">数量</td>
	 <td width="" align="center">总额</td>
	 <td width="" align="center">下单时间</td>
	 <td width="" align="center">状态</td>
	 <td width="" align="center">操作</td>
   </tr>
   <c:if test="${orderss!=null &&  fn:length(orderss)>0}">
   <c:forEach items="${orderss}" var="orders" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${orders.orders_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsOrders.start}</td>
	 <td width="" align="center">${orders.orders_no}</td>  
     <td width="" align="center">${orders.real_name}</td>  
	 <td width="" align="center">${orders.user_phone}</td>  
	 <td width="" align="center">${orders.pot_title}</td>
	 <td width="" align="center">${orders.pot_price}</td>  
	 <td width="" align="center">${orders.pot_count}</td>  
	 <td width="" align="center">${orders.orders_money}</td>  
	 <td width="" align="center">${orders.orders_date}</td>  
	 <td width="" align="center">${orders.orders_flagDesc}</td>  
	 <td width="" align="center">
	    <c:if test="${orders.orders_flag==1}">
	 	<a href="Admin_finishOrders.action?orders_id=${orders.orders_id}">确认使用</a>
	 	</c:if>
	 </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${orderss==null ||  fn:length(orderss)==0}">
   <tr>
     <td height="60" colspan="16" align="center"><b>&lt;不存在用户订单信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>