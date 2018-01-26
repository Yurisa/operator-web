new Vue({
        el: "#app",
        data: {
            station: {
                stationId: '',
                positionId: '',
                name: '',
                description: '',
                gpsLat: '',
                gpsLng: '',
                regionId: '',
                detailAddress: '',
                creatorId: ''
            },
            groupType: {
                id: '',
                name: '',
                description: '',
                maxCount: '',
                createTime: '',
                status: ''
            },
            groupForm: {
                // groupId: '',
                name: '',
                operatorId: 1,
                groupTypeId: -1,
                chargingType: -1,
                chargingPriceId: -1,
                buildingsId: -1,
                buildingsName: '',
                maxCount: -1,
                creatorId: 1,
                status: 1,
                gpsLat:null,
                gpsLng:null
            },
            map:null,
            keyword: '',
            pointArray:[],
            signal:'operator',
        },
        mounted:function () {
            this.formRender();
            // this.getArea();
            var self = this;
            var split = location.pathname.split('/');
            console.log(split);
            if (split[1] === 'updateChargingPileGroup') {
                self.groupForm.groupId = split[2];
                self.fetchGroupForm(self.groupForm.groupId);
            }
            // this.fetchPrice();
        },
        methods:{
            formRender: function () {
                layui.use('form', function(){
                    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

                    //……

                    //但是，如果你的HTML是动态生成的，自动渲染就会失效
                    //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
                    form.render();
                });
            },
            fetchGroupForm: function (groupId) {
              var self = this;
              $.ajax({
                  url: '/group/' + groupId,
                  success: function (res) {
                      var data = res.data;
                      console.log(data)
                      self.groupForm = data;
                      $('#chargingType').val(self.groupForm.chargingType);
                      self.formRender();
                      self.fetchPrice()
                  }
              }).then(()=>{
                  self.initMap();
              })
            },
            fetchPrice: function () {
                console.log("fetchprice")
                var self = this;
                $.ajax({
                    url: "/price",
                    success: function (res) {
                        console.log("11111")
                        var data = res.data;
                        console.log(res);
                        console.log(self.groupForm.chargingPriceId);
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].id === self.groupForm.chargingPriceId) {
                                $('#price').append( '<option value=' + data[i].id + ' selected>' + data[i].name + '</option>');
                            }else {
                                $('#price').append( '<option value=' + data[i].id + '>' + data[i].name + '</option>');
                            }

                        }
                        self.formRender();
                    }
                })
            },
            initMap:function () {
                var self = this;
                self.map = new BMap.Map("allmap");          // 创建地图实例
                var point = new BMap.Point(120.19, 30.26);  // 创建点坐标
                self.map.centerAndZoom(point, 20);
                self.map.enableScrollWheelZoom(true);
                self.map.addControl(new BMap.NavigationControl());
                self.map.addControl(new BMap.GeolocationControl());
                console.log(self.groupForm.gpsLng)
                var startpoint = new BMap.Point(self.groupForm.gpsLng,self.groupForm.gpsLat);
                var signLogo = new BMap.Icon("/sign.png",new BMap.Size(20,20));
                signLogo.setImageSize(new BMap.Size(20,20));
                var marker2 = new BMap.Marker(startpoint,{icon:signLogo});
                var label2 = new BMap.Label("充电群位置",{offset:new BMap.Size(20,-10)});
                marker2.setLabel(label2);
                self.map.addOverlay(marker2);
                self.pointArray.push(startpoint)
                self.map.panTo(startpoint);
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
                    self.groupForm.gpsLat = e.point.lat;
                    self.groupForm.gpsLng = e.point.lng;
                    var signLogo = new BMap.Icon("/sign.png",new BMap.Size(20,20));
                    signLogo.setImageSize(new BMap.Size(20,20));
                    var marker = new BMap.Marker(e.point,{icon:signLogo});
                    var label = new BMap.Label("充电群位置",{offset:new BMap.Size(20,-10)});
                    marker.setLabel(label);
                    self.map.addOverlay(marker);
                    self.map.panTo(new BMap.Point(e.point.lng,e.point.lat));// map.panTo方法，把点击的点设置为地图中心点
                });
            },
            searchByGroupName:function () {
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
            submitGroup:function () {
                var self = this;
                self.groupForm.chargingType = $('#chargingType').val();
                self.groupForm.chargingPriceId = $('#price').val();
                console.log(self.groupForm);
                var group = self.groupForm;
                if(group.name === "" || group.chargingPriceId === -1 || group.chargingType === -1 || group.buildingsId === -1 || group.creatorId === -1 || group.gpsLng === null || group.gpsLat === null){
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('请填写完整信息');
                    });
                }else {
                    $.post("/group/"+self.groupForm.groupId, self.groupForm, res =>{
                        console.log(res);
                        window.location.href = '/chargingPileGroup';
                    });
                }
            }

        }
    }
)
