new Vue({
        el: "#app",
        data: {
            page: {
                pageNum: 1,
                pageSize: 7,
                pages: 0,
                list: [{
                    id:"",
                    name:"",
                    detailAddress:"",
                    pileNum:0,
                    chargingNum:0,
                    brokenNum:0,
                    totalElectricity:0,
                    totalPrice:0,
                    positionId:"",
                }],
                preage: 0,
                nextPage: 0,
                hasPreviousPage: false,
                hasNextPage: false
            },

        },
        mounted:function () {
            var self = this;
            self.fetch();
        },
        methods:{
            fetch: function () {
                var self = this;
                $.ajax({
                    url: "station",
                    data:{
                        pageNum:self.page.pageNum,
                        pageSize:self.page.pageSize,
                    },
                    success: function (res) {
                        var data = res.data;
                        console.log(data.list)
                        self.page = data;
                        //分页
                        console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize
                            , curr: data.pageNum
                            , jump: function (obj, first) {
                                //首次不执行
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetch();
                                }
                            }
                        });
                    }
                })

            },
            transToUpdate:function (station) {
                window.location.href = "/updateChargingStation/" + station.id;
            },
            transToMap:function (station) {
                window.location.href = "/chargingStation/" + station.id;
            }

        }
    }
)
// 提示框tips：
var  tipsDomArray=new Array(
    ".chargingPileNumIcon",
    ".chargingNumIcon",
    ".brokenNumIcon",
    ".findGroupIcon");
var tipContentArray= ["充电桩总数", "正在充电数", "充电桩损坏数", "充电群"];
for(var i=0;i<tipContentArray.length;i++){
    $(".chargingStationIcons .iconfont").mouseover(function(){
        var index=$(".chargingStationIcons .iconfont").index(this)%4;
        var self=this;
        var tipContent=tipContentArray[index];
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                closeBtn: 0,
                anim:5,
                tips: [3, '#009688'],
                shade: 0,
                type: 4,
                content: [tipContent,self] //数组第二项即吸附元素选择器或者DOM
            });
        });
    });
    $(tipsDomArray[i]).mouseout(function(){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.close(layer.index);
        });
    });
}

$(".chargingStationName .iconfont").mouseover(function(){
    var self = this;
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.tips('修改充电站', self, {
            tips: [2, '#009688']
        });
    });
});
$(".chargingStationName .iconfont,.chargingStationPlace .layui-icon ").mouseout(function(){
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.close(layer.index);
    });
});
$(".chargingStationPlace .layui-icon ").mouseover(function(){
    var self = this;
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.tips('查看地图', self, {
            tips: [3, '#009688']
        });
    });
});
