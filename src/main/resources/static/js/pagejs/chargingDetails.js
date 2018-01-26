new Vue({
    el: "#app",
    data: {
        areaId: -1,
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
        pileList: [{
            id: '',
            name: "充电桩名称",
            garage: "",
            parkingLot: "",
            userName: "",
            licensePlate: "",
            startTime: "",
            endTime: "",
            chargingTimes: 0,
            chargingHours: 0,
            chargingPrice: 0,
            payStatus: "未支付"
        }],
        search: {
            pileId: '',
            userName: "",
            startTime: '',
            endTime: '',
            time: '',
            payStatus: "未付款"
        },
        node: {
            provinceId: '',
            cityId: '',
            zoneId: '',
            stationId: ''
        }
    },
    mounted: function () {
        var self = this;
        self.fetchTree();
        layui.use('laydate', function () {
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#timeInput'//指定元素
                , range: true
                , format: 'yyyy/MM/dd'
            });
        });
        layui.use('form', function () {
            layui.form.render(); //更新全部
            layui.form.render('select'); //刷新select选择框渲染
        });

    },
    methods: {
        fetchTree: function () {
            var self = this;
            $.ajax({
                url: "station/areaAndStations",
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
                                temp += '{name:\'' + data[i].pileStations[n].name + '\',pileStationId:\'' + data[i].pileStations[n].id + '\'},';
                            }
                            return temp;
                        }

                        //第一条数据
                        var arr = '{name:\'' + data[0].provinceName + '\',provinceId:\'' + data[0].provinceId + '\',children:[{name:\'' + data[0].cityName + '\',cityId:\'' + data[0].cityId + '\',children:[{name:\'' + data[0].zoneName + '\',zoneId:\'' + data[0].zoneId + '\',children:[' + addStation(0) + ']},]},]},';

                        for (var i = 0; i < dataLength; i++) {
                            for (var j = i + 1; j < dataLength; j++) {
                                if (data[i].provinceId == data[j].provinceId) {
                                    if (data[i].cityId == data[j].cityId) {
                                        if (data[i].zoneId == data[j].zoneId) {

                                        } else if (arr.indexOf(data[j].zoneId) == -1) {
                                            var strs2 = new Array();
                                            strs2 = arr.split(data[i].cityId + '\',children:[');
                                            arr = strs2[0] + data[i].cityId + '\',children:[' + '{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) + ']},' + strs2[1];
                                        }
                                    } else if (arr.indexOf(data[j].cityId) == -1) {
                                        var strs = new Array(); //定义一数组
                                        strs = arr.split(data[i].provinceId + '\',children:['); //字符分割
                                        arr = strs[0] + data[i].provinceId + '\',children:[' + '{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) + ']},]},' + strs[1];
                                    }
                                } else if (arr.indexOf(data[j].provinceId) == -1) {
                                    arr += '{name:\'' + data[j].provinceName + '\',provinceId:\'' + data[j].provinceId + '\',children:[{name:\'' + data[j].cityName + '\',cityId:\'' + data[j].cityId + '\',children:[{name:\'' + data[j].zoneName + '\',zoneId:\'' + data[j].zoneId + '\',children:[' + addStation(j) + ']},]},]},';
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
                                for (var obj in node) {
                                    if (obj == 'pileStationId') {
                                        self.node = node;
                                        self.fetchStationGroup(node);
                                        self.fetchStationBreadcrumb(node);
                                        self.groupInfo.stationId = node.pileStationId;
                                    } else if (obj == 'provinceId') {
                                        self.node = node;
                                        self.fetchProvinceStation(node);
                                        self.fetchProvinceBreadcrumb(node);
                                    } else if (obj == 'cityId') {
                                        self.node = node;
                                        self.fetchCityStation(node);
                                        self.fetchCityBreadcrumb(node);
                                    } else if (obj == 'zoneId') {
                                        self.node = node;
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
            self.areaId = self.node.provinceId;
            $.ajax({
                url: "/statistic/charging/province/" + self.node.provinceId,
                data: {
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data;
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.total //数据总数，从服务端得到
                        , limit: data.pageSize,
                        curr: data.pageNum,
                        jump: function (obj, first) {
                            if (!first) {
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
            self.areaId = self.node.cityId;
            $.ajax({
                url: "/statistic/charging/city/" + self.node.cityId,
                data: {
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data;
                    //分页
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.total //数据总数，从服务端得到
                        , limit: data.pageSize,
                        curr: data.pageNum,
                        jump: function (obj, first) {
                            if (!first) {
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
            self.areaId = node.zoneId;
            $.ajax({
                url: "/statistic/charging/zone/" + self.node.zoneId,
                data: {
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data;
                    //分页
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.total //数据总数，从服务端得到
                        , limit: data.pageSize,
                        curr: data.pageNum,
                        jump: function (obj, first) {
                            if (!first) {
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
            self.areaId = self.node.stationId;
            $.ajax({
                url: "/statistic/charging/station/" + self.node.pileStationId,
                data: {
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data;
                    //分页
                    layui.laypage.render({
                        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                        , count: data.total //数据总数，从服务端得到
                        , limit: data.pageSize,
                        curr: data.pageNum,
                        jump: function (obj, first) {
                            if (!first) {
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
                url: "group/provinceMessage/" + node.provinceId,
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
                url: "group/cityMessage/" + node.cityId,
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
                url: "group/zoneMessage/" + node.zoneId,
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
                url: "group/stationMessage/" + node.pileStationId,
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
        searchByPileInfo: function () {
            var self = this;
            self.search.payStatus = $('#payStatusSelect').val();
            self.search.time=$("#timeInput").val();
            console.log("areaId:"+self.areaId);
            var time = self.search.time;
            var endTime = self.search.endTime;
            var startTime = self.search.startTime;
            if (self.areaId == -1) {
                self.provinceId = -1;
                self.cityId = -1;
                self.zoneId = -1;
                self.areaId=1;
            }
            if (time != "") {
                var strs = new Array();
                strs = time.split("-"); //字符分割
                startTime = strs[0];
                endTime = strs[1];
                var reg = /(\d{4})\/(\d{2})\/(\d{2})/;
                endTime = endTime.replace(reg, "$1-$2-$3");
                startTime = startTime.replace(reg, "$1-$2-$3");
                endTime = new Date(Date.parse(endTime));
                startTime = new Date(Date.parse(startTime));
                console.log("time:" + time + ";startTime:" + startTime + ";endTime:" + endTime);
            } else {
                endTime = new Date();
                startTime = new Date("2016-01-01");
                console.log("notime----time:" + time + ";startTime:" + startTime + ";endTime:" + endTime);
            }

            // 请求数据：
            $.ajax({
                url: "/statistic/charging/requirement",
                data: {
                    provinceId: self.provinceId,
                    cityId: self.cityId,
                    zoneId: self.zoneId,
                    pageNum: self.page.pageNum,
                    pageSize: self.page.pageSize,
                    beginTime: startTime,
                    endTime: endTime,
                    status: self.search.payStatus,
                    chargingUserName: self.search.userName,
                    pileIdentification: self.search.pileId
                },
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    self.page = data;
                    if (self.page.list.length == 0) {
                        areaId = -1;
                        $("#page").hide();
                    } else {
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
                            jump: function (obj, first) {
                                if (!first) {
                                    self.page.pageNum = obj.curr;
                                    self.fetchProvinceStation();
                                }
                            }
                        });
                    }

                }
            });
        }
    }

})

    // $(function () {
    //     $("td").on("mouseenter",function() {
    //         if (this.offsetWidth < this.scrollWidth) {
    //             var that = this;
    //             var text = $(this).text();
    //             layer.tips(text, that,{
    //                 tips: 1,
    //                 time: 2000
    //             });
    //         }
    //     });
    // })
