var opDetail_WL = "";

$.fn.getBackgroundColor = function() {
	var rgb = $(this).css('background-color');
	if(rgb >= 0) return rgb;//如果是一个hex值则直接返回
	else{
		rgb = rgb.match(/^rgb(\d+),\s∗(\d+),\s∗(\d+)$/);
		function hex(x) {return ("0" + parseInt(x).toString(16)).slice(-2);}
		rgb= "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	}
	return rgb;
}

function mutilsearch(from) {
	var total;
	var elk_index = "";
	$('input[name="elk_index"]:checked').each(function() {
		elk_index = elk_index + "," + $(this).val();
	});

	elk_index = elk_index.substr(1);
	var text = $("#search_for").val();
	if (text != null && text != undefined && text != '') {
		if (elk_index == "") {
			elk_index = "kb1,kb2,kb3";
		}
		$
				.ajax({
					type : 'post',
					url : 'http://10.99.205.240:8080/elk3/search/searchfulltext.do',
					data : {
						'index' : elk_index,
						'text' : text,
						'from' : from,
						'size' : 10
					},
					dataType : 'json',
					success : function(data) {
						total = data.total;
						$("#show_blog").html("");
						for (var i = 0; i < data.blogs.length; i++) {
							var title = data.blogs[i]._source.title;
							var tag = data.blogs[i]._source.tag;
							var description = data.blogs[i]._source.description;
							var content;
							if (data.blogs[i].highlight.title != null) {
								title = data.blogs[i].highlight.title;
							}
							if (data.blogs[i].highlight.tag != null) {
								tag = data.blogs[i].highlight.tag;
							}
							if (data.blogs[i].highlight.description != null) {
								description = data.blogs[i].highlight.description;
							}
							if (data.blogs[i]._index == "kb1") {

								if (data.blogs[i].highlight.content_search != null) {
									content = data.blogs[i].highlight.content_search;
								} else {
									content = data.blogs[i]._source.content_search
											.substr(0, 100);
								}
							} else {
								if (data.blogs[i].highlight.content != null) {
									content = data.blogs[i].highlight.content;
								} else {
									content = data.blogs[i]._source.content
											.substr(0, 100);
								}
							}
							$("#show_blog")
									.append(
											"<div class=\"col-md-12\"><div class=\"card\">"
													+ "<div class=\"header\"><a href=\"http://10.99.205.240:8080/elk3/blog/single.do?index="
													+ data.blogs[i]._index
													+ "&type="
													+ data.blogs[i]._type
													+ "&id="
													+ data.blogs[i]._id
													+ "\" target=\"_blank\"><h4 class=\"title\">"
													+ title
													+ "</h4></a>"
													+ "<p class=\"category\">"
													+ description
													+ "</p>"
													+ "<div class=\"content\"><div class=\"col-md-12\">"
													+ content
													+ "</div><div class=\"footer\"><hr><div class=\"stats\"><i class=\"ti-reload\"></i>By "
													+ data.blogs[i]._source.author
													+ ","
													+ data.blogs[i]._source.date
													+ "</div></div></div></div></div>");
						}
						if(total - from > 10){
							$("#downPage").show();
						}else{
							$("#downPage").hide();
						}
						
						if(from >= 10 && total > 0){
							$("#upPage").show();
						}else{
							$("#upPage").hide();
						}
						if(total > 10){
							var totalPage = Math.ceil(total/10);
							var page = $("#pageNow").val();
							$("#pageNum_Div").html("");
							$("#pageNum_Div").append("<input type=\"text\" id=\"pageNum_now\" value=\""+page+"\" onkeypress=\"pageNow_keypress(event);\" style=\"width:20px\"> /" + totalPage);
							$("#total").val(totalPage);
						}
					}
				});
	} else {
		if (elk_index == "") {
			elk_index = "kb1";
		}
		$
				.ajax({
					type : 'post',
					url : 'http://10.99.205.240:8080/elk3/search/searchAll.do',
					data : {
						'index' : elk_index,
						'type' : 'data',
						'from' : from,
						'size' : 10
					},
					dataType : 'json',
					success : function(data) {
						total = data.total;
						$("#show_blog").html("");
						for (var i = 0; i < data.blogs.length; i++) {
							var title = data.blogs[i]._source.title;
							var tag = data.blogs[i]._source.tag;
							var description = data.blogs[i]._source.description;
							var content;
							if (data.blogs[i]._index == "kb1") {
								content = data.blogs[i]._source.content_search
										.substr(0, 100);
							} else {
								content = data.blogs[i]._source.content.substr(
										0, 100);
							}
							$("#show_blog")
									.append(
											"<div class=\"col-md-12\"><div class=\"card\">"
													+ "<div class=\"header\"><a href=\"http://10.99.205.240:8080/elk3/blog/single.do?index="
													+ data.blogs[i]._index
													+ "&type="
													+ data.blogs[i]._type
													+ "&id="
													+ data.blogs[i]._id
													+ "\" target=\"_blank\"><h4 class=\"title\">"
													+ title
													+ "</h4></a>"
													+ "<p class=\"category\">"
													+ description
													+ "</p>"
													+ "<div class=\"content\"><div class=\"col-md-12\">"
													+ content
													+ "</div><div class=\"footer\"><hr><div class=\"stats\"><i class=\"ti-reload\"></i>By "
													+ data.blogs[i]._source.author
													+ ","
													+ data.blogs[i]._source.date
													+ "</div></div></div></div></div>");
						}
						if(total - from > 10){
							$("#downPage").show();
						}else{
							$("#downPage").hide();
						}
						if(from >= 10 && total > 0){
							$("#upPage").show();
						}else{
							$("#upPage").hide();
						}
						if(total > 10){
							var totalPage = Math.ceil(total/10);
							var page = $("#pageNow").val();
							$("#pageNum_Div").html("");
							$("#pageNum_Div").append("<input type=\"text\" id=\"pageNum_now\" value=\""+page+"\" onkeypress=\"pageNow_keypress(event);\" style=\"width:20px\"> /" + totalPage);
							$("#total").val(totalPage);
						}
					}
				});
	}
}

function upPage_btn_click(){
	var from = Number($("#from").val()) - 10;
	mutilsearch(from);
	var page = Number($("#pageNow").val()) - 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}

function downPage_btn_click(){
	var from = Number($("#from").val()) + 10;
	mutilsearch(from);
	var page = Number($("#pageNow").val()) + 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}



function search_for_keypress(e) {
	var keynum;
	if (window.event) // IE
	{
		keynum = e.keyCode;

	} else if (e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which;
	}
	if (keynum == 13) {
		$("#from").val("0");
		mutilsearch(0);
	}
}

function pageNow_keypress(e){
	var keynum;
	if (window.event) // IE
	{
		keynum = e.keyCode;

	} else if (e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which;
	}
	if (keynum == 13) {
		var from = (Number($("#pageNum_now").val()) - 1) * 10;
		mutilsearch(from);
		$("#from").val(from);
		$("#pageNow").val($("#pageNum_now").val());
	}
}
function searchLoad() {
	$
			.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/search/index.do',
				data : {

				},
				dataType : 'json',
				success : function(data) {
					var str = "<tr>";
					for (var i = 0; i < data.blogs.length - 1; i++) {
						/*
						 * $("#show_tag").append("<a
						 * href=\"javascript:void(0);\" class=\"tag\"
						 * onclick=\"\">" + data.blogs[i].index + "</a>");
						 */
						if (i + 1 >= 5 && (i + 1) % 5 == 0) {
							str = str
									+ "<td><input type=\"checkbox\" name=\"elk_index\" value=\""
									+ data.blogs[i].index + "\">"
									+ data.blogs[i].index + "</td></tr><tr>";
						} else {
							str = str
									+ "<td><input type=\"checkbox\" name=\"elk_index\" value=\""
									+ data.blogs[i].index + "\">"
									+ data.blogs[i].index + "</td>";
						}
					}
					str = str + "</tr>";
					$("#show_tag").append(str);
				}
			});
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/search/esStatus.do',
		data : {

		},
		dataType : 'json',
		success : function(data) {
			$("#size").append(data.esHealth.size);
			$("#docNum").append(data.esHealth.docNum);
			$("#health").append("<p>" + data.esHealth.health + "</p>" + data.esHealth.healthNum);
			$("#indexNum").append(data.esHealth.indexNum);
			
		}
	});
	
	$.ajax({
		type :'post',
		url :'http://10.99.205.240:8080/elk3/login/getUsername.do',
		data:{},
		dataType:'json',
		success : function(data){
			$("#logo").html("");
			if(data.username != null && data.username !="" && data.username != undefined && data.username != "null"){
				$("#logo").append(data.username);
				showNotification();
			}else{
				$("#logo").append("<a href=\"http://10.99.205.240:8080/elk3/login/login.do\" class=\"simple-text\">login</a>")
				$("#notification_li").hide();
			}
		}
		
	});
}

