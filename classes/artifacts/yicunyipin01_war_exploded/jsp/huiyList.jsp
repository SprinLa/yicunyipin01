<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员名单</title>


<style type="text/css">

.pager { margin-top:15px; }
.pager ul { float:right; }
.pager ul li { float:left; border:1px solid #eee; line-height:18px; padding:0 3px; margin:0 1px; display:inline; }
.pager ul li.current { font-weight:bold; color:#630; }
.newsList{margin-top:20px;}
.newsList ul.news li { margin-left:180px;width:619px;line-height:42px; font-size:13px; overflow:hidden;border-bottom:1px solid #ffefd2; }
.newsList ul.news li span {float:right }

/* .product-list ul.product li dl dt img { width:216px; height:170px; border:1px solid #ccc; }
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
.pager ul li.current { font-weight:bold; color:#630; } */

/* 新品和推荐商品 */
/* #newProduct { width:960px; margin:0 auto; }
.list-right { width:958px; background:#FFF; padding:5px; overflow:hidden }
.list-right .title { height:25px;align:left;line-height:25px; border-bottom:1px solid #CCC }
.list-right ul { padding:5px 0; width:958px; }
.list-right ul li { width:238px; height:285px; float:left; margin-left:0px; margin-top:10px;  }
.list-right ul li img { width:220px; height:200px; padding:7px;}
.list-right ul li:hover { box-shadow: 0 0 15px #cccccc; -webkit-box-shadow: 0 0 15px #cccccc; -moz-box-shadow: 0 0 15px #cccccc; -webkit-transition:.25s linear .1s;
transition:.25s linear .1s;
-moz-transition: .25s linear .1s;
}
.list-right ul li p { text-align:center; font-size:14px; color:#333; height:34px; }
.list-right ul li span { display:block; text-align:center; color:#999; font-size:14px; }
.red{ font-size:24px; letter-spacing:1px;} */
</style>

</head>
<body>
<div class="main"style="border:1px solid #bfbfbf;">
	<div class="newsList">
			<!-- <h2>全部商品</h2> -->
			<ul class="news clearfix">
				<c:forEach items="${productList}" var="p">
					<li>
								<a href="news_showNews.action?newsId=${p.id }" target="_blank">${p.name}</a>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pager">
						<ul class="clearfix">
							${pageCode}
						</ul>
			</div>
	</div>
</div>
</body>
</html>