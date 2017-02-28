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
	
	function openTownAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加县");
		url="address_tsave.action";
	}
	
	function saveTown(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#bName').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择");
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
		//$("#remarks").val("");
	}
	
	function closeTownDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openTownModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑县信息");
		$("#name").val(row.tname);
		//$("#remarks").val(row.remarks);
		$("#bName").combobox("setValue",row.city.cid);
		url="address_tsave.action?town.tid="+row.tid;
	}
	
	function deleteTown(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].tid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("address_tdelete.action",{ids:ids},function(result){
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
	
	function formatCityName(val,row){
		return row.city.cname;
	}
	
	function formatCityId(val,row){
		return row.city.cid;
	}
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="产地县（县/区）" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="address_townList.action" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="tid" width="50" align="center">编号</th>
    		<th field="tname" width="100" align="center" >县</th>
    		<th field="city.cid" width="100" align="center" formatter="formatCityId" hidden="true">所属市ID</th>
    		<th field="city.cname" width="100" align="center" formatter="formatCityName">所属市</th>
    	    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openTownAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openTownModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteTown()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<!--  <div>
		&nbsp;县名称：&nbsp;<input type="text" name="s_productSmallType.name"  id="s_productSmallTypeName"  size="20" onkeydown="if(event.keyCode==13) searchProductSmallType()"/>
		<a href="javascript:searchProductSmallType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>-->
</div>

<div id="dlg" class="easyui-dialog" style="width: 670px;height: 350px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  	    <tr>
  			<td>所属市：</td>
  			<td colspan="4"><input class="easyui-combobox" id="bName" name="town.city.cid"  data-options="panelHeight:'auto',editable:false,valueField:'cid',textField:'cname',url:'address_ccomboList.action'"/></td>
  		</tr>
  		<tr>
  			<td>县名称：</td>
  			<td colspan="4"><input type="text" id="name" name="town.tname" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveTown()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeTownDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

</body>
</html>