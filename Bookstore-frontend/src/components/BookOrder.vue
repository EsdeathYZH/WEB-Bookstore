<template>
    <div id="order" class="container">
      <div class="order-info row">
        <div class="thumbnail col-md-4"><img :src="imagePath" :alt="imagePath"/></div>
        <div class="col-md-3">
          <div class="book-name"><a href="#">{{name}}</a></div>
          <div class="book-price" style="color: red">{{"¥"+price}}</div>
          <div class="book-amount">{{"数量："+amount+" 本"}}</div>
        </div>
        <div class="receive-info col-md-5">
          <div class="location">收货地址:{{address}}</div>
          <!--<div class="receiver">收件人:{{receiver}}</div>-->
          <div v-if="isPay" class="time">下单时间:{{datetime.getFullYear()+"年"+(datetime.getMonth()+1)+"月"+datetime.getDay()+"日"+datetime.getHours()+"时"+datetime.getMinutes()+"分"}}</div>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
      name: "book-order",
      props:['id','name','price','amount','address','receiver','time','isPay'],
      data(){
        return{
          imagePath:"",
          datetime:new Date()
        }
      },
      mounted(){
        this.imagePath = "http://localhost:8100/static/img/book"+this.id+".jpg";
        if(this.isPay){
          this.datetime = new Date(this.time);
        }
      }
    }
</script>

<style scoped>
  #order{
    margin-top: 30px;
    border-top: #9d9d9d solid 1px ;
    border-bottom: #9d9d9d solid 1px ;
    height:200px;
    width: 90%;
  }

  #order:hover{
    border-bottom: #555555 solid 1px;
  }

  #order .row .thumbnail{
    margin: 15px 0px 15px 0px;
    border-style:none;
    height: 160px;
    width:160px;
  }
  #order .row .thumbnail img{
    height:100%;
  }

  #order  .order-info{
    padding-left: 10px;
  }

  #order  .order-info .book-name{
    margin-top: 40px;
    text-decoration: none;
  }
  #order .order-info .book-name a{
    color:#444444;
    text-decoration: none;
  }
  #order .order-info .book-price,
  #order .order-info .book-amount{
    margin-top:10px;
  }

  #order .order-info .book-name{
    font-size: 20px;
  }

  #order .order-info .row .book-amount{
    padding-left: 14px;
    color:#888888;
    font-weight: bold;
  }
  #order .receive-info{
    padding-top:40px;
  }
  #order .receive-info .location,
  #order .receive-info .receiver,
  #order .receive-info .time{
    line-height: 1.8em;
    color: #9d9d9d;
  }
</style>
