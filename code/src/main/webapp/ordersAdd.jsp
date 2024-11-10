<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>景点门票预定</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reg.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="admin/My97DatePicker/WdatePicker.js"></script>
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="pot" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	<div id="list">
		 <div class="nav">当前位置: 主页 > 景点门票购买 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;height:450px" action="LoginRegSystem.action" method="post">
			 <input type="hidden" name="pot_id" id="pot_id" value="${pot.pot_id}"/>
			 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id }"/>
			 <input type="hidden" name="real_name" id="real_name" value="${userFront.real_name }"/>
			 <input type="hidden" name="pot_title" id="pot_title" value="${pot.pot_title}"/>
			 <input type="hidden" name="pot_price" id="pot_price" value="${pot.pot_price}"/>
			 <table class="regTable">
				<tr>
					<td class="theme" colspan="2">填写景点门票购买信息</td>
				</tr>
				<tr>
					<td align="right" width="20%">景点名称：</td>
					<td align="left" width="80%"> 
					   ${pot.pot_title}
					</td>
				</tr>
				<tr>
					<td align="right" width="20%">门票价格：</td>
					<td align="left" width="80%"> 
					   ￥${pot.pot_price}
					</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 购买数量：</td>
					<td align="left" width="80%"><input type="text" name="pot_count" id="pot_count" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 联系电话：</td>
					<td align="left" width="80%"><input type="text" name="user_phone" id="user_phone" style="width:200px;" class="inputstyle"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input type="button" id="addBtn" class="btnstyle" value="提 交"/>
					</td>
				</tr>
		 	 </table>
		 	 </form>
		 </div>
		 
	</div>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
}
$(document).ready(function(){
	var addBtn = $("#addBtn");
	var pot_count = $("#pot_count");
	var user_phone = $("#user_phone");
	var random = $("#random");
	
    var num= /^\d+$/;
    var Phone=/^\d{11}$/;
	addBtn.bind("click",function(){
		if(!num.exec(pot_count.val())){
			alert("购买数量必须为数字");
			return;
		}
		if(!Phone.exec(user_phone.val())){
			alert("联系电话必须为11位数字");
			return;
		}
		var aQuery = $("#info").serialize();  
		$.post('page_addOrders.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('提交成功！');
						 window.location.href="page_listMyOrderss.action";
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