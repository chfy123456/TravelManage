<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>留言板块</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
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
	 <div class="nav">帖子标题：${sblog.sblog_title } </div>
	 <!-- 信息开始 -->
	 <div class="messages">
	 	<div class="messages_top">
			<div class="nickName" style="color:orange">${sblog.user_name}</div>
			<div class="time">
				<img src="images/time.gif" valign="middle"/> 
				${fn:substring(sblog.sblog_date,0,19)}&nbsp;&nbsp;
				<img src="images/click2.gif" valign="middle"/>&nbsp;&nbsp;[浏览${sblog.sblog_click}]&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="images/quote.gif" valign="middle"/>&nbsp;&nbsp;<a id="replyCount" href="#link">[回复${sblog.sblog_reply}]</a>
			</div>
		</div>
		<div class="messages_bottom">
			<div class="sblog_title">
				 ${sblog.sblog_contentShow}
			</div>
		</div>
	 </div>
	 <c:if test="${sblogReplys!=null &&  fn:length(sblogReplys)>0}">
   	 <c:forEach items="${sblogReplys}" var="sblogReply" varStatus="status">
	 <div class="messages">
	 	<div class="messages_top">
			<div class="nickName">${sblogReply.user_name}</div>
			<div class="time">
				<img src="images/time2.gif" valign="middle"/>&nbsp;&nbsp; ${fn:substring(sblogReply.reply_date,0,19)}
			</div>
		</div>
		<div class="messages_bottom">
			<div class="sblog_title">
				${sblogReply.reply_contentShow}
			</div>
		</div>
	 </div>
	 </c:forEach>
	 </c:if>
	<!-- 信息结束 -->

	 <jsp:include page="page.jsp"></jsp:include>

	 <!-- 帖子回复 -->
	 <div id="addSblogReply" style="display:block">
	 <form name="info" id="info" action="page_addSblogReply.action" method="post">
	 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
	 <input type="hidden" name="sblog_id" id="sblog_id" value="${sblog.sblog_id}"/>
	 <table class="reply_add">
		<tr>
			<td class="theme" colspan="2">回复帖子：</td>
		</tr>
		<tr>
			<td align="left" colspan="2" style="padding-left:10px">
				<textarea name="reply_content" id="noticeContent" style="width:600px;height:150px" class="inputstyle"></textarea>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2" style="padding-left:10px">
				<img src="Random.jsp" width="50" valign="middle" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
				<input type="text" id="random" name="random" style="width:80px;" class="inputstyle"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" id="resetBtn" class="btnstyle" value="清空"/>
			</td>
		</tr>
	 </table>
	 </form>
	 </div>
	 <a name="link"></a>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
var user_id = "${userFront.user_id}";
var user_id2 = "${sblog.user_id}";
var sblog_id = "${sblog.sblog_id}";
//实现验证码点击刷新
function reloadcode(){
	var verify=document.getElementById('safecode');
	verify.setAttribute('src','Random.jsp?'+Math.random());
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
  window.location.href="page_querySblog.action?pageNo="+pagenum+"&sblog_id="+sblog_id;
}
function ChangePage(pagenum)
{
	window.location.href="page_querySblog.action?pageNo="+pagenum+"&sblog_id="+sblog_id;
}
 
$(document).ready(function(){
	$("#addBtn").bind("click",function(){
		editor.sync();
		if(user_id==''){
			alert('请先登录后在进行回复！')
			return;
		}
		if($("#noticeContent").val()==''){
			alert('回复内容不能为空！')
			return;
		}
		if($("#random").val()==''){
			alert('验证码不能为空！')
			return;
		}
		
		var aQuery = $("#info").serialize();  
		$.post('page_addSblogReply.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('回复成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('回复失败：'+responseObj.err.msg);
					}else{
						 alert('回复失败：服务器异常！');
					}	
		},'json');
	});
	
});

KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink'
		],
		uploadJson : 'admin/editor2/jsp/upload_json.jsp',
        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
});
</script>
</body>
</html>