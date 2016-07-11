<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<title></title>
</head>
<body>
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <img alt="image" class="img-circle"
						src="content/img/acms/headimg.jpg" />
					</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
						class="clear"> <span class="block m-t-xs"> <strong
								class="font-bold">admin</strong>
						</span> <span class="text-muted text-xs block">管理员 <b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="#">Profile</a></li>
						<li><a href="#">Contacts</a></li>
						<li><a href="#">Mailbox</a></li>
						<li class="divider"></li>
						<li><a href="javascript:void(0);" onclick="logout()">Logout</a></li>
					</ul>
				</div>
				<div class="logo-element">ACMS</div>
			</li>
			<li><a class="J_menuItem" href="layouts.html"><i
					class="fa fa-diamond"></i> <span class="nav-label">修改密码</span></a></li>

			<c:forEach items="${firstMenu}" var="firstmenu">
				<li><a href="${path }${firstmenu.uri}"><i
						class="fa fa-th-large"></i> <span class="nav-label">
							${firstmenu.menuname}</span> <span class="fa arrow"></span></a>
					<ul class="nav nav-second-level collapse">
						<c:forEach items="${firstmenu.childMenus }" var="secondmenu">

							<li><a class="J_menuItem" href="${path }${secondmenu.uri}">${secondmenu.menuname}</a></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
			<%--                     <li>
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">系统设置</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                        	<li><a class="J_menuItem" href="dashboard_2.html">用户管理</a></li>
                            <li><a class="J_menuItem" href="dashboard_2.html">菜单管理</a></li>
                            <li><a class="J_menuItem" href="dashboard_2.html">日志管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">产品管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                        	<li><a class="J_menuItem" href="dashboard_2.html">产品列表</a></li>
                            <li><a class="J_menuItem" href="dashboard_2.html">产品更新</a></li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">应用中心管理</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                        	<li><a class="J_menuItem" href="${path}/app/index">应用管理</a></li>
                            <li><a class="J_menuItem" href="${path}/sort/index">分类管理</a></li>
                            <li><a class="J_menuItem" href="${path}/specialtopic/index">专题管理</a></li>
                            <li><a class="J_menuItem" href="${path}/indiv/index">前端定制</a></li>
                            <li><a class="J_menuItem" href="${path}/indivConf/index">配置更新</a></li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">统计</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                        	<li><a class="J_menuItem" href="dashboard_2.html">应用平台产品统计</a></li>
                            <li><a class="J_menuItem" href="${path}/appstatistics/index">应用中心分析统计</a></li>
                        </ul>
                    </li> --%>
		</ul>

	</div>
</body>
<script type="text/javascript">
		function logout(){
			window.location.href = "${spath}/user/logout";
		}
	</script>
</html>
