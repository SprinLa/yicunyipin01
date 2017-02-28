<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填写订单</title>
	<style>
        table{
            width: 95%;
            margin:auto;
            font-size: 14px;
        }

        td{
            border: 1px solid silver;
            /*padding:.3em .5em;*/
        }
        th{
            color:#edc;
            text-align:center;
            background-color: #46a546;
            height:30px;
            width:195px;
        }
        tr td{
            color:#223;
            background-color: mintcream;
            height:26px;
        }
	</style>
<script>
    function saveOrder(){
        confirm("点击确认!");
        if('${currentUser.userName}'==''){
            alert("请先登录，然后订购！点击确定后将跳转至登录界面!");
            window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
        }else{
//            confirm("点击确认!");
//            window.location.href = "order_saveOrder.action";
            window.location.href = "index.jsp";
        }
    }

	function confirmAct()
	{
		if(confirm('订单填写完毕,请审核您的信息,确认后提交!'))
		{
			return true;
		}
		return false;
	}

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
<%--<div id="shopping">--%>
<div>
		<form action="order_saveOrder.action" method="post">
			<table id="orderSeller">
				<tr>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">商品类型:</td>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">${tbProduct.bigType.name }</td>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">产品厂家:</td>
					<td style="padding:.5em 3em;">${tbProduct.user.productName}</td>
				</tr><tr>
					<td style="padding:.5em 3em;">商品名称:</td>
					<td style="padding:.5em 3em;">${tbProduct.user.memberName }</td>
					<td style="padding:.5em 3em;">厂家地址:</td>
					<td style="padding:.5em 3em;">${tbProduct.user.address }</td>
				</tr><tr>
                  <table>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">联系人:</td>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">${tbProduct.user.contactName}</td>
					<td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">厂家电话:</td>
					<td style="padding:.5em 3em;">${tbProduct.user.telNum}</td>
					<td style="padding:.5em 3em;">单价（元/箱）:</td>
					<td style="padding:.5em 3em;">${tbProduct.price}</td>
                </table>
				</tr>
			</table>
            <br/>
            <table id="orderBuyer">
                <tr>
                    <td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">买家联系人:</td>
                    <td align="left" style="word-wrap:break-word;"><input type="text" style="width: 100%;height: 100%;padding:.3em .5em;" maxlength="16" name="tbOrder.buyerName" id="buyerName" value="张某某"/></td>
                    <td align="left" width="80px" style="padding:.5em 3em;">手机号码:</td>
                    <td><input style="width: 100%;height: 100%;padding:.3em .5em;" type="text" maxlength="16" name="tbOrder.buyerPhone" id="buyerPhone" value="131XXXXXXXX"/></td>
                    <td align="left" width="110px" style="padding:.5em 3em;">购买数量（箱）:</td>
                    <td><input style="width: 100%;height: 100%;padding:.3em .5em;" type="text" maxlength="16" name="tbOrder.orderCount" id="orderCount" value="1"/></td>
                    </tr><tr>
                <table>
                    <td align="left" width="80px" style="word-wrap:break-word;padding:.5em 3em;">买方单位:</td>
                    <td><input style="width: 100%;height: 100%;padding:.3em .5em;" type="text" maxlength="100" name="tbOrder.buyerCompany" id="buyerCompany" value="XX市XX单位XX部门采购处"/></td>

                <td align="left" width="100px" style="padding:.5em 3em;">买方详细地址:</td>
                <td><input style="width: 100%;height: 100%;padding:.3em .5em;" type="text" maxlength="100" name="tbOrder.buyerAddress" id="buyerAddress" value="XX省XX市XX县XXXX街道XX号 XX楼XX单元XX号"/></td>
                </table>
            </tr>
            </tr>
            </table>
            <br>
			<div style="width:120px;margin: auto;background-color: #46a546;padding: 1px;text-align: center">
				<input style="width:120px;white-space: nowrap;padding:.5em 3em;" type="submit" value="提交订单"  onclick="return confirmAct();"/>
                <%--a href="javascript:saveOrder()"--%>
            </div>
		</form>
</div>
</body>
</html>