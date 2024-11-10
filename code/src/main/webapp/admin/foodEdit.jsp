<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${food!=null && food.food_id!=0?'编辑':'添加'}美食信息</title>
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
		editor1.sync();
		if($("#food_title").val()==''){
			alert('美食名称不能为空');
			return;
		}
		if($("#travel_pic").val()==''){
			alert('美食图片不能为空');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('内容简介不能为空');
			return;
		}
		if($("#food_admin").val()==''){
			alert("发布人不能为空！");
			return;
		}
		$("#food_id").val(0);
		$("#info").attr('action','Admin_addFood.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor1.sync();
		if($("#food_title").val()==''){
			alert('美食名称不能为空');
			return;
		}
		if($("#travel_pic").val()==''){
			alert('美食图片不能为空');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('内容简介不能为空');
			return;
		}
		if($("#food_admin").val()==''){
			alert("发布人不能为空！");
			return;
		}
		$("#info").attr('action','Admin_saveFood.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">当地美食管理&gt;&gt;${food!=null && food.food_id!=0?'编辑':'添加'}当地美食</span>
</div>
<form id="info" name="info" action="Admin_addFood.action" method="post">   
<input type="hidden" id="food_id" name="food_id" value="${food.food_id}" /> 
<input type="hidden" id="travel_pic" name="food_pic" value="${food.food_pic}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${food!=null && food.food_id!=0?'编辑':'添加'}当地美食</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 美食名称：</td>
          <td>
          	<input type="text" name="food_title" id="food_title" value="${food.food_title}" style="width:300px"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 美食图片：</td>
          <td>
          	<img id="userImg" src="../images/infos/${food.food_pic}" width="120" height="100" style="border:0px;"/>
	        &nbsp;<span id="op" style="display:none"><img src="images/loading004.gif"  height="80" /></span>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"></td>
          <td>
          	<iframe name="uploadPage" src="uploadImg2.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>
          </td> 
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 美食介绍：</td>
          <td>
          	<textarea style="width:500px;height:300px" name="food_content" id="noticeContent">${food.food_content}</textarea>
          </td>
        </tr>
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 发布人：</td>
          <td>
            <input type="text" Style="width:200px" name="food_admin" id="food_admin" value="${food.food_admin}"/> 
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
            <c:if test="${food!=null && food.food_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${food==null || food.food_id==0}">
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