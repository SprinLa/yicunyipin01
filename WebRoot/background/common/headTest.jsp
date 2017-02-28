<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@   page   import= "com.yicunyipin.entity.TBUser "%>
<script type="text/javascript">
	function setDateTime(){
			var date=new Date();
			var day=date.getDay();
			var week;
			switch(day){
			case 0:week="星期日";break;
			case 1:week="星期一";break;
			case 2:week="星期二";break;
			case 3:week="星期三";break;
			case 4:week="星期四";break;
			case 5:week="星期五";break;
			case 6:week="星期六";break;
			}
			var today=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日  "+week+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			document.getElementById("today").innerHTML=today;
		}
		
	    window.setInterval("setDateTime()", 1000);
		
		function logout(){
			if(confirm('您确定要退出系统吗？')){
				window.location.href='tbuser_logout.action';
			}
		}
		
		
		
		$(document).ready(function(){
			var v = document.getElementById("status").innerHTML;
			if(v=="0"){
			 document.getElementById("status").innerHTML="未审核";
			}
			if(v=="1"){
				 document.getElementById("status").innerHTML="审核未通过";
				}
			if(v=="2"){
				 document.getElementById("status").innerHTML="审核通过";
				}
		}	
		)	;
		
	
</script>
<style type="text/css">
	
.help { float:right; margin:0; padding:0;margin-left:100px; background:#c9033b;}
.help{ overflow:hidden; padding-top:px; }

.help .logo { width:550px; }
.help .logo .fr { width:550px; }
.help .logo form.search { width:440px;/*IE6*/ _width:442px; height:32px; border:solid 3px #c9033b; }
.help .logo input.index_srh { width:280px; height:32px; line-height:32px; border:none; background:none; padding:0 10px; }
.help .logo input.search { width:97px; height:33px; background:#c9033b; color:#FFF; border:none; font-size:14px; cursor:pointer }
.help .logo input { outline:none }
.help .logo .fr p { height:30px; line-height:25px;float:left }
.help .logo .fr p a { padding:0 0px; }
.help .logo .fr p a.yellow { color:#f60 }
</style>

<%
	TBUser user=(TBUser)session.getAttribute("currentUser");
	String name = "";
	if(user.getType() == 1 && user.getVerified() == 1){
		name = "会员";
	}else if (user.getType() == 2 && user.getVerified() == 1){
		name = "VIP会员";
	}else{
		name = "用户";
	}
%>


<div class="am-topbar-brand">
    <strong>欢迎<%=name %></strong> :&nbsp;&nbsp;<small>${currentUser.userName}</small>
  &nbsp;&nbsp;&nbsp;&nbsp;<font id="status" color="red"></font>&nbsp;&nbsp;&nbsp;
 <font id="today" class="currentDateTime">
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span>会员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="javascript:logout()"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
  <div id="logo" >
			<img align="middle" style="margin:5px;" src="${pageContext.request.contextPath}/images/logooo.png"  height="110"></a>
</div>