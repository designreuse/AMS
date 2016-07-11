<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title></title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<link href="${spath }/content/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="${spath}/content/css/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="${spath}/content/js/acms/toastr/toastr.min.css" rel="stylesheet">

    <link href="${spath }/content/css/acms/animate.css" rel="stylesheet">
    <link href="${spath }/content/css/acms/style.css" rel="stylesheet">
    <link href="${spath }/content/css/acms/style.min.css" rel="stylesheet">
	
	<!-- Main -->
	<script type="text/javascript" src="${spath}/content/js/jquery/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="${spath}/content/js/jquery/jquery.md5.js"></script>
	<script type="text/javascript" src="${spath}/content/css/bootstrap/js/bootstrap.min.js" ></script>
	
	<!-- Mainly scripts -->
    <script src="${spath}/content/js/acms/metisMenu/jquery.metisMenu.js"></script>
    <script src="${spath}/content/js/acms/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${spath}/content/js/acms/inspinia.js"></script>
    <script src="${spath}/content/js/acms/pace/pace.min.js"></script>
    
    <!-- Contabs -->
    <script src="${spath}/content/js/acms/contabs.min.js"></script>

    <!-- jQuery UI -->
    <script src="${spath}/content/js/acms/jquery-ui/jquery-ui.min.js"></script>

    <!-- Sparkline -->
    <script src="${spath}/content/js/acms/sparkline/jquery.sparkline.min.js"></script>

    <!-- ChartJS-->
    <script src="${spath}/content/js/acms/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="${spath}/content/js/acms/toastr/toastr.min.js"></script>
  </head>
  
  <body>
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<jsp:include page="include/leftmenu.jsp"></jsp:include>
		</nav>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<jsp:include page="include/header.jsp"></jsp:include>
			</div>
			<div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content" style="margin-left: 0px;">
                        <a href="javascript:;" class="J_menuTab active" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:void(0);" onclick="logout()" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="" frameborder="0" data-id="index_v1.html"
					seamless="" style="display: inline;"></iframe>
			</div>
			<div class="footer">
				<div class="pull-right">
					© 2016-2018 <a href="#" target="_blank">ACMS</a>
				</div>
			</div>



		</div>
	</div>
</body>
	<script type="text/javascript">
	
		$(document).ready(
				function() {
					setTimeout(function() {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							positionClass : 'toast-top-right',
							showMethod : 'fadeIn',
							hideMethod : 'fadeOut',
							timeOut : 4000
						};
						toastr.success('2016-06-16 10:10:10', 'Welcome to ACMS');
					}, 1300);
				});
		function logout(){
			window.location.href = "${spath}/user/logout";
		}
	</script>
</html>
