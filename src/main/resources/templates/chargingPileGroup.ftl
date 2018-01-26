<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <title>设备管理</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    .layui-form-label {
        width: 42px;
    }
    .layui-input-block {
        margin-left: 75px;
    }
    .layui-form-item .layui-input-inline {
        width: 140px;
    }
    .layui-input-block {
        width: 140px;
    }
    .layui-layer-content {
        margin: 20px;
        margin-top: 10px;
    }
    .layui-breadcrumb a cite, .layui-breadcrumb a span {
        color: #5d656a;
        font-style: italic;
        font-size: 16px;
        font-weight: bold;
    }
    #clickInfo {
        height: 400px;
        text-align: center;
        line-height: 400px;
        font-size: 34px;
        color: #afafaf;
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
            <div style="display: inline-block; width: calc(75% - 48px); height: 100%; padding: 10px; border: 1px solid #ddd;overflow-y: hidden; overflow-x: hidden">
                <span class="layui-breadcrumb" id="nodeBreadcrumb">
                    <#--节点路径-->
                </span>
                <span class="layui-breadcrumb" lay-separator="," id="nodeMessage">
                  <#--站群桩数量-->
                </span>
                <button id="addChargingPileGroup" style="display: none" data-method="offset" data-type="auto" class="layui-btn layui-btn-normal">新建充电群</button>
                <div id="groupresourceContent" style="display: none;">
                    <div style="text-align: center; width: 100%; height: 50px; line-height: 50px; font-size: 24px;" >可用群资源</div>
                    <table class="layui-table" id="chargingPileGroup-new">
                        <colgroup>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>群类型</th>
                            <th>容量</th>
                            <th>总量</th>
                            <th>可用量</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="buildGroup in groupresource">
                            <td>{{buildGroup.groupType}}</td>
                            <td>{{buildGroup.maxNum}}</td>
                            <td>{{buildGroup.allocNum}}</td>
                            <td>{{buildGroup.residueNum}}</td>
                            <td><button class="layui-btn layui-btn-mini" id="addBtn" :groupTypeId="buildGroup.groupTypeId">新建</button></td>
                        </tr>

                        </tbody>
                    </table>
                </div>

                <div style="margin-top: 20px;display: none" id="searchArea">
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">充电站</label>
                                <div class="layui-input-inline">
                                    <input type="tel" v-model="search.stationName" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入充电站">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">充电群</label>
                                <div class="layui-input-inline">
                                    <input type="text" v-model="search.groupName" name="email" lay-verify="email" autocomplete="off" class="layui-input" placeholder="请输入群">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">状态</label>
                                <div class="layui-input-block">
                                    <select name="status" lay-filter="status" id="status">
                                        <option disabled value="0">请选择状态</option>
                                        <option value="1">正常</option>
                                        <option value="-1">异常</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <a class="layui-btn layui-btn-primary" lay-event="detail" id="searchBtn" @click="searchStation">提交</a>
                            </div>
                        </div>
                    </form>
                </div>


                <div id="clickInfo">请点击右侧节点</div>
                <table class="layui-table" id="chargingPileGroup-existed" style="display: none;">
                <#--<table class="layui-table" id="chargingPileGroup-existed">-->
                    <colgroup>

                    </colgroup>
                    <thead>
                    <tr>
                        <th>群名称</th>
                        <th>类型</th>
                        <th>桩数量</th>
                        <th>电价</th>
                        <th>充电站</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="stationTable">
                    <tr v-for="(group,index) in page.list">
                        <th id="stationName">{{group.name}}</th>
                        <th>{{group.groupType}}</th>
                        <th>{{group.pileNum}}</th>
                        <th>{{group.chargingPrice}}</th>
                        <th id="stationName">{{group.stationName}}</th>
                        <th id="stationStatus">
                            <span v-if="group.status == 1">正常</span>
                            <span v-else>异常</span>
                        </th>
                        <th>
                            <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail" id="checkBtn" :groupId="group.id">查看</a>
                            <a class="layui-btn layui-btn-mini" lay-event="edit" id="editBtn" :groupId="group.id">修改</a>
                        </th>
                    </tr>
                    </tbody>
                </table>
                <div id="page" style="text-align:center;"></div>
            </div>
        </div>
    </div>
<#include "components/footer.ftl">
</div>
</body>
<#include "components/script.ftl">
<#--some js-->
<script src="/js/pagejs/chargingPileGroup.js"></script>

</html>