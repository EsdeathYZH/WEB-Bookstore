<template>
  <div id="orderlist">
    <div class="container" >
      <form class="form-inline" id="search-bar" role="form">
        <label for="search-key">搜索栏</label>
        <input id="search-key" type="text" class="form-control" v-model="search_key" placeholder="请输入搜索关键词">
        <select class="form-control" placeholder="bookname" v-model="search_by">
          <option value="bookname">按照书名搜索</option>
          <option value="userId">按照用户搜索</option>
        </select>
        <!--<div class="search-btn btn-info" @click="search"><span class="fa fa-search"></span></div>-->
        <label for="sort" style="margin-left: 20px">排序选项</label>
        <select id="sort" class="form-control" placeholder="none" v-model="sort_by">
          <option value="none">不排序</option>
          <option value="bookname">按照书名排序</option>
          <option value="userId">按照用户排序</option>
          <option value="time">按照时间排序</option>
        </select>
        <select class="form-control"  v-model="ifDescend">
          <option value="ascend">升序</option>
          <option value="descend">降序</option>
        </select>
      </form>
    </div>
    <div id="amount_sum">当前订单的总数量为{{sum_amount}}本</div>
    <table class="table table-striped text-center">
      <thead>
      <tr>
        <th>序号</th>
        <th>图书名称</th>
        <th>用户ID</th>
        <th>数量</th>
        <th>时间</th>
        <th>地址</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for ="(order,index) in this.orders">
        <td>{{index+1}}</td>
        <td><a href="#">{{order.bookname}}</a></td>
        <td>{{order.userId}}</td>
        <td>{{order.amount}}</td>
        <td>{{order.time}}</td>
        <td>{{order.address}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import $ from 'jquery'
  export default {
    name: "OrderList",
    data (){
      return {
        ifDescend:"descend",
        search_key:"",
        search_by:"bookname",
        sort_by:"none",
        origin_orders: [
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
        url:"/orders",
        success:function(data){
          that.origin_orders = data;
        },
        error:function () {
          alert("服务器错误,获取订单信息失败!");
        }
      });
    },
    computed: {
      orders() {
        let temp_data = this.origin_orders;
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
      },
      sum_amount(){
        let sum = 0;
        for(let i=0;i<this.orders.length;i++){
          sum += this.orders[i].amount;
        }
        return sum;
      }
    }
  }
</script>

<style scoped>
  #orderlist{
    padding-left: 50px;
    padding-right: 50px;
  }

  #orderlist table tr td{
    width: 50px;
    height:50px;
  }

  #orderlist table tr td a{
    text-decoration: none;
  }
  #orderlist .container .form-inline{
    margin-top: 20px;
    margin-bottom: 20px;
  }

  #orderlist table thead tr th{
    text-align: center;
  }

  #orderlist #amount_sum{
    margin: 8px 0 12px 20px;
    font-weight: bold;
    font-size: 16px;
  }


</style>
