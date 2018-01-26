new Vue({
        el: "#app",
        data: {
            areaId:-1,
            page: {
                pageNum: 1,
                pageSize: 8,
                pages: 0,
                list: [],
                prePage: 0,
                nextPage: 0,
                hasPreviousPage: false,
                hasNextPage: false
            },
            totalPileNum:0,
            totalChargingTimes:0,
            totalChargingPower:0,
            totalChargingHours:0,
            totalChargingPrice:0,
            provinceBreadcrumb: {
                id: '',
                name: '',
                stationNum: '',
                groupNum: '',
                pileNum: ''
            },
            cityBreadcrumb: {
                id: '',
                name: '',
                provinceName: '',
                stationNum: '',
                groupNum: '',
                pileNum: ''
            },
            zoneBreadcrumb: {
                id: '',
                name: '',
                provinceName: '',
                cityName: '',
                stationNum: '',
                groupNum: '',
                pileNum: ''
            },
            search: {
                name:'',
                startTime:'',
                endTime:'',
                // time:'2017/03/20 - 2018/01/01'
                time:""
            },
            node: {
                provinceId: '',
                cityId: '',
                zoneId: '',
                stationId: ''
            }
        },
    mounted:function () {
        var self = this;
        self.fetchTree();
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#timeInput'//指定元素
                ,range: true
                ,format: 'yyyy/MM/dd'
            });
        });

    },
    methods:{
        fetchTree: function () {
            var self = this;
            $.ajax({
                url: "station/areaAndStations",
                success: function (res) {
                    // console.log(res);
                    var data = res.data;
                    var pro = function () {
                        var dataLength = data.length;
                        //第一条数据
                        var arr = '{name:\''+ data[0].provinceName + '\',provinceId:\'' + data[0].provinceId + '\',children:[{name:\'' + data[0].cityName + '\',cityId:\'' + data[0].cityId + '\',children:[{name:\'' + data[0].zoneName + '\',zoneId:\'' + data[0].zoneId + '\'},]},]},';

                        for (var i = 0; i < dataLength; i++) {
                            for (var j = i + 1; j < dataLength; j++) {
                                if (data[i].provinceId == data[j].provinceId) {
                                    if (data[i].cityId == data[j].cityId) {
                                        if (data[i].zoneId == data[j].zoneId) {

                                        }else if (arr.indexOf(data[j].zoneId) == -1) {
                                            var strs2 = new Array();
                                            strs2 = arr.split(data[i].cityId + '\',children:[');
                                            arr = strs2[0] + data[i].cityId + '\',children:[' + '{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\'},' + strs2[1];
                                        }
                                    }else if (arr.indexOf(data[j].cityId) == -1) {
                                        var strs= new Array(); //定义一数组
                                        strs = arr.split(data[i].provinceId + '\',children:['); //字符分割
                                        arr = strs[0] + data[i].provinceId + '\',children:[' + '{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\'},]},' + strs[1];
                                    }
                                }else if (arr.indexOf(data[j].provinceId) == -1) {
                                    arr += '{name:\''+ data[j].provinceName + '\',provinceId:\'' + data[j].provinceId + '\',children:[{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\'},]},]},';
                                }

                            }
                        }
                        return arr;
                    };
                    layui.use('tree', function () {
                        var tree = layui.tree;
                        var str = '[' + pro() + ']';
                        tree({
                            elem: '#chargingPileGroup-tree',
                            nodes: eval(str),
                            click: function (node) {
                                $('#rightContent').show();
                                for (var obj in node) {
                                    if (obj == 'provinceId') {
                                        self.node= node;
                                        self.fetchProvinceStation(node);
                                        self.fetchProvinceBreadcrumb(node);
                                    }else if (obj == 'cityId') {
                                        self.node= node;
                                        self.fetchCityStation(node);
                                        self.fetchCityBreadcrumb(node);
                                    }else if (obj == 'zoneId') {
                                        self.node= node;
                                        self.fetchZoneStation(node);
                                        self.fetchZoneBreadcrumb(node);
                                    }
                                }

                            }
                        });
                    })

                }
            })

        },
        fetchProvinceStation: function (node) {
            var self = this;
            self.areaId= self.node.provinceId;
            $.ajax({
                url: "/statistic/station/province/" + self.node.provinceId,
                data:{
                    page:{
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize
                    }

                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data.page;
                    self.totalPileNum=data.allPileNum;
                    self.totalChargingTimes=data.allChargingNum,
                    self.totalChargingPower=data.allChargingElectricity,
                    self.totalChargingHours=data.allChargingHours,
                    self.totalChargingPrice=data.allChargingPrice,
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.page.total //数据总数，从服务端得到
                        , limit: data.page.pageSize,
                        curr: data.page.pageNum,
                        jump: function (obj, first) {
                            if(!first){
                                self.page.pageNum = obj.curr;
                                self.fetchProvinceStation();
                            }
                        }
                    });
                }

            })
        },
        fetchCityStation: function (node) {
            var self = this;
            self.areaId=self.node.cityId;
            $.ajax({
                url: "/statistic/station/city/" + self.node.cityId,
                data:{
                    page:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize
                    }

                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data.page;
                    self.totalPileNum=data.allPileNum;
                    self.totalChargingTimes=data.allChargingNum,
                    self.totalChargingPower=data.allChargingElectricity,
                    self.totalChargingHours=data.allChargingHours,
                    self.totalChargingPrice=data.allChargingPrice,
                    //分页
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.page.total //数据总数，从服务端得到
                        , limit: data.page.pageSize,
                        curr: data.page.pageNum,
                        jump: function (obj, first) {
                            if(!first){
                                self.page.pageNum = obj.curr;
                                self.fetchCityStation();
                            }
                        }
                    });
                }

            })
        },
        fetchZoneStation: function (node) {
            var self = this;
            self.areaId=self.node.zoneId;
            $.ajax({
                url: "/statistic/station/zone/" + self.node.zoneId,
                data:{
                    page:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize
                    }

                },
                success: function (res) {
                    var data = res.data;
                    self.page = data.page;
                    self.totalPileNum=data.allPileNum;
                    self.totalChargingTimes=data.allChargingNum,
                    self.totalChargingPower=data.allChargingElectricity,
                    self.totalChargingHours=data.allChargingHours,
                    self.totalChargingPrice=data.allChargingPrice,
                    //分页
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.page.total //数据总数，从服务端得到
                        , limit: data.page.pageSize,
                        curr: data.page.pageNum,
                        jump: function (obj, first) {
                            if(!first){
                                self.page.pageNum = obj.curr;
                                self.fetchZoneStation();
                            }
                        }
                    });
                }

            })
        },
        fetchProvinceBreadcrumb: function (node) {
            var self = this;
            $.ajax({
                url: "group/provinceMessage/" + node.provinceId,
                success: function (res) {
                    var data = res.data;
                    self.provinceBreadcrumb = data;
                    $('#nodeBreadcrumb').empty();
                    $('#nodeBreadcrumb').append('<a><cite>' + data.name + '：</cite></a>>');
                }

            })
        },
        fetchCityBreadcrumb: function (node) {
            var self = this;
            $.ajax({
                url: "group/cityMessage/" + node.cityId,
                success: function (res) {
                    var data = res.data;
                    self.cityBreadcrumb = data;
                    $('#nodeBreadcrumb').empty();
                    $('#nodeBreadcrumb').append('<a><cite>' + data.provinceName + '</cite></a>>');
                    $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.name + '：</cite></a>>');

                }

            })
        },
        fetchZoneBreadcrumb: function (node) {
            var self = this;
            $.ajax({
                url: "group/zoneMessage/" + node.zoneId,
                success: function (res) {
                    var data = res.data;
                    self.zoneBreadcrumb = data;
                    $('#nodeBreadcrumb').empty();
                    $('#nodeBreadcrumb').append('<a><cite>' + data.provinceName + '</cite></a>>');
                    $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.cityName + '</cite></a>>');
                    $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.name + '：</cite></a>>');
                }

            })
        },
        searchByNameOrTime:function () {
            layui.use('laydate', function(){
                var laydate = layui.laydate;
                //执行一个laydate实例
                laydate.render({
                    elem: '#timeInput'//指定元素
                    ,range: true
                    ,format: 'yyyy/MM/dd'
                });
            });
            var self=this;
            self.search.time=$("#timeInput").val();
            var time=self.search.time;
            var endTime=self.search.endTime;
            var startTime=self.search.startTime;
            if(self.areaId==-1){
                self.provinceId=-1;
                self.cityId=-1;
                self.zoneId=-1;
            }
            if(time!=""){
                var  strs= new Array();
                strs=time.split("-"); //字符分割
                startTime=strs[0];
                endTime=strs[1];
                var reg =/(\d{4})\/(\d{2})\/(\d{2})/;
                endTime = endTime.replace(reg,"$1-$2-$3");
                startTime=startTime.replace(reg,"$1-$2-$3");
                endTime=new Date(Date.parse(endTime));
                startTime=new Date(Date.parse(startTime));
                console.log("name:"+self.search.name+";time:"+time+";startTime:"+startTime+";endTime:"+endTime);
            }else {
                endTime=new Date();
                startTime=new Date("2016-01-01");
                console.log("notime----time:" + time + ";startTime:" + startTime + ";endTime:" + endTime);
            }
            // 请求数据：
            $.ajax({
                url:"/statistic/station/requirement",
                data:{
                    provinceId:self.provinceId,
                    cityId:self.cityId,
                    zoneId:self.zoneId,
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                    beginTime:startTime,
                    endTime:endTime,
                    stationName:self.search.name
                },
                success:function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data.page;
                    console.log("self.page.list.length==="+self.page.list.length);
                    if (self.page.list.length == 0) {
                        self.areaId = -1;
                        $("#page").hide();
                    } else {
                        if(self.areaId==-1){
                            self.areaId=1;
                        }
                        self.totalPileNum=data.allPileNum;
                        self.totalChargingTimes=data.allChargingNum,
                        self.totalChargingPower=data.allChargingElectricity,
                        self.totalChargingHours=data.allChargingHours,
                        self.totalChargingPrice=data.allChargingPrice,
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.page.total //数据总数，从服务端得到
                            , limit: data.page.pageSize,
                            curr: data.page.pageNum,
                            jump: function (obj, first) {
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetchProvinceStation();
                                }
                            }
                        });
                    }

                }
            });

        },
        // 查找充电站的所有充电桩信息：
        transToPileOfStation:function(stationId){
            window.location.href ="/chargingPileStatistics/"+stationId;

        }
    }
});