<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : 'get',
			url : 'http://10.99.205.240:8080/elk3/index/index.do',
			data : {
				'index' : 'website',
				'type' : 'blog'
			},
			dataType : 'json',
			success : function(data) {
				if(data.flag == 1){
					window.location.href="http://10.99.205.240:8080/elk3/login.jsp";
				}else{
					$("#name").text(data.username);
					for (var i = 0; i < data.blogs.length; i++) {
					$("#test").append(
							"<tr><td>" + data.blogs[i]._source.title + "</td><td>"
									+ data.blogs[i]._source.author + "</td><td>"
									+ data.blogs[i]._source.text + "</td><td>"
									+ data.blogs[i]._source.comment + "</td><td>"
									+ data.blogs[i]._source.description + "</td><td>"
									+ data.blogs[i]._source.image + "</td><td>"
									+ data.blogs[i]._source.tag + "</td><td>"
									+ data.blogs[i]._source.date + "</td></tr>");
				}
				}
			}

		});
	})
</script>
</head>
<body>
	<form action="add/add.do" method="post" enctype="multipart/form-data">
		index : <input type="text" name="index"><br> 
		type : <input type="text" name="type"><br>
		title : <input type="text" name="title"><br>
		description : <input type="text" name="description"><br>
		author : <input type="text" name="author"><br>
		image : <input type="text" name="image"><br>
		tag : <input type="text" name="tag"><br>
		comment : <input type="text" name="comment"><br>
		text : <input type="text" name="text"><br>
		<input type="submit" value="submit">
	</form>
	<a href="http://10.99.205.240:8080/elk3/login.jsp">login</a>
	<p id="name"></p>
	<table>
		<tbody id="test">
		</tbody>
	</table>
	<form action="file/upload.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="submit"> 	
	</form>
	n[0].appendChild(i),
</body>
</html>