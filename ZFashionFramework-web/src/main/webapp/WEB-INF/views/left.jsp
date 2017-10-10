<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--左侧导航开始-->
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="nav-close">
		<i class="fa fa-times-circle"></i>
	</div>
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span> 
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
					<span class="clear">
							<span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
							<span class="text-muted text-xs block">超级管理员<b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
						<li><a class="J_menuItem" href="profile.html">个人资料</a></li>
						<li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
						<li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
						<li class="divider"></li>
						<li><a href="login">安全退出</a></li>
					</ul>
				</div>
				<div class="logo-element">H+</div>
			</li>
			
			<li>
				<a href="javascript:void(0);"><i class="fa fa-group"></i><span class="nav-label">用户中心</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a class="J_menuItem" href="contacts" data-index="0">代理中心</a></li>
					<li><a class="J_menuItem" href="index_v2.html">客户中心</a></li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:void(0);">
					<i class="fa fa-cubes"></i> 
					<span class="nav-label">商品中心</span><span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li><a class="J_menuItem" href="mail_detail.html">商品管理</a></li>
					<li><a class="J_menuItem" href="mailbox.html">分类管理</a></li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:void(0);">
					<i class="fa fa-book"></i> 
					<span class="nav-label">知识讲堂</span><span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li><a class="J_menuItem" href="mailbox.html">品牌知识</a></li>
					<li><a class="J_menuItem" href="mail_detail.html">代购知识</a></li>
				</ul>
			</li>
			
			<li>
				<a href="javascript:void(0);">
					<i class="fa fa-gear"></i> 
					<span class="nav-label">设置</span><span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li><a class="J_menuItem" href="dic/list">字典设置</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>
<!--左侧导航结束-->