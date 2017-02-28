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
<script type="text/javascript">
	var url;

	function searchProductBigType(){
		$('#dg').datagrid('load',{
			"s_productBigType.name":$("#s_productBigTypeName").val()
		});
	}
	
	function openProductBigTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加省");
		url="address_psave.action";
	}
	
	function saveProductBigType(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
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
	
	function resetValue(){
		$("#name").val("");
	}
	
	function closeProductBigTypeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openProductBigTypeModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑省信息");
		$("#name").val(row.name);
		$("#remarks").val(row.remarks);
		url="address_psave.action?province.pid="+row.id;
	}
	
	function deleteProductBigType(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].pid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("address_pdelete.action",{ids:ids},function(result){
					if(result.success){
						if(result.exist){
							$.messager.alert("系统提示",result.exist);
						}else{
							$.messager.alert("系统提示","数据已成功删除！");							
						}
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function formatBigTypeName(val,row){
		return row.bigProvince.name;
	}
	
	function formatBigTypeId(val,row){
		return row.bigProvince.id;
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="商品大类管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="address_provinceList.action" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="pid" width="50" align="center">编号</th>
    		<th field="pname" width="100" align="center">省名称</th>
			<th field="bigprovince.pid" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属省ID</th>
    		<th field="bigprovince.pname" width="100" align="center" formatter="formatBigTypeName">所属分区</th>
    

    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openProductBigTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openProductBigTypeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteProductBigType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;商品大类名称：&nbsp;<input type="text" name="productBigType.name"  id="s_productBigTypeName"  size="20" onkeydown="if(event.keyCode==13) searchProductBigType()"/>
		<a href="javascript:searchProductBigType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 300px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  		
  		 <tr>
  			<td>所属分区：</td>
  			<td colspan="4"><input class="easyui-combobox" id="bName" name="province.bigProvince.id"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'address_bcomboList.action'"/></td>
  		</tr>
  		<tr>
  			<td>省名称：</td>
  			<td colspan="4"><input type="text" id="province.pname" name="province.pname" class="easyui-validatebox" required="true"/></td>
  		</tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveProductBigType()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeProductBigTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

</body>
</html>