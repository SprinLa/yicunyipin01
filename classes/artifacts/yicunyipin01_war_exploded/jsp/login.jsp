<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.9.js"></script>
<script type="text/javascript" src="js/login/Shift.js"></script>
<script type="text/javascript" src="js/login/Code.js"></script>
<script type="text/javascript" src="js/login/common.js"></script>




 <base href="<%=basePath%>">
<style type="text/css">

.main_2{float:right;height:500px;margin-right:30px;border:1px solid #999999;}
.main_2_1{width: 600px;height:300px;margin-top:20px;}
.main_2_1_1{width:100px;}
.main_2_1_1 label{float:left;margin-left:10px;color: black;font-size: 13px;}


.main_2_1_2{width:553px;}

.main_2_1_2_1{float:left;width:280px;height: 39px;font-size: 15px;background:#F0EEF0;border:1px solid #999999;}
.main_2_1_2_1 input{width: 279px;height: 38px;border: 0px;background:#F0EEF0;outline:none;}
.main_2_3_1{width: 228px;height: 40px;float: left;color:#C3C9CA;font-size: 12px;line-height: 40px;text-align:left;}
.main_2_2_1_ps{margin-left:87px;}


.main_2_1_2 a{display:inline-block;text-decoration: none;color: #7B7B7B;font-size: 12px;}
.main_2_1_2 a:hover{color: #41BBEC;}


.main_2_2_1 input{margin-left:87px;font-size:22px;width:282px;height: 40px;cursor:pointer;background:#3CB371;border: 0px;}
.main_2_2_2{font-size: 12px;height:30px;}
.main_2_2_2 a{color: #66B3FF;text-decoration: none;}
.main_2_2_2_1{height:40px;float: left;margin-left:85px;}
.main_2_2_2_2{width:300px;height:40px;}
.main_2_2_3{width: 135px;height: 40px;background:#F0EEF0;border:1px solid #999999;float: left;}
.main_2_2_3 input{width: 135px;height: 39px;border: 0px;background:#F0EEF0;outline:none; }
.code1{background: url("./images/splice1.png") no-repeat 0px 7px;color: red;padding-left:15px;margin-left: 5px;float:left;margin-top:0px;}
.code2{background: url("./images/splice.png") no-repeat 0px 9px;color: #00C632;padding-left:15px;margin-left: 5px;float:left;margin-top:0px;}




</style>


</head>
<body>

<div id="tag" class="wrap">
	<jsp:include page="../common/head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="../common/top.jsp"/>
</div>

<div id="navbar01" class="wrap">
	<jsp:include page="../common/navbar.jsp"/>
</div>

<div id="main" class="wrap">
	<div class="lefter">
	     
	     <a href="#"><img src="${pageContext.request.contextPath}/images/leftpic.jpg" border="0"></a>
		 
	</div>
  <div class="main">
	
	
		
		<div class="main_2">
		<script type="text/javascript" src="js/jquery-1.9.js"></script>
		<script type="text/javascript" src="js/login/Shift.js"></script>
		<script type="text/javascript" src="js/login/Code.js"></script>
		
		<script type="text/javascript" src="js/login/common.js"></script>
			<form action="tbuser_login.action" method="post" id = "register" onsubmit="return xyj()">
				<table class="main_2_1" id="main1">
					
					
					<tr>
						<td class="main_2_1_1"><label>用户名:</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="text" value="libing1" maxlength="20" onblur="txtName()" name="tbUser.userName" id="userName" /></div>
							<div class="main_2_3_1" id="txtUserName"></div>
						</td>
					</tr>
					
					<tr>
						<td class="main_2_1_1"><label>密   码：</label></td>
						<td class="main_2_1_2">
							<div class="main_2_1_2_1"><input type="password" value="libing1" maxlength="16" onblur="txtPwd()" name="tbUser.password" id="UserPwd"  /></div>
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
						<td class="" colspan="2">
						<font id="error"  color="red">${error }</font>
							<div class="main_2_2_1_ps">
								<input type="checkbox" checked="false" id="check"/>记住密码
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


	
	





	<div class="spacer clear"></div>

 
		 




<div>


 
 
  
			<div class="spacer clear"></div>
			

		<div class="clear"></div>
</div>
</div>
</div>
<div id="footer">
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>