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
<title>Insert title here</title>
   

<script type="text/javascript">
	function checkChange(){
		if(document.getElementById("isImage").checked){
			$("#hdtp").show();
			$("#hdtp1").show();
			$("#hdtp2").show();
			$("#hdtp3").show();
			$("#hdtp4").show();
		}else{
			$("#hdtp").hide();
			$("#hdtp1").hide();
			$("#hdtp2").hide();
			$("#hdtp3").hide();
			$("#hdtp4").hide();
		}
	}
	
	function checkButton(){
		    $("#jiben").show();
			$("#jiben01").show();
			$("#jiben02").show();
			$("#jiben03").show();
			$("#jiben04").show();
			$("#jiben05").show();
			
		
		}
	
	function checkButton2(){
		 $("#jiben").hide();
		$("#jiben01").hide();
		$("#jiben02").hide();
		$("#jiben03").hide();
		$("#jiben04").hide();
		$("#jiben05").hide();
		
	
	}
	
	
	function checkButton3(){
	    $("#ac").show();
		$("#ac01").show();
		$("#ac02").show();
		
		
	
	}

function checkButton4(){
	$("#ac").hide();
	 $("#ac").hide();
	$("#ac01").hide();
	$("#ac02").hide();
}


function checkButton5(){
    $("#pv").show();
	$("#pv1").show();
	$("#pv2").show();
	$("#pv3").show();
	$("#pv4").show();
	$("#pv5").show();

}

function checkButton6(){
$("#pv").hide();
 $("#pv1").hide();
$("#pv2").hide();
$("#pv3").hide();
$("#pv4").hide();
$("#pv5").hide();
}


function checkButton7(){
    $("#desc").show();
	$("#desc1").show();


}

