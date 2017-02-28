<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一村一品交易网后台管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	var url;
	$(function(){
		if('${Curentadmin.type}'==1)
		{
		 $("#operator").css('display',''); 
		}
		else{
		 $("#operator").css('display','none'); 
		}
	});
	// 打开新标签
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>"
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	function openPasswordModifyDialog(){
		url="admin_updatePassword.action?user.id=${Curentadmin.id}";
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		//alert(url);
	}
	
	function modifyPassword(){
		 $("#fm").form("submit",{
			url:url,
			onSubmit:function(){
			
				 var oldpassword=$("#oldpassword").val();
				//alert("old11:"+oldpassword);
				var newPassword=$("#newpassword").val();
				var repassword=$("#repassword").val();
				if(!$(this).form("validate")){
					return false;
				}//var a='${Curentadmin.password}';
				//alert("new1:"+a);
				if(oldpassword!='${Curentadmin.password}'){
				//alert("new:"+a);
				//alert("old:"+oldpassword);
					$.messager.alert('系统提示','用户密码输入错误！');
					
					return false;
				}
				if(newPassword!=repassword){
					$.messager.alert('系统提示','确认密码输入错误！');
					return false;
				}
				//alert(url);
				return true; 
			 },
			success:function(result){
				var result=eval('('+result+')');
				//alert(result.success);
				if(result.success){
					$.messager.alert('系统提示','密码修改成功，下一次登录生效！');
					closePasswordModifyDialog();
				}else{
					$.messager.alert('系统提示','修改密码失败');
					return;
				}
			}
		}); 
		
	}
	
	function closePasswordModifyDialog(){
		$("#dlg").dialog("close");
		$("#oldpassword").val("");
		$("#newpassword").val("");
		$("#repassword").val("");
	}
	
	function logout(){
		$.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
			if(r){
				window.location.href="admin_logout.action";
			}
		});
	}
	
	function refreshSystem(){
		$.post("sys_refreshSystem.action",{},function(result){
			if(result.success){
				$.messager.alert("系统提示","已成功刷新系统缓存！");							
			}else{
				$.messager.alert('系统提示','刷新系统缓存');
			}
		},"json");
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 95px;background-color: #E0ECFF">
<table style="padding: 5px;" width="100%">
	<tr>
		<td width="50%"><img src="${pageContext.request.contextPath}/images/logob.png"/></td>
		<td valign="bottom" align="right" width="50%"><font size="3">&nbsp;&nbsp;<strong>欢迎管理员：</strong>${Curentadmin.userName }</font></td>
	</tr>
</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px;"><font color="red" size="10">您已进入一村一品平台后台管理系统</font></div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px;" title="导航菜单" split="true">
<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="产业政策管理"  data-options="selected:true,iconCls:'icon-news'" style="padding:10px">
			<a href="javascript:openTab('产业政策管理','policyManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产业政策管理</a>
		</div>
		<div title="实地要闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="javascript:openTab('实地要闻管理','newsManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">实地要闻管理</a>
			<!-- <a href="javascript:openTab('类别管理','newsTypeManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">类别新闻</a> -->
		</div>
    <!-- 	  <div title="用户管理"  data-options="iconCls:'icon-user'" style="padding:10px;">
		     <a href="javascript:openTab('未审核用户1','userManage0.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">未审核用户1</a>
		    <a href="javascript:openTab('审核通过用户1','userManage2.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">审核通过用户1</a>
		    <a href="javascript:openTab('审核未通过用户1','userManage1.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">审核未通过用户1</a>
			<a href="javascript:openTab('全部用户1','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">全部用户1</a>
		    
		
		</div>
		<div title="商品管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
			<a href="javascript:openTab('商品管理','productManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
			<a href="javascript:openTab('商品大类管理','productBigTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品大类</a>
			<a href="javascript:openTab('商品小类管理','productSmallTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品小类</a>
		</div>  
	 	<div title="订单管理"  data-options="iconCls:'icon-order'" style="padding:10px">
			<a href="javascript:openTab('订单管理','orderManage.jsp','icon-order')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
		</div>
		<div title="留言管理" data-options="iconCls:'icon-comment'" style="padding:10px">
			<a href="javascript:openTab('留言管理','commentManage.jsp','icon-comment')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
		</div>
		<div title="公告管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
			<a href="javascript:openTab('公告管理','noticeManage.jsp','icon-notice')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
		</div>
		<div title="新闻管理"  data-options="iconCls:'icon-news'" style="padding:10px">
			<a href="javascript:openTab('新闻管理','newsManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a>
			<a href="javascript:openTab('类别管理','newsTypeManage.jsp','icon-news')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">类别新闻</a>
		</div>
		<div title="标签管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:openTab('标签管理','tagManage.jsp','icon-tag')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理标签</a>
		</div>
		<div title="主页图片"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:openTab('标签管理','picManage.jsp','icon-tag')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">主页图片</a>
		</div>   -->
		<div title="会员信息管理"  data-options="iconCls:'icon-user'" style="padding:10px;">
		     <a href="javascript:openTab('全部会员','memberManage0.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">全部会员</a>
		    <a href="javascript:openTab('VIP会员','memberManage2.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">VIP会员</a>
		    <a href="javascript:openTab('普通会员','memberManage1.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">普通会员</a>
		</div> 
		<div title="产品信息管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
		     <a href="javascript:openTab('全部产品','productManage0.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">全部产品</a>
		    <a href="javascript:openTab('精品','productManage1.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">精品</a>
		    <a href="javascript:openTab('新品','productManage2.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">新品</a>
		</div> 
		<div title="客户资源管理"  data-options="iconCls:'icon-order'" style="padding:10px;">
		     <a href="javascript:openTab('客户资源列表统计','customerManage.jsp','icon-order')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">客户资源统计列表</a>  
		</div> 
		
		<div title="新会员资格审核管理"  data-options="iconCls:'icon-user'" style="padding:10px;">
		     <a href="javascript:openTab('待审核的新会员','verifiedMemberManage0.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">待审核的新会员</a>
		    <a href="javascript:openTab('审核未通过的新会员','verifiedMemberManage2.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">审核未通过的新会员</a>
			<a href="javascript:openTab('全部新申请的会员','verifiedMemberManage1.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">全部新申请的会员</a>
		    
		
		</div> 
		<div title="新产品审核管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
		     <a href="javascript:openTab('未审核产品','verifiedProductManage0.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">未审核产品</a>
		    <a href="javascript:openTab('审核未通过产品','verifiedProductManage2.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">审核未通过产品</a>
		     <a href="javascript:openTab('全部新产品','verifiedProductManage1.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">全部新产品</a>
		    
		
		</div> 
		<div title="产品类型管理"  data-options="iconCls:'icon-tag'" style="padding:10px;">
			<a href="javascript:openTab('产品大类','productBigTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产品大类</a>
			<a href="javascript:openTab('产品小类','productSmallTypeManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产品小类</a>
		</div>
		<div title="产地管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:openTab('产地省（省/直辖市）','provinceManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产地省（省/直辖市）</a>
			<a href="javascript:openTab('产地市（地级市）','cityManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产地市（地级市）</a>
			<a href="javascript:openTab('产地县（县/区）','townManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">产地县（县/区）</a>
		</div>
	
		<!-- <div title="订单管理"  data-options="iconCls:'icon-tag'" style="padding:10px">
			<a href="javascript:openTab('按订单号查询','provinceManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">按订单号查询</a>
			<a href="javascript:openTab('按用户查询','cityManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">按用户查询</a>
			<a href="javascript:openTab('按产品查询','townManage.jsp','icon-product')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">按产品查询</a>
		</div> -->
		<div title="系统管理"  data-options="iconCls:'icon-item'" style="padding:10px">
		    <a href="javascript:openTab('系统操作员管理','adminOperatorManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;" id="operator">系统操作员管理</a>
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">密码修改</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			<!-- <a href="javascript:refreshSystem()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>  -->
		</div>
</div>
</div>
<div region="south" style="height: 25px;padding: 5px;" align="center">
	版权所有 2014 一村一品交易网 <a href="http://www.yicunyipin.com" target="_blank">www.yicunyipin.com</a>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 220px;padding: 10px 20px" 
 closed="true" buttons="#dlg-buttons" >
 <form id="fm" method="post">
 	<table cellspacing="4px;">
 		<%-- <tr>
 			<td>用户名：</td>
 			<td><input type="hidden" name="currentUser.id" id="id" value="${currentUser.id }"><input type="text" name="user.userName" id="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px;" /></td>
 		</tr> --%>
 		<tr>
 			<td>原密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="oldpassword" id="oldpassword" style="width: 200px;" required="true" /></td>
 		</tr>
 		<tr>
 			<td>新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="newpassword" id="newpassword" style="width: 200px;" required="true"  /></td>
 		</tr>
 		<tr>
 			<td>确认新密码：</td>
 			<td><input type="password" class="easyui-validatebox" name="repassword" id="repassword" style="width: 200px;" required="true" /></td>
 		</tr>
 	</table>
 </form>
</div>
<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>