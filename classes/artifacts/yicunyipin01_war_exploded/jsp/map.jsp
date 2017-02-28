<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/pro.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
#address li {  
			float:left; width:80px; height:20px; line-height:24px; margin-left:10px;font-weight:bold; display:inline; margin-top:0px; overflow:hidden;
			}
</style>
<style type="text/css">
    #Con{text-align:center }
    .mapDiv { width:140px; height:61px; padding: 5px; color:#369; background: url('images/eg/bg.gif') no-repeat; position:absolute; display: none; word-break:break-all; }
</style>	
</head>
<body>

<div style="margin: 0 auto; width: 100%;  text-align: center;">
    <div style="height:auto; width: 945px; float: left; margin-top: 10px;">

		<div class="list-right fr">
				<div class="title" style="text-align: left;" >当前位置：<a href="">${ads}</a></div>
				<div class="title0001" style="margin-top:5px;text-align: left;width:945px;height:900px;border-bottom:#E7E7E7 1px solid;" >
				    
				   
		 				<font size=4px color=red> 中国一村一品精品分布</font>
<script language="javascript" src="js/jquery-1.6.4.min.js"></script>
	 				
<Div id="Con" class="ConDiv" style="border-bottom:#E7E7E7 1px solid; " >

	<img border="0" usemap="#Map" src="images/eg/map.png" />
<map name="Map" id="Map">
    <area id="beijing" alt="北京" href="${pageContext.request.contextPath}/goods!goodListByAddress.action?aid=1" coords="354,140,380,153" shape="rect">
    <area id="shanghai" alt="上海" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="434,246,462,259" shape="rect">
    <area id="tianjin" alt="天津" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="382,168,408,180" shape="rect">
    <area id="chongqing" alt="重庆" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="294,264,320,276" shape="rect">
    <area id="hebei" alt="河北" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="347,174,374,186" shape="rect">
    <area id="shanxi" alt="山西"  href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="322,186,348,198" shape="rect">
    <area id="neimenggu" alt="内蒙古" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="349,110,388,124" shape="rect">
    <area id="liaoning" alt="辽宁" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="406,128,432,140" shape="rect">
    <area id="jilin" alt="吉林" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="427,101,454,115" shape="rect">
    <area id="heilongjiang" alt="黑龙江" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="424,58,464,73" shape="rect">
    <area id="jiangsu" alt="江苏" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="404,224,417,250" shape="rect">
    <area id="zhejiang" alt="浙江" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="413,265,427,291" shape="rect">
    <area id="anhui" alt="安徽" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="382,236,395,263" shape="rect">
    <area id="fujian" alt="福建" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="399,300,413,327" shape="rect">
    <area id="jiangxi" alt="江西" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="371,286,385,313" shape="rect">
    <area id="shandong" alt="山东" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="373,196,399,208" shape="rect">
    <area id="henan" alt="河南" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="337,228,364,239" shape="rect">
    <area id="hubei" alt="湖北" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="329,258,356,271" shape="rect">
    <area id="hunan" alt="湖南" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="325,294,352,306" shape="rect">
    <area id="guangdong" alt="广东" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="356,343,382,355" shape="rect">
    <area id="guangxi" alt="广西" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="302,343,328,355" shape="rect">
    <area id="hainan" alt="海南" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="313,398,340,411" shape="rect">
    <area id="sichuan" alt="四川" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="239,265,265,277" shape="rect">
    <area id="guizhou" alt="贵州" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="283,311,308,324" shape="rect">
    <area id="yunnan" alt="云南" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="225,337,251,349" shape="rect">
    <area id="shaanxi" alt="鲜桃&nbsp;&nbsp;苹果&nbsp;&nbsp;核桃" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="303,224,316,251" shape="rect">
    <area id="gansu" alt="甘肃" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="179,156,205,168" shape="rect">
    <area id="qinghai" alt="青海" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="174,206,200,218" shape="rect">
    <area id="ningxia" alt="宁夏" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="277,188,290,212" shape="rect">
    <area id="xinjiang" alt="新疆" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="85,140,111,152" shape="rect">
    <area id="xizang" alt="西藏" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="87,249,113,261" shape="rect">
    <area id="xianggang" alt="香港" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="379,358,406,370" shape="rect">
    <area id="aomen" alt="澳门" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="349,371,375,383" shape="rect">
    <area id="taiwan" alt="台湾" href="${pageContext.request.contextPath}/goods!goodListByAddress.action" coords="434,322,448,348" shape="rect">
</map>
<script language="javascript">
$(function(){
	$("area").each(function(){
			var $x=-70;
			var $y=-80;	
			var name=$(this).attr("alt");	
			$(this).mouseover(function(e){
				var index_num=$(this).index();
				/*var dom="<div class='mapDiv'><p>提示消息<span class='name'></span><span class='num'></span></p></div>";
				$("body").append(dom);*/
				$(".name").text(name);
				$(".num").text(index_num);
				$(".mapDiv").css("display","block");
				$(".mapDiv").css({
						top: (e.pageY + $y)+"px",
						left: (e.pageX + $x)+"px"
					}).show("fast");
			}).mouseout(function(){				
					//$(".mapDiv").remove();
					$(".mapDiv").css("display","none");
			}).mousemove(function(e){
					$(".mapDiv").css({
						top: (e.pageY + $y)+"px",
						left: (e.pageX + $x)+"px",
					
				});
			});
		});	

});
</script>

<div id="box" class="mapDiv" style="font-size:15px">
<p><a href="" class="on"><span class='name'></span></a></p>
</div>
</Div>		
		 			
		 			
		</div>

</div>
</div>


</body>
</html>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
<!-- 	
	
	 
		
 -->