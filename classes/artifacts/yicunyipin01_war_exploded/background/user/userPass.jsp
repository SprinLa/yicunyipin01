<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>

<base href="<%=basePath%>">
<script type="text/javascript" src="ckeditor4.2/ckeditor.js"></script>
<script type="text/javascript" src="ckfinder2.3.1/ckfinder.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户密码修改</title>


<script type="text/javascript">
	function checkForm(){
		 var userName=$("#password").val();
		 var password1=$("#password1").val();
		 var password2=$("#password1").val();
		 if(userName==""){
			 $("#error").html("原密码不能为空！");
			 return false;
		 }
		 if(password1==""){
			 $("#error").html("密码不能为空！");
			 return false;
		 }
		 if(password1!=password2){
			 $("#error").html("两次密码不一致！");
			 return false;
		 }
		 return true;
	}
</script>


</head>


<body>
<div class="admin-content" align="center">
  
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">修改密码</strong> / <small>Modify Password</small></div>
    </div>

    <hr/>

    <div class="am-g">
    
	   <div class="am-u-sm-1 am-u-md-2 am-u-md-push-4"></div>

       <div class="am-u-sm-18 am-u-md-15 am-u-md-pull-29">
       <form action="tbuser_modifyPassWord.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
			<table cellpadding="5" width="100%" align="center">
				<tr>
					<td width="120px">
						<label>原密码：</label>	
					</td>
					<td>
						<input type="text" id="name" name="tbUser.password" class="input-xxlarge" value=""/>
					</td>
				</tr>
				
			   <tr>
					<td width="120px">
						<label>新密码：</label>
					</td>
					<td>
						<input type="text" id="name" name="password1" class="input-xxlarge" />
					</td>
				</tr>
				
				 <tr>
					<td width="120px">
						<label>再次密码：</label>
					</td>
					<td>
						<input type="text" id="name" name="password2" class="input-xxlarge"/>
					</td>
				</tr>
			 <tr>
				 <label><font id="error"  color="red">${error }</font></label>
			 </tr>
				<tr>
					<td>
						<input type="hidden" id="userId" name="user.id" value="${currentUser.id }"/>&nbsp;
					</td>
					<td>
						<input type="submit" class="btn btn-primary" value="更改"/>&nbsp;&nbsp;
						<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					</td>
				</tr>
				
			</table>
		</form>
          
      </div>
      
    </div>
  </div>






</body>
</html>
