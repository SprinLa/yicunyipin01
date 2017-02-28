<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	String mainPage=(String)request.getAttribute("mainPage");
	if(mainPage==null || mainPage.equals("")){
		mainPage="/background/default.jsp";
	}
	%>
</head>
<div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员服务首页</strong> / <small>Index</small></div>
  </div>
<div class="defaultPage" style="text-align:center;	margin-top: 50px;	font-size: 30px;	color: red;">
			<p>服务宗旨：提供互联网展示平台，推进一村一品发展。</p>
			<p>促进农业产品信息化推广；满足广大消费者需求。</p>
			<p>目的：惠民、惠农。 </p>
			<p> 农产品中的精品，消费者的健康高端品，农业部认证的名优品。 </p>
		</div>
