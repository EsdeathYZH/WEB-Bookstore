<template>
<div id="user_page">
  <div class="container">
    <div class="user-info row">
      <div class="col-md-11 col-md-offset-1 col-lg-11 col-lg-offset-1">
        <div class="row" id="user_message1">
          <div class="col-md-2 col-xs-12">
            <a class="thumbnail" href="#">
              <img width="180px" height="171px" alt="" :src="url">
            </a>
          </div>
          <div class="col-md-7 col-xs-12">
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <h3>{{currentUser.username}}&nbsp;&nbsp;&nbsp;</h3>
            <form class="form-horizontal">
              <div class="form-group">
                <span class="glyphicon glyphicon-education" aria-hidden="true"></span>
                <label>学校:{{currentUser.school}}&nbsp;&nbsp;</label>
                <label>|&nbsp;&nbsp;&nbsp;年龄:{{currentUser.age}}&nbsp;</label>
                <label>|&nbsp;&nbsp;&nbsp;身份:{{currentUser.role}}&nbsp;</label>
                <label>|&nbsp;&nbsp;&nbsp;所在地:{{currentUser.city}}&nbsp;</label>
              </div>
              <div class="form-group">
                <label>自我介绍:{{currentUser.introduction}}&nbsp;&nbsp;  </label>
                <input type="text"  size='50'  style="background:transparent; border:none;" >
              </div>
            </form>
          </div>
          <div class="col-md-offset-2 col-md-1" >
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <button class="btn btn-danger" data-toggle="modal" data-target="#profile" ><span class="fa fa-edit fa-lg " aria-hidden="true"></span></button>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
    <div class="col-md-8 col-md-offset-1 col-lg-8 col-lg-offset-1" id="user-record">
      <div class="page-header" style="font-size: 20px"><strong>我的订单</strong></div>
      <BookOrder v-for="(order,index) in orders" :key="index" :id="order.bookId" :name="order.bookname" :price="order.bookprice"  :time="order.time" :amount="order.amount" :address="order.address" :receiver="order.receiver" :isPay="true"/>
      <div class="page-header" style="font-size: 20px"><strong>我的收藏</strong></div>
    </div>
    <div class="col-md-3 col-lg-3" id="user_message3">
      <div class="page-header"><strong>消息框</strong></div>
        <ul>
          <li><a href="#"><h5>购物车中的商品有降价 </h5></a></li>
          <li><a href="#"><h5>您收到了来自上海书店的新消息</h5></a></li>
        </ul>
      <div class="page-header"><strong>我的钱包</strong></div>
        <ul>
          <li><h5>余额：0元 </h5></li>
          <li><h5>优惠券剩余：3张</h5></li>
        </ul>
    </div>
    </div>
  </div>

  <!--修改个人信息弹出框-->
  <div id="profile" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" data-dismiss="modal" aria-hidden="true">
            <span>&times;</span>
          </button>
          <h1 class="text-center">修改个人信息</h1>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="age">年龄</label>
            <input id="age" class="form-control" v-model="newUser.age" type="text">
          </div>
          <div class="form-group">
            <label for="school">学校</label>
            <input id="school" class="form-control" v-model="newUser.school" type="text">
          </div>
          <div class="form-group">
            <label for="city">所在地</label>
            <input id="city"class="form-control" v-model="newUser.city" type="text">
          </div>
          <div class="form-group">
            <label for="introduction">自我介绍</label>
            <input id="introduction"class="form-control" v-model="newUser.introduction" type="text">
          </div>
          <div class="text-right">
            <button class="btn btn-primary" @click="updateProfile" style="margin-top: 0px">提交</button>
            <button class="btn btn-danger" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>
</template>
<script>
  import BookOrder from "./BookOrder"
  export default {
    name:'user_page',
     data(){
       return {
         isLogin:false,
         orders: [
         ],
         currentUser:{
         },
         newUser:{
         },
       }
     },
    methods:{
      updateProfile:function () {
        let that = this;
        $.ajax({
          type:"post",
          url:"/user/updateProfile",
          data:{"age":that.newUser.age,"city":that.newUser.city,"school":that.newUser.school,"introduction":that.newUser.introduction},
          success:function(data){
            if(data==="failed"){
              alert("修改失败!")
            }
            else {
              alert("修改成功！");
              window.location.reload();
            }
          },
          error:function (data) {
            let info = JSON.stringify(data);
            alert(info);
          }
        });
      }
    },
    computed:{
      url(){
        if(this.currentUser.username===""){
          return "http://localhost:8100/icon/www";
        }
        return "http://localhost:8100/icon/"+this.currentUser.username;
      }
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
            that.newUser = data;
          }
        }
      });
      $.ajax({
        type:"get",
        url:"/record",
        success:function(data){
            that.orders = data;
        },
        error:function () {
          alert("服务器错误,获取历史订单失败!");
        }
      });
    },
  components:{BookOrder},
}
</script>

<style scoped>
  .modal-dialog{
    z-index: 9999;
  }
  #user_page{
    padding-left: 60px;
  }
  #user_message1{
    margin-top: 50px;
    padding-top: 30px;
  }
  #user_page .form-group{
    margin: 5px;
  }
  #user_page .container{
    padding:auto auto auto 0;
    margin:auto auto auto 0;
  }
  #user_message3 ul{
    padding-left: 10px;
    list-style: none;
  }
  a:hover,a:focus{
    outline: none;
    text-decoration: none;
  }
</style>
