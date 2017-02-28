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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript">
function goodsDelete(id){
	if(confirm("确认要删除这条商品吗？")){
		$.post("goods!goodsDelete",{id:id},
			function(delFlag){
				var flag=eval('('+delFlag+')');
				if(flag){
					alert("删除成功!");
					window.location.href="${pageContext.request.contextPath}/goods!GoodsshowByUser.action";
				}else{
					alert("删除失败");
				}
			}
		);
	}
}
</script>
</head>
<body>
<div class="data_list backMain">
	<div class="dataHeader navi">
		${navCode}
	</div>
	<div class="search_content" style="vertical-align: middle;">
		<form action="${pageContext.request.contextPath}/news?action=backList" method="post">
			商品标题：<input type="text" id="s_title" name="s_title" style="width:180px" value="${s_title }"/>&nbsp;&nbsp;
			发布日期：<input type="text" id="s_bPublishDate" name="s_bPublishDate" class="Wdate" onclick="WdatePicker()" style="width: 100px;" value="${s_bPublishDate }"/>
			&nbsp;到&nbsp;<input type="text" id="s_aPublishDate" name="s_aPublishDate" class="Wdate" onclick="WdatePicker()" style="width: 100px;" value="${s_aPublishDate }"/>
			&nbsp;&nbsp;<button class="btn btn-mini btn-primary" type="submit" style="margin-top: -8px;">查询</button>
		</form>
	</div>
	<div class="data_content">
		<table class="table table-hover table-bordered">
			<tr>
				<th><input type="checkbox" id="checkedAll"/></th>
				<th>序号</th>
				<th>商品名称</th>
				<th>商品类别</th>
				<th>商品价格</th>
				<th>操作</th>
			</tr>
			<c:forEach var="goods" items="${backgoodsList }" varStatus="status">
				<tr>
					<td><input type="checkbox" name="newsIds" value="${goods.id}"/></td>
					<td>${status.index+1 }</td>
					<td>${goods.name }</td>
					<td>${goods.category.cname }</td>
					<td>${goods.price}</td>
					<%-- <td><fmt:formatDate value="${goods.publishDate }" type="date" pattern="yyyy-MM-dd"/></td> --%>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location.href='goods!goodsPreSave?id=${goods.id}'">修改</button>&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="goodsDelete(${goods.id})">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="pagination pagination-centered">
  		<ul>
  			${pageCode }
  		</ul>
  	</div>
</div>

</body>
</html>