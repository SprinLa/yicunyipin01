<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <base href="<%=basePath%>">
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
.clearfix{display:inline-table;}
*html .clearfix{height:1%;}
.clearfix{display:block;}
*+html .clearfix{min-height:1%;}
/* siftbox */
.siftbox {padding:10px 0 10px 36px;background:#F2F2F2;border-bottom:solid 1px #CCCCCC;}
.siftbox .label{float:left;margin-top:2px;font-size:14px;font-weight:800;}
.siftbox #condition{float:left;}
.siftbox .inbtn,.siftbox .inbtn span{background:url(images/closelabel.png) no-repeat;cursor:pointer;}
.siftbox .inbtn{display:inline-block;height:17px;line-height:17px;overflow:hidden;margin:4px 8px 0 0;float:left;background-position:0 0;padding:0 0 0 20px;}
.siftbox .inbtn span{background-position:100% 0;display:block;float:left;padding:0 5px 0 0;}
.siftbox .inbtn:hover{background-position:0 -17px;text-decoration:none;}
.siftbox .inbtn:hover span{background-position:100% -17px;color:#5e5e5e;text-decoration:none;}
/* sortbox */
.sortbox{border-width:2px 1px 0px 1px;border-color:#ff5500 #cccccc #cccccc #cccccc;border-style:solid;width:944px;margin:10px auto 0 auto;}
.sortbox dl{padding:0 0 15px 100px;border-bottom:solid 1px #cccccc;vertical-align:bottom;}
.sortbox dl dt{float:left;width:110px;margin:15px 0 0 -110px;text-align:right;line-height:16px;display:inline;font-weight:800;font-size:14px;color:#404040;}
.sortbox dl dd{float:left;margin:12px 18px 0 0;display:inline;}
.sortbox dl dd a{cursor:pointer;white-space:nowrap;}
.sortbox dl dd a.seling{background-color:#005AA0;color:#FFFFFF;}
.sortbox dl dd a.seled{background-color:#005AA0;color:#FFFFFF;}



</style>


</head>
<body>

<div id="tag" class="wrap">
	<jsp:include page="head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="top.jsp"/>
</div>

<div id="navbar01" class="wrap">
	<jsp:include page="navbar.jsp"/>
</div>

<div id="main" class="wrap">
	<div class="lefter">
		<jsp:include page="left.jsp"/>
	</div>
  <div class="main">
	
	<div class="title" style="text-align: left;" >${navCode}</div>
			
	<!-- 已经选择栏目 -->
	<%-- <div class="sortbox">	
	
	<div class="siftbox clearfix">
		<span class="label">已选条件:</span>
		<div id="condition"></div>
	</div>
	
	<div id="filter">
		<dl class="clearfix">
			<dt>地区：</dt>
			
			<dd><div><a>全部</a></div></dd>
			
			<c:forEach var="p" items="${cityList}" varStatus="status">
						<dd><div><a href="goods!goodListByAddress.action?aid=${p.cid}">${p.cname}</a></div></dd>
			</c:forEach>
			
		</dl>
		<dl class="clearfix">
			<dt>大种类：</dt>
			<dd><div><a>全部</a></div></dd>
			<c:forEach var="bigType" items="${bigTypeList}" varStatus="status">
				<dd><div><a href="product.action?s_product.bigType.id=${bigType.id}" >${bigType.name}</a></div></dd>
			</c:forEach>
			
		</dl>
		<dl class="clearfix">
			<dt>小种类：</dt>
			<dd><div><a>全部</a></div></dd>
			<c:forEach var="bigType" items="${bigTypeList}" varStatus="status">
					<c:forEach var="smallType" items="${bigType.smallTypeList}" varStatus="status">
						<dd><div><a href="product.action?s_product.smallType.id=${smallType.id}" >${smallType.name}</a></div></dd>
					</c:forEach>
			</c:forEach>
		</dl>
		<dl class="clearfix">
			<dt>价格：</dt>
			<dd><div><a>全部</a></div></dd>
			<dd><div><a>1000-2999</a></div></dd>
			<dd><div><a>3000-3499</a></div></dd>
			<dd><div><a>3500-3999</a></div></dd>
			<dd><div><a>4000-4499</a></div></dd>
			<dd><div><a>4500-4999</a></div></dd>
			<dd><div><a>5000-5999</a></div></dd>
			<dd><div><a>6000-6999</a></div></dd>
			<dd><div><a>7000-9999</a></div></dd>
			<dd><div><a>10000以上</a></div></dd>
		</dl>
	</div>
	
</div> --%>
<!-- 已经选择栏目 -->
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var abc = [];
$(function(){
	//选中filter下的所有a标签，为其添加hover方法，该方法有两个参数，分别是鼠标移上和移开所执行的函数。
	$("#filter a").hover(function(){
		$(this).addClass("seling");
	},function(){
		$(this).removeClass("seling");
	});

	//选中filter下所有的dt标签，并且为dt标签后面的第一个dd标签下的a标签添加样式seled。(感叹jquery的强大)
	$("#filter dt+dd a").attr("class", "seled"); /*注意：这儿应该是设置(attr)样式，而不是添加样式(addClass)，
	不然后面通过$("#filter a[class='seled']")访问不到class样式为seled的a标签。*/       

	//为filter下的所有a标签添加单击事件
	
	$("#filter a").click(function(){
		
		
		$(this).parents("dl").children("dd").each(function(){
			$(this).children("div").children("a").removeClass("seled");
		});
	
		$(this).attr("class", "seled");
		var needhide = $(this);
		needhide.parentsUntil("dl").parent().hide();
		abc.push(needhide);
		var val = $(this).html().replace(/ /g, "kongge");
		var condition = '<a class="inbtn pzbtn" rel="'+$(this).html()+'"><span onclick=deleteC("'+val+'")>'+$(this).html()+'</span></a>';
		//alert(condition);
		$("#condition").append(condition);
		
		
		 //alert(RetSelecteds()); //返回选中结果
	});
// alert(RetSelecteds()); //返回选中结果
});

function deleteC(v){
	var val = v.replace(/kongge/g, " ");
	$("#condition").find("a[rel='"+val+"']").remove();
	for(var i = 0; i < abc.length; i++){
		if(abc[i].html() == val){
			abc[i].parentsUntil("dl").parent().show();
			abc.splice(i, 1);
			i--;
		}else{
			abc[i].parentsUntil("dl").parent().hide();
		}
	}
}

function RetSelecteds(){
	var result = "";
	$("#filter a[class='seled']").each(function(){
		result += $(this).html()+"\n";
	});
	return result;
}
</script>
		

<jsp:include page="${mainPage}"/>


	
	





	<div class="spacer clear"></div>

 
		 




<div>


 
 
  
			<div class="spacer clear"></div>
			

		<div class="clear"></div>
</div>
</div>
</div>
<div id="footer">
	<jsp:include page="footer.jsp"/>
</div>
</body>
</html>