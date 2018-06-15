<template>
  <div id="booklist">
    <div class="container" >
      <form class="form-inline" id="search-bar" role="form">
        <label for="search-key">搜索栏</label>
        <input id="search-key" type="text" class="form-control" v-model="search_key" placeholder="请输入查找关键词">
        <select class="form-control" placeholder="name" v-model="search_by">
          <option value="bookname">按照书名搜索</option>
          <option value="author">按照作者搜索</option>
          <option value="type">按照种类搜索</option>
          <option value="price">按照价格搜索</option>
          <option value="amount">按照库存搜索</option>
        </select>
        <!--<div class="search-btn btn-info" @click="search"><span class="fa fa-search"></span></div>-->
        <label for="sort" style="margin-left: 20px">排序选项</label>
        <select id="sort" class="form-control" placeholder="none" v-model="sort_by">
          <option value="none">不排序</option>
          <option value="bookname">按照书名排序</option>
          <option value="author">按照作者排序</option>
          <option value="type">按照种类排序</option>
          <option value="price">按照价格排序</option>
          <option value="amount">按照库存排序</option>
        </select>
        <select class="form-control" placeholder="ascend" v-model="ifDescend">
          <option value="ascend">升序</option>
          <option value="descend">降序</option>
        </select>
      </form>
    </div>
    <table class="table  table-striped text-center">
      <thead>
      <tr>
        <th>序号</th>
        <th>名称</th>
        <th>作者</th>
        <th>类别</th>
        <th>价格</th>
        <th>库存</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for ="(book,index) in this.books">
        <td>{{index+1}}</td>
        <td><a href="#">{{book.bookname}}</a></td>
        <td>{{book.author}}</td>
        <td>{{book.type}}</td>
        <td>{{book.price}}元</td>
        <td>{{book.amount}}</td>
      </tr>
      <tr v-if="isManager">
        <td><input class="form-control" type="text" placeholder="id" id="book" v-model="book.id"/></td>
        <td><input class="form-control" type="text" placeholder="名称" id="bookname" v-model="book.bookname"/></td>
        <td><input class="form-control" type="text" placeholder="作者"id="author" v-model="book.author"/></td>
        <td><input class="form-control" type="text" placeholder="类别" id="type" v-model="book.type"/></td>
        <td><input class="form-control" placeholder="价格"type="text" id="price" v-model="book.price"/></td>
        <td><input class="form-control" placeholder="库存"type="text" id="amount" v-model="book.amount"/></td>
      </tr>
      <tr v-if="isManager">
        <td colspan="6"><div class="btn btn-success" id="modify" @click="modify">修改图书信息</div></td>
      </tr>
      <tr v-if="isManager">
        <td></td>
        <td><input class="form-control" type="text" placeholder="名称" id="newname" v-model="newBook.bookname"/></td>
        <td><input class="form-control" type="text" placeholder="作者"id="newauthor" v-model="newBook.author"/></td>
        <td><input class="form-control" type="text" placeholder="类别" id="newtype" v-model="newBook.type"/></td>
        <td><input class="form-control" placeholder="价格"type="text" id="newprice" v-model="newBook.price"/></td>
        <td><input class="form-control" placeholder="库存"type="text" id="newamount" v-model="newBook.amount"/></td>
      </tr>
      <tr v-if="isManager">
        <td colspan="6"><div class="btn btn-success" id="insert" @click="insert">添加新书</div></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    import $ from 'jquery'
    export default {
        name: "BookList",
        data (){
          return {
            ifDescend:"descend",
            search_key:"",
            search_by:"bookname",
            sort_by:"none",
            book:{id:'',bookname:'',author:'',type:'',price:'',amount:''},
            newBook:{bookname:'',author:'',type:'',price:'',amount:''},
            origin_books: [
            ],
            currentUser:{
            },
            isManager:false
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
                let user = data;
                that.currentUser = user;
                if(user.role==="manager"){
                  that.isManager = true;
                }
              }
            }
          });
          $.ajax({
            type:"get",
            url:"/book/books",
            success:function(data){
              that.origin_books = data;
            },
            error:function () {
              alert("服务器错误,获取图书信息失败!");
            }
          });
        },
        methods: {
          insert:function() {
            let that = this;
            $.ajax({
              type:"post",
              url:"/book/add",
              data:{"bookname":that.newBook.bookname,"author":that.newBook.author,
                "type":that.newBook.type,"price":that.newBook.price,"amount":that.newBook.amount},
              success:function(data){
                if(data==="success"){
                  alert("添加图书成功!");
                  window.location.reload();
                }else{
                  alert("添加图书失败!");
                }
              },
              error:function () {
                alert("服务器错误,添加用户信息失败!");
              }
            });
          },
          modify:function () {
            let that = this;
            $.ajax({
              type:"post",
              url:"/book/modify",
              data:{"id":that.book.id,"bookname":that.book.bookname,"author":that.newBook.author,
                "type":that.book.type,"price":that.book.price,"amount":that.book.amount},
              success:function(data){
                if(data==="success"){
                  alert("修改图书信息成功!");
                  window.location.reload();
                }else{
                  alert("修改图书信息失败!");
                }
              },
              error:function () {
                alert("服务器错误,添加用户信息失败!");
              }
            });
          }
        },
        computed: {
          books() {
            let temp_data = this.origin_books;
            let sort_key = this.sort_by;
            let search_by = this.search_by;
            let search_key = this.search_key;
            if(this.sort_by!=="none"){
              if(this.ifDescend==="descend"){
                temp_data.sort(function(a,b){
                  return (a[sort_key] < b[sort_key]?1:-1);
                });
              }else{
                temp_data.sort(function(a,b){
                  return (a[sort_key] > b[sort_key]?1:-1);
                });
              }
            }

            if(this.search_key.trim()!==""&&this.search_by!==""){
              temp_data = temp_data.filter(
                  function(row){
                    console.log(search_by);
                    return row[search_by].toLowerCase().indexOf(search_key.toLowerCase())>-1;
                  });
            }
            return temp_data;
          }
        }
    }
</script>

<style scoped>
  #booklist{
    padding-left: 50px;
    padding-right: 50px;
  }

  #booklist table tr td{
    height:50px;
  }

  #booklist table tr td a{
    text-decoration: none;
  }
  #booklist .container .form-inline{
    margin-top: 20px;
    margin-bottom: 20px;
  }

  #booklist table thead tr th{
    text-align: center;
  }


</style>
