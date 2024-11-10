<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${house!=null && house.house_id!=0?'编辑':'添加'}酒店信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+$/;
	 var num2 = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#house_title").val()==''){
			alert('房间类型不能为空');
			return;
		}
		if(!num2.exec($("#house_price").val())){
			alert('房间价格必须为数字');
			return;
		}
		$("#house_id").val(0);
		$("#info").attr('action','Admin_addHouse.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		if($("#house_title").val()==''){
			alert('房间类型不能为空');
			return;
		}
		if(!num2.exec($("#house_price").val())){
			alert('房间价格必须为数字');
			return;
		}
		$("#info").attr('action','Admin_saveHouse.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">酒店房间管理&gt;&gt;${house!=null && house.house_id!=0?'编辑':'添加'}酒店房间</span>
</div>
<form id="info" name="info" action="Admin_addHouse.action" method="post">   
<input type="hidden" id="house_id" name="house_id" value="${house.house_id}" /> 
<input type="hidden" name="hotel_id" id="hotel_id" value="${hotel.hotel_id}"/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${house!=null && house.house_id!=0?'编辑':'添加'}酒店房间</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 房间类型：</td>
          <td>
          	<input type="text" name="house_title" id="house_title" value="${house.house_title}" style="width:300px"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 房间价格：</td>
          <td>
          	<input type="text" name="house_price" id="house_price" value="${house.house_price}"/> 元/天
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
            <c:if test="${house!=null && house.house_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${house==null || house.house_id==0}">
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
<script>        
KindEditor.ready(function(K) {
	window.editor1 = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink', '|', 'image'
		],
		uploadJson : 'editor2/jsp/upload_json.jsp',
        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
        allowFileManager : true
	});
});
</script>
</body>
</html>