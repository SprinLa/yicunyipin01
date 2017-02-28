<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加到购物车</title>


<style type="text/css">
#continue {float:right;	width:580 ;height:500px;}
#shopping {float:left; width:600;height:700px;margin-left:180px;}
.detil{width:600;}
/* #shopping table { width:100%; line-height:24px; border-top:2px solid #dfc9b2; border-bottom:2px solid #dfc9b2; }
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
 */
</style>


</head>
<body>
<div id="shopping">
		<br/>	<br/>	
		<div class="title"><font size=5px;>成功添加到购物车  ! </font></div>
         <br/>	<br/> 
         <div class="detil">
	          <div ><font size=5px;> 商品详情      </font>        </div><br/> 
	          <div><img src="images/${product.proPic}" width="200px" height="200px;" /></div>
	          <br/> <div  >产品名称：    ${product.csName} </div><br/> 
	          <div > <span>单价：  ￥<label class="price_" id="price_${product.id}">${product.price}</label></span>  </div>
	           <br/> <div  >厂商：    ${product.name} </div><br/> 
	          <div > <span>联系电话：  ￥<label class="price_" id="price_${product.id}">${product.tel}</label></span>  </div>
	          
	           <br/> <div  >地址: ${product.province.pname} ${product.city.cname} ${product.town.tname} </div><br/> 
	          
		 </div>

</div>

<div id="continue">
	<div id="right" style="padding-right:200px;padding-top:270px;">
			<div class="" style="float:left"><font size=5px;> <a href="index.jsp">继续购物!</a></font></div><br/><br/><br/>
			<div class=""style="float:left"><font size=5px;><a href="">去结算!</a> </font></div>
	     
		 
	</div>	 
</div>




</body>
</html>