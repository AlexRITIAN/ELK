<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="<%=basePath %>img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<%=basePath %>img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title></title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="<%=basePath %>css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="<%=basePath %>css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=basePath %>css/demo.css" rel="stylesheet" />


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=basePath %>css/themify-icons.css" rel="stylesheet">

</head>
<body onload="singleLoad();">

<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->
<div class="sidebar-wrapper">
            <div class="logo">
                <a href="<%=basePath %>index/show.do" class="simple-text">
                    Creative Tim
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="<%=basePath %>index/show.do">
                        <i class="ti-panel"></i>
                        <p>Search</p>
                    </a>
                </li>
                <li>
                    <a href="<%=basePath %>add/show.do">
                        <i class="ti-user"></i>
                        <p>Add Source</p>
                    </a>
                </li>
                <li class="active">
                    <a href="<%=basePath %>index/detail.do">
                        <i class="ti-view-list-alt"></i>
                        <p>Source</p>
                    </a>
                </li>
                
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
                    <a class="navbar-brand" href="<%=basePath %>index/show.do">Search</a>
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
							<li><a href="<%=basePath%>index/setting.do"> <i
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
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">${blog._source.title }</h4>
                                <p class="category">${blog._source.description }</p>
                            </div>
                            <div class="content">
                            	<div class="col-md-12">
                            		<c:if test="${blog._index == 'kb1'}">
				                      <p>${blog._source.content_show }</p>
				                 	</c:if>
				                  	<c:if test="${blog._index != 'kb1'}">
				                  		<p>${blog._source.content }</p>
				                  	</c:if>
                                </div>
                                <br>
                                <div class="footer">
                                	<div class="chart-legend">
                                	<input type="hidden" id="index" value="${blog._index }">
                                	<input type="hidden" id="type" value="${blog._type }">
                                	<input type="hidden" id="id" value="${blog._id }">
                                	<input type="hidden" id="username" value="${username.username }">
                                	<input type="hidden" id="author" value="${blog._source.author }"> 
                                	                                  <%--  <i class="fa fa-circle text-info"></i>${blog._source.tag } --%>
                                     	<input type="button" id="edit_btn" style="display:none" onclick="editButton();" value="edit">
                                    </div>
                                    <hr>
                                    <div class="stats">
                                        <i class="ti-reload"></i>By ${blog._source.author } , ${blog._source.date }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>

                        <li>
                            <a href="#">
                                Creative Tim
                            </a>
                        </li>
                        <li>
                            <a href="#">
                               Blog
                            </a>
                        </li>
                        <li>
                            <a href="#/license">
                                Licenses
                            </a>
                        </li>
                    </ul>
                </nav>
                <div class="copyright pull-right">
                    Copyright &copy; 2017.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
                </div>
            </div>
        </footer>

    </div>
</div>


</body>

    <!--   Core JS Files   -->
    <script src="<%=basePath %>js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="<%=basePath %>js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="<%=basePath %>js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="<%=basePath %>js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="<%=basePath %>js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <!---<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>--->

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="<%=basePath %>js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="<%=basePath %>js/demo.js"></script>
	<script src="<%=basePath %>js/blog.js"></script>
</html>