function detailLoad(from) {
	var total;
	$
			.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/search/termsearch.do',
				data : {
					index : 'kb1',
					type : 'data',
					parm : 'author',
					from : from,
					size : 10
				},
				dataType : 'json',
				success : function(data) {
					$("#detail_tbody").html("");
					total = data.total;
					for (var i = 0; i < data.blogs.length; i++) {

						$("#detail_tbody")
								.append(
										"<tr>" + "<td>"
												+ i
												+ "</td>"
												+ "<td><a href=\"javascript:void(0);\" onclick=\"blogdel('"
												+ data.blogs[i]._index
												+ "','"
												+ data.blogs[i]._type
												+ "','"
												+ data.blogs[i]._id
												+ "');\">DELETE</a></td>"
												+ "<td><a href=\"http://10.99.205.240:8080/elk3/blog/single.do?index="
												+ data.blogs[i]._index
												+ "&type="
												+ data.blogs[i]._type
												+ "&id="
												+ data.blogs[i]._id
												+ "\">"
												+ data.blogs[i]._source.title
												+ "</a></td>"
												+ "<td>"
												+ data.blogs[i]._source.date
												+ "</td>"
												+ "<td>"
												+ data.blogs[i]._source.description
												+ "</td>" + "</tr>");
					
						if(total - from > 10){
							$("#downPage").show();
						}else{
							$("#downPage").hide();
						}
						
						if(from >= 10 && total > 0){
							$("#upPage").show();
						}else{
							$("#upPage").hide();
						}
						if(total > 10){
							var totalPage = Math.ceil(total/10);
							var page = $("#pageNow").val();
							$("#pageNum_Div").html("");
							$("#pageNum_Div").append("<input type=\"text\" id=\"pageNum_now\" value=\""+page+"\" onkeypress=\"pageNow_keypress(event);\" style=\"width:20px\"> /" + totalPage);
							$("#total").val(totalPage);
						}
					}
				}

			});
	
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/notification/getMsg.do',
		data :{
			flag : 'show',
			from : 0,
			size : 0
		},
		dataType : 'json',
		success : function(data){
			var temp = 0;
			$("#notification_ul").html("");
			for(var i = 0;i<data.length;i++){
				if(temp <= 5){
					temp++;
					$("#notification_ul").append("<li><a href=\"#\">"+data[i].msg+"</a></li>");
				}
			}
			$("#notification_ul").append("<li><a href=\"notificationCenter.do\">消息中心</a></li>");
			$("#notification_num").text("");
			$("#notification_num").text(temp);
		}
	});
}


