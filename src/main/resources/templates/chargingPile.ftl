<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>充电桩管理</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    .info{padding-left: 1%}
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div id="content">
            <blockquote class="layui-elem-quote" v-if="signal == 'group'"><span id="groupname" class="info">{{groupMessage.name}}</span><span class="info">(编号:{{groupMessage.id}})</span><span class="info">|</span><span class="info">{{groupMessage.buildingsName}}</span><span class="info">|</span><span class="info">{{groupMessage.provinceName}} {{groupMessage.cityName}} {{groupMessage.zoneName}}</span><span class="info">容量:{{groupMessage.maxNum}}</span><span class="info">已装:{{groupMessage.usedNum}}</span><button class="layui-btn layui-btn-normal" style="margin-left: 3%"  @click="transToAdd">添加充电桩</button></blockquote>
            <blockquote class="layui-elem-quote" v-if="signal == 'station'"><span id="groupname" class="info">{{stationMessage.name}}</span><span class="info">(编号:{{stationMessage.id}})</span><span class="info">|</span><span class="info">{{stationMessage.buildingsName}}</span><span class="info">|</span><span class="info">{{stationMessage.provinceName}} {{stationMessage.cityName}} {{stationMessage.zoneName}}</span></blockquote>
            <div class="layui-form" v-if="page.list.length > 0">
                <table class="layui-table">
                    <colgroup>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>序列号</th>
                        <th>类型</th>
                        <th>厂家</th>
                        <th>车库</th>
                        <th>车位</th>
                        <th>电价</th>
                        <th>设备状态</th>
                        <th>充电状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(pile,index) in page.list">
                        <td>{{pile.identification}}</td>
                        <td>
                            <span v-if="pile.type == 1">快充</span>
                            <span v-else>慢充</span>
                        </td>
                        <td>{{pile.manufacturerName}}</td>
                        <td>{{pile.garageName}}</td>
                        <td>{{pile.parkingPotNo}}</td>
                        <td>{{pile.uniPrice}}</td>
                        <td>{{pile.deviceStatus}}</td>
                        <td>{{pile.chargingStatus}}</td>
                        <td><button class="layui-btn layui-btn-normal layui-btn-mini">查看</button><button class="layui-btn layui-btn-warm layui-btn-mini" @click="transToUpdate(pile)">修改</button><button class="layui-btn layui-btn-danger layui-btn-mini" @click="removePile(pile,index)">删除</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div v-else>
                暂无充电桩
            </div>
        </div>
        <div id="page" style="text-align:center;"></div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/chargingPile.js"></script>
</html>
