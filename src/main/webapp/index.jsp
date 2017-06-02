<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--========== The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags ==========-->
<title>Home</title>

<script src="<%=basePath %>js/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(function() {
		window.location.href = "<%=basePath %>index/show.do";
	})
	
</script>
</head>
<body class="home">
</body>
</html>
