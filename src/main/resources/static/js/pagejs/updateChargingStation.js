new Vue({
        el: "#app",
        data: {
            stationId:"",
            name:"",
            zoneId:"",
            cityId:"",
            provinceId:"",
            positionId:"",
            gpsLat:30.26,
            gpsLng:120.19,
            detailAddress:"",
            description:"",
            map:null,
            keyword:"",
            pointArray:[],
            signal:"operator",
        },
        mounted:function () {
            this.initFetch();


        },
        methods:{
            initFetch:function () {
                var self=this;
                var split = location.pathname.split('/');
                if (split.length ==3) {
                    self.stationId = split[2];
                    self.getStationMessage(self.stationId);//获取经纬度和表单信息
                    self.initMap();
                }
            },
            getStationMessage:function(stationId){
                var self = this;
                $.ajax ({
                    type:'get',
                    url:"/station/" + stationId,
                    success:function (res) {
                        var StationMessage=res.data;
                        console.log(res);
                        self.positionId=StationMessage.positionId;
                        self.stationId=StationMessage.stationId;
                        self.name =StationMessage.name ;
                        self.zoneId =StationMessage.regionId ;
                        self.detailAddress =StationMessage.detailAddress ;
                        self.description=StationMessage.description;
                        self.gpsLat =StationMessage.gpsLat ;
                        self.gpsLng =StationMessage.gpsLng ;
                        console.log("self.zoneId"+self.zoneId);
                        // 根据zoneId查找cityId:
                        $.get("/area/getMyRegionId/"+self.zoneId ,function(res){
                            var data=res.data;
                            self.cityId=data.parentId;
                            console.log("self.cityId"+self.cityId);
                            // 根据cityId查找provinceId:
                            $.get("/area/getMyRegionId/"+self.cityId ,function(res){
                                var data=res.data;
                                self.provinceId=data.parentId;
                                console.log("self.provinceId"+self.provinceId);
                                self.getArea();
                            },"json");

                        },"json");

                    }
                }).then(()=>{

                }) ;


            },
            // 点击按钮：
            clickBtn:function(){
                var self=this;
                layui.use('layer', function(){
                    var layer = layui.layer;
                    var noValueCount=0;
                    var inputValueArr=new Array(
                        $("#chargingStationName").val(),
                        $("#s_province").val(),
                        $("#s_city").val(),
                        $("#s_zone").val(),
                        $("#chargingStationAddress").val(),
                        $("#chargingStationDescription").val(),
                        self.gpsLng,
                        self.gpsLat
                    );
                    for(var i=0;i<inputValueArr.length;i++){
                        var value=inputValueArr[i];
                        if(value==""||value==null||value.length<=0){
                            noValueCount++;
                        }
                    }
                    if(noValueCount>0){
                        layer.msg('请将信息填写完整！', {icon: 5});
                    }else{
                        layer.confirm('确定提交？', {icon: 3, title:'修改充电站',shadeClose:true}, function(index){
                            layer.msg('修改成功！', {icon: 6});
                            layer.close(index);
                            self.submitMessage();
                        });
                    }



                });
            },
            // 提交信息：
            submitMessage:function () {
                var self = this;
                $.ajax ({
                    type:'post',
                    url:"/station/" + self.stationId,
                    data:{
                        stationId:self.stationId,
                        name:self.name,
                        regionId:self.zoneId,
                        positionId:self.positionId,
                        detailAddress:self.detailAddress,
                        description:self.description,
                        gpsLat:self.gpsLat,
                        gpsLng:self.gpsLng,
                    },
                    success:function (res) {
                        console.log(res);
                        self.getStationMessage(self.stationId);
                    }
                }) ;
            },
            // 初始地图：
            initMap:function () {
                var self = this;
                self.map = new BMap.Map("allmap");          // 创建地图实例
                var point = new BMap.Point(self.gpsLng, self.gpsLat);  // 创建点坐标
                self.map.centerAndZoom(point, 20);
                self.map.enableScrollWheelZoom(true);
                self.map.addControl(new BMap.NavigationControl());
                self.map.addControl(new BMap.GeolocationControl());

                var  signLogo = new BMap.Icon("/sign.png",new BMap.Size(20,20));
                signLogo.setImageSize(new BMap.Size(20,20));
                var marker = new BMap.Marker(point,{icon:signLogo});
                var label = new BMap.Label("充电站位置",{offset:new BMap.Size(20,-10)});
                marker.setLabel(label);
                self.map.addOverlay(marker);
                self.map.panTo(point);// map.panTo方法，把点击的点设置为地图中心点

                self.map.addEventListener("click",function(e){
                    if (self.pointArray.length > 0){
                        self.remove_overlay();
                    }
                    self.pointArray.push(e);
                    self.gpsLat = e.point.lat;
                    self.gpsLng = e.point.lng;
                    var  signLogo = new BMap.Icon("/sign.png",new BMap.Size(20,20));
                    signLogo.setImageSize(new BMap.Size(20,20));
                    var marker = new BMap.Marker(e.point,{icon:signLogo});
                    var label = new BMap.Label("充电站位置",{offset:new BMap.Size(20,-10)});
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
            // 通过传来的经纬度来查找地点,显示keyword
            showKeyword:function (Lon,Lat) {
                var point = new BMap.Point(Lon, Lat);  // 创建点坐标
                var marker = new BMap.Marker(point);
                self.map.addOverlay(marker);

            },
            getProvince:function (oldProvinceId) {
                console.log("oldProvinceId:"+oldProvinceId);
                $.get("/area", function(res){
                    var data=res.data;
                    $("#s_province").html("<option value=''>省</option>");
                    for(var i=0;i<data.length;i++){
                        var provinceId=data[i].id;
                        var provinceName=data[i].name;
                        if(provinceId==oldProvinceId){
                            $("#s_province").append("<option value='"+provinceId+"' selected>"+provinceName+"</option>");
                        }else{
                            $("#s_province").append("<option value='"+provinceId+"'>"+provinceName+"</option>");
                        }

                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getCity:function (provinceId,oldCityId) {
                console.log("oldCityId:"+oldCityId);
                $.get("/area/"+provinceId ,function(res){
                    var data=res.data;
                    $("#s_city").html("<option value=''>市</option>");
                    for(var i=0;i<data.length;i++){
                        var cityId=data[i].id;
                        var cityName=data[i].name;
                        if(cityId==oldCityId){
                            $("#s_city").append("<option value='"+cityId+"' selected>"+cityName+"</option>");
                        }else{
                            $("#s_city").append("<option value='"+cityId+"'>"+cityName+"</option>");
                        }

                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getZone:function (cityId,oldZoneId) {
                console.log("oldZoneId:"+oldZoneId);
                $.get("/area/"+cityId ,function(res){
                    var data=res.data;
                    $("#s_zone").html("<option value=''>区</option>");
                    for(var i=0;i<data.length;i++){
                        var zoneId=data[i].id;
                        var zoneName=data[i].name;
                        if(zoneId==oldZoneId){
                            $("#s_zone").append("<option value='"+zoneId+"' selected>"+zoneName+"</option>");
                        }else{
                            $("#s_zone").append("<option value='"+zoneId+"'>"+zoneName+"</option>");
                        }

                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getArea:function () {
                this.getProvince(this.provinceId);
                this.getCity(this.provinceId,this.cityId);
                this.getZone(this.cityId,this.zoneId);
                var self=this;

                layui.use('form', function(){
                    layui.form.on('select(province)', function(data){
                        self.provinceId=data.value;
                        var cityId="";
                        self.getCity(data.value,cityId);//得到省份对应的city
                    });
                });
                layui.use('form', function(){
                    layui.form.on('select(zone)', function(data){
                        self.zoneId=data.value;
                        console.log("zoneId:"+data.value);
                    });

                });
                layui.use('form', function(){
                    layui.form.on('select(city)', function(data){
                        self.cityId=data.value;
                        var zoneId="";
                        self.getZone(data.value,zoneId);//得到city对应的zone

                    });
                });

            }
        }
    }
)
