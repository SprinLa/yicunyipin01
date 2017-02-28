<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>

<style type="text/css">

#shopping { }
#shopping table { width:100%; line-height:24px; border-top:2px solid #dfc9b2; border-bottom:2px solid #dfc9b2; }
#shopping table th { background:#f7f4eb; color:#8a7152; }
#shopping table td { padding:10px 0; border-top:1px solid #dfc9b2; }
#shopping table td.thumb img { border:1px solid #dfdfe0; margin:0 10px; vertical-align:middle;width: 54px; heigth: 54px}
#shopping table td.price,
#shopping table td.number { text-align:center; width:100px; border-left:1px solid #e1e1e1; }
#shopping table td.price { color:#cc3300; font-weight:bold; }
#shopping table td.delete { width:60px; text-align:center; border-left:1px solid #e1e1e1; }
#shopping table td.number dl { width:80px; margin:0 auto; }
#shopping table td.number dl dt { float:left; display:inline; width:25px; text-align:center; margin:0 4px; }
#shopping table td.number dl dt input { width:20px; border:1px solid #c9c9c9; padding:2px; text-align:center; }
#shopping table td.number dl dd { float:left; width:28px; margin:3px 0; text-align:center; line-height:20px; height:19px; overflow:hidden; border:1px solid #c9c9c9; cursor:pointer; }
#shopping .button { text-align:right; padding:10px 0; }
#shopping .button input { border:0; background:url(./images/bg.png) left -213px; width:144px; height:35px; cursor:pointer; }
#shopping .shadow { width:500px; margin:50px auto; }
.shopping_list_end{
		background-color:#cddbb8;
		height:60px;
		}
		.shopping_list_end li{
			float:right;
			}
		.shopping_list_end_1{
			margin:10px 10px 0px 10px;
			}
		.shopping_list_end_2{
			font-weight:bold;
			color:#BD3E00;
			font-size:14px;
			margin:15px 10px 0px 0px;
			}
		.shopping_list_end_3{
			font-weight:bold;
			font-size:14px;
			margin:15px 0px 0px 15px;
			}
		.shopping_list_end_4{
			border-right:solid 1px #666666;
			margin:10px 0px 0px 15px;
			padding-right:10px;
			}
		.shopping_list_end_yellow{
			color:#BD3E00;
			}	



</style>


<script> 
$(function(){ 
	
	// 加商品
	$(".add").click(function(){ 
		var t=$(this).parent().find('input[class*=text_box]'); // 数量对象
		t.val(parseInt(t.val())+1); 
		var product_id=$(this).parent().find('input[id*=product_id]').val(); // 商品id
		var price=$("#price_"+product_id).html(); // 单价
		$("#productItem_total_"+product_id).html(price*t.val());
		
		refreshSession(product_id,t.val());
		setTotal(); 
	});
	
	// 减商品
	$(".min").click(function(){ 
		var t=$(this).parent().find('input[class*=text_box]'); // 数量对象
		t.val(parseInt(t.val())-1); 
		if(parseInt(t.val())<0){ 
			t.val(0); 
		} 
		var product_id=$(this).parent().find('input[id*=product_id]').val(); // 商品id
		var price=$("#price_"+product_id).html(); // 单价
		$("#productItem_total_"+product_id).html(price*t.val());
		
		refreshSession(product_id,t.val());
		setTotal(); 
	});
	
	$(".text_box").blur(function(){
		var t=$(this).parent().find('input[class*=text_box]'); // 数量对象
		if(parseInt(t.val())<0){ 
			t.val(0); 
		} 
		var product_id=$(this).parent().find('input[id*=product_id]').val(); // 商品id
		var price=$("#price_"+product_id).html(); // 单价
		$("#productItem_total_"+product_id).html(price*t.val());
		
		refreshSession(product_id,t.val());
		setTotal(); 
	});
	
	// 设置总记录数
	function setTotal(){ 
		var s=0; 
		$(".productTr").each(function(){ 
			s+=$(this).find('input[class*=text_box]').val()*$(this).find('label[class*=price_]').html(); 
		}); 
		$("#product_total").html(s); 
	} 
	
	//刷新session
	function refreshSession(productId,count){
		$.post("shopping_updateShoppingCartItem.action",{productId:productId,count:count},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
					}else{
						alert("刷新Session失败！");
					}
				}
			);
	}

	setTotal(); 
	

});



//删除商品
function removeShopping(productId){
	if(confirm("确定要删除这个商品吗")){
		$.post("shopping_removeShoppingCartItem.action",{productId:productId},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						$("#tr_"+productId).remove();
						
						var s=0; 
						$(".productTr").each(function(){ 
							s+=$(this).find('input[class*=text_box]').val()*$(this).find('label[class*=price_]').html(); 
						}); 
						$("#product_total").html(s); 
						
						
					}else{
						alert("删除商品失败！");
					}
				}
			);
	}
	
}
</script> 
</head>
<body>
<div id="shopping">
		<form action="order_save.action" method="post">
			<table id="myTableProduct">
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>金额</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${shoppingCart.shoppingCartItems}" var="shoppingCartItem" varStatus="statu">
					<tr id="tr_${shoppingCartItem.product.id }"  class="productTr">
						<td class="thumb"><img class="imgs"
							src="images/${shoppingCartItem.product.proPic}" /><a
							href="product_showProduct.action?productId=${shoppingCartItem.product.id}">${shoppingCartItem.product.name}</a>
						</td>
						<td class="price" id="price_id_${p.product.id}"><span>￥<label class="price_" id="price_${shoppingCartItem.product.id}">${shoppingCartItem.product.price}</label></span> 
						</td>
						<td class="price" id="price_id_${p.product.id}">
							<span>￥<label id="productItem_total_${shoppingCartItem.product.id}">${shoppingCartItem.product.price*shoppingCartItem.count}</label></span>
						</td>
						<td class="number">
						       <input type="hidden" id="product_id" value="${shoppingCartItem.product.id }"/>
								<input class="min" name="" type="button" value=" - "  /> 
								<input id="number_id_${shoppingCartItem.product.id}" class="text_box"  style="width: 30px;text-align: center" name="" type="text" value="${shoppingCartItem.count}" /> 
								<input class="add" name="" type="button" value=" + " /> 
						</td>
						<td class="delete"><a
							href="javascript:removeShopping(${shoppingCartItem.product.id});">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>

			<div class="button">
				<input type="submit" value="" />
			</div>
		</form>
</div>

<div class="shopping_list_end">

	<ul>
		<li class="shopping_list_end_2">￥<label id="product_total"></label>
		</li>
		<li class="shopping_list_end_3">商品金额总计：</li>
	</ul>
</div>

<div style="background-color: #cddbb8;margin-top: 10px;font-size: 20px;height: 40px;text-align: center">
	<div style="padding: 5px;">
		<b>个人信息</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>收件人：</b>${currentUser.trueName }&nbsp;&nbsp;<b>收获地址：</b>${currentUser.address }&nbsp;&nbsp;<b>联系电话：</b>${currentUser.mobile }
	</div>
</div>
</body>
</html>