<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/left1.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
	dl{clear:left;}
	dt{float:left;}
	/*dt,dd{float:left;}*/
</style>
</head>

<body>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Jdropdown.js"></script>
 --%>
<div id="box">
	<div class="area clearfix">
		<div class="category-content">
			<div>
				<span class="all-goods">产品分类<em></em></span>
			</div>

			<div class="category">
				<ul class="category-list">
				<c:forEach var="bigType" items="${bigTypeList}" varStatus="status">
					<li class="js_toggle">
						<div class="category-info list-nz">
							<h3 class="category-name">
								<i></i>
								 <a href="" class="ml-22">${bigType.name}</a>
							</h3>
							<p class="c-category-list">

						<c:forEach var="smallType" items="${bigType.smallTypeList}" varStatus="status">
                            <a href="product_showProducts.action?product.bigType.id=${bigType.id}&product.smallType.id=${smallType.id}">${smallType.name}</a>
						</c:forEach>

							</p>
							<em>&gt;</em>
						</div>
					</li>
		         </c:forEach>
				</ul>
		</div>
	</div>

	<div>
	</div>


	    <div class="area clearfix" style="margin-top:20px">
	     <div style="margin-top:3px;width:230px;border:1px solid #7a7a7a;text-align:center;float:left;">
		  		<div style="height:36px; background: #E7E7E7;"><font size=4px color=red>商品畅销榜</font></div>
		 </div>
		<div  class="last-view" style="width:230px;border:1px solid #7a7a7a;">
		  <dl ><%--class="clearfix"--%>
			<c:forEach var="p" items="${bestsellersList}" varStatus="status">
	 			<dt><%-- style="width: 50px"--%>
	 				<img src="images/${p.productPic }" class="imgs" style="height: 50px;width: 50px;"/>
	 			</dt>
	 			<dd><%-- style="width: 170px;text-overflow:ellipsis"--%>
	 				<span style="border:1px "><a href="product_showProduct.action?productId=${p.id}" target="_blank">${status.index+1}  ${p.user.memberName}</a>
						</span>
	 			</dd>
			</c:forEach>
		</dl>
		</div>
	</div>

		<div class="area clearfix" style="margin-top:20px">
			<div style="margin-top:3px;width:230px;border:1px solid #7a7a7a;text-align:center;float:left;">
				<div style="height:36px; background: #E7E7E7;"><font size=4px color=red>商品推荐</font></div>
			</div>
			<%--<div   class="category" style="border: 1">--%>
			<div  class="last-view" style="width:230px;border:1px solid #7a7a7a;margin-top: 1cm">
				<dl  class="clearfix"	>
				</dl>
			</div>
		</div>


	 <div class="area clearfix" style="margin-top:20px">
	     <div style="margin-top:3px;width:230px;border:1px solid #7a7a7a;text-align:center;float:left;">
		  		<div style="height:36px; background: #E7E7E7;"><font size=4px color=red>最近浏览</font></div>
		 </div>
		<div  class="last-view" style="width:230px;border:1px solid #7a7a7a;margin-top: 1cm">
		  <dl  class="clearfix"	><!--  class="clearfix" -->
	 <c:forEach var="p" items="${currentBrowse}" varStatus="status">
	 	<dt>
	 		<img src="images/${p.productPic}" class="imgs" style="height: 50px;width: 50px;"/>
	 	</dt>
	 	<dd>
	 		<a href="product_showProduct.action?productId=${p.id }" target="_blank">${status.index+1}  ${p.user.memberName }</a>
	 	</dd>
	 </c:forEach>
	</dl>
		</div>
	</div>

	</div>


</div>
</body>
</html>
