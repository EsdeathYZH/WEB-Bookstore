<template>
	<div id="welcome">
		<div class="container">
		  <div id="homepage-feature" class="carousel slide">
		  	<ol class="carousel-indicators">
                <li data-target="#homepage-feature" data-slide-to="0" class="active"></li>
                <li data-target="#homepage-feature" data-slide-to="1"></li>
                <li data-target="#homepage-feature" data-slide-to="2"></li>
                <li data-target="#homepage-feature" data-slide-to="3"></li>
            </ol>
		    <div class="carousel-inner">
                <div class="item active">
                    <img src="http://localhost:8100/static/img/carousel1.jpg">
                </div>
                <div class="item">
                    <img src="http://localhost:8100/static/img/carousel2.jpg" >
                </div>
                <div class="item">
                    <img src="http://localhost:8100/static/img/carousel3.jpg">
                </div>
                <div class="item">
                    <img src="http://localhost:8100/static/img/carousel4.jpg">
                </div>
            </div><!-- /.carousel-inner -->
            <!-- Carousel Controls -->
            <a class="left carousel-control" href="#homepage-feature" data-slide="prev">
                <span class="icon fa fa-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#homepage-feature" data-slide="next">
                <span class="icon fa fa-chevron-right"></span>
            </a>
	        <div class="welcome-message">
	          <h1>欢迎来到Allen的书店</h1>
	          <p>专业的图书购买平台</p>
	        </div><!-- /.welcome-message -->
	    </div>
	      <div class="tab" role="tabpanel">
	        <!-- Nav tabs -->
	        <ul class="nav nav-tabs" role="tablist">
	            <li role="presentation" class="active"><a href="#recommand"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-fire"></span> 推荐图书</a></li>
	            <li role="presentation"><a href="#booklist"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-search"></span> 查找书目</a></li>
              <li v-if="isManager" role="presentation"><a href="#userlist"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-search"></span> 浏览用户</a></li>
              <li v-if="isManager" role="presentation"><a href="#orderlist"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-search"></span> 浏览订单</a></li>
	        </ul>
	        <!-- Tab panes -->
	        <div class="tab-content tabs">
	            <div role="tabpanel" class="tab-pane fade in active" id="recommand">
                <div class="row">
                  <div class="col-md-6">
                    <BookCard v-if="index%2===0" v-for="(book,index) in books" :key="index" :id="book.id" :isLogin="isLogin"  :name="book.bookname" :price="book.price" :author="book.author" :introduction="book.introduction" :defaultAddress="currentUser.address" :saleAmount="book.saleAmount" :amount="book.amount"/>
                  </div>
                  <div class="col-md-6">
                    <BookCard v-if="index%2===1" v-for="(book,index) in books" :key="index" :id="book.id" :isLogin="isLogin"  :name="book.bookname" :price="book.price" :author="book.author" :introduction="book.introduction" :defaultAddress="currentUser.address" :saleAmount="book.saleAmount" :amount="book.amount"/>
                  </div>
                </div>
	            </div>
	            <div role="tabpanel" class="tab-pane fade" id="booklist">
                <BookList/>
	            </div>
              <div v-if="isManager" role="tabpanel" class="tab-pane fade" id="userlist">
                <UserList/>
              </div>
              <div v-if="isManager" role="tabpanel" class="tab-pane fade" id="orderlist">
                <OrderList/>
              </div>
	        </div>
	      </div>
	      </div>
	    </div>

</template>

<script>
  import $ from 'jquery'
  import BookList from './BookList'
  import BookCard from './BookCard'
  import UserList from './UserList'
  import OrderList from './OrderList'
  export default {
    name: 'welcome',
    data () {
      return {
        books:[
        ],
        isLogin:false,
        isManager:false,
        currentUser: {

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
            if(that.currentUser.role==="manager"){
              that.isManager = true;
            }
          }
        }
      });
      $.ajax({
        type:"get",
        url:"/book/books",
        success:function(data){
          that.books = data;
        },
        error:function () {
          alert("服务器错误,获取图书信息失败!");
        }
      })
    },
    components:{BookList,BookCard,UserList,OrderList}
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#welcome{
	margin-top: 30px;
	background-color: #FFF;
}
#welcome .container .carousel{
	margin-top: 0px;
	overflow: hidden;
	background-size: 100% 100%;
	position: relative;
	height: 300px;
	margin-left: 120px;
	margin-right: 120px ;
	margin-bottom:0px;
}
#welcome .container .carousel .carousel-inner{
	z-index: 100;
}
#welcome .container .carousel .carousel-control{
	z-index: 200;
}
#welcome .container .carousel .carousel-indicators{
	z-index: 300;
}
#welcome .container .carousel .welcome-message {
	z-index: 400;
	position: absolute;
	top:70px;
	left: 120px;
	background: rgba(255,255,255,0.6);
	padding:20px 100px 40px 20px;
	border-radius: 10px;
}
#welcome .container .carousel .welcome-message h1{
	font-size: 40px;
}

#welcome .container .tab .tab-content .tab-pane .types .type p{
	padding-left: 20px;
	padding-right: 20px;
}
#welcome .container .tab .tab-content .tab-pane .types .type h4{
	padding-top: 40px;
}
#welcome .container .tab .nav-tabs{
  padding-left: 20px;
  margin-top: 30px;
  margin-right: 100px;
  margin-bottom: 20px;
  border-bottom:none;
  position: relative;
  color:#B5B5B5;
}
#welcome .container .tab .nav-tabs li{
  text-align: center;
}
#welcome .container .tab .nav-tabs li a{
  display: block;
  outline: none;
  height: 50px;
  line-height: 65px;
  font-size: 15px;
  font-weight: 600;
  color: #B5B5B5;
  text-transform: uppercase;
  margin-right: 0;
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 1px solid #D3D3D3;
  position: relative;
  padding:0px 20px 60px 20px;
  transition: all 0.3s ease 0s;
}
#welcome .container .tab .nav-tabs li.active a,.tab .nav-tabs li a:hover{
  outline: none;
  color: #6B6B6B;
  background:#FFF;
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 2px solid #6B6B6B;
}
#welcome .container .tab .nav-tabs li:last-child a:before{
  border: none;
}

  #recommand{
    padding-top:60px;
    padding-left: 100px;
    padding-right: 100px;
  }
</style>