function detail_upPage_btn_click(){
	var from = Number($("#from").val()) - 10;
	detailLoad(from);
	var page = Number($("#pageNow").val()) - 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}

function detail_downPage_btn_click(){
	var from = Number($("#from").val()) + 10;
	detailLoad(from);
	var page = Number($("#pageNow").val()) + 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}

function blogdel(index, type, id) {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/blog/delete.do',
		data : {
			'index' : index,
			'type' : type,
			'id' : id,
			'url' : '/blog/delete'
		},
		dataType : 'json',
		success : function(data) {
			if (data.flag) {
				alert("delete success!");
			} else {
				alert("delete failed!");
			}
			window.location.reload();
		}
	});
}

function addBlogLoad() {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/notification/getMsg.do',
		data :{
			flag : 'show',
			from : 0,
			size : 0
		},
		dataType : 'json',
		success : function(data){
			var temp = 0;
			$("#notification_ul").html("");
			for(var i = 0;i<data.length;i++){
				if(temp <= 5){
					temp++;
					$("#notification_ul").append("<li><a href=\"#\">"+data[i].msg+"</a></li>");
				}
			}
			$("#notification_ul").append("<li><a href=\"notificationCenter.do\">消息中心</a></li>");
			$("#notification_num").text("");
			$("#notification_num").text(temp);
		}
	});
}

function addBlogReset() {
	window.location.reload();
}

function editButton() {
	window.location.href = "http://10.99.205.240:8080/elk3/blog/editPage.do?index="
			+ $("#index").val()
			+ "&type="
			+ $("#type").val()
			+ "&id="
			+ $("#id").val()
			+ "&url=/bolg/update";
}

function add_submit() {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/blog/add.do',
		data : {
			'index' : $("#index").val(),
			'type' : $("#type").val(),
			'title' : $("#title").val(),
			'author' : $("#author").val(),
			'category' : $("#category").val(),
			'description' : $("#description").val(),
			'tag' : $("#tag").val(),
			'source_object' : $("#source_object").val(),
			'content_show' : $("#text").val(),
			'url' : '/blog/add'
		},
		dataType : 'json',
		success : function(data) {
			if (data == 1) {
				alert("Add Success :)");
			} else {
				alert("Add Failed :(");
			}
		}
	});
}

function edit_submit() {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/blog/edit.do',
		data : {
			'index' : $("#index").val(),
			'type' : $("#type").val(),
			'id' : $("#id").val(),
			'title' : $("#title").val(),
			'author' : $("#author").val(),
			'category' : $("#category").val(),
			'description' : $("#description").val(),
			'tag' : $("#tag").val(),
			'source_object' : $("#source_object").val(),
			'content_show' : $("#text").val(),
			'url' : '/blog/update'
		},
		dataType : 'json',
		success : function(data) {
			if (data == 1) {
				alert("edit Success :)");
			} else {
				alert("edit Failed :(");
			}
		}
	});
}

function initchartist(labels, series) {

	Chartist.Pie('#chartPreferences', {
		labels : labels,
		series : series
	});
}

