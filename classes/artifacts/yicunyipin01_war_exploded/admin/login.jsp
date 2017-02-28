<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<style type="text/css">
.wrap { width:960px; margin:0 auto; }
#header { overflow:hidden; padding-top:10px; }
#header #logo { float:left; width:120px; text-align:center; }
#header .help { float:right; }
#header .help a { margin:0 5px; }
#header .help a.shopping { padding-left:18px; background:url(../images/bg.png) left -69px no-repeat; }
#header .navbar { padding-left:120px; margin-top:30px; }
#header .navbar li { float:left; display:inline; margin:0 3px; background:url(../images/bg.png) right -30px no-repeat; padding-right:20px; }
#header .navbar li a { display:block; word-spacing:5px; letter-spacing:5px; background:url(../images/bg.png) left -30px no-repeat; padding-left:20px; padding-top:4px; line-height:26px; font-size:14px; font-weight:bold; color:#105f4b; text-decoration:none; }
#header .navbar li.current { background-position:right top; padding-right:15px; }
#header .navbar li.current a { background-position:left top; color:#fff; padding-left:15px; }
#childNav { clear:both; height:30px; background:#fc7e31; overflow:hidden; }
#childNav ul { margin:9px; line-height:14px; }
#childNav ul li { float:left; display:inline; border-right:1px solid #dc6b04; border-left:1px solid #fba455; padding:0 9px; }
#childNav ul li a { color:#fff; text-decoration:none; }
#childNav ul li.first { border-left:0; }
#childNav ul li.last { border-right:0; }
#childNav .welcome { line-height:30px; text-align:right; color:#FFC; }



#register { clear:both; }
#register .shadow { width:599px; margin:100px auto; }
#register .shadow h1 { font-size:24px; background:#e6f5e8; line-height:54px; color:#4c4d4b; padding-left:20px; border-bottom:1px solid #eaeaea; margin-bottom:1px; }
#register .steps li { float:left; line-height:25px; background-color:#dbdbdb; width:292px; text-align:center; color:#999; }
#register .steps li em { float:right; display:inline-block; height:25px; background-image:url(../images/bg.png); background-repeat:no-repeat; background-position:-26px -90px; }
#register .steps li.finished { background:#ffdc9a; }
#register .steps li.finished em { width:24px; background-position:-51px -90px; }
#register .steps li.current,
#register .steps li.last-current { background:#ffdc9a; font-weight:bold; color:#663300; }
#register .steps li.current em { width:24px; background-position:0 -90px; }
#register .steps li.last em { width:19px; background-position:-96px -90px; }
#register .steps li.last-current em { width:19px; background-position:-75px -90px; }
#register form { clear:both; padding:20px 0; }
#register form table { width:100%; }
#register form table td { padding:10px 0; }
#register form table td.field { width:150px; text-align:right; font-size:14px; }
#register form table td input.text { border-left:1px solid #686868; border-top:1px solid #686868; border-bottom:1px solid #b6b6b6; border-right:1px solid #b6b6b6; padding:3px 4px; width:200px; }
#register form table td input.verycode { width:120px; }
#register form table td #veryCode { vertical-align:middle; width:70px; height:23px; margin-left:10px; }
#register form table td span { display:none; vertical-align:middle; margin-left:10px; padding-left:22px; padding-right:10px; background:url(../images/bg.png) -232px -63px no-repeat; }
#register form table td span.error { display:inline-block; border:1px solid #ff835a; background-color:#ffe8e0; }
#register form table td span.ok{display:inline-block;  background-color:#0F0; }
#register form table td span.focus{display:inline-block;  background-color:#9FC;}

#register form table .current { background:#edffd5; }
#register form label.ui-green { height:30px;}
#register form label.ui-green input { font-size:18px; font-weight:bold; line-height:32px; height:30px; padding:0 15px; }
#footer { clear:both; line-height:30px; text-align:center; margin-top:20px; background:#fafafa; color:#666; border-top:1px solid #e0e0e0; }

</style>


<script type="text/javascript">
	function checkForm(){
		 var userName=$("#userName").val();
		 var password=$("#password").val();
		 if(userName==""){
			 $("#error").html("用户名不能为空！");
			 return false;
		 }
		 if(password==""){
			 $("#error").html("密码不能为空！");
			 return false;
		 }
		 if(imageCode==""){
			 $("#error").html("验证码不能为空！");
			 return false;
		 }
		 return true;
	}
</script>
</head>
<body>
<div id="header" class="wrap">
<div id="logo"><img src="${pageContext.request.contextPath}/images/logo.gif" /></div>
</div>
<div id="childNav">

	<div class="wrap">
		<ul class="clearfix">
		
		</ul>
	</div>
</div>

<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>管理员登陆</h1>
			<form id="loginForm" method="post" action="${pageContext.request.contextPath}/admin_login.action" onsubmit="return checkForm()">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" id="userName" name="admin.userName"  value="${admin.userName }" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="password" name="admin.password"  value="${admin.password }"/><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input  class="text" style="width: 60px;margin-right: 10px;"
									type=text value="${imageCode }" name="imageCode" id="imageCode"><img
									onclick="javascript:loadimage();" title="换一张试试" name="randImage"
									id="randImage" src="${pageContext.request.contextPath}/image.jsp" width="60" height="20" border="1"
									align="absmiddle">					
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" name="user.status" value="2"/>
						</td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录" /></label><font id="error"  color="red">${error }</font></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2014 一寸一品交易网 All Rights Reserved.
		http://www.yicunyipin.com
</div>
</body>
</html>