<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function refreshSystem(){
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=4";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function productPreSave(){
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_ProductPreSave.action";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function showProductByUser(){
		//TBUser tbUser = (TBUser)session.
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/product_showProductByUser.action?s_product.status=4";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	function orderSelect(){
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/order_orderSelect.action";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	
	
	function showNewsByUser(){
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/news_showNewsByUser.action";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
	function confirmInfo(){
		$.post("tbproduct_init.action",{},
			function(flag){
				var flag=eval('('+flag+')');
				//alert(flag);
				if(flag.success){
					window.location.href="${pageContext.request.contextPath}/tbuser_userPreSave3.action";
				}else{
					alert("您还不是会员,不能操作！");
					//location.reload(); // 刷新本页
				}
			}
		);
	}
</script>


<ul class="am-list admin-sidebar-list">
      <li><a href="${pageContext.request.contextPath}/background/mainTemp.jsp"><span class="am-icon-home"></span> 会员服务首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 会员服务 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href="${pageContext.request.contextPath}/tbuser_userPreSave2.action?uId=${currentUser.id}" class="am-cf"><span class="am-icon-check"></span> 申请会员<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="${pageContext.request.contextPath}/tbuser_verifiedStatus.action"><span class="am-icon-puzzle-piece"></span> 申请会员状态</a></li>
          <li><a href="javascript:confirmInfo()"><span class="am-icon-puzzle-piece"></span> 会员信息核查</a></li>
          <li background-color:yellow><a href="${pageContext.request.contextPath}/tbuser_userPassword.action"><span class="am-icon-th"></span> 会员修改密码</a></li>
 
        </ul>
      </li>
	  
	   <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-file"></span> 产品发布服务 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                    <li><a href="javascript:productPreSave()"><span class="am-icon-puzzle-piece"></span> 产品发布提交</a></li>
        <!--    <li><a href="javascript:showProductByUser()"><span class="am-icon-th"></span> 商品维护<span class="am-badge am-badge-secondary am-margin-right am-fr">${goodtol}</span></a></li>-->
 
        </ul>
      </li>
      
       <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><span class="am-icon-file"></span> 会员订单服务 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
                 <!--    <li><a href="javascript:newsPreSave()"><span class="am-icon-puzzle-piece"></span> 新闻添加</a></li>--> 
          <li><a href="javascript:orderSelect()"><span class="am-icon-th"></span> 会员订单查询<span class="am-badge am-badge-secondary am-margin-right am-fr"></span></a></li>
 
        </ul>
      </li>
     
      <li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p><font id="status" color="red">首次登录时请完善个人信息，审核通过后才能操作其他功能</font></p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> 一村一品网</p>
        <p>Welcome to the chinaYcYp</p>
      </div>
    </div>