function settingLoad() {
	var username = $("#username_hidden").val();
	$("#logo").html("");
	$("#logo").html(username);
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/search/index.do',
		data : {

		},
		dataType : 'json',
		success : function(data) {
			$("#index_list_setting").html("");
			$("#index_all_setting").html("");
			for (var i = 0; i < data.blogs.length - 1; i++) {
				$("#index_list_setting").append(
						"<option value=\"" + data.blogs[i].index + "\">"
								+ data.blogs[i].index + "</option>");
			}
			for (var i = 0; i < data.allIndex.length - 1; i++) {
				$("#index_all_setting").append(
						"<option value=\"" + data.allIndex[i].index + "\">"
								+ data.allIndex[i].index + "</option>");
			}

		}
	});

	$
			.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/user/Init.do',
				data : {
					'url' : '/index/setting'
				},
				dataType : 'json',
				success : function(data) {
					$("#user_total_hidden").val(data.length);
					$("#user_from_hidden").val("0");
					$("#user_pageNow_hidden").val("1");
					if(data.length > 5){
						$("#user_pageNow").show();
						$("#user_pageNow").val("1");
						$("#user_total").text("/" + Math.ceil(data.length/5));
						$("#btn_user_next").show();
						$.ajax({
							type : 'post',
							url : 'http://10.99.205.240:8080/elk3/user/getAllLimit.do',
							data : {
								from : 0,
								url : '/index/setting'
							},
							dataType : 'json',
							success : function(data){
								$("#setting_user_tbody").html("");
								for (var i = 0; i < data.length; i++) {
									$("#setting_user_tbody")
											.append(
													"<tr><td><input type=\"checkbox\" name=\"setting_check_userid\" value=\""
															+ data[i].id
															+ "\">"
															+ data[i].id
															+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_user_detail("
															+ data[i].id + ");\">"
															+ data[i].name
															+ "</a></td><td>"
															+ data[i].lockStatus
															+ "</td></tr>");
								}
							}
						});
					}else{
						$("#setting_user_tbody").html("");
						var num = 5 - data.length;
						for (var i = 0; i < data.length; i++) {
							$("#setting_user_tbody")
									.append(
											"<tr><td><input type=\"checkbox\" name=\"setting_check_userid\" value=\""
													+ data[i].id
													+ "\">"
													+ data[i].id
													+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_user_detail("
													+ data[i].id + ");\">"
													+ data[i].name
													+ "</a></td><td>"
													+ data[i].lockStatus
													+ "</td></tr>");
						}
						for(var i = 0; i < num ; i++){
							$("#setting_user_tbody")
							.append(
									"<tr><td>"
											+ "</td><td>"
											+ "</a></td><td>"
											+ "</td></tr>");
						}
					}
				}
			});

	$
			.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/role/getAll.do',
				data : {
					url : '/index/setting'
				},
				dataType : 'json',
				success : function(data) {
					$("#role_total_hidden").val(data.length);
					$("#role_from_hidden").val("0");
					$("#role_pageNow_hidden").val("1");
					if(data.length > 5){
						$("#role_pageNow").show();
						$("#role_pageNow").val("1");
						$("#role_total").text("/" + Math.ceil(data.length/5));
						$("#btn_role_next").show();
						$.ajax({
							type : 'post',
							url : 'http://10.99.205.240:8080/elk3/role/getAllLimit.do',
							data : {
								from : 0,
								url : '/index/setting'
							},
							dataType : 'json',
							success : function(data){
								$("#setting_role_tbody").html("");
								for (var i = 0; i < data.length; i++) {
									$("#setting_role_tbody")
											.append(
													"<tr><td><input type=\"checkbox\" name=\"setting_check_roleid\" value=\""
															+ data[i].id
															+ "\">"
															+ data[i].id
															+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_role_detail("
															+ data[i].id + ");\">"
															+ data[i].name
															+ "</a></td><td>"
															+ data[i].remark
															+ "</td></tr>");
								}
							}
						});
					}else{
						$("#setting_role_tbody").html("");
						var num = 5 - data.length;
						for (var i = 0; i < data.length; i++) {
							$("#setting_role_tbody")
									.append(
											"<tr><td><input type=\"checkbox\" name=\"setting_check_roleid\" value=\""
													+ data[i].id
													+ "\">"
													+ data[i].id
													+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_role_detail("
													+ data[i].id + ");\">"
													+ data[i].name
													+ "</a></td><td>"
													+ data[i].remark
													+ "</td></tr>");
						}
						for(var i = 0; i < num ; i++){
							$("#setting_role_tbody")
							.append(
									"<tr><td>"
											+ "</td><td>"
											+ "</a></td><td>"
											+ "</td></tr>");
						}
					}
				}
			});

	$
			.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/permission/getAll.do',
				data : {
					url : '/index/setting'
				},
				dataType : 'json',
				success : function(data) {
					$("#permission_total_hidden").val(data.length);
					$("#permission_from_hidden").val("0");
					$("#permission_pageNow_hidden").val("1");
					if(data.length > 5){
						$("#permission_pageNow").show();
						$("#permission_pageNow").val("1");
						$("#permission_total").text("/" + Math.ceil(data.length/5));
						$("#btn_permission_next").show();
						$.ajax({
							type : 'post',
							url : 'http://10.99.205.240:8080/elk3/permission/getAllLimit.do',
							data : {
								from : 0,
								url : '/index/setting'
							},
							dataType : 'json',
							success : function(data){
								$("#setting_permission_tbody").html("");
								for (var i = 0; i < data.length; i++) {
									$("#setting_permission_tbody")
											.append(
													"<tr><td><input type=\"checkbox\" name=\"setting_check_permissionid\" value=\""
															+ data[i].id
															+ "\">"
															+ data[i].id
															+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_permission_detail("
															+ data[i].id + ");\">"
															+ data[i].name
															+ "</a></td><td>"
															+ data[i].remark
															+ "</td></tr>");
								}
							}
						});
					}else{
						$("#setting_permission_tbody").html("");
						var num = 5 - data.length;
						for (var i = 0; i < data.length; i++) {
							$("#setting_permission_tbody")
									.append(
											"<tr><td><input type=\"checkbox\" name=\"setting_check_permissionid\" value=\""
													+ data[i].id
													+ "\">"
													+ data[i].id
													+ "</td><td><a href=\"javascript:void(0);\" onclick=\"setting_permission_detail("
													+ data[i].id + ");\">"
													+ data[i].name
													+ "</a></td><td>"
													+ data[i].remark
													+ "</td></tr>");
						}
						for(var i = 0; i < num ; i++){
							$("#setting_permission_tbody")
							.append(
									"<tr><td>"
											+ "</td><td>"
											+ "</a></td><td>"
											+ "</td></tr>");
						}
					}
				}
			});
}

function setting_prev(name){
	var from_id = name + "_from_hidden";
	var pageNowhid_id = name + "_pageNow_hidden";
	var pageNow_id = name + "_pageNow";
	var total_id = name + "_total_hidden";
	
	var prev_id = "btn_" + name + "_prev";
	var next_id = "btn_" + name + "_next";
	
	var total = Number($("#" + total_id + "").val());
	var pageNow = Number($("#" + pageNowhid_id + "").val()) - 1;
	var from = Number($("#" + from_id + "").val()) - 5;
	
	$("#" + from_id + "").val(from);
	$("#" + pageNowhid_id + "").val(pageNow);
	$("#" + pageNow_id + "").val(pageNow);
	
	limit4Name(name,from);
	
	if(from > 0){
		$("#" + prev_id + "").show();
	}else{
		$("#" + prev_id + "").hide();
	}

	if(total - from > 5){
		$("#" + next_id + "").show();
	}else{
		$("#" + next_id + "").hide();
	}
}

