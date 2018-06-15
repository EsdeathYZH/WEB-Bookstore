<template>
<div id="user_page">
  <div class="container">
    <div class="user-info row">
      <div class="col-md-11 col-md-offset-1 col-lg-11 col-lg-offset-1">
        <div class="row" id="user_message1">
          <div class="col-md-2 col-xs-12">
            <a class="thumbnail" href="#">
              <img width="180" height="171" alt="" src="../assets/user/icon3.jpg">
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
                <label>一句话介绍:{{currentUser.introduction}}&nbsp;&nbsp;  </label>
                <input type="text"  size='50'  style="background:transparent; border:none;" >
              </div>
            </form>
          </div>
          <div class="col-md-offset-2 col-md-1" >
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <h4>&nbsp;&nbsp;&nbsp;</h4>
            <button class="btn btn-danger" ><span class="fa fa-edit fa-lg " aria-hidden="true"></span></button>
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
