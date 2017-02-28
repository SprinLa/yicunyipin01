<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>一村一品网首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/base-min.js"></script>
<style type="text/css">
 .nav2-list {	height: 104px;margin-top:20px}
 .nav2-list li {	margin-top:20px;float: left;width:220px;margin-left: 40px;background:url(${pageContext.request.contextPath}/images/mnu1_bg.png) repeat-x;}
 .nav2-list a {	display: block;	height: 40px;width:220px;	float: left;	padding: 0 0px;	text-align: center;		font: bold 15px/40px "microsoft yahei";	text-decoration: none;}


.list_lh{height:250px;overflow:hidden;margin-top:3px;}
.list_lh li{padding:10px;}
.list_lh li.lieven{background:#F0F2F3;}
.list_lh li p{height:14px;line-height:14px;}
.list_lh li p a{float:left;color:red;}
.list_lh li p em{width:80px;font:normal 12px/24px Arial;color:#FF3300;float:right;display:inline-block;}
.list_lh li p span{color:#999;float:right;}
.list_lh li p a.btn_lh{background:#28BD19;height:17px;line-height:17px;color:#fff;padding:0 5px;margin-top:4px;display:inline-block;float:right;}
 .marquee {
	 width:100%;
	 /*width:590px;*/
	 height:100%;
	 overflow:hidden;
 }
 .m2 {
	 height:100%;
 }
</style>


</head>
<body>

<div id="tag" class="wrap">
	<jsp:include page="common/head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="common/top.jsp"/>
</div>

<div id="navbar01" class="wrap" >
	<jsp:include page="common/navbar.jsp"/>
</div>

<div id="main" class="wrap">
	<div class="lefter">
		<jsp:include page="common/left.jsp"/>
	</div>
  <div class="main">

	 <div style=" border:#d2364c 1px solid;width:950px;height:130px; text-align:center;float:left; ">
		 	<div style="height:36px; width:950px;border-bottom:#d2364c 1px solid;background: #E7E7E7;"><font size=5px color=red>一村一品大地鲜网络商城</font></div>

		 	<div style="margin-top:20px;font-size:21px;LINE-HEIGHT:30px;font-weight:bold;color:green">
				一村一品大地鲜网络商城是<br/>
		 			&nbsp;&nbsp;农产品中的精品，消费者的绿色高端品，农业部认证的名优品，网络购物商城
		 	</div>
	</div>


		<!-- 精品展示	 -->
<div id="newProduct" >
   <div class="list-right"style=" border:#d2364c 1px solid;">
		<div class="title"  ><a href="product_showProducts.action?product.type=1">精品展示</a></div>
		<ul class="yh">
			<c:forEach var="goods" items="${specialPriceProductList}" varStatus="status"><!-- specialPriceProductList代表的是精品展示列表，hotProductList代表新品上市列表 -->
				<li><a href="product_showProduct.action?productId=${goods.id}"><img src="images/${goods.productPic} " /></a><p><a href="product_showProduct.action?productId=${goods.id}">${goods.user.memberName}</a></p></li>
			</c:forEach>

		 </ul>
        </div>
</div>
	  <!--VIP 轮播 -->
	  <div id="focus" style="width:960px;">
	  <c:forEach var = "vip" items = "${specialPriceProductList}" begin="1" end="6">
		  <a href="product_showProduct.action?productId=${vip.id}"><span><img alt="" src="images/${vip.user.addressPic}" width="700" height="323" ></span>&nbsp;<span><img alt="" src="images/${vip.packagePic}" width="250px" height="325px" ></span>
		<p><span class="text">${vip.user.memberName}</span><span class="play"></span></p></a>
	  </c:forEach>
		  <div id="ctr">
			  <c:forEach begin="1" end="6">
				  <span></span>
			  </c:forEach>
		  </div>
	  </div>


	<%--<div id="focus" >--%>
		<%--<a href=""><img alt="" src="images/sy01.jpg" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play"></span></p></a>--%>
		<%--<a href=""><img alt="" src="images/sy02.JPG" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play"></span></p></a>--%>
		<%--<a href=""><img alt="" src="images/sy03.JPG" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play"></span></p></a>--%>
		<%--<a href=""><img alt="" src="images/sy04.JPG" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play"></span></p></a>--%>
		<%--<a href=""><img alt="" src="images/sy05.JPG" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play" ></span></p></a>--%>
		<%--<a href=""><img alt="" src="images/sy06.jpg" width="690" height="323" ><s></s><p><span class="text">孟家塬鲜桃</span><span class="play" ></span></p></a>--%>
	<%--<div id="ctr">--%>
		<%--<span></span>--%>
		<%--<span></span>--%>
		<%--<span></span>--%>
		<%--<span></span>--%>
		<%--<span></span>--%>
		<%--<span></span>--%>
	<%--</div>--%>
<%--</div>--%>

<script type="text/javascript" src="js/Tab-min.js"></script>
<script type="text/javascript">
t1 = new Tab("ctr", "span", "focus", "a", {
    autoPlay : true,
    timeout : 3000,
    delay:200,
    event : "mouseover"
});

</script>

<%--<div id="vip">--%>
   <%--<div>--%>
		 <%--<img alt="" src="images/tao2.jpg" width="250" height="325" />--%>
	<%--</div>--%>
<%--</div>--%>

<div class="spacer clear"></div>



<!-- 开通标题	 -->
<div>
    <div style=" border:#d2364c 1px solid;width: 950px;height:90px;font-size:40px;color:red;text-align:center; margin-top: 10px;">
    <img alt="" src="images/qz01.png" width=950px height=90px; />
    </div>
</div>
<div id="newProduct" >
   <div class="list-right" style=" border:#d2364c 1px solid;">
		<div class="title"  ><a href="product_showProducts.action?product.type=2">新品上市</a></div>
		<ul class="yh">
			<c:forEach var="goods" items="${hotProductList}" varStatus="status">
				<li><a href="product_showProduct.action?productId=${goods.id}"><img src="images/${goods.productPic} " /></a><p><a href="product_showProduct.action?productId=${goods.id}">${goods.user.memberName}</a></p></li>
			</c:forEach>

		 </ul>
        </div>
</div>
<!-- 新闻加登陆 -->
<div>

  <div style=" border:#d2364c 1px solid;width: 590px;height:280px; float: left; margin-top: 10px;overflow:hidden;">
	<div style="width:598px;font-size:15px;color:red;float:left;border-bottom:1px solid #ddd;background: #E7E7E7;height:30px;">&nbsp;&nbsp;实地要闻:<span style="margin-left:220px;margin-top:20px;"></span>
	</div>

	 <div style="float:left;font-size:13px;width:590px;height:100%;">
		 <div style=" overflow:hidden;padding:0px; padding: .5em"id="marquee3" class='marquee m2'>
		 </div>
	 </div>
 </div>

	<script language="JavaScript">
		function BYMarquee(){
			<%--this.Content = "${NewsArray}"; //显示内容--%>
			this.Content = []; //显示内容
			this.Speed = 20; //上移速度
			this.Object = {}; //marquee容器对象
			this.Pause = true; //是否停留
			this.Start = function(){
				var o = this.Object;
				var Pause = this.Pause;
				var Stop = false;
				var Stop2 = false;
				o.onmouseover = function(){
					Stop = Stop2 = true;
				}
				o.onmouseout = function(){
					Stop = Stop2 = false;
				}
				var BodyHtml = [];
				for(var i in this.Content){
					BodyHtml.push("<a href=\"" + this.Content[i].url + "\">" + this.Content[i].title + "</a>"+"<span style=\"float:right;margin-right:10px;\">["+this.Content[i].time+"]</span>");
				}
//				alert(this.Content[i].url);
//				for(var i in this.Content){
//					BodyHtml.push("<a href=\"" + this.Content[i].link + "\">" + this.Content[i].text + "</a>");
//				}
				var Dv = document.createElement("div");
				Dv.innerHTML = BodyHtml.join("<br />");
				o.appendChild(Dv);
				var DvHeight = Dv.offsetHeight;
				while(Dv.offsetHeight / 2 < o.offsetHeight){
					Dv.innerHTML += "<br />" + Dv.innerHTML;
				}
				Dv.innerHTML += "<br />" + Dv.innerHTML;
				setInterval(function(){
					if(!Stop){
						o.scrollTop ++;
						if(o.scrollTop == o.scrollHeight - o.offsetHeight){
							o.scrollTop = DvHeight - o.offsetHeight;
						}
						if(Pause){
							if(o.scrollTop % o.offsetHeight == 0){
								Stop = true;
								setTimeout(function(){
									Stop = Stop2;
								}, 3000);
							}
						}
					}
				}, this.Speed);
			}
		}

		//实例3
		var marquee3 = new BYMarquee();
		marquee3.Content = ${NewsArray};
		marquee3.Speed = 50;
		marquee3.Pause = false;
		marquee3.Object = document.getElementById('marquee3');
		marquee3.Start();

		//销毁
		marquee3 = null;
	</script>

<!--  会员申请 -->
  <div style=" border:#d2364c 1px solid;width: 356px;height:280px; float: left;margin-top: 10px;">

              <div style="font-size:18px;height:30px;;color:red;text-align:center;border-bottom:1px solid #ddd;background: #E7E7E7;">&nbsp;&nbsp;&nbsp;&nbsp;会员申请</div>
	 <div id="idlogin1">
		<ul class="nav2-list cf" style="line-height:30px;">
	              <li style="width:160px;background: #FF4D63;"> <a style="width:160px;color: #fff;" href="${pageContext.request.contextPath}/jsp/register.jsp">普通会员申请</a></li>

	              <li style="width:160px;" > <a style="width:160px;" href="${pageContext.request.contextPath}/jsp/register.jsp">VIP会员申请</a></li>
		 					 </ul>

	</div>
	<div style="margin-top: 40px;margin-left:40px;"> 已是会员，现在 <a href="${pageContext.request.contextPath}/jsp/login.jsp"><font color="red">登陆</font></a> </div>
</div>

			<div class="spacer clear"></div>
		</div>
		</div>
		<div class="clear"></div>
</div>

<div id="footer">
	<jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>