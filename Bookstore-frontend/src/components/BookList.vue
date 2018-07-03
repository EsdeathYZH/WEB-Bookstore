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
        <select class="form-control"  v-model="ifDescend">
          <option value="ascend">升序</option>
          <option value="descend">降序</option>
        </select>
      </form>
      <div class="row" v-if="isManager" style="margin-bottom: 20px;">
        <div class="btn btn-danger" id="insert" style="margin-right: 10px" data-toggle="modal" data-target="#add_book">
          <span class="fa fa-plus-square fa-lg"></span> 添加新书
        </div>
        <div class="btn btn-danger" id="modify" style="margin-right: 10px" data-toggle="modal" data-target="#modify_book">
          <span class="fa fa-pencil-square fa-lg"></span> 修改图书信息
        </div>
        <div class="btn btn-danger" id="delete" style="margin-right: 10px" data-toggle="modal" data-target="#delete_book">
          <span class="fa fa-window-close fa-lg"></span> 删除图书
        </div>
      </div>
    </div>
    <table class="table table-striped text-center">
      <thead>
      <tr>
        <th>序号</th>
        <th>名称</th>
        <th>作者</th>
        <th>类别</th>
        <th>价格</th>
        <th>库存</th>
        <th>月销量</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for ="(book,index) in this.books">
        <td>{{book.id}}</td>
        <td><a href="#">{{book.bookname}}</a></td>
        <td>{{book.author}}</td>
        <td>{{book.type}}</td>
        <td>{{book.price}}元</td>
        <td>{{book.amount}}本</td>
        <td>{{book.saleAmount}}本</td>
      </tr>
      </tbody>
    </table>

    <!-- 添加图书窗口 -->
    <div id="add_book" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">添加新书</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="new_bookname">名称 *</label>
              <input id="new_bookname" class="form-control" type="text" v-model="newBook.bookname" placeholder="">
            </div>
            <div class="form-group">
              <label for="new_author">作者 *</label>
              <input id="new_author" class="form-control" type="text" v-model="newBook.author" placeholder="">
            </div>
            <div class="form-group">
              <label for="new_type">类别 *</label>
              <input id="new_type" class="form-control" type="text" v-model="newBook.type" placeholder="">
            </div>
            <div class="form-group">
              <label for="new_price">价格 *</label>
              <input id="new_price" class="form-control" type="text" v-model="newBook.price" placeholder="">
            </div>
            <div class="form-group">
              <label for="new_amount">库存 *</label>
              <input id="new_amount" class="form-control" type="text" v-model="newBook.amount" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="insert" style="margin-top: 0px">添加</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 修改图书窗口 -->
    <div id="modify_book" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">修改图书信息</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="bookid">序号 *</label>
              <input id="bookid" class="form-control" type="text" v-model="book.id" placeholder="">
            </div>
            <div class="form-group">
              <label for="bookname">名称 </label>
              <input id="bookname" class="form-control" type="text" v-model="book.bookname" placeholder="">
            </div>
            <div class="form-group">
              <label for="author">作者 </label>
              <input id="author" class="form-control" type="text" v-model="book.author" placeholder="">
            </div>
            <div class="form-group">
              <label for="type">类别 </label>
              <input id="type" class="form-control" type="text" v-model="book.type" placeholder="">
            </div>
            <div class="form-group">
              <label for="price">价格 </label>
              <input id="price" class="form-control" type="text" v-model="book.price" placeholder="">
            </div>
            <div class="form-group">
              <label for="amount">库存 </label>
              <input id="amount" class="form-control" type="text" v-model="book.amount" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="modify" style="margin-top: 0px">修改</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 删除图书窗口 -->
    <div id="delete_book" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">删除图书</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="deleteid">序号 *</label>
              <input id="deleteid" class="form-control" type="text" v-model="deleteId" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="deleteBook" style="margin-top: 0px">删除</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>
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
            deleteId:0,
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
            if(that.newBook.bookname===""||that.newBook.author===""||that.newBook.type===""||
              that.newBook.price===""||that.newBook.amount===""){
              alert("信息不完整！");
              return;
            }
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
              data:{"id":that.book.id,"bookname":that.book.bookname,"author":that.book.author,
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
                alert("服务器错误,修改图书信息失败!");
              }
            });
          },
          deleteBook:function(){
            let that = this;
            $.ajax({
              type:"post",
              url:"/book/delete",
              data:{"id":that.deleteId},
              success:function(data){
                if(data==="success"){
                  alert("删除图书成功!");
                  window.location.reload();
                }else{
                  alert("删除图书失败!");
                }
              },
              error:function () {
                alert("服务器错误,删除图书失败!");
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
  .modal-dialog{
    z-index: 9999;
  }
  #booklist{
    padding-left: 50px;
    padding-right: 50px;
  }

  #booklist table tr td{
    width: 50px;
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