function setting_next(name){
	var from_id = name + "_from_hidden";
	var pageNowhid_id = name + "_pageNow_hidden";
	var pageNow_id = name + "_pageNow";
	var total_id = name + "_total_hidden";
	
	var prev_id = "btn_" + name + "_prev";
	var next_id = "btn_" + name + "_next";
	
	var total = Number($("#" + total_id + "").val());
	var pageNow = Number($("#" + pageNowhid_id + "").val()) + 1;
	var from = Number($("#" + from_id + "").val()) + 5;
	
	$("#" + from_id + "").val(from);
	$("#" + pageNowhid_id + "").val(pageNow);
	$("#" + pageNow_id + "").val(pageNow);
	
	limit4Name(name,from);
	
	if(from > 0){
		$("#" + prev_id + "").show();
	}else{
		$("#" + prev_id + "").hide();
	}

	if(total - from > 5){
		$("#" + next_id + "").show();
	}else{
		$("#" + next_id + "").hide();
	}
}

function limit4Name(name,from){
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/' + name + '/getAllLimit.do',
		data : {
			from : from,
			url : '/index/setting'
		},
		dataType : 'json',
		success : function(data){
			var tbody_id = "setting_" + name + "_tbody";
			var check_id = "setting_check_" + name + "id";
			var detail_id = "setting_" + name + "_detail";
			$("#" + tbody_id + "").html("");
			if(name == "user"){
				
				for (var i = 0; i < data.length; i++) {
					$("#" + tbody_id + "")
					.append(
							"<tr><td><input type=\"checkbox\" name=\"" + check_id + "\" value=\""
							+ data[i].id
							+ "\">"
							+ data[i].id
							+ "</td><td><a href=\"javascript:void(0);\" onclick=\"" + detail_id + "("
							+ data[i].id + ");\">"
							+ data[i].name
							+ "</a></td><td>"
							+ data[i].lockStatus
							+ "</td></tr>");
				}
			}else{
				for (var i = 0; i < data.length; i++) {
					$("#" + tbody_id + "")
					.append(
							"<tr><td><input type=\"checkbox\" name=\"" + check_id + "\" value=\""
							+ data[i].id
							+ "\">"
							+ data[i].id
							+ "</td><td><a href=\"javascript:void(0);\" onclick=\"" + detail_id + "("
							+ data[i].id + ");\">"
							+ data[i].name
							+ "</a></td><td>"
							+ data[i].remark
							+ "</td></tr>");
				}
			}
			if(data.length != 5){
				for(var i = 0; i < 5 - data.length;i++){
					$("#" + tbody_id + "")
					.append(
							"<tr><td>"
									+ "</td><td>"
									+ "</a></td><td>"
									+ "</td></tr>");
				}
			}
			
		}
	});
}

function whiteListAdd() {
	var add = $("#index_all_setting option:selected").val();
	$("#index_list_setting").append(
			"<option value=\"" + add + "\">" + add + "</option>");
	opDetail_WL = opDetail_WL + "添加 : " + add + "!@";
}

function whiteListDel() {
	var del = $("#index_list_setting option:selected").val();
	$("#index_list_setting option:selected").remove();
	opDetail_WL = opDetail_WL + "删除 : " + del + "!@";
}

function settingWhiteListSub() {
	var whiteList = $("#index_list_setting option").map(function() {
		return $(this).val();}).get().join(",");
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/search/updateSeachIndex.do',
		data : {
			whiteList : whiteList
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				add_notification("修改白名单")
				alert("Success!");
			} else {
				alert("failed!");
			}
		}
	});
}

function add_notification(operation){
	var username = $("#username_hidden").val();
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/notification/add.do',
		data : {
			operation : operation,
			username : username,
			opDetail : "功能暂为开放"
		},
		dataType : 'json',
		success : function(data) {
			$.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/unr/add.do',
				data : {
					operationUrl : "/index/setting",
					notification_id : data
				},
				dataType : 'json',
				success : function(data) {
					opDetail_WL = "";
				}
			});
		}
	});
}

function setting_user_detail(id) {
	window
			.open(
					"http://10.99.205.240:8080/elk3/user/detailPage.do?userId="
							+ id + "&url=/user/update",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function settingUserDetailLoad() {
	var userId = $("#user_detail_id").val();
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/user/detail.do',
		data : {
			userId : userId,
			url : '/user/update'
		},
		dataType : 'json',
		success : function(data) {
			$("#user_detail_name").val(data.name);
			$("#user_detail_password").val(data.password);
			$("#user_detail_lockStatus").val(data.lockStatus);
		}
	});
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/role/getRoleByUid.do',
		data : {
			uid : userId,
			url : '/user/update'
		},
		dataType : 'json',
		success : function(data){
			$("#Role_list_settingUserDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#Role_list_settingUserDetail").append(
						"<option value=\"" + data[i].id + "\">"
								+ data[i].remark + "</option>");
			}
		}
	});
	$.ajax({
		type : 'post',
		url : "http://10.99.205.240:8080/elk3/role/getAll.do",
		data : {
			url : '/user/update'
		},
		dataType : 'json',
		success : function(data){
			$("#Role_all_settingUserDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#Role_all_settingUserDetail").append(
						"<option value=\"" + data[i].id + "\">"
								+ data[i].remark + "</option>");
			}
		}
	});
}

function Role_list_add() {
	var id = $("#Role_all_settingUserDetail option:selected").val();
	var name = $("#Role_all_settingUserDetail option:selected").text();
	$("#Role_list_settingUserDetail").append(
			"<option value=\"" + id + "\">" + name + "</option>");
}

