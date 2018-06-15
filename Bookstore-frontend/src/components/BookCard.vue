<template>
  <div class="book-item thumbnail">
    <div class="operation text-center">
      <a @click="addToCart" href="javascript:void(0);" style="margin-right:100px"><span class=" fa fa-shopping-cart fa-lg"></span></a>
      <a href="javascript:void(0);"><span class="fa fa-commenting fa-lg"></span></a>
    </div>
    <img :src="imagePath" :alt="imagePath">
    <div class="information">
      <div class="info1 row">
        <a class="book-name" href="#"><strong>{{name}}</strong></a>
        <div class="book-label label label-default">COMPUTER</div>
      </div>
      <div class="info2 row">
        <span class="price">¥{{price}}</span>
        <span class="author">{{author+" 著"}}</span><br>
        <div class="sale"><span>{{"销量:"+saleAmount+"     "+"库存:"+amount}}</span></div>
      </div>
      <div class="description row">
        <p>{{introduction}}</p>
      </div>
    </div><!-- /.information -->
  </div><!-- /.book-item -->
</template>

<script>
    export default {
      props:['isLogin','id','name','price','author','introduction','defaultAddress','saleAmount','amount'],
      data(){
        return{
          imagePath:""
        }
      },
      mounted(){
        this.imagePath = "http://localhost:8100/static/img/book"+this.id+".jpg";
      },
      methods:{
        addToCart:function () {
          let that = this;
          if(that.isLogin === false){
            alert("请先登录!");
            return;
          }
          $.ajax({
            type:"post",
            url:"/shoppingcart/add",
            data:{"id":that.id,"address":that.defaultAddress},
            success:function(data){
              if(data==="false"){
                alert("加入购物车失败!");
              }else{
                alert("加入购物车成功!");
              }
            }
          });
        }
      },
      name: "card"
    }
</script>

<style scoped>
 .book-item{
    position: relative;
    padding: 0;
    border-radius: 30px;
    border: none;
    background: #FFF;
  }
 .book-item .operation{
    display: none;
  }
 .book-item:hover .operation{
    display: block;
    width: 100%;
    position: absolute;
    background-color:rgba(0,0,0,0.6);
    top:0;
    left:0;
    padding:10px;
    color: #fff;
  }
 .book-item:hover .operation a{
    color: #FFFFFF;
  }
 .book-item:hover .operation a:hover{
   color: red;
 }
 .book-item .information {
    line-height: 1.1;
    padding-left: 20px;
    background-color:#FFF;
  }

 .book-item .information .info1{
    padding-top:30px;
    padding-bottom: 16px;
  }
 .book-item .information .info1 .book-name{
    text-align: left;
    font-size: 14px;
    color:#6B6B6B;
    text-decoration: none;
  }

 .book-item .information .info1 .book-name:hover{
    text-align: left;
    font-size: 14px;
    color:red;
  }

 .book-item .information .info1 .book-label{
    margin-left: 10px;
  }

 .book-item .information .info2{
    padding-bottom:10px;
  }
 .book-item .information .info2 .price{
    color: red;
    font-weight: bold;
  }

 .book-item .information .info2 .author{
    margin-left: 30px;
    font-weight: bold;
  }

 .book-item .information .info2 .sale{
   margin-top: 15px;
   color: #9d9d9d;
 }

 .book-item .information .description p{
    line-height: 1.5em;
    padding-right: 20px;
    color: #6B6B6B;
    font-size: 12px;
  }
</style>
