<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>


<style type="text/css">


.product-list ul.product li { width:219px; height:240px; line-height:22px; font-size:14px; overflow:hidden; }
.product-list ul.product li dl dt img { width:216px; height:170px; border:1px solid #ccc; }
.product-list ul.product li dl dd.title { height:44px; }

#product .infos .thumb { float:left; width:320px; text-align:center; }
#product .infos .buy { float:right; width:440px; line-height:40px; }
#product .infos .buy .price { color:#c00; font-weight:bold; font-size:24px; }
#product .infos .buy .button { border:1px solid #ffefd2; padding:10px; background:#fff8e7; margin:20px auto; text-align:center; }
#product .infos .buy .button input { border:0; margin:0 10px; background:url(../images/bg.png) left -176px; height:35px; width:144px; cursor:pointer; }
#product .introduce .text { padding:20px; font-size:14px; line-height:25px; }
ul.product li { width:110px; line-height:18px; height:160px; float:left; display:inline; margin:9px; }
ul.product li dl dt { text-align:center; }
ul.product li dl dt img { width:100px; height:100px; }
ul.product li dl dd.title { word-wrap:no-wrap; word-break:break-all; height:36px; overflow:hidden; }
ul.product li dl dd.title a { color:#666; }
ul.product li dl dd.price { color:#c30; font-weight:bold; }

.pager { margin-top:15px; }
.pager ul { float:right; }
.pager ul li { float:left; border:1px solid #eee; line-height:18px; padding:0 3px; margin:0 1px; display:inline; }
.pager ul li.current { font-weight:bold; color:#630; }
table{
    border: 1px;
    width: 100%;
}
td{
	/*padding:.1em .1em;*/
	padding:.5em .5em;
    /*background-color: #0c80ba*/

}
th{
	color:#edc;
	text-align:center;
	background-color:#46a546;
	font-size: 16px;
	height:40px;
}
tr td{
	color:#223;
	font-size: 14px;
	height:35px;
}

.mark
{
    /*background-color: Red;*/
    width: 100%;
    height: 100%;
    overflow: hidden;
    text-align: center;
    vertical-align: middle;
    padding: 5px;
}
</style>
<script>
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
<div class="main">
	<div><!-- class="sortbox"-->
		<div class="siftbox clearfix">
			<span class="label">${conditionCode}</span>
		</div>
			<table>
                <thead>
                <tr>
                    <th style="">编号</th>
                    <th style="">商品类型</th>
                    <th style="">商品名称</th>
                    <th style="">产品厂家</th>
                    <th style="">产品单价（箱）</th>
                    <th style="">详情</th>
                    <th style="">订购</th>
                </tr>
                </thead>
                <%--<tbody>--%>
                <c:forEach items="${tbProductList}" var="p">
                    <tr height="35px">
                        <%--<td style="width: 20px;"><div>${p.id }</div></td>--%>
                        <td style="width:50px;"><div class="mark">${p.id }</div></td>
                        <td style="width:100px;"><div class="mark">${p.bigType.name }</div></td>
                        <td style="width:100px;"><div class="mark">${p.user.memberName }</div></td>
                        <td style=""><div class="mark">${p.user.productName}</div></td>
                        <td style="width: 150px;"><div class="mark">${p.price }</div></td>
                        <td style="width: 100px;"><div class="mark"><a href="product_showProduct.action?productId=${p.id }"  target="_blank">详情</a></div></td>
                        <td style="width: 100px;"><div class="mark"><a href="javascript:findProduct(${p.id })"  target="_blank">订购</a></div></td>
                    </tr>
                </c:forEach>
                <%--</tbody>--%>

            </table>

			<div class="clear"></div>
			<div class="pager">
						<ul class="clearfix">
							${pageCode }
						</ul>
			</div>
	</div>
</div>
</body>
</html>