function checkButton8(){
$("#desc").hide();
 $("#desc1").hide();
}

	
	function checkForm(){
		var title=document.getElementById("name").value;
		var author=document.getElementById("adtess").value;
		var typeId=document.getElementById("typeId").value;
		var content=CKEDITOR.instances.content.getData();
		if(title==null||title==""){
			document.getElementById("error").innerHTML="商品标题不能为空！";
			return false;
		}
		if(author==null||author==""){
			document.getElementById("error").innerHTML="产地不能为空！";
			return false;
		}
		if(typeId==null||typeId==""){
			document.getElementById("error").innerHTML="请选择商品类别！";
			return false;
		}
		if(content==null||content==""){
			document.getElementById("error").innerHTML="商品内容不能为空！";
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
		${navCode}
	</div>
<!-- 	<a href="product_productAdd.action">提交</a> -->
	<div class="data_content">
	<a href="product_showProduct.action"   >测试</a>
	<form action="product_productAdd.action" method="post" id = "register" >
	
	<table>
		
		<tr >
					<td>
					<input type="text" maxlength="20"  name="product.name" id="userName" />
					</td>
		</tr>
	    
	    <tr >
					<td>
					<input type="text" maxlength="20"  name="product.price" id="userName" />
					</td>
		</tr>
		<tr >
					<td>
					<input type="text" maxlength="20"name="product.tel" id="userName" />
					</td>
		</tr>
	<tr >
					<td>
						<label>商品地址1：</label>
					</td>
					
					<td >
						 <select id="province" name="product.province">  
	  						<option value="">请选择</option>  
						 </select>  
					</td>
					
					<td >
						 <select id="city" name="product.city">  
	        				<option value="">请选择</option>  
	    				 </select>  
					</td>
					
					<td>
						 <select id="town" name="product.town">  
       						<option value="">请选择</option>  
    					 </select>  
					</td>
				</tr>
				
				<tr >
					
					
						<td  colspan="2">
							
								<input id = "subRegister" type="submit" value="添&nbsp;&nbsp;加"/>
						</td>
					</tr>
	</table>
	</form>
	
		<%-- <form action="product_productAdd.action" method="post" enctype="multipart/form-data" >
			<table cellpadding="5" width="100%">
			<!-- 基本信息 -->
			<tr>
				<td>
					<input type="button" class="btn btn-primary" value="产品基本信息" onclick="checkButton()"/>&nbsp;&nbsp;
				</td>
				
				<td id="jiben" style="display: none">
					<input type="button" class="btn btn-primary" value="收起" onclick="checkButton2()"/>&nbsp;&nbsp;
				</td>
			</tr>
				<tr id="jiben01" style="display: none">
					<td width="120px">
						<label>商品名称：</label>
					</td>
					<td>
						<input type="text" id="name" name="product.name" class="input-xxlarge" value="${product.name }"/>
					</td>
				</tr>
				
				
				<tr id="jiben02" style="display: none" >
					<td width="120px">
						<label>商品产地：</label>
					</td>
					<td>
						<input type="text" id="adress" name="adress" class="input-xxlarge" value="${product.adress}"/>
					</td>
				</tr>
				
				
				<tr id="jiben03" style="display: none" >
					<td>
						<label>联系电话：</label>
					</td>
					<td>
						<input type="text" id="tel" name="product.tel " value="${product.tel }"/>
					</td>
				</tr>
				
				<tr id="jiben04" style="display: none">
					<td>
						<label>商品原价：</label>
					</td>
					<td>
						<input type="text" id="price" name="product.price" value="${product.price }"/>
					</td>
				</tr>
				
				
				<tr id="jiben05" style="display: none">
					<td>
						<label>商品折扣价：</label>
					</td>
					<td>
						<input type="text" id="discount" name="discount" value="${product.discount }"/>
					</td>
				</tr>
				
			<!-- 基本信息 -->	
				
				<!-- 类别和地址 -->
			<tr>
				<td>
					<input type="button" class="btn btn-primary" value="类别和产地" onclick="checkButton3()"/>&nbsp;&nbsp;
				</td>
				
				<td id="ac" style="display: none">
					<input type="button" class="btn btn-primary" value="收起" onclick="checkButton4()"/>&nbsp;&nbsp;
				</td>
			</tr>
			
			
				<tr id="ac01" style="display: none">
					<td>
						<label>商品地址：</label>
					</td>
					
					<td >
						 <select id="province" name="product.province">  
	  						<option value="">请选择</option>  
						 </select>  
					</td>
					
					<td >
						 <select id="city" name="product.city">  
	        				<option value="">请选择</option>  
	    				 </select>  
					</td>
					
					<td>
						 <select id="town" name="product.town">  
       						<option value="">请选择</option>  
    					 </select>  
					</td>
				</tr>
				
			<!-- 	<tr id="ac02" style="display: none" >
					<td>
						<label>商品类别：</label>
					</td>
				
				    <td >
						 <select id="bigCategory" name="bigCategory">  
	  						<option value="">请选择</option>  
						 </select>  
					</td>
					
					<td >
						 <select id="smallCategory" name="smallCategory">  
	        				<option value="">请选择</option>  
	    				 </select>  
					</td>
				</tr> -->
			<!-- 类别和地址 -->	
				
				
<!-- 				图片和视频	
		<tr>
				<td>
					<input type="button" class="btn btn-primary" value="图片和视频" onclick="checkButton5()"/>&nbsp;&nbsp;
				</td>
				
				<td id="pv" style="display: none">
					<input type="button" class="btn btn-primary" value="收起" onclick="checkButton6()"/>&nbsp;&nbsp;
				</td>
		</tr>			 
				<tr id="pv1" style="display: none">
					<td ><label>首页展示图片</label></td>
					<td>
						<input type="file" id="picFile" name="image" />
						<input style="display: none" type="text" id="imageName" name="imageName" value=""/>
					</td>
				</tr>	
				<tr id="pv2" style="display: none">	
					<td><label>商品详细界面图片1：</label></td>
					<td>
						<input type="file" id="picFile" name="image" />
						<input type="text" style="display: none" id="imageName" name="imageName" value=""/>
					</td>
				</tr>	
				<tr id="pv3" style="display: none">	
					<td><label>商品详细界面图片2：</label>	</td>
				
					<td>
						<input type="file" id="picFile" name="image" />
						<input type="text"style="display: none" id="imageName" name="imageName" value=""/>
					</td>
				</tr>	
				<tr id="pv4" style="display: none">	
					<td><label>商品详细界面图片3：</label></td>
					<td>
						<input type="file" id="picFile" name="image" />
						<input type="text"  style="display: none" id="imageName" name="imageName" value=""/>
					</td>
				</tr>	
				<tr id="pv5" style="display: none">	
					<td><label>商品详细界面图片4：</label></td>
					<td>
						<input type="file" id="picFile" name="image" />
						<input type="text" style="display: none" id="imageName" name="imageName" value=""/>
					</td>
				</tr>	
					
				图片和视频 -->	
				
				
			<!-- 产品描述内容	 -->
	<tr>
				<td>
					<input type="button" class="btn btn-primary" value="产品描述内容" onclick="checkButton7()"/>&nbsp;&nbsp;
				</td>
				
				<td id="desc" style="display: none">
					<input type="button" class="btn btn-primary" value="收起" onclick="checkButton8()"/>&nbsp;&nbsp;
				</td>
		</tr>				
				<tr id="desc1" style="display: none">
					<td>产品描述内容：</td>
        			<td>	<textarea id="content" name="product.description" >${product.description }</textarea>	</td>
				</tr>
				
				
				
				
				
				<tr>
				   <td></td>
					<td>
						<input type="submit" class="btn btn-primary" value="保存商品"/>&nbsp;&nbsp;
					</td>	
					<td>
					<input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					</td>
				</tr>
				
			</table>
		</form> --%>
		
			
		
				
				
				
	</div>
</div>
</body>

 <script type="text/javascript" language="javascript">
$(document).ready(function(){
	//获取省级
	$.post("address_getProvince.action",function(data){      //data代表服务器端传来的数据
		var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
		for(var i=0;i<jsonObj.length;i++){
			var $option=$("<option></option>");
			$option.attr("value",jsonObj[i].pid);
			$option.text(jsonObj[i].pname);
			$("#province").append($option);
		}
	});
	//根据省获取市级
	$("#province").change(function(){
		$.post("address_getCity.action",{pid:$("#province").val()},function(data){
			//清空市级
			$("#city option[value!='']").remove();
			//清空县级
			$("#town option[value!='']").remove();
			var jsonObj=eval("("+data+")");
			for(var i=0;i<jsonObj.length;i++){
				var $option=$("<option></option>");
				$option.attr("value",jsonObj[i].cid);
				$option.text(jsonObj[i].cname);
				$("#city").append($option);
			}
		});
	});
	//根据市获取县级
	$("#city").change(function(){
		$.post("address_getTown.action",{cid:$("#city").val()},function(data){
			//清空县级
			$("#town option[value!='']").remove();
			
			var jsonObj=eval("("+data+")");
			for(var i=0;i<jsonObj.length;i++){
				$option=$("<option></option>");
				$option.attr("value",jsonObj[i].tid);
				$option.text(jsonObj[i].tname);
				$("#town").append($option);
			}
		});
	});
});
</script>

<script type="text/javascript" language="javascript">
$(document).ready(function(){
	//获取省级
	$.post("category_getBigCategory.action",function(data){      //data代表服务器端传来的数据
		var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
		for(var i=0;i<jsonObj.length;i++){
			var $option=$("<option></option>");
			$option.attr("value",jsonObj[i].tid);
			$option.text(jsonObj[i].tname);
			$("#bigCategory").append($option);
		}
	});
	//根据省获取市级
	$("#bigCategory").change(function(){
		$.post("category_getSmallCategory.action",{pid:$("#bigCategory").val()},function(data){
			//清空市级
			//$("#bigCategory option[value!='']").remove();
			//清空县级
			$("#smallCategory option[value!='']").remove();
			var jsonObj=eval("("+data+")");
			for(var i=0;i<jsonObj.length;i++){
				var $option=$("<option></option>");
				$option.attr("value",jsonObj[i].cid);
				$option.text(jsonObj[i].cname);
				$("#smallCategory").append($option);
			}
		});
	});
	
</script> 

 
</html>
<script>
	if('${news.isImage}'==1){
		$("#hdtp").show();
		$("#hdtp1").show();
		$("#hdtp2").show();
		$("#hdtp3").show();
		$("#hdtp4").show();
	}
</script>