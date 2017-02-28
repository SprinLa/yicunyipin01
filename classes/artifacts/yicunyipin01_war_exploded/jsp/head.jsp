<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.yicunyipin.entity.*"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet"/>
</head>
<body>

  <div id="tag" >
       <div id="tagc">
			 <% TBUser user=(TBUser)session.getAttribute("currentUser");
                if(user!=null){
                %>
                
			    <span><a href="shopping_list.action" class="shopping">购物车(${shoppingCart.shoppingCartItems==null?0:shoppingCart.shoppingCartItems.size()}件商品)</a>
			欢迎，<a href="${pageContext.request.contextPath}/background/mainTemp.jsp" title="个人信息中心" target="_blank"><font style="color: #060CFE; font-weight: bolder;"><%=user.getUserName()%></font></a>！<a
				href="user_logout.action">&nbsp;|&nbsp;<span style="color: #666666;">[退出]</span></a>&nbsp;&nbsp;
			   </span>
		       <%}else { %>
		       <a href="javascript:checkLogin()" class="shopping">购物车</a>
			   <a href="${pageContext.request.contextPath}/jsp/login.jsp"><span style="color: #666666;">[登录]</span></a>&nbsp;&nbsp;
			   <a href="${pageContext.request.contextPath}/jsp/register.jsp"><span style="color: #666666;">[注册]</span></a>
			   <%}%>
			   <!-- <a href="">注册</a><em class="line"></em> -->
		<a href="">收藏本站</a><em class="line"></em>&nbsp;&nbsp;
</div >
	</div>
</body>
</html>
