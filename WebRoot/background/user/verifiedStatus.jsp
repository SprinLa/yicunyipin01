<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@   page   import= "com.yicunyipin.entity.TBUser "%>
<html class="no-js">
<head>
  <title>一村一品会员界面</title>

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
	TBUser tbUser = (TBUser)request.getSession().getAttribute("currentUser");
	String message = "";
	if(tbUser.getType() == 1){
		if(tbUser.getVerified() == 1){
			message = "恭喜您，您已成为普通会员！";
		}else if (tbUser.getVerified() == 2){
			message = "很抱歉，请再次审核您所提交的申请材料！";
		}else{
			message = "您好，您已申请普通会员，正在审核中！";
		}
	}else if (tbUser.getType() == 2){
		if(tbUser.getVerified() == 1){
			message = "恭喜您，您已成为VIP会员！";
		}else if (tbUser.getVerified() == 2){
			message = "很抱歉，请再次审核您所提交的申请材料！";
		}else{
			message = "您好，您已申请VIP会员，正在审核中！";
		}
	}else{
		message = "您好，您还未申请会员，不能查看申请状态！";
	}
	%>
</head>

<div class="defaultPage" style="text-align:center;	margin-top: 120px;	font-size: 30px;	color: red;">
			<p> &nbsp; </p>
			<p><%=message %></p>	
			<p> &nbsp; </p><br/>
			<p> &nbsp; </p>
			<p> &nbsp; </p><br/>
		</div>
