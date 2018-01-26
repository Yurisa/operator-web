new Vue({
        el: "#app",
        data: {
            areaId:-1,
            all: {
                allChargingElectricity: '',
                allChargingHours: '',
                allChargingNum: '',
                allChargingPrice: '',
            },
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
            stationBreadcrumb: {
                id: '',
                name: '',
                provinceName: '',
                cityName: '',
                zoneName: '',
                stationNum: '',
                groupNum: '',
                pileNum: ''
            },
            search: {
                name: '',
                startTime: '',
                endTime: '',
                time:''
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
            self.initFetchStationPile();
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
            initFetchStationPile:function () {
                var self=this;
                var split = location.pathname.split('/');
                if (split.length ==3) {
                    self.node.stationId = split[2];
                    self.areaId=1;
                    self.fetchStationGroup(self.node);//获取要显示的station的pile数据
                    // self.node.provinceId=??????????????????
                    // self.fetchProvinceBreadcrumb(self.node);//获取stationId对应的provinceId
                    // self.fetchStationBreadcrumb(self.node);
                    console.log("changePageToPile,stationId="+self.node.stationId);
                }
            },
            fetchTree: function () {
                var self = this;
                $.ajax({
                    url: "/station/areaAndStations",
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.areaList = data;
                        var pro = function () {
                            var dataLength = data.length;
                            var addStation = function (i) {     //添加充电站
                                var stationNum = data[i].pileStations.length;
                                var temp = '';
                                for (var n = 0; n < stationNum; n++) {
                                    temp +=  '{name:\'' + data[i].pileStations[n].name + '\',pileStationId:\'' + data[i].pileStations[n].id + '\'},';
                                }
                                return temp;
                            }

                            //第一条数据
                            var arr = '{name:\''+ data[0].provinceName + '\',provinceId:\'' + data[0].provinceId + '\',children:[{name:\'' + data[0].cityName + '\',cityId:\'' + data[0].cityId + '\',children:[{name:\'' + data[0].zoneName + '\',zoneId:\'' + data[0].zoneId + '\',children:[' + addStation(0) + ']},]},]},';

                            for (var i = 0; i < dataLength; i++) {
                                for (var j = i + 1; j < dataLength; j++) {
                                    if (data[i].provinceId == data[j].provinceId) {
                                        if (data[i].cityId == data[j].cityId) {
                                            if (data[i].zoneId == data[j].zoneId) {

                                            }else if (arr.indexOf(data[j].zoneId) == -1) {
                                                var strs2 = new Array();
                                                strs2 = arr.split(data[i].cityId + '\',children:[');
                                                arr = strs2[0] + data[i].cityId + '\',children:[' + '{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) +  ']},' + strs2[1];
                                            }
                                        }else if (arr.indexOf(data[j].cityId) == -1) {
                                            var strs= new Array(); //定义一数组
                                            strs = arr.split(data[i].provinceId + '\',children:['); //字符分割
                                            arr = strs[0] + data[i].provinceId + '\',children:[' + '{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) + ']},]},' + strs[1];
                                        }
                                    }else if (arr.indexOf(data[j].provinceId) == -1) {
                                        arr += '{name:\''+ data[j].provinceName + '\',provinceId:\'' + data[j].provinceId + '\',children:[{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) + ']},]},]},';
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
                                    console.log(node);
                                    $('#clickInfo').hide();
                                    $('#chargingPileGroup-existed').show();
                                    for (var obj in node) {
                                        if (obj == 'pileStationId') {
                                            self.node.stationId= node.pileStationId;
                                            self.fetchStationGroup(node);
                                            self.fetchStationBreadcrumb(node);
                                            // self.fetchStationId(node);
                                            $('#searchArea').hide();
                                            $('#addChargingPileGroup').show();
                                        }else if (obj == 'provinceId') {
                                            self.node= node;
                                            self.fetchProvinceStation(node);
                                            self.fetchProvinceBreadcrumb(node);
                                            $('#searchArea').show();
                                            $('#addChargingPileGroup').hide();
                                            self.search.provinceId = node.provinceId;
                                        }else if (obj == 'cityId') {
                                            self.node= node;
                                            self.fetchCityStation(node);
                                            self.fetchCityBreadcrumb(node);
                                            $('#searchArea').show();
                                            $('#addChargingPileGroup').hide();
                                            self.search.cityId = node.cityId;
                                        }else if (obj == 'zoneId') {
                                            self.node= node;
                                            self.fetchZoneStation(node);
                                            self.fetchZoneBreadcrumb(node);
                                            $('#searchArea').show();
                                            $('#addChargingPileGroup').hide();
                                            self.search.zoneId = node.zoneId;
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
                self.areaId=self.node.provinceId;
                $.ajax({
                    url: "/statistic/pile/province/" + self.node.provinceId,
                    data:{
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        console.log(res);
                        var data = res.data.page;
                        self.page = data;
                        self.all.allChargingElectricity = res.data.allChargingElectricity;
                        self.all.allChargingHours = res.data.allChargingHours;
                        self.all.allChargingNum = res.data.allChargingNum;
                        self.all.allChargingPrice = res.data.allChargingPrice;
                        //分页
                        // console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
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
                    url: "/statistic/pile/city/" + self.node.cityId,
                    data:{
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        console.log(res);
                        var data = res.data.page;
                        self.page = data;
                        self.all.allChargingElectricity = res.data.allChargingElectricity;
                        self.all.allChargingHours = res.data.allChargingHours;
                        self.all.allChargingNum = res.data.allChargingNum;
                        self.all.allChargingPrice = res.data.allChargingPrice;
                        //分页
                        // console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
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
                    url: "/statistic/pile/zone/" + self.node.zoneId,
                    data:{
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        // console.log(res);
                        var data = res.data.page;
                        self.page = data;
                        self.all.allChargingElectricity = res.data.allChargingElectricity;
                        self.all.allChargingHours = res.data.allChargingHours;
                        self.all.allChargingNum = res.data.allChargingNum;
                        self.all.allChargingPrice = res.data.allChargingPrice;
                        //分页
                        // console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
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
            fetchStationGroup: function (node) {
                var self = this;
                $.ajax({
                    url: "/statistic/pile/station/" + self.node.stationId,
                    data:{
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        // console.log(res);
                        var data = res.data.page;
                        self.page = data;
                        self.all.allChargingElectricity = res.data.allChargingElectricity;
                        self.all.allChargingHours = res.data.allChargingHours;
                        self.all.allChargingNum = res.data.allChargingNum;
                        self.all.allChargingPrice = res.data.allChargingPrice;
                        //分页
                        // console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
                            jump: function (obj, first) {
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetchStationGroup();
                                }
                            }
                        });
                    }

                })
            },
            fetchProvinceBreadcrumb: function (node) {
                var self = this;
                $.ajax({
                    url: "/group/provinceMessage/" + node.provinceId,
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.provinceBreadcrumb = data;
                        $('#nodeBreadcrumb').empty();
                        $('#nodeBreadcrumb').append('<a><cite>' + data.name + '：</cite></a>>');
                        $('#nodeMessage').empty();
                        $('#nodeMessage').append('<a href="#"><span>' + data.stationNum + '</span><span>个站</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.groupNum + '</span><span>个群</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.pileNum + '</span><span>个桩</span></a>');
                    }

                })
            },
            fetchCityBreadcrumb: function (node) {
                var self = this;
                $.ajax({
                    url: "/group/cityMessage/" + node.cityId,
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.cityBreadcrumb = data;
                        $('#nodeBreadcrumb').empty();
                        $('#nodeBreadcrumb').append('<a><cite>' + data.provinceName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.name + '：</cite></a>>');
                        $('#nodeMessage').empty();
                        $('#nodeMessage').append('<a href="#"><span>' + data.stationNum + '</span><span>个站</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.groupNum + '</span><span>个群</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.pileNum + '</span><span>个桩</span></a>');
                    }

                })
            },
            fetchZoneBreadcrumb: function (node) {
                var self = this;
                $.ajax({
                    url: "/group/zoneMessage/" + node.zoneId,
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.zoneBreadcrumb = data;
                        $('#nodeBreadcrumb').empty();
                        $('#nodeBreadcrumb').append('<a><cite>' + data.provinceName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.cityName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.name + '：</cite></a>>');
                        $('#nodeMessage').empty();
                        $('#nodeMessage').append('<a href="#"><span>' + data.stationNum + '</span><span>个站</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.groupNum + '</span><span>个群</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.pileNum + '</span><span>个桩</span></a>');
                    }

                })
            },
            fetchStationBreadcrumb: function (node) {
                var self = this;
                $.ajax({
                    url: "/group/stationMessage/" + node.pileStationId,
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.stationBreadcrumb = data;
                        $('#nodeBreadcrumb').empty();
                        $('#nodeBreadcrumb').append('<a><cite>' + data.provinceName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.cityName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.zoneName + '</cite></a>>');
                        $('#nodeBreadcrumb').append('<a><cite>/&nbsp&nbsp' + data.name + '：</cite></a>>');
                        $('#nodeMessage').empty();
                        $('#nodeMessage').append('<a href="#"><span>' + data.groupNum + '</span><span>个群</span></a>');
                        $('#nodeMessage').append('<a href="#"><span>' + data.pileNum + '</span><span>个桩</span></a>');
                    }

                })
            },

            searchByNameOrTime:function () {
                var self=this;
                self.search.time=$("#timeInput").val();
                var time=self.search.time;
                var endTime=self.search.endTime;
                var startTime=self.search.startTime;
                if(self.areaId==-1){
                    self.node.provinceId=-1;
                    self.node.cityId=-1;
                    self.node.zoneId=-1;
                    self.node.stationId=-1;
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
                    console.log("time:"+time+";startTime:"+startTime+";endTime:"+endTime);
                }else {
                    endTime=new Date();
                    startTime=new Date("2016-01-01");
                }
                console.log("areaId="+self.areaId);
                var provinceId=-1;
                var cityId=-1;
                var zoneId=-1;
                var stationId=-1;
                if(self.areaId==self.node.provinceId && self.search.name==""){
                    provinceId=self.areaId;
                }
                if(self.areaId==self.node.cityId && self.search.name==""){
                    cityId=self.areaId;
                }
                if(self.areaId==self.node.zoneId && self.search.name==""){
                    zoneId=self.areaId;
                }
                if(self.areaId==self.node.stationId && self.search.name==""){
                    stationId=self.areaId;
                }
                // 请求数据：
                $.ajax({
                    url:"/statistic/pile/requirement",
                    data:{
                        provinceId:provinceId,
                        cityId:cityId,
                        zoneId:zoneId,
                        // stationId:stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                        beginTime:startTime,
                        endTime:endTime,
                        pileName:self.search.name
                    },
                    success:function (res) {
                        console.log(res);
                        var data = res.data;
                        self.page = data.page;
                        console.log("self.page.list.length==="+self.page.list.length);
                        if(self.page.list.length==0){
                            areaId=-1;
                            $("#page").hide();
                        }else {
                            if(self.areaId==-1){
                                self.areaId=1;
                            }
                            self.all.allPileNum=data.allPileNum;
                            self.all.allChargingNum=data.allChargingNum,
                            self.all.allChargingElectricity=data.allChargingElectricity,
                            self.all.allChargingHours=data.allChargingHours,
                            self.all.allChargingPrice=data.allChargingPrice,
                                layui.laypage.render({
                                    elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                                    , count: data.page.total //数据总数，从服务端得到
                                    , limit: data.page.pageSize,
                                    curr: data.page.pageNum,
                                    jump: function (obj, first) {
                                        if(!first){
                                            self.page.pageNum = obj.curr;
                                            // if(self.areaId==self.node.provinceId){
                                            //     self.fetchProvinceStation();
                                            // }
                                            // if(self.areaId==self.node.cityId){
                                            //     self.fetchCityStation();
                                            // }
                                            // if(self.areaId==self.node.zoneId){
                                            //     self.fetchZoneStation();
                                            // }
                                            // if(self.areaId==self.node.stationId){
                                            //    self.fetchStationGroup();
                                            // }
                                        }
                                    }
                                });
                        }

                    }
                });

            },
        }
    }
)