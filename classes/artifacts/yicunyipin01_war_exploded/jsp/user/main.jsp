<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.yicunyipin.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>一村一品登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="Shortcut Icon" href="images/favicon.ico">
  </head>
  <body class="body1">
	<script type="text/javascript" src="js/jquery-1.9.js"></script>
	 <script type="text/javascript" src="js/Abandon.js"></script>
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

	
	<!-- 注册信息 -->
	<%-- <div class="main">
		<div
			style="float: left; width: 398px; height: 40px; line-height: 40px; text-align: left;">
			 <% User user=(User)session.getAttribute("user");
                if(user!=null){
                %>
			    <span>欢迎，<%=user.getUserName()%><a href="" title="个人信息中心" target="_blank"><font style="color: #060CFE; font-weight: bolder;"></font></a>！<a
				href="user!resetUser.action"><span style="color: #666666;">[登出]</span></a>&nbsp;&nbsp;
			   </span>
		       <%}else { %>
			   <a href="${pageContext.request.contextPath}/jsps/user/login.jsp"><span style="color: #666666;">[登录]</span></a>&nbsp;&nbsp;
			   <a href="${pageContext.request.contextPath}/jsps/user/register.jsp"><span style="color: #666666;">[注册]</span></a>
			   <%}%>
			&nbsp;&nbsp;|&nbsp;&nbsp; <span style="color: #666666;">QQ登录</span> <a
				href="#"><img src="${pageContext.request.contextPath}/image/icon-qq.png" border="0"></a> <span
				style="color:#666666;">微博登录</span> <a href="#"><img
				src="${pageContext.request.contextPath}/image/icon-weibo.png" border="0"></a>
		</div>
	</div> --%>
	
  </body>
</html>
