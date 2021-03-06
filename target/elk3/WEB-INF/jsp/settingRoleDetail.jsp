<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="<%=basePath%>img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="<%=basePath%>img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title></title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, role-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="<%=basePath%>css/paper-dashboard.css" rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="<%=basePath%>css/demo.css" rel="stylesheet" />


<!--  Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link href="<%=basePath%>css/themify-icons.css" rel="stylesheet">

</head>
<body onload="settingRoleDetailLoad();">
							<div class="card" style="height: 700px;">
								<div class="header">
									<h4 class="title">role</h4>
									<p class="category">role detail</p>
								</div>
								<div class="content">
									id:<input type="text" disabled="disabled" id="role_detail_id" value="${roleId }"><br>
									name:<input type="text" id="role_detail_name" value=""><br>
									remark:<input type="text" id="role_detail_remark" value=""><br>
									
									<div class="col-md-6">
										<span class="category">List</span>
									</div>
									<div class="col-md-3">
										<select size="10" style="width:150px" id="permission_list_settingroleDetail">
										</select>
									</div>
									<div class="col-md-3">
										<button type="button" onclick="Permission_list_add();">
											<span>ADD</span>
										</button>
										<button type="button" onclick="Permission_list_delete();">
											<span>DELETE</span>
										</button>
									</div>
									<div class="col-md-6">
										<span class="category">ALL</span>
									</div>
									<div class="col-md-3">
										<select size="10" style="width:150px" id="permission_all_settingroleDetail">
										</select>
									</div>
								</div>
								<div>
									<button onclick="Setting_roleDetail_submit();">Submit</button>
									<button onclick="CloseWebPage();">back</button>
								</div>
							</div>
</body>

<!--   Core JS Files   -->
<script src="<%=basePath%>js/jquery-1.10.2.js" type="text/javascript"></script>

<%-- <script src="<%=basePath %>js/demo.js"></script> --%>
<script src="<%=basePath%>js/blog.js"></script>

</html>
