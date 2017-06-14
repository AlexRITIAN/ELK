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
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
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
<body onload="searchLoad();">

	<div class="wrapper">
		<div class="sidebar" data-background-color="white"
			data-active-color="danger">

			<!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

			<div class="sidebar-wrapper">
				<div class="logo" id="logo">
				</div>

				<ul class="nav">
					<li class="active"><a href="<%=basePath%>index/show.do"> <i
							class="ti-panel"></i>
							<p>Search</p>
					</a></li>
					<li><a href="<%=basePath%>add/show.do"> <i class="ti-user"></i>
							<p>Add Source</p>
					</a></li>
					<li><a href="<%=basePath%>index/detail.do"> <i
							class="ti-view-list-alt"></i>
							<p>Source</p>
					</a></li>

				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<!-- <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button> -->
						<a class="navbar-brand" href="#">Dashboard</a>
					</div>
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="http://10.99.205.240:8080/elk3/login/logout.do" class="dropdown-toggle"> <i class="ti-panel"></i>
									<p>Stats</p>
							</a></li>
							<li class="dropdown" id="notification_li"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="ti-bell"></i>
									<p class="notification" id="notification_num"></p>
									<p>Notifications</p> <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" id="notification_ul">
									<li><a href="#">Notification 1</a></li>
									<li><a href="#">Notification 2</a></li>
									<li><a href="#">Notification 3</a></li>
									<li><a href="#">Notification 4</a></li>
									<li><a href="#">Another notification</a></li>
								</ul></li>
							<li><a href="<%=basePath%>setting/show.do?url=/index/setting"> <i
									class="ti-settings"></i>
									<p>Settings</p>
							</a></li>
						</ul>

					</div>
				</div>
			</nav>


			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="content">
									<div class="row">
										<div class="col-xs-5">
											<div class="icon-big icon-warning text-center">
												<i class="ti-server"></i>
											</div>
										</div>
										<div class="col-xs-7">
											<div class="numbers" id="size">
												<p>Capacity</p>
											</div>
										</div>
									</div>
									<div class="footer">
										<hr />
										<div class="stats">
											<i class="ti-reload"></i> Updated now
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="content">
									<div class="row">
										<div class="col-xs-5">
											<div class="icon-big icon-success text-center">
												<i class="ti-files"></i>
											</div>
										</div>
										<div class="col-xs-7">
											<div class="numbers" id="docNum">
												<p>Doc</p>
											</div>
										</div>
									</div>
									<div class="footer">
										<hr />
										<div class="stats">
											<i class="ti-calendar"></i> Updated now
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="content">
									<div class="row">
										<div class="col-xs-5">
											<div class="icon-big icon-danger text-center">
												<i class="ti-pulse"></i>
											</div>
										</div>
										<div class="col-xs-7">
											<div class="numbers" id="health"></div>
										</div>
									</div>
									<div class="footer">
										<hr />
										<div class="stats">
											<i class="ti-timer"></i> Updated now
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-sm-6">
							<div class="card">
								<div class="content">
									<div class="row">
										<div class="col-xs-5">
											<div class="icon-big icon-info text-center">
												<i class="ti-package"></i>
											</div>
										</div>
										<div class="col-xs-7">
											<div class="numbers" id="indexNum">
												<p>Index</p>
											</div>
										</div>
									</div>
									<div class="footer">
										<hr />
										<div class="stats">
											<i class="ti-reload"></i> Updated now
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">

						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Search</h4>
									<p class="category">select index for search data</p>
								</div>
								<div class="content">
									<div class="col-md-6">
										<input type="text" class="form-control border-input"
											id="search_for" onkeypress="search_for_keypress(event);">
									</div>
									<div class="col-md-6">
										<button type="button" class="btn btn-info btn-fill btn-wd"
											onclick="mutilsearch(0);">
											<span class="ti-search"></span>Search
										</button>
									</div>
									<br>
									<!-- <div class="col-md-6"> -->
									<table class="table table-hover">
										<tbody id="show_tag"></tbody>
									</table>
									<div class="footer">
										<!--  <i class="fa fa-circle text-info"></i> Open
                                        <i class="fa fa-circle text-danger"></i> Click
                                        <i class="fa fa-circle text-warning"></i> Click Second Time -->
										<!-- </div> -->
										<hr>
										<div class="stats">
											<i class="ti-reload"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="show_blog"></div>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="col-md-3">
									<button id="upPage" onclick="upPage_btn_click();" type="button"
										style="display: none" value="上一页">上一页</button>
								</div>
								<div class="col-md-3" id="pageNum_Div">
																		
								</div>
								<div class="col-md-3">
									<button id="downPage" onclick="downPage_btn_click();"
										type="button" style="display: none" value="下一页">下一页</button>
								</div>
							</div>
						</div>
					</div>
					<input id="from" type="hidden" value="0"> 
					<input id="pageNow" type="hidden" value="1">
					<input id="total" type="hidden" value="">
				</div>
			</div>


			<footer class="footer">
				<div class="container-fluid">
					<nav class="pull-left">
						<ul>

							<li><a href="#"> Creative Tim </a></li>
							<li><a href="http://blog.creative-tim.com"> Blog </a></li>
							<li><a href="#/license"> Licenses </a></li>
						</ul>
					</nav>
					<div class="copyright pull-right">
						Copyright &copy; 2017.Company name All rights reserved.<a
							target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
					</div>
				</div>
			</footer>

		</div>
	</div>


</body>

<!--   Core JS Files   -->
<script src="<%=basePath%>js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="<%=basePath%>js/bootstrap-checkbox-radio.js"></script>

<!--  Charts Plugin -->
<script src="<%=basePath%>js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="<%=basePath%>js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<!---<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>--->

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="<%=basePath%>js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<%-- <script src="<%=basePath %>js/demo.js"></script> --%>
<script src="<%=basePath%>js/blog.js"></script>

<script type="text/javascript">
	/* $(document).ready(function() {

		//demo.initChartist();

		$.notify({
			icon : 'ti-gift',
			message : "Welcome to <b>ELK</b> :)"

		}, {
			type : 'success',
			timer : 4000
		});

	}); */
</script>

</html>
