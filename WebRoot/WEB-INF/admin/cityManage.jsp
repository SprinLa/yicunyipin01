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

	function searchProductSmallType(){
		$('#dg').datagrid('load',{
			"s_productSmallType.name":$("#s_productSmallTypeName").val()
		});
	}
	
	function openProductSmallTypeAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加城市");
		url="address_csave.action";
	}
	
	function saveProductSmallType(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#bName').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择商品大类");
					return false;
				}
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
		$("#bName").combobox("setValue","");
		$("#name").val("");
		$("#remarks").val("");
	}
	
	function closeProductSmallTypeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openProductSmallTypeModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑商品小类信息");
		$("#name").val(row.name);
		$("#remarks").val(row.remarks);
		$("#bName").combobox("setValue",row.bigType.id);
		url="productSmallType_save.action?productSmallType.id="+row.id;
	}
	
	function deleteProductSmallType(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].cid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("address_cdelete.action",{ids:ids},function(result){
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
		return row.province.pname;
	}
	
	function formatBigTypeId(val,row){
		return row.province.pid;
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="商品小类管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="address_cityList.action" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="cid" width="50" align="center">编号</th>
    		<th field="cname" width="100" align="center" >市</th>
    		<th field="province.pid" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属省ID</th>
    		<th field="province.pname" width="100" align="center" formatter="formatBigTypeName">所属省</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openProductSmallTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openProductSmallTypeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteProductSmallType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;商品小类名称：&nbsp;<input type="text" name="s_productSmallType.name"  id="s_productSmallTypeName"  size="20" onkeydown="if(event.keyCode==13) searchProductSmallType()"/>
		<a href="javascript:searchProductSmallType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 670px;height: 350px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  	    <tr>
  			<td>所属省：</td>
  			<td colspan="4"><input class="easyui-combobox" id="bName" name="city.province.pid"  data-options="panelHeight:'auto',editable:false,valueField:'pid',textField:'pname',url:'address_pcomboList.action'"/></td>
  		</tr>
  		<tr>
  			<td>市名称：</td>
  			<td colspan="4"><input type="text" id="name" name="city.cname" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveProductSmallType()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeProductSmallTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

</body>
</html>