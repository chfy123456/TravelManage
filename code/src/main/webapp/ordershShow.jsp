<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>我的订单信息</title>
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
			<div class="title">个人中心  &gt;&gt;  我的酒店预订</div>
			<div style="margin-top:5px;overflow:auto;">
				 <form id="info" name="info" action="page_listMyOrdershs.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;width:1390px">
				 	<tr>
						<td colspan="15" align="left">
							订单编号：
							<input type="text" id="ordersh_no" name="ordersh_no" style="" value="${paramsOrdersh.ordersh_no}" class="inputstyle"/>&nbsp;
							酒店名称：
      						<input type="text" id="hotel_title" name="hotel_title" style="" value="${paramsOrdersh.hotel_title}" class="inputstyle"/>&nbsp;
      					    <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
						</td>
					</tr>
					<tr class="head_text">
					     <td width="100" align="center">操作</td>
     					 <td width="150" align="center">订单编号</td>
     					 <td width="100" align="center">用户</td>
     					 <td width="100" align="center">电话</td>
					     <td width="150" align="center">酒店名称</td>
					     <td width="150" align="center">房间类型</td>
     					 <td width="100" align="center">价格</td>
     					 <td width="100" align="center">数量</td>
     					 <td width="100" align="center">总额</td>
     					 <td width="120" align="center">入住时间</td>
     					 <td width="120" align="center">下单时间</td>
     					 <td width="100" align="center">状态</td>
					</tr>
					  <c:if test="${ordershs!=null &&  fn:length(ordershs)>0}">
   					  <c:forEach items="${ordershs}" var="ordersh" varStatus="status">
   					  <td width="" align="center" style="pot-height:22px">&nbsp;
					     	<c:if test="${ordersh.ordersh_flag==1 && userFront.user_type==1}">
					     	<a id="delOrdersh_${ordersh.ordersh_id}" href="javascript:void(0)">取消</a>
					     	</c:if>
					   </td>
   					   <td width="" align="center">${ordersh.ordersh_no}</td>  
   					   <td width="" align="center">${ordersh.real_name}</td>  
   					   <td width="" align="center">${ordersh.user_phone}</td>  
				       <td width="" align="center">${ordersh.hotel_title}</td>
				       <td width="" align="center">${ordersh.house_title}</td>
   					   <td width="" align="center">${ordersh.house_price}</td>  
   					   <td width="" align="center">${ordersh.house_count}</td>  
   					   <td width="" align="center">${ordersh.ordersh_money}</td>  
     					 <td width="" align="center">${ordersh.come_date}</td>  
     					 <td width="" align="center">${ordersh.ordersh_date}</td>  
     					 <td width="" align="center">${ordersh.ordersh_flagDesc}</td>  
					   </tr> 
					   </c:forEach>
					   </c:if>
					   <c:if test="${ordershs==null || fn:length(ordershs)==0}">
					   <tr>
					     <td height="60" colspan="12" align="center"><b>&lt;不存在我的酒店预订信息&gt;</b></td>
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
	   document.info.action="page_listMyOrdershs.action";
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
	   document.info.action="page_listMyOrdershs.action";
	   document.info.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo").value=pagenum;
		document.info.action="page_listMyOrdershs.action";
		document.info.submit();
	}
	
	$(document).ready(function(){
		$("a[id^='delOrdersh']").bind('click',function(){
			var ordersh_id = $(this).attr("id").split("_")[1];
			var aQuery = {
				'ids':ordersh_id  
			}
			$.post('page_delOrdershs.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('取消成功！');
							 window.location.reload();
						}else  if(responseObj.err.msg){
							 alert('失败：'+responseObj.err.msg);
						}else{
							 alert('失败：服务器异常！');
						}	
			},'json');
		 });

		
	});
	 
</script>
</body>
</html>