new Vue({
        el: "#app",
        data: {
          map:null,
          keyword:"",
          groupId:-1,
          groupMessage:{},
          pointArray:[],
          products:[],
            price:[],
            identificationList:[],
            warn:false,
            pileForm:{
                identification:"",
                type:-1,
                productId:-1,
                garageId:-1,
                parkingPotNo:"",
                groupId:-1,
                chargingPriceId:-1,
                gpsLat:null,
                gpsLng:null
            }
        },
        watch:{
            "pileForm.identification":function () {
                console.log(this.pileForm.identification)
                this.checkIdentification();
            },
            "value":function () {
                console.log(this.value)
            }
        },
        mounted:function () {
            var self = this;
            var split = location.pathname.split('/');
            if (split.length > 2) {
                self.groupId = split.pop();
                self.getGroupMessage(self.groupId);
            }
            self.getAllPileProducts();
            self.getAllChargingPrice();
            self.fetchAllPileIdentification();
            self.initMap();
        }
        ,
        methods:{
            initMap:function () {
                var self = this;
                self.map = new BMap.Map("allmap");          // 创建地图实例
                var point = new BMap.Point(120.19, 30.26);  // 创建点坐标
                self.map.centerAndZoom(point, 20);
                self.map.enableScrollWheelZoom(true);
                self.map.addControl(new BMap.NavigationControl());
                self.map.addControl(new BMap.GeolocationControl());

                //定位
                // var geolocation = new BMap.Geolocation();
                navigator.geolocation.getCurrentPosition(function(position){
                    //得到html5定位结果
                    var x = position.coords.longitude;
                    var y = position.coords.latitude;

                    //由于html5定位的结果是国际标准gps，所以from=1，to=5
                    //下面的代码并非实际是这样，这里只是提供一个思路
                    BMap.convgps(x, y, 1, 5, function (convRst) {
                        var point = new BMap.Point(convRst.x, convRst.y);

                        //这个部分和上面的代码是一样的
                        var marker = new BMap.Marker(point);
                        marker.setLabel(new BMap.Label(""));
                        self.map.addOverlay(marker);
                        map.panTo(point);
                    })
                });
                self.map.addEventListener("click",function(e){
                    if (self.pointArray.length > 0){
                        self.remove_overlay();
                    }
                    self.pointArray.push(e);
                    self.pileForm.gpsLat = e.point.lat;
                    self.pileForm.gpsLng = e.point.lng;
                    var signLogo = new BMap.Icon("/sign.png",new BMap.Size(20,20));
                    signLogo.setImageSize(new BMap.Size(20,20));
                    var marker = new BMap.Marker(e.point,{icon:signLogo});
                    var label = new BMap.Label("充电桩位置",{offset:new BMap.Size(20,-10)});
                    marker.setLabel(label);
                    self.map.addOverlay(marker);
                    self.map.panTo(new BMap.Point(e.point.lng,e.point.lat));// map.panTo方法，把点击的点设置为地图中心点
                });
            },
            searchByStationName:function () {
                var self = this;
                var localSearch = new BMap.LocalSearch(self.map);
                localSearch.enableAutoViewport(); //允许自动调节窗体大小
                var locationLogo = new BMap.Icon("/location.png",new BMap.Size(20,20));
                locationLogo.setImageSize(new BMap.Size(20,20));
                var keyword = self.keyword;
                localSearch.setSearchCompleteCallback(searchResult=> {
                    var poi = searchResult.getPoi(0);
                    self.map.centerAndZoom(poi.point, 19);
                    var marker = new BMap.Marker(poi.point,{icon:locationLogo});
                    marker.setLabel(new BMap.Label(""));
                    self.map.addOverlay(marker);
                });
                localSearch.search(keyword);
            },
            remove_overlay:function(){
                this.map.clearOverlays();
            },
            getGroupMessage:function(groupId){
                var self = this;
                $.ajax ({
                    type:'get',
                    url:"/group/message/" + groupId,
                    success:function (res) {
                        console.log(res);
                        self.groupMessage = res.data;
                        layui.use('form', function(){
                            layui.form.render(); //更新全部
                        })
                    }
                }) ;
            },
            getAllPileProducts:function () {
                var self = this;
                $.ajax({
                    type:'get',
                    url:"/product",
                    success:function (res) {
                        self.products = res.data;
                        for(let p of self.products){
                            $("#piletype").append("<option value="+p.id+">"+p.name+"</option>");
                        }
                        layui.use('form', function(){
                            layui.form.render(); //更新全部
                            // layui.form.render('select'); //刷新select选择框渲染
                        })
                    }
                })
            },
            getAllChargingPrice:function () {
                var self = this;
                $.ajax({
                    type:'get',
                    url:"/price",
                    success:function (res) {
                        self.price = res.data;
                        for(let p of self.price){
                            $("#chargingprice").append("<option value="+p.id+">"+p.name+":"+p.uniPrice+"</option>");
                        }
                        layui.use('form', function(){
                            layui.form.render(); //更新全部
                            // layui.form.render('select'); //刷新select选择框渲染
                        })
                    }
                })
            },
            submitPile:function () {
                var self = this;
                self.pileForm.type = $("#chargingtype").val();
                self.pileForm.groupId = self.groupId;
                self.pileForm.productId = $("#piletype").val();
                self.pileForm.garageId = $("#garage").val();
                self.pileForm.chargingPriceId = $("#chargingprice").val();
                console.log(self.pileForm);
                var pile = self.pileForm;
                if (self.warn === false) {
                    if (pile.identification === "" || pile.productId === -1 || pile.chargingPriceId === -1 || pile.garageId === -1 || pile.type === -1 || pile.groupId === -1 || pile.parkingPotNo === "" || pile.gpsLng === null || pile.gpsLat === null) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('请填写完整信息');
                        });
                    } else {
                        $.post("/pile", self.pileForm, res => {
                            console.log(res)
                            window.location.href = "/chargingPile/group/" + self.pileForm.groupId;
                        });
                    }
                }else {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('已存在该桩序列号');
                    });
                }
            },
            fetchAllPileIdentification:function () {
                var self = this;
                $.get("/pile/identification", res =>{
                    console.log(res)
                    self.identificationList = res.data;
                })
            },
            checkIdentification:function () {
                var self = this;
                if (self.identificationList.indexOf(self.pileForm.identification) !== -1){
                    console.log("该序列号已存在");
                    self.warn = true;
                }else {
                    self.warn = false;
                }
            }
        }
    }
)