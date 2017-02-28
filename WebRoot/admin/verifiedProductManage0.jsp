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
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	var url;
	var nourl;
	function closeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	function searchUser(){
		$('#dg').datagrid('load',{
			"s_user.userName":$("#s_userName").val()
		});
	}
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		url="user_saveUser.action";
	}
	
	function saveProduct(){
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
	function disagreeProduct(){
		$("#fm").form("submit",{
			url:nourl,
			onSubmit:function(){
				var content=$("#verifiedReason").val();
				if(content==null || content==""){
					$.messager.alert("系统提示","未通过原因不能为空！");
					return false;
				}
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
	
	function openUserModifyDialog(){
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
	function openProductVerifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要审核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#verifiedBtn").css('display',''); 
		$("#verifiedNoBtn").css('display',''); 
		$("#closeBtn").css('display','none'); 
		$("#dlg").dialog("open").dialog("setTitle","会员产品提交信息");
		
		//$("#productName").val(row.productName);
		$("#provinceCity").val(row.user.provinceCity+row.user.memberName);
		//$("#legal").val(row.legal);
		$("#contactName").val(row.user.contactName);
		if(row.user.type==1){
			$("#type").val("普通");
			}
		else if(row.user.type==2){
			$("#type").val("VIP");
		}
		$("#memberName").val(row.user.memberName);
		
		if(row.type==1){
			$("#productType").val("精品");
			}
		else if(row.type==2){
			$("#productType").val("新品");
		}
		$("#bigType").val(row.bigType.name);
		$("#smallType").val(row.smallType.name);
		$("#address").val(row.address);
		
		$("#productInfo").val(row.productInfo);
		$("#verifiedReason").val(row.verifiedReason);
		$("#productAddressInfo").val(row.productAddressInfo);
		//url="user_agreeUser.action?user.id="+row.id;
		//nourl="user_disagreeUser.action?user.id="+row.id;
		var productPic="${pageContext.request.contextPath}/images/"+row.productPic ;
		document.getElementById("productPic").src=productPic;
		var packagePic="${pageContext.request.contextPath}/images/"+row.packagePic ;
		document.getElementById("packagePic").src=packagePic;
		var productAddressPic="${pageContext.request.contextPath}/images/"+row.productAddressPic ;
		document.getElementById("productAddressPic").src=productAddressPic;
		url="tbproduct_agreeProduct.action?product.id="+row.id;
		nourl="tbproduct_disagreeProduct.action?product.id="+row.id;
		
		
	}
	function openProductDetailsDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要审核的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#verifiedBtn").css('display','none'); 
		$("#verifiedNoBtn").css('display','none'); 
		$("#closeBtn").css('display',''); 
		$("#dlg").dialog("open").dialog("setTitle","会员产品提交信息");
		
		//$("#productName").val(row.productName);
		$("#provinceCity").val(row.user.provinceCity);
		//$("#legal").val(row.legal);
		$("#contactName").val(row.user.contactName);
		if(row.user.type==1){
			$("#type").val("普通");
			}
		else if(row.user.type==2){
			$("#type").val("VIP");
		}
		$("#memberName").val(row.user.memberName);
		
		if(row.type==1){
			$("#productType").val("精品");
			}
		else if(row.type==2){
			$("#productType").val("新品");
		}
		$("#bigType").val(row.bigType.name);
		$("#smallType").val(row.smallType.name);
		$("#address").val(row.address);
		
		$("#productInfo").val(row.productInfo);
		$("#verifiedReason").val(row.verifiedReason);
		$("#productAddressInfo").val(row.productAddressInfo);
		//url="user_agreeUser.action?user.id="+row.id;
		//nourl="user_disagreeUser.action?user.id="+row.id;
		var productPic="${pageContext.request.contextPath}/images/"+row.productPic ;
		document.getElementById("productPic").src=productPic;
		var packagePic="${pageContext.request.contextPath}/images/"+row.packagePic ;
		document.getElementById("packagePic").src=packagePic;
		var productAddressPic="${pageContext.request.contextPath}/images/"+row.productAddressPic ;
		document.getElementById("productAddressPic").src=productAddressPic;
	}
	
	function deleteProduct(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("tbproduct_delete.action",{ids:ids},function(result){
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
	
	
	function formatSmallTypeId(val,row){
		return row.smallType.id;
	}
	
	
		function formatstatus(val,row){
			if(row.verified==0){
				return "未审核";
			}
			if(row.verified==2){
				return "审核未通过";
			}
			if(row.verified==1){
				return "审核通过";
			}
			
		}
		
		function formatType(val,row){
			if(row.user.type==1){
				return "普通会员";
			}
			else if(row.user.type==2){
				return "VIP";
			}
			
		}
		
		function formatProductType(val,row){
			if(row.type==1){
				return "精品";
			}
			else if(row.type==2){
				return "新品";
			}
			
		}
	function formatProPic(val,row){
		return "<img width=100 height=100 src='${pageContext.request.contextPath}/images/"+val+"'/>";
	}
		
	function formatSmallTypeName(val,row){
		return row.smallType.name;
	}
	function formatBigTypeName(val,row){
		return row.bigType.name;
	}
	function formatProductName(val,row){
		return row.user.productName;
	}
	function formatProvincyCity(val,row){
		return row.user.provinceCity+row.user.memberName;
	}
	function formatMemberName(val,row){
		return row.user.memberName;
	}
	function formatBigTypeId(val,row){
		return row.bigType.id;
	}
	function formatSmallTypeId(val,row){
		return row.smallType.id;
	}
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="未审核产品" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="tbproduct_list.action?verified=0" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="id" width="50" align="center">编号</th>
    		<!-- <th field="type" width="100" align="center" formatter="formattype" >会员类别</th> -->
    		<th field="user.productName" width="100" align="center" formatter="formatProductName">产品厂家名称</th>
    		<th field="user.provinceCity" width="100" align="center" formatter="formatProvincyCity">会员名称</th>
    		<!-- <th field="csName" width="100" align="center">厂商名称</th>-->
    		<th field="user.type" width="100" align="center" formatter="formatType"  >会员类型</th>
    		<th field="user.memberName" width="100" align="center" formatter="formatMemberName">产品名称</th>
    		<th field="bigType.name" width="100" align="center" formatter="formatBigTypeName">产品大类</th>
    		<th field="smallType.name" width="100" align="center" formatter="formatSmallTypeName">产品小类</th>
    		<th field="type" width="100" align="center"  formatter="formatProductType">产品属性</th>
    		<th field="address" width="100" align="center" hidden=true>产地</th>
    		<th field="productInfo" width="100" align="center" hidden=true>产品信息描述</th>
    		<th field="productAddressInfo" width="100" align="center" hidden=true>产地信息描述</th>
    		<th field="user.contactName" width="100" align="center" hidden="true">会员联系人</th>
    		<!-- <th field="phoneNum" width="100" align="center">联系手机</th>
    		<th field="legal" width="100" align="center" hidden=true>企业法人</th>
    		
    		<th field="telNum" width="100" align="center" hidden=true>固话</th>
    		<th field="factoryInfo" width="100" align="center" hidden=true>厂家信息</th>
    		<th field="addressPic" width="100" align="center" hidden=true>厂家物理地点标志信息图</th>
    		<th field="awardPic" width="100" align="center" hidden=true>证书图</th>
    		<th field="licencePic" width="100" align="center" hidden=true>扫描件或照片</th> -->
    		<!--<th field="dentityPic" width="150" align="center" formatter="formatProPic" >证件扫描件</th>
    	 	<th field="password" width="100" align="center">用户密码</th>
    		<th field="email" width="100" align="center">邮件</th>
    		<th field="mobile" width="100" align="center">联系电话</th>
    		<th field="address" width="100" align="center">地址</th> -->
    		<th field="bigType.id" width="100" align="center" formatter="formatBigTypeId" hidden="true">所属商品大类ID</th>
    		<th field="smallType.id" width="100" align="center" formatter="formatSmallTypeId" hidden="true">所属商品小类ID</th>
    		<th field="verified" width="100" align="center"  formatter="formatstatus">状态</th>
    		<th field="verifiedReason" width="100" align="center" hidden=true>未通过原因</th>
    		<th field="price" width="100" align="center" hidden=true>价格</th>
    		<th field="productPic" width="100" align="center" hidden=true>产品特写单张照</th>
    		<th field="packagePic" width="100" align="center" hidden=true>产品最小包装的展示图片</th> 
    		<th field="productAddressPic" width="150" align="center" hidden=true>产地物理地点标志信息图 </th>
    	
    		
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openProductDetailsDialog()" class="easyui-linkbutton" iconCls="icon-detail" plain="true">详情</a>
		<a href="javascript:openProductVerifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">审核</a>
		<a href="javascript:deleteProduct()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
<!-- 	<div>
		&nbsp;用户名：&nbsp;<input type="text" name="user.userName"  id="s_userName"  size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div> -->
</div>

<div id="dlg" class="easyui-dialog" style="width: 700px;height: 400px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="8px;">
  		<tr>	 
  			<td>会员名称：</td>
  			<td><input type="text" id="provinceCity" name="product.user.provinceCity" class="easyui-validatebox" disabled/></td>
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>会员类型：</td>
  			<td><input type="text" id="type" name="product.user.type" class="easyui-validatebox" disabled/></td>
  			
  		</tr>
  		<tr>
  			<td>会员联系人：</td>
  			<td><input type="text" id="contactName" name="product.user.contactName" class="easyui-validatebox" disabled/></td>	
  		</tr>
  		<tr>
  			<td>产品名称：</td>
  			<td><input type="text" id="memberName" name="product.user.memberName" class="easyui-validatebox" disabled/></td>	
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>产品属性：</td>
  			<td><input type="text" id="productType" name="product.type" class="easyui-validatebox" disabled/></td>	
  		</tr>
  		<tr>
  			<td>产品大类：</td>
  			<td><input type="text" id="bigType" name="product.bigType.id" class="easyui-validatebox" disabled/></td>	
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>产品小类：</td>
  			<td><input type="text" id="smallType" name="product.smallType.id" class="easyui-validatebox" disabled/></td>	
  		</tr>
  		
  		
  		<tr>
  			<td>产地：</td>
  			<td><input type="text" id="address" name="product.address" class="easyui-validatebox" disabled/></td>			
  		</tr>
  		
  		<tr>
  			<td>产品信息描述：</td>
  			<td  colspan="4"><textarea rows="7" cols="50" name="product.productInfo" id="productInfo" disabled></textarea></td>
  		</tr>
  		<tr>
  			<td>产地信息描述：</td>
  			<td  colspan="4"><textarea rows="7" cols="50" name="product.productAddressInfo" id="productAddressInfo" disabled></textarea></td>
  		</tr>
  		
  		<tr>
  			<td colspan="4">产品特写单张照：</td>
  			
  		</tr>
  		<tr>
  			<td colspan="4"><img id="productPic" src="" style="width: 250px;height: 200px;padding: 10px 20px"  /></td>
  		</tr>
  		<tr>
  			<td colspan="4">产品最小包装照：</td>
  			
  		</tr>
  		<tr>
  			<td colspan="4"><img id="packagePic" src="" style="width: 250px;height: 200px;padding: 10px 20px"  /></td>
  		</tr>
  		<tr>
  			<td colspan="4">产地图片（产地物理地点标志信息）：</td>
  		</tr>
  		<tr>
  			<td colspan="4"><img id="productAddressPic" src="" style="width: 250px;height: 200px;padding: 10px 20px"  /></td>
  		</tr>
  		<tr>
  			<td>未通过原因：</td>
  			<td><input type="text" id="verifiedReason" name="product.verifiedReason" class="easyui-validatebox" /></td>
  		</tr>
  	</table>
  	    
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" id="closeBtn">关闭</a>
	<a href="javascript:saveProduct()" class="easyui-linkbutton" iconCls="icon-ok" id="verifiedBtn" >审核通过</a>
	<a href="javascript:disagreeProduct()" class="easyui-linkbutton" iconCls="icon-cancel" id="verifiedNoBtn">审核不通过</a>
</div>


</body>
</html>