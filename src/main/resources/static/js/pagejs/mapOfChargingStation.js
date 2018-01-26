var app = new Vue({
    el: "#app",
    data: {
        map: null,
        pointArray: [],
        opts: {
            width: 200,     // 信息窗口宽度
            height: 100,     // 信息窗口高度
            title: "充电桩详细信息："  // 信息窗口标题
        },
        StationMessage: {},
        startLocation: null,
        point: null,
        keyword: "",
    },
    mounted: function () {
        this.fetch();
        // this.initMap();
    },
    methods: {
        fetch: function () {
            var self = this;
            var split = location.pathname.split('/');
            var stationId;
            if (split.length == 3) {
                stationId = split[2];
            }
            $.ajax({
                type: 'get',
                url: "/station/" + stationId,
                success: function (res) {
                    console.log(res);
                    self.stationMessage = res.data;
                }
            }).then(() => {
                self.initMap();
            });

        },
        // 初始map：
        initMap: function () {
            var self = this;
            self.map = new BMap.Map("allmap");          // 创建地图实例
            var point = new BMap.Point(self.stationMessage.gpsLng, self.stationMessage.gpsLat);  // 创建点坐标
            self.point = point;
            self.map.centerAndZoom(point, 20);
            self.map.enableScrollWheelZoom(true);
            self.map.addControl(new BMap.NavigationControl());
            self.map.addControl(new BMap.GeolocationControl());
            var geolocation = new BMap.Geolocation();
            geolocation.getCurrentPosition(function (r) {
                if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                    var mk = new BMap.Marker(r.point);
                    self.map.addOverlay(mk);
                    self.startLocation = new BMap.Point(r.point.lng, r.point.lat);
                }
                else {
                    console.log('failed' + this.getStatus());
                }
            }, {enableHighAccuracy: true})
            var signLogo = new BMap.Icon("/sign.png", new BMap.Size(20, 20));
            signLogo.setImageSize(new BMap.Size(20, 20));
            var marker = new BMap.Marker(point, {icon: signLogo});
            var label = new BMap.Label("充电站位置", {offset: new BMap.Size(20, -10)});
            marker.setLabel(label);
            self.map.addOverlay(marker);
            marker.addEventListener("click", function () {
                self.map.openInfoWindow(new BMap.InfoWindow("名字:" + self.stationMessage.name + "<br>详细地址" + self.stationMessage.detailAddress + "<br>详细信息" + self.stationMessage.description + "<br><button class=\"layui-btn layui-btn-normal layui-btn-mini\" id='startnavigation' onclick='navigation()'>到这去</button>", self.opts), new BMap.Point(self.stationMessage.gpsLng, self.stationMessage.gpsLat)); //开启信息窗口
            });
            self.map.panTo(point);// map.panTo方法，把点击的点设置为地图中心点
            var walking = new BMap.WalkingRoute(self.map, {
                renderOptions: {
                    map: self.map,
                    panel: "r-result",
                    autoViewport: true
                }
            });

            self.map.addEventListener("click", function (e) {
                self.pointArray.push(e);
                console.log(self.pointArray.length);
                self.startLocation = new BMap.Point(e.point.lng, e.point.lat);
                var signLogo = new BMap.Icon("/location.png", new BMap.Size(20, 20));
                signLogo.setImageSize(new BMap.Size(20, 20));
                var marker = new BMap.Marker(e.point, {icon: signLogo});
                var label = new BMap.Label("起点", {offset: new BMap.Size(20, -10)});
                marker.setLabel(label);
                self.map.addOverlay(marker);
                self.map.panTo(self.startLocation);// map.panTo方法，把点击的点设置为地图中心点
                if (self.pointArray.length > 1) {
                    console.log("执行删除啦");
                    self.deletePoint();
                }
            });

        },
        searchByStationName: function () {
            var self = this;
            var localSearch = new BMap.LocalSearch(self.map);
            localSearch.enableAutoViewport(); //允许自动调节窗体大小
            var locationLogo = new BMap.Icon("/location.png", new BMap.Size(20, 20));
            locationLogo.setImageSize(new BMap.Size(20, 20));
            var keyword = self.keyword;
            localSearch.setSearchCompleteCallback(searchResult => {
                var poi = searchResult.getPoi(0);
                self.pointArray.push(poi);
                self.deletePoint();
                self.map.centerAndZoom(poi.point, 19);
                self.startLocation = new BMap.Point(poi.point.lng, poi.point.lat);
                var marker = new BMap.Marker(poi.point, {icon: locationLogo});
                var label = new BMap.Label("起点", {offset: new BMap.Size(20, -10)});
                marker.setLabel(label);
                self.map.addOverlay(marker);
            });
            localSearch.search(keyword);
        },
        deletePoint: function () {
            var self = this;
            var allOverlay = self.map.getOverlays();
            for (var i = 0; i < allOverlay.length - 1; i++) {
                if (allOverlay[i].toString() == "[object Marker]") {
                    if (allOverlay[i].getLabel() != null) {
                        if (allOverlay[i].getLabel().content == "起点") {
                            self.map.removeOverlay(allOverlay[i]);
                            return false;
                        }
                    }
                }
            }
        }
    }
})
function navigation() {
    var walking = new BMap.WalkingRoute(app.map, {renderOptions: {map: app.map, panel: "r-result", autoViewport: true}});
    walking.search(app.startLocation, app.point);
}
