<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>充电站管理</title>
    <link rel="stylesheet" href="/css/chargingStation.css">
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

        <div class="layui-container">
            <div class="layui-row">


                <div class="layui-col-xs6 layui-col-sm4 layui-col-md3"  v-for="station in page.list">
                    <div class="layui-row">
                        <div class="layui-col-sm-11  myChargingStation">
                            <div class="myChargingStationContainer">
                                <div class="chargingStationName layui-row"><span class="chargingStationNameSpan layui-col-xs9">{{station.name}}</span><i  @click="transToUpdate(station)" class="iconfont" style="color: rgb(166, 166, 166);font-size: 28px;margin-right:13%;float:right; position: relative; top:-5px;">&#xe68f;</i></div>
                                <div class="chargingStationPlace">
                                    <div class="placeName  layui-col-xs9">{{station.detailAddress}}</div>
                                    <div   @click="transToMap(station)" class="layui-icon  layui-col-xs3">&#xe715;</div>
                                </div>
                                <div class="chargingStationIcons layui-row">
                                    <div class="layui-col-xs3 "><i class="iconfont chargingPileNumIcon" style="color: #cccccc; font-size: 34px;">&#xe610;</i><span>{{station.pileNum}}</span></div>
                                    <div class="layui-col-xs3"><i class="iconfont chargingNumIcon" style="color:#29a329; font-size: 30px;">&#xe60d;</i><span>{{station.chargingNum}}</span></div>
                                    <div class="layui-col-xs3"><i class="iconfont brokenNumIcon" style="color: #e60000; font-size: 32px;">&#xe60f;</i><span>{{station.brokenNum}}</span></div>
                                    <div class="layui-col-xs2"><i class="iconfont findGroupIcon" style="color: #a6a6a6; font-size: 32px;">&#xe600;</i></div>
                                </div>
                                <div class="chargingDetails"><span>电量：{{station.totalElectricity}}kwh</span>，<span>电费：{{station.totalPrice}}元</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            <#--结束一个station-->
                <a class="layui-col-xs6 layui-col-sm4 layui-col-md3 CrossDiv" href="addChargingStation"  v-if="page.list.length%8 != 0||page.list.length==0">
                    <div class="layui-row">
                        <div class="layui-col-sm-11  myChargingStation" style="min-height: 180px;">
                            <div class="myChargingStationContainer">
                                <div id="cross"></div>
                            </div>
                        </div>
                    </div>
                </a>
            <#--结束一个station-->
                <div v-else>

                </div>
            </div>
            <div id="page" style="text-align:center;"></div>
        </div>


    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<script src="/js/pagejs/chargingStation2 .js"></script>
</html>
