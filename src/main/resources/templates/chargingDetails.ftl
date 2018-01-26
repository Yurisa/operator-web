<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>设备管理</title>
</head>
<style>
    .layui-form-item .layui-input-inline {
        float: left;
        width: 190px;
        margin-right: 0;
    }
    .layui-form-label {
        padding: 9px 4px;
    }
    .layui-inline{
        padding-right: 0px;
    }
    /*th,td {*/
        /*overflow: hidden;*/
        /*white-space:nowrap;*/
        /*text-overflow:ellipsis;*/
    /*}*/

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
            <div style="display: inline-block; width: calc(75% - 48px); height: 100%; padding: 10px; border: 1px solid #ddd; overflow-x:auto;overflow-y:auto;">
                <div id="rightContent" >
                <#--breadcrumb:xx省xx市xx区-->
                    <span class="layui-breadcrumb" id="nodeBreadcrumb">
                    <#--节点路径-->
                    </span>
                    <span class="layui-breadcrumb" lay-separator="," id="nodeMessage">
                    <#--站群桩数量-->
                    </span>

                <#--根据时间限制、站名称查询-->
                    <div style="margin-top: 20px;" id="searchArea">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="text-align: left;width:30px;">编号</label>
                                    <div class="layui-input-inline" style="width:90px">
                                        <input type="text" v-model="search.pileId" name="pileId" autocomplete="on" class="layui-input" placeholder="编号">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="text-align: left;width:30px;">用户</label>
                                    <div class="layui-input-inline" style="width:90px">
                                        <input type="text" v-model="search.userName" name="userName" autocomplete="on" class="layui-input" placeholder="姓名">
                                    </div>
                                </div>

                                <div class="layui-inline">
                                    <label class="layui-form-label" style="text-align: left;width:30px;">状态</label>
                                    <div class="layui-input-inline" style="width:100px">
                                        <select  v-model="search.payStatus" name="payStatus" lay-verify="required" lay-filter="payStatus" id="payStatusSelect">
                                            <option value="请选择状态"></option>
                                            <option value="1">已付款</option>
                                            <option value="2">未付款</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline" >
                                    <label class="layui-form-label"  style="text-align: left;width:60px;">统计时间</label>
                                    <div class="layui-input-inline" style="width:160px">
                                        <input style="text-align: center;padding-left:0" id="timeInput" type="text" v-model="search.time" name="time" autocomplete="on" class="layui-input" placeholder="开始时间 — 结束时间">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <a style="color:white;background-color: #009688;" class="layui-btn layui-btn-primary" lay-event="detail" id="searchBtn" @click="searchByPileInfo" >查询</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div v-if="areaId!=-1">
                        <div v-if="page.list.length>0">
                        <#--站点的数据表格：-->
                            <table class="layui-table"  id="station-new" style="text-align: center">
                                <colgroup>

                                    <#--<col width="100">-->
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>桩编号</th>
                                    <th>名称</th>
                                    <th>车库</th>
                                    <th>车位</th>
                                    <th>充电用户</th>
                                    <th>充电车牌</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>充电时间(小时)</th>
                                    <th>充电电量(度)</th>
                                    <th>电费(元)</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(pile,index) in page.list">
                                    <td class="layui-elip">{{pile.pileIdentification}}</td>
                                    <td class="layui-elip">{{pile.pileName}}</td>
                                    <td class="layui-elip">{{pile.garageName}}</td>
                                    <td class="layui-elip">{{pile.parkPotNo}}</td>
                                    <td class="layui-elip">{{pile.chargingUserName}}</td>
                                    <td class="layui-elip">{{pile.vehicleNo}}</td>
                                    <td class="layui-elip">{{pile.chargingBeginTime|moment}}</td>
                                    <td class="layui-elip">{{pile.chargingEndTime|moment}}</td>
                                    <td class="layui-elip">{{pile.totalChargingHours}}</td>
                                    <td class="layui-elip">{{pile.totalChargingElectricity}}</td>
                                    <td class="layui-elip">{{pile.totalChargingPrice}}</td>
                                    <td class="layui-elip">{{pile.status}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div v-else>没有充电桩！</div>
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
<script src="/js/moment.min.js"></script>
<script src="/js/pagejs/chargingDetails.js"></script>
</html>