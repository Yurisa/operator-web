<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>设备管理</title>
</head>
<style>
    @font-face {
        font-family: 'iconfont';  /* project id 518975 */
        src: url('//at.alicdn.com/t/font_518975_f7wru0e9n97ldi.eot');
        src: url('//at.alicdn.com/t/font_518975_f7wru0e9n97ldi.eot?#iefix') format('embedded-opentype'),
        url('//at.alicdn.com/t/font_518975_f7wru0e9n97ldi.woff') format('woff'),
        url('//at.alicdn.com/t/font_518975_f7wru0e9n97ldi.ttf') format('truetype'),
        url('//at.alicdn.com/t/font_518975_f7wru0e9n97ldi.svg#iconfont') format('svg');
    }
    .iconfont{
        line-height: normal;
        font-family:"iconfont" !important;
        font-size:28px;font-style:normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
        color:#8D8D8D;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div style="height: 100%;padding: 10px">
            <div style="display: inline-block; width: 25%; height: 100%; padding: 10px; border: 1px solid #ddd; overflow-y: hidden; overflow-x: hidden">
                <ul id="chargingPileGroup-tree" class="layui-box layui-tree"></ul>
            </div>
            <div style="display: inline-block; width: calc(75% - 48px); height: 100%; padding: 10px; border: 1px solid rgb(221, 221, 221); overflow-x: hidden;overflow-y: auto;">
                <div id="rightContent" >
                    <#--breadcrumb:xx省xx市xx区-->
                    <span class="layui-breadcrumb" id="nodeBreadcrumb">
                    <#--节点路径-->
                    </span>

                    <#--根据时间限制、站名称查询-->
                    <div style="margin-top: 20px;" id="searchArea">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="text-align: left;width:45px;">充电站</label>
                                    <div class="layui-input-inline" style="width:220px">
                                            <input type="text" v-model="search.name" name="name" autocomplete="on" class="layui-input" placeholder="请输入站名称">
                                    </div>
                                </div>
                                <div class="layui-inline" >
                                    <label class="layui-form-label"  style="text-align: left;width:60px;">统计时间</label>
                                    <div class="layui-input-inline" style="width:230px">
                                        <input style="text-align: center" id="timeInput" type="text" v-model="search.time" name="time" autocomplete="on" class="layui-input" placeholder="开始时间 — 结束时间">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <a style="color:white;background-color: #009688;" class="layui-btn layui-btn-primary" lay-event="detail" id="searchBtn" @click="searchByNameOrTime" >查询</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div v-if="areaId!=-1">
                        <div v-if="page.list.length>0">
                        <#--站点的数据表格：-->
                            <table class="layui-table" id="station-new">
                                <colgroup>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>充电站名称</th>
                                    <th>桩数量</th>
                                    <th>充电次数</th>
                                    <th>充电时间(小时)</th>
                                    <th>充电电量(度)</th>
                                    <th>电费(元)</th>
                                    <th>查看</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(station,index) in page.list">
                                    <td>{{station.stationName}}</td>
                                    <td>{{station.totalPileNum}}</td>
                                    <td>{{station.totalChargingNum}}</td>
                                    <td>{{station.totalChargingHours}}</td>
                                    <td>{{station.totalChargingElectricity}}</td>
                                    <td>{{station.totalChargingPrice}}</td>
                                    <td><i class="iconfont icon-search" @click="transToPileOfStation(station.stationId)">&#xe639;</i></td>
                                </tr>
                                <tr>
                                    <th>合计</th>
                                    <th>{{totalPileNum}}</th>
                                    <th>{{totalChargingTimes}}</th>
                                    <th>{{totalChargingHours}}</th>
                                    <th>{{totalChargingPower}}</th>
                                    <th>{{totalChargingPrice}}</th>
                                    <th></th>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div v-else>没有充电站！</div>
                        <div id="page" style="text-align:center;"></div>
                    </div>

                </div>

                </div>
            </div>
        </div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/chargingStationStatistics.js"></script>
</html>