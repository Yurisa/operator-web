new Vue({
        el: "#app",
        data: {
          stationId:-1,
            groupId:-1,
            groupMessage:{},
            stationMessage:{},
            page: {
                pageNum: 1,
                pageSize: 8,
                pages: 0,
                list: [],
                prePage: 0,
                nextPage: 0,
                hasPreviousPage: false,
                hasNextPage: false,
            },
            signal:"operator",
        },
        mounted:function () {
            var self = this;
            var split = location.pathname.split('/');
            console.log(split);
            if(split[2] === 'station'){
                console.log("station");
                self.signal = "station";
                if (split.length > 3) {
                    self.stationId = split.pop();
                    self.getStationMessage(self.stationId);
                    self.fetchStationPiles();
                }
            }else if(split[2] === 'group'){
                self.signal = "group";
                console.log("group");
                if (split.length > 3) {
                    self.groupId = split.pop();
                    console.log(self.groupId);
                    self.getGroupMessage(self.groupId);
                    self.fetchGroupPiles();
                }
            }else {
                self.fetch();
            }
        },
        methods: {
            fetch: function () {
                var self = this;
                $.ajax({
                    url: "pile",
                    data:{
                        pageNum:self.page.pageNum,
                        pageSize:self.page.pageSize,
                    },
                    success: function (res) {
                        console.log(res);
                        var data = res.data;
                        console.log(data.list)
                        self.page = data;
                        //分页
                        console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
                            jump: function (obj, first) {
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetch();
                                }
                            }
                        });
                    }
                })

            },
            getGroupMessage:function(groupId){
                var self = this;
                $.ajax ({
                  type:'get',
                  url:"/group/message/" + groupId,
                  success:function (res) {
                      console.log(res);
                      self.groupMessage = res.data;
                  }
                 }) ;
            },
            fetchGroupPiles:function () {
              var self = this;
                $.ajax ({
                    type:'get',
                    url:"/pile/group/" + self.groupId,
                    data:{
                        pageNum:self.page.pageNum,
                        pageSize:self.page.pageSize,
                    },
                    success:function (res) {
                        var data = res.data;
                        console.log(data.list)
                        self.page = data;
                        //分页
                        console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
                            jump: function (obj, first) {
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetchGroupPiles();
                                }
                            }
                        });
                    }
                }) ;
            },
            getStationMessage:function(stationId){
                var self = this;
                $.ajax ({
                    type:'get',
                    url:"/station/message/" + stationId,
                    success:function (res) {
                        console.log(res);
                        self.stationMessage = res.data;
                    }
                }) ;
            },
            fetchStationPiles:function () {
                var self = this;
                $.ajax ({
                    type:'get',
                    url:"/pile/station/" + self.stationId,
                    data:{
                        pageNum:self.page.pageNum,
                        pageSize:self.page.pageSize,
                    },
                    success:function (res) {
                        var data = res.data;
                        console.log(data.list)
                        self.page = data;
                        //分页
                        console.log(data.pages);
                        layui.laypage.render({
                            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                            , count: data.total //数据总数，从服务端得到
                            , limit: data.pageSize,
                            curr: data.pageNum,
                            jump: function (obj, first) {
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetchStationPiles();
                                }
                            }
                        });
                    }
                }) ;

            },
            removePile:function (pile, index) {
                var self = this;
                layui.use('layer', function() {
                    layer.open({
                        title: '提示'
                        , content: '确定删除？'
                        ,btn:['确定','取消']
                        ,closeBtn:0
                        ,yes:function(index2){
                            $.ajax({
                                type: 'delete',
                                url: '/pile/' + pile.id,
                                success: function (res) {
                                    self.page.list.splice(index,1);
                                    console.log(res);
                                    if(res.status === true){
                                        layer.close(index2);
                                    }
                                }
                            })
                        }
                        ,btn2:function(index3, layero){
                            layer.close(index3);
                        }
                    });
                })
            },
            transToUpdate:function (pile) {
                window.location.href = "/updateChargingPile/" + pile.id;
            },
            transToAdd:function () {
                var self = this;
                if(self.groupMessage.maxNum > self.groupMessage.usedNum){
                    window.location.href='../../addChargingPile/'+self.groupId
                }else {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('已到达最大容量');
                    });
                }
            }
    }
})