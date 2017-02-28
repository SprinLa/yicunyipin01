<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="tag" class="wrap">
	<jsp:include page="head.jsp"/>
</div>

<div id="header" class="wrap">
	<jsp:include page="top.jsp"/>
</div>

<div id="navbar01" class="wrap">
	<jsp:include page="navbar.jsp"/>
</div>

<div id="main" class="wrap">
<div id="position" class="wrap">
		${navCode }
</div>

<div class="wrap">
		<jsp:include page="${mainPage }"/>
</div>
</div>	
<div id="footer">
	<jsp:include page="./footer.jsp"/>
</div>
</body>
</html>