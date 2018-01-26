<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=bbEWoApoNn5nU41WAdqBEwoNygbB4y6t"></script>
    <title>添加充电站</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    #rightDiv .layui-input-inline:nth-child(1),#rightDiv .layui-input-inline:nth-child(2){
        width: 30%;
        padding-right: 2.5%;
        min-width: 100px;
    }
    #rightDiv .layui-input-inline:nth-child(3){
        width: 30.2%;
        margin-right: 0px;
        padding-right: 0px;
        min-width: 100px;
    }
    .serchLocation{
        position: relative;
    }
    .serchLocation .layui-icon{
        position: absolute;
        left: 92%;
        font-size: 22px;
        color: #009688;
        top: 8px;
        cursor: pointer;
    }
    @media screen and (max-width:1350px){
        #rightDiv{
            position: relative;
            left:110px;
            width:45%;
        }
    }
    @media screen and (max-width:1024px){
        #rightDiv .layui-input-inline:nth-child(1),#rightDiv .layui-input-inline:nth-child(2),#rightDiv .layui-input-inline:nth-child(3){
            min-width:245px;
        }
        .serchLocation .layui-icon{
            position: absolute;
            left: 87%;
            font-size: 22px;
            color: #009688;
            top: 8px;
        }
    }



</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div style="height: 145%;padding: 10px">
            <div style="display: inline-block; width: calc(100% - 22px); height: 100%; padding: 10px; border: 1px solid #ddd; overflow: hidden;">
                <form class="layui-form" action="" style="width: 50%;margin: 0 auto;margin-top: 20px;">
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电站名</label>
                        <div class="layui-input-block">
                            <input  v-model="name" type="text" name="name" id="chargingStationName" required lay-verify="required" autocomplete="on" placeholder="请输入充电站名" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline" style="margin-right:0px;">
                            <label class="layui-form-label">所在区域</label>
                            <div id="rightDiv" style="width:79%;min-width:;margin-right:0px" class="layui-input-inline">
                                <div class="layui-input-inline">
                                    <select name="provinceId" lay-filter="province"id="s_province">
                                        <option value="">省</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline"  lay-verify="">
                                    <select name="cityId" lay-filter="city" id="s_city">
                                        <option value="">市</option>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <select name="zoneId" lay-filter="zone" id="s_zone">
                                        <option value="">区</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">详细地址</label>
                        <div class="layui-input-block">
                            <input  v-model="detailAddress" type="text" name="chargingStationAddress" id="chargingStationAddress" required lay-verify="required" autocomplete="on" placeholder="请输入详细地址" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">站点描述</label>
                        <div class="layui-input-block">
                            <textarea   v-model="description"    placeholder="请输入站点描述" class="layui-textarea" name="chargingStationDescription" id="chargingStationDescription" required lay-verify="required" autocomplete="on"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">地理位置</label>
                        <div class="layui-input-block serchLocation">
                            <input v-model="keyword" type="text" name="keyword" id="chargingStationLocation" required lay-verify="required" autocomplete="on" placeholder="请选择地理位置" class="layui-input">
                            <i @click="searchByStationName" class="layui-icon">&#xe615;</i>
                        </div>
                        <div class="layui-input-block" style="min-height:250px;height: auto; border: 1px solid #ddd;margin-bottom:15px;margin-top: 15px; position:relative;"  id="mapArea">
                        <#--地图-->
                            <div id="allmap" style="position: relative;top:0;margin-top:0;left:0;width:100%"></div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <div class="myBtnsDiv" style="position: relative;padding:0;margin:0 auto;text-align:center;">
                                <button class="layui-btn" type="button" id="mySubmitBtn"  @click="clickBtn">提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                <a href="chargingStation" class="layui-btn layui-btn-primary" lay-filter="demo1" id="cancelBtn"style=" padding-right:0;padding-left:0;margin-right:0; min-width:65px;">取消</a>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/addChargingStation.js"></script>
</html>
