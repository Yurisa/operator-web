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
                    provinceName:"",
                    cityName:"",
                    zoneName:"",
                    detailAddress:"",
                    createTime:"",//站点创建时间
                    pileNum:0,
                    chargingNum:0,
                    brokenNum:0,
                    totalElectricity:0,
                    totalPrice:0,
                    status:"使用中",//状态
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
            },
            deleteStation:function (station,index) {
                var self=this;
                $.ajax({
                    url:"/station/"+station.id,
                    type:"delete",
                   success:function (res) {
                       console.log(res);
                       self.page.list.splice(index,1);
                       console.log("delete stationId="+station.stationId+" success!")
                   }
            });
            }

        }
    }
)
// 提示框tips：
var  tipsDomArray=new Array(
    ".chargingPileNumIcon",
    ".chargingNumIcon",
    ".brokenNumIcon",
);
var tipContentArray= ["充电桩总数", "正在充电数", "充电桩损坏数"];
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
