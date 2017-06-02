<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/login/style.css" rel='stylesheet'
	type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-form.js"></script>
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<script type="text/javascript">
	$(document).ready(function() {
		var options = {
			success : function(data) {
				var from = $("#from").val();
				if(data != 0){
					window.location.href="http://10.99.205.240:8080/elk3/" + from;
				}else{
					alert("用户名或密码错误!");
				}
			}
		};

		// ajaxForm
		$("#ajaxForm").ajaxForm(options);

		// ajaxSubmit
		/* $("#btnAjaxSubmit").click(function() {
			$("#ajaxForm").ajaxSubmit(options);
		}); */
	});
</script>
</head>
<body>
	<!-----start-main---->
	<div class="main">
		<div class="login-form">
			<h1>Member Login</h1>
			<div class="head">
				<img src="<%=basePath%>img/login/user.png" alt="" />
			</div>
			<form id="ajaxForm" action="<%=basePath %>login/match.do" method="post">
				<input type="text" class="text" value="USERNAME"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'USERNAME';}"
					name="name"> <input type="password" value="Password"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Password';}"
					name="password">
				<div class="submit">
					<input type="submit" value="LOGIN">
				</div>
				<p>
					<a href="#">Forgot Password ?</a>
				</p>
				<input type="hidden" value="${from }" id="from" >
			</form>
		</div>
		<!--//End-login-form-->
		<!-----start-copyright---->
		<!-- <div class="copy-right">
			<p>Copyright &copy; 2014.Company name All rights reserved.</p>
		</div> -->
		<!-----//end-copyright---->
	</div>
	<!-----//end-main---->

	<!-- <div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div> -->
</body>
</html>