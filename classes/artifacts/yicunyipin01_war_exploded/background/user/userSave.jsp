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

<title>用户管理</title>
</head>
<script type="text/javascript">
	
	
	
	
	function checkForm(){
		var trueName=document.getElementById("trueName").value;
		if (trueName==""){ 
			alert("会员名称不能为空！"); 
			document.getElementById("trueName").focus();
			return(false); 
			} 
		
		
		var name=document.getElementById("name").value;
		if (name==""){ 
			alert("商品标题不能为空！"); 
			document.getElementById("name").focus();
			return(false); 
			} 
	}
	
	
	
	
	
	
	
	function checkPhone(){
		var bool=true;
		var regCode = /^1[3|4|5|8][0-9]\d{4,8}$/;
		var q = document.getElementById("txtmobile");
		var name = $("#mobile").val();
		 if(!name) {
			 q.innerHTML = "<p class='code1'>不能为空!</p>";
				bool=false;
			 } 
		 else if(regCode.test(name) == false){
				q.innerHTML = "<p class='code1'>请输入格式正确的手机号</p>";
				bool=false;
			}
		 else{
			 q.innerHTML = "<p class='code2'>ͨ通过</p>";
			}
			return bool;
	}

	
	
	
	function txtName(){
		var bool=true;
		var regCode = /^[A-Z,a-z]+\w{3,19}$/;
		var q = document.getElementById("txtUserName");
		//var name = document.getElementById("UserName").value;
		var name = $("#trueName").val();
		 if(!name) {
			 q.innerHTML = "<p class='code1'>会员名不能为空!</p>";
				bool=false;
			 } else{
				
				$.ajax({
					cache: false,
					async: true,
					type: "POST",
				dataType: "json",
					data: {userName: name},
					url: "user_existUserWithTrueName.action",
				success: function(flage) {
						if(!flage) {
							q.innerHTML = "<p class='code2'>ͨ通过</p>";
							bool = false;				
						}else{
							q.innerHTML = "<p class='code1'>该会员名已经被注册!</p>";
						}
					}
		      });
				
				
			
			}
			return bool;
	}
	
	
	function checksp(){
		var bool=true;
		var q = document.getElementById("txtdentityName");
		var name = $("#dentityName").val();
		 if(!name) {
			 q.innerHTML = "<p class='code1'>会员名不能为空!</p>";
				bool=false;
			 } else{
				 q.innerHTML = "<p class='code2'>ͨ通过</p>";
			}
			return bool;
	}
	
</script>

<body>
	

<!-- content start -->
  <div class="admin-content">
  
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal information</small></div>
    </div>

    <hr/>

    <div class="am-g">
    
	   <div class="am-u-sm-1 am-u-md-2 am-u-md-push-4"></div>

       <div class="am-u-sm-18 am-u-md-15 am-u-md-pull-29">
       
          <form class="am-form am-form-horizontal"  action="${pageContext.request.contextPath}/user_userPreSave2.action?uId=${currentUser.id}" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
         
         
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">会员名称 </label>
            <div class="am-u-sm-9">
              <input id="trueName" name="user.trueName " onblur="txtName()" size="50" value="${currentUser.trueName }" type="text" placeholder="会员名称 / Name"disabled>
              <small id="txtUserName"></small>
            </div>
          </div>
           <hr/>
          
			<div class="am-form-group">
	          <label for="user-name" class="am-u-sm-3 am-form-label">会员类别 </label>
	          <div class="am-u-sm-9">
	            <div class="am-btn-group" data-am-button>
	              <label class="am-btn am-btn-default am-btn-xs">
	                <input type="radio" name="options" id="option1"> 企业
	              </label>
	              <label class="am-btn am-btn-default am-btn-xs">
	                <input type="radio" name="options" id="option2"> 个人
	              </label>
	            </div>
	          </div>
	        </div>
	         <hr/>
	        
                                
		  
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">厂商名称</label>
            <div class="am-u-sm-9">
              <input name="user.csName"  size="50" value="${currentUser.csName }" type="text" placeholder="输入产品厂商名称 "disabled>
              <small></small>
            </div>
          </div>

 <hr/>
          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">个人或企业法人 </label>
            <div class="am-u-sm-9">
              <input  name="user.dentityName" id="dentityName"  onblur="checksp()" value="${currentUser.dentityName}" type="text" placeholder="输入个人名称或企业法人的名称  "disabled>
              <small id="txtdentityName"></small>
			</div>
          </div>
          
            <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">个人身份证或企业工商营业执照扫描件：</label>
            <div class="am-u-sm-9">
              <input type="file" id="picFile" name="image">
                    <p class="am-form-help">请选择要上传的文件...</p>
			</div>
          </div>

    <hr/>                         

          <div class="am-form-group">
            <label for="user-QQ" class="am-u-sm-3 am-form-label">手机：</label>
            <div class="am-u-sm-9">
              <input id="mobile" name="user.mobile " onblur="checkPhone()" size="50" value="${currentUser.mobile}" type="text" placeholder="请输入有效手机号码"" disabled>
			  <small id="txtmobile"></small>
            </div>
          </div>
 <hr/>
          <div class="am-form-group">
            <label for="user-weibo" class="am-u-sm-3 am-form-label">固定电话：</label>
            <div class="am-u-sm-9">
              <input name="user.tel"  size="50" value="${currentUser.tel }" type="text" placeholder="有效电话号码" disabled>
			  <small></small>
            </div>
          </div>

		  <hr/> 
          <div class="am-form-group">
            <label for="user-intro" class="am-u-sm-3 am-form-label">详细地址</label>
            <div class="am-u-sm-9">
               <input  id="name" name="user.address " size="50" value="${currentUser.address }" type="text"  placeholder="输入详细地址 " disabled>
               <small></small>
            </div>
          </div>
			 <hr/>		
				  <div class="am-form-group">
            <label for="user-weibo" class="am-u-sm-3 am-form-label">用户邮箱（注册填写）：</label>
            <div class="am-u-sm-9">
              <input  name="user.email"  size="50" value="${currentUser.email }" type="text" placeholder="输入固定电话 "disabled>
            </div>
          </div>	
			 <hr/>		
						
				  <%-- <div class="am-form-group">
            <label for="user-weibo" class="am-u-sm-3 am-form-label">当前状态：</label>
            <div class="am-u-sm-9">
              <input  name="user.status"  size="50" value="${currentUser.status }" type="text" placeholder="输入固定电话 " disabled>
            </div>
          </div>	 --%>
					  <div class="am-form-group">
              <input  type="hidden" id="goodsId" name="user.password" value="${currentUser.password}"/>
          </div>	
			 <hr/>		
					
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <input id="btn" type="submit" class="am-btn am-btn-primary" value="修改">
            </div>
          </div>
        </form>
      </div>
      
    </div>
  </div>
  <!-- content end -->




</body>
</html>
