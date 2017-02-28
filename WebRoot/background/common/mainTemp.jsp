<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员主界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<%
	String mainPage=(String)request.getAttribute("mainPage");
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
<div class="container">
<jsp:include page="/background/common/head.jsp"/>

<div class="row-fluid">
	<div class="span3">
		<div class="newsMenu">
			<ul class="nav nav-tabs nav-stacked">
				  <li><a href="${pageContext.request.contextPath}/background/mainTemp.jsp"><strong>主页</strong></a></li>
				  <li><a href="#"><strong>会员信息</strong></a></li>
				  <li><a href="${pageContext.request.contextPath}/user_userPreSave.action?uId=${currentUser.id}">&nbsp;&nbsp;基本信息</a></li>
				    <li><a href="${pageContext.request.contextPath}/user_userPreSave2.action?uId=${currentUser.id}">&nbsp;&nbsp;修改信息</a></li>
				  <li><a href="${pageContext.request.contextPath}/user_userPassword.action">&nbsp;&nbsp;修改密码</a></li>
				  <li><a href=""><strong>商品管理</strong></a></li>
				<%--   <li><a href="${pageContext.request.contextPath}/product_ProductPreSave.action">&nbsp;&nbsp;商品添加</a></li>
				  <li><a href="${pageContext.request.contextPath}/product_showProductByUser.action">&nbsp;&nbsp;商品维护</a></li> --%>
				   <li><a href="javascript:productPreSave()">&nbsp;&nbsp;商品添加</a></li>
				  <li><a href="javascript:showProductByUser()">&nbsp;&nbsp;商品维护</a></li>
				  <li><a href=""><strong>新闻管理</strong></a></li>
				  <%-- <li><a href="${pageContext.request.contextPath}/news_newsPreSave.action">&nbsp;&nbsp;新闻添加</a></li>
				  <li><a href="${pageContext.request.contextPath}/news_showNewsByUser.action">&nbsp;&nbsp;新闻维护</a></li> --%>
				  <li><a href="javascript:newsPreSave()">&nbsp;&nbsp;新闻添加</a></li>
				  <li><a href="javascript:showNewsByUser()">&nbsp;&nbsp;新闻维护</a></li>
				  <!-- <li><a href=""><strong>系统管理</strong></a></li>
				  <li><a href="javascript:refreshSystem()">&nbsp;&nbsp;刷新服务器缓存</a></li> -->
			</ul>
		</div>
	</div>
	<div class="span9">
		<jsp:include page="<%=mainPage %>"></jsp:include>
	</div>
</div>
<jsp:include page="/background/common/foot.jsp"/>
</div>
</body>
</html>