function Role_list_delete() {
	$("#Role_list_settingUserDetail option:selected").remove();
}

function Setting_userDetail_submit() {
	var id = $("#user_detail_id").val();
	var name = $("#user_detail_name").val();
	var password = $("#user_detail_password").val();
	var lockStack = $("#user_detail_lockStatus").val();
	var roleIds = $("#Role_list_settingUserDetail option").map(function() {
		return $(this).val();
	}).get().join(",");
	var flag = false;

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/user/updateUser.do',
		data : {
			id : id,
			name : name,
			password : password,
			lockStack : lockStack,
			url : '/user/update'
		},
		dataType : 'json',
		success : function(data) {
			if (data == 1) {
				flag = true;
			}
		}
	});

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/urr/deleteByUid.do',
		data : {
			userId : id,
			url : '/user/update'
		},
		dataType : 'json',
		success : function(data) {
			if(roleIds != null && roleIds != "" && roleIds != undefined){
				
				$.ajax({
					type : 'post',
					url : 'http://10.99.205.240:8080/elk3/urr/addMore.do',
					data : {
						userId : id,
						roleIds : roleIds,
						url : '/user/update'
					},
					dataType : 'json',
					success : function(data) {
						if (data >= 0 && flag) {
							add_notification("修改" + name + "用户");
							alert("success");
						} else {
							alert("falsed");
						}
					}
				});
			}else{
				alert("success");
			}
		}
	});
}

function setting_role_detail(id) {
	window
			.open(
					"http://10.99.205.240:8080/elk3/role/detailPage.do?roleId="
							+ id + "&url=/role/update",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function settingRoleDetailLoad() {
	var roleId = $("#role_detail_id").val();
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/role/getRoleById.do',
		data : {
			roleId : roleId,
			url : '/role/update'
		},
		dataType : 'json',
		success : function(data) {
			$("#role_detail_name").val(data.name);
			$("#role_detail_remark").val(data.remark);
			
		}
	});
	
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/getByRoleId.do',
		data : {
			id : roleId,
			url : '/role/update'
		},
		dataType : 'json',
		success : function(data){
			$("#permission_list_settingroleDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#permission_list_settingroleDetail").append(
						"<option value=\"" + data[i].id + "\">"
								+ data[i].remark + "</option>");
			}
		}
	});

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/getAll.do',
		data : {
			url : '/role/update'
		},
		dataType : 'json',
		success : function(data) {
			$("#permission_all_settingroleDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#permission_all_settingroleDetail").append(
						"<option value=\"" + data[i].id + "\">" + data[i].remark
								+ "</option>");
			}
		}
	});
}

function Permission_list_add() {
	var id = $("#permission_all_settingroleDetail option:selected").val();
	var name = $("#permission_all_settingroleDetail option:selected").text();
	$("#permission_list_settingroleDetail").append(
			"<option value=\"" + id + "\">" + name + "</option>");
}

function Permission_list_delete() {
	$("#permission_list_settingroleDetail option:selected").remove();
}

function add_user_submit() {
	var name = $("#user_detail_name").val();
	var password = $("#user_detail_password").val();
	var lockStack = $("#user_detail_lockStatus").val();
	var roleIds = $("#Role_list_addUserDetail option").map(function() {
		return $(this).val();
	}).get().join(",");
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/user/add.do',
		data : {
			name : name,
			password : password,
			lockStack : lockStack,
			url : '/user/add'
		},
		dataType : 'json',
		success : function(data) {
			if (data.id != null && data.id != "" && data.id != undefined) {
				$.ajax({
					type : 'post',
					url : 'http://10.99.205.240:8080/elk3/urr/addMore.do',
					data : {
						userId : data.id,
						roleIds : roleIds,
						url : '/user/add'
					},
					dataType : 'json',
					success : function(data) {
						if (data > 0) {
							add_notification("添加用户'" + name + "'")
							alert("success");
						} else {
							alert("failed");
						}
					}
				});
			}
		}
	});
}

function add_user_detail() {
	window
			.open(
					"http://10.99.205.240:8080/elk3/user/addUserPage.do?url=/user/add",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function AddUserLoad() {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/role/getAll.do',
		data : {
			url : '/user/add'
		},
		dataType : 'json',
		success : function(data) {
			$("#Role_all_addUserDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#Role_all_addUserDetail").append(
						"<option value=\"" + data[i].id + "\">" + data[i].name
								+ "</option>");
			}
		}
	});
}

function addUser_Role_list_add() {
	var id = $("#Role_all_addUserDetail option:selected").val();
	var name = $("#Role_all_addUserDetail option:selected").text();
	$("#Role_list_addUserDetail").append(
			"<option value=\"" + id + "\">" + name + "</option>");
}

function addUser_Role_list_delete() {
	$("#Role_list_addUserDetail option:selected").remove();
}

function delete_user_btn() {
	var uids = $('input[name="setting_check_userid"]:checked').map(function() {
		return $(this).val();
	}).get().join(",");

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/urr/deleteByUid.do',
		data : {
			userId : uids,
			url : '/user/delete'
		},
		dataType : 'json',
		success : function(data) {
			$.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/unr/rmUNR.do',
				data : {
					userIds : uids,
					url : '/user/delete'
				},
				dataType : 'json',
				success : function(data) {
					$.ajax({
						type : 'post',
						url : 'http://10.99.205.240:8080/elk3/user/delete.do',
						data : {
							uids : uids,
							url : '/user/delete'
						},
						dataType : 'json',
						success : function(data) {
							if (data > 0) {
								add_notification("删除用户");
								alert("success");
							} else {
								alert("failed");
							}
						}
					});
				}
			});
		}
	});

}

