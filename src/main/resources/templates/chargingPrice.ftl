<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>电价管理</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    .layui-form{margin:5px auto;width:90%;}
    .layui-table{margin-top:20px;}
    .layui-table .layui-btn{
    	width:38px;
    	padding:0 7px;
    	background-color:#666;
    }
    .layui-table td{padding:5px 15px;}
    .layui-btn .layui-icon{font-size:24px;}
	#addAreaPriceForm .layui-input,#addAreaPriceForm .layui-textarea{
		width:85%;
	}
	.layui-textarea{
		resize:none;
	}
	#addAreaPriceForm .layui-form-item{
		margin-bottom:20px;
	}
	#addAreaPriceForm button{
		margin-right:40px;
	}
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div id="content">
            <div class="layui-form" v-if="page.list.length > 0">
            	<button class="layui-btn" @click=clickAddBtn()>添加新电价</button>
            	
            	<table class="layui-table">
            		<thead>
					    <tr>
					      	<th>序号</th>
					      	<th>电价名称</th>
					      	<th>电价</th>
					      	<th>操作</th>
					    </tr>
					</thead>
					<tbody>
						<tr v-for="(price,index) in page.list">
							<td>{{price.id}}</td>
							<td>{{price.name}}</td>
							<td>{{price.uniPrice}}</td>
							<td>
								<button class="layui-btn layui-btn-sm" @click="clickUpdateBtn(price,index)">
									<i class="layui-icon">&#xe642;</i>
								</button>
								<button class="layui-btn layui-btn-sm" @click="removeAreaPrice(price, index)">
									<i class="layui-icon">&#xe640;</i>
								</button>
							</td>
						</tr>
					</tbody>
            	</table>
            </div>
        	<div v-else>
                暂无电价
            </div>
            <div id="page" style="text-align: center"></div>
        </div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<div style="display:none" id="updatePriceDIV">
<div style="text-align: center; width: 100%; height: 50px; line-height: 50px; font-size: 24px;margin-top:10px;">修改电价</div>
<div class="layui-form" id="updateAreaPriceForm" style="margin-left:10px;">
  <div class="layui-form-item">
    <label class="layui-form-label">电价名称</label>
    <div class="layui-input-block">
     <input type="text" id="updatePriceName" name="updatePriceName" v-model="updatePriceName" required  lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">电价</label>
    <div class="layui-input-block">
      <input type="text" id="updateUniPrice" name="updateUniPrice" v-model="updateUniPrice" required lay-verify="required" placeholder="请输入电价" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">描述</label>
    <div class="layui-input-block">
      <textarea id="updateDescription" name="updateDescription" v-model="updateDescription" required lay-verify="required" placeholder="请输入相关描述" autocomplete="off" class="layui-textarea"></textarea>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block" style="text-align:center">
      <button class="layui-btn" id="submitUpdateBtn" @click="submitUpdatePrice">立即提交</button>
      <button class="layui-btn layui-btn-primary" id="cancel">取消</button>
    </div>
  </div>
</div>
</div>
<!--新增电价-->
<div id="addAreaPriceDIV" style="display:none">
<div style="text-align: center; width: 100%; height: 50px; line-height: 50px; font-size: 24px;margin-top:10px;">新电价</div>
<div class="layui-form" id="addAreaPriceForm">
	<div class="layui-form-item">
	    <label class="layui-form-label">电价名称</label>
		<div class="layui-input-block">
			<input type="text" id="priceName" name="priceName" v-model="priceName" required  lay-verify="required" placeholder="请输入电价名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">电价</label>
		<div class="layui-input-block">
			<input type="text" id="uniPrice" name="uniPrice" v-model="uniPrice" required lay-verify="required" placeholder="请输入电价" autocomplete="off" class="layui-input">
		</div>
    </div>
  	<div class="layui-form-item">
    	<label class="layui-form-label">描述</label>
    	<div class="layui-input-block">
      		<textarea id="description" name="description" v-model="description" required lay-verify="required" placeholder="请输入相关描述" autocomplete="off" class="layui-textarea"></textarea>
    	</div>
 	</div>
  	<div class="layui-form-item">
    	<label class="layui-form-label">创建人</label>
    	<div class="layui-input-block">
      		<input type="text" id="creatorId" value=${Session["member"].creatorId?if_exists} name="creatorId" v-model="creatorId" required lay-verify="required" autocomplete="off" class="layui-input">
		</div>
  	</div>
  	<div class="layui-form-item">
	    <label class="layui-form-label">运营商ID</label>
   	    <div class="layui-input-block">
  			<input type="text" id="operatorId" name="operatorId" value=${Session["member"].orgId?if_exists} v-model="operatorId" required lay-verify="required" autocomplete="off" class="layui-input">
		</div>
  	</div>
  	<div class="layui-form-item">
	    <div class="layui-input-block" style="text-align:center">
	    	<button class="layui-btn" lay-filter="formDemo" id="submitAreaPrice">立即提交</button>
      		<button class="layui-btn layui-btn-primary" id="cancel">取消</button>
	    </div>
    </div>
</div>
</div>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/chargingPrice.js"></script>
</html>