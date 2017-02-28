<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>

<base href="<%=basePath%>">
<script type="text/javascript" src="ckeditor4.2/ckeditor.js"></script>
<script type="text/javascript" src="ckfinder2.3.1/ckfinder.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
   

<script type="text/javascript">
	function checkForm(){
		var title=document.getElementById("title").value;
		var author=document.getElementById("author").value;
		var typeId=document.getElementById("typeId").value;
		var content=CKEDITOR.instances.content.getData();
		if(title==null||title==""){
			document.getElementById("error").innerHTML="新闻标题不能为空！";
			return false;
		}
		if(author==null||author==""){
			document.getElementById("error").innerHTML="产地不能为空！";
			return false;
		}
		if(typeId==null||typeId==""){
			document.getElementById("error").innerHTML="请选择新闻类别！";
			return false;
		}
		if(content==null||content==""){
			document.getElementById("error").innerHTML="新闻内容不能为空！";
			return false;
		}
		return true;
	}
</script>

<script type="text/javascript">
	var editor = null;
	window.onload = function() {
		editor = CKEDITOR.replace('content');
		CKFinder.setupCKEditor(editor, '/ckedit/ckfinder2.3.1/');
	};

	/* editor = CKEDITOR.replace('content', {
		customConfig : '${contextPath}/ckeditor4.1/myconfig.js',
		on : {
			instanceReady : function(ev) {
				this.dataProcessor.writer.setRules('p', {
					indent : false,
					breakBeforeOpen : false, //<p>之前不加换行
					breakAfterOpen : false, //<p>之后不加换行
					breakBeforeClose : false, //</p>之前不加换行
					breakAfterClose : false
				//</p>之后不加换行7
				});
			}
		}
	});
	CKFinder.setupCKEditor(editor, '/ckfinder2.3.1/'); */
</script>

</head>
<body>
<div class="data_list backMain">
	<div class="dataHeader navi">
		当前位置${navCode}
	</div>
	<div class="data_content">
		<form action="news_newsAdd.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
			<table cellpadding="5" width="100%">
			<!-- 基本信息 -->
			
			<tr id="jiben01" >
					<td width="120px">
						<label>新闻id：</label>
					</td>
					<td>
						<input type="text" id="name" name="news.id " class="input-xxlarge" value="${news.id }"/>
					</td>
				</tr>
			
				<tr id="jiben01" >
					<td width="120px">
						<label>新闻标题：</label>
					</td>
					<td>
						<input type="text" id="title" name="news.title " class="input-xxlarge" value="${news.title }"/>
					</td>
				</tr>
				
				
				
				
				<tr id="jiben03"  >
					<td>
						<label>作者：</label>
					</td>
					<td>
						<input type="text" id="author" name="news.author" value="${news.author }"/>
					</td>
				</tr>
				
				
				
				
				
			
			
				
				<tr id="ac02"  >
					<td>
						<label>新闻类别：</label>
					</td>
				
				    <td >
						 <select id="bigCategory" name="news.newsType.id" >  
	  						<option value="">请选择</option>
	  					 <c:forEach var="newsType" items="${NewsTypeList}" varStatus="status">
	  						<option value="${newsType.id}">${newsType.typeName}</option>
	  					 </c:forEach> 
						 </select>  
					</td>
					
					
				
				
				
				</tr>
	
			<!-- 产品描述内容	 -->
				<tr id="desc1" >
					<td>产品描述内容：</td>
        			<td>	<textarea id="content" name="news.content" >${news.content }</textarea>	</td>
				</tr>
				
				
				
				
				
				<tr>
				   <td></td>
					<td>
						<input type="submit" class="btn btn-primary" value="保存新闻"/>&nbsp;&nbsp;
					</td>	
					<%-- <td>
					<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					</td> --%>
				</tr>
				
			</table>
		</form>
		
			
		
				
				
				
	</div>
</div>
</body>

 <script type="text/javascript" language="javascript">
 

<script type="text/javascript" language="javascript">
$(document).ready(function(){
	//获取省级
	$.post("category_getBigType.action",function(data){      //data代表服务器端传来的数据
		var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
		for(var i=0;i<jsonObj.length;i++){
			var $option=$("<option></option>");
			$option.attr("value",jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#bigCategory").append($option);
		}
	});
	//根据省获取市级
	$("#bigCategory").change(function(){
		$.post("category_getSmallType.action",{caId:$("#bigCategory").val()},function(data){
			//清空市级
			//$("#bigCategory option[value!='']").remove();
			//清空县级
			$("#smallCategory option[value!='']").remove();
			var jsonObj=eval("("+data+")");
			for(var i=0;i<jsonObj.length;i++){
				var $option=$("<option></option>");
				$option.attr("value",jsonObj[i].id);
				$option.text(jsonObj[i].name);
				$("#smallCategory").append($option);
			}
		});
	});
});
</script> 

 
</html>
