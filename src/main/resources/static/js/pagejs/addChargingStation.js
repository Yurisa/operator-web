new Vue({
        el: "#app",
        data: {
            stationId:"",
            name:"",
            zoneId:"",
            cityId:"",
            provinceId:"",
            gpsLat:"",
            gpsLng:"",
            detailAddress:"",
            description:"",
            map:null,
            keyword:"",
            pointArray:[],
            signal:"operator",
        },
        mounted:function () {
            this.initMap();
            this.getArea();
        },
        methods:{
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
                        layer.msg(self.gpsLng);
                        if(value==""||value==null||value.length<=0){
                            noValueCount++;
                        }
                    }
                    if(noValueCount>0){
                        layer.msg('请将信息填写完整！', {icon: 5});
                    }else{
                        layer.confirm('确定提交？', {icon: 3, title:'添加充电站',shadeClose:true}, function(index){
                            layer.msg('添加成功！', {icon: 6});
                            layer.close(index);
                            self.submitMessage();
                            window.location.href = "/chargingStation";

                        });
                    }



                });
            },
            // 提交信息：
            submitMessage:function () {
                var self = this;
                self.provinceId=$("#s_province").val(),
                self.cityId=$("#s_city").val(),
                self.zoneId=$("#s_zone").val(),
                $.ajax ({
                    type:'post',
                    url:"/station/",
                    data:{
                        name:self.name,
                        regionId:self.zoneId,
                        detailAddress:self.detailAddress,
                        description:self.description,
                        gpsLat:self.gpsLat,
                        gpsLng:self.gpsLng,
                    },
                    success:function (res) {
                        console.log(res);
                    }
                }) ;
            },
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
                        self.map.addOverlay(marker);
                        map.panTo(point);
                    })
                });
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
            getProvince:function () {
                $.get("/area", function(res){
                    var data=res.data;
                    $("#s_province").html("<option value=''>省</option>");
                    for(var i=0;i<data.length;i++){
                        var provinceId=data[i].id;
                        var provinceName=data[i].name;
                        $("#s_province").append("<option value='"+provinceId+"'>"+provinceName+"</option>");
                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getCity:function (provinceId) {
                $.get("/area/"+provinceId ,function(res){
                    var data=res.data;
                    $("#s_city").html("<option value=''>市</option>");
                    for(var i=0;i<data.length;i++){
                        var cityId=data[i].id;
                        var cityName=data[i].name;
                        $("#s_city").append("<option value='"+cityId+"'>"+cityName+"</option>");
                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getZone:function (cityId) {
                $.get("/area/"+cityId ,function(res){
                    var data=res.data;
                    $("#s_zone").html("<option value=''>区</option>");
                    for(var i=0;i<data.length;i++){
                        var zoneId=data[i].id;
                        var zoneName=data[i].name;
                        $("#s_zone").append("<option value='"+zoneId+"'>"+zoneName+"</option>");
                    }
                    layui.use('form', function(){
                        layui.form.render(); //更新全部
                        layui.form.render('select'); //刷新select选择框渲染
                    });
                },"json");
            },
            getArea:function () {
                this.getProvince();
                var self=this;
                layui.use('form', function(){
                    layui.form.on('select(province)', function(data){
                        self.getCity(data.value);//得到省份对应的city
                    });
                });
                layui.use('form', function(){
                    layui.form.on('select(zone)', function(data){
                        console.log("zoneId:"+data.value);
                    });

                });
                layui.use('form', function(){
                    layui.form.on('select(city)', function(data){
                        self.getZone(data.value);//得到city对应的zone

                    });
                });

            }

        }
    }
)