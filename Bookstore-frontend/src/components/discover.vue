<template>
  <div id="discover" class="container wow fadeInLeft" data-wow-offset="0" data-wow-delay="0.4s">
    <div class="tab" role="tabpanel">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#literature" role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-book"></span> 文学</a></li>
            <li role="presentation"><a href="#education" data-toggle="tab"><span class="icon fa fa-lg fa-graduation-cap"></span> 教育</a></li>
            <li role="presentation"><a href="#biography"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-users"></span> 传记</a></li>
            <li role="presentation"><a href="#history" role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-photo"></span> 历史</a></li>
            <li role="presentation"><a href="#life"  role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-spoon"></span> 生活</a></li>
            <li role="presentation"><a href="#movie" role="tab" data-toggle="tab"><span class="icon fa fa-lg fa-television"></span> 影视</a></li>
            <li role="presentation"><a href="#youth" role="tab" data-toggle="tab">青春文学</a></li>
            <li role="presentation"><a href="#network" role="tab" data-toggle="tab">网络文学</a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content tabs">
            <div role="tabpanel" class="tab-pane fade in active" id="literature">
              <BookWall booktype="literature"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="education">
              <BookWall booktype="education"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="biography">
              <BookWall booktype="biography"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="history">
              <BookWall booktype="history"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="life">
              <BookWall booktype="life"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="movie">
              <BookWall booktype="movie"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="youth">
              <BookWall booktype="youth"/>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="network">
              <BookWall booktype="network"/>
            </div>
        </div>
    </div>
    <div class="page container text-center">
      <!-- /.row.pagination-wrap -->
    </div>
  </div>
</template>

<script >
import BookWall from './BookWall.vue'

export default {
  name: 'discover',
  data () {
    return {}
  },
  methods: {
  },
  components:{
    BookWall
  }
}
</script>

<style scoped>
#discover{
  width: 100%;
  padding:50px 50px 30px 50px;
  background-color: #FFF;
}
#discover h1{
  padding-top: 20px;
  font-family: STXihei, "华文细黑", "Microsoft YaHei", "微软雅黑";
  color:#908a78;
}
.tab .nav-tabs{
  border-bottom:none;
  padding-left: 20px;
  margin-top: 40px;
  margin-right: 100px;
  margin-bottom: 20px;
  position: relative;
  color:#B5B5B5;
}
.tab .nav-tabs li{
  text-align: center;
}
.tab .nav-tabs li a{
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
.tab .nav-tabs li.active a,.tab .nav-tabs li a:hover{
  outline: none;
  color: #6B6B6B;
  background:#FFF;
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 2px solid #6B6B6B;
}
.tab .nav-tabs li:last-child a:before{
  border: none;
}
.tab .tab-content{
  position: relative;
  color: #2C3E50;
  line-height: 26px;
  background: #fff;
  padding: 20px;
}
</style>
