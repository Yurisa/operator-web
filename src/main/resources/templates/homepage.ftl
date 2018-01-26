<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/homepage.css">
    
</head>
<body>
<div class="layui-layout layui-layout-admin" id="app">
	<#--header-->
	<div class="layui-header">
		<div class="layui-logo" id="platformlogo">
			嘉畅电动汽车充电运营平台
		</div>
		 
		<#if Session["member"]?exists>  
		<ul class="layui-nav layui-layout-right">
	        <li class="layui-nav-item" id="memberPic">
	            <a href="javascript:;">
	                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
	               ${Session["member"].name?if_exists}
	            </a>
	            <dl class="layui-nav-child" id="nav-dl" style="top:60px">
	                <dd><a href="">基本资料</a></dd>
	                <dd><a href="">安全设置</a></dd>
	                <dd><a href="/chargingStation">管理中心</a></dd>
	                <dd><a href="">退出登录</a> </dd>
	            </dl>
	        </li>
	    </ul>
		<#else>
		<ul class="layui-nav layui-layout-right" lay-filter="">
			<li class="layui-nav-item"><a href="javascript:;" @click="loginBtn">登录</a></li>
			<li class="layui-nav-item"><a href="/regist">注册</a></li>
		</ul>
	    </#if>
	</div>
	<#-- 轮播图 -->
    <div class="layui-carousel" id="picCarousel">
        <div carousel-item>
		    <div><img src="/hpbg1.jpg"></div>
		    <div><img src="/hpbg2.jpg"></div>
		    <div><img src="/hpbg3.jpg"></div>
		    <div><img src="/hpbg4.jpg"></div>
		    <div><img src="/hpbg5.jpg"></div>
  		</div>
    </div>
    <#-- 运营数据 -->
	<table class="layui-table" id="table">
		<thead>
		    <tr>
		    	<th>厂商数量</th>
		      	<th>产品数量</th>
		     	<th>运营商数量</th>
		     	<th>充电站数量</th>
		     	<th>充电桩数量</th>
		     	<th>充电次数</th>
		     	<th>充电电量</th>
		    </tr> 
		</thead>
		<tbody>
		    <tr>
			     <td>120</td>
			     <td>2400</td>
			     <td>100</td>
			     <td>50</td>
			     <td>20</td>
			     <td>345</td>
			     <td>34556</td>
		    </tr>
		</tbody>
	</table>
    <#-- 运营新闻 -->
    <div class="layui-container" style="margin-top:25px;">
    	<div class="layui-row layui-col-space30">
    		<h1 class="news-big-title" style="position:relative">
				<span>运营新闻</span>
				<a class="a-more">更多</a>
			</h1>
    		<div class="layui-col-md5">
    			<div id="left-img">
    				<img src="/newspic1.jpg">
    				<a class="layui-elip" id="left-img-a">xxxxxx</a>
    			</div>
    		</div>
    		<div class="layui-col-md7">
    			<div id="right-box">
    				<a href="" id="left-img-small"><img src="/newspic2.jpg"></a>
    				<div id="right-box-context">
    					<h3 class="layui-elip h3-title"><a class="h3-a-link">杭州xxx</a></h3>
    					<p class="lead">
				                        自2014年11月由中国汽车维修行业协会汽车维修配件工作委员会汽车维修配件工委会、
				                        杭州市机动车配件经销行业协会联合主办，杭州神辇汽车服务有限公司承办的被誉为主机厂
				         “华山论剑”的AP论坛|2014首届汽车零部件创新营销与服务高峰研讨会成功举办后，伴随着国
				                      家对汽车后市场的不断规范，各项革命性的政策出台，以及2015年各类汽配电商风起云涌，加
				                      上同质件的加快推进，各大主机厂及配套服务企业积极关注着后市场的演变。
                    	</p>
                    	<p style="float:right;margin-top:5px">2017-11-22</p>
    				</div>
    			</div>
    			<div id="right-box" style="padding-top:10px;">
    				<a href="" id="left-img-small"><img src="/newspic3.jpg"></a>
    				<div id="right-box-context">
    					<h3 class="layui-elip h3-title"><a class="h3-a-link">杭州xxx</a></h3>
    					<p class="lead">
				                        自2014年11月由中国汽车维修行业协会汽车维修配件工作委员会汽车维修配件工委会、
				                        杭州市机动车配件经销行业协会联合主办，杭州神辇汽车服务有限公司承办的被誉为主机厂
				         “华山论剑”的AP论坛|2014首届汽车零部件创新营销与服务高峰研讨会成功举办后，伴随着国
				                      家对汽车后市场的不断规范，各项革命性的政策出台，以及2015年各类汽配电商风起云涌，加
				                      上同质件的加快推进，各大主机厂及配套服务企业积极关注着后市场的演变。
                    	</p>
                    	<p style="float:right;margin-top:10px">2017-11-22</p>
    				</div>
    			</div>
    		</div>
    	</div>
    	<hr class="layui-bg-gray">
    	<div class="layui-row layui-col-space30">
    		<div class="layui-col-md6">
    			<h1 class="news-big-title" style="position:relative">
    				<span>运营新闻</span>
    				<a class="a-more">更多</a>
    			</h1>
    			
    			<ul>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    			</ul>
    		</div>
    		<div class="layui-col-md6">
    			<h1 class="news-big-title" style="position:relative">
    				<span>运营新闻</span>
    				<a class="a-more">更多</a>
    			</h1>
    			
    			<ul>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    				<li><h3 style="margin:10px 0;">
    					<span class="layui-badge-dot layui-bg-black"></span>
    					<a class="layui-inline layui-elip h3-a-link" style="width:70%">自2014年11月由中国汽车维修行业协会汽车维修</a>
    					<span style="float:right">2017-11-23</span>
    				</h3></li>
    			</ul>
    		</div>
    	</div>
    </div>
    <#-- 底部 -->
    <footer>
    	<div id="footer"><p>©2017 嘉畅智充</p></div>
    </footer>
</div>
</body>
<div id="loginDIV">
	<h1 id="login-title">登录</h1>
	<div class="layui-form" action="" id="loginForm">
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" id="organizationName" v-model="name" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">密码</label>
	    <div class="layui-input-block">
	      <input type="password" id="orLoginPassword" name="password" v-model="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" id="loginSubmit">立即提交</button>
	      <button class="layui-btn layui-btn-primary" id="cancel">取消</button>
	    </div>
	  </div>
	</div>
</div>
<#include "components/script.ftl">
<script src="/js/pagejs/homepage.js"></script>
</html>
