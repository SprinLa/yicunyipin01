<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf"%>
<%@ taglib uri="http://ckeditor.com" prefix="ck"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>

<base href="<%=basePath%>">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加商品</title>
   
<style type="text/css">
  .code1{color:red}
   .code2{color:green}
   .require-red{color:red;}
</style>

 <script type="text/javascript" language="javascript">
		
		
		 $(document).ready(function(){
				//获取省级
				var province=document.getElementById("pid").value;
				var city=document.getElementById("cid").value;
				var town=document.getElementById("tid").value;
				
				
				$.post("address_getProvince1.action",function(data){      //data代表服务器端传来的数据
					var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
					for(var i=0;i<jsonObj.length;i++){
						var $option=$("<option></option>");
						$option.attr("value",jsonObj[i].pid);
						$option.text(jsonObj[i].pname);
						$("#province").append($option);
					}
					 $(".province").val(province);
				});	
				
				var cityUrl="address_getCitys.action?s_province.pid="+province;
				$.post(cityUrl,function(data){      //data代表服务器端传来的数据
					var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
					for(var i=0;i<jsonObj.length;i++){
						var $option=$("<option></option>");
						$option.attr("value",jsonObj[i].cid);
						$option.text(jsonObj[i].cname);
						$("#city").append($option);
					}
					 $(".city").val(city);
					/*  $("#city").each(function(){this.selectedIndex=city}); */
				});	
				
				var townUrl="address_getTowns.action?s_city.cid="+city;
				$.post(townUrl,function(data){      //data代表服务器端传来的数据
					var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
					for(var i=0;i<jsonObj.length;i++){
						var $option=$("<option></option>");
						$option.attr("value",jsonObj[i].tid);
						$option.text(jsonObj[i].tname);
						$("#town").append($option);
					}
					 $(".town").val(town);
				});	
				
					
				
		//根据省获取市级
		$("#province").change(function(){
			
			
			$.post("address_getCity1.action",{pid:$("#province").val()},function(data){
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
			$.post("address_getTown1.action",{cid:$("#city").val()},function(data){
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
	var bigid=document.getElementById("bigid").value;
	var smaid=document.getElementById("smaid").value;
	$.post("category_getBigType.action",function(data){      //data代表服务器端传来的数据
		var jsonObj=eval("("+data+")");                    //封装服务器数据为json对象
		for(var i=0;i<jsonObj.length;i++){
			var $option=$("<option></option>");
			$option.attr("value",jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#bigCategory").append($option);
		}
		
		$(".bigCategory").val(bigid);
	});
	
	$.post("category_getSmallType.action",{caId:bigid},function(data){
		var jsonObj=eval("("+data+")");
		for(var i=0;i<jsonObj.length;i++){
			var $option=$("<option></option>");
			$option.attr("value",jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#smallCategory").append($option);
		}
		
		$(".smallCategory").val(smaid);
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

<script type="text/javascript">

function checkPhone(){
	var bool=true;
	var regCode = /^1[3|4|5|8][0-9]\d{4,8}$/;
	var q = document.getElementById("txtmobile");
	var name = $("#tel").val();
	 if(!name) {
		 q.innerHTML = "<p class='code1'>不能为空!</p>";
			bool=false;
		 } 
	 else if(regCode.test(name) == false){
			q.innerHTML = "<p class='code1'>请输入格式正确的手机号</p>";
			bool=false;
		}
	 else{
		 q.innerHTML = "<p class='code2'>ͨ通过</p>";
		}
		return bool;
}
	
	function checkForm(){
		
		
		var name=document.getElementById("name").value;
		if (name==""){ 
			alert("商品标题不能为空！"); 
			document.getElementById("name").focus();
			return(false); 
			} 
		
		var csName=document.getElementById("csName").value;
		if(csName==null||csName==""){
			alert("厂商不能为空！"); 
			document.getElementById("csName").focus();
			document.getElementById("error").innerHTML="厂商不能为空！";
			return(false);
		}
		
		
		var price=document.getElementById("price").value;
		if(price==null||price==""){
			alert("价格不能为空！"); 
			document.getElementById("error").innerHTML="价格不能为空！";
			return false;
		}
		
		var tel=document.getElementById("tel").value;
		if(tel==null||tel==""){
			alert("电话不能为空！"); 
			document.getElementById("error").innerHTML="电话不能为空！";
			return false;
		}
		
		var province=document.getElementById("province").value;
		if(province==null||province==""){
			alert("请选择商品类别地址！"); 
			document.getElementById("error").innerHTML="请选择商品类别地址！";
			return false;
		}
		
		var bigCategory=document.getElementById("bigCategory").value;
		if(bigCategory==null||bigCategory==""){
			alert("请选择商品类别！"); 
			document.getElementById("error").innerHTML="请选择商品类别！";
			return false;
		}
		
		
		var picFile=document.getElementById("picFile").value;
		if(picFile==null||picFile==""){
			alert("首页图片不能为空！"); 
			document.getElementById("error").innerHTML="首页图片不能为空！";
			return false;
		}
		
		var des=document.getElementById("des").value;
		if(des==null||des==""){
			alert("商品描述不能为空"); 
			document.getElementById("error").innerHTML="商品描述不能为空";
			return false;
		}
		var con=document.getElementById("con").value;
		if(con==null||con==""){
			alert("商品描述不能为空！"); 
			document.getElementById("error").innerHTML="商品描述不能为空";
			return false;
		}
		
		
	
		
		return true;
	}
	
	
	
	
	function checksp(){
		var bool=true;
		var q = document.getElementById("txtName");
		var name = $("#name").val();
		 if(!name) {
			 q.innerHTML = "<p class='code1' >商品名不能为空!</p>";
				bool=false;
			 } else{
				 q.innerHTML = "<p class='code2' >通过</p>";
			}
			return bool;
	}
	
	
</script>

<script type="text/javascript">
	var editor = null;
	window.onload = function() {
		editor = CKEDITOR.replace('content');
		CKFinder.setupCKEditor(editor, '/ckedit/ckfinder2.3.1/');
	};
	
	var editor1 = null;
	window.onload = function() {
		editor = CKEDITOR.replace('content1');
		CKFinder.setupCKEditor(editor, '/ckedit/ckfinder2.3.1/');
	};

</script>

</head>
<body>





<!-- content start -->
<div class="admin-content">

  <div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">增加商品</strong> / <small>Goods</small></div>
  </div>
  <form name="form1" action="product_productAdd.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
  										  
  <div class="am-tabs am-margin" data-am-tabs>
    
    <ul class="am-tabs-nav am-nav am-nav-tabs">
       <li class="am-active"><a href="#tab1">基本信息</a></li>
	   <li><a href="#tab2">产地类别</a></li>
      <li><a href="#tab3">详细描述</a></li>
    </ul>

    <div class="am-tabs-bd">
    
      <div class="am-tab-panel am-fade am-in am-active" id="tab1">
	  
          
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="productid" name="product.id" value="${product.id}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="bigid" name="bigid" value="${product.bigType.id}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="smaid" name="smaid" value="${product.smallType.id}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
         
           <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="pid" name="pid" value="${product.province.pid}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
         
           <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="cid" name="cid" value="${product.city.cid}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
           <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="tid" name="tid" value="${product.town.tid}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         
         
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="productid" name="product.publishTime" value="${product.publishTime}"class="am-form-field am-input-sm""/>
            </div>
         </div>
         <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"> </div>
            <div class="am-u-sm-4 ">
              <input  type="hidden" id="productid" name="product.hot" value="${product.hot}"class="am-form-field am-input-sm""/>
            </div>
         </div>
          
  		<div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>商品名称 </div>
            <div class="am-u-sm-4 ">
              <input type="text" name="product.name"  id="name"  onblur="checksp()" value="${product.name }" class="am-form-field am-input-sm">
            </div>
            <div class="am-u-sm-6" id="txtName" ></div>
         </div>
		  
	  <div class="am-g am-margin-top-sm">
	            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
	              厂商名称
	            </div>
	            <div class="am-u-sm-4 am-u-end">
	              <input type="text" id="csName" name="product.csName " value="${product.csName }" class="am-form-field am-input-sm">
	            </div>
	   </div>

		<div class="am-g am-margin-top-sm">
		            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
		              价格
		            </div>
		            <div class="am-u-sm-4 am-u-end">
		              <input type="text" id="price" name="product.price "  value="${product.price }"class="am-form-field am-input-sm">
		            </div>
		   </div>

	<div class="am-g am-margin-top-sm">
	            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
	              联系电话
	            </div>
	            <div class="am-u-sm-4 am-u-end">
	              <input type="text" id="tel" name="product.tel" onblur="checkPhone()" value="${product.tel }" class="am-form-field am-input-sm" placeholder="请输入有效 联系电话">
	             <small id="txtmobile"></small>
	            </div>
	   </div>   
	   
	   
	    <hr/>
          
		   <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
              	首页展示图片:
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="file" class="" id="picFile" name="image" value="">
            </div>
          </div>
          
          <!--  <div class="am-g am-margin-top-sm">
                  <img src="images/b1.jpg" width=200px height=200px;/> 
            </div> -->
          
          
           <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
          		    商品图片1
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="file" class="am-input-sm" id="picFile" name="image" value="c:/a好.jpg" >
            </div>
          </div>
          
            <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
          		    商品图片2
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="file" class="am-input-sm" id="picFile" name="image">
            </div>
          </div>
          
            <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
          		    商品图片3
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="file" class="am-input-sm" id="picFile" name="image">
            </div>
          </div>
          
            <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right">
          		    商品图片4
            </div>
            <div class="am-u-sm-4 am-u-end">
              <input type="file" class="am-input-sm" id="picFile" name="image">
            </div>
          </div>
           <hr/>
	   
	   
      

      </div>

      <div class="am-tab-panel am-fade" id="tab3">
	   
        <div class="am-form">

         
         
          
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
              	产品描述内容：
            </div>
            <div class="am-u-sm-10">
              <textarea rows="10" id="des" name="product.description" value="${product.description } ">${product.description }</textarea>
            </div>
          </div>
          
           <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
              	村容村貌内容：
            </div>
            <div class="am-u-sm-10">
              <textarea rows="10"  id="con" name="product.content" value="${product.content }" >${product.content }</textarea>
            </div>
          </div>
		  
        </div>
      </div>

      <div class="am-tab-panel am-fade" id="tab2">

		 <div class="am-form">
			<hr/>
			<div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
              商品产地:
            </div>
            <div class="am-u-sm-8 am-u-end">
               <div class="am-g am-margin-top">
				  <div class="am-u-sm-4">
					 <select id="province" class="province" name="product.province.pid" >  
	  						<option value="">请选择</option>  
					 </select> 
				  </div>	

					<div class="am-u-sm-4">
					 <select id="city"  class="city" name="product.city.cid" >  
	        				<option value="">请选择</option>
	    			</select> 
				  </div>	

						<div class="am-u-sm-4">
							<select id="town" class="town" name="product.town.tid"  >  
       						    <option value="">请选择</option> 
    			            </select>
						  </div>					  
               </div>
            </div>
          </div>	
		
		  <hr/>
          
          <div class="am-g am-margin-top-sm">
	            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
	              	   详细地址:
	            </div>
	            <div class="am-u-sm-4 am-u-end">
	              <input type="text" id="address" name="product.address"  value="${product.address }" class="am-form-field am-input-sm" >
	             <small id="txtmobile"></small>
	            </div>
	   </div>   
	   
		  
		  <hr/>
		  
		  <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
              商品类别:
            </div>
            <div class="am-u-sm-6 am-u-end">
              <div class="am-g am-margin-top">
				  <div class="am-u-sm-6">
					 <select id="bigCategory" class="bigCategory"name="product.bigType.id" >  
	  						<option value="">请选择</option>  
						 </select>  
				  </div>	

					<div class="am-u-sm-6">
					 <select id="smallCategory" class="smallCategory" name="product.smallType.id"  >  
	        				<option value="">请选择</option>  
	    				 </select>  
				  </div>	

										  
               </div>
            </div>
          </div>
  <hr/>

        </div>
      </div>

    </div>
  </div>

  <div class="am-margin">
  	<input type="submit" class="am-btn am-btn-primary am-btn-xs" value="提交保存"/>
    <button type="button" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
    <font id="error" color="red">${error }</font>
  </div>
  </form>
  
</div>
<!-- content end -->

</body>


 
</html>
