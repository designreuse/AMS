<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<title>404 - 页面不存在</title>
	<link href="<%=path %>/content/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path %>/content/css/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="<%=path %>/content/css/ams/animate.css" rel="stylesheet">
    <link href="<%=path %>/content/css/ams/style.css" rel="stylesheet">
</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1 style="font-size: 100px;">404</h1>
        <h3 class="font-bold">Page Not Found</h3>

        <div class="error-desc">
            Sorry, but the page you are looking for has note been found. Try checking the URL for error, then acmst the refresh button on your browser or try found something else in our app.
            <form class="form-inline m-t" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search for page">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>

    <!-- Main -->
	<script type="text/javascript" src="<%=path %>/content/js/jquery/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="<%=path %>/content/css/bootstrap/js/bootstrap.min.js" ></script>
</body>
</html>