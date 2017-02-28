<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="margin: 1px;">
<table id="dg" title="客户资源统计列表" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="order_customerList.action?" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<!-- <th field="type" width="100" align="center" formatter="formattype" >会员类别</th> -->
    		<th field="memberName" width="100" align="center">商品名称</th>
    		<th field="productName" width="100" align="center">商品厂家</th>
    		<th field="buyerName" width="100" align="center">购买方名称</th>
    		<th field="buyerPhone" width="100" align="center">买方联系人手机</th>
    		<th field="buyerCompany" width="100" align="center" >买方单位</th>
    		<th field="buyerAddress" width="100" align="center" >详细地址</th>
    		<th field="buyerTime" width="100" align="center">订单时间</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<!-- <div>
		<a href="javascript:openUserDetailsDialog()" class="easyui-linkbutton" iconCls="icon-detail" plain="true">详情</a>
		<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		
	</div> -->
<!-- 	<div>
		&nbsp;用户名：&nbsp;<input type="text" name="user.userName"  id="s_userName"  size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div> -->
</div>

<div id="dlg-buttons">
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>


</body>
</html>