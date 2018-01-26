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
                hasNextPage: false
            },
            priceInfo:{
            	id:-1,
            	name:"",
            	description:"",
            	creatorId:-1,
            	operatorId:-1,
            	status:-1,
            	uniPrice:0
            },
            signal:"operator",
        },
        mounted:function () {
        	var self = this;
        	self.fetch();
        },
        methods:{
        	fetch: function () {
                var self = this;
                $.ajax({
                    url: "price/list",
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
                            , limit: data.pageSize
                            , curr: data.pageNum
                            , jump: function (obj, first) {
                                //首次不执行
                                if(!first){
                                    self.page.pageNum = obj.curr;
                                    self.fetch();
                                }
                            }
                        });
                    }
                })
            },
            removeAreaPrice:function (price, index) {
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
                                url: '/price/' + price.id,
                                success: function (res) {
                                    self.page.list.splice(index,1);
                                    console.log(res);
                                    if(res.status === true){
                                    	window.parent.location.reload();
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
            clickUpdateBtn:function(price,index){
            	var self = this;
            	var layer = layui.layer;
            	layui.use('layer',function(){
            		layer.open({
            			type:1,
            			title:"",
            			area:"600px",
            			id:"UpdateAreaPrice",
            			content: $('#updatePriceDIV')
            			
            		});
            	});
            	$('#updatePriceName').val(price.name);
            	$('#updateUniPrice').val(price.uniPrice);
            	$('#updateDescription').val(price.description);
            	$(document).on('click', '#submitUpdateBtn', function() {
            		self.submitUpdatePrice(price,index);
            	});
            },
            submitUpdatePrice:function(price,index){
            	var self = this;
            	self.priceInfo.id=price.id;
            	self.priceInfo.creatorId=price.creatorId;
            	self.priceInfo.operatorId=price.operatorId;
            	self.priceInfo.status=price.status;
            	self.priceInfo.name=$('#updatePriceName').val();
            	self.priceInfo.uniPrice=$('#updateUniPrice').val();
            	self.priceInfo.description=$('#updateDescription').val();
            	
            	console.log(self.priceInfo);
            	
            	var updatePrice = self.priceInfo;

                if(updatePrice.name === "" || updatePrice.description === "" ||updatePrice.uniPrice === 0){
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('请填写完整信息');
                    });
                }else {
                    $.post("/price/"+updatePrice.id, self.priceInfo, res =>{
                        console.log(res);
                        location.reload();
                    });
                }
            },
            clickAddBtn:function(){
            	var self = this;
            	layui.use('layer',function(){
            		layer.open({
            			type:1,
            			title:"",
            			area:"600px",
            			id:"addAreaPrice",
            			content:$('#addAreaPriceDIV')
            		});
            		$(document).on('click', '#submitAreaPrice', function() {
                		self.submitAreaPrice();
                	});
            	})
            },
            submitAreaPrice:function () {
                var self = this;
                self.priceInfo.name=$("#priceName").val();
                self.priceInfo.uniPrice=$("#uniPrice").val();
                self.priceInfo.description=$("#description").val();
                self.priceInfo.creatorId=$("#creatorId").val();
                self.priceInfo.operatorId=$('#operatorId').val();
                if(self.priceInfo.name === "" || self.priceInfo.uniPrice === "" ||self.priceInfo.description === "" ||self.priceInfo.creatorId === "" ||self.priceInfo.operatorId === ""){
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('请填写完整信息');
                    });
                }else {
                	$.ajax ({
                        type:'post',
                        url:"/price/addAreaPrice",
                        data:{
                            name:self.priceInfo.name,
                            uniPrice:self.priceInfo.uniPrice,
                            description:self.priceInfo.description,
                            creatorId:self.priceInfo.creatorId,
                            operatorId:self.priceInfo.operatorId,
                        },
                        success:function (res) {
                            console.log(res);
                            location.reload();
                        }
                    }) 
                }
            },
        }
    }
)