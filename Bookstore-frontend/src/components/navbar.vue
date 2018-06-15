<template>
	<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
	    <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="index.html"><em><strong>BookStore</strong></em></a>
          </div>
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="搜索图书">
            </div>
            <button type="button" class="btn btn-default" aria-label="Left Align">
              <span class="fa fa-search" aria-hidden="true"></span>
            </button>
          </form>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <li class='active'>
                <a href="../../index.html"><span class="fa fa-home fa-lg" aria-hidden="true"></span></a>
              </li>
              <li>
                <a href="../../discover.html"><span class="fa fa-compass fa-lg" aria-hidden="true"></span></a>
              </li>
              <li>
                <a href="../../shopping_cart.html"><span class="fa fa-shopping-cart fa-lg" aria-hidden="true"></span></a>
              </li>
              <li>
                <a v-if="!isLogin" href="#" data-toggle="modal" data-target="#register"><span class="fa fa-user-circle fa-lg" aria-hidden="true" style="font-size: 16px"> 注册</span></a>
              </li>
              <li>
                <a v-if="!isLogin" href="#" data-toggle="modal" data-target="#login"><span class="fa fa-sign-in fa-lg" aria-hidden="true" style="font-size: 16px"> 登录</span></a>
              </li>
              <li>
                <a v-if="isLogin" href="../../user_page.html"><span class="fa fa-user fa-lg" aria-hidden="true"> {{currentUser.username}}</span></a>
              </li>
              <li>
                <a v-if="isLogin" href="#" data-toggle="modal" @click="logout"><span class="fa fa-sign-out fa-lg" aria-hidden="true" style="font-size: 16px"> 注销</span></a>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        <!-- 注册窗口 -->
	    </div><!--/.container -->
    <!--注册弹出框-->
    <div id="register" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" data-dismiss="modal" aria-hidden="true">
              <span>&times;</span>
            </button>
            <h1 class="text-center">注册</h1>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <label for="name">用户名</label>
                <input id="name" class="form-control" v-model="registerUser.username" type="text" placeholder="6-15位字母或数字">
              </div>
              <div class="form-group">
                <label for="password">密码</label>
                <input id="password" class="form-control" v-model="registerUser.password" type="password" placeholder="至少6位字母或数字">
              </div>
              <div class="form-group">
                <label for="password2">再次输入密码</label>
                <input id="password2"class="form-control" v-model="registerUser.password2" type="password" placeholder="至少6位字母或数字">
              </div>
              <div class="text-right">
                <button class="btn btn-primary" @click="register" style="margin-top: 0px">提交</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
              </div>
              <a href="" data-toggle="modal" data-dismiss="modal" data-target="#login">已有账号？点我登录</a>
          </div>
        </div>
      </div>
    </div>

    <!-- 登录窗口 -->
    <div id="login" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">登录</h1>
          </div>
          <div class="modal-body">

              <div class="form-group">
                <label for="log-name">用户名</label>
                <input id="log-name" class="form-control" type="text" v-model="user.username" placeholder="">
              </div>
              <div class="form-group">
                <label for="log-password">密码</label>
                <input id="log-password" class="form-control" type="password" v-model="user.password" placeholder="">
              </div>
              <div class="text-right">
                <button class="btn btn-primary" @click="login()" style="margin-top: 0px">登录</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
              </div>
              <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register">还没有账号？点我注册</a>

          </div>
        </div>
      </div>
    </div>
  </nav>

</template>


<script>
  import $ from 'jquery'
  export default {
    name: 'navbar',
    data() {
      return{
        isLogin:false,
        currentUser:{
        },
        user:{
          username:"",
          password:""
        },
        registerUser:{
          username:"",
          password:"",
          password2:""
        }
      };
    },
    mounted(){
      let that = this;
      $.ajax({
        type:"get",
        url:"/profile",
        success:function(data){
          if(data===""){
            that.isLogin = false
          }else{
            that.isLogin = true;
            that.currentUser = data;
          }
        }
      })
    },
    methods:{
      login:function () {
        //alert(this.user.username+this.user.password);
        let that = this;
        $.ajax({
          type:"post",
          url:"/login",
          data:{"username":that.user.username,"password":that.user.password},
          success:function(data){
            if(data==="failed"){
              alert("验证失败,请检查用户名或密码！")
            }
            else if(data==="null") {
              alert("用户不存在！")
            }else {
              alert("登录成功！");
              window.location.reload();
            }
          },
          error:function (data) {
            let info = JSON.stringify(data);
            alert(info);
          }
        });
        return false;
      },
      logout:function () {
        let that = this;
        $.ajax({
          type:"post",
          url:"/logout",
          success:function(data){
            alert("成功注销！");
            window.location.reload();
          },
          error:function () {
            alert("服务器错误!");
          }
        });
      },
      register:function () {
        let that = this;
        if(that.registerUser.password!==that.registerUser.password2){
          alert("两次输入密码不一致!");
          return;
        }
        $.ajax({
          type:"post",
          url:"/register",
          data:{"username":that.registerUser.username,"password":that.registerUser.password},
          success:function(data){
            if(data==="success"){
              alert("注册成功！");
              window.location.reload();
            }else if(data==="duplicate"){
              alert("该用户名已存在!");
            }else{
              alert(data);
            }
          },
          error:function () {
            alert("服务器错误!");
          }
        });
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .modal-dialog{
    z-index: 9999;
  }
.navbar .container .navbar-header a{
	color: #CD3700;
	font-size:24px;
}
.navbar .container{
	width:100%;
	padding-left: 20px;
	margin-right:0;
	background-color: #fff;
	color: #fff;
	border-bottom: 1px solid #EBEBEB;
	box-shadow: none;
}
.navbar .container .navbar-collapse ul li{
	padding-left: 40px;
}
.navbar-fixed-top .navbar-nav > li > a{
	margin-top: 5px;
	padding:10px 10px 10px 10px;
	border-radius:5px;
	box-shadow: none;
}
.navbar-fixed-top .navbar-nav > .active > a,
.navbar-fixed-top .navbar-nav > .active > a:hover,
.navbar-fixed-top .navbar-nav > .active > a:focus,
.navbar-fixed-top .navbar-nav > .open > a,
.navbar-fixed-top .navbar-nav > .open > a:hover,
.navbar-fixed-top .navbar-nav > .open > a:focus{
    background: #fff;
    outline:none;
}
</style>
