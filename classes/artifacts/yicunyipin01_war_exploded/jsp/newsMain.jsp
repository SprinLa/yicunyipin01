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
	<jsp:include page="../common/head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="../common/top.jsp"/>
</div>

<div id="navbar01" class="wrap">
	<jsp:include page="../common/navbar.jsp"/>
</div>

<div id="main" class="wrap">
	<div class="lefter">
		<jsp:include page="leftofnews.jsp"/>
	</div>
  <div class="main" >
	
	<div class="title" style="text-align: left;" >${navCode}</div>
			
	
		
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