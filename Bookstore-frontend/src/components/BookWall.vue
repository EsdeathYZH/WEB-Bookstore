<template>
  <div class="row fadeInLeft">
    <div class="bookwall" >
      <div class="column col-sm-6 col-md-4">
        <BookCard v-if="index%3===0" v-for ="(book,index) in this.books" :key="index" :isLogin="isLogin" :id="book.id" :name="book.bookname" :price="book.price" :author="book.author" :introduction="book.introduction" :defaultAddress="currentUser.defaultAddress" :saleAmount="book.saleAmount" :amount="book.amount"/>
      </div>
      <div class="column col-sm-6 col-md-4" >
        <BookCard v-if="index%3===1" v-for ="(book,index) in this.books" :key="index" :isLogin="isLogin" :id="book.id" :name="book.bookname" :price="book.price" :author="book.author" :introduction="book.introduction" :defaultAddress="currentUser.defaultAddress" :saleAmount="book.saleAmount" :amount="book.amount"/>
      </div>
      <div class="column col-sm-6 col-md-4">
        <BookCard v-if="index%3===2" v-for ="(book,index) in this.books" :key="index" :isLogin="isLogin" :id="book.id" :name="book.bookname" :price="book.price" :author="book.author" :introduction="book.introduction" :defaultAddress="currentUser.defaultAddress" :saleAmount="book.saleAmount" :amount="book.amount"/>
      </div>
    </div><!-- /.reviews -->
  </div>
</template>

<script >
import BookCard from './BookCard.vue'
export default {
  name: 'BookWall',
  props:['booktype'],
  data () {
      return {
        books:[],
        isLogin:false,
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
  components:{BookCard}
}

</script>

<style >
.bookwall{
  padding-left: 40px;
  padding-right: 40px;
}
  .bookwall .column{
    padding-left: 20px;
    padding-right: 20px;
  }

</style>
