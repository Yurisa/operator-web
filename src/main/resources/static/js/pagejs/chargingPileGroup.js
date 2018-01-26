var app = new Vue({
        el: "#app",
        data: {
            areaList: [{
                provinceId: '330000',
                provinceName: '浙江省',
                cityId: '330100',
                cityName: '杭州市',
                zoneId: '330106',
                zoneName: '西湖区',
                pileStations:[{
                    id: 1,
                    name: ''
                }]
            }],
            groupresource: [{
                groupTypeId: ''
                ,groupType: ''
                ,maxNum: ''
                ,allocNum: ''
                ,residueNum: ''
            }],
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
                pileStation: '',
                pileGroup: '',
                stationStatus: ''
            },
            groupInfo: {
                stationId: '',
                groupTypeId: ''
            },
            search: {
                zoneId: '-1',
                cityId: '-1',
                provinceId: '-1',
                stationName: '',
                groupName: '',
                status: '',
                pageNum: 1,
                pageSize: 8
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
            self.fetchGroupInfo();
        },
        methods:{
            fetchTree: function () {
                var self = this;
                $.ajax({
                    url: "station/areaAndStations",
                    success: function (res) {
                        console.log(res);
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
                                    // console.log(node);
                                    $('#clickInfo').hide();
                                    $('#chargingPileGroup-existed').show();
                                    for (var obj in node) {
                                        if (obj == 'pileStationId') {
                                            self.node= node;
                                            self.fetchStationGroup(node);
                                            self.fetchStationBreadcrumb(node);
                                            // self.fetchStationId(node);
                                            self.groupInfo.stationId = node.pileStationId;
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
            fetchGroupInfo: function () {
                var self = this;
                $.ajax({
                    url: "group/resource",
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        // console.log(data);
                        self.groupresource = data;
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            var form = layui.form;
                            form.render();
                            var active = function() {
                                layer.open({
                                    title: '新建充电群'
                                    // ,offset: type
                                    // ,id: 'layerDemo'+type
                                    ,type: 1
                                    ,area: '700px;'
                                    ,resize: false
                                    ,content:$("#groupresourceContent").html()
                                    ,yes: function(){
                                        layer.closeAll();
                                    }
                                });
                            }
                            $('#addChargingPileGroup').on('click',function () {
                                active();
                                // self.addChargingPileGroup();
                            })
                        });

                    }
                })

            },
            fetchProvinceStation: function (node) {
                var self = this;
                // self.node.provinceId = node.provinceId;
                $.ajax({
                    url: "group/province/" + self.node.provinceId,
                    data:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                        // pageSize: 1
                    },
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        self.page = data;
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
                $.ajax({
                    url: "group/city/" + self.node.cityId,
                    data:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        self.page = data;
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
                $.ajax({
                    url: "group/zone/" + self.node.zoneId,
                    data:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.page = data;
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
                    url: "group/station/" + self.node.pileStationId,
                    data:{
                        stationId: self.page.list.stationId,
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.page = data;
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
                    url: "group/provinceMessage/" + self.node.provinceId,
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        self.provinceBreadcrumb = data;
                        self.search.provinceId = data.id;
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
                    url: "group/cityMessage/" + self.node.cityId,
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        self.cityBreadcrumb = data;
                        // self.searchStation.data.cityId = node.cityId;
                        self.search.provinceId = data.provinceId;
                        self.search.cityId = data.id;
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
                    url: "group/zoneMessage/" + self.node.zoneId,
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        self.zoneBreadcrumb = data;
                        self.search.provinceId = data.provinceId;
                        self.search.cityId = data.cityId;
                        self.search.zoneId = data.id;
                        // self.searchStation.data.zoneId = node.zoneId;
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
                    url: "group/stationMessage/" + self.node.pileStationId,
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.stationBreadcrumb = data;
                        // self.searchStation.data.stationId = node.pileStationId;
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
            searchStation: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: '/group/requireMent',
                    data: {
                        zoneId: self.search.zoneId,
                        cityId: self.search.cityId,
                        provinceId: self.search.provinceId,
                        stationName: self.search.stationName,
                        groupName: self.search.groupName,
                        status: $('#status').val(),
                        pageNum: self.page.pageNum,
                        pageSize: self.page.pageSize,
                    },
                    success: function (res) {
                        // console.log(res);
                        var data = res.data;
                        self.page = data;
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
                                    self.searchStation();
                                }
                            }
                        });
                    }
                })
            }

        }
})

$(document).on('click', '#addBtn', function() {
    window.location.href = '/addChargingPileGroup/station/' + app.groupInfo.stationId + '/groupType/' + $(this).attr('groupTypeId');
});
$(document).on('click', '#checkBtn', function() {
    // console.log(app.page.list);
    window.location.href = '/chargingPile/group/' + $(this).attr('groupId');
});
$(document).on('click', '#editBtn', function() {
    console.log(app.page.list);
    window.location.href = '/updateChargingPileGroup/' + $(this).attr('groupId');
});



