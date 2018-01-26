
new Vue({
    el: "#registDIV",
    data: {
    	organizationInfo:{
    		parentId:-1,
    		orgName:"",
    		description:"",
    		type:-1,
    		status:-1
    	},
    	memberInfo:{
    		id:-1,
    		name:"",
    		mobile:"",
    		email:"",
    		departmentId:-1,
    		orgId:-1,
    		creatorId:-1,
    		status:-1,
    		password:""
    	}
    },
    mounted:function () {

    },
    methods:{
    	submitRegist:function(){
    		var self = this;
    		var reg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
            self.organizationInfo.orgName=$("#orgName").val().trim();
            self.organizationInfo.description=$("#description").val().trim();
            
            self.memberInfo.id=$("#memberId").val().trim();
            self.memberInfo.name=$("#memberName").val().trim();
            self.memberInfo.mobile=$('#tel').val().trim();
            self.memberInfo.email=$("#email").val().trim();
            self.memberInfo.password=$('#psw').val().trim();
        	if(self.organizationInfo.orgName === ""||self.memberInfo.id === -1||self.memberInfo.name === ""||self.memberInfo.mobile ===""||self.memberInfo.email === ""||self.memberInfo.password===""){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('请填写完整信息');
                });
        	}else if(self.memberInfo.password!=$('#confirmpsw').val().trim()){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('两次密码填写不一致');
                });
        	}
        	else if(!reg.test(self.memberInfo.email)){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('邮箱格式不正确');
                });
        	}
        	else if(self.memberInfo.mobile.length!=11){
        		layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('手机号码长度应为11位');
                });
        	}
        	else{
        		$.ajax ({
                  type:'post',
                  url:"/organization/regist",
                  data:{
                  
                  		orgName:self.organizationInfo.orgName,
                        description:self.organizationInfo.description,
                        name:self.memberInfo.name,
                  		id:self.memberInfo.id,
                  		mobile:self.memberInfo.mobile,
                  		email:self.memberInfo.email,
                  		password:self.memberInfo.password
                  	
                  },
                  success:function (res) {
                	  var status = res.status;  
                      if(status == true){  
                    	  console.log(res);
                          window.location.href="/homepage";
                      }else {  
                      	layui.use('layer', function () {
                              var layer = layui.layer;
                              
                              layer.msg('管理员帐号已存在');
                          });
                      }
                      
                  }
              })
        	}   	
    	}
    }
}
)
