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
</head>
<body>
<footer class="row" id="footer">
    <div class="container">
        <div class="row top-footer">
            <div class="widget col-sm-3 widget-about">
                <div class="row m0"><a href="index.html"><img src="<%=basePath %>images/logo-black-green.png" alt=""></a></div>
            </div>
            <div class="widget col-sm-5 widget-menu">
                <div class="row">
                    <ul class="nav column-menu white-bg">
                        <li class="active"><a href="index.html">Home</a></li>
                        <li><a href="about.html">About</a></li>
                        <li><a href="single.html">Blog Single 1</a></li>
                        <li><a href="single2.html">Blog Single 2</a></li>
                    </ul>
                    <ul class="nav column-menu white-bg">
                        <li><a href="single3.html">Blog Single 3</a></li>
                        
                        <li><a href="contact.html">contact</a></li>
                    </ul>
                </div>
            </div>
            <div class="widget col-sm-4 widget-subscribe">
                <h5 class="widget-title">subscribe to our newsletter.</h5>
                <form action="#" method="post" class="form-inline subscribe-form">                    
                    <div class="form-group">
                        <input type="email" class="form-control" placeholder="Email">
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm"><span>send</span></button>
                </form>
            </div>
        </div>
        <h5 class="copyright">Copyright &copy; 2017.Company name All rights reserved.</h5>
    </div>
</footer>
</body>
</html>