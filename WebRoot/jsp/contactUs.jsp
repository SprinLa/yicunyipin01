<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
 String  Default=(String)request.getAttribute("default");
 if(Default==null){
   Default="newdefault1.jsp";
 }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>一村一品政策首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <body >
	<%-- <center>
        <table border="0" width="100%" cellspacing="0" cellpadding="0" bgcolor="white">
            <tr><td colspan="2"><jsp:include page="top.jsp"/></td></tr>
            <tr><td colspan="2"><jsp:include page="<%=Default%>"/></td></tr>
            <tr><td colspan="2" width="100%"><%@ include file="foot.jsp" %></td></tr>
        </table>        
    </center> --%>
    
    
     <table border="0" width="100%" cellspacing="0" cellpadding="0" bgcolor="white">
            <tr><td colspan="2"><jsp:include page="top.jsp"/></td></tr>
            <tr>
                <td colspan="2" width="100%"><%@ include file="contact.jsp" %></td>
            </tr>
         <%--    <tr><td colspan="2" width="100%"><%@ include file="foot.jsp" %></td></tr> --%>
     </table>  
      
  </body>
</html>
