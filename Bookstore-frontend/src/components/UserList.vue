<template>
  <div id="userlist">
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
      <tr>
        <td></td>
        <td><input class="form-control" type="text" placeholder="用户名" id="username" v-model="user.username"/></td>
        <td><input class="form-control" type="text" placeholder="密码"id="password" v-model="user.password"/></td>
        <td><input class="form-control" type="text" placeholder="年龄" id="age" v-model="user.age"/></td>
        <td><input class="form-control" placeholder="身份"type="text" id="role" v-model="user.role"/></td>
        <td><input class="form-control" placeholder="学校"type="text" id="school" v-model="user.school"/></td>
      </tr>
      <tr>
        <td></td>
        <td colspan="5"><div class="btn btn-success" id="" @click="insert">新增用户</div></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import $ from 'jquery'
  export default {
    name: "UserList",
    data (){
      return {
        user:{username:'',password:'',age:0,role:'',school:'',},
        users: [
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
          data:{"username":that.user.username,"password":that.user.password,
            "role":that.user.role,"age":that.user.age,"school":that.user.school},
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
      }
    }
  }
</script>

<style scoped>
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

