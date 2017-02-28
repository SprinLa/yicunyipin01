<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<style type="text/css">

.last-view { border:1px solid #c3c3c3; }
 .last-view dl { margin:5px; }
.last-view dl dt { float:left; width:60px; height:60px; text-align:center; }
 .last-view dl dt img { border:1px solid #ccc; }
 .last-view dl dd { height:60px; margin-left:66px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	     <div style="font-size:15px;width:190px;border:1px solid #7a7a7a;">
				 <div style="height:36px; background: #E7E7E7;text-align:center;"><font size=4px color=red>	产品畅销榜</font></div>
		
			
		 </div>
		<div  style="width:190px;border:1px solid #7a7a7a;">
		<c:forEach var="product" items="${clickProductList}" varStatus="status">
		     <ul style="font-size:13px;line-height:30px;margin-left:20px;">
		     	<li > <a href="goods!goodListByAddress.action?aid=${product.id}">${status.index+1} ${product.name}</a></li>
			 </ul>
		</c:forEach>		     
		</div>
		
		
		
		 <div style="margin-top:3px;width:190px;border:1px solid #7a7a7a;">
		  		<div style="text-align:center;height:36px; background: #E7E7E7;"><font size=4px color=red>同类商品畅销榜</font></div>
		 </div>
		<div  class="last-view" style="width:190px;border:1px solid #7a7a7a;">
		     
		  <dl class="clearfix">
	 <c:forEach var="p" items="${clickProductList }" varStatus="status" >
	
	 	<dt>
	 		<img src="images/${p.proPic }" class="imgs" style="height: 50px;width: 50px;"/>
	 	</dt>
	 	<dd>
	 		<a href="product_showProduct.action?productId=${p.id }" target="_blank">${status.index+1}  ${p.name }</a>
	 	</dd>
	 </c:forEach>
	</dl>
		</div>
</body>
</html>
