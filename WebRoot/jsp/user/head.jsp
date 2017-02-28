<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.yicunyipin.entity.*"
	pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet"/>

  <div style="margin: 0 auto; width: 1200px; height: 40px; text-align: center;">
		
		<div style="float: right; width: 398px; height: 40px; line-height: 40px; text-align: left;">
			 <% User user=(User)session.getAttribute("user");
                if(user!=null){
                %>
			    <span>欢迎，<%=user.getUserName()%><a href="" title="个人信息中心" target="_blank"><font style="color: #060CFE; font-weight: bolder;"></font></a>！<a
				href="user!resetUser.action">&nbsp;|&nbsp;<span style="color: #666666;">[退出]</span></a>&nbsp;&nbsp;
			   </span>
		       <%}else { %>
			   <a href="${pageContext.request.contextPath}/jsp/user/login.jsp"><span style="color: #666666;">[登录]</span></a>&nbsp;&nbsp;
			   <a href="${pageContext.request.contextPath}/jsp/user/register.jsp"><span style="color: #666666;">[注册]</span></a>
			   <%}%>
			   <!-- <a href="">注册</a><em class="line"></em> -->
		
		<a href="">收藏本站</a><em class="line"></em>&nbsp;|&nbsp;
		<span style="color: #666666;">热线电话</span> <a href="#" style="color: #c40000; font-size: 12px; font-weight: normal;">010-88888888</a>
		<%-- 	&nbsp;&nbsp;|&nbsp;&nbsp; <span style="color: #666666;">QQ登录</span> <a
				href="#"><img src="${pageContext.request.contextPath}/image/icon-qq.png" border="0"></a> <span
				style="color:#666666;">微博登录</span> <a href="#"><img
				src="${pageContext.request.contextPath}/image/icon-weibo.png" border="0"></a> --%>
		</div>
		
		
		<!-- <div style="float: left; width: 600px; height: 40px; line-height: 40px; text-align: center;">
			<a href="#"><span style="color: #666666;">帮助</span></a>&nbsp;|&nbsp;
			<a href=""><span style="color: #666666;">留言</span></a>&nbsp;|&nbsp;
			<a target="_blank" style="" href=""><img  align="absmiddle" border="0" src="http://amos.alicdn.com/realonline.aw?v=2&uid=c%E8%AF%AD%E8%A8%80diy&site=cntaobao&s=1&charset=utf-8" alt="点击这里给我发消息" /></a>
			&nbsp;|&nbsp;
			<span style="color: #666666;">热线电话</span> <a href="#"
				style="color: #c40000; font-size: 12px; font-weight: normal;">020-88888888</a>&nbsp;&nbsp;
			<a href="#"><span style="color: #666666;">手机版</span></a>
			<a target=blank href=http://wpa.qq.com/msgrd?V=1&Uin=929679459&Site=网上商城&Menu=yes><img border="0" align="absmiddle" SRC=http://wpa.qq.com/pa?p=1:929679459:1 alt="点击这里给我发消息"></a>
		</div> -->
		
		<%-- <div style="float: left; width: 202px; height: 40px; line-height: 40px; text-align: right;">
			<a href="${pageContext.request.contextPath}/jsps/car/car.jsp"> <img src="${pageContext.request.contextPath}/image/cart.png" align="absmiddle"
				border="0" style="cursor: pointer;"> <span
				style="color: #666666;">购物车</span>
			</a>
		</div> --%>
		
	</div>
	

</div>