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
	
	
	var type=document.getElementById("type").value;
	if (type==""){ 
		alert("请选择类别！"); 
		return(false); 
		} 
	
	var csName=document.getElementById("csName").value;
	if (csName==""){ 
		alert("厂商名称不能为空！"); 
		document.getElementById("csName").focus();
		return(false); 
		} 
	
	
	var dentityName=document.getElementById("dentityName").value;
	if (dentityName==""){ 
		alert("个人或企业法人不能为空"); 
		return(false); 
		} 



	var picFile=document.getElementById("picFile").value;
	if (picFile==""){ 
		alert("请上传个人和企业证件扫描件！"); 
		return(false); 
		} 
	
	
	var mobile=document.getElementById("mobile").value;
	if (mobile==""){ 
		alert("手机不能为空"); 
		return(false); 
		} 
	
	var tel=document.getElementById("tel").value;
	if (tel==""){ 
		alert("电话不能为空"); 
		return(false); 
		} 
	var address=document.getElementById("address").value;
	if (address==""){ 
		alert("联系地址不能为空"); 
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员申请</strong> / <small>Personal information</small></div>
    </div>

    <hr/>

    <div class="am-g">
    
	   <div class="am-u-sm-1 am-u-md-2 am-u-md-push-4"></div>

       <div class="am-u-sm-18 am-u-md-15 am-u-md-pull-29">
       
          <form class="am-form am-form-horizontal"  action="tbuser_applyMember.action" method="post" enctype="multipart/form-data" >
         
         
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label"><i class="require-red">*</i>产品厂家名称</label>
            <div class="am-u-sm-9">
              <input id="productName" name="tbUser.productName " size="50" " type="text" placeholder="营业执照名称">
              <small id="txtUserName"></small>
            </div>
          </div>
       
          
			<div class="am-form-group">
	          <label for="user-name" class="am-u-sm-3 am-form-label"><i class="require-red">*</i>会&nbsp;&nbsp;员&nbsp;&nbsp;类&nbsp;&nbsp;别</label>
	          <div class="am-u-sm-9">
	            <div class="am-btn-group" data-am-button>
	              <label class="am-btn am-btn-default am-btn-xs">
	                <input type="radio" id="type1" name="type" value="1" > 普通
	              </label>
	              <label class="am-btn am-btn-default am-btn-xs">
	                <input type="radio" id="type2" name="type" value="2"> VIP
	              </label>
	            </div>
	          </div>
	        </div>
	     
	        
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">*省&nbsp;&nbsp;市&nbsp;&nbsp;名&nbsp;&nbsp;称</label>
            <div class="am-u-sm-9">
              <input name="tbUser.provinceCity"  id="legal" size="50"  type="text" placeholder="如：山东省烟台市 ">
              <small></small>
            </div>
          </div>      
          
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">*会&nbsp;&nbsp;员&nbsp;&nbsp;名&nbsp;&nbsp;称</label>
            <div class="am-u-sm-9">
              <input name="tbUser.memberName"  id="legal" size="50"  type="text" placeholder="如：烟台苹果 ">
              <small></small>
            </div>
          </div>                
	  
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">*法&nbsp;&nbsp;人&nbsp;&nbsp;名&nbsp;&nbsp;称 </label>
            <div class="am-u-sm-9">
              <input name="tbUser.legal"  id="legal" size="50"  type="text" placeholder="公司法人名称 ">
              <small></small>
            </div>
          </div>
     <hr/>	      
            <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">会员联系人&nbsp;&nbsp;</label>
            <div class="am-u-sm-9">
              <input  name="tbUser.contactName" id="contactName"  type="text" placeholder="输入会员联系人名称 ">
              <small id="txtdentityName"></small>
			</div>
          </div>
          
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">厂&nbsp;&nbsp;家&nbsp;&nbsp;地&nbsp;&nbsp;址</label>
            <div class="am-u-sm-9">
              <input name="tbUser.address"  id="legal" size="50"  type="text" placeholder="输入厂家地址 ">
              <small></small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">联系电话手机 </label>
            <div class="am-u-sm-9">
              <input name="tbUser.phoneNum"  id="legal" size="50"  type="text" placeholder="输入手机号 ">
              <small></small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">联系电话固话 </label>
            <div class="am-u-sm-9">
              <input name="tbUser.telNum"  id="legal" size="50"  type="text" placeholder="输入固话 ">
              <small></small>
            </div>
          </div>
          
           <div class="am-form-group">
            <label for="user-email" class="am-u-sm-3 am-form-label">厂&nbsp;&nbsp;家&nbsp;&nbsp;信&nbsp;&nbsp;息 </label>
            <div class="am-u-sm-9">
              <input name="tbUser.factoryInfo"  id="legal" size="500"  type="text" placeholder="输入厂家信息 ">
              <small></small>
            </div>
          </div>

 <hr/>
        
          
            <div class="am-form-group">
            
            <label for="user-phone" class="am-u-sm-3 am-form-label">厂家物理地点图片：</label>
            <div class="am-u-sm-9">
              <input type="file" id="addressPic" name="image">
                    <p class="am-form-help">请选择要上传的文件...</p>
			</div>
			
			 <label for="user-phone" class="am-u-sm-3 am-form-label">企业获奖证书图片：</label>
            <div class="am-u-sm-9">
              <input type="file" id="awardPic" name="image">
                    <p class="am-form-help">请选择要上传的文件...</p>
			</div>
			
            <label for="user-phone" class="am-u-sm-3 am-form-label">企业工商营业执照扫描件：</label>
            <div class="am-u-sm-9">
              <input type="file" id="licensePic" name="image">
                    <p class="am-form-help">请选择要上传的文件...</p>
			</div>
         	</div>
	
				<div class="am-form-group">
           <!--  <label for="user-weibo" class="am-u-sm-3 am-form-label">当前状态：</label> -->
             <!--  <input  type="hidden" id="status2" name="type"  size="50" value="${currentUser.type }"  > -->
			
              <input  type="hidden" id="goodsId" name="tbUser.password" value="${currentUser.password}"/>
        
              <input  type="hidden" id="goodsId" name="tbUser.verified" value="${currentUser.verified}"/>
          
              <input  type="hidden" id="goodsId" name="tbUser.memberTime" value="${currentUser.memberTime}"/>
          
              <input  type="hidden" id="goodsId" name="tbUser.verifiedReason" value="${currentUser.verifiedReason}"/>
          
              <input  type="hidden" id="trueName" name="tbUser.userName" value="${currentUser.userName}"/>
              
              <input  type="hidden" id="id" name="tbUser.id" value="${currentUser.id}"/>
          </div>  	
			 <hr/>		
					
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <input id="btn" type="submit" class="am-btn am-btn-primary" value="申请">&nbsp;&nbsp;&nbsp;&nbsp;
               <input id="btn" type="reset" class="am-btn am-btn-primary" value="重置">
            </div>
          </div>
        </form>
      </div>
	  
	  
      
    </div>
  </div>
  <!-- content end -->




</body>
</html>