function Setting_roleDetail_submit() {
	var id = $("#role_detail_id").val();
	var name = $("#role_detail_name").val();
	var remark = $("#role_detail_remark").val();
	var permissionIds = $("#permission_list_settingroleDetail option").map(
			function() {
				return $(this).val();
			}).get().join(",");
	var flag = false;

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/role/update.do',
		data : {
			'id' : id,
			'name' : name,
			'remark' : remark,
			'url' : '/role/update'
		},
		dataType : 'json',
		success : function(data) {
			if (data == 1) {
				flag = true;
			}
		}
	});

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/rpr/delete.do',
		data : {
			roleIds : id,
			url : '/role/update'
		},
		dataType : 'json',
		success : function(data) {
			if(permissionIds != null && permissionIds != "" && permissionIds != undefined){
				$.ajax({
					type : 'post',
					url : 'http://10.99.205.240:8080/elk3/rpr/add.do',
					data : {
						roleId : id,
						permissionIds : permissionIds,
						url : '/role/update'
					},
					dataType : 'json',
					success : function(data) {
						if (data >= 0 && flag) {
							alert("success");
						} else {
							alert("falsed");
						}
					}
				});
			}else{
				alert("success");
			}
		}
	});
}

function addRole_Permission_list_add() {
	var id = $("#permission_all_addroleDetail option:selected").val();
	var name = $("#permission_all_addroleDetail option:selected").text();
	$("#permission_list_addroleDetail").append(
			"<option value=\"" + id + "\">" + name + "</option>");
}

function addRole_Permission_list_delete() {
	$("#permission_list_addroleDetail option:selected").remove();
}

function addRoleDetailLoad() {
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/getAll.do',
		data : {
			url : '/role/add'
		},
		dataType : 'json',
		success : function(data) {
			$("#permission_all_addroleDetail").html("");
			for (var i = 0; i < data.length; i++) {
				$("#permission_all_addroleDetail").append(
						"<option value=\"" + data[i].id + "\">" + data[i].name
								+ "</option>");
			}
		}
	});
}

function add_roleDetail_submit() {
	var name = $("#role_detail_name").val();
	var remark = $("#role_detail_remark").val();
	var permissionIds = $("#permission_list_addroleDetail option").map(
			function() {
				return $(this).val();
			}).get().join(",");

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/role/add.do',
		data : {
			name : name,
			remark : remark,
			url : '/role/add'
		},
		dataType : 'json',
		success : function(data) {
			$.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/rpr/add.do',
				data : {
					roleId : data,
					permissionIds : permissionIds,
					url : '/role/add'
				},
				dataType : 'json',
				success : function(data) {
					if (data >= 0) {
						add_notification("添加" + name + "角色");
						alert("success");
					} else {
						alert("falsed");
					}
				}
			});
		}
	});
}

function add_role_detail() {
	window.open("http://10.99.205.240:8080/elk3/role/addRolePage.do?url=/role/add",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function delete_role_btn() {
	var roleIds = $('input[name="setting_check_roleid"]:checked').map(function() {
		return $(this).val();
	}).get().join(",");

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/rpr/delete.do',
		data : {
			roleIds : roleIds,
			url : '/role/delete'
		},
		dataType : 'json',
		success : function(data) {
			$.ajax({
				type : 'post',
				url : 'http://10.99.205.240:8080/elk3/role/delete.do',
				data : {
					roleIds : roleIds,
					url : '/role/delete'
				},
				dataType : 'json',
				success : function(data) {
					if (data > 0) {
						add_notification("删除角色");
						alert("success");
					} else {
						alert("failed");
					}
				}
			});
		}
	});

}

function delete_permission_btn() {
	var permissionIds = $('input[name="setting_check_permissionid"]:checked').map(function() {
		return $(this).val();
	}).get().join(",");

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/delete.do',
		data : {
			permissionIds : permissionIds,
			url : '/permission/delete'
		},
		dataType : 'json',
		success : function(data) {
			if(data > 0){
				add_notification("删除权限");
				alert("success");
			}else{
				alert("failed");
			}
		}
	});

}

function add_permission_detail() {
	window.open("http://10.99.205.240:8080/elk3/permission/addPermissionPage.do?url=/permission/add",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function add_permissionDetail_submit() {
	var name = $("#permission_detail_name").val();
	var operationUrl = $("#permission_detail_operationUrl").val();
	var remark = $("#permission_detail_remark").val();

	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/add.do',
		data : {
			name : name,
			operationUrl : operationUrl,
			remark:remark,
			url : '/permission/detail'
		},
		dataType : 'json',
		success : function(data) {
			if(data > 0 ){
				add_notification("添加权限");
				alert("success");
			}else{
				alert("failed");
			}
		}
	});
}

function setting_permission_detail(id) {
	window.open("http://10.99.205.240:8080/elk3/permission/permissionDetailPage.do?id=" + id + "&url=/permission/update",
					"_blank",
					"toolbar=no, location=yes, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=yes, width=600, height=700");
}

function settingPermissionDetailLoad(){
	var permissionId = $("#permission_detail_id").val();
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/getById.do',
		data : {
			permissionId : permissionId,
			url : '/permission/update'
		},
		dataType : 'json',
		success : function(data) {
			$("#permission_detail_name").val(data.name);
			$("#permission_detail_remark").val(data.remark);
			$("#permission_detail_operationUrl").val(data.operationUrl);
		}
	});
}

