<%@ page import="com.yicunyipin.entity.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<style type="text/css">
	td{color:#223;background-color:#e4f0e2;width: 80px;padding:.5em 3em;height:30px; border:2px solid #289d31;}
</style>
<link href="${pageContext.request.contextPath}/css/news.css" type="text/css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqzoom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base.js"></script>

<script type="text/javascript">
	function addShoppingCart(productId){
		if('${currentUser.userName}'==''){
			alert("请先登录，然后购物！");
		}else{
			window.location.href="shopping_addShoppingCartItem2.action?productId="+productId;
			
			//原来ajax刷新本网页和购物车数量
			/* $.post("shopping_addShoppingCartItem.action",{productId:productId},
					function(result){
						var result=eval('('+result+')');
						if(result.success){
							alert("已成功加入购物车！");
							location.reload(); // 刷新本页
						}else{
							alert(result.error);
						}
					}
				); */
		}
	}
	
	function goBuy(productId){
		if('${currentUser.userName}'==''){
			alert("请先登录，然后购物！点击确定后将跳转至登录界面");
			window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
		}else{
			window.location.href="product_findProduct.action?productId="+productId;
		}
	}

	function findProduct(productId){
		if('${currentUser.userName}'==''){
			alert("请先登录，然后订购！点击确定后将跳转至登录界面!");
			window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
		}else {
			window.open("product_findProduct.action?productId=" + productId);

		}
	}
</script>
</head>	
<body>
	<table>
		<tr>
			<td>商品类型</td>
			<td>鲜品果园</td>
			<td>产品厂家</td>
			<td colspan="3">${tbProduct.user.productName}</td>
		</tr>
		<tr>
			<td>商品名称</td>
			<td>${tbProduct.user.memberName}</td>
			<td>联系人</td>
			<td>${tbProduct.user.contactName}</td>
			<td>厂家电话（固定）</td>
			<td>${tbProduct.user.telNum}</td>
		</tr>
		<tr>
	 		<td><img jqimg="images/${tbProduct.productPic} " src="images/${tbProduct.productPic}" height=200px width=180px;/></td>
	 		<td>产品介绍</td>
	 		<td colspan="4">${tbProduct.productInfo}</td>
	 	</tr>
	 	<tr>
	 		<td>单价（箱）</td>
			<td>${tbProduct.price}</td>
	 		<td colspan="2">是否订购</td>
	 		<td><a href="javascript:findProduct(${tbProduct.id})"  target="_blank">订购</a></td>
	 		<td><a href="${pageContext.request.contextPath}/index.jsp">首页</a></td>
	 	</tr>
	</table>

</body>
</html>
		