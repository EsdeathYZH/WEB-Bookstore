<template>
  <div id="shopping_cart">
    <h1 style="padding-top: 100px;padding-left:400px;" v-if="!isLogin">请先登录哦~</h1>
    <h1 style="padding-top: 100px;padding-left:400px;" v-if="(items.length===0 && isLogin)">购物车为空~</h1>
    <div class="order-row row" v-if="isLogin" v-for ="(item,index) in items" :key="index">
      <div class="col-md-9">
        <BookOrder :id="item.book.id" :name="item.book.bookname" :receiver="item.receiver"  :price="item.book.price" :amount="item.amount" :address="item.address" :isPay="false"/>
      </div>
      <div class="col-md-3">
        <div class="btn btn-default" @click="pay(item,index)"><span class="fa fa-credit-card fa-lg" aria-hidden="true"></span></div>
        <div class="btn btn-default" @click="remove(item,index)"><span class="fa fa-window-close-o fa-lg" aria-hidden="true"></span></div>
        <div class="btn btn-default" data-toggle="modal" data-target="#modify" @click="openModal(index)"><span class="fa fa-edit fa-lg" aria-hidden="true"></span></div>
      </div>
    </div>

    <!-- 修改订单窗口 -->
    <div id="modify" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">修改订单</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="amount">数量</label>
              <input id="amount" class="form-control" type="text" v-model="order.amount" placeholder="">
            </div>
            <div class="form-group">
              <label for="address">收货地址</label>
              <input id="address" class="form-control" type="text" v-model="order.address" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" data-dismiss="modal" @click="modify" style="margin-top: 0px">修改</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import $ from 'jquery';
  import BookOrder from "./BookOrder"
    export default {
        name: "shopping-cart",
        data(){
            return{
              items: [
              ],
              modifyIndex:0,
              isLogin:false,
              currentUser:{
              },
              order:{id:'',address:'',amount:''}
            }
        },
        mounted(){
          let that = this;
          $.ajax({
            type:"get",
            url:"/profile",
            success:function(data){
              if(data===""){
                that.isLogin = false;
              }else{
                that.isLogin = true;
                that.currentUser = data;
                $.ajax({
                  type:"get",
                  url:"/shoppingcart/items",
                  success:function(data){
                    if(data===""){
                      alert("获取购物车数据失败!");
                    }else{
                      that.items = data;
                      for (let i=0;i<that.items.length;i++){
                        //默认收件人为用户名
                        that.items[i].receiver = that.currentUser.username;
                      }
                    }
                  },
                  error:function (data) {
                    alert("服务器错误!获取购物车信息失败!");
                  }
                });
              }
            }
          });
        },
        methods:{
          openModal:function (index) {
            this.order = this.items[index];
            this.modifyIndex = index;
          },
          pay:function (item,index) {
            let that = this;
            $.ajax({
              type:"post",
              url:"/pay",
              data:{"book_id":item.book.id,"user_id":that.currentUser.id,"amount":item.amount,"receiver":item.receiver,"address":item.address},
              success:function(data){
                if(data==="success"){
                  alert("付款成功!");
                  that.items.splice(index, 1);
                }else{
                  alert("购买失败!");
                }
              },
              error:function () {
                alert("服务器错误!");
              }
            });
          },
          modify:function () {
            let item = this.items[this.modifyIndex];
            let that = this;
            $.ajax({
              type:"post",
              url:"/shoppingcart/modify",
              data:{"id":item.book.id,"amount":that.order.amount,"address":that.order.address},
              success:function(data){
                if(data==="success"){
                }else{
                  alert("修改失败!");
                }
              },
              error:function () {
                alert("服务器错误!");
              }
            });
          },
          remove: function (item,index) {
            let that = this;
            $.ajax({
              type:"post",
              url:"/shoppingcart/remove",
              data:{"id":item.book.id},
              success:function(data){
                if(data==="success"){
                  that.items.splice(index, 1);
                }else{
                  alert("删除失败!");
                }
              },
              error:function () {
                alert("服务器错误!");
              }
            });
          }
        },
        components:{BookOrder}
    }
</script>

<style scoped>
  .modal-dialog{
    z-index: 9999;
  }

#shopping_cart{
  padding-left:60px;
  padding-right: 60px;
  margin-top: 60px;
}

#shopping_cart .order-row .col-md-3{
  padding-top:30px;
}

#shopping_cart .order-row .col-md-3 .btn{
  border-radius: 25px;
  padding-top: 15px;
  border:0px;
  display: block;
  margin-top:10px;
  width:50px;
  height:50px;
}
</style>
