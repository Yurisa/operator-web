<!DOCTYPE html>
<html>
<head>
<#include "components/header.ftl">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=bbEWoApoNn5nU41WAdqBEwoNygbB4y6t"></script>
    <title>添加充电桩</title>
</head>
<style>
    body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;}
    .info{padding-left: 1%}
    .layui-input {
        width: 440px;
    }
    .layui-form-select .layui-edge {
        right: -130px;
    }
    .searchLocation {
        position: relative;
    }
    .search-icon{
        position: relative;
        left: 200px;
        font-size: 22px;
        color: #009688;
        top: 8px;
        cursor: pointer;
    }
    #chargingPilePrice div div input {
        width: 320px;
    }
    #chargingPilePrice i {
        right: 5px;
    }
    .priceBtn {
        position: relative;
        left: 45px;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
<#include "components/topnav.ftl">
<#include "components/leftmenu.ftl">
    <div class="layui-body" id="app">
        <!-- 内容主体区域 -->
        <div style="height: 100%;padding: 10px">
            <blockquote class="layui-elem-quote"><span id="groupname" class="info">{{groupMessage.name}}</span><span class="info">(编号:{{groupMessage.id}})</span><span class="info">|</span><span class="info">{{groupMessage.buildingsName}}</span><span class="info">|</span><span class="info">{{groupMessage.provinceName}} {{groupMessage.cityName}} {{groupMessage.zoneName}}</span><span class="info">容量:{{groupMessage.maxNum}}</span><span class="info">已装:{{groupMessage.usedNum}}</span><button class="layui-btn layui-btn-normal" style="margin-left: 49%"  onclick="window.location.href='../../addChargingPile'">添加充电桩</button></blockquote>
            <div style="display: inline-block; width: calc(100% - 22px); height: calc(100% -20px); padding: 10px; border: 1px solid #ddd; overflow-y: hidden; overflow-x: hidden">
                <form class="layui-form" style="width: 80%;margin-left: 25%;">
                    <div class="layui-form-item">
                        <label class="layui-form-label">桩序列号</label>
                        <div class="layui-input-block" style="width: 30%" >
                            <input type="text" name="identification"  lay-verify="required" placeholder="请输入桩序列号" autocomplete="off" class="layui-input" v-model="pileForm.identification"><span v-if="warn" style="color: #FF5722;margin-left: 4%;font-size: 13px;">该序列号已存在</span>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电类型</label>
                        <div class="layui-input-block" style="width: 30%">
                            <select name="chargingType" lay-verify="required" id="chargingtype">
                                <option value=""></option>
                                <option value="1">快充</option>
                                <option value="2">慢充</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">车库</label>
                        <div class="layui-input-block" style="width: 30%">
                            <select name="garage" lay-verify="required" id="garage">
                                <option value=""></option>
                                <option value="1">一号车库</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">车位</label>
                        <div class="layui-input-block" style="width: 30%">
                            <input v-model="pileForm.parkingPotNo" type="text" name="parkNo" required  lay-verify="required" placeholder="请输入车位" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电桩类型</label>
                        <div class="layui-input-block" style="width: 30%">
                            <select name="chargingPileType" lay-verify="required"  id="piletype">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">充电桩电价</label>
                        <div class="layui-input-block" style="width: 300px;display: inline-block;margin-left: 0" id="chargingPilePrice">
                            <select name="chargingPilePrice" lay-verify="required" id="chargingprice">
                                <option value=""></option>
                            </select>
                        </div>
                        <button class="layui-btn layui-btn-warm priceBtn" type="button">新增电价</button>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">地理位置</label>
                        <div class="layui-input-block" style="width: 21.6%;display: inline-block;margin-left: 0">
                            <input v-model="keyword" type="text" name="title" placeholder="位置搜索" autocomplete="off" class="layui-input">
                        </div>
                        <#--<button type="button" style="width: 8%;" class="layui-btn layui-btn-normal" @click="searchByStationName">搜索&nbsp<i class="layui-icon">&#xe615;</i></button>-->
                        <i class="layui-icon search-icon" @click="searchByStationName">&#xe615;</i>
                    </div>
                    <div id="maparea" style="width: 40%;height: 300px">
                        <div id="allmap" style="margin-top: 190px;margin-left: 31%"></div>
                    </div>
                    <div class="layui-form-item" style="margin-top: 35px">
                        <div class="layui-input-block">
                            <button class="layui-btn" type="button" @click="submitPile">提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
<script src="/js/pagejs/addChargingPile.js"></script>
<script>
    $('#chargingPilePrice').find('.layui-edge').css('right','-90px');
</script>
</html>
