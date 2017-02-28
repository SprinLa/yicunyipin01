<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="news" class=""style="margin-top:40px;">
		<div id="title" style="width:800px;text-align:center; border-bottom:1px solid #008D97;">
		
		<div class="title"><font size=5px;>${news.title }</font></div>
		<div class=""  style="margin-top:10px;">
				<fmt:formatDate value="${news.createTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;${news.newsType.typeName}  作者${news.author}
		</div>
	
		</div>
		<div class="content" style="width:780px;margin-top:20px;margin-left:30px; font-size:15px;">
			${news.content}
		</div>
</div>
</body>
</html>