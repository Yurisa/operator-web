<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=bbEWoApoNn5nU41WAdqBEwoNygbB4y6t"></script>
    <title>设备管理</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    .layui-input {
        width: calc(100% - 15px);
    }
    .layui-form-select .layui-edge {
        right: 25px;
    }
    .searchLocation {
        position: relative;
    }
    .search-icon{
        position: absolute;
        left: 90%;
        font-size: 22px;
        color: #009688;
        top: 8px;
        cursor: pointer;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div style="height: 100%;padding: 10px">
            <div style="display: inline-block; width: calc(100% - 22px); height: calc(100% -20px); padding: 10px; border: 1px solid #ddd; overflow: auto; overflow-x: hidden">
                <form class="layui-form" style="width: 50%;margin: 0 auto;">
                    <div style="text-align: center; width: 100%; height: 50px; line-height: 50px; font-size: 24px">新建充电群</div>
                    <div class="layui-form-item">
                        <div class="layui-inline" style="text-align: center; width: calc((100% - 68px) / 2); min-width: 230px; height: 50px; line-height: 50px; border: 1px solid #ddd; margin: 9px 15px">
                            <span>充电站：</span><span v-text="station.name"></span>
                        </div>
                        <div class="layui-inline" style="text-align: center; width: calc((100% - 68px) / 2); min-width: 230px;  height: 50px; line-height: 50px; border: 1px solid #ddd; margin: 9px 15px">
                            <span>群类型：</span><span v-text="groupType.name + '(' + groupType.maxCount + ')' "></span>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">权限</label>
                        <div class="layui-input-block">
                            <select name="chargingType" lay-filter="price" id="chargingType">
                                <option value=""></option>
                                <option value="1">开放</option>
                                <option value="2">受限</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电群名</label>
                        <div class="layui-input-block">
                            <input v-model="groupForm.name" type="text" id="groupName" name="chargingPileGroupName" lay-verify="title" autocomplete="off" placeholder="请输入充电群名" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label searchLocation">地理位置</label>
                        <div class="layui-input-block">
                            <input type="text" v-model="keyword" name="chargingPileGroupLocation" lay-verify="title" autocomplete="off" placeholder="请选择地理位置" class="layui-input">
                            <#--<i @click="searchByGroupName" class="layui-icon search-icon">&#xe615;</i>-->
                            <i class="layui-icon search-icon" @click="searchByGroupName">&#xe615;</i>
                        </div>

                        <#--<div class="layui-input-block" style="height:250px; border: 1px solid #ddd;margin-top: 15px; margin-right:15px">-->
                        <#--&lt;#&ndash;地图&ndash;&gt;-->
                        <#--</div>-->
                        <div class="layui-input-block" style="min-height:250px;height: auto; border: 1px solid #ddd;margin-bottom:15px;margin-top: 15px; position:relative;"  id="mapArea">
                        <#--地图-->
                            <div id="allmap" style="position: relative;top:0;margin-top:0;left:0;width:100%"></div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电价格</label>
                        <div class="layui-input-block">
                            <select name="price" id="price">
                                <#--<option v-for="option in price.data" b-bind:value="option.id">{{option.name}}</option>-->

                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn" @click="submitGroup" id="submitBtn">提交</button>
                            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
                            <#--<button class="layui-btn layui-btn-primary"  id="cancelBtn">取消</button>-->

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
<script src="/js/pagejs/addChargingPileGroup.js"></script>

</html>