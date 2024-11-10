<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>${sblog!=null && sblog.sblog_id!=0?'编辑':'发布'}帖子信息</title>
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
<script charset="utf-8" src="admin/editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="admin/editor2/lang/zh-CN.js"></script>
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
<jsp:include page="top.jsp"><jsp:param name="menu" value="sblog" /></jsp:include>
<div id="picnews2"></div>
<div id="middle">
	 <!--  购物车 -->
	 <div id="product_info" style="width:100%">
			<div class="title">留言交流  &gt;&gt;  ${sblog!=null && sblog.sblog_id!=0?'编辑':'发布'}帖子信息</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_saveSblog.action" method="post">    
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id }"/>
				 <input type="hidden" name="sblog_id" id="sblog_id" value="${sblog.sblog_id}"/>
				 <table class="ptable" style="margin-bottom:5px;">
					<tr>
			          <td width="15%" align="right" style="padding-right:5px">帖子标题：</td>
			          <td width="*">
			          	<input type="text" style="width:575px;" name="sblog_title" id="sblog_title" value="${sblog.sblog_title}"/> 
			          </td>
			        </tr> 
			        <tr>
			          <td align="right" style="padding-right:5px">帖子内容：</td>
			          <td class="KEEdit">
			          	<textarea style="width:580px;height:300px" name="sblog_content" id="noticeContent">${sblog.sblog_content}</textarea> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="4">
			            <c:if test="${sblog!=null && sblog.sblog_id!=0}">
			          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
			          	</c:if>
			            <c:if test="${sblog==null || sblog.sblog_id==0}">
			          	<input type="button" id="addBtn" Class="btnstyle" value="发 布" />
			          	</c:if>
			          </td>
			        </tr>
				 </table>
				 </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		$("#addBtn").bind('click',function(){
			editor.sync();
			if($("#sblog_title").val()==''){
				alert('帖子标题不能为空');
				return;
			}
			if($("#noticeContent").val()==''){
				alert('帖子内容不能为空');
				return;
			}
			$("#sblog_id").val(0);
			var aQuery = $("#info").serialize();  
			$.post('page_addSblog.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('发布成功！');
							 window.location.href="page_listSblogs.action";
						}else  if(responseObj.err.msg){
							 alert('发布失败：'+responseObj.err.msg);
						}else{
							 alert('发布失败：服务器异常！');
						}	
			},'json');
		 });
		
		 $("#editBtn").bind('click',function(){
			 editor.sync();
			if($("#sblog_title").val()==''){
				alert('帖子标题不能为空');
				return;
			}
			if($("#noticeContent").val()==''){
				alert('帖子内容不能为空');
				return;
			}
			var aQuery = $("#info").serialize();  
			$.post('page_saveSblog.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMySblogs.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
	KindEditor.ready(function(K) {
		window.editor = K.create('#noticeContent',{
			width : '95%',
			items:[
				'fullscreen','|','undo', 'redo', '|', 'code', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'quickformat', 'selectall', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
				 'table', 'hr', 'emoticons', 'pagebreak','anchor', 'link', 'unlink'
			],
			uploadJson : 'admin/editor2/jsp/upload_json.jsp',
	        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
	        allowFileManager : true

		});
	});
</script>
</body>
</html>