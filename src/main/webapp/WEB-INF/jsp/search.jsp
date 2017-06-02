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
<title>Chivalric</title>

<!--==========Dependency============-->
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath %>vendors/owl-carousel/<%=basePath %>owl.carousel.css">
<link rel="stylesheet" href="<%=basePath %>vendors/magnific-popup/magnific-popup.css">
<link rel="stylesheet" href="<%=basePath %>vendors/flexslider/flexslider.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kanit:500">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Josefin+Sans:600,700italic">
<link href='https://fonts.googleapis.com/css?family=Dosis:400,200,300,500,600,800,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Muli:400,300,300italic,400italic">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500italic,500,700italic,700,900,900italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Fredoka+One' rel='stylesheet' type='text/css'>

<!--==========Theme Styles==========-->
<link href="<%=basePath %>css/style.css" rel="stylesheet">
<link href="<%=basePath %>css/theme/green.css" rel="stylesheet">
<link href="<%=basePath %>css/searchTable/bootstrap.css" rel="stylesheet">

<!--========== HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries ==========-->
<!--========== WARNING: Respond.js doesn't work if you view the page via file:// ==========-->
<!--==========[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
<![endif]==========-->
<script src="<%=basePath %>js/jquery-2.2.0.min.js"></script>
<script src="<%=basePath %>js/blog.js"></script>
</head>
<body class="home" onload="searchLoad();">

<header class="row transparent white" data-spy="affix" data-offset-top="300" id="header">
    <div class="container">
        <div class="row top-header">
            <div class="col-sm-4 search-form-col">
                <form action="#" method="get" class="search-form">
                    <div class="input-group">
                        <a href="<%=basePath %>login/login.do?from=search/show.do" id="login-span"><span class="form-control" >Login</span></a>
                        <a href="<%=basePath %>index/detail.do" id="username-a"><span class="form-control" id="username-span"></span></a>
                    </div>
                </form>
            </div>
            <div class="col-sm-4 logo-col text-center">
                <a href="<%=basePath %>index.jsp"><img src="<%=basePath %>images/logo-black-green.png" alt=""></a>
            </div>
            <div class="col-sm-4 menu-trigger-col">
                <button class="menu-trigger black pull-right">
                    <span class="active-page">About</span>
                    <img src="<%=basePath %>images/menu-align-dark.png" alt="" class="icon-burger">
                    <img src="<%=basePath %>images/menu-close-dark.png" alt="" class="icon-cross">
                </button>
            </div>
        </div>        
    </div>
    <div class="row menu-section">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 menu-col">
                    <div class="row">
                        <ul class="nav column-menu white-bg">
                            <li class="dropdown">
                                <a href="<%=basePath %>index.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Home <span class="caret"></span></a>
                            </li>
                            <li><a href="<%=basePath %>search/show.do">Search Blog</a></li>
                            <li><a href="<%=basePath %>add/show.do">Add Blog</a></li>
                            <li><a href="<%=basePath %>index/detail.do">Detail Blog</a></li>
                        </ul>
                        <!-- <ul class="nav column-menu white-bg">
                            <li><a href="single3.html">Blog Single 3</a></li>
                        </ul> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<section class="row content-wrap">
    <div class="container">
        <div class="row">
        	<div class="col-md-8 single-post-contents">
                <article class="single-post-content row m0 post">
                    <div class="row m0 tags">
	                	<input type="text" class="form-control" placeholder="Search for..." id="search_for">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="mutilsearch();"><img alt="" src="<%=basePath %>images/search-icon-dark.png"></button>
						</span>  
						<table>
							<tbody id="show_tag">
								
							</tbody>
						</table>    
                   	</div>
                </article>
            </div>
            <div class="col-md-4 sidebar">               
                <!--Author Widget-->
                <aside class="row m0 widget-author">
                    <div class="widget-author-inner row">
                    	<div class="input-group">
							<input type="text" class="form-control" placeholder="Search for..." id="search_for">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="mutilsearch();"><img alt="" src="<%=basePath %>images/search-icon-dark.png"></button>
							</span>
					</div>
                    </div>
                </aside>
            </div>
            <div id="show_blog"></div>
        </div>
    </div>
</section>


<!--Footer-->
<footer class="row" id="footer">
    <div class="container">
        <div class="row top-footer">
            <div class="widget col-sm-3 widget-about">
                <div class="row m0"><a href="index.html"><img src="<%=basePath %>images/logo-black-green.png" alt=""></a></div>
            </div>
            <div class="widget col-sm-5 widget-menu">
                <div class="row">
                    <ul class="nav column-menu white-bg">
                       <li class="active"><a href="<%=basePath %>index.jsp">Home</a></li>
                       <li><a href="<%=basePath %>search/show.do">Search</a></li>
                       <li><a href="<%=basePath %>add/show.do">Add</a></li>
                       <li><a href="<%=basePath %>index/detail.do">Detail Blog</a></li>
                    </ul>
                    <!-- <ul class="nav column-menu white-bg">
                        <li><a href="single3.html">Blog Single 3</a></li>
                        
                        <li><a href="contact.html">contact</a></li>
                    </ul> -->
                </div>
            </div>
        </div>
        <h5 class="copyright">Copyright &copy; 2017.Company name All rights reserved.</h5>
    </div>
</footer>

<!--========== jQuery (necessary for Bootstrap's JavaScript plugins) ==========-->

<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="<%=basePath %>vendors/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="<%=basePath %>vendors/instafeed/instafeed.min.js"></script>
<script src="<%=basePath %>vendors/imagesLoaded/imagesloaded.pkgd.min.js"></script>
<script src="<%=basePath %>vendors/isotope/isotope.pkgd.min.js"></script>
<script src="<%=basePath %>vendors/flexslider/jquery.flexslider-min.js"></script>
<script src="<%=basePath %>js/theme.js"></script>
<script src="<%=basePath %>js/blog.js"></script>

</body>
</html>
