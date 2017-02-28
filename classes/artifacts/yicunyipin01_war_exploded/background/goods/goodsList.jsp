<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>

<title>商品列表</title>
<script type="text/javascript">

function goodsDelete(id){
	if(confirm("确认要删除这条商品吗？")){
		
		$.post("product_productDelById.action",{id:id},function(result){
			if(result.success){
				alert("删除成功!");
				window.location.href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=4";
			}else{
				alert("删除失败");
			}
		},"json");
	}
}

$(document).ready(function(){
	
	var td=document.getElementsByClassName("td");
	for(var i=0;i<td.length;i++){
		
	if(td[i].innerHTML=="0"){
	  td[i].innerHTML="未审核";
	}
	 if(td[i].innerHTML=="1"){
		td[i].innerHTML="审核未通过";
	}
	 if(td[i].innerHTML=="2"){
			td[i].innerHTML="审核通过";
		}
	}
}
	
);

</script>
</head>
<body>


 <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">商品</strong> / <small>Table</small></div>
    </div>
 <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=0" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>未审核<br/>${total-okTotal-noTotal}</a></li>
      <li><a href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=2" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>审核通过<br/>${okTotal}</a></li>
      <li><a href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=1" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>审核未通过<br/>${noTotal}</a></li>
      <li><a href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=4" class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>全部商品<br/>${total}</a></li>
    </ul>
   
    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"><input type="checkbox" /></th>
                <th class="table-id">序号</th>
				<th class="table-title" >商品名称</th>
				<th class="table-type">商品类别</th>
				<th class="table-author">商品价格</th>
				<th class="table-date">上线时间</th>
				<th class="table-type">厂商名称</th>
				<th class="table-type" >商品状态</th>
				<th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
        
          <c:forEach var="goods" items="${backgoodsList}" varStatus="status">
            <tr>
              <td><input type="checkbox" name="newsIds" value="${goods.id}"/></td>
             		<td>${status.index+1 }</td>
					<td>${goods.name }</td>
					<td>${goods.bigType.name }</td>
					<td>${goods.price}</td>
					<td>${goods.publishTime}</td>
					<td>${goods.csName}</td>
					<td class="td">${goods.status}</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" type="button" onclick="javascript:window.location.href='product_productModify.action?proId=${goods.id}'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                    <button class="am-btn am-btn-default am-btn-xs am-text-danger" type="button" onclick="goodsDelete(${goods.id})"><span class="am-icon-trash-o"></span> 删除</button>
                  </div>
                </div>
              </td>
            </tr>
            </c:forEach>
           
           
          </tbody>
        </table>
          <div class="am-cf">
                  
  <div class="am-fr">
    <ul class="am-pagination">
      ${pageCode }
    </ul>
  </div>
</div>
         
        </form>
      </div>

    </div>
  </div>





</body>
</html>