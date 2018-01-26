<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=bbEWoApoNn5nU41WAdqBEwoNygbB4y6t"></script>
    <title>查看地图</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div class="layui-form-item" style="margin-left: 1%">
            <label class="layui-form-label">地理位置</label>
            <div class="layui-input-block" style="width: 21.6%;display: inline-block;margin-left: 0">
                <input v-model="keyword" type="text" name="title" placeholder="位置搜索" autocomplete="off" class="layui-input">
            </div>
        <#--<button type="button" style="width: 8%;" class="layui-btn layui-btn-normal" @click="searchByStationName">搜索&nbsp<i class="layui-icon">&#xe615;</i></button>-->
            <i class="layui-icon search-icon" @click="searchByStationName">&#xe615;</i>
            <button class="layui-btn layui-btn-normal" onclick="navigation()" style="margin-left: 3%;">开始导航</button>
        </div>
        <div id="mapArea" style=" text-align:center;margin:0 auto;">
            <div id="allmap" style=" text-align:center;position: relative;top:0;;width:92% ;height:450px;overflow: auto;"></div>
            <div id="r-result"></div>
        </div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/mapOfChargingStation.js"></script>
</html>