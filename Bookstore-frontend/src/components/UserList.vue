<template>
  <div id="userlist">
    <div class="row" v-if="isManager" style="margin-left:5px;margin-bottom: 20px;">
      <div class="btn btn-danger" id="insert" style="margin-right: 10px" data-toggle="modal" data-target="#add_user">
        <span class="fa fa-user-plus fa-lg"></span> 添加用户
      </div>
      <!--<div class="btn btn-danger" id="modify" style="margin-right: 10px" data-toggle="modal" data-target="#modify_user">-->
        <!--<span class="fa fa-user-md fa-lg"></span> 修改用户权限-->
      <!--</div>-->
      <div class="btn btn-danger" id="delete" style="margin-right: 10px" data-toggle="modal" data-target="#delete_user">
        <span class="fa fa-user-times fa-lg"></span> 删除用户
      </div>
    </div>
    <table class="table  table-striped text-center">
      <thead>
      <tr>
        <th>序号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>年龄</th>
        <th>身份</th>
        <th>学校</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for ="(user,index) in this.users">
        <td>{{index+1}}</td>
        <td><a href="#">{{user.username}}</a></td>
        <td>{{user.password}}</td>
        <td>{{user.age}}</td>
        <td>{{user.role}}</td>
        <td>{{user.school}}</td>
      </tr>
      </tbody>
    </table>

    <!-- 添加用户窗口 -->
    <div id="add_user" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">添加用户</h1>
          </div>
          <div class="modal-body">

            <div class="form-group">
              <label for="username">用户名</label>
              <input id="username" class="form-control" type="text" v-model="newUser.username" placeholder="">
            </div>
            <div class="form-group">
              <label for="password">密码</label>
              <input id="password" class="form-control" type="password" v-model="newUser.password" placeholder="">
            </div>
            <div class="form-group">
              <label for="age">年龄</label>
              <input id="age" class="form-control" type="text" v-model="newUser.age" placeholder="">
            </div>
            <div class="form-group">
              <label for="role">身份</label>
              <input id="role" class="form-control" type="text" v-model="newUser.role" placeholder="">
            </div>
            <div class="form-group">
              <label for="school">学校</label>
              <input id="school" class="form-control" type="text" v-model="newUser.school" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="insert" style="margin-top: 0px">添加</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 修改权限窗口 -->
    <div id="modify_user" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">修改用户权限</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="name">用户名</label>
              <input id="name" class="form-control" type="text" v-model="user.username" placeholder="">
            </div>
            <div class="form-group">
              <label for="new_role">新身份</label>
              <input id="new_role" class="form-control" type="text" v-model="user.role" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="modifyRole" style="margin-top: 0px">修改</button>
              <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 删除用户窗口 -->
    <div id="delete_user" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
          </div>
          <div class="modal-title">
            <h1 class="text-center">删除用户</h1>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="deletename">用户名 *</label>
              <input id="deletename" class="form-control" type="text" v-model="deleteName" placeholder="">
            </div>
            <div class="text-right">
              <button class="btn btn-primary" @click="deleteUser" style="margin-top: 0px">删除</button>
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
    name: "UserList",
    data (){
      return {
        newUser:{username:'',password:'',age:0,role:'',school:'',},
        user:{username:'',role:''},
        users: [
        ],
        currentUser:{
        },
        deleteName:"",
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
        url:"/user/users",
        success:function(data){
          that.users = data;
        },
        error:function () {
          alert("服务器错误,获取用户信息失败!");
        }
      });
    },
    methods: {
      insert:function() {
        let that = this;
        $.ajax({
          type:"post",
          url:"/user/add",
          data:{"username":that.newUser.username,"password":that.newUser.password,
            "role":that.newUser.role,"age":that.newUser.age,"school":that.newUser.school},
          success:function(data){
            if(data==="success"){
              alert("添加用户成功!");
              window.location.reload();
            }else{
              alert("添加用户失败!");
            }
          },
          error:function () {
            alert("服务器错误,添加用户信息失败!");
          }
        });
      },
      modifyRole:function () {

      },
      deleteUser:function () {
        let that = this;
        $.ajax({
          type:"post",
          url:"/user/delete",
          data:{"username":that.deleteName},
          success:function(data){
            if(data==="success"){
              alert("删除用户成功!");
              window.location.reload();
            }else{
              alert("删除用户成功!");
            }
          },
          error:function () {
            alert("服务器错误,删除用户失败!");
          }
        });
      }
    }
  }
</script>

<style scoped>
  .modal-dialog{
    z-index: 9999;
  }
  #userlist{
    padding-left: 50px;
    padding-right: 50px;
  }

  #userlist table tr td{
    height:50px;
  }

  #userlist table tr td a{
    text-decoration: none;
  }
  #userlist .container .form-inline{
    margin-top: 20px;
    margin-bottom: 20px;
  }

  #userlist table thead tr th{
    text-align: center;
  }


</style>

