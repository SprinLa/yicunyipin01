<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>一村一品注册用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="Shortcut Icon" href="images/favicon.ico">
	<link href="${pageContext.request.contextPath}/css/regis.css" type="text/css" rel="stylesheet"/>
	
  </head>
  <body class="body1">
	<script type="text/javascript" src="js/jquery-1.9.js"></script>
	<!--  <script type="text/javascript" src="js/Abandon.js"></script>
	<script type="text/javascript">
		function getOs() 
		{ 
		    var OsObject = ""; 
		   if(navigator.userAgent.indexOf("MSIE")>0) { 
		        return "MSIE";
		   } 
		   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
		        return "Firefox"; 
		   } 
		   if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
		        return "Safari"; 
		   }  
		   if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
		        return "Camino"; 
		   } 
		   if(isMozilla=navigator.userAgent.indexOf("Gecko")>0){ 
		        return "Gecko"; 
		   } 
		} 
		if(getOs() == "MSIE"){
			document.write("<link rel='stylesheet' type='text/css' href='css/register/IERegister.css'>");
		}
		if(getOs() == "Firefox"){
			document.write("<link rel='stylesheet' href='css/register/FFRegister.css' type='text/css'>");
		}
		if(getOs() == "Safari"){
			document.write("<link rel='stylesheet' href='css/register/GGRegister.css' type='text/css'>");
		}
		if(getOs() == "Camino"){
			alert("Camino");
		}
		if(getOs() == "Gecko"){
			alert("Gecko");
		}
	</script>
 -->
	 <%@include file="../top.jsp" %>
	 
	 
	 <div style="margin: 0 auto; width: 1200px;height:700px; text-align: center;border: 1px solid #54ADE7;">
	
	
	<div class="left-pic">
	     
	     <a href="#"><img src="${pageContext.request.contextPath}/images/leftpic.jpg" border="0"></a>
		 
	</div>
	<!-- 注册信息 -->
	<div class="main">
		<div class="main_1"><p></p></div>
		<div class="main_2">
		<script type="text/javascript" src="js/jquery-1.9.js"></script>
		<script type="text/javascript" src="js/registerJS/Shift.js"></script>
		<script type="text/javascript" src="js/registerJS/Code.js"></script>
		
		<script type="text/javascript" src="js/registerJS/common.js"></script>
			<form action="user!register.action" method="post" id = "register" onsubmit="return xyj()">
				<table class="main_2_1" id="main1">
					
					
					<tr>
						<td class="main_2_1_1"><label>邮箱：</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="text" maxlength="25" onblur="txtEmail()" name="user.email" id="email" /></div>
							<div class="main_2_3_1" id="txtUserEmail" ></div>
						</td>
					</tr>
					
					<tr>
						<td class="main_2_1_1"><label>用户名：</label></td>
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
						<td class="main_2_1_1"><label>确认密码：</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="password" maxlength="16" onblur="txtPass()" name="userPass"  id="UserPass" /></div>
							<div class="main_2_3_1"  id="txtUserPass" ></div>
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
								<input type="checkbox" checked="false" id="check"/>我已认真阅读并同意<a href="#">《一村一品服务条款》</a>
							</div>
							
							<div class="main_2_2_2_2"  id="txtCheck" ></div>
						</td>
					</tr>
					<tr>
						<td class="main_2_1_1" colspan="2">
							<div class="main_2_2_1">
								<input id = "subRegister" type="submit" value="立即注册"/>
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
