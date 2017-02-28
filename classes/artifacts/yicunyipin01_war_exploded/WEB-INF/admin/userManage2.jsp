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
	var nourl;
	function searchUser(){
		$('#dg').datagrid('load',{
			"s_user.userName":$("#s_userName").val()
		});
	}
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		url="user_saveUser.action";
	}
	
	function saveUser(){
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
	
	
	function disagreeUser(){
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
		$("#trueName").val("");
		$("#userName").val("");
		$("#password").val("");
		$("#sex").combobox("setValue","");
		$("#birthday").datebox("setValue","");
		$("#dentityCode").val("");
		$("#email").val("");
		$("#mobile").val("");
		$("#address").val("");
	}
	
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	
	/* function openUserVerifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要审核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#blg").dialog("open").dialog("setTitle","审核会员信息");
		$("#trueName").val(row.trueName);
		$("#userName").val(row.userName);
		$("#password").val(row.password);
		$("#sex").combobox("setValue",row.sex);
		$("#birthday").datebox("setValue",row.birthday);
		$("#dentityCode").val(row.dentityCode);
		$("#email").val(row.email);
		$("#mobile").val(row.mobile);
		$("#address").val(row.address);
		url="user_saveUser.action?user.id="+row.id;
		document.getElementById("tupian").src="../img/my51job_.jpg";
		
	} */
	
	/* function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
		$("#trueName").val(row.trueName);
		$("#userName").val(row.userName);
		$("#password").val(row.password);
		$("#sex").combobox("setValue",row.sex);
		$("#birthday").datebox("setValue",row.birthday);
		$("#dentityCode").val(row.dentityCode);
		$("#email").val(row.email);
		$("#mobile").val(row.mobile);
		$("#address").val(row.address);
		url="user_saveUser.action?user.id="+row.id;
	}
	 */
	
	function openUserVerifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要审核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","审核会员信息");
		
		$("#userName").val(row.userName);
		$("#trueName").val(row.trueName);
		$("#csName").val(row.csName);
		if(row.type==0){
		    $("#type").val("企业");
		}
		if(row.type==1){
			$("#type").val("个人");
		}
		$("#address").val(row.address);
		$("#dentityName").val(row.dentityName);
		$("#email").val(row.email);
		$("#mobile").val(row.mobile);
		$("#dentityPic").val(row.dentityPic);
		url="user_agreeUser.action?user.id="+row.id;
		nourl="user_disagreeUser.action?user.id="+row.id;
		var pic="${pageContext.request.contextPath}/images/"+row.dentityPic ;
		document.getElementById("tupian").src=pic;
		
	}
	
	
	
	function deleteUser(){
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
				$.post("user_deleteUsers.action",{ids:ids},function(result){
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
	
	
	function formatSmallTypeId(val,row){
		return row.smallType.id;
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
		
	
		
		function formatType(val,row){
			if(row.type==0){
				return "企业";
			}
			else{
				return "个人";
			}
			
		}
	function formatProPic(val,row){
		return "<img width=100 height=100 src='${pageContext.request.contextPath}/images/"+val+"'/>";
	}
		
	
	
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="用户管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="user_list.action?status=2" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<!-- <th field="type" width="100" align="center" formatter="formattype" >会员类别</th> -->
    		<th field="trueName" width="100" align="center">会员名称</th>
    		<th field="csName" width="100" align="center">厂商名称</th>
    		<th field="type" width="100" align="center" formatter="formatType"  >会员类别</th>
    		<th field="dentityName" width="100" align="center">个人/企业法人姓名</th>
    		<th field="dentityPic" width="150" align="center" formatter="formatProPic" >证件扫描件</th>
    	<!-- 	<th field="password" width="100" align="center">用户密码</th> -->
    		<th field="email" width="100" align="center">邮件</th>
    		<th field="tel" width="100" align="center">联系电话</th>
    		<th field="address" width="100" align="center">地址</th>
    		<th field="status" width="100" align="center"  formatter="formatstatus">当前状态</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<a href="javascript:openUserVerifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核</a>
<!-- 		<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> -->
	</div>
	<div>
		&nbsp;用户名：&nbsp;<input type="text" name="user.userName"  id="s_userName"  size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>



<div id="dlg" class="easyui-dialog" style="width: 700px;height: 400px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  		<tr>
  		     <td>详细地址：</td>
  			<td><input type="text" id="address" name="user.address" class="easyui-validatebox"  disabled/></td>
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>会员名：</td>
  			<td><input type="text" id="trueName" name="user.trueName" class="easyui-validatebox" disabled/></td>
  		</tr>
  		<tr>
  			<td>厂商名称：</td>
  			<td><input type="text" id="csName" name="user.csName" class="easyui-validatebox" disabled/></td>
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>会员类别：</td>
  			<!-- <td>
  				<select class="easyui-combobox"  id="type"  name="user.type" style="width:154px;" editable="false" panelHeight="auto">
  				    <option value="">请选择性别</option>
					<option value="1">企业1</option>
					<option value="2">个人</option>
				</select>
  			</td> -->
  			<td><input type="text" id="type" name="user.type" class="easyui-validatebox" disabled/></td>
  			
  			<!-- <td colspan="4"><input class="easyui-combobox" id="bName" name="user.type"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'name',url:'productBigType_comboList.action'" disabled/></td>
  			 -->
  		</tr>
  		
  		<tr>
  			<td>邮件：</td>
  			<td><input type="text" id="email" name="user.email" class="easyui-validatebox" disabled/></td>
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>联系电话：</td>
  			<td><input type="text" id="mobile" name="user.mobile" class="easyui-validatebox" disabled/></td>
  		</tr>
  		
  		<tr>
  			<td>身份证或企业名称：</td>
  			<td  colspan="4"><input type="text" id="dentityName" name="user.dentityName" class="easyui-validatebox" disabled/></td>
  		</tr>
  		
  		<tr>
  			<td colspan="4">身份证或企业工商营业照扫描件：</td>
  			
  		</tr>
  	</table>
  	    <div><img id="tupian" src="" style="width: 500px;height: 400px;padding: 10px 20px"  /></div>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >审核通过</a>
	<a href="javascript:disagreeUser()" class="easyui-linkbutton" iconCls="icon-cancel" >审核不通过</a>
</div>












</body>
</html>