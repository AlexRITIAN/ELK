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

<!-- edit -->
<link rel="stylesheet" href="<%=basePath %>css/editor/wangEditor.css">
</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
	Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
	Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="<%=basePath %>index/show.do" class="simple-text">
                    ${username.username }
                </a>
            </div>

            <ul class="nav">
					<li class="active"><a href="<%=basePath%>index/show.do"> <i
							class="ti-panel"></i>
							<p>Search</p>
					</a></li>
					<li><a href="<%=basePath%>blog/show.do?url=/blog/add"> <i class="ti-user"></i>
							<p>Add Source</p>
					</a></li>
					<li><a href="<%=basePath%>blog/detail.do?url=/blog/update"> <i
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
                   <!--  <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button> -->
                    <a class="navbar-brand" href="#">User Profile</a>
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
                    
                    <div class="col-lg-8 col-md-7">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Edit Source</h4>
                            </div>
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Index</label>
                                                <input id="index" type="text" class="form-control border-input" disabled value="${blog._index }">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Type</label>
                                                <input id="type" type="text" class="form-control border-input" disabled value="${blog._type }">
                                                <input id="id" type="hidden" value="${blog._id }">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Author</label>
                                                <input id="author" type="text" class="form-control border-input" disabled value="${blog._source.author }">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Title</label>
                                                <input id="title" type="text" class="form-control border-input" value="${blog._source.title }">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Category</label>
                                                <input id="category" type="text" class="form-control border-input" value="${blog._source.category }">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <input id="description" type="text" class="form-control border-input" value="${blog._source.description }">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Tag</label>
                                                <input id="tag" type="text" class="form-control border-input" value="${blog._source.tag }">
                                            </div>
                                        </div>
                                       <div class="col-md-4">
                                            <div class="form-group">
                                                <label>source_object</label>
                                                <input id="source_object" type="text" class="form-control border-input" disabled value="${blog._source.source_object }">
                                            </div>
                                        </div>
                                        <!-- <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Postal Code</label>
                                                <input type="number" class="form-control border-input" placeholder="ZIP Code">
                                            </div>
                                        </div> -->
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Content</label>
                                                <textarea rows="10" class="form-control border-input" id="text">${blog._source.content_show }</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="button" class="btn btn-info btn-fill btn-wd" onclick="edit_submit();">Commit</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
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
                            <a href="http://blog.creative-tim.com">
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
                    Copyright &copy; 2017.Company name All rights reserved.
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
<!-- edit -->
<script src="<%=basePath %>js/editor/wangEditor.js"></script>
<script type="text/javascript">
        $(function () {
            var editor = new wangEditor('text');
            
            editor.config.uploadImgUrl = 'http://10.99.205.240:8080/elk3/file/upload.do';
            
            editor.config.uploadImgFileName = 'file';
            
            editor.create();
        });
</script>

</html>
