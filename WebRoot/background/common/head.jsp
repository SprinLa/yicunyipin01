<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				window.location.href='user_logout.action';
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
<div class="row-fluid">
	<div class="span12">
		<div>
			<div class="headLeft">
				<a href="index.jsp"><img src="${pageContext.request.contextPath}/images/logob.png"/></a>
			</div>
			<div class="headRight">
				欢迎用户：<font color="red">${currentUser.userName}</font>&nbsp;&nbsp;&nbsp;&nbsp;(<font id="status" color="red">${currentUser.status}</font>)&nbsp;&nbsp;&nbsp;<a href="javascript:logout()">&nbsp;[&nbsp;安全退出&nbsp;]</a>&nbsp;&nbsp;&nbsp;&nbsp;<font id="today" class="currentDateTime"></font>
			</div>
		</div>
	</div>
</div>
