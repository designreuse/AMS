<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>ams</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${spath}/content/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${spath}/content/css/ams/animate.css" >
    <link rel="stylesheet" href="${spath}/content/css/ams/style.css" >
	<script type="text/javascript" src="${spath}/content/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${spath}/content/js/jquery/jquery.md5.js"></script>
  </head>
  
  <body class="gray-bg">
  	<div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">AMS</h1>
            </div>
            <h3>欢迎使用 AMS</h3>
            <form class="m-t" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="用户名" required autofocus />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码" required />
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="login()">登 录</button>
				<div style="height:10px;clear:both;display:block"></div>
				<div class="alert alert-success" role="alert" style="display: none;">登录成功</div>
				<div class="alert alert-danger" role="alert" style="display: none;">登录失败,用户名或密码错误</div>
				<div class="alert alert-warning" role="alert" style="display: none;">请输入用户名和密码</div>
                <a href="#"><small>忘记密码？</small></a>|<a class="text-muted text-center"><small>创建新账户！</small></a>
            </form>
        </div>
    </div>
	
	<script type="text/javascript">
	
	$(document).keydown(function(event) {
		if (event.keyCode == 13) {
			login();
		}
	});
	
  	function login(){
  		if(!$("#username").val() || !$("#password").val()){
  			$(".alert-warning").fadeIn(300).delay(2000).fadeOut(500);
  			return false;
  		}
  		$.ajax({ 
            type : "post", 
            url:'${spath}/user/login',
            cache : false, 
            async: false, 
            data:{"userName" : $("#username").val(),password : $.md5($("#password").val())},
    	    success : function(data){
    	    	alert(1);
    	  			if (data.code == 0) {
    	  				alert("成功");
    					$(".alert-success").fadeIn(300).delay(2000).fadeOut(500);
    					setTimeout(function(){
    						window.location = '${path}/index';
    					}, 1000);
    				} else {
    					$(".alert-danger").fadeIn(300).delay(2000).fadeOut(500);
    				}
    			},
            error : function() {
            	alert("连接失败！");
            }
  		});
  	}
  	</script>
 </body>
</html>
