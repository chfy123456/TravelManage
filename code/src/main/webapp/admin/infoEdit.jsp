<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${info!=null && info.info_id!=0?'编辑':'发布'}旅游新闻</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	function trimStr(str)     
	{     
	    if ((typeof(str) != "string") || !str)   
	    {    
	        return "";    
	    }   
	    return str.replace(/(^\s*)|(\s*$)/g, "");    
	}
	
	 
	$("#addBtn").bind('click',function(){
		editor.sync();
		if($("#info_title").val()==''){
			alert("标题不能为空！");
			return;
		}
		if($("#info_admin").val()==''){
			alert("发布人不能为空！");
			return;
		}
		if($("#noticeContent").val()==''){
			alert("内容不能为空！");
			return;
		}
		$("#info_id").val(0);
		$("#info").attr('action','Admin_addInfo.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 editor.sync();
		    if($("#info_title").val()==''){
				alert("标题不能为空！");
				return;
			}
		    if($("#info_admin").val()==''){
				alert("发布人不能为空！");
				return;
			}
			if($("#noticeContent").val()==''){
				alert("内容不能为空！");
				return;
			}
			$("#info").attr('action','Admin_saveInfo.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">旅游新闻管理&gt;&gt;${info!=null && info.info_id!=0?'编辑':'发布'}旅游新闻</span>
</div>
<form id="info" name="info" action="Admin_saveInfo.action" method="post">    
<input type="hidden" id="info_id" name="info_id" value="${info.info_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${info!=null && info.info_id!=0?'编辑':'发布'}旅游新闻</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="150" align="right" style="padding-right:5px"><font color="red">*</font> 标题：</td>
          <td width="*">
            <input type="text" style="width:495px;" name="info_title" id="info_title" value="${info.info_title}"/> 
          </td>  
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 发布人：</td>
          <td>
            <input type="text" Style="width:200px" name="info_admin" id="info_admin" value="${info.info_admin}"/> 
          </td>  
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 内容：</td>
          <td>
            <textarea style="width:500px;height:300px" name="info_content"  id="noticeContent">${info.info_content}</textarea>
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
            <c:if test="${info!=null && info.info_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${info==null || info.info_id==0}">
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
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink', '|', 'image'
		],
        allowFileManager : true
	});
});
	   
</script>
</body>
</html>