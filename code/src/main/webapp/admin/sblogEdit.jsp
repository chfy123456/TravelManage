<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复留言交流信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num1 = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#editBtn").bind('click',function(){
		if($("#noticeContent1").val()==''){
			alert('回复内容不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveSblog.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">留言交流管理&gt;&gt;回复留言交流</span>
</div>
<form id="info" name="info" action="Admin_addSblog.action" method="post">   
<input type="hidden" id="sblog_id" name="sblog_id" value="${sblog.sblog_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">回复留言交流</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 发帖人：</td>
          <td colspan="3">
           ${sblog.real_name}
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 留言内容：</td>
          <td>
           ${sblog.sblog_content}
          </td>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 留言时间：</td>
          <td>
          ${sblog.sblog_date}
          </td>
		</tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 回复内容：</td>
          <td colspan="3">
            <textarea style="width:500px;height:100px" name="reply_content"  id="noticeContent1">${sblog.reply_content}</textarea>
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
           <input type="button" id="editBtn" Class="btnstyle" value="回 复"/> 
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