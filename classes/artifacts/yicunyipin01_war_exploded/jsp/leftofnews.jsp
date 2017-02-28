<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
 .nav2-list {	height: 44px;}

 .nav2-list li {	margin-top:10px;float: left;width:230px;margin-right: 1px;background:url(${pageContext.request.contextPath}/images/mnu1_bg.png) repeat-x;}

 .nav2-list a {	display: block;	height: 40px;width:230px;	float: left;	padding: 0 0px;	text-align: center;	color: #fff;	font: bold 15px/40px "microsoft yahei";	text-decoration: none;}
 .nav2-list a:hover {
	background: #FDE2D7;
	text-decoration: none;
	color: #F00;
	width:230px;
	}
 .nav2-list a.on {
	background: #d25400;width:230px;
}

</style>
</head>

<body>


<div style="text-align:center;margin-top:25px;font-size:17px;height:36px;border:1px solid #7a7a7a;background:url(${pageContext.request.contextPath}/images/label01.png) repeat-x;">
				类别
</div>
		<div  style="margin-top:10px;">
		     
		     <ul class="nav2-list cf" style="font-size:13px;line-height:30px;">
				     <c:forEach var="newsType" items="${NewsTypeList}" varStatus="status">
				     	<li > <a href="news.action?s_news.newsType.id=${newsType.id}">${newsType.typeName}	</a></li>
				     	
				     </c:forEach>
			  </ul>
		</div>
</body>
</html>
