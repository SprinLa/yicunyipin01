<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--  <%@include file="../main/head.jsp"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    
    <title>用户登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="Shortcut Icon" href="images/favicon.ico">
	<link href="${pageContext.request.contextPath}/css/login.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.9.js"></script>

  </head>
  <body class="body1">
  
	
	
	<div id="tag" class="wrap">
	<jsp:include page="../head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="../top.jsp"/>
</div>

<div id="navbar01" class="wrap">
	<jsp:include page="../navbar.jsp"/>
</div>
   
	<div id="main" class="wrap">
	
	
	
	<div class="left-pic">
	     
	     <a href="#"><img src="${pageContext.request.contextPath}/images/leftpic.jpg" border="0"></a>
		 
	</div>
	
	<div class="main" >
	
		<div class="main_1"style="font-size: 23px;width:450px;height: 84px;margin-top:5px;">用户登陆</div>
		<div class="main_2">
		<script type="text/javascript" src="js/jquery-1.9.js"></script>
		<script type="text/javascript" src="js/login/Shift.js"></script>
		<script type="text/javascript" src="js/login/Code.js"></script>
		
		<script type="text/javascript" src="js/login/common.js"></script>
			<form action="user!login.action" method="post" id = "register" onsubmit="return xyj()">
				<table class="main_2_1" id="main1">
					
					
					<tr>
						<td class="main_2_1_1"><label>用户名</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="text" maxlength="20" onblur="txtName()" name="user.userName" id="userName" /></div>
							<div class="main_2_3_1" id="txtUserName"></div>
						</td>
					</tr>
					
					<tr>
						<td class="main_2_1_1"><label>密码：</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="password" maxlength="16" onblur="txtPwd()" name="user.password" id="UserPwd"  /></div>
							<div class="main_2_3_1" id="txtUserPwd" ></div>
						</td>
					</tr>
					
					<tr>
						<td class="main_2_1_1"><label>验证码：</label></td>
						<td class="main_2_1_2">
							<div class="main_2_2_3"><input type="text" maxlength="4" id="inputCode" onblur="txtCode()" /></div>
							<div class="main_2_3_3"  onclick="createCode()" id="checkCode"></div>
							<div class="main_2_3_4" ><a  onclick="createCode()" id="qq" title="看不清楚了吧，点击这里换一张验证码">换一张</a></div>
							<div class="main_2_3_1"  id="txtCode" ></div>
						</td>
					</tr>
					
					
					
					<tr>
						<td class="main_2_2_2" colspan="2">
							<div class="main_2_2_2_1">
								<input type="checkbox" checked="false" id="check"/>记住密码
							</div>
							
							<div class="main_2_2_2_2">
							     <input type="checkbox" checked="false" id="check"/>安全登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">忘记密码</a>
						
							</div>
						</td>
					</tr>
					
					
					
					<tr >
						<td  colspan="2">
							<div class="main_2_2_1" >
								<input id = "subRegister" type="submit" value="登&nbsp;&nbsp;陆"/>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</div>
	
  </body>
</html>
