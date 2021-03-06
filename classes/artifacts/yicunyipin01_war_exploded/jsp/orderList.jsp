<%@ page import="java.util.Date" %>
<%@ page import="com.yicunyipin.entity.TBOrder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的订单</title>
	<style>
		table{
			width: 100%;
			margin:auto;
		}

		td{
			border: 1px solid silver;
			padding:.5em 3em;
		}
		th{
			color:#edc;
			text-align:center;
			background-color: #46a546;
			height:40px;
			width:195px;
		}
		tr td{
			color:#223;
			background-color: mintcream;
			height:35px;
		}
	</style>
	<script>
		$(function(){

			$(".add").click(function(){
				var t=$(this).parent().find('input[class=text_box]');
				t.val(parseInt(t.val())+1);
				var product_id=$(this).parent().find('input[id=product_id]').val();
				var price=$("#price_"+product_id).html();
				$("#productItem_total_"+product_id).html(price*t.val());

				refreshSession(product_id,t.val());
				setTotal();
			});

			$(".min").click(function(){
				var t=$(this).parent().find('input[class=text_box]');
				t.val(parseInt(t.val())-1);
				if(parseInt(t.val())<0){
					t.val(0);
				}
				var product_id=$(this).parent().find('input[id=product_id]').val();
				var price=$("#price_"+product_id).html();
				$("#productItem_total_"+product_id).html(price*t.val());

				refreshSession(product_id,t.val());
				setTotal();
			});

			$(".text_box").blur(function(){
				var t=$(this).parent().find('input[class=text_box]');
				if(parseInt(t.val())<0){
					t.val(0);
				}
				var product_id=$(this).parent().find('input[id=product_id]').val();
				var price=$("#price_"+product_id).html();
				$("#productItem_total_"+product_id).html(price*t.val());

				refreshSession(product_id,t.val());
				setTotal();
			});

			function setTotal(){
				var s=0;
				$(".productTr").each(function(){
					var n=$(this).find('input[class=text_box]').val();
					var price=$(this).find('label[class=price_]').html();
					s+=n*price;
				});
				$("#product_total").html(s);
			}

			function refreshSession(productId,count){
				$.post("shopping_updateShoppingCartItem.action",{productId:productId,count:count},
						function(result){
							var result=eval('('+result+')');
							if(result.success){

							}else{
								alert("刷新Session失败");
							}
						}
				);
			}

			setTotal();

		});

		function removeShopping(productId){
			if(confirm("您确定要删除这个商品吗？")){
				window.location.href="shopping_removeShoppingCartItem.action?productId="+productId;
			}
		}
	</script>
</head>
<body>
<div class="order-list">
	<table>
		<thead>
		<tr>
			<th style="width:11%;">商品类型</th>
			<th style="width:12%;">商品名称</th>
			<th>产品厂家</th>
			<th style="width:10%;">联系人</th>
			<th>厂家地址</th>
			<th style="width:14%;">厂家电话</th>
			<th style="width:11%;">产品单价（元/箱）</th>
			<th style="width:9%;">购买数量(箱)</th>
		</tr>
		</thead>
		<c:set var="sum" scope="page"/>
		<c:forEach items="${tbOrderList}" var="tbOrder">
			<tbody>
			<tr>
				<td>${tbOrder.product.bigType.name }</td>
				<td>${tbOrder.product.user.memberName }</td>
				<td>${tbOrder.product.user.productName}</td>
				<td>${tbOrder.product.user.contactName}</td>
				<td>${tbOrder.product.user.address}</td>
				<td>${tbOrder.product.user.telNum}</td>
				<td>${tbOrder.product.price}</td>
				<td>${tbOrder.orderCount}</td>
				<c:set var="price" value="${tbOrder.product.price}" scope="page"/>
				<c:set var="count" value="${tbOrder.orderCount}" scope="page"/>
				<c:set var="sumTmp" value="${price*count}"/>
				<c:set var="sum" value="${sum+sumTmp}"/>
			</tr>
			</tbody>

		</c:forEach>
	</table>
	<br>
	<br>

	<table>
		<thead>
		<tr>
			<th>买家联系人</th>
			<th>联系电话(手机)</th>
			<th>买方单位</th>
			<th>买方详细地址</th>
			<th>订单时间</th>
			<th>合计金额(元)</th>

		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${tbOrder.buyerName }</td>
			<td>${tbOrder.buyerPhone }</td>
			<td>${tbOrder.buyerCompany}</td>
			<td>${tbOrder.buyerAddress}</td>
			<td>${tbOrder.buyerTime}</td>
			<td>${sum}</td>

		</tr>
		</tbody>
	</table>
</div>

</body>
</html>