<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@   page   import= "com.yicunyipin.entity.TBUser "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/background/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/background/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/background/assets/css/admin.css">
	
	
	
	<%
		String mainPage=(String)request.getAttribute("mainPage");
	  	TBUser u=(TBUser)session.getAttribute("currentUser");
	  	
	    if(!((u.getType() == 1 && u.getType() == 2) && u.getVerified() == 1)){
	    	
	   //     System.out.println(""+u.getStatus());
	        
	    	//mainPage="/background/user/userSave2.jsp";
	    }
		
		if(mainPage==null || mainPage.equals("")){
			mainPage="/background/default.jsp";
		}
	%>
	
</head>


<script type="text/javascript">
	function refreshSystem(){
		$.post("product_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_showProductByUser.action";
				}else{
					alert("你的账号正在审核中,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function productPreSave(){
		$.post("product_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_ProductPreSave.action";
				}else{
					alert("你的账号正在审核中,不能操作！请尝试重新登录");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function showProductByUser(){
		$.post("product_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_showProductByUser.action";
				}else{
					alert("你的账号正在审核中,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function newsPreSave(){
		$.post("product_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/news_newsPreSave.action";
				}else{
					alert("你的账号正在审核中,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	
	function showNewsByUser(){
		$.post("product_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/news_showNewsByUser.action";
				}else{
					alert("你的账号正在审核中,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
</script>
<body>

<header class="am-topbar admin-header">
  <jsp:include page="/background/common/headTest.jsp"/>
</header>

 <div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <jsp:include page="/background/common/sideTest.jsp"/>
  </div> 
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
			<jsp:include page="<%=mainPage %>"></jsp:include>
  </div>
  <!-- content end -->
</div>
<footer>
   <jsp:include page="/background/common/footTest.jsp"/>
</footer>


<script src="${pageContext.request.contextPath}/background/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/background/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${pageContext.request.contextPath}/background/assets/js/app.js"></script>
</body>
</body>
</html>