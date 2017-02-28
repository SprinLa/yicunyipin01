<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript">
	$(function(){
		$('#bName').combobox({
			onSelect: function(record){
				$('#sName').combobox("reload",'productSmallType_comboList.action?s_productSmallType.bigType.id='+record.id);
				$("#sName").combobox("setValue","");
			}
		});
	});
	
	
	$(function(){
		$('#pName').combobox({
			onSelect: function(record){
				$('#cName').combobox("reload",'address_ccomboList.action?s_city.province.pid='+record.pid);
				$("#cName").combobox("setValue","");
			}
		});
	});

	
	
	$(function(){
		$('#cName').combobox({
			onSelect: function(record){
				$('#tName').combobox("reload",'address_tcomboList.action?s_town.city.cid='+record.cid);
				$("#tName").combobox("setValue","");
			}
		});
	});


	var url;
    var nourl;
    
	function searchProduct(){
		$('#dg').datagrid('load',{
			"s_product.name":$("#s_productName").val()
		});
	}
	
	function openProductAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加商品信息");
	}
	
	function saveProduct(){
		url="product_save.action";
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				/* if($('#bName').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择商品大类");
					return false;
				}
				if($('#sName').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择商品小类");
					return false;
				}
				return $(this).form("validate"); */
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败");
					return;
				}
			}
		});
	}
	
	function agreeProduct(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","审核成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","审核失败");
					return;
				}
			}
		});
	}
	
	function disagreeProduct(){
		$("#fm").form("submit",{
			url:nourl,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","审核成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","审核失败");
					return;
				}
			}
		});
	}
	
	
	
	
	function resetValue(){
		$("#name").val("");
		$("#price").val("");
		$("#stock").val("");
		$("#pP").val("");
		$("#sName").combobox("setValue","");
		$("#bName").combobox("setValue","");
		$("#description").val("");
		
		$("#id").val("");
		$("#proPic").val("");
		$("#specialPrice").val("");
		$("#specialPriceTime").val("");
	}
	
	function closeProductDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openProductModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要审核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","商品信息");
		$("#id").val(row.id);
		$("#name").val(row.name);
		$("#price").val(row.price);
		$("#csName").val(row.csName);
		$("#proPic").val(row.proPic);
	    /* 	alert(row.proPic); */
		$("#tel").val(row.tel);
		$("#publishTime").val(row.publishTime);
		$("#description").val(row.description);
		$("#sName").combobox("setValue",row.smallType.id);
		$("#bName").combobox("setValue",row.bigType.id);
		$("#pName").combobox("setValue",row.province.pid);
		$("#cName").combobox("setValue",row.city.cid);
		$("#tName").combobox("setValue",row.town.tid);
		$("#userName").val(row.user.userName);
		url="product_agreeProduct.action?product.id="+row.id;
		nourl="product_disagreeProduct.action?product.id="+row.id;
		var pic="${pageContext.request.contextPath}/images/"+row.proPic ;
		document.getElementById("tupian").src=pic;
	}
	
	function deleteProduct(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("product_delete.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");							
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function setProductHot(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要设置为精品的商品！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为精品吗？",function(r){
			if(r){
				$.post("product_setProductWithHot.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已成功设置！");							
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function setProductNews(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要设置新品！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要设置这<font color=red>"+selectedRows.length+"</font>个商品为新品吗？",function(r){
			if(r){
				$.post("product_setProductWithNews.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","已成功设置！");							
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function formataddress(val,row){
		return row.province.pname+row.city.cname+row.town.tname+row.address;
	}
	
	function formatBigTypeName(val,row){
		return row.bigType.name;
	}
	
	function formatBigTypeId(val,row){
		return row.bigType.id;
	}
	
	function formatSmallTypeName(val,row){
		return row.smallType.name;
	}
	
	function formatSmallTypeId(val,row){
		return row.smallType.id;
	}
	
	function formatUser(val,row){
		return row.user.userName;
	}
	
	
	
	
	function formatstaus(val,row){
		if(row.hot==2){
			return "精品";
		}else
		
		if(row.hot==1){
			return "新品";
		}
		else{
			return "未设置";
		}
	}
	function formatstatus(val,row){
		if(row.status==0){
			return "未审核";
		}
		if(row.status==1){
			return "审核未通过";
		}
		if(row.status==2){
			return "审核通过";
		}
		
	}
	
	function formatProPic(val,row){
		return "<img width=100 height=100 src='${pageContext.request.contextPath}/images/"+val+"'/>";
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="商品管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="product_list.action" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<th field="proPic" width="150" align="center" formatter="formatProPic" >商品图片</th>
    		<th field="name" width="150" align="center" >商品名称</th>
    		<th field="price" width="50" align="center" >价格</th>
    		<th field="publishTime" width="100" align="center" >上线时间</th>
    		<th field="smallType.id" width="100" align="center" formatter="formatSmallTypeId" hidden="true">所属商品小类ID</th>
    		<th field="smallType.name" width="100" align="center" formatter="formatSmallTypeName">所属商品小类</th>
    		<th field="bigType.id" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属商品大类ID</th>
    		<th field="bigType.name" width="100" align="center" formatter="formatBigTypeName">所属商品大类</th>
    		<th field="description" width="200" align="center" hidden=true>描述</th>
    		<th field="address" width="100" align="center" formatter="formataddress">商品地址</th>
    		<th field="user" width="100" align="center" formatter="formatUser">所有人</th>
    		<th field="staus" width="100" align="center" formatter="formatstaus">商品属性</th>
    		<th field="status" width="100" align="center"  formatter="formatstatus">当前状态</th>
    		
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openProductAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:deleteProduct()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<a href="javascript:openProductModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核</a>
		<a href="javascript:setProductHot()" class="easyui-linkbutton" iconCls="icon-hot" plain="true">设置为精品</a>
		<a href="javascript:setProductNews()" class="easyui-linkbutton" iconCls="icon-special" plain="true">设置为新品</a>
	</div>
	<div>
		&nbsp;商品名称：&nbsp;<input type="text" name="s_product.name"  id="s_productName"  size="20" onkeydown="if(event.keyCode==13) searchProduct()"/>
		<a href="javascript:searchProduct()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 670px;height: 500px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post"  enctype="multipart/form-data">
  	<table cellspacing="8px;">
  		<tr>
  			<td>商品名称：</td>
  			<td colspan="4"><input class="easyui-validatebox" id="name" name="product.name"   style="width: 300px;" disabled /></td>
  		</tr>
  		<tr>
  			<td>所属会员名称：</td>
  			<td colspan="4"><input class="easyui-validatebox" id="userName" name="product.user.userName"   style="width: 300px;" disabled/></td>
  		</tr>
  		<tr>
  			<td>厂商：</td>
  			<td colspan="4"><input class="easyui-validatebox" id="csname" name="product.csName"   style="width: 300px;" disabled /></td>
  		</tr>
  		<tr>
  			<td>电话：</td>
  			<td colspan="4"><input class="easyui-validatebox" id="price" name="product.tel"  disabled /></td>
  		</tr>
  		
  	    <tr>
  			<td>所属大类：</td>
  			<td colspan="4"><input class="easyui-combobox" id="bName" name="product.bigType.id"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productBigType_comboList.action'" disabled/></td>
  		</tr>
  		<tr>
  			<td>所属小类：</td>
  			<td colspan="4"><input class="easyui-combobox"  id="sName" name="product.smallType.id" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productSmallType_comboList.action'" disabled /></td>
  		</tr>
  		
  		 <tr>
  			<td>省：</td>
  			<td colspan="4"><input class="easyui-combobox" id="pName" name="product.province.pid"  data-options="panelHeight:'auto',editable:false,valueField:'pid',textField:'pname',url:'address_pcomboList.action'" disabled /></td>
  		</tr>
  		<tr>
  			<td>市：</td>
  			<td colspan="4"><input class="easyui-combobox"  id="cName" name="product.city.cid" data-options="panelHeight:'auto',editable:false,valueField:'cid',textField:'cname',url:'address_ccomboList.action'"  disabled /></td>
  		</tr>
  		
  		 <tr>
  			<td>县：</td>
  			<td colspan="4"><input class="easyui-combobox" id="tName" name="product.town.tid"  data-options="panelHeight:'auto',editable:false,valueField:'tid',textField:'tname',url:'address_tcomboList.action'" disabled/></td>
  		</tr>
  		<tr>
  			<td>具体地址：</td>
  			<td colspan="4"><input class="easyui-validatebox" id="csname" name="product.address"   style="width: 300px;" disabled /></td>
  		</tr>
  		<tr>
  			<td valign="top">产品描述：</td>
  			<td colspan="4">
  				<textarea rows="7" cols="50" name="product.description" id="description" disabled></textarea>
  				<input type="hidden"  id="proPic" name="product.proPic" />
  				<input type="hidden"  id="id" name="product.id" />
  			</td>
  		</tr>
  		
  		<tr>
  			<td>商品图片：</td>
  			
  		</tr>
  	</table>
  	      <div><img id="tupian" src="" style="width: 500px;height: 400px;padding: 10px 20px"  /></div>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:agreeProduct()" class="easyui-linkbutton" iconCls="icon-ok" >审核通过</a>
	<a href="javascript:disagreeProduct()" class="easyui-linkbutton" iconCls="icon-cancel" >审核不通过</a>
</div>

</body>
</html>