function setting_permissionDetail_submit(){
	var id = $("#permission_detail_id").val();
	var name = $("#permission_detail_name").val();
	var operationUrl = $("#permission_detail_operationUrl").val();
	var remark = $("#permission_detail_remark").val();
	
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/permission/update.do',
		data : {
			id : id,
			name : name,
			operationUrl : operationUrl,
			remark : remark,
			url : '/permission/update'
		},
		dataType : 'json',
		success : function(data) {
			if(data >0){
				add_notification("修改"+name+"权限");
				alert("success");
			}else{
				alert("failed");
			}
		}
	});
}

function singleLoad(){
	var username = $("#username").val();
	var author = $("#author").val();
	var index = $("#index").val();
	if(username == author){
		$("#edit_btn").show();
	}else{
		$.ajax({
			type : 'post',
			url : 'http://10.99.205.240:8080/elk3/setting/match.do',
			data : {
				url : "/blog/update"
			},
			dataType : 'json',
			success : function(data) {
				if(data && index == "kb1"){
					$("#edit_btn").show();
				}
			}
		});
	}
}

function showNotification(){
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/notification/getMsg.do',
		data :{
			flag : 'show',
			from : 0,
			size : 0
		},
		dataType : 'json',
		success : function(data){
			var temp = 0;
			$("#notification_ul").html("");
			for(var i = 0;i<data.length;i++){
				if(temp <= 5){
					temp++;
					$("#notification_ul").append("<li><a href=\"#\">"+data[i].msg+"</a></li>");
				}
			}
			$("#notification_ul").append("<li><a href=\"/elk3/notification/centerPage.do\">消息中心</a></li>");
			$("#notification_num").text("");
			$("#notification_num").text(temp);
		}
	});
}

function notificationCenterLoad(from){
	var total;
	$.ajax({
		type : 'post',
		url : 'http://10.99.205.240:8080/elk3/notification/getMsg.do',
		data :{
			flag : 'cc',
			from : from,
			size : 10
		},
		dataType : 'json',
		success : function(data){
			var temp = 0;
			$("#notification_ul").html("");
			$("#show_blog").html("");
			for(var i = 0;i<data.length;i++){
				if(data[i].status == 0 && temp <= 5){
					temp++;
					$("#show_blog")
					.append(
							"<div class=\"col-md-12\"><div class=\"card\" id=\"notification_content_"+i+"\" onclick=\"notification_show("+i+","+data.length+");\">"
							+ "<div class=\"header\">"
							+ "<p class=\"category\">"
							+ data[i].msg
							+ "</p>"
							+ "<input type=\"hidden\" id=\"notification_id_"+i+"\" value=\""+data[i].id+"\"> </div><div class=\"footer\"><hr><div class=\"stats\">"
							+ data[i].time
							+"<input type=\"hidden\" id=\"notification_oc_"+i+"\" value=\"0\">"
							+ "</div></div></div></div>");
				}else{
					$("#show_blog")
					.append(
							"<div class=\"col-md-12\"><div class=\"card\" style=\"background-color:#D3D3D3\" id=\"notification_content_"+i+"\">"
							+ "<div class=\"header\">"
							+ "<p class=\"category\">"
							+ data[i].msg
							+ "</p>"
							+ "<input type=\"hidden\" id=\"notification_id_"+i+"\" value=\""+data[i].id+"\"></div><div class=\"footer\"><hr><div class=\"stats\">"
							+ data[i].time
							+ "</div></div></div></div>");
				}
			}
			$("#notification_num").text("");
			$("#notification_num").text(temp);
		}
	});
	
	$.ajax({
		type:'post',
		url:'http://10.99.205.240:8080/elk3/notification/getTotal.do',
		data:{
			
		},
		dataType:'json',
		success : function(data){
			total = data.total;
			if(total - from > 10){
				$("#downPage").show();
			}else{
				$("#downPage").hide();
			}
			
			if(from >= 10 && total > 0){
				$("#upPage").show();
			}else{
				$("#upPage").hide();
			}
			if(total > 10){
				var totalPage = Math.ceil(total/10);
				var page = $("#pageNow").val();
				$("#pageNum_Div").html("");
				$("#pageNum_Div").append("<input type=\"text\" id=\"pageNum_now\" value=\""+page+"\" onkeypress=\"pageNow_keypress(event);\" style=\"width:20px\"> /" + totalPage);
				$("#total").val(totalPage);
			}
		}
	});
}

function notification_show(num,length){
	var div_id = "notification_content_" + num;
	var nid_id = "notification_id_" + num;
	var notification_oc_ = "notification_oc_" + num;
	$("#" + div_id + "").css("background-color","#D3D3D3");
	var ocid = $("#" + notification_oc_ + "").val();
	var id = $("#" + nid_id + "").val();
	if(ocid == 0){
		$.ajax({
			type : 'post',
			url : 'http://10.99.205.240:8080/elk3/unr/readed.do',
			data :{
				notification_id : id,
				status : 1
			},
			dataType : 'json',
			success : function(data){
				if(data == "login"){
					window.location.href = "http://10.99.205.240:8080/elk3/login/login.do";
				}
			}
		});
		$("#" + notification_oc_ + "").val("1");
	}
}

function notification_upPage_btn_click(){
	var from = Number($("#from").val()) - 10;
	notificationCenterLoad(from);
	var page = Number($("#pageNow").val()) - 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}

function notification_downPage_btn_click(){
	var from = Number($("#from").val()) + 10;
	notificationCenterLoad(from);
	var page = Number($("#pageNow").val()) + 1;
	$("#from").val(from.toString());
	$("#pageNow").val(page);
}

function CloseWebPage(){
	window.close();
}

