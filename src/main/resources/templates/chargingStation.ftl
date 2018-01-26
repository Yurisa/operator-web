<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>充电站管理</title>
</head>
<style>
    #headerTr th{
        text-align: center;
    }
    @font-face {
        font-family: 'iconfont';  /* project id 502974 */
        src: url('//at.alicdn.com/t/font_502974_btphnadyw8p30udi.eot');
        src: url('//at.alicdn.com/t/font_502974_btphnadyw8p30udi.eot?#iefix') format('embedded-opentype'),
        url('//at.alicdn.com/t/font_502974_btphnadyw8p30udi.woff') format('woff'),
        url('//at.alicdn.com/t/font_502974_btphnadyw8p30udi.ttf') format('truetype'),
        url('//at.alicdn.com/t/font_502974_btphnadyw8p30udi.svg#iconfont') format('svg');
    }
    .iconfont{
           font-family:"iconfont" !important;
           font-size:28px;
           color: #8D8D8D;
           /*color: #00b3a2;*/
           font-style:normal;
           -webkit-font-smoothing: antialiased;
           -webkit-text-stroke-width: 0.2px;
           -moz-osx-font-smoothing: grayscale;
       }
</style>


<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->

        <div style="text-align:left;">
            <a class="layui-btn"  href="addChargingStation" style="margin-left: 35px">
                <i class="layui-icon">&#xe608;</i> 添加充电站
            </a>
        </div>
        <div class="layui-container" v-if="page.list.length>0" style="padding:10px 50px 20px 0">
            <table class="layui-table" style="text-align:center;">
                <colgroup>
                    <col width="220">
                    <col >
                    <col >
                    <col>
                    <col width="120">
                    <col width="40">
                    <col width="90">
                </colgroup>
                <thead>
                <tr id="headerTr">
                    <th>名称</th>
                    <th>地址</th>
                    <th>创建时间</th>
                    <th>充电桩</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody   v-for="(station,index) in page.list">
                <tr>
                    <td><a :href="'/updateChargingStation/'+station.id">{{station.name}}</a></td>
                    <td>{{station.provinceName}}{{station.cityName}}{{station.zoneName}}{{station.detailAddress}}<span @click="transToMap(station)" class="iconfont icon-map" style="margin-left: 10px;">&#xe6f8;</span></td>
                    <td >{{station.createTime|moment}}</td>
                    <td class="chargingStationIcons">
                        <i class="iconfont chargingPileNumIcon" style="color: #cccccc; font-size: 34px;">&#xe610;</i>{{station.pileNum}}
                        <i class="iconfont chargingNumIcon" style="color:#29a329; font-size: 30px; margin-left: 5px;margin-right: 5px;">&#xe60d;</i>{{station.chargingNum}}
                        <i class="iconfont brokenNumIcon" style="color: #e60000; font-size: 32px;">&#xe60f;</i>{{station.brokenNum}}
                    </td>
                    <td><span v-if="station.status==1">正常</span><span v-else>异常</span></td>
                    <td style="line-height: inherit;"><span @click="transToUpdate(station)" class="iconfont icon-h-update" style="margin-right: 5px;">&#xe688;</span><span class="iconfont icon-delete-selected" @click="deleteStation(station,index)" style="cursor: pointer;">&#xe603;</span></td>
                </tr>
                </tbody>
            </table>

        <div id="page" style="text-align:center;"></div>
    </div>
    <div v-else> 目前没有充电站哦！</div>



    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<script src="/js/moment.min.js"></script>
<script src="/js/pagejs/chargingStation.js"></script>
</html>
