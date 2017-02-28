<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.yicunyipin.entity.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	function logout(){
		if(confirm('您确定要退出系统吗？')){
			window.location.href="user_logout.action";
		}
	}
</script>


<style type="text/css">
	
.help { float:right; margin:0; padding:0;margin-left:100px; }
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
</head>
<body>

<div id="logo" >
			<a href="index.action"><img style="margin:5px;" src="${pageContext.request.contextPath}/images/logob.png"  height="110"></a>
</div>


<div class="help">
			<div class="logo">
				<div class="fr">
				<form name="searchform" method="post" action="product.action" class="search fl">
					<input name='ecmsfrom' type='hidden' value='[!--news.url--]'><!--这里更改返回路径-->
					<input type="hidden" name="show" value="title,newstext">
					<input class="index_srh fl" name="s_product.name" value="${s_product.name }" onBlur="if(this.value=='') this.value='请输入您要查找的商品...';" onFocus="if(this.value=='请输入您要查找的商品...') this.value='';">
					<input class="search yh fr" type="submit" name="submit" value="搜 索">
				</form>
			<p>热门搜索：<a href="" class="yellow">鲜桃</a><a href="">栗子</a><a href="">苹果</a><a href="" class="yellow">鲜桃</a><a href="" class="yellow">鲜桃</a><a href="" class="yellow">鲜桃</a></p>
			</div>
			</div>
</div>


<%-- <div class="nav-wrap" style="background:url(${pageContext.request.contextPath}/images/mnu1_bg.png) repeat-x;">
			 <div class="wrap">
			 <div class="nav-wrap">
			 <div class="nav">
                <ul class="nav-list cf">
                  <li> <a href="" class="index">首页</a> </li>
                  <li><a href="${pageContext.request.contextPath}/news!newsList.action">产业政策</a></li>
                  <li>	<a href="${pageContext.request.contextPath}/goods!goodList.action">新品展示</a></li>
               <li><a href="">交易商城</a></li>
				<li><a href="${pageContext.request.contextPath}/goods!goodGoodsList.action">一村一品精品展示</a></li>
				<li><a href="${pageContext.request.contextPath}/goods!goodGoodsList.action">一乡一业</a></li>
				<li><a href="">会员管理</a></li>
				<li><a href="">后台管理</a></li>
				<li><a href="${pageContext.request.contextPath}/jsp/main/contactUs.jsp">联系我们</a></li>
                </ul>
              </div>
              </div>
              </div>
</div> --%>



<%-- <div id="logo">
	<img src="images/logo.gif" />
</div>
<div class="help">
	<c:choose>
		<c:when test="${not empty currentUser }">
			<a href="#" class="shopping">购物车(0件商品)</a>
			<a href="#">${currentUser.userName }</a>
			<a href="javascript:logout()">注销</a>
			<a href="register.jsp">注册</a>
			<a href="comment_list.action">留言</a>	
		</c:when>
		<c:otherwise>
			<a href="#" class="shopping">购物车</a>
			<a href="login.jsp">登录</a>
			<a href="register.jsp">注册</a>
			<a href="comment_list.action">留言</a>		
		</c:otherwise>
	</c:choose>

	<form action="product.action" method="post">
		<input type="text" id="txtSearch" name="s_product.name"
			onkeyup="" autocomplete="off" value="${s_product.name }" /> <input
			type="submit" id="cmdSearch"  value="搜索" /><br/>
		<div id="suggest" style="width: 200px"></div>
	</form>
</div>

<div class="navbar">
	<ul class="clearfix">
		<li class="current"><a href="index.jsp">首页</a>
		</li>
		<c:forEach var="bigType" items="${bigTypeList }">
			<li>
				<a href="product.action?s_product.bigType.id=${bigType.id }">${bigType.name }</a>
			</li>
		</c:forEach>
	</ul>
</div>


<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<c:forEach var="tag" items="${tagList }" varStatus="status">
				<c:choose>
					<c:when test="${status.index==0 }">
						<li class="first">
							<a href="${tag.url }" target="_blank">${tag.name }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${tag.url }" target="_blank">${tag.name }</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul> 
	</div>
</div> --%>
</body>
</html>