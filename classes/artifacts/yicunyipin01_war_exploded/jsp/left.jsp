<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/left1.css" type="text/css" rel="stylesheet"/>

<style type="text/css">

.last-view { border:1px solid #c3c3c3; }
 .last-view dl { margin:5px; }
.last-view dl dt { float:left; width:60px; height:60px; text-align:center; }
 .last-view dl dt img { border:1px solid #ccc; }
 .last-view dl dd { height:60px; margin-left:66px; }
</style>

</head>

<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Jdropdown.js"></script>

<div id="box">
	<div class="area clearfix">
		<div class="category-content">
			<div>
				<span class="all-goods">地区分布<em></em></span>
			</div>
		    	
			<div class="category">
				<ul class="category-list">
				<c:forEach var="address" items="${addressList}" varStatus="status">
					<li class="js_toggle">
						<div class="category-info list-nz">
							<h3 class="category-name">
								<i></i>
								<a href="" class="ml-22">${address.name}</a>
							</h3>
							<p class="c-category-list">
							
						<c:forEach var="address1" items="${address.provinceList}" varStatus="status">	
							    <a href="product.action?s_product.province.pid=${address1.pid}">${address1.pname}</a>
						</c:forEach>	
							</p>
							<em>&gt;</em>
						</div>
						<div class="menu-item menu-in">
							<div class="area-bg">
								<ul class="sublist clearfix">
									<c:forEach var="address1" items="${address.provinceList}" varStatus="status">
									<li>
										<h3 class="product.action?s_product.province.pid=${address1.pid}"><span>${address1.pname}</span></h3>
										<p class="mcate-item-bd">
										<c:forEach var="address2" items="${address1.cityList}" varStatus="status">
											<a href="product.action?s_product.city.cid=${address2.cid}">${address2.cname}</a>
										</c:forEach>
										</p>
									</li>
									  </c:forEach>
								</ul>	
							</div>
						</div>
					</li>
		         </c:forEach>
				</ul>
		</div>
	</div>
	
	<div>
	</div>    
	    
	    
	    <div class="area clearfix" style="margin-top:20px">
		<div class="category-content"style="margin-top:5px">
			<div>
				<span class="all-goods">产品畅销榜1<em></em></span>
			</div>
		<div class="category01" style="height:470px;border:1px solid #7a7a7a;">
		     
		    <div  class="last-view" style="width:230px;border:1px solid #7a7a7a;">
		     
		  <dl class="clearfix">
	 <c:forEach var="p" items="${clickProductList }" varStatus="status">
	
	 	<dt>
	 		<img src="images/${p.proPic }" class="imgs" style="height: 50px;width: 50px;"/>
	 	</dt>
	 	<dd>
	 		<a href="product_showProduct.action?productId=${p.id }" target="_blank">${status.index+1}  ${p.name }</a>
	 	</dd>
	 </c:forEach>
	</dl>
		</div>
		</div>
		</div>
	</div>
	 <div class="area clearfix" style="margin-top:20px">
	    
	    
	     <div style="margin-top:3px;width:230px;border:1px solid #7a7a7a;text-align:center;float:left;">
		  		<div style="height:36px; background: #E7E7E7;"><font size=4px color=red>最近浏览</font>1</div>
		 </div>
		<div  class="last-view" style="width:230px;border:1px solid #7a7a7a;">
		     
		  <dl class="clearfix">
	 <c:forEach var="p" items="${currentBrowse }" varStatus="status">
	
	 	<dt>
	 		<img src="images/${p.proPic }" class="imgs" style="height: 50px;width: 50px;"/>
	 	</dt>
	 	<dd>
	 		<a href="product_showProduct.action?productId=${p.id }" target="_blank">${status.index+1}  ${p.name }</a>
	 	</dd>
	 </c:forEach>
	</dl>
		</div>
	</div>
	
	</div>
</div>
</body>
</html>
