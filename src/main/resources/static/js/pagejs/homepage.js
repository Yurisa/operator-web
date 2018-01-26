new Vue({
    el: "#app",
    data: {
    	name:'',
		password:''
    },
    mounted:function () {

    },
    methods:{
    	loginBtn:function(){
    		var self = this;
    		layui.use('layer',function(){
    			var loginLayer=layer.open({
				  type:1
				  ,title:""
				  ,id: 'loginLayer' //设定一个id，防止重复弹出
			  	  ,content: $('#loginDIV')
			  	  ,area:'400px'
    			});
    		});
    		$('#cancel').click(function(){
    			layer.closeAll();
    		});
    		$(document).on('click', '#loginSubmit', function() {
        		self.checkInfo();
        		
        	});
    	}
    	,checkInfo : function(){  
    		var self = this;
            self.name = $('#organizationName').val().trim();
            self.password = $('#orLoginPassword').val();
            if(self.name == '' || self.password == ''){  
            	layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('用户名或密码不能为空');
                });
            }else{
            	$.post({  
                    url : '/member/checkLoginInfo',  
                    type : 'post',  
                    data : {  
                        name : self.name,  
                        password : self.password  
                    },  
                    success : function(res) {  
                    	var status = res.status;  
                        if(status == true){  
                        	//layer.closeAll();
                        	// location.reload();
                        	window.location.href = "/chargingStation"
                        }else {  
                        	layui.use('layer', function () {
                                var layer = layui.layer;
                                
                                layer.msg('用户名或密码不正确');
                            });
                        } 
                    }
                })
            }       
        }
    }
})

layui.use('element', function(){
  var element = layui.element;

});

layui.use('carousel', function(){
  var carousel = layui.carousel;
  //建造实例
  carousel.render({
    elem: '#picCarousel'
    ,width: '100%' //设置容器宽度
    ,height:'500px'
    ,arrow: 'always' //始终显示箭头
    //,anim: 'updown' //切换动画方式
  });
});

$('#memberPic').mouseover(function(){
	$('#nav-dl').css('display','block');
});
$('#memberPic').mouseout(function(){
	$('#nav-dl').css('display','none');
})
