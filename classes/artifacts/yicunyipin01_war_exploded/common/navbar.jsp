<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.yicunyipin.entity.*"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
/**
*菜单的构造,需要绑定到onload
*/
startList = function() {
    if (document.all&&document.getElementById) {
        navRoot = document.getElementById("nav");
            for (i=0; i<navRoot.childNodes.length; i++) {
                node = navRoot.childNodes[i];
                if (node.nodeName=="LI") {
                    node.onmouseover=function() {
                    this.className+=" over";
                }
                node.onmouseout=function() {
                    this.className=this.className.replace(" over", "");
                }
            }
        }
    }
}
window.onload=startList;

function findOrder(){
    if('${currentUser.userName}'==''){
        alert("请先登录，然后订购！点击确定后将跳转至登录界面!");
        window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
    }else {
        window.open("${pageContext.request.contextPath}/order_findOrder.action");
    }
}
function memberService(){
    if('${currentUser.userName}'==''){
        alert("请先登录，才能使用会员服务!");
        window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
    }else {
        window.location.href="${pageContext.request.contextPath}/background/mainTemp.jsp";
    }
}
</script>
<style type="text/css" media="all">


#gouwu{
    margin: 0 auto;
    text-align:left;
    padding: 0;
    list-style: none;
    z-index:99;
}

 
#gouwu li{
    display:block;
}
#gouwu {
    width:50px;
    height:auto;
    position: absolute;
    text-align:left;
    top:195px;
    display: none;
    border:solid 0px #697210;
}
 
/*当鼠标在子菜单和父菜单上时，父菜单的样式*/
#tt.over a,#tt:hover a{
    border-color:#E2144A;
    background: #fdd;
    font-weight:bold; 
    color: #E2144A;
}
/*将子菜单的样式清除*/
#tt.over ul a,#tt:hover ul a{
    background:#fff;
    font-weight:normal;
    color:#777;
}
/*子菜单的hover样式*/
#tt.over ul a:hover,#tt:hover ul a:hover{
    background:#fff;
    font-weight:normal;
    color: #E2144A;
    background: #fdd; 
    border-color:#E2144A;
    font-weight:bold;
}
 
 
#gouwu li{
    width:100px;
    border:0;
}
/* Fix IE. Hide from IE Mac \*/
* html #tt { float: left; height: 17px; }
* html #tt a {  height: 17px; }
/* End */
 
#gouwu a { padding: 2px 0px 2px  10px;border:0;width:90px;     } /* Sub Menu Styles */
        
#tt:hover ul,#tt.over ul { display: block; } /* The magic */
</style>
</head>

<body>

 <div class="nav-wrap" style="background:url(${pageContext.request.contextPath}/images/mnu1_bg.png) repeat-x;">
			 <div style="margin-left:10%">
                <ul class="nav-list cf">
                  <li> <a href="${pageContext.request.contextPath}/refresh_refreshPage.action" class="index">首页</a> </li>
                  <li><a href="${pageContext.request.contextPath}/policy.action">产业政策</a></li>
                    <li id="tt"><a href=''>精品展示</a>
                        <ul id="gouwu"><li><a href="${pageContext.request.contextPath}/product_showProducts.action?product.type=1">精品列表</a></li></ul>
                    </li>
                    <li id="tt"><a href=''>新品展示</a>
                        <ul id="gouwu">
                  <li>	<a href="${pageContext.request.contextPath}/product_showProducts.action?product.type=2">新品列表</a></li></ul>
                    </li>
                   <li id="tt"><a href=''>购物服务</a> 
       				 <ul id="gouwu">
           				 <li><a href="${pageContext.request.contextPath}/product_showProducts.action?product.type=0">订单服务</a></li>
            			 <%--<li><a href="${pageContext.request.contextPath}/order_findOrder.action">我的订单</a></li>--%>
            			 <li><a href="javascript:findOrder()">我的订单</a></li>
            		</ul>
            		</li>
               <li><a href="">交易商城</a></li>
               <li><a href="">农家乐园</a></li>
               <li><a href="">鲜果庄园</a></li>
				<%-- <li><a href="${pageContext.request.contextPath}/goods!goodGoodsList.action">一乡一业</a></li> --%>
				
				
				<li id="tt"><a href=''>会员服务</a> 
       				 <ul id="gouwu">
           				 <li><a href="${pageContext.request.contextPath}/policy_fuwuzongzhi.action">服务宗旨</a></li>
            			 <%--<li><a href="${pageContext.request.contextPath}/order_findOrder.action">我的订单</a></li>--%>
            			 <li><a href="javascript:memberService()">会员服务</a></li>
            		</ul>
            		</li>
				
				<li id="tt"><a href=''>联系我们</a> 
       				 <ul id="gouwu">
           				 <li><a href="${pageContext.request.contextPath}/policy_lianxiwomen.action">联系我们</a></li>
            			 <%--<li><a href="${pageContext.request.contextPath}/order_findOrder.action">我的订单</a></li>--%>
            			 <li><a href="${pageContext.request.contextPath}/policy_xiazai.action">APP下载</a></li>
            		</ul>
            		</li>
				<li><a target="_blank" href="admin/login.jsp">后台管理</a></li>
				
                </ul>
              </div>
</div> 
</body>
</html>
