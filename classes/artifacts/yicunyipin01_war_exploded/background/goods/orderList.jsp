<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@   page   import= "com.yicunyipin.entity.TBOrder "%>
<%@   page   import= "java.util.* "%>
<%@   page   import= "net.sf.json.JSONArray; "%>

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
<style>
		table{
			width: 100%;
            margin:auto;
		}

		td{
			border: 1px solid silver;
        }
        th{
            color:#edc;
            text-align:center;
            background-color: #8FBC8F;
        }
        tr td{
            color:#223;
            text-align:center;
            background-color: mintcream;
        }
	</style>

<body>
	




<!-- content start -->
  <div class="admin-content">
  
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">查询结果</strong> / <small>Order Query</small></div>
    </div>

    <hr/>

    <div class="am-g">
    

       <div class="am-u-sm-18 am-u-md-15 am-u-md-pull-29">
       
         
         <div class="am-g am-margin-top-sm">
         <%
         JSONArray ja = (JSONArray)request.getAttribute("orderList");
         //List<TBOrder> OrderList = (List<TBOrder>)request.getAttribute("tbOrderList");
         if(ja.size() == 0){
         %>
         	无订单信息
         <%
         }else{ %>
	            <table>
		<thead>
		<tr>
			<th >商品名称</th>
			<th >购买方名称</th>
			<th >联系电话</th>
			<th >购买方单位</th>
			<th >购买方详细地址</th>
			<th >购买数量(箱)</th>
			<th >合计金额(元)</th>
			<th >订单时间</th>
		</tr>
		</thead>
		<c:forEach items="${orderList}" var="tbOrder">
			<tbody>
			<tr>
				<td style="width: 50px; ">${tbOrder.memberName }</td>
				<td style="width: 50px; ">${tbOrder.buyerName }</td>
				<td style="width: 20px; ">${tbOrder.buyerPhone }</td>
				<td style="width: 100px; ">${tbOrder.buyerCompany}</td>
				<td style="width: 100px; ">${tbOrder.buyerAddress}</td>
				<td style="width: 11px; ">${tbOrder.orderCount}</td>
				<td style="width: 11px; ">${tbOrder.orderSum}</td>
				<td style="width: 60px; ">${tbOrder.buyTime}</td>
			</tr>
			</tbody>
		</c:forEach>
	</table>
	<%} %>
	   </div>
   
      </div>
	  
	  
      
    </div>
  </div>
  <!-- content end -->




</body>
</html>
