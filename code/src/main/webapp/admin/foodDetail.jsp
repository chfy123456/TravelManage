<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>当地美食信息详情</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">当地美食信息详情</span>
</div>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">当地美食信息详情</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td align="center" style="font-weight:bold;font-size:16px"> ${food.food_title} </td>
        </tr> 
         <tr>
          <td align="center" style="padding-right:5px">
          	发布人：${food.food_admin}　　　
          	发布时间：${food.food_date}
          </td>
        </tr> 
        <tr>
          <td align="center">
          	<img src="../images/infos/${food.food_pic}" style="max-width:500px" /> 
          </td>
        </tr> 
        <tr>
          <td colspan="2" style="food-height:22px;padding:5px">
          	<strong>美食介绍：</strong>
          	<br/>${food.food_contentShow}
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
          	<input type="button" id="editBtn" Class="btnstyle" value="返 回" onclick="window.history.back(-1);"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr> 
</table>
</body>
</html>