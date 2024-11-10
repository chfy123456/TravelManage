<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user!=null && user.user_id!=0?'编辑':'添加'}系统用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var user_sex = "${user.user_sex}";
	 if(user_sex!=''){
		 $("#sex"+user_sex).attr('checked','checked');
	 }else{
		 $("#sex1").attr('checked','checked');
	 }
	 
	 var num = /^\d+$/;
	 var email=/^[\w]+[@]\w+[.][\w]+$/;
	 var Phone=/^\d{11}$/;
 	 var user_mail = $("#user_mail");
 	 var user_phone = $("#user_phone");
	 $("#addBtn").bind('click',function(){
		if($("#user_name").val()==''){
			alert('用户名不能为空');
			return;
		}
		if($("#user_pass").val()==''){
			alert('密码不能为空');
			return;
		}
		if($("#real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if(user_mail.val()!='' && !email.exec(user_mail.val())){
			alert("邮箱格式不正确");
			return;
		}
		if(user_phone.val()!='' && !Phone.exec(user_phone.val())){
			alert("联系电话必须为11位数字");
			return;
		}
		$("#user_id").val(0);
		$("#info").attr('action','Admin_addManager.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if(user_mail.val()!='' && !email.exec(user_mail.val())){
			alert("邮箱格式不正确");
			return;
		}
		if(user_phone.val()!='' && !Phone.exec(user_phone.val())){
			alert("联系电话必须为11位数字");
			return;
		}
		$("#info").attr('action','Admin_saveManager.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统用户管理&gt;&gt;${user!=null && user.user_id!=0?'编辑':'添加'}${user_typeDesc}</span>
</div>
<form id="info" name="info" action="Admin_addUser.action" method="post">   
<input type="hidden"  id="user_id" name="user_id" value="${user.user_id}" /> 
<input type="hidden"  id="user_type" name="user_type" value="${user_type}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${user!=null && user.user_id!=0?'编辑':'添加'}${user_typeDesc}</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font> 用户名：</td>
          <td width="35%" >
          	<c:if test="${user!=null && user.user_id!=0}">${user.user_name}</c:if>
          	<c:if test="${user==null || user.user_id==0}"><input type="text" name="user_name" id="user_name" value="${user.user_name }"/> </c:if>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密码：</td>
          <td>
            <c:if test="${user!=null && user.user_id!=0}">
          	<input type="password" name="user_pass" id="user_pass" value="" />
          	</c:if>
          	<c:if test="${user==null || user.user_id==0}">
          	<input type="password" name="user_pass" id="user_pass" value="111111" />
          	<span id="passTip" style="color:red;">初始密码：111111</span>
          	</c:if>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 姓名：</td>
          <td>
            <input type="text"  name="real_name" id="real_name" value="${user.real_name}"/>
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 性别：</td>
          <td>
            <input type="radio" id="sex1" name="user_sex" value="1"/>男&nbsp;&nbsp;
            <input type="radio" id="sex2" name="user_sex" value="2"/>女
          </td>
        </tr>   
        <tr>
          <td align="right" style="padding-right:5px">邮箱：</td>
          <td>
            <input type="text" name="user_mail" id="user_mail" value="${user.user_mail}"/>
          </td> 
          <td align="right" style="padding-right:5px">电话：</td>
          <td>
          	<input type="text" name="user_phone" id="user_phone" value="${user.user_phone}"/>
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<c:if test="${user!=null && user.user_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${user==null || user.user_id==0}">
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</c:if>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>