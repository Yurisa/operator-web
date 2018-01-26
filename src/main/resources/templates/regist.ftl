<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/regist.css">
    
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<div>
		<div class="layui-logo" id="platformlogo">
			嘉畅智充<span id="welcome">欢迎注册</span>
		</div>
	</div>
	
	<div id="registDIV">
		<form class="layui-form" action="" id="registForm">
		  <div class="layui-form-item">
		    <label class="layui-form-label">运营商名称</label>
		    <div class="layui-input-block">
		      <input type="text" id="orgName" name="orgName" required  lay-verify="required" placeholder="请输入运营商名称" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">运营商简介</label>
		    <div class="layui-input-block">
		      <textarea name="description" id="description" lay-verify="desc" placeholder="请输入运营商简介" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">管理员姓名</label>
		    <div class="layui-input-block">
		      <input type="text" id="memberName" name="name" required  lay-verify="adminname" placeholder="请输入管理员姓名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">管理员帐号</label>
		    <div class="layui-input-block">
		      <input type="number" id="memberId" name="account" required  lay-verify="required" placeholder="请输入帐号" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">管理员密码</label>
		    <div class="layui-input-block">
		      <input id="psw" type="password" name="password" required lay-verify="psw" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">密码确认</label>
		    <div class="layui-input-block">
		      <input type="password" name="confirmpsw" id="confirmpsw" required lay-verify="confirmpsw" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">电子邮箱</label>
		    <div class="layui-input-block">
		      <input id="email" type="text" name="email" required  lay-verify="email|required" placeholder="请输入电子邮箱" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">手机号码</label>
		    <div class="layui-input-block">
		      <input id="tel" type="text" name="tel" required  lay-verify="phone|number|required" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" type="button" id="submitBtn" @click="submitRegist">提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">取消</button>
		    </div>
		  </div>
		</form>
	</div>
</div>
</body>
<#include "components/script.ftl">
<script src="/js/pagejs/regist.js"></script>
</html>
