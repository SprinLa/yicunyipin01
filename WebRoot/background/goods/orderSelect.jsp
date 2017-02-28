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

<title>用户管理</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script> 


<body>
	




<!-- content start -->
  <div class="admin-content">
  
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单查询</strong> / <small>Order Query</small></div>
    </div>

    <hr/>

    <div class="am-g">
    
	   <div class="am-u-sm-1 am-u-md-2 am-u-md-push-4"></div>

       <div class="am-u-sm-18 am-u-md-15 am-u-md-pull-29">
       
          <form class="am-form am-form-horizontal"  action="order_getOrderList.action" method="post"  >
         
         <div class="am-g am-margin-top-sm">
	            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
	              开始时间
	            </div>
	            <div class="am-u-sm-4 am-u-end">
	            <input id="d313" class="Wdate" name="startTime"  size="20"  type="text" placeholder="请选择开始时间 " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            </div>
	   </div>
          
         <div class="am-g am-margin-top-sm">
	            <div class="am-u-sm-2 am-text-right"><i class="require-red">*&nbsp;</i>
	              结束时间
	            </div>
	            <div class="am-u-sm-4 am-u-end">
	            <input id="d314" class="Wdate" name="endTime"  size="20"  type="text" placeholder="请选择结束时间 " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
	            </div>
	   </div>
       
        
			
		<hr/>		
					
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <input id="btn" type="submit" class="am-btn am-btn-primary" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
               <input id="btn" type="reset" class="am-btn am-btn-primary" value="重置">
            </div>
          </div>
        </form>
      </div>
	  
	  
      
    </div>
  </div>
  <!-- content end -->




</body>
</html>
